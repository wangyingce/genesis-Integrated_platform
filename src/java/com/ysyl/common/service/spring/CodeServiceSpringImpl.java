package com.ysyl.common.service.spring;

import java.util.List;

import ins.framework.cache.CacheManager;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.schema.model.YyDcode;
import com.ysyl.common.schema.model.Yydconfig;
import com.ysyl.common.service.facade.CodeService;

/**
 * 基础代码服务类对应yydcode
 * @author lenovo
 *
 */
public class CodeServiceSpringImpl extends GenericDaoHibernate<YyDcode, String> implements CodeService {
	private static CacheManager cacheManager = CacheManager.getIntance("Code");
	/**
	 * 翻译wxCode
	 * @param codeType 代码类型
	 * @param codeCode 内部代码
	 */
	public String translateWxCode(String codeType,String codeCode) throws Exception{
		String wxCode = "";
		Object result = cacheManager.getCache(codeType+codeCode);
		if (result != null) {
			wxCode = (String)result;
		}else{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.codeType", codeType);
			queryRule.addEqual("id.codeCode", codeCode);
			queryRule.addEqual("validStatus", "1");
			YyDcode yyDcode = findUnique(queryRule);
			wxCode = yyDcode.getWxCodeCode();
			//放入缓存
			cacheManager.putCache(codeType+codeCode, wxCode);
		}
		return wxCode;
	}
	
	/**
	 * 翻译wxCode
	 * @param codeType 代码类型
	 * @param codeCode 内部代码
	 */
	public String translateName(String codeType, String codeCode) throws Exception {
		String name = "";
		Object result = cacheManager.getCache(codeType+codeCode);
		if (result != null) {
			name = (String)result;
		}else{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.codeType", codeType);
			queryRule.addEqual("id.codeCode", codeCode);
			queryRule.addEqual("validStatus", "1");
			YyDcode yyDcode = findUnique(queryRule);
			name = yyDcode.getCodeCName();
			//放入缓存
			cacheManager.putCache(codeType+codeCode, name);
		}
		return name;
	}
	
	/**
	 * 根据codetype获取基础代码
	 * @param codeType 代码类型
	 */
	@SuppressWarnings("unchecked")
	public List<YyDcode> getYyDcodesByCodeType(String codeType)  throws Exception{
		List<YyDcode> yyDcodes= null;
		List<Object> results = (List)cacheManager.getCache(codeType);
		if (results != null) {
			yyDcodes = (List)results;
		}else{
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("id.codeType", codeType);
			queryRule.addEqual("validStatus", "1");
			yyDcodes = find(queryRule);
			//放入缓存
			cacheManager.putCache(codeType, yyDcodes);
		}
		return yyDcodes;
	}
	
	public Yydconfig getYydconfig(String type, String configCode) {
		String key = cacheManager.generateCacheKey("getYydconfig", type,configCode);
		Object result = cacheManager.getCache(key);
		if(result!=null){
			return (Yydconfig)result;
		}else{
			QueryRule qr = QueryRule.getInstance();
			qr.addEqual("id.type", type);
			qr.addEqual("id.configCode", configCode);
			Yydconfig config = super.findUnique(Yydconfig.class, qr);
			cacheManager.putCache(key, config);
			return config;
		}
	}
}
