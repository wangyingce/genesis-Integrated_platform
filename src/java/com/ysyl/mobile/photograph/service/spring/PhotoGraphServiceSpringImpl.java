package com.ysyl.mobile.photograph.service.spring;

import ins.framework.cache.CacheManager;
import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.sinosoft.sysframework.exception.BusinessException;
import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyDcode;
import com.ysyl.common.schema.model.YyImageFile;
import com.ysyl.common.schema.model.YyPay;
import com.ysyl.common.schema.model.YyPgIndex;
import com.ysyl.common.schema.model.YyPgMyOrder;
import com.ysyl.common.schema.model.YyPgOrderDate;
import com.ysyl.common.schema.model.YyPgOrderDetail;
import com.ysyl.common.schema.model.YyPgOrderTime;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.schema.model.YyWaresOwner;
import com.ysyl.common.service.facade.CodeService;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.user.service.facade.UserService;
import com.ysyl.common.verify.VerifyVo;
import com.ysyl.common.verify.service.facade.VerifyService;
import com.ysyl.mobile.pay.service.facade.PayService;
import com.ysyl.mobile.photograph.service.facade.PhotoGraphService;
import com.ysyl.mobile.photograph.vo.PhotoGraphVo;
import com.ysyl.mobile.waresowner.service.facade.WaresOwnerService;
import com.ysyl.weixin.common.util.CommonUtils;
import com.ysyl.weixin.message.service.facade.WxMessageService;
import com.ysyl.weixin.pay.service.facade.WeiXinPayService;
import com.ysyl.weixin.pay.vo.WeiXinPayVo;
import com.ysyl.weixin.schema.model.YywxUserInfo;

public class PhotoGraphServiceSpringImpl extends GenericDaoHibernate<YyPgOrderTime, String> implements PhotoGraphService {
	private static CacheManager cacheManager = CacheManager.getIntance("Code");
	/** 公共服务*/
	private CommonService commonService;
	
	/** 验证服务对象*/
	private VerifyService verifyService;
	
	/** 商户服务对象*/
	private WaresOwnerService waresOwnerService;
	
	/** 用户服务对象*/
	private UserService userService;
	
	/** 基础代码服务*/
	private CodeService codeService;
	
	/** 微信支付服务*/
	private WeiXinPayService weiXinPayService;
	
	/** 订单服务*/
	private PayService payService;
	
	/** 微信消息服务*/
	private WxMessageService wxMessageService;
	
