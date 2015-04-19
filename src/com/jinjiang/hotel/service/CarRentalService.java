/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;
import java.util.Map;

import com.jinjiang.hotel.domain.CarRental;

/**
 * @author gaojianm
 *
 */
public interface CarRentalService {

	Integer saveOrUpdateCarRental(CarRental rental);
	void delete(CarRental rental);
	CarRental getById(Integer id);
	List<CarRental> getCarRentalsByUsage(String usage);
	List<CarRental> getCarRentalsByCar(String car);
	List<CarRental> getAll();
	List<String> getCars();
	List<String> getCarUsages();
	Map<String, List<CarRental>> getCarRentalMatrixByUsage();
}
