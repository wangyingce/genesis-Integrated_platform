$(function() {
	/*alert(window.location.href);*/
	$("#queryBtn").click(function() {
		//定义查询表格
		$("#infos").jqGrid({
			autowidth : true, //高自动
			height : 'auto', //自动宽度
			url : "/merchant/queryMerchant.do",
			datatype : "json", //数据格式
			mtype : "POST",
			colNames:['商户名称','收款人','银行','账号'],
			colModel:[
                 /*{name:'nickName',index:'nickName',sortable:false,align:"center",
                	 //formatter:function(cellvalue, options, rowObject){
                	 //{field:'nickName',title:'nickName', width:80,formatter: function(value,row,index){
                	 //return viewInfo(cellvalue);
                     //return "<a onclick=\"viewInfo(this)\" style='text-decoration:blink;color:red' >"+cellvalue+"</a>";
                       //                     return "<a onclick="viewInfo(cellvalue);">"+cellvalue+"</a>";
                	 formatter:function(value,rec){  
                		 var btn = "<a  onclick='editRow('"+"1111"+"')' >编辑</a>";  
                         return btn; 
                         
                 }},*/
                /* {name:'nickName',index:'nickName',formatter: cLink,sortable:false},
                 function cLink(cellvalue, options, rowObject){
                 return '<a href="javascript:void(0)" onclick="">xxx</a>';
                 } ,*/
				//{name:'nickName',index:'nickName',align:'center',sortable:true},
                {name:'nickName',index:'nickName',align:'center',sortable:true},
				{name:'corporation',index:'corporation',align:'center',sortable:true},
				{name:'bankCname',index:'bankCname',align:'center',sortable:true},
				{name:'account',index:'account',align:'center',sortable:true}
			],
			onCellSelect : function(rowid, index, contents, event){
				var nickName = contents;
				window.location.href='merchantAdd.html?ycode='+nickName;
			 },
			rowNum:10,
			rowList:[10,20,30],
			viewrecords:true,
			jsonReader:{
	              root:"jsondata",  
	        	  records: "record", 
	        	  repeatitems : false
			},
			
			prmNames:{rows:"merchantVo.pageSize",page:"merchantVo.pageNo",sort:"page.orderBy",order:"page.order"},
			pager:"#pager",
		});
		 
		var queryDate1 = $("#queryDate1").val();
		var queryDate2 = $("#queryDate2").val();
		/*alert("queryDate1"+queryDate1);
		alert("queryDate2"+queryDate2);
		alert("开始查询");*/

		//$("#infos").jqGrid('clearGridData');
		//组装参数
		$("#infos").jqGrid('setGridParam', {
			url : "/merchant/queryMerchant.do",
			postData : {
				"merchantVo.queryDate1" : queryDate1,
				"merchantVo.queryDate2" : queryDate2
			},
		}).trigger("reloadGrid");
		//alert("查询结束");

		// init();
		// $("#infos").setGridWidth($(window).width()*0.982);
	});
});

function editRecord () {
alert("点击");
alert("点击");
}

/*$.ajax({
    type: "get", 
    url: "/merchant/loadMerchant.do", 
    data: {rqstUrl:rqstUrl},
    dataType: "json",
    success: function(data) {
  //  alert(data.nickName); 
               document.getElementById("merchantId").value = data.merchantId;
                document.getElementById("merchantId").readOnly=true;
               document.getElementById("nickName").value = data.nickName;
               document.getElementById("oldqrcodeimg").src = data.qrcodePath;
               document.getElementById("corporation").value = data.corporation;
               document.getElementById("identifyNumber").value = data.identifyNumber;
               document.getElementById("bankCname").value = data.bankCname;
               document.getElementById("account").value = data.account;
               document.getElementById("payBtn").value = "更新";
               $('#oldqrcode').show();
               },
    });     */
