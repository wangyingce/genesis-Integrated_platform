<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title>确认预约</title>
    	<%@ include file="/pages/mobile/common/Meta_mobile_css.jsp"%>
    	<%@ include file="/pages/mobile/common/Meta_mobile_js.jsp"%>
	</head>
	<body>
		<!--顶部-->
				<div class="pgbg">
					<div class="pgbgdiv">
						<h3 class="pgbgtop"><span class="successfont"> 预约成功信息</span></h3>
		            	<h3 class="pgbgtext">预约类型：<span>${yyPgMyOrder.cityName}/${yyPgMyOrder.photoName}</span></h3>
		            	<h3 class="pgbgtext">预约时段：<span>${yyPgMyOrder.pgDate} ${yyPgMyOrder.pgBeginTime}点-${yyPgMyOrder.pgEndTime}点</span></h3>
		            	<h3 class="pgbgtext">已支付费用：<span class="money money_o">${yyPgMyOrder.orderPrice}</span></h3>
		            	<h3 class="pgbgtext">门店地址：<span>${yyPgMyOrder.ownerAddress}</span></h3>
		            	<h3 class="pgbgtext">预约人信息：<span>${yyPgMyOrder.userName} <a class="phonefont" href="tel:${yyPgMyOrder.userPhone}">${yyPgMyOrder.userPhone}</a></span></h3>
		            	<h3 class="pgbgbutton"><input type='button' id='confirmOrder' value='确认订单'/><input type='button' id='exportOrder' value='导出订单'/></h3>
		            	<!-- <h3 class="text_b">我要留言：
		            		<textarea class="input_textarea" placeholder="选填(亲可以在这里添加想说的话)"></textarea>
		            	</h3> -->
		            	<input type="hidden" id="myOrderId" value="${yyPgMyOrder.id}">
		            	<input type="hidden" id="isCheck" value="${yyPgMyOrder.isCheck}">
		            </div>
	    		</div>
	     <!--底部-->	
	</body>
<script src="/pages/mobile/photograph/js/PgOrderConfirm.js" type="text/javascript" ></script>
</html>
