/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.Room;

/**
 * @author gaojianm
 *
 */
public interface HotelService {

	Integer saveOrUpdate(Hotel hotel);
	void deleteHotel(Hotel hotel);
	Hotel getById(Integer id);
	Room getRoomById(Integer id);
	List<Hotel> getHotels(Expo expo);
	List<Hotel> getAll();
	List<Expo> getExpos(Hotel hotel);
	
	Integer saveOrUpdateRoom(Room room);
	void deleteRoom(Room room);
	List<Room> getHotelRooms(Hotel hotel);
	
	Integer saveOrUpdate(PaymentMode mode);
	void delete(PaymentMode mode);
	PaymentMode getPaymentModeById(Integer id);
	List<PaymentMode> getAllPaymentModes();
}
