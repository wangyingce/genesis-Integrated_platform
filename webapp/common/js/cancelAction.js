//���������̹��ܼ�����������Ҽ���Ctrl+n��shift+F10��F5ˢ�¡��˸�� add by zhangdong 2014.5.30
function KeyDown(){ 
	//��������¼� ��ֹ���˼���Backspace��������С������ı������       
	var ev = window.event; //��ȡevent����   
	//Ĭ�ϲ����⴦��ȫ���ƣ�
	var iscontrol="0";
	if(ev!=null){    
	  	var obj = ev.target || ev.srcElement; //��ȡ�¼�Դ             
	  	//��ȡ��Ϊ�ж��������¼�����          
	  	var t = obj.type || obj.getAttribute('type'); 
	  	//��ȡֻ�����õ����ԣ���ֹҳ�治����Ԫ�ص�backspace�¼���Ч
		var vReadOnly = obj.readOnly;            
		var vDisabled = obj.disabled;            
		//����undefinedֵ���            
		vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;          
		vDisabled = (vDisabled == undefined) ? true : vDisabled;
	  	if((t == "password" || t == "text" || t == "textarea")&&!(vReadOnly||vDisabled)){
	  		//��Щ���Ͳ�����
	  		iscontrol="1";
	  	}
	}
	if("0"==iscontrol){
		//�������в�����������¼�
		if ((window.event.altKey)&&((window.event.keyCode==37)||(window.event.keyCode==39))){  //���� Alt+ ����� ��
			 //���� Alt+ ����� ��
			 event.returnValue=false;
		 }
		if ((event.keyCode==8)||(event.keyCode==116)||(event.ctrlKey && event.keyCode==82)){ //�����˸�ɾ����  //Ctrl + R
			 event.keyCode=0;
			 event.returnValue=false;
		 }
		if ((event.ctrlKey)&&(event.keyCode==78)){ //���� Ctrl+n
		 	event.returnValue=false;
		 }
		if ((event.shiftKey)&&(event.keyCode==121)){ //���� shift+F10
		 	event.returnValue=false;
		 }
		if (window.event.srcElement.tagName == "A" && window.event.shiftKey){ 
		 	window.event.returnValue = false; //���� shift ���������¿�һ��ҳ
		 }
		if ((window.event.altKey)&&(window.event.keyCode==115)){ //����Alt+F4
			 window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
			 return false;
		 }
	 }
 }
