/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;

/**
 * @author gaojianm
 *
 */
public interface GuestMapper {

	Integer save(Guest guest);
	void delete(Guest guest);
	void update(Guest guest);
	Guest getById(Integer id);
	List<Guest> getAll();
	List<Guest> getGuestsByExpo(Expo expo);
	
	Guest findGuest(Guest guest);
}
