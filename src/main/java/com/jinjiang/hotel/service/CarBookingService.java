/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.Guest;

/**
 * @author gaojianm
 *
 */
public interface CarBookingService {
	Integer saveOrUpdate(CarBooking booking);
	void delete(CarBooking booking);
	void deleteByGuest(Guest guest);
	CarBooking getBooking(Integer id);
	List<CarBooking> getBookings(Guest guest);
	List<CarBooking> getBookings();
	
}
