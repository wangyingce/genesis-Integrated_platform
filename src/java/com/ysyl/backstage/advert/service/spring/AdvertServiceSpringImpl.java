package com.ysyl.backstage.advert.service.spring;
import ins.framework.common.HqlQueryRule;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ysyl.backstage.advert.service.facade.AdvertService;
import com.ysyl.backstage.product.service.facade.ProductService;
import com.ysyl.backstage.product.vo.GenusVo;
import com.ysyl.backstage.schema.model.YybsAdvert;
import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.backstage.schema.model.YybsProduct;

public class AdvertServiceSpringImpl extends GenericDaoHibernate<YybsAdvert, String> implements AdvertService {

	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部(分页在前台)
	 * @param productName
	 * @param date
	 * @return
	 */
	public List<YybsAdvert> findAdvertByNameOrTime(String rqsttype,String advertName,Long brandId, Date date) {
//		HqlQueryRule hqlQueryRule = new HqlQueryRule();
//		hqlQueryRule.addGreaterEqual("p.inputTime", Long.parseLong(date));
		StringBuffer hql = new StringBuffer();
		hql.append("select p from YybsAdvert p where p.advertName like '%'");
		if(rqsttype!=null&&!"".equals(rqsttype)&&("modband".equals(rqsttype)||"modband"==rqsttype)){
			if(brandId!=null&&!"".equals(brandId)){
				hql.append(" and p.band='"+brandId+"'");
			}
		}
		if(rqsttype!=null&&!"".equals(rqsttype)&&("addband".equals(rqsttype)||"addband"==rqsttype)){
			hql.append(" and p.band is null ");
		}
		List<YybsAdvert> list  = super.findByHql(hql.toString());
		return list;
	}

}
