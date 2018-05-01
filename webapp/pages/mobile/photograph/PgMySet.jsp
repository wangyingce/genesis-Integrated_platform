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
			<h1 class="mui-title">我的设置</h1>
		</header>
		<%@ include file="/pages/mobile/common/working.html"%>
	     <!--底部-->	
	     <input type="hidden" id="bottomId" value="gear">
    <%@ include file="/pages/mobile/photograph/PgBottom.jsp"%>
	</body>
<script src="/pages/mobile/photograph/js/PgMyOrder.js" type="text/javascript" ></script>
</html>
