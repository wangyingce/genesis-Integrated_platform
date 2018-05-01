$(document).ready(function(){
	var storeId = getArgsFromHref(window.location.href,"storeId");
	
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
	
});
