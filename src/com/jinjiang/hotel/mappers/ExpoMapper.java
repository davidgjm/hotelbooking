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
public interface ExpoMapper {

	void save(Expo expo);
	void delete(Expo expo);
	void update(Expo expo);
	Expo getById(Integer id);
	List<Expo> getAll();
	List<Expo> getCurrent();
	List<Expo> getCurrentEnglish();
	List<Expo> getByName(String name);
	Integer getLast();
	void removeAllHotels(Expo expo);
}
