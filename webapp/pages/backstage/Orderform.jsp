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
<script type="text/javascript" src="js/H-ui.js"></script>     
<script src="assets/js/typeahead-bs2.min.js"></script>           	
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="assets/layer/layer.js" type="text/javascript" ></script>          
<script src="assets/laydate/laydate.js" type="text/javascript"></script>
<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="js/lrtk.js" type="text/javascript" ></script>
<!-- 引入angularjs -->
<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
<!-- 引入angular tree -->
<script type="text/javascript" src="js/angular-tree-control.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/tree-control.css">
<link rel="stylesheet" type="text/css" href="assets/css/tree-control-attribute.css">
</head>
<title>订单管理</title>
</head>

<body ng-app="orderFormApp" ng-controller="orderFormCtrl">
<div class="margin clearfix" >
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
 <div class="cover_style" id="cover_style">
    <div class="top_style Order_form" id="Order_form_style">
      <div class="type_title">购物产品比例
      <div class="btn_style">  
      <a href="javascript:ovid()"  class="xianshi_btn Statistic_btn">显示</a> 
      <a href="javascript:ovid()"  class="yingchan_btn Statistic_btn b_n_btn">隐藏</a>
      </div>
      </div>
        <div class="hide_style clearfix">
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="20" data-color="#D15B47"><span class="percent">{{sp}}</span>%</div><span class="name">食品类</span></div>									
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="55" data-color="#87CEEB"><span class="percent">{{fz}}</span>%</div><span class="name">服装类</span></div>									
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="90" data-color="#87B87F"><span class="percent">{{smpj}}</span>%</div><span class="name">数码配件</span></div>
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="30" data-color="#d63116"><span class="percent">{{sj}}</span>%</div><span class="name">手机</span></div>
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="60" data-color="#ff6600"><span class="percent">{{dn}}</span>%</div><span class="name">电脑</span></div>
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="40" data-color="#2ab023"><span class="percent">{{dzcp}}</span>%</div><span class="name">电子产品</span></div>
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="46" data-color="#1e3ae6"><span class="percent">{{cfyp}}</span>%</div><span class="name">厨房用品</span></div>
       <div class="proportion"> <div class="easy-pie-chart percentage" data-percent="65" data-color="#c316a9"><span class="percent">{{jydq}}</span>%</div><span class="name">家用电器</span></div>
    </div>
    </div>
    <!--内容-->
   <div class="centent_style" id="centent_style">
     <div id="covar_list" class="order_list">
       <div id="scrollsidebar" class="left_Treeview">
        <div class="show_btn" id="rightArrow"><span></span></div>
        <div class="widget-box side_content" >
         <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
         <div class="side_list"><div class="widget-header header-color-green2"><h4 class="lighter smaller">订单类型分类</h4></div>
         <div class="widget-body">         
         <treecontrol class="tree-classic"
                        tree-model="dataForTheTree"
                        options="treeOptions"
                        on-selection="showSelected(node)"
                        selected-node="node1">
                        {{node.name}}({{node.ocount}}) 
           </treecontrol>
       </div>
      </div>  
     </div>
     </div>
     <!--左侧样式-->
     <div class="list_right_style">
     <div class="search_style">
     
      <ul class="search_content clearfix">
       <li><label class="l_f">订单编号</label><input name="" type="text" class="text_add" ng-model ="orderSeries" placeholder="订单订单编号" style=" width:250px"></li>
       <li><label class="l_f">时间</label><input class="inline laydate-icon" id="start" style=" margin-left:10px;"></li>
       <li style="width:90px;"><button type="button" ng-click="queryList()" class="btn_search"><i class="fa fa-search"></i>查询</button></li>
      </ul>
    </div>
    <span class="r_f" id="ttct">12321312321</span>
      <!--订单列表展示-->
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
				<th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
				<th width="120px">订单编号</th>
				<!-- <th width="250px">产品名称</th> -->
				<th width="100px">总价</th>
				<th width="100px">优惠</th>
                <th width="100px">订单时间</th>				
				<th width="180px">所属类型</th>
                <th width="80px">数量</th>
				<th width="70px">状态</th>                
				<th width="200px">操作</th>
			</tr>
		</thead>
	<tbody>
     <tr ng-repeat="o in ordersJson">
     <input type="text" id="orderId" name="orderId" ng-model="o.orderId" style="display:none"/>
     <input type="text" id="prodId" name="prodId" ng-model="o.prodId" style="display:none"/>
     <input type="text" id="genusId" name="genusId" ng-model="o.genusId" style="display:none"/>
     <td><label><input type="checkbox" class="ace"><span class="lbl"></span></label></td>
     <td>{{o.orderSeries}}</td>
     <!-- <td><img src={{o.imgId}}/></td> -->
     <td>{{o.orderAmount}}</td>
     <td>{{o.orderDiscount}}</td>
     <td>{{o.inputtime}}</td>
     <td>{{o.orderGenus}}</td>
     <td>{{o.orderCount}}</td>
      <td class="td-status"><span class="label label-success radius">{{o.orderStatus}}</span></td>
     <td>
     <a ng-click="Delivery_stop(this,o.orderId)"  href="javascript:;" title="发货"  class="btn btn-xs btn-success"><i class="fa fa-cubes bigger-120"></i></a> 
     <a title="订单详细" ng-click="showDetail(o.orderId);" class="btn btn-xs btn-info order_detailed" ><i class="fa fa-list bigger-120"></i></a> 
     <a title="删除" href="javascript:;"  onclick="Order_form_del(this,'1')" class="btn btn-xs btn-warning" ><i class="fa fa-trash  bigger-120"></i></a>    
     </td>
     </tr>
     </tbody>
     </table>
     <nav>
          <ul class="pagination">
            &nbsp; 每页 <select ng-change="queryList();" ng-model="everypage" ><option ng-repeat="page in pageNos">{{page}}</option></select>
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
   </div>
 </div>
