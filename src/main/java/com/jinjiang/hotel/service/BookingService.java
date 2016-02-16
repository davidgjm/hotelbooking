/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;
import java.util.Map;

import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.domain.TourGuidingBooking;

/**
 * @author gaojianm
 *
 */
public interface BookingService {
	Integer saveOrUpdate(Guest guest);
	
	Integer saveOrUpdate(RoomBooking booking);
	void delete(RoomBooking booking);
	List<RoomBooking> getRoomBooking(Hotel hotel);
	List<RoomBooking> getRoomBooking(Expo expo);
	List<PaymentMode> getAllPaymentModes();
	
	/*
	 * car booking
	 */
	Integer saveOrUpdate(CarBooking booking);
	void delete(CarBooking booking);
	List<CarBooking> getCarBookings(Guest guest);
	List<CarBooking> getCarBookings();
	List<CarRental> getRentalsByUsage(String usage);
	Map<String, List<CarRental>> getCarRentalMatrixByUsage();
	List<String> getCarUsages();
	List<String> getCars();
	
	/*
	 * Tour Guide booking
	 */
	Integer saveOrUpdate(TourGuidingBooking booking);
	void delete(TourGuidingBooking booking);
	List<TourGuidingBooking> getTourGuidingBookings(Guest guest);
	List<TourGuidingBooking> getTourGuidingBookings();
	List<TourGuiding> getTourGuidings(String route);
	List<TourGuiding> getTourGuidings();
	/**
	 * Loads all existing tour guiding routes
	 * @return
	 */
	List<String> getTourRoutes();
	
	Integer save(GuestBooking booking);
	void update(GuestBooking booking, boolean cascade);
	void delete(GuestBooking booking);
	void deleteBooking(Guest guest);
	GuestBooking getBooking(Guest guest);
	GuestBooking getBooking(Integer id);
	List<GuestBooking> getBookings(Expo expo);
	List<GuestBooking> getBookingsUnconfirmed(Expo expo);
	
	List<Expo> getCurrentExpos();

}
