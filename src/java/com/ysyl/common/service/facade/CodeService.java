package com.ysyl.common.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyDcode;
import com.ysyl.common.schema.model.Yydconfig;


/**
 * 基础代码服务
 * @author ysyl
 *
 */
public interface CodeService{
	
	/**
	 * 翻译wxCode
	 * @param codeType 代码类型
	 * @param codeCode 内部代码
	 */
	public String translateWxCode(String codeType,String codeCode) throws Exception;
	/**
	 * 翻译wxCode
	 * @param codeType 代码类型
	 * @param codeCode 内部代码
	 */
	public String translateName(String codeType,String codeCode) throws Exception;
	
	/**
	 * 根据codetype获取基础代码
	 * @param codeType 代码类型
	 */
	public List<YyDcode> getYyDcodesByCodeType(String codeType)  throws Exception;
	
	/**
	 * 根据type 及configCode获取配置
	 * */
	public Yydconfig getYydconfig(String type,String configCode);
}
