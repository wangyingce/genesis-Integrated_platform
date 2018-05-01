//集中支付 对收款人信息的校验 add by liudenghui

/* 校验身份证号 */
function checkIdentifyNumber(field){
	 if(field.value!=null && field.value!=""){
	 	 var fieldvalue=trim(field.value);
	 	 var rule=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	 	 if(!(rule.test(fieldvalue))){
			alert("身份证号必须为15位或18位，18位时，只有最后一位可以为字母且仅为x或X！");
			field.focus();
	 	 }
	 }
}
/* 校验银行卡号 */
function checkCustAccountNo(field){
	var i=0;
	if(field.value!=null && field.value!=""){
		var fieldvalue=trim(field.value);
		i=fieldvalue.length;
		if(i>30){
			alert("银行账号不能超过30位~！");
			field.focus();
		}
	}
}