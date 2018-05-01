<%@ page contentType="text/html; charset=utf-8"%>
	<nav class="mui-bar mui-bar-tab">
        <a class="mui-tab-item" id="home" href="/photograph/showIndex.do?photoGraphVo.yyUser.userCode=${photoGraphVo.yyUser.userCode}&photoGraphVo.storeId=${photoGraphVo.storeId}&photoGraphVo.yyUser.inKey=${photoGraphVo.yyUser.inKey}">
            <span class="mui-icon mui-icon-home"></span>
            <span class="mui-tab-label">首页</span>
        </a>
        <a class="mui-tab-item" id="compose" href="/photograph/registPhotoGraph.do?storeId=${photoGraphVo.storeId}">
            <span class="mui-icon mui-icon-compose"></span>
            <span class="mui-tab-label">预约</span>
        </a>
        <a class="mui-tab-item" id="contact" href="/photograph/initMyOrder.do?photoGraphVo.yyUser.userCode=${photoGraphVo.yyUser.userCode}&photoGraphVo.storeId=${photoGraphVo.storeId}&photoGraphVo.yyUser.inKey=${photoGraphVo.yyUser.inKey}">
            <span class="mui-icon mui-icon-contact" ></span>
            <span class="mui-tab-label">我的订单</span>
        </a>
        <a class="mui-tab-item" id="gear" href="/photograph/initMySet.do?photoGraphVo.yyUser.userCode=${photoGraphVo.yyUser.userCode}&photoGraphVo.storeId=${photoGraphVo.storeId}&photoGraphVo.yyUser.inKey=${photoGraphVo.yyUser.inKey}">
            <span class="mui-icon mui-icon-gear"></span>
            <span class="mui-tab-label">设置</span>
        </a>
    </nav>
	<input type="hidden" id="storeId" value="${photoGraphVo.storeId}" />
    <input type="hidden" id="userCode" value="${photoGraphVo.yyUser.userCode}" />
