<%@page language="java" pageEncoding="UTF-8"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加品牌</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/style.css"/>       
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<!--[if lte IE 8]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
<![endif]-->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/layer/layer.js" type="text/javascript"></script>
<script type="text/javascript" src="Widget/swfupload/swfupload.js"></script>
<script type="text/javascript" src="Widget/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="Widget/swfupload/swfupload.speed.js"></script>
<script type="text/javascript" src="Widget/swfupload/handlers.js"></script>
<!-- 引入angularjs -->
<script src="http://cdn.bootcss.com/angular.js/1.5.0/angular.min.js"></script>
</head>

<body>
<div class=" clearfix" ng-app="addbrandApp" ng-controller="addbrandCtrl">
<div id="loading" class="loading" ng-show="nowloading" >Loading</div> 
 <div id="add_brand" class="clearfix">
 <div class="left_add">
   <div class="title_name">添加品牌</div>
   <ul class="add_conent">
    <input type="text" id="brandId" name="brandId" ng-model="bd.brandId" style="display:none"/>
    <li class=" clearfix"><label class="label_name"><i>*</i>品牌名称：</label> <input name="bdnm" id="bdnm" ng-model="bd.brandName" type="text" class="add_text"/></li>
    <li class=" clearfix"><label class="label_name"><i>*</i>品牌序号：</label> <input name="" id="bdsn" ng-model="bd.brandSeriaNo" type="text" class="add_text" style="width:80px"/></li>
    <li class=" clearfix"><label class="label_name">品牌图片：</label>
           <div class="demo l_f">
	           <div class="logobox"><div class="resizebox"><img src="images/image.png" width="100px" alt="" height="100px"/></div></div>	
               <div class="logoupload">
                  <div class="btnbox"><a id="uploadBtnHolder" class="uploadbtn" href="javascript:;">上传替换</a></div>
                  <div style="clear:both;height:0;overflow:hidden;"></div>
                  <div class="progress-box" style="display:none;">
                    <div class="progress-num">上传进度：<b>0%</b></div>
                    <div class="progress-bar"><div style="width:0%;" class="bar-line"></div></div>
                  </div>
              </div>            
                      
           </div> <div class="prompt"><p>图片大小<b>120px*60px</b>图片大小小于2MB,</p><p>支持.jpg;.gif;.png;.jpeg格式的图片</p></div>  
    </li>
    <input type="text" id="brandPicture" name="brandPicture" ng-model="bd.brandPictureId" style="display:none"/>
         <li class=" clearfix"><label class="label_name"><i>*</i>所属地区：</label>
         <input name="" id="bdar" ng-model="bd.brandArea" type="text" class="add_text" style="width:120px"/>
         <select id="nation" ng-model="bd.nation" name="" class="text_add"><option  value="1">国内品牌</option><option value="2">国外品牌</option></select>
         </li>
         <li class=" clearfix"><label class="label_name">品牌描述：</label> <textarea name="" cols="" id="bdmk" ng-model="bd.brandRemark" rows="" class="textarea" onkeyup="checkLength(this);"></textarea><span class="wordage">剩余字数：<span id="sy" style="color:Red;">500</span>字</span></li>
         <li class=" clearfix"><label class="label_name"><i>*</i>显示状态：</label> 
         <label><input name="checkbox" type="radio" id="bdst1" class="ace" checked="checked"><span class="lbl">显示</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <label><input type="radio" class="ace" name="checkbox"id="bdst2"><span class="lbl">不显示</span></label>
         </li>
   </ul>
 </div>
 <div class="right_add">
  <div class="title_name">添加商品-add</div>
 
    <div class="p_select_list">
        <div class="left_produt produt_select_style" >
           <div id="select_style"></div>   
           <span class="seach_style"><input name="" type="text"  id="seach"/ class="seach"><i class="icon-search"></i></span>
        </div>
        <div  class="Switching_btn"> 
          <span id="add_all"  class="Switching">
          <input type="button" class="btn btn-warning" value=">>"/>
          </span>
          <span id="add" class="Switching">
          <input type="button" class="btn btn-primary" value=">"/>
          </span>
          <span id="remove"  class="Switching">
          <input type="button" class="btn btn-primary" value="<"/>
          </span>
          <span id="remove_all"  class="Switching">
          <input type="button" class="btn btn-warning" value="<<"/>
          </span> 
        </div>
        <div class="right_product produt_select_style">
         <div class="title_name">归类商品</div>
          <select multiple="multiple" id="select2" class="select">
          </select>
        </div>
      </div>
 </div>
 </div>
  <div class="button_brand"><input name="" id="submitband" type="button"  class="btn btn-warning" value="保存"/><input name="" type="reset" value="取消" class="btn btn-warning"/></div>
