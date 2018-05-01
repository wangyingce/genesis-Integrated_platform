package com.ysyl.common.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.ysyl.common.CodeConst;
import com.ysyl.common.log.service.facade.LogService;
import com.ysyl.common.qrcode.TwoDimensionCode;
import com.ysyl.common.schema.model.IpServiceConfig;
import com.ysyl.common.schema.model.MaxUse;
import com.ysyl.common.schema.model.UtiKey;
import com.ysyl.common.schema.model.YyLog;
import com.ysyl.common.schema.model.Yydconfig;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.util.DateUtil;
import com.ysyl.common.verify.VerifyVo;
import com.ysyl.common.vo.WxFowardVo;
import com.ysyl.weixin.common.util.CommonUtils;
import com.ysyl.weixin.focusUser.service.facade.WeiXinUserInfoService;
import com.ysyl.weixin.pay.utils.CommonUtil;
import com.ysyl.weixin.schema.model.YywxUserInfo;

public class CommonServiceSpringImpl extends GenericDaoHibernate<IpServiceConfig, String> implements CommonService {
	private static CacheManager cacheManager = CacheManager.getIntance("Common");
	
	private LogService logService;
	
	private DataSource dataSource;
	/**
	 * 根据servercode获取接口信息
	 * @param 对应ip表中serverCode字段
	 * @param type:wx,alipay,传空值只返回地址不带token
	 * @throws Exception 
	 */
	public String getIpServiceWithTokenByCode(String serverCode,String type) throws Exception{
		String url = "";
		//获取系统环境变量
		String environmenTypeCode = System.getProperty("environmenTypeCode");
		//如果是微信
		if(CodeConst.ChannelType.wx.equals(type)){
			//获取上一次token获取的时间,如果>=118分钟则重新获取
			Object result = cacheManager.getCache("access_token_time");
			String lastTime = "";
			String access_token = "";
			boolean isGetToken = true;
			if (result != null) {
				lastTime = (String) result;
				//获取时间差
				Long timDiff = DateUtil.getTimeDifferenceMin(lastTime);
				//如果小于118分钟则不获取了
				if(timDiff < 118){
					isGetToken = false;
					access_token = (String)cacheManager.getCache("access_token");
				}
			}
			if(isGetToken){
				//如果没有缓存或者>118分钟则重新获取微信token
				String appId = this.getYydconfig(CodeConst.ConfigCode.WEIXIN,CodeConst.ConfigCode.APPID).getConfigValue();
				String secret = this.getYydconfig(CodeConst.ConfigCode.WEIXIN,CodeConst.ConfigCode.APPSECRET).getConfigValue();
				access_token = CommonUtils.accessTokenGet(appId,secret);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowDate = df.format(new Date());
				//放入缓存
				cacheManager.putCache("access_token", access_token);
				cacheManager.putCache("access_token_time", nowDate);
			}
			
			result = cacheManager.getCache(serverCode+environmenTypeCode);
			if (result != null) {
				url = (String)result;
			}else{
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("id.serverCode", serverCode);
				queryRule.addEqual("id.environmentCode", environmenTypeCode);
				queryRule.addEqual("validStatus", "1");
				IpServiceConfig ipServiceConfig = this.findUnique(IpServiceConfig.class,queryRule);
				url = this.getUrl(ipServiceConfig);
				cacheManager.putCache(serverCode+environmenTypeCode, url);
			}
			url = url + access_token;
			//url放缓存
		}else{
			String key = cacheManager.generateCacheKey("getIpServiceWithTokenByCode",serverCode,type);
			Object result = cacheManager.getCache(key);
			if (result != null) {
				url = (String)result;
			}else{
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("id.serverCode", serverCode);
				queryRule.addEqual("id.environmentCode", environmenTypeCode);
				IpServiceConfig ipServiceConfig = this.findUnique(IpServiceConfig.class,queryRule);
				url = this.getUrl(ipServiceConfig);
				cacheManager.putCache(key, url);
			}
		}
		return url;
	}
	
