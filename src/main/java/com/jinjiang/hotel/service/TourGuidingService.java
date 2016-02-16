/**
 * 
 */
package com.jinjiang.hotel.service;

import java.util.List;

import com.jinjiang.hotel.domain.TourGuiding;

/**
 * @author gaojianm
 *
 */
public interface TourGuidingService {

	Integer saveOrUpdate(TourGuiding tg);
	void delete(TourGuiding tg);
	TourGuiding getById(Integer id);
	List<TourGuiding> getAll();
	List<TourGuiding> getByLanguage(String language);
	List<TourGuiding> getByRoute(String route);
	List<String> getRoutes();
}
