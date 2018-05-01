function initStoreIndexJS(){
	var mySwiper = new Swiper('#slide',{
		  autoplay:5000,
		  visibilityFullFit : true,
		  loop:true,
		  pagination : '.pagination',
	 });
	//加载完后给标签赋js效果product list:Tab
	$(".tab_proList dd").eq(0).show().siblings(".tab_proList dd").hide();
	$(".tab_proList dt a").eq(0).addClass("currStyle");
	$(".tab_proList dt a").click(function(){
		var liindex = $(".tab_proList dt a").index(this);
		$(this).addClass("currStyle").siblings().removeClass("currStyle");
		$(".tab_proList dd").eq(liindex).fadeIn(150).siblings(".tab_proList dd").hide();
	});
				
	//飞入动画，具体根据实际情况调整
   	$(".addToCart").click(function(){
		$(".hoverCart a").html(parseInt($(".hoverCart a").html())+1);/*悬浮购物车+1*/
		var shopOffset = $(".hoverCart").offset();
		var cloneDiv = $(this).parent().siblings(".goodsPic").clone();
		var proOffset = $(this).parent().siblings(".goodsPic").offset();
		cloneDiv.css({ "position": "absolute", "top": proOffset.top, "left": proOffset.left });
		$(this).parent().siblings(".goodsPic").parent().append(cloneDiv);
		cloneDiv.animate({
			width:0,
			height:0,
			left: shopOffset.left,
			top: shopOffset.top,
			opacity:1
		},"slow");
	});
	
}

//购物车存储
function checkCargo(i){
	//var cargoId = document.getElementById("cargo"+i).value;
	//var cargoName = document.getElementById("cargoName"+i).value;
	//var discountPrice = document.getElementById("discountPrice"+i).value;
	//var icoUrl = document.getElementById("icoUrl"+i).value;
	//var sCartVo = new Object();
	//sCartVo.cargoId = cargoId;
	//sCartVo.cargoName = cargoName;
	//sCartVo.discountPrice = discountPrice;
	//sCartVo.quantity = "1";
	//sCartVo.icoUrl = icoUrl;
	//存入变量list中
	//sCartList.push(sCartVo);
	var cargoId = document.getElementById("cargo"+i).value;
	var shoppingCart = document.getElementById("shoppingCart").value;
	if(shoppingCart != null && shoppingCart != "" && shoppingCart != undefined){
		shoppingCart = shoppingCart+","+cargoId;
	}else{
		shoppingCart = cargoId;
	}
	document.getElementById("shoppingCart").value = shoppingCart;
}

//查看购物车
function viewCargo(){
	//获取点击的购物车
	var shoppingCart = document.getElementById("shoppingCart").value;
	var storeId = document.getElementById("storeId").value;
	var openId = document.getElementById("openId").value;
	fm.action="/ysyl/store/viewCargo.do?initType=viewCargo&storeId="+storeId+"&shoppingCart="+shoppingCart+"&openId="+openId;
	fm.submit();
}