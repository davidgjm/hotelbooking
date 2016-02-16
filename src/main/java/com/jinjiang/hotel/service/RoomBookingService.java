/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.RoomBooking;

/**
 * @author gaojianm
 *
 */
public interface RoomBookingService {
	Integer saveOrUpdate(RoomBooking booking);
	void delete(RoomBooking booking);
	void deleteBookings(Guest guest);
	List<RoomBooking> getBookings(Hotel hotel);
	List<RoomBooking> getBookings(Expo expo);
	List<RoomBooking> getBookings(Guest guest);
	List<PaymentMode> getAllPaymentModes();

}
