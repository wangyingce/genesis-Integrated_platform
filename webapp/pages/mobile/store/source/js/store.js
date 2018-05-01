$(document).ready(function(){
	var storeId = getArgsFromHref(window.location.href,"storeId");
	var nickName = getArgsFromHref(window.location.href,"nickName");
	var openId = getArgsFromHref(window.location.href,"openId");
	var storeName = getArgsFromHref(window.location.href,"inputName");
	//左上角欢迎
	document.getElementById("welcome").innerHTML=nickName+"，您好";
	//小店名称
	document.getElementById("storeName").innerHTML=storeName+"'s小店";
	
	document.getElementById("storeId").value=storeId;
	document.getElementById("openId").value=openId;
	
	//商品大类innerString
	var innerClassString = "";
	$.ajax({    
            type: "get",     
            url: "/ysyl/store/init.do?initType=waresClass",    
            data: {storeId:storeId}, 
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
  				$.each(data,function(i,item){
  					//根据返回的内容拼接html5商品大类
  					innerClassString += "<li><a href='#'><img src='"+item.imageUrl+"'/>";
  					innerClassString += "<em>"+item.className+"</em></a></li>";
  				});
  				document.getElementById("waresClass").innerHTML=innerClassString;
            },
            error: function(err) { 
                alert("error"+err);     
            }     
        });
	
	 //商品明细
	var innerDetailString = "";
	//生成商品标签map
	var proListMap = new HashMap();
	//标签下对应的商品明细
	var proLisDtailstMap = new HashMap();
	$.ajax({
            type: "get",     
            url: "/ysyl/store/init.do?initType=waresDetail",    
            data: {storeId:storeId}, 
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
  				$.each(data,function(i,item){
  					//存储商品标签
  					//proListMap.put(item.cargoStatusName);
  					//生成明细html
  					var listStr = "<li><a href='#' class='goodsPic'><img src='"+item.icoUrl+"'/></a>";
  					listStr += "<div class='goodsInfor'><h2><a onclick='viewStoreDetail("+item.id+");'>"+item.cargoName+"</a></h2>";
  					listStr += "<p><del>"+item.unitPrice+"</del></p>";
  					listStr += "<p><strong class='price'>"+item.discountPrice+"</strong></p>";
  					listStr += "<input type='hidden' id='cargo"+i+"' value='"+item.id+"'/><input type='hidden' id='cargoName"+i+"' value='"+item.cargoName+"'/>";
  					listStr += "<input type='hidden' id='discountPrice"+i+"' value='"+item.discountPrice+"'/>";
  					listStr += "<input type='hidden' id='icoUrl"+i+"' value='"+item.icoUrl+"'/>";
  					listStr += "<a class='addToCart' onclick='checkCargo("+i+");'>&#126;</a></div></li>";
	  				var dStr = proLisDtailstMap.get(item.cargoStatusName);
	  				if(dStr == null){
	  					dStr = "";
	  				}
	  				dStr += listStr;
	  				//alert(item.cargoStatusName + "-------"+dStr);
	  				proLisDtailstMap.put(item.cargoStatusName,dStr);
	  				
  				});
  				
  				var key = proLisDtailstMap.keySet();
  				var waresTablesString = "";
  				var waresDetalsString = "";
  				//生成商品明细table页
				for (var i in key){
					waresTablesString += "<a>"+key[i]+"</a>";
					waresDetalsString +="<dd><ul>" + proLisDtailstMap.get(key[i]) + "</ul></dd>";
					//alert("1111");
					//alert(waresDetalsString);
				}
  				//将商品标签页打到页面上
				//alert(document.getElementById("waresTables").innerHTML);
				document.getElementById("waresTables").innerHTML=waresTablesString;
				document.getElementById("spdetails").innerHTML=waresDetalsString;
				//alert(document.getElementById("waresTables").innerHTML);
				//加载完后初始化js效果
				initStoreIndexJS();
            },
            error: function(err) { 
                alert("error"+err);     
            }     
        });
	
	//加载商店信息首页相关的其它信息，目前暂时只需加载购物车信息
	var innerOtherString = "";
	$.ajax({
            type: "get",     
            url: "/ysyl/store/init.do?initType=waresOther",    
            data: {
            	openId:openId,
            	storeId:storeId
            }, 
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
  				$.each(data,function(i,item){
  					//根据返回的内容拼接html5商品大类
  					innerClassString += "<li><a href='#'><img src='"+item.imageUrl+"'/>";
  					innerClassString += "<em>"+item.className+"</em></a></li>";
  				});
  				document.getElementById("waresClass").innerHTML=innerClassString;
            },
            error: function(err) { 
                alert("error"+err);     
            }     
        });
});

//查看商品明细
function viewStoreDetail(cargoId){
	var storeId = document.getElementById("storeId").value;
	var openId = document.getElementById("openId").value;
	fm.action="/ysyl/store/viewStoreDetail.do?initType=viewStoreDetail&storeId="+storeId+"&cargoId="+cargoId+"&openId="+openId;
	fm.submit();
}
