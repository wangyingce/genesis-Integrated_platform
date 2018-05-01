package com.ysyl.common.verify.service.facade;

import com.ysyl.common.verify.VerifyVo;

public interface VerifyService {
	/***
	 * 根据手机号及类型获取验证码
	 * @param mobile 手机号必传
	 * @param type必传 类型 1-商户注册
	 * @return 验证码对象
	 */
	public VerifyVo receiveVerify(VerifyVo verifyVo) throws Exception ;
	
	/***
	 * 验证码验证
	 * @param mobile 手机号必传
	 * @param type必传 类型 1-商户注册
	 * @param verifyNo验证码必传
	 * @return 验证码传输类
	 */
	public VerifyVo confirmVerify(VerifyVo verifyVo) throws Exception ;
	
	/***
	 * 验证码验证(新)
	 * @param mobile 手机号必传
	 * @param type必传 类型 1-商户注册 2-摄影工作室注册
	 * @param verifyNo验证码必传
	 * @return 验证码传输类
	 */
	public VerifyVo checkVerifyNo(VerifyVo verifyVo) throws Exception ;
	
}
