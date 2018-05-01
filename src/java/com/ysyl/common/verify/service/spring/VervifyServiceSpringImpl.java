package com.ysyl.common.verify.service.spring;

import ins.framework.common.DateTime;
import ins.framework.common.HqlQueryRule;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.Yyverify;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.util.DateUtil;
import com.ysyl.common.verify.VerifyVo;
import com.ysyl.common.verify.service.facade.VerifyService;
import com.ysyl.weixin.common.util.CommonUtils;
import com.ysyl.weixin.merchant.service.facade.MerchantService;
import com.ysyl.weixin.schema.model.YywxMerchant;

public class VervifyServiceSpringImpl extends GenericDaoHibernate<Yyverify, String> implements VerifyService {
	private CommonService commonService;
	private MerchantService merchantService;
	private Logger logger = Logger.getLogger(VervifyServiceSpringImpl.class);
	
	
	
	/***
	 * 根据手机号及类型获取验证码
	 * @param mobile 手机号
	 * @param type 类型 1-商户注册
	 * @return 验证码对象
	 * @throws Exception 
	 */
	public VerifyVo receiveVerify(VerifyVo verifyVo) throws Exception {
		//校验手机号，不允许带,号同时发送多个手机
		if(verifyVo.getPhoneNumber().indexOf(",")>-1){
			verifyVo.setCode(CodeConst.VerifyState.fail);
			verifyVo.setMessage("手机号中含有逗号，不允许，请检查");
			return verifyVo;
		}
		//第1层校验，如果该手机号在24小时内获取几次
		int count = findTimesByPhoneNumber(verifyVo.getPhoneNumber());
		if(count>=10){
			verifyVo.setCode(CodeConst.VerifyState.fail);
			verifyVo.setMessage("该手机当日已发送短信次数超过上限，请明日再试");
			return verifyVo;
		}
		//检查手机号、类型、在当前过期时间内是否有未失效验证码
		Yyverify yyVerify = this.findYyverifyByPhoneNumAndTypeAndExpTime(verifyVo.getPhoneNumber(), verifyVo.getType());
		String time = commonService.getYydconfig(CodeConst.ConfigCode.Verity,CodeConst.ConfigCode.VerityMerchantTime).getConfigValue();
		//如果已经有了,需要检验是否配置允许的分钟内已经发送过,避免重复发送
		if(yyVerify!=null){
			Calendar c=Calendar.getInstance();
			c.setTime(yyVerify.getCreateTime());
			c.add(Calendar.MINUTE, Integer.parseInt(time));//1分钟后
			
			Calendar cNow=Calendar.getInstance();
			//如果减规定分钟还小,说明在这个允许时间内已经发送过
			logger.debug(c.getTime());
			logger.debug(cNow.getTime());
			if(c.getTime().after(cNow.getTime())){
				verifyVo.setCode(CodeConst.VerifyState.fail);
				verifyVo.setMessage("请不要重复获取短信验证码");
				return verifyVo;
			}
		}else{
			yyVerify = new Yyverify();
			yyVerify.setMobile(verifyVo.getPhoneNumber());
			yyVerify.setType(verifyVo.getType());
		}
		//获取验证码有效时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = dateFormat.format(new Date());
		yyVerify.setCreateTime(DateUtil.stringToDate(dateString.toString(), 100));

		Calendar c=Calendar.getInstance();
		c.setTime(yyVerify.getCreateTime());
		c.add(Calendar.MINUTE, Integer.parseInt(time));
		yyVerify.setExpiredTime(c.getTime());
		//获取4位随即数
		int ran = CommonUtils.getRandom(4);
		yyVerify.setVerifyNo(ran);
		super.save(yyVerify);
		
		//发送短信
		verifyVo.setVerifyNo(new Integer(ran).toString());
		boolean isSuccess = commonService.sendMessageBySendVo(verifyVo);
		verifyVo.setVerifyNo(yyVerify.getVerifyNo().toString());
		if(isSuccess){
			verifyVo.setCode(CodeConst.VerifyState.success);
			verifyVo.setMessage("验证码已发送至"+verifyVo.getPhoneNumber()+","+time+"分钟内有效");
		}else{
			verifyVo.setCode(CodeConst.VerifyState.fail);
			verifyVo.setMessage("验证码发送失败,请稍后重试");
		}
		return verifyVo;
	}
	

	/***
	 * 验证码验证
	 * @param verifyVo
	 * @return 验证码传输类
	 */
	public VerifyVo confirmVerify(VerifyVo verifyVo)  throws Exception {
		//验证码验证
		verifyVo = this.confirmVerifyNo(verifyVo);
		//业务信息处理
		if(CodeConst.VerifyState.success.equals(verifyVo.getCode())){
			//商户注册验证
			if(CodeConst.VerifyType.merchantRegist.equals(verifyVo.getType())){
				YywxMerchant merchant = merchantService.queryMerchantByMerchantId(verifyVo.getInputId());
				if(merchant!=null){
					merchant.setUwflag(CodeConst.Uwflag.pass);
					super.save(merchant);
				}
			}
		}
		return verifyVo;
	}
	
