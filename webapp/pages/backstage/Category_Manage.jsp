<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<!--[if lt IE 9]>
		<script type="text/javascript" src="js/html5.js"></script>
		<script type="text/javascript" src="js/respond.min.js"></script>
		<script type="text/javascript" src="js/PIE_IE678.js"></script>
		<![endif]-->
		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/style.css"/>       
		<link href="assets/css/codemirror.css" rel="stylesheet">
		<link rel="stylesheet" href="assets/css/ace.min.css" />
		<link rel="stylesheet" href="Widget/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
		<!--[if IE 7]>
			<link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
		<link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="Widget/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
		<!-- 引入angularjs -->
		<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
		<!-- 引入angular tree -->
		<script type="text/javascript" src="js/angular-tree-control.js"></script>
		<link rel="stylesheet" type="text/css" href="assets/css/tree-control.css">
		<link rel="stylesheet" type="text/css" href="assets/css/tree-control-attribute.css">
		<title>新增图片</title>
	</head>
	<body>
		<div class="clearfix" id="add_picture" ng-app="cateApp" ng-controller="cateCtrl">
			<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
			<div id="scrollsidebar" class="left_Treeview">
			    <div class="show_btn" id="rightArrow"><span></span></div>
			    <div class="widget-box side_content" >
				    <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
				    <div class="side_list">
				      <div class="widget-header header-color-green2">
				          <h4 class="lighter smaller">选择产品类型</h4>
				      </div>
				      <div class="widget-body">
				          <div class="widget-main padding-8">
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
			 </div>
			 <div class="type_style">
			 	<div class="type_title">产品类型信息</div>
				<div class="type_content">
					<div class="Operate_btn">
						<a href="javascript:ovid()" ng-click="updategenus('add');" class="btn  btn-warning"><i class="icon-edit align-top bigger-125"></i>新增子类型</a>
					  	<a href="javascript:ovid()" ng-click="updategenus('mod');" class="btn  btn-success"><i class="icon-ok align-top bigger-125"></i>修改该类型</a>
					  	<a href="javascript:ovid()" ng-click="updategenus('del');" class="btn  btn-danger"><i class="icon-trash   align-top bigger-125"></i>禁用该类型</a>
				  	</div>
				 </div>
			 </div>
			 <div class="page_right_style">
			    <input type="hidden" ng-model="genusId">
				<form action="" method="post" class="form form-horizontal" id="form-article-add">
					<div class="Operate_cont clearfix">
			      		<label class="form-label"><span class="c-red">*</span>分类名称：</label>
				      	<div class="formControls ">
				        	<input type="text" class="input-text" ng-model="name" placeholder="">
				      	</div>
			    	</div>
				    <div class="Operate_cont clearfix">
					    <label class="form-label">备注：</label>
					    <div class="formControls">
						    <textarea ng-model="genusremark" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="textarealength(this,100)"></textarea>
						    <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
					    </div>
				    </div>
				</form>
			 </div>  
		</div>
		<script src="js/jquery-1.9.1.min.js"></script>   
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/typeahead-bs2.min.js"></script>
		<script src="assets/layer/layer.js" type="text/javascript" ></script>
		<script src="assets/laydate/laydate.js" type="text/javascript"></script>
		<script type="text/javascript" src="Widget/My97DatePicker/WdatePicker.js"></script> 
		<script type="text/javascript" src="Widget/Validform/5.3.2/Validform.min.js"></script> 
		<script src="js/lrtk.js" type="text/javascript" ></script>
		<script type="text/javascript" src="js/H-ui.js"></script> 
		<script type="text/javascript" src="js/H-ui.admin.js"></script> 
	
		<script>
		
			$(function() { 
				$("#add_picture").fix({
					float : 'left',
					skin : 'green',	
					durationTime :false,
					stylewidth:'220',
					spacingw:0,
					spacingh:260,
				});
			});
			
			$( document).ready(function(){
				//初始化宽度、高度
				$(".widget-box").height($(window).height()); 
				$(".page_right_style").height($(window).height()); 
				$(".page_right_style").width($(window).width()-220); 
			    //当文档窗口发生改变时 触发  
				$(window).resize(function(){
				
					 $(".widget-box").height($(window).height()); 
					 $(".page_right_style").height($(window).height()); 
					 $(".page_right_style").width($(window).width()-220); 
				});	
			});
		
		</script>
		<!-- angularJs加载块 -->
		<script>
			/**自定义域*/
			var cateApp = angular.module("cateApp", ['treeControl']);  
			cateApp.controller('cateCtrl', function($scope, $http) {
				   $scope.nowloading = true;
				   $http.get("/product/queryGenusTree.do").success(
						    function (response) {
						    	                 $scope.nowloading = false;
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
			  	   
			  	   /**页面按钮功能 start>>*/
			  	   $scope.updategenus = function(operation) {
			  		 $scope.nowloading = true;
					 $http.get("/product/updateGenusData.do?genusId="+$scope.genusId+"&name="+$scope.name+"&genusRemark="+$scope.genusRemark+"&operation="+operation).success(
							    function (response) {
							    	                 $scope.nowloading = false;
							    	                 //加载所属分类数据
						    	    	             alert(response);
						    	    	             location.reload();
							                        }
							    );
				   }
			  	   /**页面按钮功能 end<<*/
			  	   
			  	   /**选择节点触发*/
				   $scope.showSelected = function(node) {
			  		   //$scope.nowloading = true;
			  		   $scope.genusId = node.genusId;
			  		   /**
					   $http.get("/product/queryGenusData.do?genusId="+node.genusId).success(
							    function (response) {
							    	                 $scope.nowloading = false;
							    	                 //加载所属分类数据
						    	    	             $scope.name = response.name;
							                        }
							    );
			  		   */
				   }
			});
		</script>
	</body>
</html>