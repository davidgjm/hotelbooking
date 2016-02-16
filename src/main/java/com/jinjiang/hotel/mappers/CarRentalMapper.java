/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.CarRental;

/**
 * @author gaojianm
 *
 */
public interface CarRentalMapper {
	Integer save(CarRental rental);
	void update(CarRental rental);
	void delete(CarRental rental);
	CarRental getById(Integer id);
	List<CarRental> getAll();
	List<CarRental> getByUsage(String usage);
	List<CarRental> getByCar(String car);
	List<String> getCars();
	List<String> getUsages();
}
