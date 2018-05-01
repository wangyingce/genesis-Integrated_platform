//屏蔽鼠标键盘功能键、屏蔽鼠标右键、Ctrl+n、shift+F10、F5刷新、退格键 add by zhangdong 2014.5.30
function KeyDown(){ 
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外       
	var ev = window.event; //获取event对象   
	//默认不特殊处理，全控制！
	var iscontrol="0";
	if(ev!=null){    
	  	var obj = ev.target || ev.srcElement; //获取事件源             
	  	//获取作为判断条件的事件类型          
	  	var t = obj.type || obj.getAttribute('type'); 
	  	//获取只读禁用等属性，防止页面不控制元素的backspace事件无效
		var vReadOnly = obj.readOnly;            
		var vDisabled = obj.disabled;            
		//处理undefined值情况            
		vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;          
		vDisabled = (vDisabled == undefined) ? true : vDisabled;
	  	if((t == "password" || t == "text" || t == "textarea")&&!(vReadOnly||vDisabled)){
	  		//这些类型不控制
	  		iscontrol="1";
	  	}
	}
	if("0"==iscontrol){
		//控制所有不允许操作的事件
		if ((window.event.altKey)&&((window.event.keyCode==37)||(window.event.keyCode==39))){  //屏蔽 Alt+ 方向键 ←
			 //屏蔽 Alt+ 方向键 →
			 event.returnValue=false;
		 }
		if ((event.keyCode==8)||(event.keyCode==116)||(event.ctrlKey && event.keyCode==82)){ //屏蔽退格删除键  //Ctrl + R
			 event.keyCode=0;
			 event.returnValue=false;
		 }
		if ((event.ctrlKey)&&(event.keyCode==78)){ //屏蔽 Ctrl+n
		 	event.returnValue=false;
		 }
		if ((event.shiftKey)&&(event.keyCode==121)){ //屏蔽 shift+F10
		 	event.returnValue=false;
		 }
		if (window.event.srcElement.tagName == "A" && window.event.shiftKey){ 
		 	window.event.returnValue = false; //屏蔽 shift 加鼠标左键新开一网页
		 }
		if ((window.event.altKey)&&(window.event.keyCode==115)){ //屏蔽Alt+F4
			 window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
			 return false;
		 }
	 }
 }
