package com.ysyl.weixin.common.util;

import ins.framework.cache.CacheManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.ysyl.common.util.DateUtil;
import com.ysyl.weixin.pay.utils.CommonUtil;
import com.ysyl.weixin.pay.utils.http.HttpClientConnectionManager;

public class CommonUtils {
	public static DefaultHttpClient httpclient;

	  static
	  {
	    httpclient = new DefaultHttpClient();
	    httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
	  }
	
	/**
	 * 生成商务关注二维码-请求access_token
	 */
	public static String accessTokenGet(String appid,String appsecret){
		//获取access_token
		String access_token = "";
		String aCTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
		System.out.println("aCTokenUrl"+aCTokenUrl);
		JSONObject jsonObject = CommonUtil.httpsRequest(aCTokenUrl, "GET", null);
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			if(jsonObject.toString().indexOf("access_token")>-1){
				access_token = jsonObject.getString("access_token");
			}
		}
		return access_token;
	}
	
	/**
	 * 生成商务关注二维码-请求ticket
	 */
	public String ticketGet(String access_token){
		String ticket = "";
		String ticketUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token;
		System.out.println("ticketUrl"+ticketUrl);
		JSONObject jsonObject = CommonUtil.httpsRequest(ticketUrl, "GET", null);
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			if(jsonObject.toString().indexOf("ticket")>-1){
				ticket = jsonObject.getString("ticket");
			}
		}
		return ticket;

	}

		
	/**
	 * 生成商务关注二维码-请求返回二维码图片并展示
	 * @throws Exception 
	 */
	public String qRCodeGet(String appid,String appsecret) throws Exception{
		String showQRCodeUrl = "";
		//获取access_token
		String access_token = accessTokenGet(appid,appsecret);
		if(access_token!=null&&!"".equals(access_token)){
			//获取ticket
			String ticket = ticketGet(access_token);
			//二维码跳转地址
			showQRCodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
			System.out.println("showQRCodeUrl"+showQRCodeUrl);
		}else{
			throw new Exception("access_token无效或者失效");
		}
		return showQRCodeUrl;
	}
	
	public static String sendForHttp(String s,String url){
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
		String string = null;
		try {
			httpost.setEntity(new StringEntity(s, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			string = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
	
	/***
	 * 获取随机数
	 * @param digit 随机数位数
	 * @return
	 */
	public static int getRandom(int digit){
		int i =1;
		for (int j = 1; j < digit; j++) {
			i=10*i;
		}
		return (int) (Math.random()*9*i+i);
	}
	
	/**
	 * jssdk jsapi_ticket获取
	 * **/
	public static String jsapiTicketGet(String appid,String appsecret) throws Exception{
		
		CacheManager cacheManager = CacheManager.getIntance("Common");
		
		//获取上一次token获取的时间,如果>=118分钟则重新获取
		Object result = cacheManager.getCache("access_token_time");
		String lastTime = "";
		String access_token = "";
		boolean isGetToken = true;
		if (result != null) {
			lastTime = (String) result;
			//获取时间差
			Long timDiff = DateUtil.getTimeDifferenceMin(lastTime);
			//如果小于118分钟则不获取了
			if(timDiff < 118){
				isGetToken = false;
				access_token = (String)cacheManager.getCache("access_token");
			}
		}
		if(isGetToken){
			//如果没有缓存或者>118分钟则重新获取微信token
			access_token = CommonUtils.accessTokenGet(appid,appsecret);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowDate = df.format(new Date());
			//放入缓存
			cacheManager.putCache("access_token", access_token);
			cacheManager.putCache("access_token_time", nowDate);
		}
		
		//获取上一次jsticket获取的时间,如果>=118分钟则重新获取
		Object jsResult = cacheManager.getCache("jsticket_time");
		String jsLastTime = "";
		String ticket = "";
		boolean isGetjsTicket = true;
		if (jsResult != null) {
			jsLastTime = (String) jsResult;
			//获取时间差
			Long timDiff = DateUtil.getTimeDifferenceMin(jsLastTime);
			//如果小于118分钟则不获取了
			if(timDiff < 118){
				isGetjsTicket = false;
				ticket = (String)cacheManager.getCache("jsticket");
			}
		}
		if(isGetjsTicket){
			//如果没有缓存或者>118分钟则重新获取微信token
			String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
			JSONObject jsonObject = CommonUtil.httpsRequest(ticketUrl, "GET", null);
			if (null != jsonObject) {
				if(jsonObject.toString().indexOf("ticket")>-1){
					ticket = jsonObject.getString("ticket");
				}
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowDate = df.format(new Date());
			//放入缓存
			cacheManager.putCache("jsticket", ticket);
			cacheManager.putCache("jsticket_time", nowDate);
		}
		return ticket;
	}
	
	/**
	 * 获取签名
	 * */
	public static String getSign(Map<String, String> map) throws Exception{
		String sign = null;
		if(map!=null&&map.size()>0){
			TreeMap<String, String> t = new TreeMap<String, String>();
			for (String k : map.keySet()) {
				t.put(k, map.get(k));
			}
			
			StringBuffer sb = new StringBuffer();
			for (String k : t.keySet()) {
				sb.append(k);
				sb.append("=");
				sb.append(t.get(k));
				sb.append("&");
			}
			String str = sb.substring(0, sb.length()-1);
			sign = SHA1Util.hex_sha1(str);
			System.out.println(sign);
		}
		return sign;
	}
	
	/**
	 * 获取ip地址
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if("".equals(ip) && ip !=null && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if("".equals(ip) && ip !=null && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}
	
	/** * 获取指定日期是星期几
	  * 参数为null时表示获取当前日期是星期几
	  * @param date
	  * @return
	*/
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
}
