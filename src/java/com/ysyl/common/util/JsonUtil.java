package com.ysyl.common.util;  
  
import java.lang.reflect.Type;  
import com.google.gson.Gson;  
import com.google.gson.GsonBuilder;
/** 
 * Java对象和JSON字符串相互转化工具类 
 * @author penghuaiyi 
 * @date 2013-08-10 
 */  
public final class JsonUtil {  
      
    private JsonUtil(){}  
      
    /** 
     * 对象转换成json字符串 
     * @param obj  
     * @return  
     */  
    public static String toJson(Object obj) {  
    	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);  
    }  
  
    /** 
     * json字符串转成对象 
     * @param str   
     * @param type 
     * @return  
     */  
    public static <T> T fromJson(String str, Type type) {  
        Gson gson = new Gson();  
        return gson.fromJson(str, type);  
    }  
  
    /** 
     * json字符串转成对象 
     * @param str   
     * @param type  
     * @return  
     */  
    public static <T> T fromJson(String str, Class<T> type) {  
        Gson gson = new Gson();  
        return gson.fromJson(str, type);  
    }  
  
} 