package com.ysyl.backstage.brand.service.spring;
import ins.framework.common.HqlQueryRule;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ysyl.backstage.brand.service.facade.BrandService;
import com.ysyl.backstage.schema.model.YybsBrand;
import com.ysyl.backstage.schema.model.YybsProduct;

public class BrandServiceSpringImpl extends GenericDaoHibernate<YybsBrand, String> implements BrandService {

	public List<YybsBrand> findBrandByNameOrTime(String productName, Date date,Integer pageStart,Integer pageQueryNo) {
		HqlQueryRule hqlQueryRule = new HqlQueryRule();
//		hqlQueryRule.addGreaterEqual("merchant.inputTime", Long.parseLong(date));
		StringBuffer hql = new StringBuffer();
		hql.append("select p from YybsBrand p where p.brandName like '%'");
		Page page = super.findByHql(hql.toString(), pageStart, pageQueryNo,new Object[]{});
		logger.debug(hql.toString());
		List<YybsProduct> ps = new ArrayList<YybsProduct>(0);
		return null;
	}

	/**
	 * 查询品牌列表
	 * @param brandName
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	public List<YybsBrand> findBdByNameOrTime(Long brandid , String brandName, String inputtime, String nation) throws Exception {
		QueryRule qr = QueryRule.getInstance();
		if(brandid!=null&&!"".equals(brandid)){
			qr.addEqual("brandId", new Long(brandid));
		}
		if(brandName!=null&&!"".equals(brandName)){
			qr.addLike("brandName", brandName+"%");
		}
		if(inputtime!=null&&!"".equals(inputtime)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			Date ipttm = sdf.parse(inputtime);
			qr.addGreaterThan("inputtime", ipttm);
		}
		if(nation!=null&&!"".equals(nation)){
			qr.addEqual("nation", nation);
		}
		List<YybsBrand> list  = super.find(YybsBrand.class,qr);
		return list;
	}

	/**
	 * 保存品牌信息
	 * @param bd
	 */
	public Long saveBrand(YybsBrand bd) {
		if(bd.getBrandId()!=null&&!"".equals(bd.getBrandId())){
			super.update(bd);
		}else{
			super.save(bd);
		}
		return bd.getBrandId();
	}
	
	/**
	 * 根据id查找品牌信息
	 * @param brandId
	 * @return
	 */
	public YybsBrand findBdById(Long brandId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("brandId", brandId);
		YybsBrand brand  = super.findUnique(qr);
		return brand;
	}

	
}
