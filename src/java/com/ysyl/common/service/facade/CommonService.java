package com.ysyl.common.service.facade;

import javax.servlet.http.HttpSession;

import com.ysyl.common.schema.model.Yydconfig;
import com.ysyl.common.verify.VerifyVo;
import com.ysyl.common.vo.WxFowardVo;


/**
 * 公共service,查询ip,代码翻译等公共数据库查询服务
 * @author ysyl
 *
 */
public interface CommonService{
	
	/**
	 * 根据servercode获取接口信息
	 * @param serverCode 对应ip表中serverCode字段
	 * @param type:wx,alipay,传空值只返回地址不带token
	 */
	public String getIpServiceWithTokenByCode(String serverCode,String type) throws Exception;
	
	/**
	 * 获取微信网页授权全地址公用方法
	 * @param code 微信端网页授权发过来的code
	 * @throws Exception
	 */
	public String getWxAuthorizeAccessUrl(String code) throws Exception;
	
	/**
	 * 根据type 及configCode获取配置
	 * */
	public Yydconfig getYydconfig(String type,String configCode);
	
	/**
	 * 获取商户二维码
	 * @param merchantId
	 * @param nickName 
	 * @return
	 */
	public String  getQRCode(String merchantId, String nickName);
	
	/**
	 * 微信网页授权地址跳转service服务
	 * @param wxFowardVo 入参，目前必需要字段code
	 * @param isGetUserInfo true|WxFowardVo.YywxUserInfo包含用户详细信息，false|WxFowardVo.YywxUserInfo中仅openid有值
	 * @return 返回网页授权对应用户信息YywxUserInfo（WxFowardVo中的子对象形式返回）
	 * @throws Exception
	 */
	public WxFowardVo fowardUrlForWx(WxFowardVo wxFowardVo,boolean isGetUserInfo) throws Exception;

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * @param length
	 * @return
	 */
	public  String generateString(int length);
	
	/**
	 * 获取随机编码
	 * @param tableName-表名,对应utikey表tablename字段
	 * @param fieldCode-单号编码,对应utikey表fieldCode字段
	 * @return 编码
	 * @throws Exception
	 */
	public String getNoRandom(String tableName,String fieldCode) throws Exception;
	
	/**
	 * 获取顺序编码
	 * @param tableName-表名,对应utikey表tablename字段
	 * @param fieldCode-单号编码,对应utikey表fieldCode字段
	 * @return 编码
	 * @throws Exception
	 */
	public String getNoOrder(String tableName,String fieldCode) throws Exception;

	/**
	 * 根据类型发送短信验证码
	 * @param type
	 * @param moblie 
	 * @param verifyNo 
	 */
	public void sendMessageByType(String type, String moblie, Integer verifyNo);
	
	/**
	 * 根据传入的发送对象发送短信
	 * @param verifyVo
	 * @return true/false 成功/失败
	 */
	public boolean sendMessageBySendVo(VerifyVo verifyVo) throws Exception;
	
	/**
	 * 将用户信息存session
	 * @param openId 微信id
	 * @param userCode 用户代码
	 * @param userName 用户名称
	 */
	public void setSession(HttpSession session,String openId,String userCode,String userName)  throws Exception;
	
}
