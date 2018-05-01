<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title>快速注册预约</title>
    	<%@ include file="/pages/mobile/common/Meta_mobile_css.jsp"%>
    	<%@ include file="/pages/mobile/common/Meta_mobile_js.jsp"%>
		<script src="/pages/mobile/photograph/js/PgRegist.js" type="text/javascript"></script>

</head>
<body>
<form action="submitRegistPhotoGraph.do" method="post" name="fm" namespace="/photograph">
	<div class="bao">
		<!--顶部-->
		<header class="mui-bar mui-bar-nav you">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		    <h1 class="mui-title">快速预约</h1>
		</header>
		
		<div class="mui-content bw">
			<div class="headtitle">
		  		${nickName}您好，预约说明：
		    </div>
		    <div class="midtitle">
		    	1、预约成功后订单不可更改，如需取消，规则如下：1)距拍摄时间72小时以上取消订单，退99%订金。2)距拍摄时间48-72小时取消订单，退50%订金。3)距拍摄不足48小时取消订单，不可退订金。
		    	<br>
		    	2、如未按预定日期到店，视为放弃，订金将不予退还。
		    	<br>
		    	3、暂时仅支持单人预约，如需多人请分别预约。
		    </div>	
		</div>
		<div class="bw h5">
			<div class="input_row" >
			    <span class="tdleft">昵称</span><span><img id="registericonName" class="registerinputicon" src="/pages/mobile/photograph/images/registericon1.png"/></span>
	      		<input type="text" id="registerName" name="yyUser.userName" value="输入昵称"/>
			</div>
		  	<div class="input_row" >
			  	<span class="tdleft">+86</span><span><img id="registericonNum" class="registerinputicon" src="/pages/mobile/photograph/images/registericon1.png"/></span>
			    <input type="tel" id="registerMobile" name="yyUser.phone"  maxlength="11" value="输入手机号码" />
			</div>
			<div class="input_row" >
			  	<input type="tel"  id="checkNum" style="width:42%;" name="yyUser.checkNo" value="请输入验证码" />
        		<input type="button" id="getNo-Btn" style="border-radius:2px;width:33%;height:29px" value="获取验证码" />
			</div>
			<div class="input_button1">
      			<input type="button" id="next_Btn" value="下一步" />
    		</div>
			<div class="h5 input_row">
        		<img style="width:4%;" src="/pages/mobile/photograph/images/yesBtn.png"/>
        		<span>点击"下一步",表示已同意<a href="#">《注册协议》</a></span>
        		<br>
        		<br>
        		<label id="checkPhoneText" style="display:none;">手机号或类型不正确</label>
        		<br>
        		<br>
    		</div>
    		<input type="hidden" name="yyUser.owner" id="storeId" value="${yyUser.owner}"/>
    		<input type="hidden" name="yyUser.inType" id="inType" value="${yyUser.inType}"/>
    		<input type="hidden" name="yyUser.inKey" id="inKey" value="${yyUser.inKey}"/>
    		<div class="h5"></div>
    		<div class="h5"></div>
    		<div class="h5"></div>
    		<div class="h5"></div>
		</div>
		<!--中部-->
				
		<!--底部-->
	<!--底部-->
    <input type="hidden" id="bottomId" value="compose">	
	<%@ include file="/pages/mobile/photograph/PgBottom.jsp"%>
</div>
</form>
</body>
</html>
