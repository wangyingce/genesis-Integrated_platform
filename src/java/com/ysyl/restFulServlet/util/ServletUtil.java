package com.ysyl.restFulServlet.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ysyl.common.util.JsonUtil;

/**
 * servlet工具类
 * @author ysyl
 */
public class ServletUtil {
	private static Logger logger = Logger.getLogger(ServletUtil.class);
	/**
	 * 根据request解析Xml
	 * @param request请求
	 * @return 解析完成的xml
	 */
	public static String postXmlByRequest(HttpServletRequest request){
		BufferedInputStream input = null;
		try {
			//微信传送默认是使用UTF-8，所以要用UTF-8解析
			List ioList = IOUtils.readLines(request.getInputStream(),"UTF-8");
			InputStream in = request.getInputStream();
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			byte[] bufferRead = new byte[1024];
			input = new BufferedInputStream(in);
			int count = 0;
			while ((count = input.read(bufferRead)) != -1) {
				byteOutput.write(bufferRead, 0, count);
			}
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ioList.size(); ++i) {
				sb.append(ioList.get(i));
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		}
	}
	
	/**
	 * 根据入参返回参数
	 * @param response请求
	 * @param responseMessage 返回的String 或者是xml 或者是json
	 * @return 解析完成的xml
	 */
	public static void backResponse(HttpServletResponse response,String responseMessage) throws Exception{
//		返回空消息
		byte[] byteMessages = responseMessage.getBytes();
		
		try {
			Object outS = response.getOutputStream();
			response.setContentType("text/html; charset=UTF-8");
			((OutputStream) outS).write(byteMessages);
			((OutputStream) outS).flush();
			((OutputStream) outS).close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
     * post方式请求服务器(https协议)
     * 
     * @param url
     *            请求地址
     * @param content
     *            参数
     * @param charset
     *            编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static byte[] post(String url, String content, String charset) throws Exception {
    	logger.debug(url);
    	logger.debug(content);
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                new java.security.SecureRandom());
        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.setDoOutput(true);
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        // 刷新、关闭
        out.flush();
        out.close();
        InputStream is = conn.getInputStream();
        if (is != null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            logger.debug(new String(outStream.toByteArray(), "UTF-8"));
            return outStream.toByteArray();
        }
        return null;
    }
    
    /**
	 * 根据入参返回ajax请求的json
	 * @param response请求
	 * @param obj 可以是string，oject，list
	 */
	public static void responseAjaxJson(HttpServletResponse response,Object obj) throws Exception{
		response.reset();
		response.setContentType("text/html;charset=utf-8");  
		PrintWriter pw = response.getWriter();  
		String json = JsonUtil.toJson(obj);  
		pw.print(json);
		pw.flush();  
		pw.close();  
	}
    
    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
 
    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
 
    public static void main(String args[])throws Exception{
    	String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=P9oikd9hWTwcB2o0a8RgGmHpD2EQAw43aTfIabc5Z7RQgTZPpuKFrCi5Jzu_tAQjjGUrD7qkbyIC7i_brDgjLJcYNRRD1avzfJ_BBERf4c_ECJscY3giwJP34jd0YAI9KHXhADAURH";
    	String content = "{\"touser\":\"oVvpDwviNyWzlM_4_b2I3hfjOxkQ\",\"template_id\":\"wXYVAUnXVPRT5K_G1o8AyrLHnpin1h2ec4wIXqOdazU\",\"url\":\"http://www.youshangyouliang.com/cm/cf.do?inputId\u003dAVgKsNwM\u0026type\u003dwo\",\"data\":{\"first\":{\"value\":\"您好，您已支付成功！\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"兰州牛肉拉面中测试店\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"￥0.01\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"2016-08-25 13:03:25\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"AVgKsNwM\",\"color\":\"#173177\"},\"remark\":{\"value\":\"欢迎再次使用！\",\"color\":\"#173177\"}}}";
    	String charset="utf-8";
    	byte[] s = post(url, content, charset);
    	
    	System.out.println(new String(s, "UTF-8"));
    }
}