</div>
</body>
</html>
<script type="text/javascript">
     $(document).ready(function(){
 $(".left_add").height($(window).height()-60); 
  $(".right_add").width($(window).width()-600);
   $(".right_add").height($(window).height()-60);
  $(".select").height($(window).height()-195); 
  $("#select_style").height($(window).height()-220); 
 //当文档窗口发生改变时 触发  
    $(window).resize(function(){
		  $(".right_add").width($(window).width()-600); 
		 $(".left_add").height($(window).height()-60);
		 $(".right_add").height($(window).height()-60); 
		 $(".select").height($(window).height()-195); 
		$("#select_style").height($(window).height()-220); 
	});
	 })
	function checkLength(which) {
	var maxChars = 500;
	if(which.value.length > maxChars){
	   layer.open({
	   icon:2,
	   title:'提示框',
	   content:'您出入的字数超多限制!',	
    });
		// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
		which.value = which.value.substring(0,maxChars);
		return false;
	}else{
		var curr = maxChars - which.value.length; // 减去 当前输入的
		document.getElementById("sy").innerHTML = curr.toString();
		return true;
	}
}
//下拉框交换JQuery
$(function(){
    //移到右边
    $('#add').click(function() {
    //获取选中的选项，删除并追加给对方
        $('#select1 option:selected').appendTo('#select2');
    });
    //移到左边
    $('#remove').click(function() {
        $('#select2 option:selected').appendTo('#select1');
    });
    //全部移到右边
    $('#add_all').click(function() {
        //获取全部的选项,删除并追加给对方
        $('#select1 option').appendTo('#select2');
    });
    //全部移到左边
    $('#remove_all').click(function() {
        $('#select2 option').appendTo('#select1');
    });
    //双击选项
    $('#select1').dblclick(function(){ //绑定双击事件
        //获取全部的选项,删除并追加给对方
        $("option:selected",this).appendTo('#select2'); //追加给对方
    });
    //双击选项
    $('#select2').dblclick(function(){
       $("option:selected",this).appendTo('#select1');
    });
    
    /**actionsubmit--------品牌提交-------------start*/
    $('#submitband').click(function() {
    	/**获取已选择的商品id*/
    	var s2;
    	$("#select2 option").each(function(i){
    		if(s2==undefined){
    			s2=this.value+",";
    		}else{
    			s2=s2+this.value+",";	
    		}
    	});
    	/**获取页面品牌相关参数----------------start*/
    	var brandId = document.getElementById("brandId").value;
    	var brandName = document.getElementById("bdnm").value;
    	var brandSeriaNo = document.getElementById("bdsn").value;
    	var brandPicture = document.getElementById("brandPicture").value;
    	var brandArea = document.getElementById("bdar").value;
    	var brandRemark = document.getElementById("bdmk").value;
    	var nation = document.getElementById("nation").value;
    	var brandStatus;
    	var st1 = document.getElementById("bdst1").value;
    	var st2 = document.getElementById("bdst1").value;
    	if(st1){
    		brandStatus="show";
    	}else if(st2){
    		brandStatus="hid";
    	}
    	/**获取页面品牌相关参数----------------end*/
    	/**aj submit start----------------------------------------*/
    	$.ajax({
	        type: "get",     
	        url: "/brand/addbrand.do",    
	        data: {
	        	prodids:s2,
	        	brandId:brandId,
	        	brandName:encodeURI(brandName),
	        	brandSeriaNo:brandSeriaNo,
	        	brandPicture:brandPicture,
	        	brandArea:encodeURI(brandArea),
	        	brandRemark:encodeURI(brandRemark),
	        	brandStatus:brandStatus,
	        	nation:nation
	        }, 
	           dataType: "json",
	           success: function(res) {
	        	   //alert(res);
	        	   if(res=="sus"){
     	        		 layer.alert('提交成功！',{title:'提示框',icon:1,}); 
	        		     setTimeout(function(){
	        		       location.reload();
	                     },5000);
	                  }else{
	                 	 layer.alert('提交失败',{title: '提示框',icon:1,});
	                  }
	           }
	    });
    	/**aj submit end----------------------------------------*/
    });
    /**actionsubmit--------品牌提交-------------end*/
});

