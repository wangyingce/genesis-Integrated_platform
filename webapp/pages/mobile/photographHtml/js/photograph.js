
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
	
	//姓名
	$("#registerName").focus(function () {
		if (this.value == "输入姓名") {
			this.value = "";
		}
	});
	$("#registerName").blur(function () {
		if (this.value == "") {
			this.style.color = "#8f8f8f";
			this.value = "输入姓名";
		}
	});
	$("#registerName").keydown(function () {
		this.style.color = "#000";
	});
	$("#registericonName").click(function () {
		var a = $("#registerName")[0].value;
		if (a != "" && a != "输入姓名") {
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
		var a = $("#registerName")[0].value;
		if (a != "" && a != "请输入验证码") {
			$("#checkNum").val("");
			$("#checkNum").focus();
		}
	});
	$("#registerMobile").keydown(function () {
		this.style.color = "#000";
		$("#registericonNum").show();
	});
	
	$("#getNo-Btn").click(function () {
		if ($("#registerName").val() == "" || $("#registerName").val() == "输入姓名" || $("#registerName").val() == null || $("#registerName").val() == undefined) {
			$("#registerName").focus();
			document.getElementById("checkPhoneText").innerHTML = "姓名不能为空";
			$("#checkPhoneText").show();
			return false;
		}else if ($("#registerMobile").val() == "" || $("#registerMobile").val() == "输入手机号码" || $("#registerMobile").val() == null || $("#registerMobile").val() == undefined) {
			$("#registerMobile").focus();
			document.getElementById("checkPhoneText").innerHTML = "手机号不能为空或类型不正确";
			$("#checkPhoneText").show();
			return false;
		} else {
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
		
		
//			var registerMobile = $("#registerMobile").val();
//			$.ajax({type:"get", url:"/ysyl/merchant/checkMerchant.do", data:{merchantId:merchantId, registerMobile:registerMobile}, dataType:"json", success:function (data) {
//				if (data.nickName == "OKAY") {
//					$("#registerMobile").css("background-color", "#FFFFFF");
//					$("#rmt").hide();
//					util.hsTime("#getNo-Btn");
//					$.ajax({type:"get", url:"/ysyl/vf/rv.do", data:{mobile:$("#registerMobile").val(), type:"1"}, contentType:"application/json; charset=utf-8", dataType:"json", success:function (data) {
//						document.getElementById("canon").value = data.verifyNo;
//					}});
//				} else {
//					document.getElementById("rmt").innerHTML = "\u624b\u673a\u53f7:" + $("#registerMobile").val() + "\u5df2\u6ce8\u518c\u5e97\u94fa";
//					document.getElementById("registerMobile").style.color = "#8f8f8f";
//					$("#registerMobile").val("输入手机号码");
//					$("#registericon1").hide();
//					$("#rmt").show();
//				}
//			}});
		}
	});
	
});

