package com.ysyl.backstage.brand.service.facade;

import java.util.Date;
import java.util.List;
import com.ysyl.backstage.schema.model.YybsBrand;

/**
 * 商品功能接口
 * @author wyc
 *
 */
public interface BrandService{

//	/**
//	 * 保存商品schema
//	 * @param prd
//	 */
//	void saveProduct(YybsProduct prd);
//
//	/**
//	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部
//	 * @param productName
//	 * @param date
//	 * @return
//	 */
//	Page findProductsByNameOrTime(String productName, Date date,Integer pageStart,Integer pageQueryNo);
	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部(分页在前台)
	 * @param productName
	 * @param date
	 * @return
	 */
	List<YybsBrand> findBrandByNameOrTime(String productName, Date date,Integer pageStart,Integer pageQueryNo);

	/**
	 * 查询品牌列表
	 * @param brandName
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	List<YybsBrand> findBdByNameOrTime(Long brandid , String brandName, String inputtime, String nation) throws Exception;

	/**
	 * 保存品牌信息
	 * @param bd
	 * @return 
	 */
	Long saveBrand(YybsBrand bd);

	/**
	 * 根据id查找品牌信息
	 * @param brandId
	 * @return
	 */
	YybsBrand findBdById(Long brandId);
	
}
