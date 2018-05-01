package test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class ShortMessageTest {
	public static void main(String[] args) {
		String url = "https://eco.taobao.com/router/rest";
		String appkey = "23352856";
		String secret = "4ddc33437aac4aa3597aa8b703c2a583";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//可选 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
		req.setExtend("123456");
		//必须 短信类型，传入值请填写normal
		req.setSmsType("normal");
		//可选 短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
		req.setSmsFreeSignName("惠好近");
		//必须 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
		//板名称: 用户注册验证码
		//模板ID: SMS_5052276
		//*模板内容:
		//验证码${code}，您正在注册成为${product}用户，感谢您的支持！
		req.setSmsParamString("{'code':'3527','product':'灰鲸摄影'}");
		//必须 短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
		req.setRecNum("13669291037");
		//短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
		req.setSmsTemplateCode("SMS_8155814");
		//成功返回码{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"100663458616^1101070900185","success":true},"request_id":"r4lu3uaf87g2"}}
		//失败返回码{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.MOBILE_NUMBER_ILLEGAL","sub_msg":"号码格式错误","request_id":"ishfhkt4jeym"}}

		
		//验证码${code}，您正尝试异地登录${product}，若非本人操作，请勿泄露
		
		
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
	}
}
