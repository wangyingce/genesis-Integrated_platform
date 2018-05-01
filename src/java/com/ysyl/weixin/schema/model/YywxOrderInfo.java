package com.ysyl.weixin.schema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yywxorderinfo")
public class YywxOrderInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 商户订单号
	private String orderid;
	// 公众账号ID-微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;
	// 微信订单号-微信支付分配的商户号
	private String mch_id;
	// 微信订单号
	private String transaction_id;
	// 随机字符串
	private String nonce_str;
	// 签名
	private String sign;
	// 返回状态码
	private String result_code;
	// 错误代码
	private String err_code;
	// 错误代码描述
	private String err_code_des;
	// 设备号
	private String device_info;
	// 用户标识
	private String openid;
	// 是否关注公众账号
	private String is_subscribe;
	// 交易类型-调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定
	private String trade_type;
	// 交易状态\r\nSUCCESS—支付成功\r\nREFUND—转入退款\r\nNOTPAY—未支付\r\nCLOSED—已关闭\r\nREVOKED—已撤销（刷卡支付）\r\nUSERPAYING--用户支付中\r\nPAYERROR--支付失败(其他原因，如银行返回失败)
	private String trade_state;
	// 付款银行
	private String bank_type;
	//总金额-订单总金额，单位为分
	private Integer total_fee;
	// 货币种类-货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String fee_type;
	// 现金支付金额-现金支付金额订单现金支付金额，详见支付金额
	private Integer cash_fee;
	// 现金支付货币类型-货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private String cash_fee_type;
	// 代金券或立减优惠金额-“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
	private Integer coupon_fee;
	// 代金券或立减优惠使用数量
	private Integer coupon_count;
	// 附加数据-商户id
	private String attach;
	// 支付完成时间
	private Long time_end;
	// 交易状态描述-对当前查询订单状态的描述和下一步操作的指引
	private String trade_state_desc;
	// 时间戳
	private Long timeStamp;
	private String flag;
	private String valid;
	//预支付交易会话标识
	private String prepay_id;
	//终端ip
	private String spbill_create_ip;
	//商品描述
	private String body;

	@Id
	@Column(name = "ORDERID")
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Column(name = "APPID")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	@Column(name = "MCH_ID")
	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	@Column(name = "TRANSACTION_ID")
	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	@Column(name = "NONCE_STR")
	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	@Column(name = "SIGN")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "RESULT_CODE")
	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	@Column(name = "ERR_CODE")
	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	@Column(name = "ERR_CODE_DES")
	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	@Column(name = "DEVICE_INFO")
	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	@Column(name = "OPENID")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "IS_SUBSCRIBE")
	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	@Column(name = "TRADE_TYPE")
	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	@Column(name = "TRADE_STATE")
	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	@Column(name = "BANK_TYPE")
	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	@Column(name = "TOTAL_FEE")
	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	@Column(name = "FEE_TYPE")
	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	@Column(name = "CASH_FEE")
	public Integer getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}

	@Column(name = "CASH_FEE_TYPE")
	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	@Column(name = "COUPON_FEE")
	public Integer getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	@Column(name = "COUPON_COUNT")
	public Integer getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}

	@Column(name = "ATTACH")
	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Column(name = "TIME_END")
	public Long getTime_end() {
		return time_end;
	}

	public void setTime_end(Long time_end) {
		this.time_end = time_end;
	}

	@Column(name = "TRADE_STATE_DESC")
	public String getTrade_state_desc() {
		return trade_state_desc;
	}

	public void setTrade_state_desc(String trade_state_desc) {
		this.trade_state_desc = trade_state_desc;
	}
	@Column(name = "TIMESTAMP")
	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Column(name = "FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "VALID")
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
	@Column(name = "SPBILL_CREATE_IP")
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	@Column(name = "BODY")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "PREPAY_ID")
	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

}
