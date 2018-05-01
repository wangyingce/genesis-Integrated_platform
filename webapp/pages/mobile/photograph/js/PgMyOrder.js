$(function() {
	var isOrder = document.getElementById("isOrder").value;
	if(isOrder == "0"){
		document.getElementById("noOrder").style.display="";
	}
	
	var $a = $('.sy a');
	$a.click(function(){
		var $this = $(this);
		var idName = $(this).attr("id");
		//先获取nowIdName,根据idName与nowIDname判断是否需要加载
		var nowIdName = document.getElementById("nowIdName").value;
		if(idName != nowIdName){
			//不相等再进行下一步
			//var $t = $this.index();
			$a.removeClass();
			$this.addClass('baccolor');
			//弹出一个等待层
			var layIndex = layer.load(1, {
			  shade: [0.2,'#fff'] //0.2透明度的白色背景
			});
			//根据id名称动态加载订单信息
			var storeId = document.getElementById("storeId").value;
			var userCode = document.getElementById("userCode").value;
			var innerString = "";
			$.ajax({
			    type: "get",     
			    url: "/photograph/initMyChangeOrder.do?",    
			    data: {
			    	storeId:storeId,
			    	userCode:userCode,
			    	inKey:idName
			    }, 
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data) {
			    	$.each(data,function(i,item){
	  					//根据返回的内容拼接订单信息
	  					innerString += "<div class='bwl'><div>";
	  					innerString += "<span class='spanbefore'>"+item.ownerName+"</span>";
	  					innerString += "<a class='bwla'>"+item.payStateName+"</a></div>";
	  					innerString += "<div class='bgef'>";
						innerString += "<span><img src='"+item.imageUrl+"'/>";
						innerString += "<div class='text_c'>"+item.photoName+" "+item.pgDate+" "+item.pgBeginTime+"点-"+item.pgEndTime+"点</div>";
						innerString += "<div class='text_c'>"+item.ownerAddress+" Tel:"+item.ownerPhone+"</div>";
						innerString += "<div class='text_c'>预约金额:￥"+item.orderPrice+"</div></span></div>";
						innerString += "";
						if(idName == "01"){
							innerString += "<div class='input_button4'><input type='button' onclick='cancelBtn("+i+")' id='cancelBtn"+i+"' value='取消订单' />";
							innerString += "<span><input type='button' onclick='payBtn("+i+")' id='payBtn"+i+"' value='付款' /></span></div>";
						}else if(idName == "11"){
							innerString += "<div class='input_button4'><input type='button' onclick='cancelBtnPay("+i+")' id='cancelBtn"+i+"' value='取消订单' /></div>";
						}else if(idName == "67"){
							innerString += "<div class='input_button4'><input type='button' onclick='deleteBtn("+i+")' id='cancelBtn"+i+"' value='删除' /></div>";
						}
						innerString += "<input type='hidden' id='orderId"+i+"' value='"+item.id+"'/></div>";
	  				});
	  				document.getElementById("myOrderBody").innerHTML=innerString;
	  				if(innerString == ""){
	  					document.getElementById("noOrder").style.display="";
	  				}else{
	  					document.getElementById("noOrder").style.display="none";
	  				}
	  				document.getElementById("nowIdName").value = idName;
	  				layer.close(layIndex); //关闭层
			    },
			    error: function(err) { 
			        alert("error"+err);     
			    }     
			});
		}
	});	
	
});
	
	function cancelBtn(index){
		var cancelId = "cancelBtn"+index+"";
		var payId = "payBtn"+index+"";
		//获取对应的订单id
		var orderIdName = "orderId"+index;
		var deleteIdName = "bwl"+index;
		var orderId = document.getElementById(orderIdName).value;
		var storeId = document.getElementById("storeId").value;
		var userCode = document.getElementById("userCode").value;
		//弹出一个询问层
		layer.msg('你确定要取消吗？', {
		  time: 0, //不自动关闭
		  btn: ['确定', '取消'],
		  skin: 'layui-layer-lan',//调用样式
		  shift: 5,
		  shade: [0.2],
		  yes: function(index){
		    layer.close(index);
		    //弹出一个等待层
			var layIndex = layer.load(1, {
			  shade: [0.2,'#fff'] //0.2透明度的白色背景
			});
		    //开始执行删除
		    $.ajax({
			    type: "get",     
			    url: "/photograph/cancelMyOrder.do?",    
			    data: {
			    	storeId:storeId,
			    	userCode:userCode,
			    	inKey:orderId
			    }, 
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data) {
			    	//获取序号
				    $("#"+cancelId).attr("disabled", true);
				    $("#"+cancelId).val("已取消");
				    $("#"+payId).attr("disabled", true);
				    layer.close(layIndex);
				    layer.msg('取消成功', {
				    	time: 1000 //1秒后自动关闭
				    });
			    },
			    error: function(err) {
			        alert("error"+err);     
			    }
			});
			
		   }
		});
	};
	
	function deleteBtn(index){
		var cancelId = "cancelBtn"+index+"";
		var payId = "payBtn"+index+"";
		//获取对应的订单id
		var orderIdName = "orderId"+index;
		var deleteIdName = "bwl"+index;
		var orderId = document.getElementById(orderIdName).value;
		var storeId = document.getElementById("storeId").value;
		var userCode = document.getElementById("userCode").value;
		//弹出一个询问层
		layer.msg('删除后将无法恢复', {
		  time: 0, //不自动关闭
		  btn: ['确定', '取消'],
		  skin: 'layui-layer-lan',//调用样式
		  shift: 5,
		  shade: [0.2],
		  yes: function(index){
		    layer.close(index);
		    //弹出一个等待层
			var layIndex = layer.load(1, {
			  shade: [0.2,'#fff'] //0.2透明度的白色背景
			});
		    //开始执行删除
		    $.ajax({
			    type: "get",     
			    url: "/photograph/deleteMyOrder.do?",    
			    data: {
			    	storeId:storeId,
			    	userCode:userCode,
			    	inKey:orderId
			    }, 
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data) {
			    	//获取序号
				    $("#"+cancelId).attr("disabled", true);
				    $("#"+cancelId).val("已删除");
				    $("#"+payId).attr("disabled", true);
				    layer.close(layIndex);
				    layer.msg('删除成功', {
				    	time: 1000 //1秒后自动关闭
				    });
			    },
			    error: function(err) {
			        alert("error"+err);     
			    }
			});
			
		   }
		});
	};
	
	function payBtn(index){
		var cancelId = "cancelBtn"+index+"";
		var payId = "payBtn"+index+"";
		//获取对应的订单id
		var orderIdName = "orderId"+index;
		var deleteIdName = "bwl"+index;
		var orderId = document.getElementById(orderIdName).value;
		var storeId = document.getElementById("storeId").value;
		var userCode = document.getElementById("userCode").value;
		//打开一个loading层
		var index = layer.load(1, {
		  shade: [0.2,'#fff'] //0.2透明度的白色背景
		});
		//按钮只读
		$("#"+payId).attr("disabled", true);
		$("#"+cancelId).attr("disabled", true);
		//订单支付，打开微信支付页面
		 $.ajax({
	            type: "get",     
	            url: "/photograph/payMyOrder.do?",
	            data: {
				   	storeId:storeId,
				   	userCode:userCode,
				   	inKey:orderId
				},
	            contentType: "application/json; charset=utf-8",     
	            dataType: "json",     
	            success: function(data) {
	            	//alert(data.error);
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
		   	            alert("success");
		   	            	WeixinJSBridge.invoke('closeWindow');
		   	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
							//按钮只读
							$("#"+payId).attr("disabled", false);
							$("#"+cancelId).attr("disabled", false);
							layer.close(index); //此时你只需要把获得的index，轻轻地赋予layer.close即可
		   	            }else{
		   	               	alert("error,请稍后重试");
				            $("#"+payId).attr("disabled", false);
							$("#"+cancelId).attr("disabled", false);
				            layer.close(index); //此时你只需要把获得的index，轻轻地赋予layer.close即可
		   	            }  
		   			})
	            },     
	            error: function(err) {
	                alert("error,请稍后重试");
	                layer.close(index); //此时你只需要把获得的index，轻轻地赋予layer.close即可
		            $("#checkPayBtn").attr("disabled",false);
	            }     
	        });
	};

	function cancelBtnPay(index){
		alert("暂不支持在线退款，请联系商家退款");
	};
	