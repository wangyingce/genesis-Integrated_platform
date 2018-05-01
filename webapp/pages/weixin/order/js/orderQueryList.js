jQuery(function() {
	jQuery("#queryBtn").click(function() {
		
		//定义查询表格
		jQuery("#infos").jqGrid({
			autowidth : true, //高自动
			height : 'auto', //自动宽度
			url : "/weixinorder/orderQuery.do",
			datatype : "json", //数据格式
			mtype : "POST",
			colNames:['交易时间','商户订单号','微信支付单号','交易金额'],
			colModel:[
				{name:'time_end1',index:'time_end1',align:'center',sortable:true},
				{name:'orderid',index:'orderid',align:'center',sortable:true},
				{name:'transaction_id',index:'transaction_id',align:'center',sortable:true},
				{name:'total_fee1',index:'total_fee1',align:'center',sortable:true}
			],
			rowNum:10,
			rowList:[10,20,30],
			viewrecords:true,
			jsonReader:{
	              root:"jsondata",  
	        	  records: "record", 
	        	  repeatitems : false
			},
			prmNames:{rows:"orderQueryVo.pageSize",page:"orderQueryVo.pageNo",sort:"page.orderBy",order:"page.order"},
			pager:"#pager",
		});

		
		var payDate1 = jQuery("#payDate1").val();
		var payDate2 = jQuery("#payDate2").val();
		//jQuery("#infos").jqGrid('clearGridData');
		//组装参数
		jQuery("#infos").jqGrid('setGridParam', {
			url : "/weixinorder/orderQuery.do",
			postData : {
				"orderQueryVo.payDate1" : payDate1,
				"orderQueryVo.payDate2" : payDate2
			},
		}).trigger("reloadGrid");
		return false;
	});
	
	jQuery(".chzn-select").chosen();
});
