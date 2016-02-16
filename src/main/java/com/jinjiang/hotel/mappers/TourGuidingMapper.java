/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.TourGuiding;

/**
 * @author gaojianm
 *
 */
public interface TourGuidingMapper {
	Integer save(TourGuiding tg);
	void update(TourGuiding tg);
	void delete(TourGuiding tg);
	TourGuiding getById(Integer id);
	List<TourGuiding> getAll();
	List<TourGuiding> getByLanguage(String language);
	List<TourGuiding> getByRoute(String route);
	List<String> getRoutes();
}
