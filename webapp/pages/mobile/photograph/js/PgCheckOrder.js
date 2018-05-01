$(function() {
	var index = 0;
	//弹出一个页面层
	$('#payBtn').on('click', function(){
	  var wheight = window.screen.height/1.7+"px";
	  var wwidth = window.screen.width+"px";
	  //组织要展示的页面 预约信息
	  var place = document.getElementById("place");
	  var placeTxt=place.options[place.options.selectedIndex].text;
	  var photoType = document.getElementById("photoType");
	  var photoTypeTxt=photoType.options[photoType.options.selectedIndex].text;
	  //获取预约时段
	  var checkOpt = document.getElementById("checkTime");
	  var timeLabel = (checkOpt.options[checkOpt.options.selectedIndex].parentNode.attributes)[1].value;
	  var timeText = checkOpt.options[checkOpt.options.selectedIndex].text;
	  
	  //获取服务价格
	  var disPrice = document.getElementById("disPrice").value;
	  //获取预约费用
	  var orderPrice = document.getElementById("orderPrice").value;
	  //获取商户地址、电话、名称
	  var ownerAddress = document.getElementById("ownerAddress").value;
	  var ownerPhone = document.getElementById("ownerPhone").value;
	  var ownerName = document.getElementById("ownerName").value;
	  var innerHtml = "<div class='input_ceng'>" +
	  		"<h3 class='text_b'><span>预约信息："+placeTxt+"/"+photoTypeTxt+"</span></h3>" +
	  		"<h3 class='text_b'>预约时段：<span>"+timeLabel+" "+timeText+"</span></h3>" +
	  		"<h3 class='text_b'>服务价格：<span class='money money_o'>"+disPrice+"</span></h3>" +
	  		"<h3 class='text_b'>预约费用：<span class='money money_o'>"+orderPrice+"</span></h3>" +
	  		"<h3 class='text_b'>商户名称：<span>"+ownerName+"</span></h3>" +
	  		"<h3 class='text_b'>商户地址：<span>"+ownerAddress+"  tel:"+ownerPhone+"</span></h3>" +
	  		"</div>" +
	  		"<div class='input_button3 w30 h5'>" +
	  		"<input type='button' id='checkPayBtn' value='确认支付' onclick='payOrder();'/>" +
	  		"<span><input type='button' id='cancelBtn' value='取消支付' onclick='closeLay();'/></span>" +
	  		"</div>";
	  
	  
	 index = layer.open({
		  title :'订单确认',
		  type: 1,
		  area: [wwidth, wheight],//定义弹出的宽度和高度
		  offset: 'rb', //右下角弹出
		  shift:2,//弹出动画方式
		  shadeClose: false, //点击遮罩关闭
		  skin: 'layui-layer-lan',//调用样式
		  content: innerHtml
	  });
	  //把打开层的id存在页面上
	  document.getElementById("layId").value = index;
	  
	});
	
    //初始化三层选择
    /**var storeId = $("#storeId").val();
    var innerString = "";
    var valuesList = new Array();
    $.ajax({
            type: "get",     
            url: "/photograph/initCheckOrderInfo.do?",    
            data: {
            	storeId:storeId
            }, 
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
            	innerString += "<select id='checkTime' class='demo-test-select-opt'>";
  				$.each(data.photoGraphTimeVos,function(i,item){
  					//根据返回的内容拼接html5商品大类
  					innerString += "<optgroup id='"+i+"' label='"+item.pgDate+"'>";
  					innerString += "<li><a href='#'><img src='"+item.pgDate+"'/>";
  					valuesList = item.yyPgCheckOrders;
  					for(var i=0; i<valuesList.length; i++) {
						innerString += "<option value="+valuesList[i].pgBeginTime+">"+valuesList[i].pgBeginTime+"点-"+valuesList[i].pgEndTime+"点</option>";
					}
  					innerString += "</optgroup>";
  				});
  				innerString += "</select>"
  				//document.getElementById("innerTime").innerHTML=innerString;
            },
            error: function(err) { 
                alert("error"+err);     
            }     
        });*/
        
         var opt = {
			        'select-city': {
			            preset: 'select',
			            selectedText: '请选择',
			            //样式'android-holo-light','android-ics light'
			            theme: 'android-ics light',
			            mode: 'scroller',
			            //指定显示格式 'bottom','modal'
			            display: 'modal',
			            animate: 'fade'
			        },
			        'select-photoType': {
			            preset: 'select',
			            selectedText: '请选择',
			            //样式'android-holo-light','android-ics light'
			            theme: 'android-ics light',
			            mode: 'scroller',
			            //指定显示格式 'bottom','modal'
			            display: 'modal',
			            animate: 'fade'
			        },
			        'select-opt': {
			            preset: 'select',
			            group: true,
			            selectedText: '请选择',
			            width: 50,
			            //指定显示格式 'bottom','modal'
			            display: 'bottom',
			            //滚动区域内的行数
			            //rows:3  
					    //defaultValue: ['', '', ''],//设置初始值  
					    //labels: ['省', '市', '区'],  
					    //showLabel:true,//是否显示labels  
			        },
			        'default-photoType': {
			            //useShortLabels: true,
			            //是否显示label
			            //showLabel:true,
			            onSelect: function(valueText, labelText) {
			                //点击确定以后的结果
			                //获得实际选择的
			                var photoType = (labelText.values)[0];
			                //获得目前展示的
			                var yyPgPhotoType =document.getElementById("yyPgPhotoType").value;
			                //如果实际选择的和展示的不等于则说明发生变化，需要重新加载信息
			                if(photoType != yyPgPhotoType){
				                //获取城市
				                var city = document.getElementById("place").value;
				                //获取商户id
				                var storeId = document.getElementById("storeId").value;
			                	var innerString = "";
			                	var innerStringTime = "";
			                	//先把对应模块变为加载中
			                	//document.getElementById("photoExplain").innerHTML="<img src='/pages/mobile/photograph/images/loading.gif'/>";
			                	document.getElementById("photoExplain").innerHTML="加载中.....请稍后";
			                	$("#payBtn").attr("disabled", true);
			                	$.ajax({
						            type: "get",     
						            url: "/photograph/initCheckOrderInfo.do?",    
						            data: {
						            	storeId:storeId,
						            	city:city,
						            	photoType:photoType
						            }, 
						            contentType: "application/json; charset=utf-8",
						            dataType: "json",
						            success: function(data) {
						            	//生成照片说明
						            	innerString += "<h3 class='headtitle text_w'>"+data.yyPgOrderDetail.photoName+"：<del class='money'>"+data.yyPgOrderDetail.price+"</del>";
						            	innerString += "/<span class='money'>"+data.yyPgOrderDetail.disPrice+"</span></h3>";
						            	innerString += "<img src='"+data.yyPgOrderDetail.imageUrl+"'/>";
						            	innerString += "<h3 class='headtitle text_w'>"+data.yyPgOrderDetail.projects+"</h3>";
						            	innerString += "<h3 class='headtitle text_w'>服务时长：<span>"+data.yyPgOrderDetail.serverTimes+"小时</span></h3>";
						            	innerString += "<h3 class='headtitle text_w'>预约费用：<span class='money'>"+data.yyPgOrderDetail.orderPrice+"</span></h3>";
						            	innerString += "<input type='hidden' id='yyPgPhotoType' value='"+data.yyPgOrderDetail.photoType+"'/>";
						  				document.getElementById("photoExplain").innerHTML=innerString;
						  				document.getElementById("disPrice").value=data.yyPgOrderDetail.disPrice;
						  				document.getElementById("orderPrice").value=data.yyPgOrderDetail.orderPrice;
						  				//循环生成可预约时段
						  				innerStringTime += "<select id='checkTime' class='demo-test-select-opt'>";
						  				$.each(data.yyPgOrderDates,function(i,item){
						  					//根据返回的内容拼接html5商品大类
						  					innerStringTime += "<optgroup id='checkOpt"+i+"' label='"+item.pgDate+"("+item.weekName+")'>";
						  					innerStringTime += "<li><a href='#'><img src='"+item.pgDate+"'/>";
						  					valuesList = item.yyPgOrderTimes;
						  					for(var i=0; i<valuesList.length; i++) {
						  						if(valuesList[i].freeTimes == 0){
													innerStringTime += "<option disabled='disabled' id='checkTime"+valuesList[i].id+"' value="+valuesList[i].id+">"+valuesList[i].pgBeginTime+"点-"+valuesList[i].pgEndTime+"点</option>";
						  						}else{
						  							innerStringTime += "<option id='checkTime"+valuesList[i].id+"' value="+valuesList[i].id+">"+valuesList[i].pgBeginTime+"点-"+valuesList[i].pgEndTime+"点</option>";
						  						}
											}
						  					innerStringTime += "</optgroup>";
						  				});
						  				innerStringTime += "</select>";
						  				document.getElementById("checkTime").innerHTML=innerStringTime;
						  				//级联选择设置样式			
			    						$('.demo-test-select-opt').scroller($.extend(opt['select-opt'], opt['default-opt']));
						  				$("#payBtn").attr("disabled", false);
						            },
						            error: function(err) { 
						                alert("error"+err);     
						            }     
						        });
			                }
			                //alert(labelText.values[1]);
			                //document.getElementById("photoExplain_Jt").style = "";
			                //document.getElementById("photoExplain_Wm").style = "display:none";
			            }
			        },
			        'default-city': {
			            //useShortLabels: true,
			            //是否显示label
			            //showLabel:true,
			            onSelect: function(valueText, labelText) {
			            }
			        },
			        'default-opt': {
			            //useShortLabels: true,
			            //是否显示label
			            //showLabel:true,
			            //返回自定义格式结果,把label和value拼起来显示
			            formatResult: function(array) {
			                var ss = null;
			                var text = "";
			                var returnTxt = "";
			                var lableTxt = "";
			                try {
			                	//先获取label对应的位置
			                	ss = document.getElementById("checkOpt"+array[0]);
			                    //ss = checkTime.options[array[0]].parentNode.attributes;
			                    lableTxt = ss.label;
			                    //再获取option对应的Text
			                    text = document.getElementById("checkTime"+array[1]).text;
			                    //text = checkTime.options[array[1] - 1].text;
			                    returnTxt = lableTxt + ' ' + text;
			                } catch(e) {
			                    //alert("error");
			                }
			                return returnTxt;
			            }
			
			        },
			    }
			    //单选设置样式
			    $('.demo-test-select-city').scroller($.extend(opt['select-city'], opt['default-city']));
			    $('.demo-test-select-photoType').scroller($.extend(opt['select-photoType'], opt['default-photoType']));
			    //级联选择设置样式			
			    $('.demo-test-select-opt').scroller($.extend(opt['select-opt'], opt['default-opt']));
			    	     
});

