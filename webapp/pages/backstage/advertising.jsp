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
        <link href="assets/css/codemirror.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/ace.min.css" />
        <link rel="stylesheet" href="font/css/font-awesome.min.css" />
        <!--[if lte IE 8]>
			<link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->
		<script src="js/jquery-1.9.1.min.js"></script>
		<script src="assets/js/typeahead-bs2.min.js"></script>   
        <script src="js/lrtk.js" type="text/javascript" ></script>		
		<script src="assets/js/jquery.dataTables.min.js"></script>
		<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
        <script src="assets/layer/layer.js" type="text/javascript" ></script>          
        <script type="text/javascript" src="Widget/swfupload/swfupload.js"></script>
        <script type="text/javascript" src="Widget/swfupload/swfupload.queue.js"></script>
        <script type="text/javascript" src="Widget/swfupload/swfupload.speed.js"></script>
        <script type="text/javascript" src="Widget/swfupload/handlers.js"></script>
        <!-- 引入angularjs -->
		<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
		<!-- 引入angular tree -->
		<script type="text/javascript" src="js/angular-tree-control.js"></script>
		<title>广告管理</title>
	</head>
	<body>
		<div ng-app="myApp" ng-controller="myCtrl">
<!--			<div id="loading" class="loading" ng-show="nowloading" >Loading</div> -->
			<div id="scrollsidebar" class="left_Treeview">
			    <div class="show_btn" id="rightArrow"><span></span></div>
			    <div class="widget-box side_content" >
				    <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
				    <div class="side_list">
					    <div class="widget-header header-color-green2">
					          <h4 class="lighter smaller">广告图分类</h4>
					    </div>
						<!-- 左侧导航栏 -->
					    <div class="nav-list" >
					        <li><a class="orange  fa fa-reorder" ng-click="clickNav()">&nbsp;&nbsp;全部</a></li>
							<li ng-repeat="nav in navList">
					        	<a class="fa fa-sticky-note pink " ng-click="clickNav(nav.genusId)" ng-class="focusNav(nav.genusId)">&nbsp;&nbsp;{{nav.name}}</a>
                            </li>
					    </div>
					</div>
				</div>  
			</div>
			
		    <div class="Ads_list">
		    	 <!-- 表单输入过滤 -->
			    <div class="search_style">
			      <ul class="search_content clearfix">
			       <li><label class="l_f">产品名称</label>  <!-- 表单输入过滤 -->
		        		<input type="text" placeholder="输入关键字搜索" ng-model="selt"/>
		           </li>
			       <li><label class="l_f">添加时间</label><input class="inline laydate-icon" id="start"  ng-model = "start" style=" margin-left:10px;"/></li>
			       <li style="width:90px;"><button ng-click="queryList()"  class="btn_search" id="queryProdList" ><i class="icon-search"></i>查询</button></li>
			      </ul>
			    </div>
				<div class="border clearfix">
				    <span class="l_f">
				    	<a href="avder-add.jsp" title="添加广告"  class="btn btn-warning Order_form"><i class="icon-plus"></i>添加广告</a>
						<a href="javascript:ovid()" class="btn btn-danger"><i class="fa fa-trash"></i> 批量删除</a>
				    </span>
				    <span class="r_f">共：<b>45</b>条广告</span>
				</div>
					每页 <select ng-change="queryList();" ng-model="prodpage" ><option ng-repeat="page in pageNos">{{page}}</option></select>条
		        <br/><br/>
		     	<div class="Ads_lists">
			        <table class="table table-striped table-bordered table-hover" id="sample-table">
						<thead>
							 <tr>
								<th width="25"><label><input type="checkbox" class="ace"><span class="lbl"></span></label></th>
								<th width="80">ID</th>
				                <th>排序</th>
								<th width="100">分类</th>
								<th width="220px">图片</th>
								<th width="150px">尺寸（大小）</th>
								<th width="250px">链接地址</th>
								<th width="180px">加入时间</th>
								<th width="70px">状态</th>                
								<th width="250px">操作</th>
							</tr>
						</thead>
						<tbody>
							<!-- 右侧显示表单内容 -->
						    <div class="tab-cont">
						        <!-- 循环出商品详细信息 -->
						        <tr class="tab" ng-repeat="advert in yybsadverts | filter:filterNav | filter:selt">
							        <td width="25px"><label><input type="checkbox" class="ace" ><span class="lbl"></span></label></td>
							        <td width="80px" >{{advert.advertId}}</td>
							        <td width="250px"><u style="cursor:pointer" class="text-primary" onclick="">{{advert.name}}</u></td>
							        <td width="100px">{{advert.name}}</td>
							        <td width="100px">{{advert.describe}}</td> 
							        <td width="100px">{{advert.classify}}</td> 
							        <td width="100px">{{advert.price | currency:"￥RMB:"}}</td>         
							        <td width="180px">2014-6-11 11:11:42</td>
							        <td class="text-l">通过</td>
							        <td class="td-manage">
								        <a onClick="member_stop(this,'10001')"  href="javascript:;" title="停用"  class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></a> 
								        <a title="编辑" ng-click="goedit('编辑','avder-add.jsp',advert.advertId,'','510');" href="javascript:;"  class="btn btn-xs btn-info" ><i class="icon-edit bigger-120"></i></a> 
								        <a title="删除" href="javascript:;"  onclick="member_del(this,'1')" class="btn btn-xs btn-warning" ><i class="icon-trash  bigger-120"></i></a>
							        </td>
							    </tr>
						    </div>
					    </tbody>
			        </table>
		        </div>
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
		</div>
	</body>
