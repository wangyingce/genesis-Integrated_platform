package com.ysyl.backstage.report.service.spring;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;
import ins.framework.utils.DataUtils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ysyl.backstage.order.vo.OrderVo;
import com.ysyl.backstage.product.vo.GenusVo;
import com.ysyl.backstage.report.service.facade.ReportService;
import com.ysyl.backstage.schema.model.YybsGenus;
import com.ysyl.backstage.schema.model.YybsReport;

public class ReportServiceSpringImpl extends GenericDaoHibernate<YybsReport, String> implements ReportService{

	/**
	 * 更新统计分析表数据
	 * @param reportName
	 * @param nation
	 */
	public void saveReportCount(String reportName, String nation) {
		YybsReport rptold =   findEleByReportName(reportName);
		if(rptold!=null){
			if(nation=="1"||"1".equals(nation)){
				rptold.setReportEleOne((new Integer(rptold.getReportEleOne())+1)+"");//国内+1
			}else{
				rptold.setReportEleTwo((new Integer(rptold.getReportEleTwo())+1)+"");//国外+1
			}
			super.save(rptold);
		}else{
			YybsReport rpt = new YybsReport();
			rpt.setReportName(reportName);
			if(nation=="1"||"1".equals(nation)){
				rpt.setReportEleOne(new Integer(1)+"");//国内+1
			}else{
				rpt.setReportEleTwo(new Integer(1)+"");//国外+1
			}
			rpt.setFlag("1");
			rpt.setValidate("1");
			super.save(rpt);
		}
 	}