function closeLay(){
	var layId =  document.getElementById("layId").value;
	layer.close(layId); //此时你只需要把获得的index，轻轻地赋予layer.close即可
};

function payOrder(){
	//打开一个loading层
	var index = layer.load(1, {
	  shade: [0.2,'#fff'] //0.2透明度的白色背景
	});
	//获取页面选择的预约信息
	var checkTimeId = document.getElementById("checkTime").value;
	var storeId = document.getElementById("storeId").value;
	var storeName = document.getElementById("ownerName").value;
	var userCode = document.getElementById("userCode").value;
	var inKey = document.getElementById("inKey").value;
	//按钮只读
	$("#checkPayBtn").attr("disabled", true);
	//先保存订单信息，然后再订单支付，打开微信支付页面
	 $.ajax({
            type: "get",     
            url: "/photograph/submitPgCheckOrder.do?",
            data: {
			   	storeId:storeId,
			   	checkTimeId:checkTimeId,
			   	storeName:storeName,
			   	userCode:userCode,
			   	inKey:inKey
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
	   	            	WeixinJSBridge.invoke('closeWindow');
	   	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
						$("#checkPayBtn").attr("disabled", false);
						layer.close(index); //此时你只需要把获得的index，轻轻地赋予layer.close即可
	   	            }else{
	   	               	alert("error,请稍后重试");
			            $("#checkPayBtn").attr("disabled", false);
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
	