	/**
	 * 获取微信网页授权全地址公用方法
	 * @param code 微信端网页授权发过来的code
	 * @throws Exception
	 */
	public String getWxAuthorizeAccessUrl(String code) throws Exception{
		//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		//先获取发送地址，不带事件类token
		String appId = "";
		String secret = "";
		String sendUrl = this.getIpServiceWithTokenByCode(CodeConst.ServerCode.WXauthorizeaccess, "");
		//组装微信网页授权URL所需参数，从dconfig表获取appid、secret
		Yydconfig yydconfigAppId = this.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.APPID);
		Yydconfig yydconfigSecret = this.getYydconfig(CodeConst.ConfigCode.WEIXIN, CodeConst.ConfigCode.APPSECRET);
		appId = yydconfigAppId.getConfigValue();
		secret = yydconfigSecret.getConfigValue();
		//再组装全部的地址
		sendUrl = sendUrl + "?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		return sendUrl;
	}
	
	
	private String getUrl(IpServiceConfig ipServiceConfig){
		StringBuffer url=new StringBuffer();
		if(ipServiceConfig!=null&&!"".equals(ipServiceConfig)){
			if ((ipServiceConfig.getProteclType() != null) && 
			        (!("".equals(ipServiceConfig.getProteclType())))) {
		        url.append(ipServiceConfig.getProteclType()).append("://");
		       }
		      if ((ipServiceConfig.getServerIP() != null) && 
		        (!("".equals(ipServiceConfig.getServerIP())))) {
		        url.append(ipServiceConfig.getServerIP());
		      }
		      if ((ipServiceConfig.getServerPort() != null) && 
		        (!("".equals(ipServiceConfig.getServerPort())))) {
		        url.append(":").append(ipServiceConfig.getServerPort());
		      }
		      if ((ipServiceConfig.getServerAppName() != null) && 
		        (!("".equals(ipServiceConfig.getServerAppName())))) {
		        url.append("/").append(ipServiceConfig.getServerAppName());
		      }
		}
		  
		return url.toString();
	}

	public Yydconfig getYydconfig(String type, String configCode) {
		String key = cacheManager.generateCacheKey("getYydconfig", type,configCode);
		Object result = cacheManager.getCache(key);
		if(result!=null){
			return (Yydconfig)result;
		}else{
			QueryRule qr = QueryRule.getInstance();
			qr.addEqual("id.type", type);
			qr.addEqual("id.configCode", configCode);
			Yydconfig config = super.findUnique(Yydconfig.class, qr);
			cacheManager.putCache(key, config);
			return config;
		}
	}

	/**
	 * 生成二维码支付名牌
	 */
	public String getQRCode(String merchantId,String nickName) {
		String ip = null;
		try {
			ip  = this.getIpServiceWithTokenByCode(CodeConst.ServerCode.ysyl, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String args = ip+ "/cm/aw.do?inputId="+merchantId+"&type=wp";
		//获取路径
		String rootPath = System.getProperty("b2cweb.root");  
		//设置保存的服务器目录
		String imgPath = rootPath+"/qrcode/"+merchantId+".png";  
        String encoderContent = args;  
        TwoDimensionCode handler = new TwoDimensionCode();  
        String type = "";
        
        //wx二维码-s
        type="wx";
        String wximgPath= rootPath+"/qrcode/wx-"+merchantId+".png";  
      	String wxlogoPath = rootPath+"/qrcode/qrcodelogo.png";  
        handler.crtQRCode(encoderContent, wximgPath, wxlogoPath,nickName,type); 
        //wx二维码-e
        
        //zfb二维码-s
        type="zfb";
        String zfbimgPath= rootPath+"/qrcode/zfb-"+merchantId+".png";  
        String zfblogoPath = rootPath+"/qrcode/zfb.png"; 
        handler.crtQRCode(encoderContent, zfbimgPath, zfblogoPath,nickName,type); 
        //zfb二维码-e
        
        String topimg = rootPath+"/qrcode/top.png";
        String bottomimg = rootPath+"/qrcode/bottom.png";
        handler.crtShowImage(imgPath,topimg,bottomimg,wximgPath,zfbimgPath,nickName);
        
        String qrcodeUrl = "";
        String qrcodePort  = "";
        String ipAdd = "";
		HttpServletRequest requet=ServletActionContext.getRequest();
		//如果是本地调用获取本地服务地址，如果是服务器调用获取服务器地址
		if(requet!=null){
			ipAdd =  requet.getScheme()+"://"+getLocalIP();
			qrcodePort = ":"+requet.getServerPort();
			if(qrcodePort!=null&&qrcodePort!=""){
				qrcodeUrl = ipAdd + qrcodePort + "/qrcode/"+merchantId+".png";
			}
			
		}
        return qrcodeUrl;
    }
	
	/**
	 * 获取服务器IP
	 * @return
	 */
	public static String getLocalIP(){   
		InetAddress addr = null;   
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

		byte[] ipAddr = addr.getAddress();   
		String ipAddrStr = "";   
		for (int i = 0; i < ipAddr.length; i++) {   
			if (i > 0) {   
				ipAddrStr += ".";   
			}   
			ipAddrStr += ipAddr[i] & 0xFF;   
		}   
		return ipAddrStr;   
}
	
	/**
	 * 微信网页授权地址跳转service服务
	 * @param wxFowardVo 入参，目前必需要字段code
	 * @param isGetUserInfo true|WxFowardVo.YywxUserInfo包含用户详细信息，false|WxFowardVo.YywxUserInfo中仅openid有值
	 * @return 返回网页授权对应用户信息YywxUserInfo（WxFowardVo中的子对象形式返回）
	 * @throws Exception
	 */
	public WxFowardVo fowardUrlForWx(WxFowardVo wxFowardVo,boolean isGetUserInfo) throws Exception{
		//先获取获取微信网页授权全地址
		YywxUserInfo yyWxUserInfo = null;
		String sendUrl = this.getWxAuthorizeAccessUrl(wxFowardVo.getCode());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDate = df.format(new Date());
		System.out.println("拉取用户信息="+nowDate+" "+sendUrl);
		JSONObject jsonObject = CommonUtil.httpsRequest(sendUrl, "GET", null);
		if (null != jsonObject) {
			String openId  = jsonObject.getString("openid");
			String access_token = jsonObject.getString("access_token");
			//如果需要拉取用户详细信息
			if(isGetUserInfo){
				//拉取用户信息(需scope为 snsapi_userinfo)
				String userInfoUrl = this.getIpServiceWithTokenByCode(CodeConst.ServerCode.WXuserinfo, "")+"?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
				jsonObject = CommonUtil.httpsRequest(userInfoUrl, "GET", null);
				Gson gson = new Gson();
				yyWxUserInfo = gson.fromJson(jsonObject.toString(), YywxUserInfo.class);
				//保存时间
				//yyWxUserInfo.setSubscribe_time(new Date());
				//拉取完后保存
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("openid", openId);
				YywxUserInfo yyWxUserInfoOld = this.findUnique(YywxUserInfo.class,queryRule);
				if(yyWxUserInfoOld != null){
					DataUtils.copySimpleObject(yyWxUserInfo,yyWxUserInfoOld,false);
				}
//				WeiXinUserInfoService weiXinUserInfoService = (WeiXinUserInfoService) applicationContext.getBean("weiXinUserInfoService");
//				weiXinUserInfoService.saveUserInfo(yyWxUserInfo);
			}else{
				yyWxUserInfo = new YywxUserInfo();
				yyWxUserInfo.setOpenid(openId);
			}
			wxFowardVo.setYywxUserInfo(yyWxUserInfo);
		}
		//生成单号测试
		//this.getNo(CodeConst.TableName.yywaresownerNo, CodeConst.NoCode.WaresOwnerNo);
//		yyWxUserInfo= new YywxUserInfo();
//		yyWxUserInfo.setNickname("测试");
//		wxFowardVo.setYywxUserInfo(yyWxUserInfo);
		return wxFowardVo;
	}
	
	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 */
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
	public  String generateString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }
        return sb.toString();  
    }
	
	/**
	 * 获取随机编码
	 * @param tableName-表明,对应utikey表tablename字段
	 * @param fieldCode-单号编码,对应utikey表fieldCode字段
	 * @return 编码
	 * @throws Exception
	 */
	public static final String AllChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String AllNums = "0123456789";  
	public String getNoRandom(String tableName,String fieldCode) throws Exception{
		java.sql.Connection conn = null;
		java.sql.PreparedStatement stat = null;
		String returnNo = "";
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tableName", tableName);
		queryRule.addEqual("fieldCode", fieldCode);
		queryRule.addEqual("validStatus", "1");
		UtiKey utiKey = this.findUnique(UtiKey.class,queryRule);
		//获取该单号生成的长度
		Integer collengTh = utiKey.getColLength();
		//获取该单号的首字母
		String headId = utiKey.getHeadId();
		while (true) {
			//生成根据配置的对应长度随机编码
			StringBuffer sb = new StringBuffer(); 
			Random random = new Random();  
			for (int i = 0; i < collengTh; i++) {
				if("1".equals(utiKey.getType())){
					sb.append(AllNums.charAt(random.nextInt(AllNums.length())));  
				}else{
					sb.append(ALLCHAR.charAt(random.nextInt(AllChars.length())));  
				}
			}
			returnNo = sb.toString();
			//如果首字母不为空，则把首字母加上后返回
			if(!"".equals(headId) && headId != null){
				returnNo = headId + returnNo;
			}

			//检查单号是否重复
			QueryRule queryRule1 = QueryRule.getInstance();
			queryRule1.addEqual("userNo", returnNo);
			MaxUse maxUse = this.findUnique(MaxUse.class,queryRule1);
			//如果为空，直接插入占号
			if(maxUse ==null){
				try{
					conn = dataSource.getConnection();
					conn.setAutoCommit(false);
					String sql = "insert into maxuse(tablename,fieldcode,userno) values(?,?,?)";
					stat = conn.prepareStatement(sql);
					stat.setString(1, tableName);
					stat.setString(2, fieldCode);
					stat.setString(3, returnNo);
					stat.executeUpdate();
					conn.commit();
					conn.setAutoCommit(true);
				}catch(Exception ex4){
					throw ex4;
				}finally{
					if(stat!=null){
						try {
							stat.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(conn!=null){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return returnNo;
			}else{
				continue;
			}
		}
	}

	/**
	 * 获取顺序编码
	 * @param tableName-表明,对应utikey表tablename字段
	 * @param fieldCode-单号编码,对应utikey表fieldCode字段
	 * @return 编码
	 * @throws Exception
	 */
	public String getNoOrder(String tableName,String fieldCode) throws Exception{
		java.sql.Connection conn = null;
		java.sql.PreparedStatement stat = null;
		String returnNo = "";
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("tableName", tableName);
		queryRule.addEqual("fieldCode", fieldCode);
		queryRule.addEqual("validStatus", "1");
		UtiKey utiKey = this.findUnique(UtiKey.class,queryRule);
		//获取该单号生成的长度
		Integer collengTh = utiKey.getColLength();
		//获取该单号的首字母
		String headId = utiKey.getHeadId();
		while (true) {
			//获取目前使用的最大单号
			QueryRule queryRule1 = QueryRule.getInstance();
			queryRule1.addEqual("tableName", tableName);
			queryRule1.addEqual("fieldCode", fieldCode);
			MaxUse maxUse = this.findUnique(MaxUse.class,queryRule1);
			//如果为空，直接根据长度插入对应的号数占号
			if(maxUse ==null){
				//获得首字母长度
				int headLength = headId.length();
				int forTimes = collengTh - headLength;
				//根据长度产生对应位数的000开头的数字
				for (int i = 0; i < forTimes; i++) {
					if(i == forTimes-1){
						returnNo = returnNo +"1";
					}else{
						returnNo = returnNo +"0";
					}
				}
				//如果首字母不为空，则把首字母加上后返回
				if(!"".equals(headId) && headId != null){
					returnNo = headId + returnNo;
				}
				try{
					conn = dataSource.getConnection();
					conn.setAutoCommit(false);
					String sql = "insert into maxuse(tablename,fieldcode,userno) values(?,?,?)";
					stat = conn.prepareStatement(sql);
					stat.setString(1, tableName);
					stat.setString(2, fieldCode);
					stat.setString(3, returnNo);
					stat.executeUpdate();
					conn.commit();
					conn.setAutoCommit(true);
					break;
				}catch(Exception ex4){
					throw ex4;
				}finally{
					if(stat!=null){
						try {
							stat.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if(conn!=null){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				returnNo = maxUse.getUserNo();
				long num = new Long(returnNo) +1;
				returnNo = new Long(num).toString();
				maxUse.setUserNo(returnNo);
				maxUse.setInsertTime(new Date());
				break;
			}
		}
		return returnNo;
	}
	/**
	 * 根据类型发送短信验证码
	 * @param type
	 */
	public void sendMessageByType(String type, String moblie,Integer verifyNo) {
		String url = "https://eco.taobao.com/router/rest";
		String appkey = "23352856";
		String secret = "4ddc33437aac4aa3597aa8b703c2a583";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		if(type=="1"||"1".equals(type)){
			//可选 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
			req.setExtend("123456");
			//必须 短信类型，传入值请填写normal
			req.setSmsType("normal");
			//可选 短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
			req.setSmsFreeSignName("惠好近");
			//必须 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
			//板名称: 用户注册验证码
			//模板ID: SMS_5052276
			//*模板内容:
			//验证码${code}，您正在注册成为${product}用户，感谢您的支持！
			req.setSmsParamString("{'code':'"+verifyNo+"','product':'灰鲸摄影'}");
			//必须 短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
			req.setRecNum(moblie);
			//短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
			req.setSmsTemplateCode("SMS_8155814");
		}
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//成功返回码{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"100663458616^1101070900185","success":true},"request_id":"r4lu3uaf87g2"}}
		//失败返回码{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.MOBILE_NUMBER_ILLEGAL","sub_msg":"号码格式错误","request_id":"ishfhkt4jeym"}}
		System.out.println(rsp.getBody());
	}
	
	/**
	 * 根据传入的发送对象发送短信
	 * @param sendMessageVo
	 */
	public boolean sendMessageBySendVo(VerifyVo verifyVo) throws Exception {
		boolean successFlag = false;
		//String url = "https://eco.taobao.com/router/rest";
		//String appkey = "23352856";
		//String secret = "4ddc33437aac4aa3597aa8b703c2a583";
		//先获取对应的url、appkey、secret
		//获取url
		String url = this.getIpServiceWithTokenByCode(CodeConst.ServerCode.AliDayuMessage,"");
		//获取appkey
		String appkey = this.getYydconfig(CodeConst.ConfigCode.alidayu, CodeConst.ConfigCode.alidayuappkey).getConfigValue();
		//获取secret
		String secret = this.getYydconfig(CodeConst.ConfigCode.alidayu, CodeConst.ConfigCode.alidayusecret).getConfigValue();
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//获取发送模板
		String SmsTemplateCode = this.getYydconfig(CodeConst.ConfigCode.alidayu,verifyVo.getMessageTemplate()).getConfigValue();
		//根据传入的商户id获取短信签名
		String SmsFreeSignName = this.getYydconfig(CodeConst.ConfigCode.smsfreesignname,verifyVo.getInputId()).getConfigValue();
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		//验证码模板
		if(CodeConst.ShortMessageTemplate.SmsTemplateCode_1.equals(verifyVo.getMessageTemplate())){
			//可选 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
			req.setExtend(verifyVo.getInputId());
			//必须 短信类型，传入值请填写normal
			req.setSmsType("normal");
			//可选 短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
			req.setSmsFreeSignName(SmsFreeSignName);
			//必须 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
			//板名称: 用户注册验证码
			//模板ID: SMS_5052276
			//*模板内容:
			//验证码${code}，您正在注册成为${product}用户，感谢您的支持！
			req.setSmsParamString("{'code':'"+verifyVo.getVerifyNo()+"','product':'"+verifyVo.getProduct()+"'}");
			//必须 短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
			req.setRecNum(verifyVo.getPhoneNumber());
			//短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
			req.setSmsTemplateCode(SmsTemplateCode);
		}
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		String returnXml = "";
		
		try {
			rsp = client.execute(req);
			returnXml = rsp.getBody();
			//解析返回的rsp看看是否成功
			if("error_response".indexOf(rsp.getBody())>-1){
				successFlag = false;
			}else{
				successFlag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		//保存日志
		YyLog yyLog = new YyLog();
		yyLog.setCertiNo(verifyVo.getPhoneNumber());
		yyLog.setInputId(verifyVo.getInputId());
		yyLog.setSendXml(req.getSmsParam());
		yyLog.setUrl(url);
		yyLog.setCertiName("阿里大于短信发送");
		yyLog.setReslutXml(returnXml);
		yyLog.setValidStatus("1");
		logService.saveLog(yyLog);
		//成功返回码{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"100663458616^1101070900185","success":true},"request_id":"r4lu3uaf87g2"}}
		//失败返回码{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.MOBILE_NUMBER_ILLEGAL","sub_msg":"号码格式错误","request_id":"ishfhkt4jeym"}}
		return successFlag;
	}
	
	/**
	 * 将用户信息存session
	 * @param openId 微信id
	 * @param userCode 用户代码
	 * @param userName 用户名称
	 */
	public void setSession(HttpSession session,String openId,String userCode,String userName)  throws Exception{
		if(openId != null && !"".equals(openId)){
			session.setAttribute("OpenId", openId);
		}
		if(userCode != null && !"".equals(userCode)){
			session.setAttribute("UserCode", userCode);
		}
		if(userName != null && !"".equals(userName)){
			session.setAttribute("UserName", userName);
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	
	
}
