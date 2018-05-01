package com.ysyl.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 时间格式转换
	 * @param date
	 * @param kind 0 (yyyy-MM-dd)    1 (HH:mm:ss)   2 ...(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static Date stringToDate(String date, int kind){
		if(date!=null&&date.trim().length()!=0){
			String k = null;
			if(kind==0){
				k = "yyyy-MM-dd";
			}else if(kind==9){
				k = "yyyy-MM-dd HH:mm";
			}else if(kind==1){
				k = "HH:mm:ss";
			}else if(kind==2){
				k = "yyyyMMddHHmmss";
			}else if(kind==3){
				k = "yyyyMMdd";
			}else {
				k = "yyyy-MM-dd HH:mm:ss";
			}
			Date d = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat(k);
			try {
				d = dateFormat.parse(date);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return d;
		}else{
			return null;
		}
	}
	
	/**
	 * 时间格式转换
	 * type 1 yyyy-MM-dd   2 yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date,int type){
		if(date!=null){
			SimpleDateFormat dateFormat = null;
			if(type==1){
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			}else if(type==2){
				dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			}else if(type==3){
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else if(type==4){
				dateFormat = new SimpleDateFormat("yyyyMMdd");
			}
			return dateFormat.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 时间格式转换
	 * @param date
	 * @return
	 */
	public static String dateHourToString(Date date){
		if(date!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			return dateFormat.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 获取和之前存储的某个时间比时间相差几分钟
	 * @param afterDate 之前存储的某个时间
	 * @return
	 * @throws Exception 
	 */
	public static Long getTimeDifferenceMin(String lastTime) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		Date lsDate = df.parse(lastTime);
		long l=nowDate.getTime()-lsDate.getTime();
		long min=((l/(60*1000)));
		return min;
//		long day=l/(24*60*60*1000);
//		long hour=(l/(60*60*1000)-day*24);
//		long min=((l/(60*1000))-day*24*60-hour*60);
//		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
//		System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
	}
	
}
