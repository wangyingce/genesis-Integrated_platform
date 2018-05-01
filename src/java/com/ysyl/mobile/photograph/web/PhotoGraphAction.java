package com.ysyl.mobile.photograph.web;

import ins.framework.web.Struts2Action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.ysyl.common.CodeConst;
import com.ysyl.common.schema.model.YyPgMyOrder;
import com.ysyl.common.schema.model.YyPgOrderDate;
import com.ysyl.common.schema.model.YyPgOrderDetail;
import com.ysyl.common.schema.model.YyUser;
import com.ysyl.common.service.facade.CommonService;
import com.ysyl.common.user.service.facade.UserService;
import com.ysyl.common.verify.VerifyVo;
import com.ysyl.mobile.photograph.service.facade.PhotoGraphService;
import com.ysyl.mobile.photograph.vo.PhotoGraphVo;
import com.ysyl.restFulServlet.util.ServletUtil;
import com.ysyl.weixin.common.util.CommonUtils;

/**
 * 摄影工作室action
 * @author lenovo
 *
 */
public class PhotoGraphAction extends Struts2Action{
	private static final long serialVersionUID = 1L;
	
	private PhotoGraphService photoGraphService;
	
	private UserService userService;
	
	private CommonService commonService;
	
	/** 公众号id*/
	private String openId;
	
	/** 用户昵称*/
	private String nickName;
	
	/** 手机号码*/
	private String phoneNum;
	
	/** 商户ID*/
	private String storeId;
	
	/** 商户名称*/
	private String storeName;
	
	/** 摄影工作室传送对象*/
	private PhotoGraphVo photoGraphVo;
	
	/** 验证码*/
	private String checkNo;
	
	/** 城市*/
	private String city;
	
	/** 拍照类型*/
	private String photoType;
	
	/** 支付用户代码*/
	private String userCode;
	
	private YyUser yyUser;
	
	/** 预约时间表的id*/
	private String checkTimeId;
	
	/** 唯一id 或用作公共入参*/
	private String inKey;
	
	/**
	 * 我的订单信息
	 */
	private List<YyPgMyOrder> yyPgMyOrders;
	
	/**
	 * 订单详情
	 */
	private YyPgMyOrder yyPgMyOrder;
	
	public String getInKey() {
		return inKey;
	}

	public void setInKey(String inKey) {
		this.inKey = inKey;
	}

