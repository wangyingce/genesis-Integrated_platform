package com.ysyl.weixin.merchant.web;
import ins.framework.common.Page;
import ins.framework.web.Struts2Action;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.util.DateUtil;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.merchant.vo.MerchantVo;
import com.ysyl.weixin.schema.model.YywxMerchant;
/**
 * 商户功能
 * 
 * @author Scorpiō
 * @category Amicus Plato, sed magis amica veritas
 */
public class MerchantAction extends Struts2Action {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(MerchantAction.class);
	private MerchantService merchantService;
	private MerchantVo merchantVo;
	private String merchantId;
	private String nickName;
	private String corporation;
	private String identifyNumber;
	private String bankCname;
    private String account;
	private YywxMerchant merchant;
	private CommonService commonService;
	private String qrcodePath;
	private String rqstUrl;
	private String unionid;
	private String registermobile;
	private String address;
	/**
	 * 分页查询商户信息
	 * @return (page)json
	 * @throws Exception 
	 */
	public String queryMerchant() throws Exception {
		if(merchantVo!=null){
			Page page = merchantService.queryMerchantByMerchantVo(merchantVo);
			List list = page.getResult();
			if(list!=null&&list.size()>0){
				JSONObject alljson = new JSONObject();
				JSONArray json = JSONArray.fromObject(page.getResult());
				alljson.put("jsondata", json);
				alljson.put("page", merchantVo.getPageNo());// 当前页
				alljson.put("total", page.getTotalPageCount());// 总页数
				alljson.put("record", page.getTotalCount());// 总条数
				logger.debug(alljson);
				PrintWriter out = null;
				try {
					this.getResponse().setCharacterEncoding("UTF-8");
					out = this.getResponse().getWriter();
					out.print(alljson);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(out!=null){
						out.close();
					}
				}
			}else{
			}

		}
		return null;
	}
	/**
	 * 加载页面信息， 页面初始化时调用
	 * @return (merchant)json
	 * @throws Exception
	 */
	public String loadMerchant() throws Exception {
		if(rqstUrl!=null&&!"".equals(rqstUrl)&&rqstUrl.indexOf("=")>0){
			if(rqstUrl.indexOf("xcode=")>0){
				String merchantId = rqstUrl.substring(rqstUrl.indexOf("=")+1, rqstUrl.length());
				merchant = new YywxMerchant();
				merchant = merchantService.queryMerchantByMerchantId(merchantId);
			}else if(rqstUrl.indexOf("ycode=")>0){
				String nickName = rqstUrl.substring(rqstUrl.indexOf("=")+1, rqstUrl.length());
				nickName = URLDecoder.decode(nickName,"UTF-8");
				merchant = new YywxMerchant();
				merchant = merchantService.queryMerchantByNickName(nickName);
			}
			
			if(merchant!=null){
				Gson g = new Gson();
				String json = g.toJson(merchant);
				this.getResponse().setCharacterEncoding("UTF-8");
				ServletActionContext.getResponse().getWriter().print(json);
			}
		}
		return null;

	}

