<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>友商友良-快捷点餐</title>
<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
<%@ include file="/pages/orderFood/css/meta_css.jsp"%>
<%@ include file="/pages/orderFood/js/meta_js.jsp"%>

</head>
<body>
<!--header-->
<header>
 <h1 class="logoIcon" style="font-size:.80rem;">首  页</h1>
 <a href="search.html" class="rt_searchIcon">&#37;</a>
</header>
<div style="height:1rem;"></div>
<!--头部图片旋转-->
<div id="slide">
  <div class="swiper-wrapper">
    <div class="swiper-slide">
     <a href="#">
      <img src="/pages/orderFood/upload/slide001.jpg"/>
     </a>
    </div>
    <div class="swiper-slide">
     <a href="#">
      <img src="/pages/orderFood/upload/slide002.jpg"/>
     </a>
    </div>
    <div class="swiper-slide">
     <a href="#">
      <img src="/pages/orderFood/upload/slide003.jpg"/>
     </a>
    </div>
  </div>
  <div class="pagination"></div>  
</div>
<!--Tab:productList-->
<dl class="tab_proList">
 <dt class="sy">
	 <a id="01">热销</a>
	 <a id="02">新品</a>
	 <a id="03">打折</a>
 </dt>
 <div id="noOrder" style="display: none">
	 <%@ include file="/pages/mobile/common/NoOrder.html"%>
	 <input type="hidden" id="isOrder" value="${photoGraphVo.isOrder}">
 </div>
 <dd>
  <ul id="myOrderBody">
   <c:forEach var="yyWaresDetailVo" items="${cartVo.yyWaresDetailVos}" varStatus="status">
	   <li>
	    <a href="/orderFood/getProductDetil.do?productId=${yyWaresDetailVo.id }" class="goodsPic">
	     <img src="${yyWaresDetailVo.icoUrl }"/>
	    </a>
	    <div class="goodsInfor">
	     <h2>
	      <a href="/orderFood/getProductDetil.do?productId=${yyWaresDetailVo.id }">${yyWaresDetailVo.cargoName }</a>
	     </h2>
	     <p>
	     <!-- 原价 -->
	      <del>${yyWaresDetailVo.unitPrice }</del>
	     </p>
	     <p>
	     <!--现价 -->
	      <strong class="price">${yyWaresDetailVo.discountPrice }</strong>
	     </p>
	     <a class="addToCart" name="addCart" id="${yyWaresDetailVo.id }">&#126;</a>
	     <input type='hidden' id='orderId${status.index }' value='${yyWaresDetailVo.id }'/></div>
	    </div>
	   </li>
   </c:forEach>
  </ul>
 </dd>
 
</dl>
<!--floatCart-->
<div class="hoverCart">
 <a href="/orderFood/getShoppingCartByUserCode.do?storeId=${storeId}&userCode=${userCode}">${cartVo.sumNumber }</a>
</div>
<div style="height:1.2rem;"></div>
<%@ include file="/pages/orderFood/orderFoodBotton.jsp"%>

<script>
  document.oncontextmenu=new Function("event.returnValue=false;");
  document.onselectstart=new Function("event.returnValue=false;"); 
</script>
</body>
<script src="/pages/orderFood/js/orderFoodIndex.js" type="text/javascript" ></script>
</html>
