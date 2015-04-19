/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.Guest;

/**
 * @author gaojianm
 *
 */
public interface CarBookingMapper {
	Integer getLastId();
	Integer save(CarBooking booking);
	void update(CarBooking booking);
	void delete(CarBooking booking);
	void deleteByGuest(Guest guest);
	CarBooking getById(Integer id);
	List<CarBooking> getAll();
	List<CarBooking> getByGuest(Guest guest);
}
