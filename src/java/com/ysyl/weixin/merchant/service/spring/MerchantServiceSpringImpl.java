package com.ysyl.weixin.merchant.service.spring;
import ins.framework.common.HqlQueryRule;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ysyl.common.util.DateUtil;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.merchant.vo.MerchantVo;
import com.ysyl.weixin.schema.model.YywxMerchant;
/**
 * 
 * 店铺功能实现
 * @author Scorpio
 * @category Amicus Plato, sed magis amica veritas
 */
public class MerchantServiceSpringImpl extends
		GenericDaoHibernate<YywxMerchant, String> implements MerchantService {
	
	/**
	 * 根据merchantId查询一个店铺信息
	 * @param merchantId：YywxMerchant主键
	 * @return YywxMerchant：店铺信息schema
	 */
	public YywxMerchant queryMerchantByMerchantId(String merchantId){
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("merchantId", merchantId);
		List<YywxMerchant> merchantList=super.find(YywxMerchant.class, queryRule);
		if(merchantList!=null && merchantList.size()>0){
			return merchantList.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 分页查询店铺信息
	 * @param merchantVo:店铺信息vo
	 * @return （merchantVo）page
	 */
	public Page queryMerchantByMerchantVo(MerchantVo merchantVo) {
		HqlQueryRule hqlQueryRule = new HqlQueryRule();
		if(merchantVo.getQueryDate1()!=null&&!"".equals(merchantVo.getQueryDate1())){
			merchantVo.setQueryDate1(merchantVo.getQueryDate1().replace("T", " "));
			Date date = DateUtil.stringToDate(merchantVo.getQueryDate1(), 9);
			String queryDate = DateUtil.dateToString(date, 2); 
			logger.debug(queryDate);
			hqlQueryRule.addGreaterEqual("merchant.inputTime", Long.parseLong(queryDate));
		}
		if(merchantVo.getQueryDate1()!=null&&!"".equals(merchantVo.getQueryDate1())){
			merchantVo.setQueryDate1(merchantVo.getQueryDate1().replace("T", " "));
			Date date = DateUtil.stringToDate(merchantVo.getQueryDate1(), 9);
			String queryDate = DateUtil.dateToString(date, 2); 
			logger.debug(queryDate);
			hqlQueryRule.addGreaterEqual("merchant.inputTime", Long.parseLong(queryDate));
		}
		StringBuffer hql = new StringBuffer();
		hql.append("select merchant from YywxMerchant merchant where merchant.valid='1' and merchant.flag='1' ");
//		String con = hqlQueryRule.getHql();
//		if(con.length()>0){
//			hql.append(" and ");
//		}
//		hql.append(con);
		logger.debug(hql.toString());
		Page page = super.findByHql(hql.toString(), merchantVo.getPageNo(), merchantVo.getPageSize(),new Object[]{});
		List<MerchantVo> merchantVoList = new ArrayList<MerchantVo>();
		if(page!=null){
			List<YywxMerchant> list = page.getResult();
			if(list.size()>0){
				for (YywxMerchant merchant : list) {
					MerchantVo mctVo = new MerchantVo();
						DataUtils.copySimpleObject(merchant, mctVo);
						Date d = DateUtil.stringToDate(String.valueOf(merchant.getInputTime()), 2);
						mctVo.setInputTime(DateUtil.dateToString(d, 3));
						merchantVoList.add(mctVo);
				}
			}
			
		}
		return new Page(merchantVo.getPageNo(), page.getTotalCount(), merchantVo.getPageSize(), merchantVoList);
	}
	
	/**
	 * 根据店铺名称查询一个店铺信息
	 * @param nickName：店铺名称
	 * @return YywxMerchant：店铺信息schema
	 */
	public YywxMerchant queryMerchantByNickName(String nickName) {
				QueryRule queryRule = QueryRule.getInstance();
				queryRule.addEqual("nickName", nickName);
				List<YywxMerchant> merchantList=super.find(YywxMerchant.class, queryRule);
				if(merchantList!=null && merchantList.size()>0){
					return merchantList.get(0);
				}else{
					return null;
				}
	}
	
	/**
	 * 保存或更新店铺
	 * @param merchant
	 */
	public void saveOrUpdateMerchant(YywxMerchant merchant) {
		if(merchant!=null){
				super.save(merchant);
		}
	}
	
	/**
	 * 根据店铺名称查询所有店铺信息
	 * @param nickName:店铺名称
	 * @return List<YywxMerchant> ：店铺信息schema》list
	 */
	public List<YywxMerchant> queryMerchantListByNickName(String nickName) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("nickName", nickName);
		List<YywxMerchant> merchantList=super.find(YywxMerchant.class, queryRule);
		return merchantList;
	}
	
	/**
	 * 根据电话号查询所有店铺信息
	 * @param registermobile
	 * @return List<YywxMerchant>：店铺信息schema》list
	 */
	public List<YywxMerchant> queryMerchantListByReportMobile(
			String registermobile) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("registermobile", registermobile);
		List<YywxMerchant> merchantList=super.find(YywxMerchant.class, queryRule);
		return merchantList;
	}

}