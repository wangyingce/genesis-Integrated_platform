package com.ysyl.search.vo;

public class SearchQueryVo {
	private String _id;

	/** 保单号 */
	private String policyNo;
	
	/** 类型Q报价 T投保 C保单 P批单 */
	private String certiType;
	
	/** 投保单号 */
	private String proposalNo;

	/** 号牌号码 */
	private String licenseNo;

	/** VIN码 */
	private String vinNo;

	/** 车架号 */
	private String frameNo;

	/** 行驶证车型名称 */	
	private String brandName;

	/** 发动机号 */
	private String engineNo;
	
	/** 被保险人 */
	private String insuredName;

	/** 投保日期/报价时间 */
	private String operateDate;
	
	private Long operateDateTs;

	/** 起保日期 */
	private String startDate;
	
	private Long startDateTs;

	/** 终保日期 */
	private String endDate;
	
	private Long endDateTs;

	/** 归属机构代码 */
	private String comCode;

	/**  归属机构名称 */
	private String comName;

	/** 归属机构树 */
	private String comCodeTree;

	/** 系统来源 */
	private String systemSourceCode;

	/** 保单印刷号 */
	private String policyPrintNo;

	/** 是否打印 */
	private String printFlag;

	/** 保单状态/报价单状态 */
	private String policyState;

	/** 查询结果排序 */
	private String orderFlag;

	/** 核保状态 */
	private String underWriteFlag;

	/** 投保人 */
	private String apliName;

	/** 归属业务员代码 */
	private String handler1Code;

	/** 归属业务员名称 */
	private String handler1Name;

	/** 代理/经纪代码 */
	private String agentCode;

	/** 代理/经纪名称 */
	private String agentName;

	/** 险种代码 */
	private String riskCode;

	/** 险种名称 */
	private String riskName;

	/** 出单机构 */
	private String makeCom;

	/** 出单机构名称 */
	private String makeName;

	/** 操作员代码 */
	private String operatorCode;

	/** 操作员名称 */
	private String operatorName;

	/** 提核日期 */
	private String undwrtSubmitDate;
	
	private Long undwrtSubmitDateTs;

	/** 签单日期 */
	private String signDate;
	
	private Long signDateTs;

	/** 查看退票信息 */
	private String payBackFlag;

	/**  核保人 */
	private String underwriteCode;

	/** 核保人名称 */
	private String underwriteName;

	/** 保费 */
	private String sumPremium;

	/** 保额 */
	private String sumAmount;

	/** 报价单号 */
	private String quotationNo;

	/** 保单组合 */
	private String itemPlan;
	
	/** 所保险别代码 */
	private String kindCodes;
	
	/** 所保险别名称	*/
	private String kindNames;
	/**批改日期*/
	private String endorDate;
	
	/** 备用字段 */
	private String flag;
	
	/** 有效标志 */
	private String valid;
	
	private Long endorDateTs;
	/**生效日期*/
	private String validDate;
	
	private Long validDateTs;
	/**批单申请号*/
	private String applyNo;
	/**批单号*/
	private String endorseNo;
	/**保费变化量*/
	private String chgpremium;
	/**批改类型*/
	private String endorType;
	/**批单印刷号*/
	private String visaNo;
	/** 是否已上牌照 */
	private String licenseFlag;
	/**是否新车*/
	private String newCarFlag;
	
	/** 是否关联批改标识 0否1是，默认为0 */
	private String isRelateEndorseFlag;
	
	/** 关联批单申请号 */
	private String relateApplyNo;
	
	/** 交商联动类型：0-单保商业；1-单保交强；2-交商联投；3-单保商业匹配交强；4-单保交强匹配商业 */
	private String correlateType;
	
	/** 关联单号 */
	private String mainSubNo;
	
	/** 交商联动单号 */
	private String correlateNo;
	
	/** 支付申请号 */
	private String payAppNo;

	/**批改次数*/
	private String endorseTimes;
	
	/**车船税总额*/
	private String sumPayTax;
	
	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	public String getEndorType() {
		return endorType;
	}

	public void setEndorType(String endorType) {
		this.endorType = endorType;
	}

	public String getChgpremium() {
		return chgpremium;
	}

