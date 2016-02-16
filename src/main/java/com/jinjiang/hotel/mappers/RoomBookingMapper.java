/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.domain.RoomBooking;

/**
 * @author gaojianm
 *
 */
public interface RoomBookingMapper {
	Integer save(RoomBooking rb);
	void update(RoomBooking rb);
	void deleteByGuest(Guest guest);
	void delete(RoomBooking rb);
	RoomBooking getById(Integer id);
	List<RoomBooking> getByGuest(Guest guest);
	List<RoomBooking> getByRoom(Room room);
}
