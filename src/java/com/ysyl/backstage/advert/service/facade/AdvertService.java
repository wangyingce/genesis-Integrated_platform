package com.ysyl.backstage.advert.service.facade;

import ins.framework.common.Page;

import java.util.Date;
import java.util.List;

import com.ysyl.backstage.product.vo.GenusVo;
import com.ysyl.backstage.schema.model.YybsAdvert;
import com.ysyl.backstage.schema.model.YybsProduct;

/**
 * 商品功能接口
 * @author wyc
 *
 */
public interface AdvertService{

	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部(分页在前台)
	 * @param advertName
	 * @param date
	 * @return
	 */
	List<YybsAdvert> findAdvertByNameOrTime(String rqsttype ,String advertName, Long brandId,Date date);

	
	
}
