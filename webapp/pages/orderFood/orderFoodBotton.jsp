<%@ page contentType="text/html; charset=utf-8"%>
	<nav class="mui-bar mui-bar-tab">
        <a class="mui-tab-item" id="home" href="/orderFood/init.do?storeId=${storeId}&cargoStatus=${cargoStatus}&userCode=${userCode }">
            <span class="mui-icon mui-icon-home"></span>
            <span class="mui-tab-label">首页</span>
        </a>
        <a class="mui-tab-item" id="compose" href="category.html">
            <span class="mui-icon mui-icon-compose"></span>
            <span class="mui-tab-label">分类</span>
        </a>
        <a class="mui-tab-item" id="contact" href="cart.html">
            <span class="mui-icon mui-icon-contact" ></span>
            <span class="mui-tab-label">购物车</span>
        </a>
        <a class="mui-tab-item" id="gear" href="user.html">
            <span class="mui-icon mui-icon-gear"></span>
            <span class="mui-tab-label">我的</span>
        </a>
    </nav>
	<input type="hidden" id="storeId" value="${storeId}" />
    <input type="hidden" id="userCode" value="${userCode}" />