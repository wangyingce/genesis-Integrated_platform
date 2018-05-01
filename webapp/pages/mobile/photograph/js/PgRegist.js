$(function () {
	//手机号
	$("#registerMobile").focus(function () {
		if (this.value == "输入手机号码") {
			this.value = "";
		}
	});
	$("#registerMobile").blur(function () {
		if (this.value == "") {
			this.style.color = "#8f8f8f";
			this.value = "输入手机号码";
		}
	});
	$("#registerMobile").keydown(function () {
		this.style.color = "#000";
	});
	$("#registericonNum").click(function () {
		var a = $("#registerMobile")[0].value;
		if (a != "" && a != "输入手机号码") {
			$("#registerMobile").val("");
			$("#registerMobile").focus();
		}
	});
	
	//昵称
	$("#registerName").focus(function () {
		if (this.value == "输入昵称") {
			this.value = "";
		}
	});
	$("#registerName").blur(function () {
		if (this.value == "") {
			this.style.color = "#8f8f8f";
			this.value = "输入昵称";
		}
	});
	$("#registerName").keydown(function () {
		this.style.color = "#000";
	});
	$("#registericonName").click(function () {
		var a = $("#registerName")[0].value;
		if (a != "" && a != "输入昵称") {
			$("#registerName").val("");
			$("#registerName").focus();
		}
	});
	$("#registerMobile").keydown(function () {
		this.style.color = "#000";
		$("#registericonNum").show();
	});
	
	//验证码
	$("#checkNum").focus(function () {
		if (this.value == "请输入验证码") {
			this.value = "";
		}
	});
	$("#checkNum").blur(function () {
		if (this.value == "") {
			this.style.color = "#8f8f8f";
			this.value = "请输入验证码";
		}
	});
	$("#checkNum").keydown(function () {
		this.style.color = "#000";
	});
	$("#checkNum").click(function () {
		var a = $("#checkNum")[0].value;
		if (a != "" && a != "请输入验证码") {
			$("#checkNum").focus();
		}
	});
	$("#registerMobile").keydown(function () {
		this.style.color = "#000";
		$("#registericonNum").show();
	});
	
	
	$("#getNo-Btn").click(function () {
		var isContine = checkRegist("getNo");
		if(isContine){
			$("#checkPhoneText").hide();
			$("#registerMobile").css("border-color","");
			$("#registerName").css("border-color","");
			var checkPhoneText = document.getElementById("checkPhoneText");
			var phoneNum = $("#registerMobile").val();
			var storeId = $("#storeId").val();
			//发送短信验证码
			$.ajax({    
	            type: "get",
	            url: "/photograph/getMessageIdCode.do?",    
	            data: {phoneNum:phoneNum,storeId:storeId},
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            success: function(data) {
	  				checkPhoneText.innerHTML = data;
					checkPhoneText.style.color = "#CDAF95";
					$("#checkPhoneText").show();
	            },
	            error: function(err) {
	                checkPhoneText.innerHTML = "短信发送失败,请稍后重试";
					checkPhoneText.style.color = "#CDAF95";
					$("#checkPhoneText").show();
	            }
        	});
			
			$("#getNo-Btn").attr("disabled", true);
			var count = 60;
			var countdown = setInterval(CountDown, 1000);
			function CountDown() {
				$("#getNo-Btn").val("请等待," + count + "秒");
				if (count == 0) {
					$("#getNo-Btn").val("重新获取").removeAttr("disabled");
					clearInterval(countdown);
				}
				count--;
			}
		}
	});
	
	$("#next_Btn").click(function () {
		var isContine = checkRegist("submit");
		var result = "";
		if(isContine){
			$("#next_Btn").attr("disabled", true);
			//校验短信验证码是否正确
			var checkNo = $("#checkNum").val();
			var phoneNum = $("#registerMobile").val();
			$.ajax({
	            type: "get",
	            url: "/photograph/checkIdCode.do?",    
	            data: {checkNo:checkNo,phoneNum:phoneNum}, 
	            contentType: "application/json; charset=utf-8",
	            dataType: "json",
	            success: function(data) {
	            	result = data.code;
	  				checkPhoneText.innerHTML = data.message;
					checkPhoneText.style.color = "#CDAF95";
					$("#checkPhoneText").show();
					if("success" == result){
						fm.submit();
					}else{
						$("#next_Btn").attr("disabled", false);
					}
	            },
	            error: function(err) {
	                checkPhoneText.innerHTML = "短信验证码校验失败,请稍后重试";
					checkPhoneText.style.color = "#CDAF95";
					$("#checkPhoneText").show();
					$("#next_Btn").attr("disabled", false);
	            }
        	});
			//fm.action="/store/viewStoreDetail.do?initType=viewStoreDetail&storeId="+storeId+"&cargoId="+cargoId+"&openId="+openId;
			
		}
	});
});

	/** 检查是否可注册或者可获取短信*/
	function checkRegist(type){
		if ($("#registerName").val() == "" || $("#registerName").val() == "输入昵称" || $("#registerName").val() == null || $("#registerName").val() == undefined) {
			var checkPhoneText = document.getElementById("checkPhoneText");
			checkPhoneText.innerHTML = "昵称不能为空";
			checkPhoneText.style.color = "#CDAF95";
			$("#registerName").css("border-color","#CDAF95");
			$("#checkPhoneText").show();
			return false;
		}else if ($("#registerMobile").val() == "" || $("#registerMobile").val() == "输入手机号码" || $("#registerMobile").val() == null || $("#registerMobile").val() == undefined) {
			var checkPhoneText = document.getElementById("checkPhoneText");
			checkPhoneText.innerHTML = "手机号不能为空或类型不正确";
			checkPhoneText.style.color = "#CDAF95";
			$("#registerMobile").css("border-color","#CDAF95");
			$("#checkPhoneText").show();
			return false;
		}else{
			//如果是获取验证码
			if(type == "submit"){
				//否则则是提交
				if ($("#checkNum").val() == "" || $("#checkNum").val() == "请输入验证码" || $("#checkNum").val() == null || $("#checkNum").val() == undefined) {
					var checkPhoneText = document.getElementById("checkPhoneText");
					checkPhoneText.innerHTML = "验证码不能为空";
					checkPhoneText.style.color = "#CDAF95";
					$("#checkNum").css("border-color","#CDAF95");
					$("#checkPhoneText").show();
					return false;
				}
			}
			return true;
		}
	}
