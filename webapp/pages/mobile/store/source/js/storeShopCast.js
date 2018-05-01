$(document).ready(function(){
	var storeId = getArgsFromHref(window.location.href,"storeId");
	var shoppingCart = getArgsFromHref(window.location.href,"shoppingCart");
	var openId = getArgsFromHref(window.location.href,"openId");
	document.getElementById("storeId").value=storeId;
	document.getElementById("openId").value=openId;
	document.getElementById("shoppingCart").value=shoppingCart;
	//商品大类innerString
	var castString = "<dl class='tab_proList'><dd>";
	var sumTotalPay = 0.0;
	$.ajax({    
            type: "get",     
            url: "/ysyl/store/initShopCast.do?initType=initShopCast",    
            data: {storeId:storeId,shoppingCart:shoppingCart}, 
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
  				$.each(data,function(i,item){
  					var sumPrice = parseFloat(item.discountPrice);
					sumPrice = round((sumPrice * parseInt(item.quantity)),2);
  					castString += "<ul><li><div class='goodsPic'><img src='"+item.icoUrl+"'/></div>";
					castString +="<div class='goodsInfor'><h2>"+item.cargoName+"</h2>";
					castString +="<p>单价：<strong class='price'><input type='text' id='unitPrice"+i+"' readonly='readonly' class='input_r w_30' value = '"+item.discountPrice+"'/></strong></p>";
					castString +="<d>数量：<input type='button' id='cutButton"+i+"' value='-' onclick='cutOneCart("+i+");'/><input type='text' id='count"+i+"' value='"+item.quantity+"' readonly='readonly' class='w_15'/><input type='button' value='+' onclick='addOneCart("+i+");'/></d>";
					castString +="<p>合计：<strong class='price'><input type='text' id='sumPrice"+i+"' readonly='readonly' class='input_r w_30' value='"+sumPrice+"'/></strong></p>";
					castString += "</div></li></ul>";
					sumTotalPay += sumPrice;
  				});
  				castString +="<div><h2>总计：<input type='text' id='sumTotalPay' readonly='readonly' value='"+sumTotalPay+"' class='input_r w_30' /><input type='button' value='去结算' onclick='toPay();'/></h2></div>"
  				castString += "</dd></dl>";
  				//再加上去结算模块
  				document.getElementById("shopCartDiv").innerHTML=castString;
  				//加载完后初始化js效果
				initStoreIndexJS();
            },
            error: function(err) { 
                alert("error"+err);     
            }     
        });
	
	var mySwiper = new Swiper('#slide',{
		  autoplay:5000,
		  visibilityFullFit : true,
		  loop:true,
		  pagination : '.pagination',
	 });
});

function initStoreIndexJS(){
	//加载完后给标签赋js效果product list:Tab
	$(".tab_proList dd").eq(0).show().siblings(".tab_proList dd").hide();
	$(".tab_proList dt a").eq(0).addClass("currStyle");
	$(".tab_proList dt a").click(function(){
		var liindex = $(".tab_proList dt a").index(this);
		$(this).addClass("currStyle").siblings().removeClass("currStyle");
		$(".tab_proList dd").eq(liindex).fadeIn(150).siblings(".tab_proList dd").hide();
	});
}

//查看购物车
function toPay(){
	//获取点击的购物车
	var shoppingCart = document.getElementById("shoppingCart").value;
	var storeId = document.getElementById("storeId").value;
	var openId = document.getElementById("openId").value;
	var sumTotalPay = document.getElementById("sumTotalPay").value;
	//调用微信支付
	 $.ajax({     
            type: "get",     
            url: "/ysyl/store/toPay.do?initType=toPay",
            data: {
            	openId:openId,
            	storeId:storeId,
            	sumTotalPay:sumTotalPay
            }, 
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
	   	            	WeixinJSBridge.invoke('closeWindow');
	   	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
						$("#payBtn").removeClass("weui_btn_disabled");
						$("#payBtn").attr("disabled",false);
	   	            }else{  
	   	               	alert("支付失败!");
			            $("#payBtn").removeClass("weui_btn_disabled");
			            $("#payBtn").attr("disabled",false);
	   	            }  
	   			})   
            },     
            error: function(err) {     
                alert("error"+err); 
                $("#payBtn").removeClass("weui_btn_disabled");
	            $("#payBtn").attr("disabled",false);
            }     
        });
	//fm.action="/ysyl/store/viewCargo.do?initType=viewCargo&storeId="+storeId+"&shoppingCart="+shoppingCart;
	//fm.submit();
}


function cutOneCart(i){
	var count = document.getElementById("count"+i).value;
	count = parseInt(count)-1;
	document.getElementById("count"+i).value = count;
	if(count == 0){
		document.getElementById("cutButton"+i).disabled = true;
	}
	//计算总价，单价*数量
	var unitPrice1 = document.getElementById("unitPrice"+i).value;
	var sumPrice1 = parseFloat(unitPrice1)*count;
	sumPrice1 = round(sumPrice1,2);
	document.getElementById("sumPrice"+i).value=sumPrice1;
}

function addOneCart(i){
	var count = document.getElementById("count"+i).value;
	count = parseInt(count)+1;
	document.getElementById("count"+i).value = count;
	if(count > 0){
		document.getElementById("cutButton"+i).disabled = false;
	}
		//计算总价，单价*数量
	var unitPrice1 = document.getElementById("unitPrice"+i).value;
	var sumPrice1 = parseFloat(unitPrice1)*count;
	sumPrice1 = round(sumPrice1,2);
	document.getElementById("sumPrice"+i).value=sumPrice1;
}