//����֧�� ���տ�����Ϣ��У�� add by liudenghui

/* У�����֤�� */
function checkIdentifyNumber(field){
	 if(field.value!=null && field.value!=""){
	 	 var fieldvalue=trim(field.value);
	 	 var rule=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	 	 if(!(rule.test(fieldvalue))){
			alert("���֤�ű���Ϊ15λ��18λ��18λʱ��ֻ�����һλ����Ϊ��ĸ�ҽ�Ϊx��X��");
			field.focus();
	 	 }
	 }
}
/* У�����п��� */
function checkCustAccountNo(field){
	var i=0;
	if(field.value!=null && field.value!=""){
		var fieldvalue=trim(field.value);
		i=fieldvalue.length;
		if(i>30){
			alert("�����˺Ų��ܳ���30λ~��");
			field.focus();
		}
	}
}