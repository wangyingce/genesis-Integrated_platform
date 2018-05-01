<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>表格</title> 
</head>
<body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css">
<style>
#divMain {
width: 500px;
margin: 0 auto;
margin-top: 100px;
}
nav {
position: relative;
width:100%;
height: 50px;
}
.pagination {
right: 0px;
position: absolute;
top: -30px;
}
nav li {
cursor: pointer;
}
</style>
<div id="divMain" ng-app="myApp" ng-controller="myCtrl">
<table class="table table-bordered">
<tr>
				<th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
				<th width="80px">产品编号</th>
				<th width="250px">产品名称</th>
				<th width="100px">原价格</th>
				<th width="100px">现价</th>
				<th width="100px">数量</th>
                <th width="100px">所属地区/国家</th>				
				<th width="180px">加入时间</th>
                <th width="80px">审核状态</th>
				<th width="70px">状态</th>                
				<th width="200px">操作</th>
			</tr>
<tr ng-repeat="prod in yybsproducts">
        <td width="25px"><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
        <td width="80px" >{{prod.productId}}</td>
        <td width="250px"><u style="cursor:pointer" class="text-primary" onclick="">{{prod.productName}}</u></td>
        <td width="100px">{{prod.marketPrice}}</td>
        <td width="100px">{{prod.showPrice}}</td> 
        <td width="100px">{{prod.productNum}}</td> 
        <td width="100px">{{prod.country}}</td>         
        <td width="180px">2014-6-11 11:11:42</td>
        <td class="text-l">通过</td>
        <td class="td-status"><span class="label label-success radius">{{prod.validstatus}}</span></td>
        <td class="td-manage">
        <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
        <a title="编辑" onclick="member_edit('编辑','member-add.html','4','','510')" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
        <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
       </td>
	  </tr>
</table>
<nav>
<ul class="pagination">
<li>
<a ng-click="Previous()">
<span>上一页</span>
</a>
</li>
<li ng-repeat="page in pageList" ng-class="{active: isActivePage(page)}" >
<a ng-click="selectPage(page)" >{{ page }}</a>
</li>
<li>
<a ng-click="Next()">
<span>下一页</span>
</a>
</li>
</ul>
</nav>
</div>
<script src="http://apps.bdimg.com/libs/angular.js/1.5.0-beta.0/angular.js"></script>
<script>
var app = angular.module("myApp", []);
app.controller("myCtrl", function ($scope, $http) {
$http.get("/product/queryProductList.do").success(function (response) {
//数据源
$scope.data = response.jsondata;
//分页总数
$scope.pageSize = 5;
$scope.pages = Math.ceil($scope.data.length / $scope.pageSize); //分页数
$scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
$scope.pageList = [];
$scope.selPage = 1;
//设置表格数据源(分页)
$scope.setData = function () {
$scope.yybsproducts = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
}
$scope.yybsproducts = $scope.data.slice(0, $scope.pageSize);
//分页要repeat的数组
for (var i = 0; i < $scope.newPages; i++) {
$scope.pageList.push(i + 1);
}
//打印当前选中页索引
$scope.selectPage = function (page) {
//不能小于1大于最大
if (page < 1 || page > $scope.pages) return;
//最多显示分页数5
if (page > 2) {
//因为只显示5个页数，大于2页开始分页转换
var newpageList = [];
for (var i = (page - 3) ; i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)) ; i++) {
newpageList.push(i + 1);
}
$scope.pageList = newpageList;
}
$scope.selPage = page;
$scope.setData();
$scope.isActivePage(page);
console.log("选择的页：" + page);
};
//设置当前选中页样式
$scope.isActivePage = function (page) {
return $scope.selPage == page;
};
//上一页
$scope.Previous = function () {
$scope.selectPage($scope.selPage - 1);
}
//下一页
$scope.Next = function () {
$scope.selectPage($scope.selPage + 1);
};
});
})
</script>
</body>
</html>