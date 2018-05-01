$(function() {
	var storeId = document.getElementById("storeId").value;
	var userCode = document.getElementById("userCode").value;
	var mySwiper = new Swiper('#slide',{
		  autoplay:5000,
		  visibilityFullFit : true,
		  loop:true,
		  pagination : '.pagination',
	  });
	$(".tab_proList dd").eq(0).show().siblings(".tab_proList dd").hide();
	$(".tab_proList dt a").eq(0).addClass("currStyle");
	$(".tab_proList dt a").click(function(){
	var liindex = $(".tab_proList dt a").index(this);
	$(this).addClass("currStyle").siblings().removeClass("currStyle");
	$(".tab_proList dd").eq(liindex).fadeIn(150).siblings(".tab_proList dd").hide();
	});
   //飞入动画，具体根据实际情况调整
   $(".addToCart").click(function(){
        $(".hoverCart a").html(parseInt($(".hoverCart a").html())+1);/*测试+1*/
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
           //添加购物车
           var waresID =this.id;
          $.ajax({
	          	type: "get",     
			    url: "/orderFood/saveShoppingCart.do",    
			    data: {
			    	storeId:storeId,
			    	userCode:userCode,
			    	productId:waresID
			    }, 
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data) {
			    	
			    },
			    error: function(err) { 
			        alert("添加购物车异常，请换其他菜系");     
			    }  
           });
	});
	
	var isOrder = document.getElementById("isOrder").value;
	if(isOrder == "0"){
		document.getElementById("noOrder").style.display="";
	}
	var $hoverCart = $('.hoverCart');
	$hoverCart.click(function(){
		var storeId=document.getElementById("storeId").value;
		var userCode=document.getElementById("userCode").value;
		 window.location.href="/orderFood/getShoppingCartByUserCode.do?storeId="+storeId+"&userCode="+userCode; 
	});
	var $a = $('.sy a');
	$a.click(function(){
		var $this = $(this);
		var idName = $(this).attr("id");
		var innerString = "";
		$.ajax({
		    type: "get",     
		    url: "/orderFood/initCargoStatus.do",    
		    data: {
		    	storeId:storeId,
		    	userCode:userCode,
		    	cargoStatus:idName
		    }, 
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data) {
		    	$.each(data,function(i,item){
		    	innerString+=" <li>";
		    	innerString+=" <a href=\"/orderFood/getProductDetil.do?productId="+item.id +"\" class=\"goodsPic\">";
	            innerString+="<img src=\""+item.icoUrl+"\"/>";
			    innerString+="</a>";
			    innerString+="<div class=\"goodsInfor\">";
			    innerString+=" <h2>";
			    innerString+="  <a href=\"/orderFood/getProductDetil.do?productId="+item.id +"\">"+item.cargoName+"</a>";
			    innerString+=" </h2>";
			    innerString+=" <p>";
			    innerString+=" <!-- 原价 -->";
			    innerString+="  <del>"+item.unitPrice+"</del>";
			    innerString+=" </p>";
			    innerString+=" <p>";
			    innerString+=" <!--现价 -->";
			    innerString+="  <strong class=\"price\">"+item.discountPrice+"</strong>";
			    innerString+=" </p>";
			    innerString+=" <a class=\"addToCart\" name=\"addCart\" onclick=\"aclick(this)\" id=\""+item.id +"\">&#126;</a>";
			    innerString += "<input type='hidden' id='orderId"+i+"' value='"+item.id+"'/></div>";
			    innerString+="</div>";
			    innerString+="</li>";
  				});
  				document.getElementById("myOrderBody").innerHTML=innerString;
  				if(innerString == ""){
  					document.getElementById("noOrder").style.display="";
  				}else{
  					document.getElementById("noOrder").style.display="none";
  				}
		    },
		    error: function(err) { 
		        alert("error"+err);     
		    }     
		});			
	});	
	
});

//双击添加任务
	function aclick(file){
	var storeId = document.getElementById("storeId").value;
	var userCode = document.getElementById("userCode").value;
	 $(".hoverCart a").html(parseInt($(".hoverCart a").html())+1);/*测试+1*/
           var shopOffset = $(".hoverCart").offset();
           var cloneDiv = $(file).parent().siblings(".goodsPic").clone();
           var proOffset = $(file).parent().siblings(".goodsPic").offset();
           cloneDiv.css({ "position": "absolute", "top": proOffset.top, "left": proOffset.left });
           $(file).parent().siblings(".goodsPic").parent().append(cloneDiv);
           cloneDiv.animate({
			width:0,
			height:0,
            left: shopOffset.left,
            top: shopOffset.top,
			opacity:1
           },"slow");
           //添加购物车
           var waresID =file.id;
          $.ajax({
	          	type: "get",     
			    url: "/orderFood/saveShoppingCart.do",    
			    data: {
			    	storeId:storeId,
			    	userCode:userCode,
			    	productId:waresID
			    }, 
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data) {
			    	
			    },
			    error: function(err) {
			        alert("添加购物车异常，请换其他菜系");     
			    }  
           });
	}