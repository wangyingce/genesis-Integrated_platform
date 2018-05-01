$(document).ready(function(){
  //效果测试，程序对接可将其删除
  $(".btmNav a:first").click(function(){
	  $(".topCart em").html(parseInt($(".topCart em").html())+1);
	  });
});