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
<link rel="stylesheet" href="Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />   
<!--[if IE 7]><link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ace-ie.min.css" /><![endif]-->
<script src="js/jquery-1.9.1.min.js"></script>   
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<!-- page specific plugin scripts -->
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script src="assets/layer/layer.js" type="text/javascript" ></script>
<script src="assets/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript" src="Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script> 
<script src="js/lrtk.js" type="text/javascript" ></script>
<!-- 引入angularjs -->
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<title>产品列表</title>
</head>
<body>
<div class=" page-content clearfix" ng-app="prodApp" ng-controller="productsCtrl" >
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
 <div id="products_style">
    <div class="search_style">
     
      <ul class="search_content clearfix">
       <li><label class="l_f">产品名称</label><input id="productName" name="productName" ng-model = "pnm"  type="text"  class="text_add" placeholder="输入品牌名称"  style=" width:250px"/></li>
       <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start"  ng-model = "start" style=" margin-left:10px;"/></li>
       <li style="width:90px;"><button ng-click="queryList()"  class="btn_search" id="queryProdList" ><i class="icon-search"></i>查询</button></li>
      </ul>
    </div>
     <div class="border clearfix">
       <span class="l_f">
        <a href="picture-add.jsp" title="添加商品" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加商品</a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
       </span>
       <span class="r_f" id="ttct"></span>
     </div>
     <!--产品列表展示-->
     <!--<div class="h_products_list clearfix" id="products_list">-->
       <!--<div id="scrollsidebar" class="left_Treeview">
        <div class="show_btn" id="rightArrow"><span></span></div>
        <div class="widget-box side_content" >
         <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
         <div class="side_list"><div class="widget-header header-color-green2"><h4 class="lighter smaller">产品类型列表</h4></div>
         <div class="widget-body">
          <div class="widget-main padding-8"><div id="treeDemo" class="ztree"></div></div>
        </div>
       </div>
      </div>  
     </div>-->
         <!-- <div class="table_menu_list" id="testIframe" > -->
                         每页 <select ng-change="queryList();" ng-model="prodpage" ><option ng-repeat="page in pageNos">{{page}}</option></select>条
        <br/><br/>
       <div id="testIframe" >
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
	        <a title="编辑" ng-click="goedit('编辑','picture-add.jsp',prod.productId,'','510');" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
	       <!-- <a title="编辑" Onclick="member_edit('编辑','picture-add.jsp','4','','510');" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> -->  
	        <a title="删除" href="javascript:;"  ng-click="godel(prod.productId)" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
	       </td>
		  </tr>
	    </tbody>
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
    <!-- 第 {{cp}} 到 {{prodpage}} 条记录，共 {{tpn}} 条
    <button ng-click="gopage('prev')" >prev</button>
    <input type="text"  ng-model = "cp" class="input-text"><button ng-click="gopage()" >go</button>
    <button ng-click="gopage('next')" >next</button>
    
    <ul class="pagination">
	<li><a href="#">&laquo;</a></li>
	<li class="active"><a href="#">1</a></li>
	<li class="disabled"><a href="#">2</a></li>
	<li><a href="#">3</a></li>
	<li><a href="#">4</a></li>
	<li><a href="#">5</a></li>
	<li><a href="#">&raquo;</a></li>
    </ul> -->   
  <!--</div>-->
 </div>
</div>
<!-- ppmessage
<script> window.ppSettings = {app_uuid:'bd9130ea-b6ab-11e6-972a-02287b8c0ebf'};(function(){var w=window,d=document;function l(){var a=d.createElement('script');a.type='text/javascript';a.async=!0;a.src='https://ppmessage.com/ppcom/assets/pp-library.min.js';var b=d.getElementsByTagName('script')[0];b.parentNode.insertBefore(a,b)}l();})();</script>
-->
</body>
</html>
<script language='javascript'>
 laydate({
    elem: '#start',
    event: 'focus' 
});
$(function() { 
	$("#products_style").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		spacingw:30,//设置隐藏时的距离
	    spacingh:260,//设置显示时间距
	});
});
</script>
<script type="text/javascript">
//初始化宽度、高度  
 $(".widget-box").height($(window).height()-215); 
$(".table_menu_list").width($(window).width()-260);
 $(".table_menu_list").height($(window).height()-215);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	$(".widget-box").height($(window).height()-215);
	 $(".table_menu_list").width($(window).width()-260);
	  $(".table_menu_list").height($(window).height()-215);
	})
/*产品-停用*/
function member_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*产品-启用*/
function member_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="icon-ok bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!',{icon: 6,time:1000});
	});
}
/*产品-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

/*产品-删除*/
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
    parent.$('#parentIframe').html(cname);
    parent.$('#iframe').attr("src",chref).ready();;
	parent.$('#parentIframe').css("display","inline-block");
	parent.$('.Current_page').attr({"name":herf,"href":"javascript:void(0)"}).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+" class='iframeurl'>" + cnames + "</a>");
    parent.layer.close(index);
	
});
//自定义域
var prodApp = angular.module("prodApp", []);
//使用 provider 创建 service
prodApp.config(function($provide) {
   $provide.provider('ProdQueryListService', function() {
      this.$get = function() {
    	  var factory = {};
    	  factory.prodQueryList = function($scope, $http) {
    		  //sample-table_length
    		  var pnm = $scope.pnm;
     		  var st = document.getElementById("start").value;
     		  $scope.nowloading = true;
     		  $http.get("/product/queryProductList.do?productName="+pnm+"&inputtime="+st).success(
    			    function (response) {
    					$scope.nowloading = false;  		    	    	
  		    	        //数据源
  		    	    	$scope.data = response.jsondata;
  		    	        document.getElementById("ttct").innerHTML = "共：<b>"+response.record+"</b>件商品";
  		    	    	//分页总数
  		    	    	$scope.pageSize = $scope.prodpage;
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
  		    		}
    		   );
    	  }
    	  return factory;
      };
   });
});

//在 service 中注入 factory "ProdQueryListService"
prodApp.service('ProdService', function(ProdQueryListService){
   this.queryList = function($scope, $http) {
      return ProdQueryListService.prodQueryList($scope, $http);
   }
});

prodApp.controller('productsCtrl', function($scope, ProdService, $http) {
	//分页条数加载
	$scope.pageNos = ["5", "20", "50"];
	//初始化一页包含多少条数据
	$scope.prodpage = "5";
	//初始化查询
	ProdService.queryList($scope, $http);
	//点击按钮查询
    $scope.queryList = function() {
    	//$scope.prodpage = "5";
    	ProdService.queryList($scope, $http);
    }
    $scope.goedit = function(title,url,id,w,h) {
    	url = url +"?id="+id;
    	layer_show(title,url,w,h);
    }
    $scope.godel = function(id) {
    	layer.confirm('确认要删除吗？',function(index){
        	$scope.nowloading = true;
        	$http.get("/product/delProduct.do?productId="+id).success(
    			    function (response) {
			    		$scope.nowloading = false;
			    		if(response!=null&&response!=undefined){
        		    		layer.msg('已删除!',{icon:1,time:1000});
        		    		ProdService.queryList($scope, $http);
    			    	}else{
    			    		layer.msg('删除失败!',{icon:1,time:1000});
    			    	}
    			    }
    	      );
    	});
    	
		  
    }
	//页数据量改变触发
   //$scope.selectChange = function () { 
   // 	ProdService.queryList($scope, $http);
   // }
});
</script>
