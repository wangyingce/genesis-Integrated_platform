package com.ysyl.common.log.service.facade;

import com.ysyl.common.schema.model.YyLog;



public interface LogService{
	
	/**
	 * 保存日志表
	 * photoGraphVo 日志对象
	 */
	public void saveLog(YyLog yyLog) throws Exception;
	
	
}