	public PayService getPayService() {
		return payService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	/**
	 * 发送短信验证码
	 * @param photoGraphVo 传送对象
	 */
	public PhotoGraphVo getMessageIdCode(PhotoGraphVo photoGraphVo) throws Exception{
		//组装短信发送对象
		VerifyVo verifyVo = new VerifyVo();
		//根据商户id翻译商户名称
		YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(photoGraphVo.getStoreId());
		photoGraphVo.setStoreName(yyWaresOwner.getOwnerName());
		verifyVo.setInputId(photoGraphVo.getStoreId());
		//调用短信验证码模板
		verifyVo.setMessageTemplate(CodeConst.ShortMessageTemplate.SmsTemplateCode_1);
		verifyVo.setPhoneNumber(photoGraphVo.getPhoneNumber());
		verifyVo.setProduct(photoGraphVo.getStoreName());
		verifyVo.setType(CodeConst.VerifyType.photoGraphRegist);
		//调用短信发送
		verifyVo = verifyService.receiveVerify(verifyVo);
		photoGraphVo.setVerifyVo(verifyVo);
		return photoGraphVo;
	}
	
	/**
	 * 验证验证码
	 */
	public PhotoGraphVo checkIdCode(PhotoGraphVo photoGraphVo) throws Exception{
		VerifyVo verifyVo = verifyService.checkVerifyNo(photoGraphVo.getVerifyVo());
		photoGraphVo.setVerifyVo(verifyVo);
		return photoGraphVo;
	}
	
	/**
	 * 摄影工作室注册提交
	 * @param photoGraphVo 传送对象
	 * @param yyUser 用户信息对象
	 */
	public PhotoGraphVo submitRegistPhotoGraph(PhotoGraphVo photoGraphVo) throws Exception{
		String userCode = photoGraphVo.getYyUser().getUserCode();
		YyUser yyUser = null;
		if(userCode == null || "".equals(userCode)){
			//注册一条用户信息
			yyUser = userService.addUser(photoGraphVo.getYyUser());
		}else{
			yyUser = photoGraphVo.getYyUser();
		}
		photoGraphVo.setYyUser(yyUser);
		//初始化工作室预约相关信息
		this.initPgCheckOrderInfo(photoGraphVo);
		//初始化商户信息
		//初始化商户信息
		YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(photoGraphVo.getStoreId());
		photoGraphVo.setYyWaresOwner(yyWaresOwner);
		
		//初始化可预约时段
//		//获取当前日期
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();  
        cl.setTime(new Date());  
        cl.add(Calendar.MONTH, +1);  
        String endDateStr = df.format(cl.getTime());  
		
//		String endDateStr = df.format(new Date(new Date().getTime() + 15 * 24 * 60 * 60 * 1000));
		//如果已经初始化过就不再初始化了
		Boolean results = (Boolean)cacheManager.getCache(df.format(new Date())+endDateStr+photoGraphVo.getStoreId());
		if(results== null ){
			this.generateDateAndTime(df.format(new Date()),endDateStr,photoGraphVo.getStoreId());
		}
		
		return photoGraphVo;
	}
	
	/**
	 * 初始化摄影工作室预约页面信息
	 * @param photoGraphVo 传送对象
	 */
	@SuppressWarnings("unchecked")
	public PhotoGraphVo initPgCheckOrderInfo(PhotoGraphVo photoGraphVo) throws Exception{
		//先初始化城市
		List<YyDcode> yyDcodeCities = codeService.getYyDcodesByCodeType(CodeConst.CodeType.PgCity);
		//初始化拍照类型
		List<YyDcode> yyDcodePhotoTypes = codeService.getYyDcodesByCodeType(CodeConst.CodeType.PgPhotoType);
		//城市
		String city = yyDcodeCities.get(0).getId().getCodeCode();
		//拍照类型
		String photoType = yyDcodePhotoTypes.get(0).getId().getCodeCode();
		//商户id
		String owner = photoGraphVo.getStoreId();
			
		//初始化第一条拍照类型所涉及的可预约时间，只初始化最近7天的，其它时间采取ajax异步加载，目前只有一个城市，暂不考虑多个城市的问题
		List<YyPgOrderDate> yyPgOrderDates = this.findPgCheckDateByCityAndPhotoType(city,photoType,owner);
		
		//根据预约明细初始化页面选择的具体时间控件数据
		//List<PhotoGraphTimeVo> photoGraphTimeVos = this.getTimeVosByYyPgOrderTimes(yyPgOrderTimes);
		
		//初始化对应照片类型的说明yypgorderdetail
		YyPgOrderDetail yyPgOrderDetail = this.getOrderDetailByCPO(city, photoType, owner);
		
		photoGraphVo.setYyDcodeCities(yyDcodeCities);
		photoGraphVo.setYyDcodePhotoTypes(yyDcodePhotoTypes);
		photoGraphVo.setYyPgOrderDates(yyPgOrderDates);
		photoGraphVo.setYyPgOrderDetail(yyPgOrderDetail);
		
		return photoGraphVo;
	}
	
	
	/**
	 * 初始化摄影工作室预约页面信息（只查最近7天）
	 * @param city 城市
	 * @param photoType 照片类型
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<YyPgOrderDate> findPgCheckDateByCityAndPhotoType(String city,String photoType,String owner) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<YyPgOrderDate> yyPgOrderDatesOne = new ArrayList<YyPgOrderDate>();
		List<YyPgOrderDate> returnYyPgOrderDates = new ArrayList<YyPgOrderDate>();
		Date nowDate = new Date();
		//获取当前小时
		int nowHour = nowDate.getHours();
		//查两次，一次是查今天之后的，一次是查今天的，但是要比当前小时大
		queryRule.addEqual("city", city);
		queryRule.addEqual("photoType", photoType);
		queryRule.addEqual("validStatus", "1");
		queryRule.addEqual("owner", owner);
		queryRule.addGreaterThan("pgDate",sdf.format(nowDate));
		queryRule.addAscOrder("pgDate");
//		queryRule.addAscOrder("pgBeginTime");
//		queryRule.addAscOrder("pgEndTime");
		//小于30天
		//queryRule.addLessEqual("pgDate",sdf.format(new Date(new Date().getTime() + 29 * 24 * 60 * 60 * 1000)));
		List<YyPgOrderDate> yyPgOrderDates = this.find(YyPgOrderDate.class,queryRule);
		//查第二次，查当天
		QueryRule queryRule1 = QueryRule.getInstance();
		queryRule1.addEqual("city", city);
		queryRule1.addEqual("photoType", photoType);
		queryRule1.addEqual("validStatus", "1");
		queryRule1.addEqual("owner", owner);
		queryRule1.addEqual("pgDate",sdf.format(nowDate));
		queryRule1.addGreaterThan("pgEndTime",nowHour+1);
		queryRule1.addAscOrder("pgBeginTime");
		queryRule1.addAscOrder("pgEndTime");
		List<YyPgOrderTime> yyPgOrdertimes = this.find(queryRule1);
		if(yyPgOrdertimes != null && yyPgOrdertimes.size()>0){
			YyPgOrderDate YyPgOrderDateOld = yyPgOrdertimes.get(0).getYyPgOrderDate();
			YyPgOrderDate YyPgOrderDateNew = new YyPgOrderDate();
			DataUtils.copySimpleObject(YyPgOrderDateOld,YyPgOrderDateNew,false);
			YyPgOrderDateNew.setYyPgOrderTimes(yyPgOrdertimes);
			yyPgOrderDatesOne.add(YyPgOrderDateNew);
		}
		yyPgOrderDatesOne.addAll(yyPgOrderDates);
		//处理返回的对象，简单化
		for (YyPgOrderDate yyPgOrderDate : yyPgOrderDatesOne) {
			YyPgOrderDate YyPgOrderDateNew = new YyPgOrderDate();
			DataUtils.copySimpleObject(yyPgOrderDate,YyPgOrderDateNew,false);
			List<YyPgOrderTime> yyPgOrderTimesNew = new ArrayList<YyPgOrderTime>();
			for (YyPgOrderTime yyPgOrderTimeOld : yyPgOrderDate.getYyPgOrderTimes()) {
				YyPgOrderTime yyPgOrderTimeNew = new YyPgOrderTime();
				DataUtils.copySimpleObject(yyPgOrderTimeOld,yyPgOrderTimeNew,false);
				yyPgOrderTimesNew.add(yyPgOrderTimeNew);
			}
			YyPgOrderDateNew.setYyPgOrderTimes(yyPgOrderTimesNew);
			returnYyPgOrderDates.add(YyPgOrderDateNew);
		}
		
		
		return returnYyPgOrderDates;
	}
	
	/**
	 * 根据城市，照片类型，商户id初始化出预约详细说明
	 * @param photoGraphVo 传送对象
	 */
	public YyPgOrderDetail getOrderDetailByCPO(String city,String photoType,String owner) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("city", city);
		queryRule.addEqual("photoType", photoType);
		queryRule.addEqual("owner", owner);
		YyPgOrderDetail yyPgOrderDetail = this.findUnique(YyPgOrderDetail.class,queryRule);
		return yyPgOrderDetail;
	}
	
