/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;

/**
 * @author gaojianm
 *
 */
public interface ExpoService {

	Integer saveOrUpdateExpo(Expo expo);
	/**
	 * @param expo
	 */
	void deleteExpo(Expo expo);
	
	Expo getExpoById(Integer id);
	
	List<Expo> getAll();
	
	/**
	 * Get expos not happened yet
	 * @return
	 */
	List<Expo> getCurrentExpos(boolean isGetEnglishExpos);
	List<Hotel> getHotels(Expo expo);
	/**
	 * @return
	 */
	List<Hotel> getAllHotels();
	
	void addHotel(Expo expo, Hotel hotel, String note);
	void addHotel(Expo expo, Hotel hotel);
	/**
	 * Remove the hotel from this expo
	 * @param expo
	 * @param hotel
	 */
	void removeHotel(Expo expo, Hotel hotel);
	
	void updateExpoHotels(Expo expo, List<Hotel> hotels);
}
