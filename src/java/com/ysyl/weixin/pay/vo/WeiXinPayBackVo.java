package com.ysyl.weixin.pay.vo;

public class WeiXinPayBackVo {
	private String sign_type;//签名方式
	private String input_charset;//字符集
	private String sign ;//签名
	private String trade_mode;//交易模式trade_mode 是Int 1-即时到账
	private String trade_state ;//交易状态trade_state 是Int 支付结果：	0—成功 其他保留
	private String partner ;//商户号partner 是String(10) 商户号，也即之前步骤的partnerid
	private String bank_type ;//付款银行bank_type 是String(16) 银行类型，在微信中使用WX
	private String bank_billno ;//银行订单号bank_billno 否String(32) 银行订单号
	private String total_fee ;//总金额total_fee 是Int 支付金额，单位为分，如果discount有值，通知的total_fee + discount =	请求的total_fee
	private String fee_type ;//币种fee_type 是Int 现金支付币种,目前只支持人民币,	默认值是1-人民币
	private String notify_id ;//通知ID notify_id 是String(128) 支付结果通知id，对于某些特定商户，只返回通知id，要求商户据此查询交易结果
	private String transaction_id ;//订单号transaction_id 是String(28)交易号，28 位长的数值，其中前10	位为商户号，之后8 位为订单产生的日期，如20090415，最后10位是流水号。
	private String out_trade_no ;//商户订单号out_trade_no 是String(32) 商户系统的订单号，与请求一致
	private String attach ;//商户数据包attach 否String(127) 商户数据包，原样返回，空参数不传递
	private String time_end ;//支付完成时间time_end 是String(14) 支付完成时间， 格式为yyyyMMddhhmmss，如2009年12月27日9点10分10秒表示为20091227091010。时区为GMT+8	beijing。
	private String transport_fee;//物流费用transport_fee 否Int 物流费用，单位分，默认0。如果有值， 必须保证transport_fee +product_fee = total_fee
	private String product_fee ;//物品费用product_fee 否Int 物品费用，单位分。如果有值，必须保证transport_fee +product_fee=total_fee
	private String discount ;//折扣价
	private String appId;//公众号id；字段来源：商户注册具有支付权限的公众号成功后即可获得；传入方式：由商户直接传入。
	private String timeStamp;//时间戳；字段来源：商户生成从1970 年1 月1 日00：00：00 至今的秒数，即当前的时间；由商户生成后传入。取值范围：32字符以下
	private String nonceStr;//随机字符串；字段来源：商户生成的随机字符串；取值范围：长度为32 个字符以下。由商户生成后传入。取值范围：32 字符以下
	private String openId;//支付该笔订单的用户ID，商户可通过公众号其他接口为付款用户服	务。
	private String appSignature;//签名；字段来源：对前面的其他字段与appKey 按照字典序排序后，使用SHA1 算法得到的结果。由商户生成后传入
	private String isSubscribe;//用户是否关注了公众号。1 为关注，0 为未关注。
	private String isSuccess;//是否解析成功 success成功 fail失败
	private String message;//失败时 失败原因
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSignature() {
		return appSignature;
	}
	public void setAppSignature(String appSignature) {
		this.appSignature = appSignature;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getBank_billno() {
		return bank_billno;
	}
	public void setBank_billno(String bank_billno) {
		this.bank_billno = bank_billno;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(String product_fee) {
		this.product_fee = product_fee;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getTrade_mode() {
		return trade_mode;
	}
	public void setTrade_mode(String trade_mode) {
		this.trade_mode = trade_mode;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(String transport_fee) {
		this.transport_fee = transport_fee;
	}
}
