<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/style.css"/>
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link href="assets/css/codemirror.css" rel="stylesheet">
<link rel="stylesheet" href="font/css/font-awesome.min.css" />
<!--[if lte IE 8]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
<![endif]-->
<!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<!--[if lte IE 8]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
<![endif]-->
<script src="assets/js/ace-extra.min.js"></script>
<!--[if lt IE 9]>
<script src="assets/js/html5shiv.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->
		<!--[if !IE]> -->
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>       
<!-- <![endif]-->
<script src="assets/dist/echarts.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<!-- 引入angularjs -->
<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
<title>交易</title>
</head>

<body>
<div class=" page-content clearfix"  ng-app="transApp" ng-controller="transCtrl">
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
 <div class="transaction_style">
   <ul class="state-overview clearfix">
    <li class="Info">
     <span class="symbol red"><i class="fa fa-jpy"></i></span>
     <span class="value"><h4>交易金额</h4><p class="Quantity color_red">{{jyje}}</p></span>
    </li>
     <li class="Info">
     <span class="symbol  blue"><i class="fa fa-shopping-cart"></i></span>
     <span class="value"><h4>订单数量</h4><p class="Quantity color_red">{{ddsl}}</p></span>
    </li>
     <li class="Info">
     <span class="symbol terques"><i class="fa fa-shopping-cart"></i></span>
     <span class="value"><h4>交易成功</h4><p class="Quantity color_red">{{jyje}}</p></span>
    </li>
     <li class="Info">
     <span class="symbol yellow"><i class="fa fa-shopping-cart"></i></span>
     <span class="value"><h4>交易失败</h4><p class="Quantity color_red">{{jysb}}</p></span>
    </li>
     <li class="Info">
     <span class="symbol darkblue"><i class="fa fa-jpy"></i></span>
     <span class="value"><h4>退款金额</h4><p class="Quantity color_red">{{tkje}}</p></span>
    </li>
   </ul>
 
 </div>
 <div class="t_Record">
               <div id="main" style="height:400px; overflow:hidden; width:100%; overflow:auto" ></div>     
              </div> 
</div>
</body>
</html>
<script type="text/javascript">
     $(document).ready(function(){
		 
		  $(".t_Record").width($(window).width()-60);
		  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
		 $(".t_Record").width($(window).width()-60);
		});
 });