	/**
	 * 预约页面提交
	 * @param photoGraphVo 传送对象 
	 */
	public PhotoGraphVo submitPgCheckOrder(PhotoGraphVo photoGraphVo) throws Exception{
		//先检查预约的是否还有，如果有锁定预定提交
		YyPgOrderTime yyPgOrderTime = this.findOrderTimeById(new Long(photoGraphVo.getCheckTimeId()));
		//先定义一个预约次数，以后如果页面要增加预约人数则把这个字段改活即可
		long cheNum = 1;
		//如果预约次数>0则说明还有可预约次数
		Long freeTime = yyPgOrderTime.getFreeTimes();
		Long inTime = yyPgOrderTime.getInTimes();
		Long sumFreeTime = yyPgOrderTime.getYyPgOrderDate().getSumFreeTimes();
		Long sumInTime = yyPgOrderTime.getYyPgOrderDate().getSumInTimes();
		if(freeTime>=cheNum){
			//将子表和主表的可预约次数及已预约次数分别-和+
			yyPgOrderTime.setFreeTimes(freeTime-cheNum);
			yyPgOrderTime.setInTimes(inTime+cheNum);
			yyPgOrderTime.getYyPgOrderDate().setSumInTimes(sumInTime+cheNum);
			yyPgOrderTime.getYyPgOrderDate().setSumFreeTimes(sumFreeTime-cheNum);
			this.save(yyPgOrderTime);
			//查询预约明细表，获取预约金额
			YyPgOrderDetail yyPgOrderDetail = this.getOrderDetailByCPO(yyPgOrderTime.getCity(), yyPgOrderTime.getPhotoType(), yyPgOrderTime.getOwner());
			photoGraphVo.setPayMoney(yyPgOrderDetail.getOrderPrice().toString());
			//保存我的订单表
			YyPgMyOrder yyPgMyOrder = new YyPgMyOrder();
			DataUtils.copySimpleObject(yyPgOrderTime,yyPgMyOrder,false);
			DataUtils.copySimpleObject(yyPgOrderDetail,yyPgMyOrder,false);
			yyPgMyOrder.setUserCode(photoGraphVo.getUserCode());
			//查询用户中文名称
			YyUser yyUser = userService.findUserByUserCode(photoGraphVo.getUserCode());
			yyPgMyOrder.setUserName(yyUser.getUserName());
			yyPgMyOrder.setUserPhone(yyUser.getPhone());
			yyPgMyOrder.setChannelType(CodeConst.ChannelType.wx);
			yyPgMyOrder.setCheckTimeId(new Integer(photoGraphVo.getCheckTimeId()));
			yyPgMyOrder.setPayState(CodeConst.PayState.waitPay);
			yyPgMyOrder.setIsCheck(CodeConst.isOrNot.is_zero);
			yyPgMyOrder.setPayStateName(CodeConst.PayStateName_Map.get(CodeConst.PayState.waitPay));
			//根据商户id查询商户信息
			YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(photoGraphVo.getStoreId());
			yyPgMyOrder.setOwnerName(yyWaresOwner.getOwnerName());
			yyPgMyOrder.setOwnerAddress(yyWaresOwner.getRegistAddress());
			yyPgMyOrder.setOwnerPhone(yyWaresOwner.getRegistPhone());
			yyPgMyOrder.setCreateTime(new Date());
			yyPgMyOrder.setId(null);
			this.save(yyPgMyOrder);
			photoGraphVo.setCheckTimeId(yyPgMyOrder.getId().toString());
			//占号之后要保存待支付订单表,保存订单同时调用微信支付
			photoGraphVo = this.toWeiXinPay(photoGraphVo);
		}else{
			WeiXinPayVo payVo = new WeiXinPayVo();
			payVo.setError("您所选择的时间已无可预约次数，请重新选择");
			Gson g = new Gson();
			String json = g.toJson(payVo);
			photoGraphVo.setJson(json);
		}
		
		return photoGraphVo;
	}
	