	/**
	 * 预留
	 * @return null
	 * @throws Exception
	 */
	public String initMerchant() throws Exception {
		return "initMerchant";
	}
	/**
	 * 保存或更新商户信息
	 * @return merchantId,qrcodePath
	 * @throws Exception
	 */
	public String saveMerchant() throws Exception {
		if(merchantId!=null&&!"".equals(merchantId)){ 
			merchant = new YywxMerchant();
			merchant = merchantService.queryMerchantByMerchantId(merchantId);
		}
		if(merchant!=null){
			merchant.setBankCname(URLDecoder.decode(bankCname,"UTF-8"));
			merchant.setCorporation(URLDecoder.decode(corporation,"UTF-8"));
			merchant.setNickName(URLDecoder.decode(nickName,"UTF-8"));
			merchant.setAccount(account);
			merchant.setRegistermobile(registermobile);
			merchant.setIdentifyNumber(identifyNumber);
			merchant.setAddress(URLDecoder.decode(address,"UTF-8"));
			merchant.setUwflag("3");
			merchantService.saveOrUpdateMerchant(merchant);
			qrcodePath = merchant.getQrcodePath();
			merchantId = merchant.getMerchantId();
			Gson g = new Gson();
			String json = g.toJson(merchant);
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
		}else{
			YywxMerchant merchant = new YywxMerchant();
			//随机数+固定组合ID
			String generateString = commonService.generateString(20);
			String mctId  ="yywx"+generateString+"mcid";
			merchant.setMerchantId(mctId);
			merchant.setBankCname(URLDecoder.decode(bankCname,"UTF-8"));
			merchant.setCorporation(URLDecoder.decode(corporation,"UTF-8"));
			merchant.setNickName(URLDecoder.decode(nickName,"UTF-8"));
			merchant.setAccount(account);
			merchant.setAddress(URLDecoder.decode(address,"UTF-8"));
			merchant.setRegistermobile(registermobile);
			merchant.setIdentifyNumber(identifyNumber);
			String newdate = DateUtil.dateToString(new Date(), 2);
			merchant.setInputTime(new Long(newdate));
			merchant.setUwflag("3");
			merchant.setValid("1");
			merchant.setFlag("1");
			//生产二维码
			qrcodePath = commonService.getQRCode(mctId,merchant.getNickName());
			merchant.setQrcodePath(qrcodePath);
			merchantService.saveOrUpdateMerchant(merchant);
			merchantId = mctId;
			Gson g = new Gson();
			String json = g.toJson(merchant);
			this.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(json);
			logger.debug(json);
		}
		return null;
	}

	
	/**
	 * 生成二维码
	 * @return qrcodePath：二维码路径
	 * @throws Exception
	 */
	public String createQRcode() throws Exception {
		merchant = new YywxMerchant();
		merchant = merchantService.queryMerchantByMerchantId(merchantId);
		qrcodePath = commonService.getQRCode(merchant.getMerchantId(),merchant.getNickName());
		merchant.setQrcodePath(qrcodePath);
		Gson g = new Gson();
		String json = g.toJson(merchant);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		logger.debug(json);
		return null;
	}
	
    /**
     * 检查registermobile是否已存在
     * @return （tmpMerchant）json
     */
	public String checkMerchant() throws Exception {
//		nickName = URLDecoder.decode(nickName,"UTF-8");
//		this.registermobile=
		//如果merchantId不为空则视为更新
		String cankeydownthis="1";
//		List<YywxMerchant> checkMerchantList = merchantService.queryMerchantListByNickName(nickName);
		List<YywxMerchant> checkMerchantList = merchantService.queryMerchantListByReportMobile(registermobile);
		if(checkMerchantList!=null&&checkMerchantList.size()>0){
			for(YywxMerchant oldMer : checkMerchantList){
				if(registermobile == oldMer.getRegistermobile()||registermobile.equals(oldMer.getRegistermobile())){
					//不论merchantId是否是空，merchantId不同，说明不是自己
					if((oldMer.getMerchantId())!=merchantId&&!(oldMer.getMerchantId()).equals(merchantId)){
						cankeydownthis = "0";
						break;
					}
				}
			}
		}
		YywxMerchant tmpMerchant = new YywxMerchant();
		if(cankeydownthis=="1"||"1".equals(cankeydownthis)){
			tmpMerchant.setNickName("OKAY");
		}else{
			tmpMerchant.setNickName("NO");
		}
		Gson g = new Gson();
		String json = g.toJson(tmpMerchant);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
	}

	/**
	 * 根据商户id获取商户信息
	 * @return (merchantVo)json
	 * @throws Exception
	 */
	public String getMerchantById() throws Exception{
		YywxMerchant merchant = merchantService.queryMerchantByMerchantId(merchantId);
		//防止商户信息泄漏，只需要名字及id，如需其他信息再添加
		YywxMerchant merchantVo = new YywxMerchant();
		merchantVo.setMerchantId(merchant.getMerchantId());
		merchantVo.setRegistermobile(merchant.getRegistermobile());
		merchantVo.setNickName(merchant.getNickName());
		Gson g = new Gson();
		String json = g.toJson(merchantVo);
		this.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return null;
	}



	public MerchantService getMerchantService() {
		return merchantService;
	}

	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}

	public MerchantVo getMerchantVo() {
		return merchantVo;
	}

	public void setMerchantVo(MerchantVo merchantVo) {
		this.merchantVo = merchantVo;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public YywxMerchant getMerchant() {
		return merchant;
	}

	public void setMerchant(YywxMerchant merchant) {
		this.merchant = merchant;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getQrcodePath() {
		return qrcodePath;
	}

	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public String getBankCname() {
		return bankCname;
	}

	public void setBankCname(String bankCname) {
		this.bankCname = bankCname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRqstUrl() {
		return rqstUrl;
	}

	public void setRqstUrl(String rqstUrl) {
		this.rqstUrl = rqstUrl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRegistermobile() {
		return registermobile;
	}

	public void setRegistermobile(String registermobile) {
		this.registermobile = registermobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
