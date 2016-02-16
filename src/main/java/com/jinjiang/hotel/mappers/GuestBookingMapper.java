/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.GuestBooking;

/**
 * @author gaojianm
 *
 */
public interface GuestBookingMapper {
	Integer save(GuestBooking booking);
	void update(GuestBooking booking);
	void delete(GuestBooking booking);
	void deleteByGuest(Guest guest);
	void deleteAll(Guest guest);
	GuestBooking getById(Integer id);
	GuestBooking getByGuest(Guest guest);
	List<GuestBooking> getAllBookings();
	List<GuestBooking> getUnconfirmedBookings();
	
	GuestBooking findBooking(GuestBooking booking);
}
