package com.ysyl.search.service.facade;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.ysyl.search.vo.Must;
import com.ysyl.search.vo.Must_not;
import com.ysyl.search.vo.SearchInterfMesaageVo;
import com.ysyl.search.vo.SearchQueryVo;
import com.ysyl.search.vo.SearchVo;
import com.ysyl.search.vo.Should;
import com.ysyl.search.vo.Sort;

public interface SearchService {
	public String searchQuery(String json);
	
	/** 新增索引 */
	public String addSearChIndex(Map<String,Object> map,String type);
	
	/**带有汉字的条件使用此方法*/
	public void setChineseConMust(String conName,String conValue, List<Must> mustList);
	
	/** 不带有汉字的条件使用此方法 */
	public void setNoChineseConMust(String conName,String conValue,List<Must> mustList);
	
	/** 不带有汉字的条件或的使用此方法 带模糊查询 */
	public void setNoChineseConShoud(String conName,String conValue, List<Should> shouldList);
	
    /** 带有汉字的条件或的使用此方法 带模糊查询 */
	public void setChineseConShoud(String conName,String conValue, List<Should> shouldList);
	
	/** 不带有汉字条件 */
	public void setNoChineseConMustNot(String conName,String conValue, List<Must_not> mustNotList);
	
	/** 时间段查询条件使用此方法 */
	public void setTimeslotCon(String conName,String formValue,String toValue,String sign,List<Must> mustList);
	
	/** 排序使用此方法 */
	public void setOrderCon(String conName,String orderType,List<Sort> sortList);
	
	/** 获取Minimum_should_match数,使用shoud条件必须调用此方法 */
	public int getMininmumShouldMatch(List<Should> shouldList);
	
	/** update索引数据库中的字段值 */
	public String updateSearChIndex(SearchVo vo);
	
	/** 保存错误信息 
	 * e不为空时保存e的信息
	 * 否则取message的信息
	 * e与message不能同时为空
	 * */
	public void saveErrorMessage(Exception e,String message,String type);
	
	/** 
	 * 保存接口交互日志 
	 * type 类型
	 * inputDate 交互日期
	 * requesttext 发送报文
	 * reponsetext 返回报文
	 * */
	public void saveInterfMessage(SearchInterfMesaageVo vo);
	
	/** 
	 * 保存接口交互日志 
	 * type 类型
	 * requesttext 发送报文
	 * reponsetext 返回报文
	 * */
	public void saveInterfMessage(String type, String requestText,String reponseText) ;
}