	/**
	 * 初始化用户注册摄影工作室
	 * @return
	 */
	public String registPhotoGraph(){
		try {
			//先从session获取用户信息
			if(openId == null || "".equals(openId)){
				openId = (String)this.getRequest().getSession().getAttribute("OpenId");
			}
			
			yyUser = new YyUser();
			yyUser.setOwner(storeId);
			if(openId != null && !"".equals(openId)){
				//如果openid有值，说明渠道是微信注册
				yyUser.setInType(CodeConst.ChannelType.wx);
				yyUser.setInKey(openId);
				commonService.setSession(this.getRequest().getSession(), openId, null,null);
				//检查该用户是否通过微信注册过这个商户
				YyUser yyUserOld = userService.findUserByInTypeAndInKeyAnyOwner(CodeConst.ChannelType.wx,openId,storeId);
				if(yyUserOld != null){
					yyUser = yyUserOld;
					//保存session
					commonService.setSession(this.getRequest().getSession(), null, yyUserOld.getUserCode(), yyUserOld.getUserName());
					return "registed";
				}
				photoGraphVo = new PhotoGraphVo();
				photoGraphVo.setStoreId(storeId);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 注册摄影工作室提交
	 * @return
	 */
	public String submitRegistPhotoGraph(){
		//注册成功生成用户信息yyuser表
		try {
			//获取ip
			String loginIp = CommonUtils.getIp(this.getRequest());
			photoGraphVo = new PhotoGraphVo();
			yyUser.setSource(CodeConst.SourceType.pg);
			yyUser.setLoginIp(loginIp);
			photoGraphVo.setYyUser(yyUser);
			photoGraphVo.setStoreId(yyUser.getOwner());
			photoGraphVo = photoGraphService.submitRegistPhotoGraph(photoGraphVo);
			//保存session
			commonService.setSession(this.getRequest().getSession(), "", photoGraphVo.getYyUser().getUserCode(), photoGraphVo.getYyUser().getUserCode());
			System.out.println("注册工作室 success"+ new Date());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 初始拍照化详细说明信息
	 * @return
	 */
	public void initCheckOrderInfo(){
		//注册成功生成用户信息yyuser表
		try {
			photoGraphVo = new PhotoGraphVo();
			//初始化预约说明
			YyPgOrderDetail yyPgOrderDetail = photoGraphService.getOrderDetailByCPO(city,photoType,storeId);
			//初始化可预约时段
			List<YyPgOrderDate> yyPgOrderDates = photoGraphService.findPgCheckDateByCityAndPhotoType(city,photoType,storeId);
			//根据预约明细初始化页面选择的具体时间控件数据
			//List<PhotoGraphTimeVo> photoGraphTimeVos = photoGraphService.getTimeVosByYyPgOrderTimes(yyPgOrderTimes);
			
			photoGraphVo.setYyPgOrderDetail(yyPgOrderDetail);
			photoGraphVo.setYyPgOrderDates(yyPgOrderDates);
			
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), photoGraphVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取注册短信验证码
	 * @param 
	 * @return
	 */
	public void getMessageIdCode(){
		try {
//			//初始化商品大类
//			if(StoreConst.InitType.waresClass.equals(initType)){
//				//根据商户Id查询该商户的商品大类明细
//				List<YyWaresClass> yyWaresClasses = mobileStoreService.getWaresClassByStoreId(storeId);
//				//返回ajax
//				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), yyWaresClasses);
//			}else if(StoreConst.InitType.waresDetail.equals(initType)){
//				//初始化商品明细
//				List<YyWaresDetail> yyWaresDetails = mobileStoreService.getWaresDetailsByStoreId(storeId);
//				//返回ajax
//				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), yyWaresDetails);
//			}
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setPhoneNumber(phoneNum);
			photoGraphVo.setStoreId(storeId);
			//发送验证码
			photoGraphVo = photoGraphService.getMessageIdCode(photoGraphVo);
			//commonService.sendMessageByType("1",phoneNum,ran);
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), photoGraphVo.getVerifyVo().getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 预约页面提交
	 * @param 
	 * @return
	 */
	public void submitPgCheckOrder(){
		try {
			String ip = this.getRequest().getRemoteAddr();
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setCheckTimeId(checkTimeId);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo.setStoreName(storeName);
			photoGraphVo.setUserCode(userCode);
			photoGraphVo.setOpenId(inKey);
			photoGraphVo.setIp(ip);
			//预约信息提交
			photoGraphVo = photoGraphService.submitPgCheckOrder(photoGraphVo);
			//commonService.sendMessageByType("1",phoneNum,ran);
			//返回ajax
			ServletActionContext.getResponse().getWriter().print(photoGraphVo.getJson());
		} catch (Exception e) {
			try {
				//String aa  = e.getClass().get;111
				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), "error");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取注册短信验证码
	 * @param 
	 * @return
	 */
	public void checkIdCode(){
		try {
			photoGraphVo = new PhotoGraphVo();
			VerifyVo verifyVo = new VerifyVo();
			verifyVo.setPhoneNumber(phoneNum);
			verifyVo.setVerifyNo(checkNo);
			verifyVo.setType(CodeConst.VerifyType.photoGraphRegist);
			photoGraphVo.setVerifyVo(verifyVo);
			//发送验证码
			photoGraphVo = photoGraphService.checkIdCode(photoGraphVo);
			//commonService.sendMessageByType("1",phoneNum,ran);
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), photoGraphVo.getVerifyVo());
		} catch (Exception e) {
			VerifyVo verifyVo = new VerifyVo();
			verifyVo.setCode(CodeConst.VerifyState.fail);
			verifyVo.setMessage("验证码输入错误或已失效，请重新获取");
			try {
				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), verifyVo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			System.out.println("验证码格式乱输入");
			e.printStackTrace();
		}
	}
	
	/**
	 * 点击首页我的订单按钮
	 * @return
	 */
	public String initMyOrder(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			photoGraphVo.setPayState(CodeConst.PayState.waitPay);
			photoGraphVo = photoGraphService.initMyOrder(photoGraphVo);
			//yyPgMyOrders = photoGraphVo.getYyPgMyOrders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 点击更换订单类型查看
	 * @return
	 */
	public void initMyChangeOrder(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			//要获取的订单状态
			photoGraphVo = new PhotoGraphVo();
			yyUser = new YyUser();
			yyUser.setUserCode(userCode);
			photoGraphVo.setPayState(inKey);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo.setYyUser(yyUser);
			photoGraphVo = photoGraphService.initMyOrder(photoGraphVo);
			yyPgMyOrders = photoGraphVo.getYyPgMyOrders();
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),yyPgMyOrders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消订单
	 */
	public void cancelMyOrder(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			//要获取的订单状态
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setOrderId(inKey);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo.setUserCode(userCode);
			photoGraphVo = photoGraphService.cancelMyOrder(photoGraphVo);
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),"success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除订单
	 */
	public void deleteMyOrder(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			//要获取的订单状态
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setOrderId(inKey);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo.setUserCode(userCode);
			photoGraphVo = photoGraphService.deleteMyOrder(photoGraphVo);
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),"success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 我的订单页面点击支付
	 */
	public void payMyOrder(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			String loginIp = CommonUtils.getIp(this.getRequest());
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setOrderId(inKey);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo.setUserCode(userCode);
			photoGraphVo.setIp(loginIp);
			photoGraphVo = photoGraphService.payMyOrder(photoGraphVo);
			//返回ajax
			ServletActionContext.getResponse().getWriter().print(photoGraphVo.getJson());
		} catch (Exception e) {
			try {
				//String aa  = e.getClass().get;111
				ServletUtil.responseAjaxJson(ServletActionContext.getResponse(), "error");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 点击推送信息详细页面（商家看到的）
	 */
	public String showOrderForOwner(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setOrderId(inKey);
			yyPgMyOrder = photoGraphService.showOrderForOwner(photoGraphVo);
			//返回ajax
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 初始化我的设置
	 */
	public String initMySet(){
		return "success";
	}
	
	/**
	 * 展示首页
	 */
	public String showIndex(){
		try {
			photoGraphVo = photoGraphService.showIndex(photoGraphVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}
	
	/**
	 * 获取我的底片
	 */
	public String getMyPhoto(){
		try {
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setPhoneNumber(phoneNum);
			photoGraphVo.setOpenId(openId);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo = photoGraphService.getMyPhoto(photoGraphVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}
	
	/**
	 * 商户确认客户订单
	 * @return
	 */
	public void confirmOrder(){
		//根据用户代码和inkey查询订单，默认初始化未付款标签
		try {
			//要获取的订单状态
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setInKey(inKey);
			photoGraphVo = photoGraphService.confirmOrder(photoGraphVo);
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取学生照片
	 * @return
	 */
	public void getStudentPhoto(){
		try {
			photoGraphVo = new PhotoGraphVo();
			photoGraphVo.setInKey(inKey);
			photoGraphVo.setStoreId(storeId);
			photoGraphVo = photoGraphService.getStudentPhoto(photoGraphVo);
			//返回ajax
			ServletUtil.responseAjaxJson(ServletActionContext.getResponse(),photoGraphVo.getYyImageFiles());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PhotoGraphService getPhotoGraphService() {
		return photoGraphService;
	}

	public void setPhotoGraphService(PhotoGraphService photoGraphService) {
		this.photoGraphService = photoGraphService;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public PhotoGraphVo getPhotoGraphVo() {
		return photoGraphVo;
	}

	public void setPhotoGraphVo(PhotoGraphVo photoGraphVo) {
		this.photoGraphVo = photoGraphVo;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public YyUser getYyUser() {
		return yyUser;
	}

	public void setYyUser(YyUser yyUser) {
		this.yyUser = yyUser;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhotoType() {
		return photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public String getCheckTimeId() {
		return checkTimeId;
	}

	public void setCheckTimeId(String checkTimeId) {
		this.checkTimeId = checkTimeId;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public List<YyPgMyOrder> getYyPgMyOrders() {
		return yyPgMyOrders;
	}

	public void setYyPgMyOrders(List<YyPgMyOrder> yyPgMyOrders) {
		this.yyPgMyOrders = yyPgMyOrders;
	}

	public YyPgMyOrder getYyPgMyOrder() {
		return yyPgMyOrder;
	}

	public void setYyPgMyOrder(YyPgMyOrder yyPgMyOrder) {
		this.yyPgMyOrder = yyPgMyOrder;
	}
}