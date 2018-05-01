
$(function () {
  //show or hide:delBtn
	$(".edit").toggle(function () {
		$(this).parent().siblings("dd").find(".delBtn").fadeIn();
		$(this).html("完成");
		//$(".numberWidget").show();
		//$(".priceArea").hide();
	}, function () {
		$(this).parent().siblings("dd").find(".delBtn").fadeOut();
		$(this).html("编辑");
		//$(".numberWidget").hide();
		//$(".priceArea").show();
	});
  
  //删除购物车
	$(".delBtn").click(function () {
		var cartId = ($(this).siblings(".cartIDa")).val();//获取购物车id
		$.ajax({
			type:"get", 
			url:"/orderFood/deleteShoppingCart.do", 
			data:{
				cartId:cartId
			}, 
			contentType:"application/json; charset=utf-8", 
			dataType:"json", 
			success:function (data) {
			}, 
			error:function (err) {
				alert("删除商品异常，请联系管理员！");
			}
		});
		$(this).parent().remove();
		nullTips();
	});
  //计算购物车情况
	function nullTips() {
		if ($(".cart dd").length == 0) {
			var tipsCont = "<mark style='display:block;background:none;text-align:center;color:grey;'>购物车为空！</mark>";
			$(".cart").remove();
			$("body").append(tipsCont);
			document.getElementById("sumPrice").innerHTML="合计：0.0";
		}else{
			var sumPrice=0.0;
			for(var i=0;i<$(".cart dd").length;i++){
				var quantityVar=document.getElementsByName("quantity")[i].value;
				var discountPrice=document.getElementsByName("discountPrice")[i].value;
			   	sumPrice+=parseFloat(discountPrice)*parseFloat(quantityVar);
			}
			document.getElementById("sumPrice").innerHTML="合计："+sumPrice;
		}
	}
});

