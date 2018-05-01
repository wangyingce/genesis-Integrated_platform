package com.ysyl.common.log.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import com.ysyl.common.log.service.facade.LogService;
import com.ysyl.common.schema.model.YyLog;

public class LogServiceSpringImpl extends GenericDaoHibernate<YyLog, String> implements LogService {

	/**
	 * 保存日志表
	 * photoGraphVo 日志对象
	 */
	public void saveLog(YyLog yyLog) throws Exception {
		// TODO Auto-generated method stub
		this.save(yyLog);
	}
	
	
}
