$(function() {
	//初始化是否已确认按钮
	var isCheck = document.getElementById("isCheck").value;
	if(isCheck == "1"){
		$("#confirmOrder").attr("disabled", true);
		$("#confirmOrder").val("已确认");
	}
	//查看近几日订单
	$('#confirmOrder').on('click', function(){
		var myOrderId = document.getElementById("myOrderId").value;
		//弹出一个询问层
		layer.msg('将会立刻通知客户确认消息', {
		  time: 0, //不自动关闭
		  btn: ['确定', '取消'],
		  skin: 'layui-layer-lan',//调用样式
		  shift: 5,
		  shade: [0.2],
		  yes: function(index){
		    layer.close(index);
		    //弹出一个等待层
			var layIndex = layer.load(1, {
			  shade: [0.2,'#fff'] //0.2透明度的白色背景
			});
		    //开始执行删除
		    $.ajax({
			    type: "get",     
			    url: "/photograph/confirmOrder.do?",    
			    data: {
			    	inKey:myOrderId
			    }, 
			    contentType: "application/json; charset=utf-8",
			    dataType: "json",
			    success: function(data) {
			    	//获取序号
				    $("#confirmOrder").attr("disabled", true);
				    $("#confirmOrder").val("已确认");
				    layer.close(layIndex);
				    layer.msg('确认成功', {
				    	time: 1000 //1秒后自动关闭
				    });
			    },
			    error: function(err) {
			        alert("error"+err);     
			    }
			});
			
		   }
		});
	});
	
	//查看近几日订单
	$('#exportOrder').on('click', function(){
		alert("程序猿正在努力码字中.....");
	});	    	     
});

