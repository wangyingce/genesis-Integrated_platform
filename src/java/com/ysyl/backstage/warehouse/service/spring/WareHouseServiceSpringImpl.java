package com.ysyl.backstage.warehouse.service.spring;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.ysyl.backstage.schema.model.RetotheSituationInfo;
import com.ysyl.backstage.schema.model.WhAccessory;
import com.ysyl.backstage.schema.model.WhCar;
import com.ysyl.backstage.schema.model.WhCarSeries;
import com.ysyl.backstage.warehouse.service.facade.WareHouseService;

public class WareHouseServiceSpringImpl extends GenericDaoHibernate<WhCarSeries, String> implements WareHouseService {

	public Long saveCarSeries(WhCarSeries whCarSeries) {
		super.save(whCarSeries);
		return whCarSeries.getCarserId();
	}

	public List<WhCarSeries> findCarSeriesByAll(Long carserId,String carserName, String carserMinPrice, String carserMaxPrice) {
		QueryRule qr = QueryRule.getInstance();
		if(carserId!=null&&!"".equals(carserId)){
			qr.addEqual("carserId", new Long(carserId));
		}
		if(carserName!=null&&!"".equals(carserName)){
			qr.addLike("carserName", carserName+"%");
		}
		if(carserMinPrice!=null&&!"".equals(carserMinPrice)){
			qr.addGreaterThan("carserMinPrice", carserMinPrice);
		}
		if(carserMaxPrice!=null&&!"".equals(carserMaxPrice)){
			qr.addEqual("carserMaxPrice", carserMaxPrice);
		}
		List<WhCarSeries> list  = super.find(WhCarSeries.class,qr);
		return list;
	}

	public WhCarSeries findCarSeriesById(Long carserId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("carserId", carserId);
		WhCarSeries cs  = super.findUnique(qr);
		return cs;
	}

	public void delCarSeries(WhCarSeries cs) {
		super.delete(cs);		
	}

	public WhCar findCarById(Long carId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("carId", carId);
		WhCar car  = super.findUnique(WhCar.class,qr);
		return car;
	}

	public Long saveCar(WhCar whCar) {
		super.save(whCar);
		return whCar.getCarId();
	}

	public List<WhCar> findCarByAll(Long carId, String carName,String carStandPrice, Long carserId) {
		QueryRule qr = QueryRule.getInstance();
		if(carId!=null&&!"".equals(carId)){
			qr.addEqual("carId", new Long(carId));
		}
		if(carserId!=null&&!"".equals(carserId)){
			qr.addEqual("carserId", new Long(carserId));
		}
		if(carName!=null&&!"".equals(carName)){
			qr.addLike("carName", carName+"%");
		}
		if(carStandPrice!=null&&!"".equals(carStandPrice)){
			qr.addGreaterThan("carStandPrice", carStandPrice);
		}
		List<WhCar> list  = super.find(WhCar.class,qr);
		return list;
	}

	public void delCar(WhCar cr) {
		super.delete(cr);		
	}

	public WhAccessory findAccessoryById(Long accessoryId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("accessoryId", accessoryId);
		WhAccessory acs  = super.findUnique(WhAccessory.class,qr);
		return acs;
	}

	public List<WhAccessory> findAccessoryByAll(Long accessoryId,String accessoryName, String accessoryStandPrice, Long carserId) {
		QueryRule qr = QueryRule.getInstance();
		if(accessoryId!=null&&!"".equals(accessoryId)){
			qr.addEqual("accessoryId", new Long(accessoryId));
		}
		if(carserId!=null&&!"".equals(carserId)){
			qr.addEqual("carserId", new Long(carserId));
		}
		if(accessoryName!=null&&!"".equals(accessoryName)){
			qr.addLike("accessoryName", accessoryName+"%");
		}
		if(accessoryStandPrice!=null&&!"".equals(accessoryStandPrice)){
			qr.addGreaterThan("accessoryStandPrice", accessoryStandPrice);
		}
		List<WhAccessory> list  = super.find(WhAccessory.class,qr);
		return list;
	}

	public Long saveAccessory(WhAccessory accessory) {
		super.save(accessory);
		return accessory.getAccessoryId();
	}

	public void delAccessory(WhAccessory acc) {
		super.delete(acc);	
	}

	public void defWhObject(Object standObject) {
		if(standObject instanceof WhCar){
			WhCar cr = (WhCar) standObject;
			super.delete(cr);	
		}
		if(standObject instanceof WhAccessory){
			WhAccessory acs = (WhAccessory) standObject;
			super.delete(acs);	
		}
		if(standObject instanceof WhCarSeries){
			WhCarSeries cs = (WhCarSeries) standObject;
			super.delete(cs);	
		}
	}

