package com.ysyl.weixin.pay.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

public class SignUtil {
	
	public static String getSign(SortedMap<String, String> map,String partnerkey){
		StringBuffer sb = new StringBuffer();
		Set es = map.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + partnerkey);
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase();
		return sign;
	}
	
	
	
    // 与接口配置信息中的Token要一致
    private static String token = "greenfrogcostume";
 
    public static void main(String a[]){
    	System.out.println(SignUtil.getRandom12());
    }
    
    public static String getRandom12(){
    	int a = SignUtil.getRandom(10);
    	int b = SignUtil.getRandom(2);
    	return String.valueOf(a)+b;
    }
    /**
	* 得到n位长度的随机数
	* @param n 随机数的长度
	* @return 返回 n位的随机整数
	*/
	public static int getRandom(int n){
		int temp = 0;
		int min = (int) Math.pow(10, n-1);
		int max = (int) Math.pow(10, n);
		Random rand = new Random();
	
		while(true){
			temp = rand.nextInt(max);
			if(temp >= min)
			break;
		}
	
		return temp;
	}
    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp,
                                         String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
 
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
 
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }
 
    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    public static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
 
    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
 
        String s = new String(tempArr);
        return s;
    }
}
