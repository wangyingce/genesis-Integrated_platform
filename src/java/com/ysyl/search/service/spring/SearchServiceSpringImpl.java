package com.ysyl.search.service.spring;

import ins.framework.common.ServiceFactory;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.exception.BusinessException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.sinosoft.sinoiaci.util.UUID;
import com.ysyl.common.CodeConst;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.util.DateUtil;
import com.ysyl.search.InterfLogThread;
import com.ysyl.search.MyHttpClient;
import com.ysyl.search.service.facade.SearchService;
import com.ysyl.search.vo.Doc;
import com.ysyl.search.vo.Must;
import com.ysyl.search.vo.Must_not;
import com.ysyl.search.vo.Query_string;
import com.ysyl.search.vo.Range;
import com.ysyl.search.vo.ResultVo;
import com.ysyl.search.vo.SearchErrorMesaageVo;
import com.ysyl.search.vo.SearchInterfMesaageVo;
import com.ysyl.search.vo.SearchQueryVo;
import com.ysyl.search.vo.SearchVo;
import com.ysyl.search.vo.Should;
import com.ysyl.search.vo.Sort;
import com.ysyl.search.vo.Timeslot;
import com.ysyl.search.vo.Wildcard;


public class SearchServiceSpringImpl extends GenericDaoHibernate implements SearchService {
	

