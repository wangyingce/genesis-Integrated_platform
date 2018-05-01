package com.ysyl.backstage.report.service.facade;

import java.util.List;

import net.sf.json.JSONObject;

import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.backstage.schema.model.YybsReport;

public interface ReportService {

	/**
	 * 更新统计分析表数据
	 * @param reportName
	 * @param nation
	 */
	void saveReportCount(String reportName, String nation);

	/**
	 * 根据reportName查询统计表数据
	 * @param string
	 * @return
	 */
	YybsReport findEleByReportName(String reportName);

	/**
	 * 组装统计数据
	 * @param alljson
	 * @param string
	 * @return
	 */
	JSONObject addJsonData(JSONObject alljson, String string);

	/**
	 * id查询产品类型表
	 * @param genusId
	 * @return
	 */
	YybsGenus findGenusDataById(Long genusId);

	/**
	 * 更新产品类型操作
	 * @param genus
	 * @param operation 
	 */
	void updateGenus(YybsGenus genus, String operation);
	/**
	 * Type查询产品类型表
	 * @param genusId
	 * @return
	 */
	List<YybsGenus> findGenusDataByGenusType(String genusType);
	
}
