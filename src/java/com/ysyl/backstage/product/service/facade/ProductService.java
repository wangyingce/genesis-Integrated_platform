package com.ysyl.backstage.product.service.facade;

import ins.framework.common.Page;

import java.util.Date;
import java.util.List;

import com.ysyl.backstage.product.vo.GenusVo;
import com.ysyl.backstage.schema.model.YybsProduct;

/**
 * 商品功能接口
 * @author wyc
 *
 */
public interface ProductService{

	/**
	 * 保存商品schema
	 * @param prd
	 */
	void saveProduct(YybsProduct prd);

	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部
	 * @param productName
	 * @param date
	 * @return
	 */
	Page findProductsByNameOrTime(String productName, Date date,Integer pageStart,Integer pageQueryNo);
	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部(分页在前台)
	 * @param productName
	 * @param date
	 * @return
	 */
	List<YybsProduct> findProdByNameOrTime(String rqsttype ,String productName, Long brandId,Date date);

	/**
	 * 根据id返回商品列表
	 * @param prodids
	 * @return
	 */
	List<YybsProduct> findProdByIds(String prodids);

	/**
	 * 批量保存或更新商品信息
	 * @param prds
	 */
	void saveAllProduct(List<YybsProduct> prds);

	/**
	 * 查询找品牌下的产品
	 * @param brandId
	 * @return
	 */
	List<YybsProduct> findProdByBrandId(Long brandId,Long genusId);

	/**
	 * 查找所有分类
	 * @return
	 */
	GenusVo findAllGenus();

	/**
	 * 查找商品信息
	 * @param productId
	 * @return
	 */
	YybsProduct findProdById(Long productId);

	
	
}
