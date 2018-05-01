<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title>我的照片</title>
    	<%@ include file="/pages/mobile/common/Meta_mobile_css.jsp"%>
    	<%@ include file="/pages/mobile/common/Meta_mobile_js.jsp"%>
    	<script language="javascript" src="/pages/mobile/common/js/mui.min.js"></script>
    	<script language="javascript" src="/pages/mobile/common/js/mui.zoom.js"></script>
    	<script language="javascript" src="/pages/mobile/common/js/mui.previewimage.js"></script>
<style type="text/css">
.mui-preview-image.mui-fullscreen {
	position: fixed;
	z-index: 20;
	background-color: #000;
}
.mui-preview-header,
.mui-preview-footer {
	position: absolute;
	width: 100%;
	left: 0;
	z-index: 10;
}
.mui-preview-header {
	height: 44px;
	top: 0;
}
.mui-preview-footer {
	height: 50px;
	bottom: 0px;
}
.mui-preview-header .mui-preview-indicator {
	display: block;
	line-height: 25px;
	color: #fff;
	text-align: center;
	margin: 15px auto 4;
	width: 70px;
	background-color: rgba(0, 0, 0, 0.4);
	border-radius: 12px;
	font-size: 16px;
}
.mui-preview-image {
	display: none;
	-webkit-animation-duration: 0.5s;
	animation-duration: 0.5s;
	-webkit-animation-fill-mode: both;
	animation-fill-mode: both;
}
.mui-preview-image.mui-preview-in {
	-webkit-animation-name: fadeIn;
	animation-name: fadeIn;
}
.mui-preview-image.mui-preview-out {
	background: none;
	-webkit-animation-name: fadeOut;
	animation-name: fadeOut;
}
.mui-preview-image.mui-preview-out .mui-preview-header,
.mui-preview-image.mui-preview-out .mui-preview-footer {
	display: none;
}
.mui-zoom-scroller {
	position: absolute;
	display: -webkit-box;
	display: -webkit-flex;
	display: flex;
	-webkit-box-align: center;
	-webkit-align-items: center;
	align-items: center;
	-webkit-box-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	left: 0;
	right: 0;
	bottom: 0;
	top: 0;
	width: 100%;
	height: 100%;
	margin: 0;
	-webkit-backface-visibility: hidden;
}
.mui-zoom {
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
}
.mui-slider .mui-slider-group .mui-slider-item img {
	width: auto;
	height: auto;
	max-width: 100%;
	max-height: 100%;
}
.mui-android-4-1 .mui-slider .mui-slider-group .mui-slider-item img {
	width: 100%;
}
.mui-android-4-1 .mui-slider.mui-preview-image .mui-slider-group .mui-slider-item {
	display: inline-table;
}
.mui-android-4-1 .mui-slider.mui-preview-image .mui-zoom-scroller img {
	display: table-cell;
	vertical-align: middle;
}
.mui-preview-loading {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	display: none;
}
.mui-preview-loading.mui-active {
	display: block;
}
.mui-preview-loading .mui-spinner-white {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -25px;
	margin-top: -25px;
	height: 50px;
	width: 50px;
}
.mui-preview-image img.mui-transitioning {
	-webkit-transition: -webkit-transform 0.5s ease, opacity 0.5s ease;
	transition: transform 0.5s ease, opacity 0.5s ease;
}
@-webkit-keyframes fadeIn {
	0% {
		opacity: 0;
	}
	100% {
		opacity: 1;
	}
}
@keyframes fadeIn {
	0% {
		opacity: 0;
	}
	100% {
		opacity: 1;
	}
}
@-webkit-keyframes fadeOut {
	0% {
		opacity: 1;
	}
	100% {
		opacity: 0;
	}
}
@keyframes fadeOut {
	0% {
		opacity: 1;
	}
	100% {
		opacity: 0;
	}
}
p img {
	max-width: 100%;
	height: auto;
}	
</style>
</head>
<body>
	<!--顶部-->
	<header class="mui-bar mui-bar-nav you">
		<a class="mui-action-back mui-icon"></a>
		<h1 class="mui-title">${photoGraphVo.yyWaresOwner.ownerName}</h1>
		<input type="hidden" id="storeId" value="${photoGraphVo.yyWaresOwner.id}">
	</header>
	<div class="mui-content indexfull">
		<div class="mui-content-padded">
			<p>点击图片可放大，长按图片可保存到手机，图片有效期7天，请尽快下载</p>
			<c:if test="${photoGraphVo.isOrder == '2'}">
				<div class="input_row" >
				    <span class="tdleft">学号</span>
		      		<input type="text" id="studentId" name="studentId" value="输入学号" />
				</div>
				 <div class="input_button2">
	      			<input type="button" id="selectBtn" value="查询" disabled="disabled"/>
	    		</div>
			</c:if>
			<div id="myPhotoBody" class="h5">
				<c:if test="${photoGraphVo.isOrder == '1'}">
					<ul class="mui-table-view mui-grid-view">
						<c:forEach var="yyImageFileVo" items="${photoGraphVo.yyImageFiles}">
							 <li class="mui-table-view-cell mui-media mui-col-xs-6">
					         	<p>
					            	<img class="mui-media-object" data-preview-src="" data-preview-group="1" src="${yyImageFileVo.filepath}">
					         	</p>
					            	<div class="mui-media-body">${yyImageFileVo.fileIndex}</div>
					        </li>
						<c:set var="index" value="${index+1}" />
						</c:forEach>
				    </ul>
			    </c:if>
		    </div>
		    <div id="noOrder" style="display:none">
				<%@ include file="/pages/mobile/common/NoOrder.html"%>
				<input type="hidden" id="isOrder" value="${photoGraphVo.isOrder}">
			</div>
		</div>
	</div>
    
</body>
<script>
	mui.previewImage();
</script>
<script src="/pages/mobile/photograph/js/PgMyPhoto.js" type="text/javascript" ></script>
</html>
