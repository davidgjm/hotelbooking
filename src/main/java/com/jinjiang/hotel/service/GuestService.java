/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;

/**
 * @author gaojianm
 *
 */
public interface GuestService {
	Integer saveOrUpdateGuest(Guest guest);
	Guest getById(Integer id);
	List<Guest> getGuests(Expo expo);
}