	public String getSearchUrl() {
		try {
			CommonService commonService = (CommonService) applicationContext.getBean("commonService");
			String url = commonService.getIpServiceWithTokenByCode(CodeConst.ServerCode.searchUrl, "");
			return url;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}


	public String searchQuery(String json){
		String url = this.getSearchUrlByType("", "query","");
		try{
			String reJson = this.getURLContent(url,json,"");
			return reJson;
		}catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	
	//增加索引
	public String addIndex(SearchVo vo){
		try {
			String url = this.getSearchUrlByType(vo.get_id(), "add",vo.getIndexType());
			vo.setIndexType(null);
			Gson g = new Gson();
			String json = g.toJson(vo);
			System.out.println(json);
			String rep = this.getURLContent(url, json,"");
			return rep;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}
	
//	更新索引
	public String updateIndex(SearchVo vo){
		try {
			String url = this.getSearchUrlByType(vo.get_id(), "update",vo.getIndexType());
			Gson g = new Gson();
			Doc doc = new Doc();
			doc.setDoc(vo);
			String json = g.toJson(doc);
			System.out.println(json);
			String rep = this.getURLContent(url, json,"");
			return rep;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}
	
//	删除索引
	public String deleteIndex(SearchVo vo){
		if(vo.get_id()==null||"".equals(vo.get_id())){
			throw new BusinessException("删除业务号不能为空!!",false);
		}
		try {
			String url = this.getSearchUrlByType(vo.get_id(), "delete",vo.getIndexType());
			String rep = this.getURLContent(url, "","delete");
			return rep;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.toString();
		}
	}
	
	/** 获取Minimum_should_match数,使用shoud条件必须调用此方法 */
	public int getMininmumShouldMatch(List<Should> shouldList){
		int c = 0;
		if(shouldList!=null&&shouldList.size()>0){
			Set<String> set = new HashSet<String>();
			for (Object o : shouldList) {
				Should s= (Should)o;
				if(s.getQuery_string()!=null){
					set.add(s.getQuery_string().getDefault_field());
				}
				if(s.getWildcard()!=null){
					if(s.getWildcard().getApliName()!=null&&!"".equals(s.getWildcard().getApliName())){
						set.add("apliName");
					}
					if(s.getWildcard().getBrandName()!=null&&!"".equals(s.getWildcard().getBrandName())){
						set.add("brandName");
					}
					if(s.getWildcard().getInsuredName()!=null&&!"".equals(s.getWildcard().getInsuredName())){
						set.add("insuredName");
					}
					if(s.getWildcard().getLicenseNo()!=null&&!"".equals(s.getWildcard().getLicenseNo())){
						set.add("licenseNo");
					}
				}
			}
			c=set.size();
		}
		return c;
	}
	
	//解析json
	public ResultVo paseJson(String json){
		JSONObject  dataJson=new JSONObject();
		dataJson = dataJson.getJSONObject(json);
		JSONObject  hit=dataJson.getJSONObject("hits");
		String  total=hit.getString("total");
		JSONArray hits = hit.getJSONArray("hits");
		ResultVo rv = new ResultVo();
		List<ResultVo> rvList = new ArrayList<ResultVo>();
		if(hits.size()!=0){
			for(int i=0;i<hits.size();i++){
				JSONObject h=hits.getJSONObject(i);
				JSONObject jr = h.getJSONObject("_source");
				ResultVo r = new ResultVo();
				r.setProposalNo(jr.getString("proposalno"));
				r.setPolicyNo(jr.getString("policyno"));
				r.setLicenseNo(jr.getString("licenseno"));
				r.setFrameNo(jr.getString("frameno"));
				r.setEngineNo(jr.getString("engineno"));
				r.setInsuredName(jr.getString("insuredname"));
				r.setComCode(jr.getString("comcode"));
				r.setStartDate(jr.getString("startdate"));
				r.setEndDate(jr.getString("enddate"));
				rvList.add(r);
			}
		}
		rv.setRvList(rvList);
		rv.setTotalCount(Integer.parseInt(total));
		rv.setEveryPage(10);
		
		return rv;
	}
	
	public String getSearchUrlByType(String id,String type,String indexType){
		String url = this.getSearchUrl();
		String urlString = "";
		
		if("query".equals(type)){
			urlString = url+"/_search?pretty";
		}else if("add".equals(type)){
			urlString = url+"/"+indexType+"/"+id+"?pretty";
		}else if("update".equals(type)){
			urlString = url+"/"+indexType+"/"+id+"/_update?pretty";
		}else if("delete".equals(type)){
			urlString = url+"/"+indexType+"/"+id+"?pretty";
		}
		return urlString;
	}
	
	/**
	 * 程序中访问http数据接口
	 */
	public String getURLContent(String urlStr,String json,String type) throws Exception{
		HttpResponse response;
		if("delete".equals(type)){
			HttpDelete httpDelete = new HttpDelete(urlStr);
			HttpClient client = MyHttpClient.getSaveHttpClient();
			response = client.execute(httpDelete);
		}else{
			HttpPost httpPost = new HttpPost(urlStr);
			StringEntity entity = new StringEntity(json, HTTP.UTF_8);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			HttpClient client = MyHttpClient.getSaveHttpClient();
			response = client.execute(httpPost);
		}
          
        HttpEntity entity1 = response.getEntity();  
        String respContent = EntityUtils.toString(entity1 , HTTP.UTF_8).trim();  
	    return respContent;
	}

//	带有汉字的条件使用此方法
	public void setChineseConMust(String conName,String conValue, List<Must> mustList){
		if(conValue!=null&&!"".equals(conValue)){
			Wildcard wildcard = new Wildcard();
			invokeSet(wildcard, conName, this.trimAndFuzzy(conValue));
			Must must = new Must();
			must.setWildcard(wildcard);
			mustList.add(must);
		}
	}
	
//	不带有汉字的条件使用此方法
	public void setNoChineseConMust(String conName,String conValue,List<Must> mustList){
		if(conValue!=null&&!"".equals(conValue)){
			Query_string query_string = new Query_string();
			query_string.setDefault_field(conName);
			query_string.setQuery(this.trimAndFuzzy(conValue));
			Must must = new Must();
			must.setQuery_string(query_string);
			mustList.add(must);
		}
	}
	
//	不带有汉字的条件或的使用此方法 带模糊查询
	public void setNoChineseConShoud(String conName,String conValue, List<Should> shouldList){
		if(conValue!=null&&!"".equals(conValue)){
			Query_string query_string = new Query_string();
			query_string.setDefault_field(conName);
			query_string.setQuery(this.trimAndFuzzy(conValue));
			Should should = new Should();
			should.setQuery_string(query_string);
			shouldList.add(should);
		}
	}
	
	/** 不带有汉字条件 */
	public void setNoChineseConMustNot(String conName,String conValue, List<Must_not> mustNotList){
		if(conValue!=null&&!"".equals(conValue)){
			Query_string query_string = new Query_string();
			query_string.setDefault_field(conName);
			query_string.setQuery(this.trimAndFuzzy(conValue));
			Must_not must_not = new Must_not();
			must_not.setQuery_string(query_string);
			mustNotList.add(must_not);
		}
	}
	
//	带有汉字的条件或的使用此方法 带模糊查询
	public void setChineseConShoud(String conName,String conValue, List<Should> shouldList){
		if(conValue!=null&&!"".equals(conValue)){
			Wildcard wildcard = new Wildcard();
			invokeSet(wildcard, conName, this.trimAndFuzzy(conValue));
			Should should = new Should();
			should.setWildcard(wildcard);
			shouldList.add(should);
		}
	}
	
	public String trimAndFuzzy(String s){
		StringBuffer sb= new StringBuffer();
		sb.append("*");
		sb.append(s.trim());
		sb.append("*");
		
		String regEx = "[' ']+"; // 一个或多个空格  
		Pattern p = Pattern.compile(regEx);  
		Matcher m = p.matcher(sb.toString());
		
		return m.replaceAll("*").trim();
	}
	
	//时间段查询条件使用此方法
	public void setTimeslotCon(String conName,String formValue,String toValue,String sign,List<Must> mustList){
		try {
			Range range = new Range();
			Timeslot timeslot = new Timeslot();
			if(">=".equals(sign)){
				if(formValue!=null&&!"".equals(formValue)){
					SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
					Date date = sdf.parse(formValue);
					timeslot.setFrom(date.getTime()/1000+"");
				}
			}else if ("<=".equals(sign)) {
				if(formValue!=null&&!"".equals(formValue)){
					SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
					Date date = sdf.parse(formValue);
					timeslot.setTo(date.getTime()/1000+"");
				}
			}else if (">".equals(sign)) {
				if(formValue!=null&&!"".equals(formValue)){
					SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
					Date date = sdf.parse(formValue);
					Calendar calendar=new GregorianCalendar(); 
					calendar.setTime(date); 
					calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
					date=calendar.getTime();
					String formValue1 = date.getTime()/1000+"";
					timeslot.setFrom(formValue1);
				}
			}else if ("<".equals(sign)) {
				if(formValue!=null&&!"".equals(formValue)){
					SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
					Date date = sdf.parse(formValue);
					Calendar calendar=new GregorianCalendar(); 
					calendar.setTime(date); 
					calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动 
					date=calendar.getTime();
					String formValue1 = date.getTime()/1000+"";
					timeslot.setTo(formValue1);
				}
			}else if ("=".equals(sign)){
				//修改时间查询条件：查询指定日期  modify by chenguojun
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
				if(formValue!=null&&!"".equals(formValue)){
					Date date = sdf.parse(formValue);
					timeslot.setFrom(date.getTime()/1000+"");
					timeslot.setTo(date.getTime()/1000+"");
				}
			}else{
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
				if(formValue!=null&&!"".equals(formValue)){
					Date date = sdf.parse(formValue);
					timeslot.setFrom(date.getTime()/1000+"");
				}
				if(toValue!=null&&!"".equals(toValue)){
					Date date = sdf.parse(toValue);
					timeslot.setTo(date.getTime()/1000+"");
				}
			}
			invokeSet(range, conName, timeslot);
			Must must = new Must();
			must.setRange(range);
			mustList.add(must);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//排序使用此方法
	public void setOrderCon(String conName,String orderType,List<Sort> sortList){
		try {
			Sort sort = new Sort();
			Timeslot timeslot = new Timeslot();
			timeslot.setOrder(orderType);
			invokeSet(sort, conName, timeslot);
			sortList.add(sort);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**    
	 * java反射bean的get方法    
	 *     
	 * @param objectClass    
	 * @param fieldName    
	 * @return    
	 */       
	@SuppressWarnings("unchecked")       
	public static Method getGetMethod(Class objectClass, String fieldName) {       
	    StringBuffer sb = new StringBuffer();       
	    sb.append("get");       
	    sb.append(fieldName.substring(0, 1).toUpperCase());       
	    sb.append(fieldName.substring(1));       
	    try {       
	        return objectClass.getMethod(sb.toString());       
	    } catch (Exception e) {       
	    }       
	    return null;       
	}       
	  
	       
	  
	/**    
	 * java反射bean的set方法    
	 * @param objectClass    
	 * @param fieldName    
	 * @return    
	 */       
	@SuppressWarnings("unchecked")       
	public static Method getSetMethod(Class objectClass, String fieldName) {       
	    try {       
	        Class[] parameterTypes = new Class[1];       
	        Field field = objectClass.getDeclaredField(fieldName);       
	        parameterTypes[0] = field.getType();       
	        StringBuffer sb = new StringBuffer();       
	        sb.append("set");       
	        sb.append(fieldName.substring(0, 1).toUpperCase());       
	        sb.append(fieldName.substring(1));       
	        Method method = objectClass.getMethod(sb.toString(), parameterTypes);       
	        return method;       
	    } catch (Exception e) {       
	        e.printStackTrace();       
	    }       
	    return null;       
	}       
	  
	       
	  
	/**    
	 
	 * 执行set方法    
	 * @param o执行对象    
	 * @param fieldName属性    
	 * @param value值    
	 */       
	public static void invokeSet(Object o, String fieldName, Object value) {       
	    Method method = getSetMethod(o.getClass(), fieldName);       
	    try {       
	        method.invoke(o, new Object[] { value });       
	    } catch (Exception e) {       
	        e.printStackTrace();       
	    }       
	}

	/** 新增索引 */
	public String addSearChIndex(Map<String, Object> map, String type) {
		String resp = null;
		//错误信息 
		if(CodeConst.SearchType.errorMessage.equals(type)){
			SearchErrorMesaageVo vo = (SearchErrorMesaageVo) map.get(CodeConst.SearchType.errorMessage);
			vo.setIndexType(CodeConst.SearchType.errorMessage);
			resp = this.addIndex(vo);
		}else if(CodeConst.SearchType.interfMessage.equals(type)){
		//接口交互 
			SearchInterfMesaageVo vo = (SearchInterfMesaageVo) map.get(CodeConst.SearchType.interfMessage);
			vo.setIndexType(CodeConst.SearchType.interfMessage);
			resp = this.addIndex(vo);
		}
		
		
		System.out.println(resp);
		return resp;
	}   
	
	
	@SuppressWarnings("unused")
	private void getTitleString(String name,String val,String key,StringBuffer stringB){
		if(val!=null){
			String []keyList;
			if(key.indexOf(" ")>-1){
				keyList = key.split(" ");
			}else{
				keyList=new String[]{key};
			}
			StringBuffer sb = new StringBuffer();
			sb.append(name);
			sb.append(":");
			int c = 0;
			for (String k : keyList) {
				if(val.indexOf(k)>-1){
					c++;
					StringBuffer s = new StringBuffer();
					val = val.replaceAll(k, s.append("<B>").append(k).append("</B>").toString());
				}
			}
			sb.append(val);
			sb.append("&emsp;");
			if(c==keyList.length){
				stringB.append(sb);
			}
		}
		
	}
	
	/** 更新索引数据库索引 */
	public String updateSearChIndex(SearchVo vo) {
		if(vo.get_id()==null||"".equals(vo.get_id())){
			throw new BusinessException("索引ID不能为空",false);
		}
		String resp = this.updateIndex(vo);
		System.out.println(resp);
		return resp;
	}

	/** 保存错误信息 
	 * e不为空时保存e的信息
	 * 否则取message的信息
	 * e与message不能同时为空
	 * */
	public void saveErrorMessage(Exception e,String message,String type) {
		SearchErrorMesaageVo vo = new SearchErrorMesaageVo();
		vo.set_id(UUID.generate());
		String date = DateUtil.dateToString(new Date(), 2);
		vo.setInputDate(Long.parseLong(date));
		if(e!=null){
			String mess = this.getErrorInfoFromException(e);
			vo.setMessage(mess);
		}else if(message!=null&&!"".equals(message)){
			vo.setMessage(message);
		}
		vo.setType(type);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(CodeConst.SearchType.errorMessage, vo);
		this.addSearChIndex(map, CodeConst.SearchType.errorMessage);
	}

	private String getErrorInfoFromException(Exception e) {  
        try {  
            StringWriter sw = new StringWriter();  
            PrintWriter pw = new PrintWriter(sw);  
            e.printStackTrace(pw);  
            return "\r\n" + sw.toString() + "\r\n";  
        } catch (Exception e2) {  
            return "bad getErrorInfoFromException";  
        }  
    } 
	
	/** 
	 * 保存接口交互日志 
	 * type 类型
	 * inputDate 交互日期
	 * requesttext 发送报文
	 * reponsetext 返回报文
	 * */
	public void saveInterfMessage(SearchInterfMesaageVo vo) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(CodeConst.SearchType.interfMessage, vo);
		this.addSearChIndex(map, CodeConst.SearchType.interfMessage);
	}
	
	/** 
	 * 保存接口交互日志 
	 * type 类型
	 * requesttext 发送报文
	 * reponsetext 返回报文
	 * */
	public void saveInterfMessage(String type, String requestText,String reponseText) {
		SearchInterfMesaageVo vo = new SearchInterfMesaageVo();
		vo.set_id(UUID.generate());
		vo.setIndexType(CodeConst.SearchType.interfMessage);
		String date = DateUtil.dateToString(new Date(), 2);
		vo.setInputDate(Long.parseLong(date));
		vo.setRequestText(requestText);
		vo.setReponseText(reponseText);
		vo.setType(type);
		InterfLogThread thread = new InterfLogThread(vo,this);
		thread.start();
	}
}