</div>
</div>
 <!--发货-->
 <div id="Delivery_stop" style=" display:none">
  <div class="">
    <div class="content_style">
  <div class="form-group"><label class="col-sm-2 control-label no-padding-right" for="form-field-1">快递公司 </label>
       <div class="col-sm-9"><select class="form-control" id="form-field-select-1" ng-model = "expressName">
																<option value="">--选择快递--</option>
																<option value="天天快递">天天快递</option>
																<option value="圆通快递">圆通快递</option>
																<option value="天天快递">中通快递</option>
																<option value="天天快递">顺丰快递</option>
																<option value="申通快递">申通快递</option>
																<option value="邮政EMS">邮政EMS</option>
																<option value="邮政EMS">邮政小包</option>
																<option value="韵达快递">韵达快递</option>
															</select></div>
	</div>
   <div class="form-group"><label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 快递号 </label>
    <div class="col-sm-9"><input type="text" id="form-field-1" placeholder="快递号" ng-model = "expressNo" class="col-xs-10 col-sm-5" style="margin-left:0px;"></div>
	</div>
    <div class="form-group"><label class="col-sm-2 control-label no-padding-right" for="form-field-1">货到付款 </label>
     <div class="col-sm-9"><label><input name="checkbox" type="checkbox" class="ace" ng-model = "cod" id="checkbox"><span class="lbl"></span></label></div>
	</div>
 </div>
  </div>
 </div>
</body>
</html>
<script>
 $(function() { 
	$("#cover_style").fix({
		float : 'top',
		minStatue : false,
		skin : 'green',	
		durationTime :false,
		window_height:30,//设置浏览器与div的高度值差
		spacingw:0,//
		spacingh:0,//
		close_btn:'.yingchan_btn',
		show_btn:'.xianshi_btn',
		side_list:'.hide_style',
		widgetbox:'.top_style',
		close_btn_width:60,	
		da_height:'#centent_style,.left_Treeview,.list_right_style',	
		side_title:'.b_n_btn',		
		content:null,
		left_css:'.left_Treeview,.list_right_style'
		
		
	});
});
//左侧显示隐藏
$(function() { 
	$("#covar_list").fix({
		float : 'left',
		minStatue : false,
		skin:false,	
		//durationTime :false,
		spacingw:50,//设置隐藏时的距离
	    spacingh:270,//设置显示时间距
		stylewidth:'220',
		close_btn:'.close_btn',
		show_btn:'.show_btn',
		side_list:'.side_list',
		content:'.side_content',
		widgetbox:'.widget-box',
		da_height:null,
		table_menu:'.list_right_style'
	});
});
//时间选择
 laydate({
    elem: '#start',
    event: 'focus' 
});
/*订单-删除*/
function Order_form_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Order_form,.order_detailed').on('click', function(){
	var cname = $(this).attr("title");
	var chref = $(this).attr("href");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIframe').css("display","inline-block");
	parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});

