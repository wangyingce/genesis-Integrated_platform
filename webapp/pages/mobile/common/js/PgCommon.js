$(function() {
	/**var $a = $('.btf a');
	$a.click(function(){
		var $this = $(this);
		$a.removeClass();
		$this.addClass('mui-tab-item mui-active');
	});*/
	//增加选中效果
	var bottonName = $('#bottomId').val();
	$('#'+bottonName).addClass('mui-active');
	$('#'+bottonName).removeAttr("href");
});
	

	