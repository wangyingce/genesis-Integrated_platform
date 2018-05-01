<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<link href="/pages/orderFood/css/style.css" type="text/css" rel="stylesheet"/>
<%@ include file="/pages/orderFood/js/meta_js.jsp"%>
</head>
<body>
<!--header-->
<header>
 <a href="javascript:history.go(-1);" class="iconfont backIcon">&#60;</a>
 <h1>购物车</h1>
</header>
<div style="height:1rem;"></div>
<dl class="cart">
 <dt>
 <a class="edit">编辑</a>
  <label></label>
 </dt>
 <c:forEach var="yyShoppingCartVo" items="${cartVo.yyShoppingCartVos}" varStatus="status">
 <dd>
  <a href="" class="goodsPic"><img src="${yyShoppingCartVo.yyWaresDetailVo.icoUrl }"/></a>
  <div class="goodsInfor">
   <h2>
    <a href="">${yyShoppingCartVo.yyWaresDetailVo.cargoName }</a>
    <span>${yyShoppingCartVo.quantity }</span>
   </h2>
   <div class="priceArea">
    <strong>${yyShoppingCartVo.yyWaresDetailVo.discountPrice }</strong>
    <del>${yyShoppingCartVo.yyWaresDetailVo.unitPrice }</del>
   </div>
   <div class="numberWidget">
    <input type="button" value="-" class="minus"/>
    <input type="text" value="${yyShoppingCartVo.quantity }" disabled  class="number"/>
    <input type="button" value="+"  class="plus"/>
   </div>
  </div>
  	<input type="hidden" name="storeId" class="storeId" value="${yyShoppingCartVo.storeId }">
   	<input type="hidden" name="waresID" class="waresID" value="${yyShoppingCartVo.waresID }">
   	<input type="hidden" name="userCode" class="userCode" value="${yyShoppingCartVo.userCode }">
   	<input type="hidden" name="quantity" class="quantity" value="${yyShoppingCartVo.quantity }">
   	<input type="hidden" name="cartID" class="cartIDa" value="${yyShoppingCartVo.id }">
   	<input type="hidden" name="discountPrice" class="discountPrice" value="${yyShoppingCartVo.yyWaresDetailVo.discountPrice }">
  	<c:if test="${yyShoppingCartVo.validStatus=='00'}">
  	<a class="delBtn">删除</a>
  	</c:if>
 </dd>
</c:forEach>
</dl>
<!--bottom nav-->
<div style="height:1rem;"></div>
<aside class="btmNav">
 <a id='sumPrice' style="background:#f8f8f8;color:#999;text-shadow:none;">合计：￥${cartVo.sumPrice}</a>
 <a href="confirm_order.html" style="background:#64ab5b;color:white;text-shadow:none;">立即下单</a>
</aside>
<script src="/pages/orderFood/js/cart.js" type="text/javascript" ></script>
</body>
</html>