	/**
	 * 根据reportName查询统计表数据
	 * @param string
	 * @return
	 */
	public YybsReport findEleByReportName(String reportName) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("reportName", reportName);
		List<YybsReport> list  = super.find(YybsReport.class,qr);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 组装统计数据
	 * @param alljson
	 * @param string
	 * @return
	 */
	public JSONObject addJsonData(JSONObject alljson, String type) {
		YybsReport report = findEleByReportName(type);
		if(report!=null){
			if(type=="brand"||"brand".equals(type)){
				if(report.getReportEleOne()!=null&&!"".equals(report.getReportEleOne())){
					alljson.put("ttcn", report.getReportEleOne());//国内品牌总条数
				}else{
					alljson.put("ttcn", "0");
				}
				if(report.getReportEleOne()!=null&&!"".equals(report.getReportEleOne())){
					alljson.put("tten", report.getReportEleTwo());//国外品牌总条数
				}else{
					alljson.put("tten", "0");
				}
					
			}else if(type=="order_percent"||"order_percent".equals(type)){
				if(report.getReportEleOne()!=null&&!"".equals(report.getReportEleOne())){
					String[] porders = report.getReportEleOne().split(",");
			        alljson.put("sp" , porders[0]);
			        alljson.put("fz" , porders[1]);
			        alljson.put("smpj" , porders[2]);
			        alljson.put("sj" , porders[3]);
			        alljson.put("dn" , porders[4]);
			        alljson.put("dzcp" , porders[5]);
			        alljson.put("cfyp" , porders[6]);
			        alljson.put("jydq" , porders[7]);
				}
			}else if(type=="order_map"||"order_map".equals(type)){
				if(report.getReportEleOne()!=null&&!"".equals(report.getReportEleOne())){
					String areaList = report.getReportEleOne();
					String [] areas = areaList.split(",");
					List<OrderVo> orderMaps  = new ArrayList<OrderVo>(0);
					for (int i = 0; i < areas.length; i++) {
						String[] area = areas[i].split("-");
						OrderVo orderVo = new OrderVo();
						orderVo.setName(area[0]);
						orderVo.setValue(new Integer(area[1]));
						orderMaps.add(orderVo);
					}
					JSONArray orderMapJson = JSONArray.fromObject(orderMaps);
					alljson.put("orderMap", orderMapJson);
				}
			}else if(type=="order_amount"||"order_amount".equals(type)){
				alljson.put("jyje", report.getReportEleOne());
				alljson.put("ddsl", report.getReportEleTwo());
				alljson.put("jycg", report.getReportEleThree());
				alljson.put("jysb", report.getReportEleFour());
				alljson.put("tkje", report.getReportEleFive());
			}else if(type=="order"||"order".equals(type)){
				/**所有订单*/
				List<String> allOrders = new ArrayList<String>(0);
				Integer amax = new Integer(0);
				Integer aXAxis = new Integer(0);
				Integer amin = new Integer(0);
				Integer amXAxis = new Integer(0);
				/**待付款订单*/
				List<String> unpaidOrders = new ArrayList<String>(0);
				Integer upmax = new Integer(0);
				Integer upXAxis = new Integer(0);
				Integer upmin = new Integer(0);
				Integer upmXAxis = new Integer(0);
				/**已付款订单*/
				List<String> paidOrders = new ArrayList<String>(0);
				Integer pmax = new Integer(0);
				Integer pXAxis = new Integer(0);
				Integer pmin = new Integer(0);
				Integer pmXAxis = new Integer(0);
				/**待发货订单*/
				List<String> unSend = new ArrayList<String>(0);
				Integer usmax = new Integer(0);
				Integer usXAxis = new Integer(0);
				Integer usmin = new Integer(0);
				Integer usmXAxis = new Integer(0);

				/**所有订单组织数据*/
				String aorder = report.getReportEleOne();
				if(aorder!=null&&!"".equals(aorder)){
					String[] atmp = aorder.split(",");
					for (int i = 0; i < atmp.length; i++) {
						String value = atmp[i].trim();
						allOrders.add(value);
						//计算最大值
						if(amax<(new Integer(value))){
							amax = new Integer(value);
							aXAxis  = i;
						}
						//计算最小值
						if(i==0){
							amin = new Integer(value);
							amXAxis  = i;
						}else if(amin>(new Integer(value))){
							amin = new Integer(value);
							amXAxis  = i;
						}
					}
					JSONArray ajson = JSONArray.fromObject(allOrders);
					alljson.put("ajson", ajson);
					alljson.put("amax", amax);
					alljson.put("aXAxis", aXAxis);
					alljson.put("amin", amin);
					alljson.put("amXAxis", amXAxis);
				}
				/**待付款订单组织数据*/
				String uporder = report.getReportEleTwo();
				if(uporder!=null&&!"".equals(uporder)){
					String[] tmp = uporder.split(",");
					for (int i = 0; i < tmp.length; i++) {
						String value = tmp[i].trim();
						unpaidOrders.add(value);
						//计算最大值
						if(upmax<(new Integer(value))){
							upmax = new Integer(value);
							upXAxis  = i;
						}
						//计算最小值
						if(i==0){
							upmin = new Integer(value);
							upmXAxis  = i;
						}else if(upmin>(new Integer(value))){
							upmin = new Integer(value);
							upmXAxis  = i;
						}
					}
					JSONArray upjson = JSONArray.fromObject(unpaidOrders);
					alljson.put("upjson", upjson);
					alljson.put("upmax", upmax);
					alljson.put("upXAxis", upXAxis);
					alljson.put("upmin", upmin);
					alljson.put("upmXAxis", upmXAxis);
				}
				
				/**已付款订单组织数据*/
				String porder = report.getReportEleThree();
				if(porder!=null&&!"".equals(porder)){
					String[] tmp = porder.split(",");
					for (int i = 0; i < tmp.length; i++) {
						String value = tmp[i].trim();
						paidOrders.add(value);
						//计算最大值
						if(pmax<(new Integer(value))){
							pmax = new Integer(value);
							pXAxis  = i;
						}
						//计算最小值
						if(i==0){
							pmin = new Integer(value);
							pmXAxis  = i;
						}else if(pmin>(new Integer(value))){
							pmin = new Integer(value);
							pmXAxis  = i;
						}
					}
					JSONArray pjson = JSONArray.fromObject(paidOrders);
					alljson.put("pjson", pjson);
					alljson.put("pmax", pmax);
					alljson.put("pXAxis", pXAxis);
					alljson.put("pmin", pmin);
					alljson.put("pmXAxis", pmXAxis);
				}
				/**所有订单组织数据*/
				String usorder = report.getReportEleOne();
				if(usorder!=null&&!"".equals(usorder)){
					String[] tmp = aorder.split(",");
					for (int i = 0; i < tmp.length; i++) {
						String value = tmp[i].trim();
						unSend.add(value);
						//计算最大值
						if(usmax<(new Integer(value))){
							usmax = new Integer(value);
							usXAxis  = i;
						}
						//计算最小值
						if(i==0){
							usmin = new Integer(value);
							usmXAxis  = i;
						}else if(usmin>(new Integer(value))){
							usmin = new Integer(value);
							usmXAxis  = i;
						}
					}
					JSONArray usjson = JSONArray.fromObject(unSend);
					alljson.put("usjson", usjson);
					alljson.put("usmax", usmax);
					alljson.put("usXAxis", usXAxis);
					alljson.put("usmin", usmin);
					alljson.put("usmXAxis", usmXAxis);
				}
			}
		}
		if(type=="genus"||"genus".equals(type)){
			QueryRule qr = QueryRule.getInstance();
			qr.addEqual("genusType", "genus");
			List<YybsGenus> genuses  = super.find(YybsGenus.class, qr);
			GenusVo gindex = new GenusVo();
			/**第一层*/
			if(genuses!=null&&genuses.size()>0){
				List<YybsGenus> delgens = new ArrayList<YybsGenus>(0);
				for(YybsGenus gen : genuses){
					if(gen.getGenusId().equals(gen.getUppergenusId())){
						DataUtils.copySimpleObject(gen, gindex);
						delgens.add(gen);
					}
				}
				//清理元素
				genuses.removeAll(delgens);
				genuses=takeOffNullObject(genuses);
				/**第二层*/
				if(gindex!=null&&!"".equals(gindex)){
					List<GenusVo> gfolr = new ArrayList<GenusVo>(0);
					delgens = new ArrayList<YybsGenus>(0);
					for(YybsGenus gen : genuses){
						GenusVo folr = new GenusVo();
						if(gindex.getGenusId().equals(gen.getUppergenusId())){
							DataUtils.copySimpleObject(gen, folr);
							gfolr.add(folr);
							delgens.add(gen);
						}
					}
					//清理元素
					genuses.removeAll(delgens);
					genuses=takeOffNullObject(genuses);
					gindex.setChildren(gfolr);
					/**第三层*/
					if(gindex.getChildren()!=null&&gindex.getChildren().size()>0){
						for(GenusVo flor : gindex.getChildren()){
							List<GenusVo> gnodes = new ArrayList<GenusVo>(0);
							for(YybsGenus gen : genuses){
								if(flor.getGenusId().equals(gen.getUppergenusId())){
									GenusVo node = new GenusVo();
									DataUtils.copySimpleObject(gen, node);
									gnodes.add(node);
								}
							}
							flor.setChildren(gnodes);
						}
					}
				}
			}
			alljson.put("tttt", gindex);
		}
		return alljson;
	}

	@SuppressWarnings("unchecked")
	public List takeOffNullObject(List list) {
		List newList = new ArrayList();
		if (list != null) {
			for (Object object : list) {
				if (object != null) {
					newList.add(object);
				}
			}
		}
		return newList;
	}

	/**
	 * id查询产品类型表
	 * @param genusId
	 * @return
	 */
	public YybsGenus findGenusDataById(Long genusId) {
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("genusId", genusId);
		List<YybsGenus> list  = super.find(YybsGenus.class,qr);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * Type查询产品类型表
	 * @param genusId
	 * @return
	 */
	public List<YybsGenus> findGenusDataByGenusType(String genusType){
		QueryRule qr = QueryRule.getInstance();
		qr.addEqual("genusType", genusType);
		qr.addEqual("flag", "2");
		List<YybsGenus> list  = super.find(YybsGenus.class,qr);
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	public void updateGenus(YybsGenus genus , String operation) {
		if(operation=="add"||"add".equals(operation)){
			super.save(genus);
		}else if(operation=="mod"||"mod".equals(operation)){
			super.update(genus);
		}else if(operation=="del"||"del".equals(operation)){
			super.delete(genus);
		}
		
	}
}
