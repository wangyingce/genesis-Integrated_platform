package com.ysyl.mobile.photograph.service.facade;

import java.util.List;

import com.ysyl.common.schema.model.YyPay;
import com.ysyl.common.schema.model.YyPgIndex;
import com.ysyl.common.schema.model.YyPgMyOrder;
import com.ysyl.common.schema.model.YyPgOrderDate;
import com.ysyl.common.schema.model.YyPgOrderDetail;
import com.ysyl.common.schema.model.YyPgOrderTime;
import com.ysyl.mobile.photograph.vo.PhotoGraphVo;



public interface PhotoGraphService{
	
	/**
	 * 发送短信验证码
	 * @param photoGraphVo 传送对象
	 */
	public PhotoGraphVo getMessageIdCode(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 验证验证码
	 */
	public PhotoGraphVo checkIdCode(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 摄影工作室注册提交
	 * @param photoGraphVo 传送对象
	 */
	public PhotoGraphVo submitRegistPhotoGraph(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 初始化摄影工作室预约页面信息
	 * @param photoGraphVo 传送对象
	 */
	public PhotoGraphVo initPgCheckOrderInfo(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 根据城市，照片类型，商户id初始化出预约详细说明
	 * @param photoGraphVo 传送对象
	 */
	public YyPgOrderDetail getOrderDetailByCPO(String city,String photoType,String owner) throws Exception;
	
	/**
	 * 初始化摄影工作室预约页面信息（只查最近30天）
	 * @param city 城市
	 * @param photoType 照片类型
	 */
	public List<YyPgOrderDate> findPgCheckDateByCityAndPhotoType(String city,String photoType,String owner) throws Exception;
	
	/**
	 * 预约页面提交
	 * @param photoGraphVo 传送对象 
	 */
	public PhotoGraphVo submitPgCheckOrder(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 根据ID查找预约时间表
	 * @param Id
	 * @return YyPgOrderTime
	 * @throws Exception
	 */
	public YyPgOrderTime findOrderTimeById(Long Id) throws Exception;
	
	/**
	 * 根据起止日期生成对应商户的yypgorderdate 和 yypgordertime表
	 * @throws Exception
	 */
	public void generateDateAndTime(String pgBeginDate,String pgEndDate,String owner) throws Exception;
	
	/**
	 * 初始化我的订单信息
	 * @param payState 支付状态 ALL-代表全查
	 * @throws Exception
	 */
	public PhotoGraphVo initMyOrder(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 微信支付回调方法
	 * @throws Exception
	 * @return 商户中文名称 
	 */
	public YyPgMyOrder PayBack(YyPay yyPay) throws Exception;
	
	/**
	 * 查询我的订单信息
	 * @param userCode
	 * @param streoId
	 * @param payState
	 * @return
	 */
	public List<YyPgMyOrder> findYyPgMyOrderByUserCodeAndStoreIdAndPayState(String userCode,String streoId,String payState) throws Exception;
	
	/**
	 * 根据id查询我的订单信息
	 * @throws Exception
	 */
	public YyPgMyOrder findYyPgMyOrderById(Long id) throws Exception;
	
	/**
	 * 取消订单
	 * @throws Exception
	 */
	public PhotoGraphVo cancelMyOrder(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 删除订单
	 * @throws Exception
	 */
	public PhotoGraphVo deleteMyOrder(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 订单页面点击订单支付
	 * @throws Exception
	 */
	public PhotoGraphVo payMyOrder(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 点击推送信息详细页面（商家看到的）
	 */
	public YyPgMyOrder showOrderForOwner(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 展示首页
	 */
	public PhotoGraphVo showIndex(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 根据商户代码查询对应商户首页配置信息
	 */
	public List<YyPgIndex> findYyPgIndexByOwner(String owner) throws Exception;
	
	/**
	 * 商户确认客户订单
	 */
	public PhotoGraphVo confirmOrder(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 获取我的底片
	 */
	public PhotoGraphVo getMyPhoto(PhotoGraphVo photoGraphVo) throws Exception;
	
	/**
	 * 获取学生照片
	 */
	public PhotoGraphVo getStudentPhoto(PhotoGraphVo photoGraphVo) throws Exception;
}