	 /** 调用微信支付
	 * @param WeiXinPayVo 微信支付入参对象
	 * @return 返回微信json
	 */
	public PhotoGraphVo toWeiXinPay(PhotoGraphVo photoGraphVo) throws Exception{
		//查询商户名称
		//YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(photoGraphVo.getStoreId());
		WeiXinPayVo weiXinPayVo = new WeiXinPayVo();
		weiXinPayVo.setNickName(photoGraphVo.getStoreName());
		weiXinPayVo.setSourceType(CodeConst.SourceType.pg);
		weiXinPayVo.setUserCode(photoGraphVo.getUserCode());
		weiXinPayVo.setInKey(photoGraphVo.getCheckTimeId());
		weiXinPayVo.setMoney(photoGraphVo.getPayMoney());
		weiXinPayVo.setOrderId(photoGraphVo.getStoreId());
		weiXinPayVo.setOpenId(photoGraphVo.getOpenId());
		weiXinPayVo.setBusinessNo(photoGraphVo.getStoreId());
		weiXinPayVo.setIp(photoGraphVo.getIp());
		String json = weiXinPayService.payWeiXinForStore(weiXinPayVo);
		photoGraphVo.setJson(json);
		return photoGraphVo;
	}
	
	
	/**
	 * 根据ID查找有效的预约时间表
	 * @param Id
	 * @return YyPgOrderTime
	 * @throws Exception
	 */
	public YyPgOrderTime findOrderTimeById(Long Id) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("id",Id);
		queryRule.addEqual("validStatus", "1");
		YyPgOrderTime yyPgOrderTime = this.findUnique(queryRule);
		return yyPgOrderTime;
	}
	
	/**
	 * 根据起止日期生成对应商户的yypgorderdate 和 yypgordertime表
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void generateDateAndTime(String pgBeginDate,String pgEndDate,String owner) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date beforeDate=df.parse(pgBeginDate);
		//减一天
		String beforDateStr = df.format(new Date(beforeDate.getTime() - 1 * 24 * 60 * 60 * 1000));
		int beforNum = 0;
		List<YyPgOrderDate> yyPgOrderDateLastDates  = null;
		while(true){
			//拿传入值的前一天数据来生成时间段和可预约数
			QueryRule queryRule1 = QueryRule.getInstance();
//			queryRule1.addEqual("city", city);
//			queryRule1.addEqual("photoType", photoType);
			queryRule1.addEqual("owner", owner);
			queryRule1.addEqual("validStatus", "1");
			queryRule1.addEqual("pgDate",beforDateStr);
			yyPgOrderDateLastDates = this.find(YyPgOrderDate.class,queryRule1);
			if(yyPgOrderDateLastDates != null && yyPgOrderDateLastDates.size()>0){
				break;
			}
			//再减一天
			beforeDate=df.parse(beforDateStr);
			beforDateStr = df.format(new Date(beforeDate.getTime() - 1 * 24 * 60 * 60 * 1000));
			if(beforNum ++ > 30){
				throw new BusinessException("无法找到近30天历史数据，无法自动配置，请先配置",false);
			}
		}
		List<YyPgOrderDate> yyPgOrderDates = new ArrayList<YyPgOrderDate>();
		for (YyPgOrderDate yyPgOrderDateLastDate : yyPgOrderDateLastDates) {
			//把传入的区间值查一下，看看是否有
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("city", yyPgOrderDateLastDate.getCity());
			queryRule.addEqual("photoType", yyPgOrderDateLastDate.getPhotoType());
			queryRule.addEqual("owner", yyPgOrderDateLastDate.getOwner());
			queryRule.addEqual("validStatus", "1");
			queryRule.addGreaterEqual("pgDate",pgBeginDate);
			queryRule.addLessEqual("pgDate",pgEndDate);

			List<YyPgOrderDate> yyPgOrderDateOlds = this.find(YyPgOrderDate.class,queryRule);

			Map<String,YyPgOrderDate> yyPgOrderDateMaps = new HashMap<String,YyPgOrderDate>();
			if(yyPgOrderDateOlds != null && yyPgOrderDateOlds.size() >0){
				//如果不是空，则说明有数据，有数据的要跳过不生成
				for (YyPgOrderDate date : yyPgOrderDateOlds) {
					yyPgOrderDateMaps.put(date.getCity()+date.getPhotoType()+date.getOwner()+date.getPgDate(), date);
				}
			}
			//开始保存
			int forTimes = 0;
			String forDate = pgBeginDate;
			while(true){
				Date nowDate=df.parse(forDate);
				if(yyPgOrderDateMaps.get(yyPgOrderDateLastDate.getCity()+yyPgOrderDateLastDate.getPhotoType()+owner+forDate) == null){
					YyPgOrderDate yyPgOrderDate = new YyPgOrderDate();
					DataUtils.copySimpleObject(yyPgOrderDateLastDate,yyPgOrderDate,false);
					yyPgOrderDate.setPgDate(forDate);
					//获取星期几
					yyPgOrderDate.setWeekName(CommonUtils.getWeekOfDate(nowDate));
					yyPgOrderDate.setSumInTimes(0L);
					yyPgOrderDate.setSumFreeTimes(yyPgOrderDate.getSumTotalTimes());
					yyPgOrderDate.setId(null);
					yyPgOrderDate.setCreateTime(new Date());
					List<YyPgOrderTime> yyPgOrderTimes = new ArrayList<YyPgOrderTime>();
					for (YyPgOrderTime yyPgOrderTimeOld : yyPgOrderDateLastDate.getYyPgOrderTimes()) {
						YyPgOrderTime yyPgOrderTime = new YyPgOrderTime();
						DataUtils.copySimpleObject(yyPgOrderTimeOld,yyPgOrderTime,false);
						yyPgOrderTime.setPgDate(yyPgOrderDate.getPgDate());
						yyPgOrderTime.setWeekName(yyPgOrderDate.getWeekName());
						yyPgOrderTime.setCreateTime(yyPgOrderDate.getCreateTime());
						yyPgOrderTime.setInTimes(0L);
						yyPgOrderTime.setFreeTimes(yyPgOrderTime.getTotalTimes());
						yyPgOrderTime.setId(null);
						yyPgOrderTime.setYyPgOrderDate(yyPgOrderDate);
						
						yyPgOrderTimes.add(yyPgOrderTime);
					}
					yyPgOrderDate.setYyPgOrderTimes(yyPgOrderTimes);

					yyPgOrderDates.add(yyPgOrderDate);
				}
				if(forTimes >= 365){
					break;
				}

				//加一天
				forDate = df.format(new Date(nowDate.getTime() + 1 * 24 * 60 * 60 * 1000));
				forTimes ++;
				if(forDate.equals(pgEndDate)){
					break;
				}
			}
		}
		this.saveAll(yyPgOrderDates);
		cacheManager.putCache(pgBeginDate+pgEndDate+owner, true);
	}
	
	/**
	 * 初始化我的订单信息
	 * @param payState 支付状态 ALL-代表全查
	 * @throws Exception
	 */
	public PhotoGraphVo initMyOrder(PhotoGraphVo photoGraphVo) throws Exception{
		String userCode = photoGraphVo.getYyUser().getUserCode();
		String storeId = photoGraphVo.getStoreId();
		String payState = photoGraphVo.getPayState();
		List<YyPgMyOrder> yyPgMyOrders = this.findYyPgMyOrderByUserCodeAndStoreIdAndPayState(userCode,storeId,payState);
		photoGraphVo.setYyPgMyOrders(yyPgMyOrders);
		if(yyPgMyOrders != null && yyPgMyOrders.size() > 0){
			photoGraphVo.setIsOrder("1");
		}
		if(yyPgMyOrders != null && yyPgMyOrders.size()>0){
			photoGraphVo.setPhoneNumber(yyPgMyOrders.get(0).getOwnerPhone());
		}else{
			YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(storeId);
			photoGraphVo.setPhoneNumber(yyWaresOwner.getRegistPhone());
		}
		return photoGraphVo;
	}
	
	
	/**
	 * 查询我的订单信息
	 * @param userCode
	 * @param streoId
	 * @param payState
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<YyPgMyOrder> findYyPgMyOrderByUserCodeAndStoreIdAndPayState(String userCode,String streoId,String payState) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("userCode", userCode);
		queryRule.addEqual("owner", streoId);
		queryRule.addEqual("validStatus", "1");
		//传ALL代表全查，则不组条件
		if(!"ALL".equals(payState)){
			if(CodeConst.PayState.finishPay.equals(payState)){
				queryRule.addEqual("payState",CodeConst.PayState.successPay);
			}else{
				queryRule.addEqual("payState",payState);
			}
		}
		queryRule.addDescOrder("pgDate");
		queryRule.addDescOrder("pgBeginTime");
		//如果传进来的是11-已付款，需要看当前时间是否已经超过预约时间，如果已超过则显示为已完成，否则显示为预约中
		List<YyPgMyOrder> returnList = new ArrayList<YyPgMyOrder>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		//获取当前小时
		int nowHour = nowDate.getHours();
		if(CodeConst.PayState.successPay.equals(payState)){
			//查两次，一次是查今天之后的，一次是查今天的，但是要比当前小时大
			queryRule.addGreaterThan("pgDate",sdf.format(nowDate));
			Page page = (Page)this.find(YyPgMyOrder.class,queryRule,0,10);
			List<YyPgMyOrder> yyPgMyOrders = page.getResult();
			//查第二次，查当天当前小时之后的
			QueryRule queryRule1 = QueryRule.getInstance();
			queryRule1.addEqual("userCode", userCode);
			queryRule1.addEqual("owner", streoId);
			queryRule1.addEqual("validStatus", "1");
			queryRule1.addEqual("payState",payState);
			queryRule1.addEqual("pgDate",sdf.format(nowDate));
			queryRule1.addGreaterThan("pgEndTime",nowHour);
			queryRule1.addDescOrder("pgDate");
			//queryRule1.addDescOrder("pgBeginTime");
			Page page1 = (Page)this.find(YyPgMyOrder.class,queryRule1,0,10);
			List<YyPgMyOrder> yyPgMyOrdersTodaies = page1.getResult();
			if(yyPgMyOrdersTodaies!=null){
				returnList.addAll(yyPgMyOrdersTodaies);
			}
			if(yyPgMyOrders!=null){
				returnList.addAll(yyPgMyOrders);
			}
			return returnList;
		}else if(CodeConst.PayState.finishPay.equals(payState)){
			//查两次，一次是查今天之后的，一次是查今天的，但是要比当前小时大
			queryRule.addLessThan("pgDate",sdf.format(nowDate));
			Page page = (Page)this.find(YyPgMyOrder.class,queryRule,0,10);
			List<YyPgMyOrder> yyPgMyOrders = page.getResult();
			//查第二次，查当天当前小时之后的
			QueryRule queryRule1 = QueryRule.getInstance();
			queryRule1.addEqual("userCode", userCode);
			queryRule1.addEqual("owner", streoId);
			queryRule1.addEqual("payState",CodeConst.PayState.successPay);
			queryRule1.addEqual("validStatus", "1");
			queryRule1.addEqual("pgDate",sdf.format(nowDate));
			queryRule1.addLessThan("pgEndTime",nowHour);
			queryRule1.addDescOrder("pgDate");
			queryRule1.addDescOrder("pgBeginTime");
			Page page1 = (Page)this.find(YyPgMyOrder.class,queryRule1,0,10);
			List<YyPgMyOrder> yyPgMyOrdersTodaies = page1.getResult();
			if(yyPgMyOrdersTodaies!=null){
				returnList.addAll(yyPgMyOrdersTodaies);
			}
			if(yyPgMyOrders!=null){
				returnList.addAll(yyPgMyOrders);
			}
			return returnList;
		}
		
		//只显示最近10条
		Page page = (Page)this.find(YyPgMyOrder.class,queryRule,0,10);
		List<YyPgMyOrder> yyPgMyOrders = page.getResult();
//		return this.find(YyPgMyOrder.class,queryRule);
		return yyPgMyOrders;
	}
	
	/**
	 * 微信支付回调方法
	 * @throws Exception
	 * @return 商户中文名称 
	 */
	public YyPgMyOrder PayBack(YyPay yyPay) throws Exception{
//		String ownerName = "";
//		//先根据商户id查询商户信息
//		YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(yyPay.getStoreId());
//		ownerName = yyWaresOwner.getOwnerName();
		//更新我的订单表状态
		YyPgMyOrder yyPgMyOrder = this.findYyPgMyOrderById(new Long(yyPay.getInKey()));
		yyPgMyOrder.setPayState(yyPay.getPayState());
		yyPgMyOrder.setPayStateName(CodeConst.PayStateName_Map.get(yyPay.getPayState()));
		return yyPgMyOrder;
	}
	
	/**
	 * 根据id查询我的订单信息
	 * @throws Exception
	 */
	public YyPgMyOrder findYyPgMyOrderById(Long id) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("id", id);
		return this.findUnique(YyPgMyOrder.class,queryRule);
	}
	
	/**
	 * 取消订单
	 * @throws Exception
	 */
	public PhotoGraphVo cancelMyOrder(PhotoGraphVo photoGraphVo) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("id", new Long(photoGraphVo.getOrderId()));
		YyPgMyOrder yyPgMyOrder = this.findUnique(YyPgMyOrder.class,queryRule);
		//yyPgMyOrder.setValidStatus("0");
		yyPgMyOrder.setPayState(CodeConst.PayState.userCancelPay);
		yyPgMyOrder.setPayStateName(CodeConst.PayStateName_Map.get(CodeConst.PayState.userCancelPay));
		return photoGraphVo;
	}
	
	/**
	 * 删除订单
	 * @throws Exception
	 */
	public PhotoGraphVo deleteMyOrder(PhotoGraphVo photoGraphVo) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("id", new Long(photoGraphVo.getOrderId()));
		YyPgMyOrder yyPgMyOrder = this.findUnique(YyPgMyOrder.class,queryRule);
		yyPgMyOrder.setValidStatus("0");
		//yyPgMyOrder.setPayState(CodeConst.PayState.userCancelPay);
		//yyPgMyOrder.setPayStateName(CodeConst.PayStateName_Map.get(CodeConst.PayState.userCancelPay));
		return photoGraphVo;
	}
	
	/**
	 * 订单页面点击订单支付
	 * @throws Exception
	 */
	public PhotoGraphVo payMyOrder(PhotoGraphVo photoGraphVo) throws Exception{
		//先通过支付订单id拿到详细支付信息
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("id", new Long(photoGraphVo.getOrderId()));
		YyPgMyOrder yyPgMyOrder = this.findUnique(YyPgMyOrder.class,queryRule);
		//组织微信支付参数
		photoGraphVo.setStoreName(yyPgMyOrder.getOwnerName());
		photoGraphVo.setUserCode(yyPgMyOrder.getUserCode());
		photoGraphVo.setCheckTimeId(yyPgMyOrder.getId().toString());
		photoGraphVo.setPayMoney(yyPgMyOrder.getOrderPrice().toString());
		photoGraphVo.setStoreId(yyPgMyOrder.getOwner());
		YyUser yyUser = userService.findUserByUserCode(yyPgMyOrder.getUserCode());
		photoGraphVo.setOpenId(yyUser.getInKey());
		photoGraphVo = this.toWeiXinPay(photoGraphVo);
		
		return photoGraphVo;
	}
	
	/**
	 * 点击推送信息详细页面（商家看到的）
	 */
	public YyPgMyOrder showOrderForOwner(PhotoGraphVo photoGraphVo) throws Exception{
		YyPgMyOrder yyPgMyOrder = this.findYyPgMyOrderById(new Long(photoGraphVo.getOrderId()));
		return yyPgMyOrder;
	}
	
	/**
	 * 展示首页
	 */
	public PhotoGraphVo showIndex(PhotoGraphVo photoGraphVo) throws Exception{
		List<YyPgIndex> yyPgIndexs = this.findYyPgIndexByOwner(photoGraphVo.getStoreId());
		photoGraphVo.setYyPgIndexs(yyPgIndexs);
		//查询商户信息
		YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(photoGraphVo.getStoreId());
		photoGraphVo.setYyWaresOwner(yyWaresOwner);
		
		return photoGraphVo;
	}
	
	
	/**
	 * 根据商户代码查询对应商户首页配置信息
	 */
	public List<YyPgIndex> findYyPgIndexByOwner(String owner) throws Exception{
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("owner", owner);
		queryRule.addAscOrder("serialNo");
		List<YyPgIndex> yyPgIndexs = this.find(YyPgIndex.class,queryRule);
		return yyPgIndexs;
	}
	
	/**
	 * 商户确认客户订单
	 */
	public PhotoGraphVo confirmOrder(PhotoGraphVo photoGraphVo) throws Exception{
		//根据inkey查询信息
		YyPgMyOrder yyPgMyOrder = this.findYyPgMyOrderById(new Long(photoGraphVo.getInKey()));
		//根据usercode查询客户微信openid
		YyUser yyUser = userService.findUserByUserCode(yyPgMyOrder.getUserCode());
		//得到之后调用发送消息接口
		StringBuffer sendMessageBuffer = new StringBuffer(10);
		sendMessageBuffer.append("您的预约："+yyPgMyOrder.getPgDate());
		sendMessageBuffer.append("("+yyPgMyOrder.getWeekName()+")");
		sendMessageBuffer.append("【"+yyPgMyOrder.getPhotoName()+"】订单已确认，期待与您的相见");
		wxMessageService.sendTextToUserId(yyUser.getInKey(),sendMessageBuffer.toString());
		//发送成功后更新是否确认状态为1
		yyPgMyOrder.setIsCheck("1");
		this.save(yyPgMyOrder);
		return photoGraphVo;
	}
	
	/**
	 * 获取我的底片
	 */
	@SuppressWarnings("unchecked")
	public PhotoGraphVo getMyPhoto(PhotoGraphVo photoGraphVo) throws Exception{
		//如果phone为空
		YyUser yyUser = null;
		List<YyImageFile> yyImageFiles = null;
		
		if(photoGraphVo.getPhoneNumber() == null || "".equals(photoGraphVo.getPhoneNumber())){
			yyUser = userService.findUserByInTypeAndInKeyAnyOwner(CodeConst.ChannelType.wx, photoGraphVo.getOpenId(), photoGraphVo.getStoreId());
		}
		if(yyUser!=null){
			photoGraphVo.setPhoneNumber(yyUser.getPhone());
			//根据Phone查询我的底片
			QueryRule queryRule = QueryRule.getInstance();
			//根据usercode或者inkey取
			queryRule.addEqual("owner", photoGraphVo.getStoreId());
			queryRule.addEqual("phone",photoGraphVo.getPhoneNumber());
			queryRule.addEqual("validStatus","1");
			yyImageFiles = this.find(YyImageFile.class,queryRule);
			photoGraphVo.setYyImageFiles(yyImageFiles);
			if(yyImageFiles != null && yyImageFiles.size()>0){
				photoGraphVo.setIsOrder("1");
			}
		}
		if(yyImageFiles != null && yyImageFiles.size()>0){
			photoGraphVo.setIsOrder("1");
		}else{
			//如果是空判断一下这个用户是不是在10月25日到10月27日之间关注的，如果是的话要特殊处理，需要这批用户输入学号来查询照片底片
			QueryRule queryRule1 = QueryRule.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryRule1.addLessEqual("subscribe_time", sdf.parse("2016-10-27 23:59:59"));
			queryRule1.addGreaterEqual("subscribe_time", sdf.parse("2016-10-25 00:00:01"));
			queryRule1.addEqual("openid", photoGraphVo.getOpenId());
			YywxUserInfo yywxUserInfo = this.findUnique(YywxUserInfo.class,queryRule1);
			if(yywxUserInfo != null){
				photoGraphVo.setIsOrder("2");
			}else{
				photoGraphVo.setIsOrder("0");
			}
		}
		//查询商户信息
		YyWaresOwner yyWaresOwner = waresOwnerService.getWaresOnwerById(photoGraphVo.getStoreId());
		photoGraphVo.setYyWaresOwner(yyWaresOwner);
		return photoGraphVo;
	}
	
	/**
	 * 获取学生照片
	 */
	public PhotoGraphVo getStudentPhoto(PhotoGraphVo photoGraphVo) throws Exception{
		//根据remark查询我的底片
		QueryRule queryRule = QueryRule.getInstance();
		//根据usercode或者inkey取
		queryRule.addEqual("remark", photoGraphVo.getInKey());
		queryRule.addEqual("validStatus","1");
		List<YyImageFile> yyImageFiles = this.find(YyImageFile.class,queryRule);
		photoGraphVo.setYyImageFiles(yyImageFiles);
		return photoGraphVo;
	}
	
	public CommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public VerifyService getVerifyService() {
		return verifyService;
	}
	public void setVerifyService(VerifyService verifyService) {
		this.verifyService = verifyService;
	}
	public WaresOwnerService getWaresOwnerService() {
		return waresOwnerService;
	}
	public void setWaresOwnerService(WaresOwnerService waresOwnerService) {
		this.waresOwnerService = waresOwnerService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CodeService getCodeService() {
		return codeService;
	}

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public WeiXinPayService getWeiXinPayService() {
		return weiXinPayService;
	}

	public void setWeiXinPayService(WeiXinPayService weiXinPayService) {
		this.weiXinPayService = weiXinPayService;
	}

	public WxMessageService getWxMessageService() {
		return wxMessageService;
	}

	public void setWxMessageService(WxMessageService wxMessageService) {
		this.wxMessageService = wxMessageService;
	}
	
	
}