/*var rqstUrl = window.location.href;
var brandid = rqstUrl.substring(rqstUrl.indexOf("=")+1, rqstUrl.length);
if(brandid!=null&&brandid!=undefined){
	$.ajax({
	    type: "get",     
	    url: "/brand/queryBrandList.do",    
	    data: {brandId:brandid}, 
	    dataType: "json",
	    success: function(data) {
	    	                     alert(data.jsondata);
	    	                     var dada = data.jsondata;
	    	                     alert(dada.brandName);
	    	                     }
	});
}*/
/*
$.ajax({
    type: "get",     
    url: "/product/queryProductList.do?",    
    data: {
    	rqsttype:"addband"
    }, 
       dataType: "json",
       success: function(response) {
            var user=response;
            $(document).ready(function(){
                var seach=$("#seach");
        		
        		seach.keyup(function(event){
        		//获取当前文本框的值
                var seachText=$("#seach").val();
        		 var product="<div class='title_name'>产品名称</div><select multiple='multiple' id='select1' class='select'>";
        		  if(seachText!=""){			 
        			  $.each(user,function(id, item){				     				    
                             //如果包含则为select赋值
                             if(item.productName.indexOf(seachText)!=-1 && item.validstatus.indexOf(seachText)!=-1 ){
                                product+="<option value="+item.productId+">"+"("+item.validstatus+")"+item.productName+"</option>";
                             }
                          })								  
        				  $("#select_style").html(product);
        			  }
        			  else{					 
        				  $.each(user,function(id, item){
        					name = item.productName; 
        					status= item.validstatus;
        				   product+="<option value="+item.productId+">"+"("+item.validstatus+")"+item.productName+"</option>";				   
        				  })
                          $("#select_style").html(product);
                       }
        			   product+"</select>";
        		}) ;
        					
        			  var product="<div class='title_name'>产品名称</div>";
        				
        				 product+="<select multiple='multiple' id='select1' class='select'";
        				  $.each(user,function(id, item){
        					name = item.productName;  
        					status= item.validstatus;
        				   product+="<option value="+item.productId+" title="+item.productName+">"+"("+item.validstatus+")"+item.productName+"</option>";
        				   
        				  })
        				  product+"</select>";
                          $("#select_style").html(product);
        			
        	})
       }
});*/
</script>
<script type="text/javascript">
function updateProgress(file) {
	$('.progress-box .progress-bar > div').css('width', parseInt(file.percentUploaded) + '%');
	$('.progress-box .progress-num > b').html(SWFUpload.speed.formatPercent(file.percentUploaded));
	if(parseInt(file.percentUploaded) == 100) {
		// 如果上传完成了
		$('.progress-box').hide();
	}
}

function initProgress() {
	$('.progress-box').show();
	$('.progress-box .progress-bar > div').css('width', '0%');
	$('.progress-box .progress-num > b').html('0%');
}

function successAction(fileInfo) {
	var up_path = fileInfo.path;
	var up_width = fileInfo.width;
	var up_height = fileInfo.height;
	var _up_width,_up_height;
	if(up_width > 120) {
		_up_width = 120;
		_up_height = _up_width*up_height/up_width;
	}
	$(".logobox .resizebox").css({width: _up_width, height: _up_height});
	$(".logobox .resizebox > img").attr('src', up_path);
	$(".logobox .resizebox > img").attr('width', _up_width);
	$(".logobox .resizebox > img").attr('height', _up_height);
}

