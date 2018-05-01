package com.ysyl.backstage.product.service.spring;
import ins.framework.common.HqlQueryRule;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ysyl.backstage.product.service.facade.ProductService;
import com.ysyl.backstage.product.vo.GenusVo;
import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.backstage.schema.model.YybsProduct;

public class ProductServiceSpringImpl extends GenericDaoHibernate<YybsProduct, String> implements ProductService {
	/**
	 * 保存商品
	 * @param prd 商品表
	 */
	public void saveProduct(YybsProduct prd) {
		super.save(prd);
	}

	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部
	 * @param productName
	 * @param date
	 * @return
	 */
	public Page findProductsByNameOrTime(String productName, Date date,Integer pageStart,Integer pageQueryNo) {
		// TODO Auto-generated method stub
		HqlQueryRule hqlQueryRule = new HqlQueryRule();
//		hqlQueryRule.addGreaterEqual("merchant.inputTime", Long.parseLong(date));
		StringBuffer hql = new StringBuffer();
		hql.append("select p from YybsProduct p where p.productName like '%'");
		Page page = super.findByHql(hql.toString(), pageStart, pageQueryNo,new Object[]{});
		logger.debug(hql.toString());
		List<YybsProduct> ps = new ArrayList<YybsProduct>(0);
		if(page!=null){
			List<YybsProduct> list = page.getResult();
			if(list.size()>0){
				for (YybsProduct prod : list) {
					YybsProduct mctVo = new YybsProduct();
					if(prod.getValidstatus()=="1"||"1".equals(prod.getValidstatus())){
						prod.setValidstatus("有效");
					}else{
						prod.setValidstatus("无效");
					}
					DataUtils.copySimpleObject(prod, mctVo);
//					Date d = DateUtil.stringToDate(String.valueOf(merchant.getInputTime()), 2);
//					mctVo.setInputTime(DateUtil.dateToString(d, 3));
					ps.add(mctVo);
				}
			}
			
		}
		return new Page(pageStart, page.getTotalCount(), pageQueryNo, ps);
	}

	/**
	 * 根据商品名称或者时间查询商品列表，如果参数为空，查询全部(分页在前台)
	 * @param productName
	 * @param date
	 * @return
	 */
	public List<YybsProduct> findProdByNameOrTime(String rqsttype,String productName,Long brandId, Date date) {
//		HqlQueryRule hqlQueryRule = new HqlQueryRule();
//		hqlQueryRule.addGreaterEqual("p.inputTime", Long.parseLong(date));
		StringBuffer hql = new StringBuffer();
		hql.append("select p from YybsProduct p where p.validstatus='1' and p.productName like '%'");
		if(rqsttype!=null&&!"".equals(rqsttype)&&("modband".equals(rqsttype)||"modband"==rqsttype)){
			if(brandId!=null&&!"".equals(brandId)){
				hql.append(" and p.band='"+brandId+"'");
			}
		}
		if(rqsttype!=null&&!"".equals(rqsttype)&&("addband".equals(rqsttype)||"addband"==rqsttype)){
			hql.append(" and p.band is null ");
		}
		List<YybsProduct> list  = super.findByHql(hql.toString());
		return list;
	}

	/**
	 * 根据id返回商品列表
	 * @param prodids
	 * @return
	 */
	public List<YybsProduct> findProdByIds(String prodids) {
		StringBuffer hql = new StringBuffer();
		hql.append("select p from YybsProduct p where p.productId in ("+prodids+")");
		List<YybsProduct> list  = super.findByHql(hql.toString());
		return list;
	}

	/**
	 * 批量保存或更新商品信息
	 * @param prds
	 */
	public void saveAllProduct(List<YybsProduct> prds) {
		super.saveAll(prds);		
	}

	/**
	 * 查询找品牌下的产品
	 * @param brandId
	 * @return
	 */
	public List<YybsProduct> findProdByBrandId(Long brandId , Long genusId) {
//		QueryRule qr = QueryRule.getInstance();
//		qr.addEqual("band", brandId+"");
//		if(genusId!=null&&!"".equals(genusId)){
//			genusId = new Long(11);
//			qr.addLike("genusId", genusId);
//		}
//		List<YybsProduct> prods  = super.find(YybsProduct.class, qr);
		String sql = "from YybsProduct t where t.band="+brandId+"";
		if(genusId!=null&&!"".equals(genusId)){
			String genid = (genusId+"").replace("0", "");
			sql = sql + " and t.genusId like '"+genid+"%'";
		}
		List<YybsProduct> prods  = super.findByHql(sql);
		return prods;
	}

	public GenusVo findAllGenus() {
		QueryRule qr = QueryRule.getInstance();
		List<YybsGenus> genuses  = super.find(YybsGenus.class, qr);
		GenusVo gindex = new GenusVo();
		/**第一层*/
		if(genuses!=null&&genuses.size()>0){
			List<YybsGenus> delgens = new ArrayList<YybsGenus>(0);
			for(YybsGenus gen : genuses){
				if(gen.getGenusId().equals(gen.getUppergenusId())){
					DataUtils.copySimpleObject(gen, gindex);
					delgens.add(gen);
				}
			}
			//清理元素
			genuses.removeAll(delgens);
			genuses=takeOffNullObject(genuses);
			/**第二层*/
			if(gindex!=null&&!"".equals(gindex)){
				List<GenusVo> gfolr = new ArrayList<GenusVo>(0);
				delgens = new ArrayList<YybsGenus>(0);
				for(YybsGenus gen : genuses){
					GenusVo folr = new GenusVo();
					if(gindex.getGenusId().equals(gen.getUppergenusId())){
						DataUtils.copySimpleObject(gen, folr);
						gfolr.add(folr);
						delgens.add(gen);
					}
				}
				//清理元素
				genuses.removeAll(delgens);
				genuses=takeOffNullObject(genuses);
				gindex.setChildren(gfolr);
				/**第三层*/
				if(gindex.getChildren()!=null&&gindex.getChildren().size()>0){
					for(GenusVo flor : gindex.getChildren()){
						List<GenusVo> gnodes = new ArrayList<GenusVo>(0);
						for(YybsGenus gen : genuses){
							if(flor.getGenusId().equals(gen.getUppergenusId())){
								GenusVo node = new GenusVo();
								DataUtils.copySimpleObject(gen, node);
								gnodes.add(node);
							}
						}
						flor.setChildren(gnodes);
					}
				}
			}
		}
		return gindex;
	}

//	private YybsGenus createGenusOrder(List<YybsGenus> genuses,YybsGenus genus) {
//		for(YybsGenus gen : genuses){
//			/**头结点*/
//			if(genus!=null&&!"".equals(genus)&&gen.getGenusId().equals(gen.getUppergenusId())){
//				DataUtils.copySimpleObject(gen, genus);
//				genuses.remove(gen);
//				createGenusOrder(genuses,genus);
//			}else if(){
//				
//			}
//		}
//		return null;
//	}
	@SuppressWarnings("unchecked")
	public List takeOffNullObject(List list) {
		List newList = new ArrayList();
		if (list != null) {
			for (Object object : list) {
				if (object != null) {
					newList.add(object);
				}
			}
		}
		return newList;
	}

	/**
	 * 查找商品信息
	 * @param productId
	 * @return
	 */
	public YybsProduct findProdById(Long productId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("productId", productId);
		YybsProduct prod  = super.findUnique(qr);
		return prod;
	}
}
