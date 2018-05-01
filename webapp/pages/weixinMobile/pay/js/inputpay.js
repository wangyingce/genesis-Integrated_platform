/*
*函数功能：从href获得参数
*sHref:   http://www.artfh.com/arg.htm?arg1=d&arg2=re
*sArgName:arg1, arg2
*return:    the value of arg. d, re
*/
function getArgsFromHref(sHref, sArgName)
{
      var args    = sHref.split("?");
      var retval = "";
    
      if(args[0] == sHref) /*参数为空*/
      {
           return retval; /*无需做任何处理*/
      }  
      var str = args[1];
      args = str.split("&");
      for(var i = 0; i < args.length; i ++)
      {
          str = args[i];
          var arg = str.split("=");
          if(arg.length <= 1) continue;
          if(arg[0] == sArgName) retval = arg[1]; 
      }
      return retval;
}

$(function() {     
    $("#payBtn").click(function() {   
        $.ajax({     
            type: "get",     
            url: "/weixin/payWeiXin.do",    
            data: {openId:getArgsFromHref(window.location.href,"openId"),money:$("#money").val()}, 
            contentType: "application/json; charset=utf-8",     
            dataType: "json",     
            success: function(data) {
            	WeixinJSBridge.invoke('getBrandWCPayRequest',{
             		 "appId"	:	data.appId,
             		 "timeStamp":	data.timeStamp,
             		 "nonceStr"	:	data.nonceStr,
             		 "package"	: 	data.packageStr,
             		 "signType" :	data.signType,
	         		 "paySign"	:	data.paySign
	      		},function(res){
	   				WeixinJSBridge.log(res.err_msg);
	   	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	   	                alert("微信支付成功!");  
	   	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	   	                alert("用户取消支付!");  
	   	            }else{  
	   	               alert("支付失败!");  
	   	            }  
	   			})   
            },     
            error: function(err) {     
                alert("error"+err);     
            }     
        });     
    
        //禁用按钮的提交      
        return false;     
    });     
});
	