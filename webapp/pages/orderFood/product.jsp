<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>菜品详细信息</title>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<%@ include file="/pages/orderFood/css/meta_css.jsp"%>
<%@ include file="/pages/orderFood/js/meta_js.jsp"%>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>菜品详细信息</h1>
 <a href="cart.html" class="topCart"><em>0</em></a>
</header>
<div style="height:1rem;"></div>
<div class="pro_bigImg">
 <img src="${yyWaresDetailVo.icoUrl }" border="1px"/>
</div>
<!--base information-->
<section class="pro_baseInfor">
 <h2>${yyWaresDetailVo.cargoName }</h2>
 <p>
  <strong>${yyWaresDetailVo.discountPrice }</strong>
  <del>${yyWaresDetailVo.unitPrice }</del>
 </p>
</section>
<!--product attr-->
<dl class="pro_attr">
 <dt>菜品属性信息</dt>
 <dd>
 	${yyWaresDetailVo.remark }
 	<br/><br/><br/>
 </dd>
</dl>
<!--bottom nav-->
<aside class="btmNav">
 <a>加入购物车</a>
</aside>
<input type="hidden" name="yyWaresDetailVo.id" id="yyWaresDetailVo.id" value="${yyWaresDetailVo.id }"/>
</body>
<script src="/pages/orderFood/js/product.js" type="text/javascript" ></script>
</html>
