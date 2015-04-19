/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.domain.TourGuidingBooking;
import com.jinjiang.hotel.mappers.TourGuidingBookingMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.TourGuidingBookingService;
import com.jinjiang.hotel.service.TourGuidingService;

/**
 * @author gaojianm
 *
 */
public class TourGuidingBookingServiceImpl implements TourGuidingBookingService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private TourGuidingService tgs;
	private static TourGuidingBookingServiceImpl instance;
	/**
	 * 
	 */
	private TourGuidingBookingServiceImpl() {
		tgs=TourGuidingServiceImpl.getInstance();
	}
	
	public static synchronized TourGuidingBookingService getInstance() {
		if(instance==null) instance=new TourGuidingBookingServiceImpl();
		return instance;
	}

	@Override
	public Integer saveOrUpdate(TourGuidingBooking booking) {
		Integer id=booking.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingBookingMapper tgbm=session.getMapper(TourGuidingBookingMapper.class);
			if (id!=null) {
				if (log.isDebugEnabled()) {
					log.debug("Updating {} : {}",booking.getClass().getSimpleName(), id);
				}
				tgbm.update(booking);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("Inserting {}.",booking.getClass().getSimpleName());
				}
				id=tgbm.save(booking);
				if (log.isDebugEnabled()) {
					log.debug("Booking saved with id: {}", id);
				}
			}
			session.commit();
		} finally {
		  session.close();
		}
		if (log.isDebugEnabled()) {
			log.debug("Booking id to return: {}", id);
		}
		return id;
	}

	@Override
	public void delete(TourGuidingBooking booking) {
		if(booking==null||booking.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingBookingMapper tgbm=session.getMapper(TourGuidingBookingMapper.class);
			tgbm.delete(booking);
			session.commit();
		} finally {
		  session.close();
		}
	}

	@Override
	public List<TourGuidingBooking> getBookings(Guest guest) {
		if(guest==null) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingBookingMapper tgbm=session.getMapper(TourGuidingBookingMapper.class);
			return tgbm.getByGuest(guest);
		} finally {
		  session.close();
		}
	}

	@Override
	public List<TourGuidingBooking> getBookings() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingBookingMapper tgbm=session.getMapper(TourGuidingBookingMapper.class);
			return tgbm.getAll();
		} finally {
		  session.close();
		}
	}

	@Override
	public List<TourGuiding> getTourGuidings() {
		return tgs.getAll();
	}

	@Override
	public List<String> getAllRoutes() {
		return tgs.getRoutes();
	}

	@Override
	public List<TourGuiding> getTourGuidings(String route) {
		if(route==null||route.trim().isEmpty()) return null;
		return tgs.getByRoute(route);
	}

	@Override
	public void delete(Guest guest) {
		if(guest==null||guest.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingBookingMapper tgbm=session.getMapper(TourGuidingBookingMapper.class);
			tgbm.deleteByGuest(guest);
			session.commit();
		} finally {
		  session.close();
		}
	}

}
