$(function() {
	//获得slider插件对象
	var gallery = mui('.mui-slider');
	gallery.slider({
	  interval:4000//自动轮播周期，若为0则不自动播放，默认为0；
	});
	//解决 所有a标签 导航不能跳转页面
	mui('body').on('tap','a',function(){document.location.href=this.href;});
});
