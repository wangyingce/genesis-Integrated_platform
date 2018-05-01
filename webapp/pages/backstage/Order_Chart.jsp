<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>订单</title>
	<script src="js/jquery-1.9.1.min.js"></script>
    <script src="assets/dist/echarts.js"></script>
    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="css/style.css"/>
    
    <!-- 引入angularjs -->
<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
</head>

<body ng-app="orderMapApp" ng-controller="orderMapCtrl">
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
<div id="map" style="width:100%; overflow:auto; overflow:hidden";></div>
</body>
</html>
<script>
 //初始化宽度、高度
    $("#map").height($(window).height()-20); 
	
    //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$("#map").height($(window).height()-20); 
  });
</script>
<!-- angularJs加载块 -->
<script>
/**自定义域*/
var orderMapApp = angular.module("orderMapApp", []); 
orderMapApp.controller('orderMapCtrl', function($scope, $http) {
	   $scope.nowloading = true;
	   $http.get("/order/queryOrderMap.do").success(
			    function (response) {
			    	$scope.nowloading = false;
	                 /**地图数据*/
	                 $scope.orderMap = response.orderMap;
			    	/**地图统计 start*/
	        require.config({
            paths: {
                echarts: './assets/dist'
            }
        });
        require(
            [
                'echarts',
				'echarts/theme/macarons',
                'echarts/chart/map',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                //'echarts/chart/bar'
            ],
            function (ec,theme) {
                var myChart = ec.init(document.getElementById('map'),theme);
				option = {
    title : {
        text: '订单量',
        subtext: 'CHINA',
        x:'center'
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['订单量']
    },
    dataRange: {
        x: 'left',
        y: 'bottom',
        splitList: [
            {start: 1500},
            {start: 900, end: 1500},
            {start: 310, end: 1000},
            {start: 200, end: 300},
            {start: 10, end: 200, label: '10 到 200'},
            {start: 5, end: 5, label: '5', color: 'black'},
            {end: 10}
        ],
        color: ['#E0022B', '#E09107', '#A3E00B']
    },
    toolbox: {
        show: true,
        orient : 'vertical',
        x: 'right',
        y: 'center',
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    roamController: {
        show: true,
        x: 'right',
        mapTypeControl: {
            'china': true
        }
    },
    series : [
        {
            name: '订单量',
            type: 'map',
            mapType: 'china',
            roam: false,
            itemStyle:{
                normal:{
                    label:{
                        show:true,
                        textStyle: {
                           color: "rgb(249, 249, 249)"
                        }
                    }
                },
                emphasis:{label:{show:true}}
            },
            data:$scope.orderMap
        }
    ]
};
					 myChart.setOption(option);
			})
			    	/**地图统计 end*/
			    });
});
</script>