//初始化宽度、高度  
  var heights=$(".top_style").outerHeight()+47; 
 $(".centent_style").height($(window).height()-heights); 
 $(".page_right_style").width($(window).width()-220);
 $(".left_Treeview,.list_right_style").height($(window).height()-heights-2); 
 $(".list_right_style").width($(window).width()-250);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".centent_style").height($(window).height()-heights); 
	 $(".page_right_style").width($(window).width()-220);
	 $(".left_Treeview,.list_right_style").height($(window).height()-heights-2);  
	 $(".list_right_style").width($(window).width()-250);
	}) 
//比例
var oldie = /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase());
			$('.easy-pie-chart.percentage').each(function(){
				$(this).easyPieChart({
					barColor: $(this).data('color'),
					trackColor: '#EEEEEE',
					scaleColor: false,
					lineCap: 'butt',
					lineWidth: 10,
					animate: oldie ? false : 1000,
					size:103
				}).css('color', $(this).data('color'));
			});
		
			$('[data-rel=tooltip]').tooltip();
			$('[data-rel=popover]').popover({html:true});
</script>
<!-- angularJs加载块 -->
<script>
/**自定义域*/
var orderFormApp = angular.module("orderFormApp", ['treeControl']); 
//使用 provider 创建 service
orderFormApp.config(function($provide) {
   $provide.provider('orderQueryListService', function() {
      this.$get = function() {
    	  var factory = {};
    	  factory.orderQueryList = function($scope, $http) {
    		  var orderSeries = "";
    		  if($scope.orderSeries!=undefined){
    			  orderSeries = $scope.orderSeries;
    		  }
     		  var st = "";
     		  if(document.getElementById("start").value!=undefined){
     			  st = document.getElementById("start").value;
     		  }
     		  var orderGenus = "";
     		  if($scope.orderGenus!=undefined){
     			 orderGenus = $scope.orderGenus;
     		  }
     		  $scope.nowloading = true;
     		  $http.get("/order/queryOrderForm.do?orderSeries="+orderSeries+"&inputtime="+st+"&orderGenus="+orderGenus).success(
    			    function (response) {
    					$scope.nowloading = false;  		    	    	
  		    	        //数据源
  		    	        /**汇总订单报表*/
                        $scope.sp = response.sp;
	                    $scope.fz = response.fz;
	                    $scope.smpj = response.smpj;
	                    $scope.sj = response.sj;
	                    $scope.dn = response.dn;
	                    $scope.dzcp = response.dzcp;
	                    $scope.cfyp = response.cfyp;
	                    $scope.jydq = response.jydq;
  		    	    	$scope.data = response.ordersJson;
  		    	    	if($scope.data!=undefined){
  		    	    		document.getElementById("ttct").innerHTML = "共：<b>"+response.record+"</b>件订单";
  	  		    	    	//分页总数
  	  		    	    	$scope.pageSize = $scope.everypage;
  	  		    	    	$scope.pages = Math.ceil($scope.data.length / $scope.pageSize); //分页数
  	  		    	    	$scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
  	  		    	    	$scope.pageList = [];
  	  		    	    	$scope.selPage = 1;
  	  		    	    	//设置表格数据源(分页)
  	  		    	    	$scope.setData = function () {
  	  		    	    	       $scope.ordersJson = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
  	  		    	    	}
  	  		    	    	$scope.ordersJson = $scope.data.slice(0, $scope.pageSize);
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
  		    	    	}else{
  		    	    		document.getElementById("ttct").innerHTML = "共：<b>0</b>件品牌";
  	  		    	    	$scope.ordersJson = $scope.data;
  		    	    	}
  		    		}
    		   );
    	  }
    	  return factory;
      };
   });
});
//在 service 中注入 factory "OrderQueryListService"
orderFormApp.service('orderService', function(orderQueryListService){
   this.queryList = function($scope, $http) {
      return orderQueryListService.orderQueryList($scope, $http);
   }
});

