<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/style.css"/>       
<link href="assets/css/codemirror.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="font/css/font-awesome.min.css" />
<!--[if lte IE 8]>
<link rel="stylesheet" href="assets/css/ace-ie.min.css" />
<![endif]-->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script>      	
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="assets/layer/layer.js" type="text/javascript" ></script>          
<script src="assets/laydate/laydate.js" type="text/javascript"></script>
<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="js/lrtk.js" type="text/javascript" ></script>
<!-- 引入angularjs -->
<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
<title>订单详细</title>
</head>

<body ng-app="orderDetailApp" ng-controller="orderDetailCtrl" >
<div class="margin clearfix">
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
<div class="Order_Details_style">
<div class="Numbering">订单编号:<b>{{order.orderSeries}}</b></div></div>
 <div class="detailed_style">
 <!--收件人信息-->
    <div class="Receiver_style">
     <div class="title_name">收件人信息</div>
     <div class="Info_style clearfix">
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 收件人姓名： </label>
         <div class="o_content">{{order.orderUser}}</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 收件人电话： </label>
         <div class="o_content">{{order.orderMobile}}</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 收件地邮编： </label>
         <div class="o_content">{{order.orderPostcode}}</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 收件地址： </label>
         <div class="o_content">{{order.orderAddress}}</div>
        </div>
     </div>
    </div>
    <!--订单信息-->
    <div class="product_style">
    <div class="title_name">产品信息</div>
    <div class="Info_style clearfix">
      <div class="product_info clearfix" ng-repeat="details in orderdetail">
      <a href="#" class="img_link"><img src={{details.prodImgPath}} /></a>
      <span>
      <a href="#" class="name_link">{{details.productName}}</a>
      <b>{{details.remark}}</b>
      <p>规格：{{details.weight}}/{{details.unit}}</p>
      <p>数量：{{details.productCount}}</p>
      <p>价格：<b class="price"><i>￥</i>{{details.showPrice}}</b></p>  
      <p>状态：<i class="label label-success radius" ng-if="checkMyVal(details.count)">
                 {{orderStatus}}
             </i>
      </p>   
      </span>
      </div>
    </div>
    </div>
    <!--总价-->
    <div class="Total_style">
     <div class="Info_style clearfix">
      <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 支付方式： </label>
         <div class="o_content">{{order.payType}}</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 支付状态： </label>
         <div class="o_content">{{order.orderStatus}}</div>
        </div>
        <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 订单生成日期： </label>
         <div class="o_content">{{order.inputtime}}</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 快递名称： </label>
         <div class="o_content">{{order.expressName}}</div>
        </div>
         <div class="col-xs-3">  
         <label class="label_name" for="form-field-2"> 发货日期： </label>
         <div class="o_content">{{order.expressStartDate}}</div>
        </div>
        </div>
      <div class="Total_m_style"><span class="Total">总数：<b>{{order.orderCount}}</b></span><span class="Total_price">总价：<b>{{order.orderAmount}}</b>元</span></div>
    </div>
    
    <!--物流信息-->
    <div class="Logistics_style clearfix">
     <div class="title_name">物流信息</div>
      <!--<div class="prompt"><img src="images/meiyou.png" /><p>暂无物流信息！</p></div>-->
       <div data-mohe-type="kuaidi_new" class="g-mohe " id="mohe-kuaidi_new">
        <div id="mohe-kuaidi_new_nucom">
            <div class="mohe-wrap mh-wrap">
                <div class="mh-cont mh-list-wrap mh-unfold">
                    <div class="mh-list">
                        <ul>
                            <li  ng-repeat="exp in exps">
                                <p>{{exp.inputtime}}</p>
                                <p>{{exp.expressInfo}}</p>
                                <span class="before"></span><span class="after"></span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<div class="Button_operation">
				<button onclick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="icon-save "></i>返回上一步</button>
				
				<button onclick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
            
            
 </div>
</div>
</body>
<script>
/**自定义域*/
var orderDetailApp = angular.module("orderDetailApp", []); 
orderDetailApp.controller('orderDetailCtrl', function($scope,$http) {
	var orderId = window.location.href.substring(window.location.href.indexOf("=")+1, window.location.href.length);
	$scope.nowloading = true;
	$http.get("/order/queryOrderDetailInfo.do?orderId="+orderId).success(
		    function (response) {
		    	                 $scope.nowloading = false;
		    	                 $scope.order = response.order;
		    	                 $scope.orderdetail = response.orderdetail;
		    	                 $scope.exps = response.exps;
		                        }
		    );
	$scope.checkMyVal = function(count){
		if(count>0&&count<5){
			$scope.orderStatus = "库存紧张";
		}else if(count>5){
			$scope.orderStatus = "有货";
		}else{
			$scope.orderStatus = "缺货";
		}
		return true;
	}
});
</script>
</html>
