<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title>我的订单</title>
    	<%@ include file="/pages/mobile/common/Meta_mobile_css.jsp"%>
    	<%@ include file="/pages/mobile/common/Meta_mobile_js.jsp"%>
	</head>
	<body>
		<!--顶部-->
		<header class="mui-bar mui-bar-nav you">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">我的订单</h1>
			<a class="mui-icon mui-icon-left-nav mui-pull-right phonefont" href="tel:${photoGraphVo.phoneNumber}"></a>
		</header>
			<div class="mui-content">
				<dl class="bw tab_proList">
					<dt class="sy">
				  		<a id="01" class="baccolor">待付款</a>
				  		<a id="11">预约中</a>
				  		<a id="67">已完成</a>
				  		<a id="ALL">全部</a>
				 	</dt>
				 	<div id="noOrder" style="display: none">
						<%@ include file="/pages/mobile/common/NoOrder.html"%>
						<input type="hidden" id="isOrder" value="${photoGraphVo.isOrder}">
					</div>
				</dl>
				<input type="hidden" id="nowIdName" value="01">
			</div>
			<div id="myOrderBody">
				<c:if test="${photoGraphVo.isOrder == '1'}">
					<c:set var="index" value="0"></c:set>
				    <c:forEach var="yyPgMyOrderVo" items="${photoGraphVo.yyPgMyOrders}">
				    	<div class="bwl">
					    	<div>
								<span class="spanbefore">${yyPgMyOrderVo.ownerName}</span>
							  	<a class="bwla">${yyPgMyOrderVo.payStateName}</a>
							</div>
							<div class="bgef">
								<span><img src="${yyPgMyOrderVo.imageUrl}"/>
						  		<div class="text_c">${yyPgMyOrderVo.photoName} ${yyPgMyOrderVo.pgDate} ${yyPgMyOrderVo.pgBeginTime}点-${yyPgMyOrderVo.pgEndTime}点</div>
						  		<div class="text_c">${yyPgMyOrderVo.ownerAddress} Tel:${yyPgMyOrderVo.ownerPhone}</div>
						  		<div class="text_c">预约金额:￥${yyPgMyOrderVo.orderPrice}</div></span>
							</div>
							<div class="input_button4">
				      			<input type="button" id="cancelBtn${index}" onclick="cancelBtn(${index})" value="取消订单" />
				      			<span><input type="button" onclick="payBtn(${index})" id="payBtn${index}" value="付款" /></span>
				    		</div>
				    		<input type="hidden" id="orderId${index}" value="${yyPgMyOrderVo.id}"/>
						</div>
					<c:set var="index" value="${index+1}" />
					</c:forEach>
				</c:if>
			</div>
			<div class="h5"></div>
			<div class="h5"></div>
	     <!--底部-->	
	     <input type="hidden" id="bottomId" value="contact">
    <%@ include file="/pages/mobile/photograph/PgBottom.jsp"%>
	</body>
<script src="/pages/mobile/photograph/js/PgMyOrder.js" type="text/javascript" ></script>
</html>
