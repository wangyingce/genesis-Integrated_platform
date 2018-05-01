package com.ysyl.common.util;  
  
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
  
public class XstreamUtil {  
	
	public static XStream xstream = new XStream(); 
	
    @SuppressWarnings("unchecked")
	public static <T> T XMLToVo(String xml, Class<T> clazz)  
            throws IOException {
        xstream.processAnnotations(clazz);
        //忽略未知元素，如果有xml有变直接忽略
        xstream.ignoreUnknownElements();
        return (T) xstream.fromXML(xml);  
    }  
}  