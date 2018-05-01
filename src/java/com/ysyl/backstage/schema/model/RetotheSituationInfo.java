package com.ysyl.backstage.schema.model;

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

	private String ReportedNumber;             
	private String Submissiondate;             
	private String Thebackup;                  
	private String Reportedunit;               
	private String Reportedphonenumber;        
	private String Reportedmodels;             
	private String EngineNO;                   
	private String Productcode;                
	private String ALLVINNO;                   
	private String VINNO;                      
	private String Sold;                       
	private String Soldcities;                 
	private String username;                   
	private String connectionway;              
	private String personalname;               
	private String insudernumber;              
	private String personalcontact;            
	private String Reportedstated;             
	private String Reportedreason;             
	private String Reporteddetail;            
	private String Drivername;                 
	private String provincessign;              
	private String Relateddepartment;          
	private String Relevantsignature;          
	private String Whetherqualified;           
	private String lastdate;                   
	private String Reportedyear;               
	private String Reportedmonth;              
	private String squalified;                 
	private String yqualified;                 
	private String doorqualified;               
	private String conclusions;                 
	private String instructions;                
	private String failure;                     
	private String losetrack;                   
	private String Reporteddate;                
	private String Makeinvoicedate;            
	private String rqualified;                 
	private String Invoicebuyer;               
	private String consistent;                 
	private String trailer;                     
	private String agreement;                   
	private String relationship;                
	private String Affiliateddangerous;         
	private String approval;                    
	private String information;                 
	private String Makeprice;                   
	private String results;                     
	private String reason;                      
	private String rdetail;                      
	private String provideinformation;          
	private String Noticedate;                  
	private String thirdphonedheadprice;        
	private String thirdverifyprice;            
	private String Thirdcallprices;             
	private String Telephoneparty;
	
	/**       
	 * Id
	 */
	@Id
	@Column(name = "ReportedNumber")
	public String getReportedNumber() {
		return ReportedNumber;
	}
	public void setReportedNumber(String reportedNumber) {
		ReportedNumber = reportedNumber;
	}
	@Column(name = "Submissiondate")
	public String getSubmissiondate() {
		return Submissiondate;
	}
	public void setSubmissiondate(String submissiondate) {
		Submissiondate = submissiondate;
	}
	@Column(name = "Thebackup")
	public String getThebackup() {
		return Thebackup;
	}
	public void setThebackup(String thebackup) {
		Thebackup = thebackup;
	}
	@Column(name = "Reportedunit")
	public String getReportedunit() {
		return Reportedunit;
	}
	public void setReportedunit(String reportedunit) {
		Reportedunit = reportedunit;
	}
	@Column(name = "Reportedphonenumber")
	public String getReportedphonenumber() {
		return Reportedphonenumber;
	}
	public void setReportedphonenumber(String reportedphonenumber) {
		Reportedphonenumber = reportedphonenumber;
	}
	@Column(name = "Reportedmodels")
	public String getReportedmodels() {
		return Reportedmodels;
	}
	public void setReportedmodels(String reportedmodels) {
		Reportedmodels = reportedmodels;
	}
	@Column(name = "EngineNO")
	public String getEngineNO() {
		return EngineNO;
	}
	public void setEngineNO(String engineNO) {
		EngineNO = engineNO;
	}
	@Column(name = "Productcode")
	public String getProductcode() {
		return Productcode;
	}
	public void setProductcode(String productcode) {
		Productcode = productcode;
	}
	@Column(name = "ALLVINNO")
	public String getALLVINNO() {
		return ALLVINNO;
	}
	public void setALLVINNO(String allvinno) {
		ALLVINNO = allvinno;
	}
	@Column(name = "VINNO")
	public String getVINNO() {
		return VINNO;
	}
	public void setVINNO(String vinno) {
		VINNO = vinno;
	}
	@Column(name = "Sold")
	public String getSold() {
		return Sold;
	}
	public void setSold(String sold) {
		Sold = sold;
	}
	@Column(name = "Soldcities")
	public String getSoldcities() {
		return Soldcities;
	}
	public void setSoldcities(String soldcities) {
		Soldcities = soldcities;
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
	@Column(name = "Reportedstated")
	public String getReportedstated() {
		return Reportedstated;
	}
	public void setReportedstated(String reportedstated) {
		Reportedstated = reportedstated;
	}
	@Column(name = "Reportedreason")
	public String getReportedreason() {
		return Reportedreason;
	}
	public void setReportedreason(String reportedreason) {
		Reportedreason = reportedreason;
	}
	@Column(name = "Reporteddetail")
	public String getReporteddetail() {
		return Reporteddetail;
	}
	public void setReporteddetail(String reporteddetail) {
		Reporteddetail = reporteddetail;
	}
	@Column(name = "Drivername")
	public String getDrivername() {
		return Drivername;
	}
	public void setDrivername(String drivername) {
		Drivername = drivername;
	}
	@Column(name = "provincessign")
	public String getProvincessign() {
		return provincessign;
	}
	public void setProvincessign(String provincessign) {
		this.provincessign = provincessign;
	}
	@Column(name = "Relateddepartment")
	public String getRelateddepartment() {
		return Relateddepartment;
	}
	public void setRelateddepartment(String relateddepartment) {
		Relateddepartment = relateddepartment;
	}
	@Column(name = "Relevantsignature")
	public String getRelevantsignature() {
		return Relevantsignature;
	}
	public void setRelevantsignature(String relevantsignature) {
		Relevantsignature = relevantsignature;
	}
	@Column(name = "Whetherqualified")
	public String getWhetherqualified() {
		return Whetherqualified;
	}
	public void setWhetherqualified(String whetherqualified) {
		Whetherqualified = whetherqualified;
	}
	@Column(name = "lastdate")
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	@Column(name = "Reportedyear")
	public String getReportedyear() {
		return Reportedyear;
	}
	public void setReportedyear(String reportedyear) {
		Reportedyear = reportedyear;
	}
	@Column(name = "Reportedmonth")
	public String getReportedmonth() {
		return Reportedmonth;
	}
	public void setReportedmonth(String reportedmonth) {
		Reportedmonth = reportedmonth;
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
	@Column(name = "Reporteddate")
	public String getReporteddate() {
		return Reporteddate;
	}
	public void setReporteddate(String reporteddate) {
		Reporteddate = reporteddate;
	}
	@Column(name = "Makeinvoicedate")
	public String getMakeinvoicedate() {
		return Makeinvoicedate;
	}
	public void setMakeinvoicedate(String makeinvoicedate) {
		Makeinvoicedate = makeinvoicedate;
	}
	@Column(name = "rqualified")
	public String getRqualified() {
		return rqualified;
	}
	public void setRqualified(String rqualified) {
		this.rqualified = rqualified;
	}
	@Column(name = "Invoicebuyer")
	public String getInvoicebuyer() {
		return Invoicebuyer;
	}
	public void setInvoicebuyer(String invoicebuyer) {
		Invoicebuyer = invoicebuyer;
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
	@Column(name = "Affiliateddangerous")
	public String getAffiliateddangerous() {
		return Affiliateddangerous;
	}
	public void setAffiliateddangerous(String affiliateddangerous) {
		Affiliateddangerous = affiliateddangerous;
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
	@Column(name = "Makeprice")
	public String getMakeprice() {
		return Makeprice;
	}
	public void setMakeprice(String makeprice) {
		Makeprice = makeprice;
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
	@Column(name = "Noticedate")
	public String getNoticedate() {
		return Noticedate;
	}
	public void setNoticedate(String noticedate) {
		Noticedate = noticedate;
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
	@Column(name = "Thirdcallprices")
	public String getThirdcallprices() {
		return Thirdcallprices;
	}
	public void setThirdcallprices(String thirdcallprices) {
		Thirdcallprices = thirdcallprices;
	}
	@Column(name = "Telephoneparty")
	public String getTelephoneparty() {
		return Telephoneparty;
	}
	public void setTelephoneparty(String telephoneparty) {
		Telephoneparty = telephoneparty;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}              

	          



}