	/***
	 * 验证码验证
	 * @param verifyVo
	 * @return 验证码传输类 
	 */
	public VerifyVo confirmVerifyNo(VerifyVo verifyVo) {
		Yyverify yyVerify = findYyverifyById(verifyVo.getPhoneNumber(), verifyVo.getType());
		if(yyVerify==null){//没有验证记录
			verifyVo.setCode(CodeConst.VerifyState.fail);
			verifyVo.setMessage("手机号或类型不正确");
		}else{
			if(!verifyVo.getVerifyNo().equals((yyVerify.getVerifyNo().toString()))){//验证码不一致
				verifyVo.setCode(CodeConst.VerifyState.fail);
				verifyVo.setMessage("验证码不正确");
			}else{
				if(yyVerify.getExpiredTime().before(new Date())){//验证码有效时间在当前时间之前
					verifyVo.setCode(CodeConst.VerifyState.fail);
					verifyVo.setMessage("验证码已过期，请重新获取");
				}else{//验证通过
					verifyVo.setCode(CodeConst.VerifyState.success);
					verifyVo.setMessage("验证通过");
				}
			}
		}
		return verifyVo;
	}
	
	@SuppressWarnings("unused")
	private Yyverify findYyverifyById(String mobile,String type){
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("id.mobile", mobile);
		qr.addEqual("id.type", type);
		Yyverify yyVerify = super.findUnique(qr);
		return yyVerify;
	}
	
	@SuppressWarnings("unused")
	private Yyverify findYyverifyByPhoneAndVerifyNoAndExpTime(String mobile,String type,String checkNo){
		Date now = new Date();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addGreaterEqual("expiredTime", now);
		queryRule.addEqual("type", type);
		queryRule.addEqual("mobile", mobile);
		queryRule.addEqual("verifyNo", new Integer(checkNo));
		List<Yyverify> yyVerifies = super.find(Yyverify.class,queryRule);
		if(yyVerifies != null && yyVerifies.size()>0){
			return yyVerifies.get(0);
		}
		return null;
	}
	
	
	/***
	 * 验证码验证(新)
	 * @param mobile 手机号必传
	 * @param type必传 类型 1-商户注册 2-摄影工作室注册
	 * @param verifyNo验证码必传
	 * @return 验证码传输类
	 */
	public VerifyVo checkVerifyNo(VerifyVo verifyVo) throws Exception{
		Yyverify yyVerify = this.findYyverifyByPhoneAndVerifyNoAndExpTime(verifyVo.getPhoneNumber(), verifyVo.getType(), verifyVo.getVerifyNo());
		if(yyVerify==null){//没有验证记录
			verifyVo.setCode(CodeConst.VerifyState.fail);
			verifyVo.setMessage("验证码输入错误或已失效，请重新获取");
		}else{
			verifyVo.setCode(CodeConst.VerifyState.success);
			verifyVo.setMessage("验证通过");
		}
		return verifyVo;
	}
	
	/**
	 * 检查手机号、类型、在当前过期时间内是否有未失效验证码
	 * @param mobile
	 * @param type
	 * @return
	 */
	private Yyverify findYyverifyByPhoneNumAndTypeAndExpTime(String phoneNumber,String type){
		Date now = new Date();
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("mobile", phoneNumber);
		queryRule.addGreaterEqual("expiredTime", now);
		queryRule.addEqual("type", type);
		List<Yyverify> yyVerifies = super.find(Yyverify.class,queryRule);
		if(yyVerifies != null && yyVerifies.size()>0){
			return yyVerifies.get(0);
		}
		return null;
	}
	
	/**
	 * 获取该手机号在24小时内获取几次
	 * @param mobile
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unused")
	private int findTimesByPhoneNumber(String phoneNumber){
		HqlQueryRule hqlQueryRule = new HqlQueryRule();
		
		long time = 60*60*24*1000;//24小时
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		Date beforeDate = new Date(now .getTime() - time);//24小时之前
		hqlQueryRule.addBetween("yyverify.expiredTime",beforeDate, now,DateTime.YEAR_TO_DAY);
		hqlQueryRule.addEqual("yyverify.mobile", phoneNumber);
		String hql = "select yyverify.id from Yyverify yyverify where "+hqlQueryRule.getHql();
		List<Object[]> listResult = findByHql(hql, hqlQueryRule.getValues());
		System.out.println(hqlQueryRule.getHql());
		if(listResult != null && listResult.size()>0){
			return listResult.size();
		}
		return 0;
	}
	
	public CommonService getCommonService() {
		return commonService;
	}


	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}


	public MerchantService getMerchantService() {
		return merchantService;
	}


	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
	
	
}
