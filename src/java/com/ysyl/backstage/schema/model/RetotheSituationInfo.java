package com.ysyl.backstage.schema.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报备导入信息表
 * @author ysyl
 */
@Entity
@Table(name = "RetotheSituationInfo")
public class RetotheSituationInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String reportedNumber;             
	private String submissiondate;             
	private String thebackup;                  
	private String reportedunit;               
	private String reportedphonenumber;        
	private String reportedmodels;             
	private String engineNO;                   
	private String productcode;                
	private String ALLVINNO;                   
	private String VINNO;                      
	private String sold;                       
	private String soldcities;                 
	private String username;                   
	private String connectionway;              
	private String personalname;               
	private String insudernumber;              
	private String personalcontact;            
	private String reportedstated;             
	private String reportedreason;             
	private String reporteddetail;            
	private String drivername;                 
	private String provincessign;              
	private String relateddepartment;          
	private String relevantsignature;          
	private String whetherqualified;           
	private String lastdate;                   
	private String reportedyear;               
	private String reportedmonth;              
	private String squalified;                 
	private String yqualified;                 
	private String doorqualified;               
	private String conclusions;                 
	private String instructions;                
	private String failure;                     
	private String losetrack;                   
	private String reporteddate;                
	private String makeinvoicedate;            
	private String rqualified;                 
	private String invoicebuyer;               
	private String consistent;                 
	private String trailer;                     
	private String agreement;                   
	private String relationship;                
	private String affiliateddangerous;         
	private String approval;                    
	private String information;                 
	private String makeprice;                   
	private String results;                     
	private String reason;                      
	private String rdetail;                      
	private String provideinformation;          
	private String noticedate;                  
	private String thirdphonedheadprice;        
	private String thirdverifyprice;            
	private String thirdcallprices;             
	private String telephoneparty;
	private Date inputtime;
	private String createrName;
	
	/**       
	 * Id
	 */
	@Id
	@Column(name = "reportedNumber")
	public String getReportedNumber() {
		return reportedNumber;
	}
	public void setReportedNumber(String reportedNumber) {
		this.reportedNumber = reportedNumber;
	}
	@Column(name = "submissiondate")
	public String getSubmissiondate() {
		return submissiondate;
	}
	public void setSubmissiondate(String submissiondate) {
		this.submissiondate = submissiondate;
	}
	@Column(name = "thebackup")
	public String getThebackup() {
		return thebackup;
	}
	public void setThebackup(String thebackup) {
		this.thebackup = thebackup;
	}
	@Column(name = "reportedunit")
	public String getReportedunit() {
		return reportedunit;
	}
	public void setReportedunit(String reportedunit) {
		this.reportedunit = reportedunit;
	}
	@Column(name = "reportedphonenumber")
	public String getReportedphonenumber() {
		return reportedphonenumber;
	}
	public void setReportedphonenumber(String reportedphonenumber) {
		this.reportedphonenumber = reportedphonenumber;
	}
	@Column(name = "reportedmodels")
	public String getReportedmodels() {
		return reportedmodels;
	}
	public void setReportedmodels(String reportedmodels) {
		this.reportedmodels = reportedmodels;
	}
	@Column(name = "engineNO")
	public String getEngineNO() {
		return engineNO;
	}
	public void setEngineNO(String engineNO) {
		this.engineNO = engineNO;
	}
	@Column(name = "productcode")
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	@Column(name = "ALLVINNO")
	public String getALLVINNO() {
		return ALLVINNO;
	}
	public void setALLVINNO(String aLLVINNO) {
		ALLVINNO = aLLVINNO;
	}
	@Column(name = "VINNO")
	public String getVINNO() {
		return VINNO;
	}
	public void setVINNO(String vINNO) {
		VINNO = vINNO;
	}
	@Column(name = "sold")
	public String getSold() {
		return sold;
	}
	public void setSold(String sold) {
		this.sold = sold;
	}
	@Column(name = "soldcities")
	public String getSoldcities() {
		return soldcities;
	}
	public void setSoldcities(String soldcities) {
		this.soldcities = soldcities;
	}
	@Column(name = "username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "connectionway")
	public String getConnectionway() {
		return connectionway;
	}
	public void setConnectionway(String connectionway) {
		this.connectionway = connectionway;
	}
	@Column(name = "personalname")
	public String getPersonalname() {
		return personalname;
	}
	public void setPersonalname(String personalname) {
		this.personalname = personalname;
	}
	@Column(name = "insudernumber")
	public String getInsudernumber() {
		return insudernumber;
	}
	public void setInsudernumber(String insudernumber) {
		this.insudernumber = insudernumber;
	}
	@Column(name = "personalcontact")
	public String getPersonalcontact() {
		return personalcontact;
	}
	public void setPersonalcontact(String personalcontact) {
		this.personalcontact = personalcontact;
	}
	@Column(name = "reportedstated")
	public String getReportedstated() {
		return reportedstated;
	}
	public void setReportedstated(String reportedstated) {
		this.reportedstated = reportedstated;
	}
	@Column(name = "reportedreason")
	public String getReportedreason() {
		return reportedreason;
	}
	public void setReportedreason(String reportedreason) {
		this.reportedreason = reportedreason;
	}
	@Column(name = "reporteddetail")
	public String getReporteddetail() {
		return reporteddetail;
	}
	public void setReporteddetail(String reporteddetail) {
		this.reporteddetail = reporteddetail;
	}
	@Column(name = "drivername")
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	@Column(name = "provincessign")
	public String getProvincessign() {
		return provincessign;
	}
	public void setProvincessign(String provincessign) {
		this.provincessign = provincessign;
	}
	@Column(name = "relateddepartment")
	public String getRelateddepartment() {
		return relateddepartment;
	}
	public void setRelateddepartment(String relateddepartment) {
		this.relateddepartment = relateddepartment;
	}
	@Column(name = "relevantsignature")
	public String getRelevantsignature() {
		return relevantsignature;
	}
	public void setRelevantsignature(String relevantsignature) {
		this.relevantsignature = relevantsignature;
	}
	@Column(name = "whetherqualified")
	public String getWhetherqualified() {
		return whetherqualified;
	}
	public void setWhetherqualified(String whetherqualified) {
		this.whetherqualified = whetherqualified;
	}
	@Column(name = "lastdate")
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	@Column(name = "reportedyear")
	public String getReportedyear() {
		return reportedyear;
	}
	public void setReportedyear(String reportedyear) {
		this.reportedyear = reportedyear;
	}
	@Column(name = "reportedmonth")
	public String getReportedmonth() {
		return reportedmonth;
	}
	public void setReportedmonth(String reportedmonth) {
		this.reportedmonth = reportedmonth;
	}
	@Column(name = "squalified")
	public String getSqualified() {
		return squalified;
	}
	public void setSqualified(String squalified) {
		this.squalified = squalified;
	}
	@Column(name = "yqualified")
	public String getYqualified() {
		return yqualified;
	}
	public void setYqualified(String yqualified) {
		this.yqualified = yqualified;
	}
	@Column(name = "doorqualified")
	public String getDoorqualified() {
		return doorqualified;
	}
	public void setDoorqualified(String doorqualified) {
		this.doorqualified = doorqualified;
	}
	@Column(name = "conclusions")
	public String getConclusions() {
		return conclusions;
	}
	public void setConclusions(String conclusions) {
		this.conclusions = conclusions;
	}
	@Column(name = "instructions")
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	@Column(name = "failure")
	public String getFailure() {
		return failure;
	}
	public void setFailure(String failure) {
		this.failure = failure;
	}
	@Column(name = "losetrack")
	public String getLosetrack() {
		return losetrack;
	}
	public void setLosetrack(String losetrack) {
		this.losetrack = losetrack;
	}
	@Column(name = "reporteddate")
	public String getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(String reporteddate) {
		this.reporteddate = reporteddate;
	}
	@Column(name = "makeinvoicedate")
	public String getMakeinvoicedate() {
		return makeinvoicedate;
	}
	public void setMakeinvoicedate(String makeinvoicedate) {
		this.makeinvoicedate = makeinvoicedate;
	}
	@Column(name = "rqualified")
	public String getRqualified() {
		return rqualified;
	}
	public void setRqualified(String rqualified) {
		this.rqualified = rqualified;
	}
	@Column(name = "invoicebuyer")
	public String getInvoicebuyer() {
		return invoicebuyer;
	}
	public void setInvoicebuyer(String invoicebuyer) {
		this.invoicebuyer = invoicebuyer;
	}
	@Column(name = "consistent")
	public String getConsistent() {
		return consistent;
	}
	public void setConsistent(String consistent) {
		this.consistent = consistent;
	}
	@Column(name = "trailer")
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	@Column(name = "agreement")
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	@Column(name = "relationship")
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(name = "affiliateddangerous")
	public String getAffiliateddangerous() {
		return affiliateddangerous;
	}
	public void setAffiliateddangerous(String affiliateddangerous) {
		this.affiliateddangerous = affiliateddangerous;
	}
	@Column(name = "approval")
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	@Column(name = "information")
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	@Column(name = "makeprice")
	public String getMakeprice() {
		return makeprice;
	}
	public void setMakeprice(String makeprice) {
		this.makeprice = makeprice;
	}
	@Column(name = "results")
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	@Column(name = "reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "rdetail")
	public String getRdetail() {
		return rdetail;
	}
	public void setRdetail(String rdetail) {
		this.rdetail = rdetail;
	}
	@Column(name = "provideinformation")
	public String getProvideinformation() {
		return provideinformation;
	}
	public void setProvideinformation(String provideinformation) {
		this.provideinformation = provideinformation;
	}
	@Column(name = "noticedate")
	public String getNoticedate() {
		return noticedate;
	}
	public void setNoticedate(String noticedate) {
		this.noticedate = noticedate;
	}
	@Column(name = "thirdphonedheadprice")
	public String getThirdphonedheadprice() {
		return thirdphonedheadprice;
	}
	public void setThirdphonedheadprice(String thirdphonedheadprice) {
		this.thirdphonedheadprice = thirdphonedheadprice;
	}
	@Column(name = "thirdverifyprice")
	public String getThirdverifyprice() {
		return thirdverifyprice;
	}
	public void setThirdverifyprice(String thirdverifyprice) {
		this.thirdverifyprice = thirdverifyprice;
	}
	@Column(name = "thirdcallprices")
	public String getThirdcallprices() {
		return thirdcallprices;
	}
	public void setThirdcallprices(String thirdcallprices) {
		this.thirdcallprices = thirdcallprices;
	}
	@Column(name = "telephoneparty")
	public String getTelephoneparty() {
		return telephoneparty;
	}
	public void setTelephoneparty(String telephoneparty) {
		this.telephoneparty = telephoneparty;
	}
	@Column(name = "inputtime")
	public Date getInputtime() {
		return inputtime;
	}
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}
	@Column(name = "createrName")
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
}
