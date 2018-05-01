package com.ysyl.common.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyWaresClass;

public interface YyWaresClassService {
	public List<YyWaresClass> getWaresClassByStoreId(String storeId) throws Exception;
}
