$(function() {
	var isOrder = document.getElementById("isOrder").value;
	if(isOrder == "0"){
		document.getElementById("noOrder").style.display="";
	}
	//点击查询按钮
	$('#selectBtn').on('click', function(){
		if ($("#studentId").val() == "" || $("#studentId").val() == "输入学号" || $("#studentId").val() == null || $("#studentId").val() == undefined) {
			$("#studentId").css("border-color","#e10602");
			return false;
		}
		$("#selectBtn").attr("disabled", true);
		document.getElementById("myPhotoBody").innerHTML="";
		document.getElementById("noOrder").style.display="none";
		var studentId = document.getElementById("studentId").value;
		var storeId = document.getElementById("storeId").value;
		var innerString = "";
		var layIndex = layer.load(1, {
			shade: [0.2,'#fff'] //0.2透明度的白色背景
		});
		$.ajax({
			type: "get",     
			url: "/photograph/getStudentPhoto.do?",    
			data: {
				storeId:storeId,
				inKey:studentId
			}, 
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				var titleString = "<ul class='mui-table-view mui-grid-view'>";
				$.each(data,function(i,item){
					innerString += "<li class='mui-table-view-cell mui-media mui-col-xs-6'><p>";
					innerString += "<img class='mui-media-object' data-preview-src='' data-preview-group='1' src='"+item.filepath+"'></p>";
					innerString += "<div class='mui-media-body'>"+item.fileIndex+"</div></li>";
					innerString = titleString + innerString+"</ul>";
				});
				if(innerString == ""){
					document.getElementById("noOrder").style.display="";
				}else{
					document.getElementById("myPhotoBody").innerHTML=innerString;
				}
				layer.close(layIndex); //关闭层
			},
			error: function(err) {
				layer.close(layIndex); //关闭层
				alert("error"+err);     
			}
		});
	});
	
	//昵称
	$("#studentId").focus(function () {
		if (this.value == "输入学号") {
			this.value = "";
		}
		$("#selectBtn").attr("disabled", false);
	});
	$("#studentId").blur(function () {
		if (this.value == "") {
			this.style.color = "#8f8f8f";
			this.value = "输入学号";
		}
	});
	$("#studentId").keydown(function () {
		this.style.color = "#000";
	});
});
