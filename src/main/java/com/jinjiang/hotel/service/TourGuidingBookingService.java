/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.domain.TourGuidingBooking;

/**
 * @author gaojianm
 *
 */
public interface TourGuidingBookingService {
	Integer saveOrUpdate(TourGuidingBooking booking);
	void delete(TourGuidingBooking booking);
	void delete(Guest guest);
	List<TourGuidingBooking> getBookings(Guest guest);
	List<TourGuidingBooking> getBookings();
	List<TourGuiding> getTourGuidings(String route);
	List<TourGuiding> getTourGuidings();
	List<String> getAllRoutes();
}
