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
<script src="assets/dist/echarts.js"></script>
<script src="js/lrtk.js" type="text/javascript"></script>
<!-- 引入angularjs -->
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<title>品牌管理</title>
</head>
<body>
<div class="page-content clearfix" ng-app="brandApp" ng-controller="brandsCtrl" >
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
  <div id="brand_style">
    <div class="search_style">
     
      <ul class="search_content clearfix">
       <li><label class="l_f">品牌名称</label><input name="" type="text"  class="text_add" ng-model = "bnm"  placeholder="输入品牌名称"  style=" width:250px"/></li>
       <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start" style=" margin-left:10px;"></li>
       <!-- <li><select name="" class="text_add"><option  value="1">国内品牌</option><option value="2">国外品牌</option></select></li> -->
       <li style="width:90px;"><button type="button" ng-click="queryList()" class="btn_search"><i class="icon-search"></i>查询</button></li>
      </ul>
    </div>
     <div class="border clearfix">
       <span class="l_f">
        <a href="Add_Brand.jsp"  title="添加品牌" class="btn btn-warning Order_form"><i class="icon-plus"></i>添加品牌</a>
        <a href="javascript:ovid()" class="btn btn-danger"><i class="icon-trash"></i>批量删除</a>
        <a href="javascript:ovid()" class="btn btn-info" ng-click="queryList('1');">国内品牌</a>
        <a href="javascript:ovid()" class="btn btn-success" ng-click="queryList('2');">国外品牌</a>
       </span>
       <span class="r_f" id="ttct"></span>
     </div>
    <!--品牌展示-->
     <div class="brand_list clearfix" id="category">
     <div class="show_btn" id="rightArrow"><span></span></div>
     <div class="chart_style side_content">
     <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
         <div id="main" style="height:300px;" class="side_list"></div>
     </div>
     <!--品牌列表-->
      <div class="table_menu_list">
       <table class="table table-striped table-bordered table-hover" id="sample-table">
		<thead>
		 <tr>
				<th width="25px"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
				<th width="50px">ID</th>
				<th width="50px">排序</th>
				<th width="120px">品牌LOGO</th>
				<th width="120px">品牌名称</th>
				<th width="120px">所属地区/国家</th>
				<th width="120px">描述</th>
				<th width="100px">加入时间</th>
				<th width="50px">状态</th>                
				<th width="100px">操作</th>
			</tr>
		</thead>
	<tbody>
		<tr ng-repeat="b in yybsbrands">
          <td><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
          <td>{{b.brandId}}</td>
          <td>{{b.brandSeriaNo}}</td>
          <td><img src={{b.brandPicture}}  width="130"/></td>
          <td><a href="javascript:ovid()" name="Brand_detailed.html" style="cursor:pointer" class="text-primary brond_name" ng-click="showbranddetail(b.brandId);">{{b.brandName}}</a></td>
          <td>{{b.brandArea}}</td>
          <td class="text-l">{{b.brandRemark}}</td>
          <td>{{b.inputtime}}</td>
          <td class="td-status"><span class="label label-success radius">{{b.brandStatus}}</span></td>
          <td>
          <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
          <a title="编辑" ng-click="goedit(b.brandId);" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
          <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
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
</body>
</html>
<!-- others -->
<script>
//初始化宽度、高度  
 $(".chart_style").height($(window).height()-215);
  $(".table_menu_list").height($(window).height()-215);
  $(".table_menu_list ").width($(window).width()-440);
  //当文档窗口发生改变时 触发  
    $(window).resize(function(){
	 $(".chart_style").height($(window).height()-215);
	 $(".table_menu_list").height($(window).height()-215);
	 $(".table_menu_list").width($(window).width()-440);
	});	
	//图层隐藏限时参数		 
