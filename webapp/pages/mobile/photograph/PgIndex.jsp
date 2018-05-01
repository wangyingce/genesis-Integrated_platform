<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title>快速预约</title>
    	<%@ include file="/pages/mobile/common/Meta_mobile_css.jsp"%>
    	<%@ include file="/pages/mobile/common/Meta_mobile_js.jsp"%>
    	<script language="javascript" src="/pages/mobile/common/js/mui.min.js"></script>

</head>
<body>
	<!--顶部-->
	<header class="mui-bar mui-bar-nav you">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">${photoGraphVo.yyWaresOwner.ownerName}</h1>
	</header>
	<div class="indexfull">
		<div class="pgbg">
			<div class="mui-slider indeximg">
			  <div class="mui-slider-group mui-slider-loop">
			    <!--支持循环，需要重复图片节点-->
			    <div class="mui-slider-item mui-slider-item-duplicate"><a href="${photoGraphVo.yyWaresOwner.logoHref1}"><img src="${photoGraphVo.yyWaresOwner.logoUrl1}" /></a></div>
			    <div class="mui-slider-item"><a href="${photoGraphVo.yyWaresOwner.logoHref}"><img src="${photoGraphVo.yyWaresOwner.logoUrl}" /></a></div>
			    <div class="mui-slider-item"><a href="${photoGraphVo.yyWaresOwner.logoHref1}"><img src="${photoGraphVo.yyWaresOwner.logoUrl1}" /></a></div>
			    <c:if test="${photoGraphVo.yyWaresOwner.logoUrl2 != null}">
			    	<div class="mui-slider-item"><a href="${photoGraphVo.yyWaresOwner.logoHref2}"><img src="${photoGraphVo.yyWaresOwner.logoUrl2}" /></a></div>
			    </c:if>
			    <div class="mui-slider-item mui-slider-item-duplicate "><a href="${photoGraphVo.yyWaresOwner.logoHref}"><img src="${photoGraphVo.yyWaresOwner.logoUrl}" /></a></div>
			  </div>
				<div class="mui-slider-indicator">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>
					<c:if test="${photoGraphVo.yyWaresOwner.logoUrl2 != null}">
			    		<div class="mui-indicator"></div>
			    	</c:if>
				</div>
			</div>
		</div>
		<%-- <div class="indexphoto">
			<img src="/pages/mobile/photograph/images/idxleft.jpg"/>
			<span><img src="/pages/mobile/photograph/images/idxright.jpg"/></span>
		</div>--%>
			<c:set var="index" value="0"></c:set>
			<c:forEach var="yyPgIndex" items="${photoGraphVo.yyPgIndexs}">
				<div class="indexdiv">
					<span><img src="${yyPgIndex.logoUrl}"/>
					<a href="${yyPgIndex.contextUrl}">
						<div class="indextext indextexttitle">${yyPgIndex.title}</div>
						<div class="indextext">${yyPgIndex.context}</div></span>
					</a>
				</div>
			<c:set var="index" value="${index+1}" />
			</c:forEach>
		
		<div class="h15">
		</div>
	</div>
	
	
	
    <!--底部-->
    	<input type="hidden" id="bottomId" value="home">
    <%@ include file="/pages/mobile/photograph/PgBottom.jsp"%>
</body>
<script src="/pages/mobile/photograph/js/PgIndex.js" type="text/javascript" ></script>
</html>
