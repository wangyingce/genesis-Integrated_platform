$(function(){
	var InterValObj; //timer变量，控制时间
	var count = 60; //间隔函数，1秒执行
	var curCount;//当前剩余秒数
	//手机号
	$("#loginmobile").focus(function(){
		if(this.value=="手机号或胎大王账号登入"){this.value="";}
	});
	$("#loginmobile").blur(function(){
		if(this.value==""){this.style.color="#ccc";this.value="手机号或胎大王账号登入";}
	});
	$("#loginmobile").keydown(function(){
		this.style.color="#fff";
	});
	//密码
	$("#loginkeyword").focus(function(){
		if(this.value=="请输入密码"){this.value="";}
	});
	$("#loginkeyword").blur(function(){
		if(this.value==""){this.style.color="#ccc";this.value="请输入密码";}
	});
	$("#loginkeyword").keydown(function(){
		this.style.color="#fff";
	});
	
	// 表单验证、记住用户名
	$("#loginForm").validate({
        rules: {
            loginmobile: {
                required: true
            },
            loginkeyword: {
                required: true,
				rangelength:[4,12]
            }
        },
        messages: {
            loginmobile: {
				required:"手机号不能为空"
			},
            loginkeyword: {
                required: "请填写密码",
				rangelength:"请填写正确的密码"
            }
        }
    });
	//页面加载完毕
	$(document).ready(function(){
		$("#rmt").hide();
	});
	
	
	//手机号
	$("#registermobile").focus(function(){
		if(this.value=="输入手机号码"){this.value="";$('#registericon1').hide();}
	});
	$("#registermobile").blur(function(){
		if(this.value==""){
			 this.style.color="#8f8f8f";
			 this.value="输入手机号码";
			 $('#registericon1').hide();
		}else{
			$('#registericon1').show();
			var registermobile = $("#registermobile").val();
	        var merchantId = $("#merchantId").val();
	        $.ajax({type: "get", url: "/merchant/checkMerchant.do", data: {merchantId:merchantId,registermobile:registermobile},dataType: "json",success: function(data) {
	            if(data.nickName=="OKAY"){
	                    $("#registermobile").css("background-color","#FFFFFF");
	                    $("#rmt").hide();
	            }else{
            	        document.getElementById("rmt").innerHTML= "手机号:"+$("#registermobile").val()+"已注册店铺";
            	        document.getElementById("registermobile").style.color="#8f8f8f";
            	        $("#registermobile").val("输入手机号码");
            	        $('#registericon1').hide();
	            	    $("#rmt").show();
//	            	    $("#registermobile").focus();
//	            	    return false;
	            }
	          },
	         });
		}
	});
	$("#registermobile").keydown(function(){
		this.style.color="#000";$('#registericon1').show();
	});
	
	
	//new add by wangyingce
	//验证码
	$("#password").focus(function(){
		if(this.value=="请输入验证码"){this.value="";}
	});
	$("#password").blur(function(){
		if(this.value==""){
			this.style.color="#8f8f8f";this.value="请输入验证码";
	    }else{
	    	 $.ajax({
                 type: "get",     
                 url: "/vf/cy.do",
                 data: {
                       mobile:$("#registermobile").val(),
                       verifyNo:$("#password").val(),
          	           type:'1'
                 },
                 contentType: "application/json; charset=utf-8",     
                 dataType: "json",     
                 success: function(data) {
                	 //alert(data.message);
          	        if(data.message=="手机号或类型不正确"){
                         document.getElementById("password").style.color="#8f8f8f";
             	         $("#password").val("请输入验证码");
             	         document.getElementById('password-m').innerHTML = "手机号或类型不正确";
         	             $("#password-m").show();
                         //$("#password").focus();
          	             return false;
          	        }else if(data.message=="验证码不正确"){
          	        	 document.getElementById("password").style.color="#8f8f8f";
            	         $("#password").val("请输入验证码");
          	        	 document.getElementById('password-m').innerHTML = "验证码不正确";
               	         $("#password-m").show();
                         //$("#password").focus();
          	             return false;
          	        }else if(data.message=="验证码已过期，请重新获取"){
          	        	 document.getElementById("password").style.color="#8f8f8f";
           	             $("#password").val("请输入验证码");
         	        	 document.getElementById('password-m').innerHTML = "验证码已过期，请重新获取";
           	             $("#password-m").show();
                         //$("#password").focus();
          	             return false;
          	        }else if(data.message=="验证通过"){
          	           $("#password-m").hide();
          	        }
                 },
               });
	    }
	});
	$("#password").keydown(function(){
		this.style.color="#000";
	});
	//店铺名称
	$("#nickName").focus(function(){
		if(this.value=="输入店铺名称"){this.value="";$('#registericon3').hide();}
	});
	$("#nickName").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";this.value="输入店铺名称";$('#registericon3').hide();}else{$('#registericon3').show();}
	});
	$("#nickName").keydown(function(){
		this.style.color="#000";$('#registericon3').show();
	});
	//收款人
	$("#corporation").focus(function(){
		if(this.value=="输入收款人名称"){this.value="";$('#registericon4').hide();}
	});
	$("#corporation").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";this.value="输入收款人名称";$('#registericon4').hide();}else{$('#registericon4').show();}
	});
	$("#corporation").keydown(function(){
		this.style.color="#000";$('#registericon4').show();
	});
	//收款人
	$("#identifyNumber").focus(function(){
		if(this.value=="输入身份证号码"){this.value="";$('#registericon5').hide();}
	});
	$("#identifyNumber").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";this.value="输入身份证号码";$('#registericon5').hide();}else{$('#registericon5').show();}
	});
	$("#identifyNumber").keydown(function(){
		this.style.color="#000";$('#registericon5').show();
	});
	//bankCname
	$("#bankCname").focus(function(){
		if(this.value=="输入开户行名称"){this.value="";$('#registericon6').hide();}
	});
	$("#bankCname").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";this.value="输入开户行名称";$('#registericon6').hide();}else{$('#registericon6').show();}
	});
	$("#bankCname").keydown(function(){
		this.style.color="#000";$('#registericon6').show();
	});
	//account
	$("#account").focus(function(){
		if(this.value=="输入银行卡号码"){this.value="";$('#registericon7').hide();}
	});
	$("#account").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";this.value="输入银行卡号码";$('#registericon7').hide();}else{$('#registericon7').show();}
	});
	$("#account").keydown(function(){
		this.style.color="#000";$('#registericon7').show();
	});
	//address
	$("#address").focus(function(){
		if(this.value=="输入详细地址"){this.value="";$('#registericon8').hide();}
	});
	$("#address").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";this.value="输入详细地址";$('#registericon8').hide();}else{$('#registericon8').show();}
	});
	$("#address").keydown(function(){
		this.style.color="#000";$('#registericon8').show();
	});
	
	
	//密码
	$("#registerkeyword").focus(function(){
		if(this.value==""){$(".keywordtip").css("display","none");}
	});
	$(".keywordtip").click(function(){
		if($("#registerkeyword").val()==""){$(".keywordtip").css("display","none");$("#registerkeyword").focus();}
	});
	$("#registerkeyword").blur(function(){
		if(this.value==""){$(".keywordtip").css("display","block");}
	});
	$("#registerkeyword").keydown(function(){
		this.style.color="#000";
	});
	$("#registericon1").click(function(){
		var a=$("#registermobile")[0].value;
		if(a!=""&&a!="输入手机号码"){$("#registermobile").val("");$("#registermobile").focus();$('#registericon1').hide();}
	});
	
	
	//new add by wangyingce
	//店铺名称
	$("#registericon3").click(function(){
		var a=$("#nickName")[0].value;
		if(a!=""&&a!="输入店铺名称"){$("#nickName").val("");$("#nickName").focus();$('#registericon3').hide();}
	});
	//收款人
	$("#registericon4").click(function(){
		var a=$("#corporation")[0].value;
		if(a!=""&&a!="输入收款人名称"){$("#corporation").val("");$("#corporation").focus();$('#registericon4').hide();}
	});
	//identifyNumber
	$("#registericon5").click(function(){
		var a=$("#identifyNumber")[0].value;
		if(a!=""&&a!="输入身份证号码"){$("#identifyNumber").val("");$("#identifyNumber").focus();$('#registericon5').hide();}
	});
	//bankCname
	$("#registericon6").click(function(){
		var a=$("#bankCname")[0].value;
		if(a!=""&&a!="输入开户行名称"){$("#bankCname").val("");$("#bankCname").focus();$('#registericon6').hide();}
	});
	//bankCname
	$("#registericon7").click(function(){
		var a=$("#account")[0].value;
		if(a!=""&&a!="输入银行卡号码"){$("#account").val("");$("#account").focus();$('#registericon7').hide();}
	});
	//address
	$("#registericon8").click(function(){
		var a=$("#address")[0].value;
		if(a!=""&&a!="输入详细地址"){$("#address").val("");$("#address").focus();$('#registericon8').hide();}
	});
	
	
	/*$("#registericon2").click(function(){
		$(".keywordtip").css("display","none");
		$("#registerkeyword").focus();
		$("#registerkeyword").attr("type","text");
	});*/
	//验证码
	$("#registercaptcha").blur(function(){
		if(this.value==""){this.style.color="#8f8f8f";}
	});
	$("#registercaptcha").keydown(function(){
		this.style.color="#000";
	});
	//客服热线 遮罩
	//遮罩
	$("#callservice").click(function(){
		ShowGuide();
	});
	function ShowGuide(){
		$(".guide_cover").css("height",$(document).height()+"px").fadeIn();
		if($(window).height()<$(".callservice").height()){
			$(".callservice").css({"margin-top":-$(window).height()/2+"px"});
		}
		$(".callservice").fadeIn();
	}
	function HideGuide(){
			$(".guide_cover").fadeOut();
			$(".callservice").fadeOut();
	}
	$(".guide_cover,#callcancel,#callconfirm").click(function(){
			HideGuide();
	});
});