</script> 
<!-- angularJs加载块 -->
<script>
/**自定义域*/
var transApp = angular.module("transApp", []); 
transApp.controller('transCtrl', function($scope, $http) {
	   $scope.nowloading = true;
	   $http.get("/order/queryOrderDetail.do").success(
			    function (response) {
			    	                 $scope.nowloading = false;
			    	                 /**汇总订单报表*/
			    	                 $scope.jyje = response.jyje;
		    	    	             $scope.ddsl = response.ddsl;
		    	    	             $scope.jycg = response.jycg;
		    	    	             $scope.jysb = response.jysb;
		    	    	             $scope.tkje = response.tkje;
			    	                 /**所有订单数据*/
		    	    	             $scope.ajson = response.ajson;
		    	    	             $scope.amax = response.amax;
		    	    	             $scope.aXAxis = response.aXAxis;
		    	    	             $scope.amin = response.amin;
		    	    	             $scope.amXAxis = response.amXAxis;
		    	    	             /**待付款订单*/
		    	    	             $scope.upjson = response.upjson;
		    	    	             $scope.upmax = response.upmax;
		    	    	             $scope.upXAxis = response.upXAxis;
		    	    	             $scope.upmin = response.upmin;
		    	    	             $scope.upmXAxis = response.upmXAxis;
		    	    	             /**待付款订单*/
		    	    	             $scope.pjson = response.pjson;
		    	    	             $scope.pmax = response.pmax;
		    	    	             $scope.pXAxis = response.pXAxis;
		    	    	             $scope.pmin = response.pmin;
		    	    	             $scope.pmXAxis = response.pmXAxis;
		    	    	             /**待发货订单*/
		    	    	             $scope.usjson = response.usjson;
		    	    	             $scope.usmax = response.usmax;
		    	    	             $scope.usXAxis = response.usXAxis;
		    	    	             $scope.usmin = response.usmin;
		    	    	             $scope.usmXAxis = response.usmXAxis;
			    	                 /***echart start***/
			    	                 require.config({
			    	                     paths: {
			    	                         echarts: './assets/dist'
			    	                     }
			    	                 });
			    	                 require(
			    	                     [
			    	                         'echarts',
			    	          				'echarts/theme/macarons',
			    	                         'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
			    	                         'echarts/chart/bar'
			    	                     ],
			    	                     function (ec,theme) {
			    	                         var myChart = ec.init(document.getElementById('main'),theme);
			    	                        option = {
			    	             title : {
			    	                 text: '月购买订单交易记录',
			    	                 subtext: '实时获取用户订单购买记录'
			    	             },
			    	             tooltip : {
			    	                 trigger: 'axis'
			    	             },
			    	             legend: {
			    	                 data:['所有订单','待付款','已付款','代发货']
			    	             },
			    	             toolbox: {
			    	                 show : true,
			    	                 feature : {
			    	                     mark : {show: true},
			    	                     dataView : {show: true, readOnly: false},
			    	                     magicType : {show: true, type: ['line', 'bar']},
			    	                     restore : {show: true},
			    	                     saveAsImage : {show: true}
			    	                 }
			    	             },
			    	             calculable : true,
			    	             xAxis : [
			    	                 {
			    	                     type : 'category',
			    	                     data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
			    	                 }
			    	             ],
			    	             yAxis : [
			    	                 {
			    	                     type : 'value'
			    	                 }
			    	             ],
			    	             series : [
			    	                 {
			    	                     name:'所有订单',
			    	                     type:'bar',
			    	                     data:$scope.ajson,
			    	                     markPoint : {
			    	                         data : [
			    	                             {name : '年最高', value : $scope.amax, xAxis:$scope.aXAxis, yAxis: $scope.amax, symbolSize:18},
			    	                             {name : '年最低', value : $scope.amin, xAxis:$scope.amXAxis, yAxis:$scope.amin}
			    	                         ]
			    	                     }           
			    	                 },
			    	                 {
			    	                     name:'待付款',
			    	                     type:'bar',
			    	                     data:$scope.upjson,
			    	                     markPoint : {
			    	                         data : [
			    	                             {name : '年最高', value : $scope.upmax, xAxis:$scope.upXAxis, yAxis: $scope.upmax, symbolSize:18},
			    	                             {name : '年最低', value : $scope.upmin, xAxis:$scope.upmXAxis, yAxis:$scope.upmin}
			    	                         ]
			    	                     },
			    	                    
			    	          			
			    	                 }
			    	          		, {
			    	                     name:'已付款',
			    	                     type:'bar',
			    	                     data:$scope.pjson,
			    	                     markPoint : {
			    	                         data : [
			    	                             {name : '年最高', value : $scope.pmax, xAxis:$scope.pXAxis, yAxis:$scope.pmax, symbolSize:18},
			    	                             {name : '年最低', value : $scope.pmin, xAxis:$scope.pmXAxis, yAxis:$scope.pmin}
			    	                         ]
			    	                     },
			    	                    
			    	          		}
			    	          		, {
			    	                     name:'代发货',
			    	                     type:'bar',
			    	                     data:$scope.usjson,
			    	                     markPoint : {
			    	                         data : [
			    	                             {name : '年最高', value : $scope.usmax, xAxis: $scope.usXAxis, yAxis: $scope.usmax, symbolSize:18},
			    	                             {name : '年最低', value : $scope.usmin, xAxis: $scope.usmXAxis, yAxis: $scope.usmin}
			    	                         ]
			    	                     },
			    	                    
			    	          		}
			    	             ]
			    	          };
			    	                             
			    	                         myChart.setOption(option);
			    	                     }
			    	                 );
			    	                 /**echart end**/
			                        }
			    );
	   

});
</script>