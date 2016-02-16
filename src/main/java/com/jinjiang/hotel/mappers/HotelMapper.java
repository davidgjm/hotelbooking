/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;

/**
 * @author gaojianm
 *
 */
public interface HotelMapper {

	Integer save(Hotel hotel);
	void delete(Hotel hotel);
	void update(Hotel hotel);
	List<Hotel> getAll();
	List<Hotel> getHotelsByExpo(Expo expo);
	List<Expo> getExposByHotel(Hotel hotel);
	Hotel getById(Integer id);
}