orderFormApp.controller('orderFormCtrl', function($scope,orderService,$http) {
	$scope.nowloading = true;
	$scope.everypage = "5";
	$scope.pageNos = ["5", "20", "50"];
	orderService.queryList($scope, $http);
	//点击按钮查询
    $scope.queryList = function() {
    	orderService.queryList($scope, $http);
    }
    /**初始化 angular-tree*/
	   $scope.nowloading = true;
	   $http.get("/product/queryGenusTree.do").success(
			    function (response) {
			    	                 $scope.nowloading = false;
			    	                 /**加载所属分类数据*/
		    	    	             $scope.dataForTheTree = [response.tttt];
			                        }
			    );
	   $scope.treeOptions = {
			    nodeChildren: "children",
			    dirSelectable: true,
			    injectClasses: {
			        ul: "a1",
			        li: "a2",
			        liSelected: "a7",
			        iExpanded: "a3",
			        iCollapsed: "a4",
			        iLeaf: "a5",
			        label: "a6",
			        labelSelected: "a8"
			    }
		}
	   /**点选树状图，赋值genusid*/
	   $scope.showSelected = function(node) {
			//document.getElementById("genusId").value = node.genusId;
	  		 $scope.nowloading = true;
	  		 $scope.orderGenus = node.name;
	  		 orderService.queryList($scope, $http);
	   }
	   /**发货处理*/
	   $scope.Delivery_stop = function (obj,id){
	   	layer.open({
	           type: 1,
	           title: '发货',
	   		maxmin: true, 
	   		shadeClose:false,
	           area : ['500px' , ''],
	           content:$('#Delivery_stop'),
	   		btn:['确定','取消'],
	   		yes: function(index, layero){		
	   			if($('#form-field-1').val()==""){
		   			layer.alert('快递号不能为空！',{
		                  title: '提示框',				
		   			  icon:0,		
		   			  }) 
		   			
		   		}else{	
	    	            $scope.nowloading = true;
	    	            //alert("/order/updateOrder.do?orderId="+id+"&expressName="+$scope.expressName+"&expressNo="+$scope.expressNo+"&cod="+$scope.cod);
		   				$http.get("/order/updateOrder.do?orderId="+id+"&expressName="+$scope.expressName+"&expressNo="+$scope.expressNo+"&cod="+$scope.cod).success(
		   					    function (response) {
		   					    	                 $scope.nowloading = false;
		   					    	                 alert(response);
		   					    	                 if(response=="1"||"1"==response){
		   					    	                	layer.confirm('提交成功！',function(index){
		   					  		   		                $(obj).parents("tr").find(".td-manage").prepend('<a style=" display:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="已发货"><i class="fa fa-cubes bigger-120"></i></a>');
		   					  		   		                $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发货</span>');
		   					  		   		                $(obj).remove();
		   					  		   		                layer.msg('已发货!',{icon: 6,time:1000});
		   					  		   			        });  
		   					  		   			        layer.close(index);
		   					    	                 }else{
		   					    	                	layer.confirm('提交失败！',function(index){
		   					    	                		layer.close(index);
		   					  		   			        });  
		   					    	                 }
		   					    	                  
		   					                        }
		   					    );	
		   	    }	   		
	   		}
	   	})
	   };
	   /**点击查看订单信息*/
	   $scope.showDetail = function(orderId) {
	    	window.location.href = "order_detailed.jsp?orderId="+orderId;
	   }
});
</script>