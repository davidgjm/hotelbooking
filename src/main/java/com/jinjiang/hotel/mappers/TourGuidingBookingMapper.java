/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.TourGuidingBooking;

/**
 * @author gaojianm
 *
 */
public interface TourGuidingBookingMapper {
	Integer save(TourGuidingBooking booking);
	void update(TourGuidingBooking booking);
	void delete(TourGuidingBooking booking);
	void deleteByGuest(Guest guest);
	TourGuidingBooking getById();
	List<TourGuidingBooking> getAll();
	List<TourGuidingBooking> getByGuest(Guest guest);
}