$(function() { 
	$("#category").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		stylewidth:'400',
		spacingw:30,//设置隐藏时的距离
	    spacingh:440,//设置显示时间距
	});
});
//面包屑返回值
var index = parent.layer.getFrameIndex(window.name);
parent.layer.iframeAuto(index);
$('.Order_form ,.brond_name').on('click', function(){
	var cname = $(this).attr("title");
	var cnames = parent.$('.Current_page').html();
	var herf = parent.$("#iframe").attr("src");
    parent.$('#parentIframe span').html(cname);
	parent.$('#parentIframe').css("display","inline-block");
    parent.$('.Current_page').attr("name",herf).css({"color":"#4c8fbd","cursor":"pointer"});
	//parent.$('.Current_page').html("<a href='javascript:void(0)' name="+herf+">" + cnames + "</a>");
    parent.layer.close(index);
	
});
/*品牌-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
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
/*品牌-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

/*品牌-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
laydate({
    elem: '#start',
    event: 'focus' 
});


</script>
<!-- angularJs加载块 -->
<script>
//自定义域
var brandApp = angular.module("brandApp", []);
//使用 provider 创建 service
brandApp.config(function($provide) {
   $provide.provider('brandQueryListService', function() {
      this.$get = function() {
    	  var factory = {};
    	  factory.brandQueryList = function($scope, $http) {
    		  var bnm = "";
    		  if($scope.bnm!=undefined){
    			  bnm = $scope.bnm;
    		  }
     		  var st = "";
     		  if(document.getElementById("start").value!=undefined){
     			  st = document.getElementById("start").value;
     		  }
     		  var nt = "";
     		 if($scope.nation!=undefined){
    			  nt = $scope.nation;
    		  }
     		  $scope.nowloading = true;
     		  $http.get("/brand/queryBrandList.do?brandName="+bnm+"&inputtime="+st+"&nation="+nt).success(
    			    function (response) {
    					$scope.nowloading = false;  		    	    	
  		    	        //数据源
  		    	    	$scope.data = response.jsondata;
  		    	    	$scope.ttcn = response.ttcn;
  		    	    	$scope.tten = response.tten;
  		    	    	if($scope.data!=undefined){
  		    	    		document.getElementById("ttct").innerHTML = "共：<b>"+response.record+"</b>件品牌";
  	  		    	    	//分页总数
  	  		    	    	$scope.pageSize = $scope.everypage;
  	  		    	    	$scope.pages = Math.ceil($scope.data.length / $scope.pageSize); //分页数
  	  		    	    	$scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
  	  		    	    	$scope.pageList = [];
  	  		    	    	$scope.selPage = 1;
  	  		    	    	//设置表格数据源(分页)
  	  		    	    	$scope.setData = function () {
  	  		    	    	       $scope.yybsbrands = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
  	  		    	    	}
  	  		    	    	$scope.yybsbrands = $scope.data.slice(0, $scope.pageSize);
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
  	  		    	        /**饼状图加载块--------------------------s*/
  	  		    	        var cn = $scope.ttcn;
  	  		    	        var en = $scope.tten;
  	  		    	            require.config({paths: {echarts: './assets/dist'}});
  	  		    	                                 /** 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表*/   
  	  		    	            require(['echarts',           'echarts/chart/pie'                    ,'echarts/chart/funnel'],
  	  		    	                    function (ec) {
  	  		    	                	               var myChart = ec.init(document.getElementById('main'));			
  	  		    	        		                   option = {title : {text: '国内国外品牌比例',subtext: '',x:'center'},
  	  		    	                                             tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
  	  		    	                                             legend: {orient : 'vertical',x : 'left',data:['国内品牌','国外品牌']},
  	  		    	                                             toolbox: {show : false,feature : {mark : {show: false},
  	  		    	                        	                                                   dataView : {show: false, readOnly: false},
  	  		    	                        	                                                   magicType : {show: true,
  	  		    	                        	                                	                            type: ['pie', 'funnel'],
  	  		    	                        	                                	                            option: {funnel: {x: '25%',
  	  		    	                        	                                	        	    	                          width: '50%',
  	  		    	                        	                                	        	    	                          funnelAlign: 'left',
  	  		    	                        	                                	        	    	                          max: 545
  	  		    	                        	                                	        	    	                          }
  	  		    	                        	                                                                         }
  	  		    	                        	                                                                },
  	  		    	                                                                               restore : {show: true},
  	  		    	                                                                               saveAsImage : {show: true}
  	  		    	                                                                               }
  	  		    	                                                       },
  	  		    	                                             calculable : true,
  	  		    	                                             series : [{name:'品牌数量',type:'pie',radius : '55%',center: ['50%', '60%'],data:[{value:cn, name:'国内品牌'},{value:en, name:'国外品牌'},]}]  
  	  		    	        		                         	 };
  	  		    	                                   myChart.setOption(option);
  	  		    	        		                   }
  	  		    	                    );
  	  		    	          /**饼状图加载块--------------------------e*/
  		    	    	}else{
  		    	    		document.getElementById("ttct").innerHTML = "共：<b>0</b>件品牌";
  	  		    	    	$scope.yybsbrands = $scope.data;
  		    	    	}
  		    		}
    		   );
    	  }
    	  return factory;
      };
   });
});


//在 service 中注入 factory "BrandQueryListService"
brandApp.service('brandService', function(brandQueryListService){
   this.queryList = function($scope, $http) {
      return brandQueryListService.brandQueryList($scope, $http);
   }
});


brandApp.controller('brandsCtrl', function($scope, brandService, $http) {
	brandService.queryList($scope, $http);
	$scope.everypage = "5";
	$scope.pageNos = ["5", "20", "50"];
	//点击按钮查询
    $scope.queryList = function(nation) {
		if(nation!=undefined){
			$scope.nation = nation;
		}else{
			$scope.nation = "";
		}
    	brandService.queryList($scope, $http);
    }
	/**展示品牌详细信息*/
    $scope.showbranddetail = function(brandid) {
    	//alert(brandid);
    	window.location.href = "Brand_detailed.jsp?brandid="+brandid;
    }
	/**弹出编辑页面*/
	$scope.goedit = function(brandid) {
    	//alert(brandid);
    	window.location.href = "Add_Brand.jsp?brandid="+brandid;
    }
});
</script>