<meta http-equiv="Content-Type" content="text/html;charset=GBK"> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
</script>
<script language="javascript" src="/pages/orderFood/js/jquery.js"></script>
<script language="javascript" src="/pages/orderFood/js/mui.min.js"></script>
<script language="javascript" src="/pages/orderFood/js/swiper.min.js"></script>

