
<meta http-equiv="Content-Type" content="text/html;charset=GBK"> 
<script type="text/javascript">
/** deleteNode */
function  delNode(){   
  var nodeId = "loading";
  try{   
	  var div =document.getElementById(nodeId);  
	  if(div !==null){
		  document.body.removeChild(div);
		  div=null;    
 	  }  
  } catch(e){   
  	   alert("delete node "+nodeId+" error");
  }   
}

//delNode();//delete loading
</script>
<script language="vbscript"   src="${ctx}/common/js/urlencode.vbs"></script>
<script language="javascript" src="${ctx}/common/js/sinosoft.js"></script>
<script language="javascript" src="${ctx}/common/js/Common.js"></script> 
<script language="javascript" src="${ctx}/common/js/Application.js"></script> 
<script language="javascript" src="${ctx}/common/js/prototype.js"></script> 
<script language="javascript" src="${ctx}/widgets/yui/yahoo-dom-event/yahoo-dom-event.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/connection/connection-min.js"></script> 
<script language="javascript" src="${ctx}/widgets/yui/element/element-beta-min.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/dragdrop/dragdrop-min.js"></script> 
<script language="javascript" src="${ctx}/widgets/yui/container/container-min.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/datasource/datasource-beta-min.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/datatable/datatable-beta-min.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/calendar/calendar-min.js"></script>
<script language="javascript" src="${ctx}/widgets/yui/treeview/treeview-min.js"></script>
  
<script language="javascript" src="${ctx}/widgets/datatable_init.js"></script> 
<script language="javascript" src="${ctx}/common/js/newCalendar.js"></script>
<script language="javascript" src="${ctx}/common/validdate/checkdata.js"></script>

<script language="Javascript" src="${ctx}/common/js/PopCalendar.js" ></script>

