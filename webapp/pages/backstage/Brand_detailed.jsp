<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/style.css"/>       
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
<!--[if IE 7]><link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />[if lte IE 8]><link rel="stylesheet" href="assets/css/ace-ie.min.css" /><![endif]-->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="assets/layer/layer.js" type="text/javascript" ></script>
<script src="assets/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script src="js/lrtk.js" type="text/javascript"></script>
<!-- 引入angularjs -->
<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
<!-- 引入angular tree -->
<script type="text/javascript" src="js/angular-tree-control.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/tree-control.css">
<link rel="stylesheet" type="text/css" href="assets/css/tree-control-attribute.css">
<title>品牌详细</title>
</head>

<body>
<div class="page-content clearfix" ng-app="branddetailApp" ng-controller="brandsdetailCtrl">
 <div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
 <div class="brand_detailed">
  <div class="brand_info clearfix">
   <div class="title_brand">品牌信息</div>
   <form>
    <ul class="Info_style clearfix">
      <li><label class="label_name">品牌名称：</label><span class="name">{{brand.brandName}}</span></li>
      <li><label class="label_name">品牌类型：</label><span class="name">{{brand.nation}}</span></li>
      <li><label class="label_name">所属国家：</label><span class="name">{{brand.brandArea}}</span></li>
      <li><label class="label_name">品牌编号：</label><span class="name">{{brand.brandSeriaNo}}</span></li>
      <li><label class="label_name">品牌商品：</label><span class="name">{{brand.brandArea}}</span></li>
      <li><label class="label_name">添加时间：</label><span class="name">{{brand.inputtime}}</span></li>
      <li><label class="label_name">状态：         </label><span class="name">{{brand.brandStatus}}</span></li>
      <li><label class="label_name">品牌介绍：</label><span class="name">{{brand.brandRemark}}</span></li>
    </ul>
    <div class="brand_logo">
      <img src={{brand.brandPicture}}  width="130px" height="100px"/>
      <p class="name">{{brand.brandName}}</p>
    </div>
   </form>
   </div>
 </div>
 <!--品牌商品-->
 <div class="border clearfix">
       <span class="l_f">
        <a href="picture-add.html"  title="添加商品" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加商品</a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
       </span>
       <span class="r_f" id="ttct"></span>
  </div>
     <!--产品列表-->
      <div class="b_products_list clearfix" id="products_list">
      <div id="scrollsidebar" class="left_Treeview">
        <div class="show_btn" id="rightArrow"><span></span></div>
        <div class="widget-box side_content" >
         <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
         <div class="side_list">
          <div class="widget-header header-color-green2"><h4 class="lighter smaller">产品所属分类</h4></div>
          <div class="widget-body">
           <treecontrol class="tree-classic"
                        tree-model="dataForTheTree"
                        options="treeOptions"
                        on-selection="showSelected(node)"
                        selected-node="node1">
                        {{node.name}} 
           </treecontrol>
          </div>
        </div>
       </div>
       </div> 
     <!--列表展示-->
       <div class="table_menu_list" id="testIframe">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
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
		</thead>
	<tbody>
     <tr ng-repeat="prod in prods">
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
    </tbody>
    </table>
    </div>     
     </div>
</div>
</body>
</html>
<script type="text/javascript">
//图层隐藏限时参数		 
$(function() { 
	$("#products_list").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		stylewidth:'220',
		spacingw:30,//设置隐藏时的距离
	    spacingh:260,//设置显示时间距
	});
});
//初始化宽度、高度  
 $(".widget-box").height($(window).height()-215); 
$(".table_menu_list").width($(window).width()-260);
 $(".table_menu_list").height($(window).height()-215);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".widget-box").height($(window).height()-215);
	 $(".table_menu_list").width($(window).width()-260);
	  $(".table_menu_list").height($(window).height()-215);
	});
/*品牌-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}

/*品牌-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Order_form').on('click', function(){
	var cname = $(this).attr("title");
	var chref = $(this).attr("href");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIfour').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIfour').css("display","inline-block");
	//parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	parent.$('.parentIframe').attr("name",herf).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});
</script>
<!-- angularJs加载块 -->
<script>
/**自定义域*/
var branddetailApp = angular.module("branddetailApp", ['treeControl']);  
branddetailApp.controller('brandsdetailCtrl', function($scope, $http) {
	   var rqstUrl = window.location.href;
	   var brandid = rqstUrl.substring(rqstUrl.indexOf("=")+1, rqstUrl.length);
	   $scope.nowloading = true;
	   $http.get("/brand/queryBrandDetail.do?brandId="+brandid).success(
			    function (response) {
			    	                 $scope.nowloading = false;
			    	                 /**加载品牌数据*/
		    	    	             $scope.brand = response.brand;
		    	    	             /**加载商品数据*/
			    	                 $scope.prods = response.prods;
			    	                 /**加载所属分类数据*/
		    	    	             $scope.dataForTheTree = [response.tttt];
			                        }
			    );
	   /**初始化 angular-tree start*/
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
  	   /**初始化  angular-tree end*/
	   $scope.showSelected = function(node) {
  		 $scope.nowloading = true;
  		 $http.get("/brand/queryBrandDetail.do?brandId="+brandid+"&genusId="+node.genusId).success(
 			    function (response) {
 			    	                 $scope.nowloading = false;
			    	                 $scope.prods = response.prods;
 			                        }
 			    );
	   }
});
</script>