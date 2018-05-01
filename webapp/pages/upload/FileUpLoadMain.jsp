<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片上传</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
body { background: #ffffff; color: #444; }
a { color: #07c; text-decoration: none; border: 0; background-color: transparent; }
body, div, q, iframe, form, h5 { margin: 0; padding: 0; }
img, fieldset { border: none 0; }
body, td, textarea { word-break: break-all; word-wrap: break-word; line-height:1.6; }
body, input, textarea, select, button { margin: 0; font-size: 14px; font-family: Tahoma, SimSun, sans-serif; }
div, p, table, th, td { font-size:1em; font-family:inherit; line-height:inherit; }
h5 { font-size:12px; }
ol li,ul li{ margin-bottom:0.5em;}
pre, code { font-family: "Courier New", Courier, monospace; word-wrap:break-word; line-height:1.4; font-size:12px;}
pre{background:#f6f6f6; border:#eee solid 1px; margin:1em 0.5em; padding:0.5em 1em;}
#content { padding-left:50px; padding-right:50px; }
#content h2 { font-size:20px; color:#069; padding-top:8px; margin-bottom:8px; }
#content h3 { margin:8px 0; font-size:14px; COLOR:#693; }
#content h4 { margin:8px 0; font-size:16px; COLOR:#690; }
#content div.item { margin-top:10px; margin-bottom:10px; border:#eee solid 4px; padding:10px; }
hr { clear:both; margin:7px 0; +margin: 0;
border:0 none; font-size: 1px; line-height:1px; color: #069; background-color:#069; height: 1px; }
.infobar { background:#fff9e3; border:1px solid #fadc80; color:#743e04; }
</style>
<script type="text/javascript" src="/pages/upload/js/zDrag.js"></script>
<script type="text/javascript" src="/pages/upload/js/zDialog.js"></script>
<script type="text/javascript">
  var diag;
  var initHeight=180;
  function openWindow()
  {
    diag   = new Dialog();
	diag.Width = 680;
	diag.Height = initHeight;
	diag.Title = "文件上传窗口";
	diag.URL = "FileUpLoad.jsp";
	diag.show();
  }
  function addHeight()
  {
    diag.setSize(680,initHeight+30);
    initHeight=initHeight+30;
  }
  function decreaseHeight()
  {
    diag.setSize(680,initHeight-30);
    initHeight=initHeight-30;
  }
  function closeWindow()
  {
    diag.close();
  }
  function resizeHeight()
  {
    initHeight=180;
    diag.setSize(680,180);
  }
  function addProgressHeight()
  {
    diag.setSize(680,initHeight+80);
  }
</script>
  </head>
  
  <body>
    <input type="button" value="上传文件" onclick="openWindow();"/> 
  </body>
</html>