var swfImageUpload;
$(document).ready(function() {
	var settings = {
		flash_url : "Widget/swfupload/swfupload.swf",
		flash9_url : "Widget/swfupload/swfupload_fp9.swf",
		upload_url: "./UpLoadFileServlet.action",// 接受上传的地址
		file_size_limit : "5MB",// 文件大小限制
		file_types : "*.jpg;*.gif;*.png;*.jpeg;",// 限制文件类型
		file_types_description : "图片",// 说明，自己定义
		file_upload_limit : 100,
		file_queue_limit : 0,
		custom_settings : {},
		debug: false,
		// Button settings
		button_image_url: "Widget/swfupload/upload-btn.png",
		button_width: "95",
		button_height: "30 ",
		button_placeholder_id: 'uploadBtnHolder',
		button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
		button_cursor : SWFUpload.CURSOR.HAND,
		button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,
		
		moving_average_history_size: 40,
		
		// The event handler functions are defined in handlers.js
		swfupload_preload_handler : preLoad,
		swfupload_load_failed_handler : loadFailed,
		file_queued_handler : fileQueued,
		file_dialog_complete_handler: fileDialogComplete,
		upload_start_handler : function (file) {
			initProgress();
			updateProgress(file);
		},
		upload_progress_handler : function(file, bytesComplete, bytesTotal) {
			updateProgress(file);
		},
		upload_success_handler : function(file, data, response) {
			// 上传成功后处理函数
			//var fileInfo = eval("(" + data + ")");
			//successAction(fileInfo);
			if(data.indexOf("错误")!=-1){
				alert("错误信息:"+data);
			}else{
				document.getElementById("brandPicture").value = data;
				//根据上传成功返回的id查询文件表并展示预览
				$.ajax({
			        type: "get",     
			        url: "/brand/findBrandImage.do",    
			        data: {
			        	brandPicture:data
			        }, 
			           dataType: "json",
			           success: function(res) {
			                //alert("filepath:"+res.filepath);
			                var up_path = res.filepath;
			            	var	_up_width = 120;
			            	var	_up_height = 120;
			            	$(".logobox .resizebox").css({width: _up_width, height: _up_height});
			            	$(".logobox .resizebox > img").attr('src', up_path);
			            	$(".logobox .resizebox > img").attr('width', _up_width);
			            	$(".logobox .resizebox > img").attr('height', _up_height);
			           }
			    });
			}
		},
		upload_error_handler : function(file, errorCode, message) {
			alert('上传发生了错误！');
		},
		file_queue_error_handler : function(file, errorCode, message) {
			if(errorCode == -110) {
				alert('您选择的文件太大了。');	
			}
		}
	};
	swfImageUpload = new SWFUpload(settings);
});
</script>
<!-- angularJs加载块 -->
<script>
/**自定义域*/
var addbrandApp = angular.module("addbrandApp", []);  
addbrandApp.controller('addbrandCtrl', function($scope, $http) {
	   $scope.nowloading = true;
	   /**查询可以添加的产品列表，无论新增品牌还是编辑品牌都加载*/
	   $http.get("/product/queryProductList.do?rqsttype=addband").success(
			    function (response) {
                   			 	      $scope.nowloading = false;
			    	                  var user=response;
			  				    	  //alert("412:"+response);
		                              $(document).ready(function(){
		                                  var seach=$("#seach");		        		
		        	                      seach.keyup(function(event){
		        	                    	  //获取当前文本框的值
			                                  var seachText=$("#seach").val();
			        	                      var product="<div class='title_name'>产品名称</div><select multiple='multiple' id='select1' class='select'>";
			        	                      if(seachText!=""){			 
		        	                    	      $.each(user,function(id, item){				     				    
		                                             //如果包含则为select赋值
		                                             if(item.productName.indexOf(seachText)!=-1 && item.validstatus.indexOf(seachText)!=-1 ){
		                                                product+="<option value="+item.productId+">"+"("+item.validstatus+")"+item.productName+"</option>";
		                                             }
		                                          })								  
		        	                    		  $("#select_style").html(product);
		        	                           }else{					 
		        	                    		  $.each(user,function(id, item){
		        	                    			name = item.productName; 
		        	                    			status= item.validstatus;
		        	                    		    product+="<option value="+item.productId+">"+"("+item.validstatus+")"+item.productName+"</option>";				   
		        	                    		  })
		                                          $("#select_style").html(product);
		                                       }
			        	                       product+"</select>";
		        	                      
		        	                       });
		        	                       var product="<div class='title_name'>产品名称</div>";
		        	                       product+="<select multiple='multiple' id='select1' class='select'";
		        	                       $.each(user,function(id, item){
		        	                    		   name = item.productName;  
		        	                    		   status= item.validstatus;
		        	                    		   product+="<option value="+item.productId+" title="+item.productName+">"+"("+item.validstatus+")"+item.productName+"</option>";
		        	                    		   
		        	                       })
		        	                       product+"</select>";
		                                   $("#select_style").html(product);
		        	                     })
			                         }
			    );
	   /**如果是编辑品牌信息，先查询后加载数据*/
	   var rqstUrl = window.location.href;
	   if(rqstUrl.indexOf("=")>=0){
		   $scope.nowloading = true;
		   var brandid = rqstUrl.substring(rqstUrl.indexOf("=")+1, rqstUrl.length);
		   /**加载品牌信息以及其下的商品列表--------------------------------------------------start*/
		   $http.get("/brand/queryBrandDetail.do?brandId="+brandid).success(
				    function (response) {
				 	    $scope.nowloading = false;
				    	/**加载基础信息*/
	    	            $scope.bd = response.brand;
	    	            /**加载图片信息*/
	    	            var up_path = response.brand.brandPicture;
		            	var	_up_width = 120;
		            	var	_up_height = 120;
		            	$(".logobox .resizebox").css({width: _up_width, height: _up_height});
		            	$(".logobox .resizebox > img").attr('src', up_path);
		            	$(".logobox .resizebox > img").attr('width', _up_width);
		            	$(".logobox .resizebox > img").attr('height', _up_height);
		            	/**记载列表数据*/
				    	var user=response.prods;
                        $(document).ready(function(){
                        	var product="";
                        	$.each(user,function(id, item){
	                    		   name = item.productName;  
	                    		   status= item.validstatus;
	                    		   product+="<option value="+item.productId+" title="+item.productName+">"+"("+item.validstatus+")"+item.productName+"</option>";
	                    		   
	                       })
	                       $("#select2").html(product);
                        });
				    }
		   );	    
	   }
	   /**加载品牌信息以及其下的商品列表--------------------------------------------------end*/
});
</script>