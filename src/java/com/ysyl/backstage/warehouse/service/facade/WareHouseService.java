package com.ysyl.backstage.warehouse.service.facade;
import java.io.File;
import java.util.List;

import com.ysyl.backstage.schema.model.WhAccessory;
import com.ysyl.backstage.schema.model.WhCar;
import com.ysyl.backstage.schema.model.WhCarSeries;

/**
 * 商品功能接口
 * @author wyc
 *
 */
public interface WareHouseService{

	/**
	 * rt
	 * @param whCarSeries
	 */
	Long saveCarSeries(WhCarSeries whCarSeries);

	List<WhCarSeries> findCarSeriesByAll(Long carserId, String carserName,
			String carserMinPrice, String carserMaxPrice);

	WhCarSeries findCarSeriesById(Long carserId);

	void delCarSeries(WhCarSeries cs);

	WhCar findCarById(Long carId);

	Long saveCar(WhCar whCar);

	List<WhCar> findCarByAll(Long carId, String carName, String carStandPrice,
			Long object);

	void delCar(WhCar cr);

    WhAccessory findAccessoryById(Long accessoryId);

	List<WhAccessory> findAccessoryByAll(Long accessoryId, String accessoryName, String accessoryStandPrice,Long carserId);

	Long saveAccessory(WhAccessory accessory);

	void delAccessory(WhAccessory acc);

	void defWhObject(Object standObject);
	void setBasicDataByExcel(File file);
}