	public void setChgpremium(String chgpremium) {
		this.chgpremium = chgpremium;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getEndorseNo() {
		return endorseNo;
	}

	public void setEndorseNo(String endorseNo) {
		this.endorseNo = endorseNo;
	}

	public String getEndorDate() {
		return endorDate;
	}

	public void setEndorDate(String endorDate) {
		this.endorDate = endorDate;
	}

	public Long getEndorDateTs() {
		return endorDateTs;
	}

	public void setEndorDateTs(Long endorDateTs) {
		this.endorDateTs = endorDateTs;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public Long getValidDateTs() {
		return validDateTs;
	}

	public void setValidDateTs(Long validDateTs) {
		this.validDateTs = validDateTs;
	}

	public String getKindCodes() {
		return kindCodes;
	}

	public void setKindCodes(String kindCodes) {
		this.kindCodes = kindCodes;
	}

	public String getKindNames() {
		return kindNames;
	}

	public void setKindNames(String kindNames) {
		this.kindNames = kindNames;
	}

	public String getItemPlan() {
		return itemPlan;
	}

	public void setItemPlan(String itemPlan) {
		this.itemPlan = itemPlan;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getApliName() {
		return apliName;
	}

	public void setApliName(String apliName) {
		this.apliName = apliName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComCodeTree() {
		return comCodeTree;
	}

	public void setComCodeTree(String comCodeTree) {
		this.comCodeTree = comCodeTree;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getFrameNo() {
		return frameNo;
	}

	public void setFrameNo(String frameNo) {
		this.frameNo = frameNo;
	}

	public String getHandler1Code() {
		return handler1Code;
	}

	public void setHandler1Code(String handler1Code) {
		this.handler1Code = handler1Code;
	}

	public String getHandler1Name() {
		return handler1Name;
	}

	public void setHandler1Name(String handler1Name) {
		this.handler1Name = handler1Name;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getMakeCom() {
		return makeCom;
	}

	public void setMakeCom(String makeCom) {
		this.makeCom = makeCom;
	}

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getPayBackFlag() {
		return payBackFlag;
	}

	public void setPayBackFlag(String payBackFlag) {
		this.payBackFlag = payBackFlag;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyPrintNo() {
		return policyPrintNo;
	}

	public void setPolicyPrintNo(String policyPrintNo) {
		this.policyPrintNo = policyPrintNo;
	}

	public String getPolicyState() {
		return policyState;
	}

	public void setPolicyState(String policyState) {
		this.policyState = policyState;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getSumPremium() {
		return sumPremium;
	}

	public void setSumPremium(String sumPremium) {
		this.sumPremium = sumPremium;
	}

	public String getSystemSourceCode() {
		return systemSourceCode;
	}

	public void setSystemSourceCode(String systemSourceCode) {
		this.systemSourceCode = systemSourceCode;
	}

	public String getUnderwriteCode() {
		return underwriteCode;
	}

	public void setUnderwriteCode(String underwriteCode) {
		this.underwriteCode = underwriteCode;
	}

	public String getUnderWriteFlag() {
		return underWriteFlag;
	}

	public void setUnderWriteFlag(String underWriteFlag) {
		this.underWriteFlag = underWriteFlag;
	}

	public String getUnderwriteName() {
		return underwriteName;
	}

	public void setUnderwriteName(String underwriteName) {
		this.underwriteName = underwriteName;
	}

	public String getUndwrtSubmitDate() {
		return undwrtSubmitDate;
	}

	public void setUndwrtSubmitDate(String undwrtSubmitDate) {
		this.undwrtSubmitDate = undwrtSubmitDate;
	}

	public String getVinNo() {
		return vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public Long getEndDateTs() {
		return endDateTs;
	}

	public void setEndDateTs(Long endDateTs) {
		this.endDateTs = endDateTs;
	}

	public Long getOperateDateTs() {
		return operateDateTs;
	}

	public void setOperateDateTs(Long operateDateTs) {
		this.operateDateTs = operateDateTs;
	}

	public Long getSignDateTs() {
		return signDateTs;
	}

	public void setSignDateTs(Long signDateTs) {
		this.signDateTs = signDateTs;
	}

	public Long getStartDateTs() {
		return startDateTs;
	}

	public void setStartDateTs(Long startDateTs) {
		this.startDateTs = startDateTs;
	}

	public Long getUndwrtSubmitDateTs() {
		return undwrtSubmitDateTs;
	}

	public void setUndwrtSubmitDateTs(Long undwrtSubmitDateTs) {
		this.undwrtSubmitDateTs = undwrtSubmitDateTs;
	}

	public String getCertiType() {
		return certiType;
	}

	public void setCertiType(String certiType) {
		this.certiType = certiType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getLicenseFlag() {
		return licenseFlag;
	}

	public void setLicenseFlag(String licenseFlag) {
		this.licenseFlag = licenseFlag;
	}

	public String getNewCarFlag() {
		return newCarFlag;
	}

	public void setNewCarFlag(String newCarFlag) {
		this.newCarFlag = newCarFlag;
	}

	public String getIsRelateEndorseFlag() {
		return isRelateEndorseFlag;
	}

	public void setIsRelateEndorseFlag(String isRelateEndorseFlag) {
		this.isRelateEndorseFlag = isRelateEndorseFlag;
	}

	public String getRelateApplyNo() {
		return relateApplyNo;
	}

	public void setRelateApplyNo(String relateApplyNo) {
		this.relateApplyNo = relateApplyNo;
	}

	public String getCorrelateNo() {
		return correlateNo;
	}

	public void setCorrelateNo(String correlateNo) {
		this.correlateNo = correlateNo;
	}

	public String getCorrelateType() {
		return correlateType;
	}

	public void setCorrelateType(String correlateType) {
		this.correlateType = correlateType;
	}

	public String getMainSubNo() {
		return mainSubNo;
	}

	public void setMainSubNo(String mainSubNo) {
		this.mainSubNo = mainSubNo;
	}

	public String getPayAppNo() {
		return payAppNo;
	}

	public void setPayAppNo(String payAppNo) {
		this.payAppNo = payAppNo;
	}

	public String getEndorseTimes() {
		return endorseTimes;
	}

	public void setEndorseTimes(String endorseTimes) {
		this.endorseTimes = endorseTimes;
	}

	public String getSumPayTax() {
		return sumPayTax;
	}

	public void setSumPayTax(String sumPayTax) {
		this.sumPayTax = sumPayTax;
	}

}