	public void setBasicDataByExcel(File file) {
		// TODO Auto-generated method stub
		List<RetotheSituationInfo> basicDataByExcel = new ArrayList<RetotheSituationInfo>();
        try {
            Workbook rwb=Workbook.getWorkbook(file); 
            
//            System.out.println(rwb);
//            Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
            Sheet rs = rwb.getSheet(0);
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            
//            System.out.println("clos==="+clos+" rows:"+rows);
            for (int i = 0; i < rows-1; i++) {
            	RetotheSituationInfo ionInfo = new RetotheSituationInfo();
                	System.out.println(rs.getCell(0, i+1).getContents()+"-----------"); 
                	System.out.println(rs.getCell(1, i+1).getContents()+"-----------"); 

                	ionInfo.setReportedNumber(rs.getCell(0, i+1).getContents());
                	ionInfo.setSubmissiondate(rs.getCell(1, i+1).getContents());
                	ionInfo.setThebackup(rs.getCell(2, i+1).getContents());
                	ionInfo.setReportedunit(rs.getCell(3, i+1).getContents());
                	ionInfo.setReportedphonenumber(rs.getCell(4, i+1).getContents());
                	ionInfo.setReportedmodels(rs.getCell(5, i+1).getContents());
                	ionInfo.setEngineNO(rs.getCell(6, i+1).getContents());
                	ionInfo.setProductcode(rs.getCell(7, i+1).getContents());
                	ionInfo.setALLVINNO(rs.getCell(8, i+1).getContents());
                	ionInfo.setVINNO(rs.getCell(9, i+1).getContents());
                	ionInfo.setSold(rs.getCell(10, i+1).getContents());
                	ionInfo.setSoldcities(rs.getCell(11, i+1).getContents());
                	ionInfo.setUsername(rs.getCell(12, i+1).getContents());
                	ionInfo.setConnectionway(rs.getCell(13, i+1).getContents());
                	ionInfo.setPersonalname(rs.getCell(14, i+1).getContents());
                	ionInfo.setInsudernumber(rs.getCell(15, i+1).getContents());
                	ionInfo.setPersonalcontact(rs.getCell(16, i+1).getContents());
                	ionInfo.setReportedstated(rs.getCell(17, i+1).getContents());
                	ionInfo.setReportedreason(rs.getCell(18, i+1).getContents());
                	ionInfo.setReporteddetail(rs.getCell(19, i+1).getContents());
                	ionInfo.setDrivername(rs.getCell(20, i+1).getContents());
                	ionInfo.setProvincessign(rs.getCell(21, i+1).getContents());
                	ionInfo.setRelateddepartment(rs.getCell(22, i+1).getContents());
                	ionInfo.setRelevantsignature(rs.getCell(23, i+1).getContents());
                	ionInfo.setWhetherqualified(rs.getCell(24, i+1).getContents());
                	ionInfo.setLastdate(rs.getCell(25, i+1).getContents());
                	ionInfo.setReportedyear(rs.getCell(26, i+1).getContents());
                	ionInfo.setReportedmonth(rs.getCell(27, i+1).getContents());
                	ionInfo.setSqualified(rs.getCell(28, i+1).getContents());
                	ionInfo.setYqualified(rs.getCell(29, i+1).getContents());
                	ionInfo.setDoorqualified(rs.getCell(30, i+1).getContents());
                	ionInfo.setConclusions(rs.getCell(31, i+1).getContents());
                	ionInfo.setInstructions(rs.getCell(32, i+1).getContents());
                	ionInfo.setFailure(rs.getCell(33, i+1).getContents());
                	ionInfo.setLosetrack(rs.getCell(34, i+1).getContents());
                	ionInfo.setReporteddate(rs.getCell(35, i+1).getContents());
                	ionInfo.setMakeinvoicedate(rs.getCell(36, i+1).getContents());
                	ionInfo.setRqualified(rs.getCell(37, i+1).getContents());
                	ionInfo.setInvoicebuyer(rs.getCell(38, i+1).getContents());
                	ionInfo.setConsistent(rs.getCell(39, i+1).getContents());
                	ionInfo.setTrailer(rs.getCell(40, i+1).getContents());
                	ionInfo.setAgreement(rs.getCell(41, i+1).getContents());
                	ionInfo.setRelationship(rs.getCell(42, i+1).getContents());
                	ionInfo.setAffiliateddangerous(rs.getCell(43, i+1).getContents());
                	ionInfo.setApproval(rs.getCell(44, i+1).getContents());
                	ionInfo.setInformation(rs.getCell(45, i+1).getContents());
                	ionInfo.setMakeprice(rs.getCell(46, i+1).getContents());
                	ionInfo.setResults(rs.getCell(47, i+1).getContents());
                	ionInfo.setReason(rs.getCell(48, i+1).getContents());
                	ionInfo.setRdetail(rs.getCell(49, i+1).getContents());
                	ionInfo.setProvideinformation(rs.getCell(50, i+1).getContents());
                	ionInfo.setNoticedate(rs.getCell(51, i+1).getContents());
                	ionInfo.setThirdphonedheadprice(rs.getCell(52, i+1).getContents());
                	ionInfo.setThirdverifyprice(rs.getCell(53, i+1).getContents());
                	ionInfo.setThirdcallprices(rs.getCell(54, i+1).getContents());
                	ionInfo.setTelephoneparty(rs.getCell(55, i+1).getContents());
                	basicDataByExcel.add(ionInfo);
                	
            }
            this.saveAll(basicDataByExcel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
	}
}
