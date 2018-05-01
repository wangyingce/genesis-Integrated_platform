<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title>快速预约</title>
    	<%@ include file="/pages/mobile/common/Meta_mobile_css.jsp"%>
    	<%@ include file="/pages/mobile/common/Meta_mobile_js.jsp"%>
    	<script src="/pages/mobile/common/js/mobiscroll.2.13.2.js"></script>
    	<link href="/pages/mobile/common/style/mobiscroll.2.13.2.css" rel="stylesheet" />

</head>
<body>
	<!--顶部-->
	<header class="mui-bar mui-bar-nav you">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">快速预约</h1>
	</header>
		
		<div class="mui-content">
			<div class="bw">
				<div class="headtitle">
					验证成功！请选择您的预约类型
				</div>
				<div class="midtitle">
			    	&nbsp;&nbsp;预约流程：选择摄影类型→选择摄影时间→确认订单信息→订单支付→预约成功
			    </div>
			</div>
			 
			<div class="bw h5">
			    <div class="input_row">
					<span class="tdleft">地区</span>
			            <select class="demo-test-select-city" data-role="none" id="place">
							<c:forEach var="yyDcodeC" items="${photoGraphVo.yyDcodeCities}">
				            	<option value="${yyDcodeC.id.codeCode}">${yyDcodeC.codeCName}</option>
							</c:forEach>
			            </select>
			    </div>
				<div class="input_row">
					<span class="tdleft">类型</span>
			            <select class="demo-test-select-photoType" data-role="none" id="photoType">
			               <c:forEach var="yyDcodeP" items="${photoGraphVo.yyDcodePhotoTypes}">
				            	<option value="${yyDcodeP.id.codeCode}">${yyDcodeP.codeCName}</option>
							</c:forEach>
			            </select>
			    </div>
			    <div class="input_row">
				    <span class="tdleft">日期</span>
				    <div id="innerTime">
				        <select id="checkTime" class="demo-test-select-opt" >
				        	<c:set var="index" value="0"></c:set>
				        	<c:forEach var="yPgOrderDateVo" items="${photoGraphVo.yyPgOrderDates}">
					            <optgroup id="checkOpt${index}" label="${yPgOrderDateVo.pgDate}(${yPgOrderDateVo.weekName})">
					            	<c:forEach var="yyPgOrderTime" items="${yPgOrderDateVo.yyPgOrderTimes}">
					            		<c:if test="${yyPgOrderTime.freeTimes =='0'}">
						            		<option id="checkTime${yyPgOrderTime.id}" disabled="disabled" value="${yyPgOrderTime.id}">${yyPgOrderTime.pgBeginTime}点-${yyPgOrderTime.pgEndTime}点</option>
					            		</c:if>
					            		<c:if test="${yyPgOrderTime.freeTimes !='0'}">
						            		<option id="checkTime${yyPgOrderTime.id}" value="${yyPgOrderTime.id}">${yyPgOrderTime.pgBeginTime}点-${yyPgOrderTime.pgEndTime}点</option>
					            		</c:if>
					            	</c:forEach>
					            </optgroup>
					         <c:set var="index" value="${index+1}" />
							</c:forEach>
				        </select>
			        </div>
			     </div>
    		</div>
    		<div class="bw h5" id="photoExplain_Wm">
	    		<div class="input_img" id="photoExplain">
	            	<h3 class="headtitle text_w">${photoGraphVo.yyPgOrderDetail.photoName}：<del class="money">${photoGraphVo.yyPgOrderDetail.price}</del>/<span class="money">${photoGraphVo.yyPgOrderDetail.disPrice}</span></h3>
	    			<img src="${photoGraphVo.yyPgOrderDetail.imageUrl}"/>
	            	<h3 class="headtitle text_w">${photoGraphVo.yyPgOrderDetail.projects}</h3>
	            	<h3 class="headtitle text_w">服务时长：<span>${photoGraphVo.yyPgOrderDetail.serverTimes}小时</span></h3>
	            	<h3 class="headtitle text_w">预约费用：<span class="money">${photoGraphVo.yyPgOrderDetail.orderPrice}</span></h3>
	            	<input type="hidden" id="yyPgPhotoType" value="${photoGraphVo.yyPgOrderDetail.photoType}" />
	            </div>
	            <div class="input_button2">
	      			<input type="button" id="payBtn" value="预约支付"/>
	    		</div>
	    		<div class="h15 bw"></div>
    		</div>
    	</div>
    	
    	<input type="hidden" id="layId" value="1" />
    	<input type="hidden" id="disPrice" value="${photoGraphVo.yyPgOrderDetail.disPrice}"/>
    	<input type="hidden" id="orderPrice" value="${photoGraphVo.yyPgOrderDetail.orderPrice}"/>
    	<input type="hidden" id="ownerAddress" value="${photoGraphVo.yyWaresOwner.registAddress}"/>
    	<input type="hidden" id="ownerPhone" value="${photoGraphVo.yyWaresOwner.registPhone}"/>
    	<input type="hidden" id="ownerName" value="${photoGraphVo.yyWaresOwner.ownerName}"/>
    	<input type="hidden" id="inKey" value="${photoGraphVo.yyUser.inKey}" />
    	<!--底部-->
    	<input type="hidden" id="bottomId" value="compose">
    <%@ include file="/pages/mobile/photograph/PgBottom.jsp"%>
</body>
<script src="/pages/mobile/photograph/js/PgCheckOrder.js" type="text/javascript" ></script>
</html>