</html>
<script>
 //初始化宽度、高度  
 $(".widget-box").height($(window).height()); 
 $(".Ads_list").width($(window).width()-220);
 //当文档窗口发生改变时 触发  
 $(window).resize(function(){
	 $(".widget-box").height($(window).height());
	 $(".Ads_list").width($(window).width()-220);
 });
 $(function() { 
	$("#advertising").fix({
		float : 'left',
		//minStatue : true,
		skin : 'green',	
		durationTime :false,
		stylewidth:'220',
		spacingw:30,//设置隐藏时的距离
	    spacingh:250,//设置显示时间距
		set_scrollsidebar:'.Ads_style',
		table_menu:'.Ads_list'
	});
 });
/*广告图片-停用*/
function member_stop(obj,id){
	layer.confirm('确认要关闭吗？',{icon:0,},function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs " onClick="member_start(this,id)" href="javascript:;" title="显示"><i class="fa fa-close bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已关闭</span>');
		$(obj).remove();
		layer.msg('关闭!',{icon: 5,time:1000});
	});
}
/*广告图片-启用*/
function member_start(obj,id){
	layer.confirm('确认要显示吗？',{icon:0,},function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" class="btn btn-xs btn-success" onClick="member_stop(this,id)" href="javascript:;" title="关闭"><i class="fa fa-check  bigger-120"></i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">显示</span>');
		$(obj).remove();
		layer.msg('显示!',{icon: 6,time:1000});
	});
}
/*广告图片-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',{icon:0,},function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}

</script>

<!-- angularJs加载块 -->

<script>
var app = angular.module('myApp', []);
//使用 provider 创建 service
app.config(function($provide) {
   $provide.provider('ProdQueryListService', function() {
      this.$get = function() {
    	  var factory = {};
    	  factory.prodQueryList = function($scope, $http) {
    		  //sample-table_length
    		  var pnm = $scope.selt;
     		  var st = document.getElementById("start").value;
     		  $scope.nowloading = true;
     		  $http.get("/advertising/queryAdvertising.do?advertName="+pnm+"&inputtime="+st).success(
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
  		    	    	$scope.yybsadverts = $scope.data.slice(($scope.pageSize * ($scope.selPage - 1)), ($scope.selPage * $scope.pageSize));//通过当前页数筛选出表格当前显示数据
  		    	    	}
  		    	    	$scope.yybsadverts = $scope.data.slice(0, $scope.pageSize);
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
app.service('ProdService', function(ProdQueryListService){
   this.queryList = function($scope, $http) {
      return ProdQueryListService.prodQueryList($scope, $http);
   }
});

app.controller('myCtrl', function($scope, $http) {
				$http.get("/advertising/queryNavList.do?genusType=advertType")
			  			.success(
			   				function (response) {
			   					//数据源
			  		    	    	$scope.data = response.jsondata;
			  		    	    	
			  		    	    	$scope.navList = $scope.data;
   					
				});
				$scope.pageNos = ["5", "20", "50"];
				//初始化一页包含多少条数据
				$scope.prodpage = "5";
				
				
				//初始化查询
				//ProdService.queryList($scope, $http);
				//点击按钮查询
			  //  $scope.queryList = function() {
			    	//$scope.prodpage = "5";
			    	//ProdService.queryList($scope, $http);
			  //  }
			  
			  
				// 定义显示的商品信息
				$scope.showClassify=null;
				// 定义开始页数
				$scope.startPage=1;
				// 定义每页显示条数
				$scope.pageNum=2;
				// 点击导航条添加广告信息
				$scope.clickNav=function(genusId){
					$scope.startPage=1;
					$scope.showClassify=genusId;
				}
				// 设置过滤器
				$scope.filterNav=function(advert){
					// 这里相当于给全局变量赋值，advert.genusId是由ng-repeat循环出来的
					return $scope.showClassify==advert.genusId || $scope.showClassify==null;
				}
			
			
			 
});
</script>
