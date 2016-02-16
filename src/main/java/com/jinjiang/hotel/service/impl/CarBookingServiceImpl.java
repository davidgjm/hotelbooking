/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.mappers.CarBookingMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.CarBookingService;

/**
 * @author gaojianm
 *
 */
public class CarBookingServiceImpl implements CarBookingService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private static CarBookingService instance;
	
	/**
	 * 
	 */
	private CarBookingServiceImpl() {
	}
	
	public static synchronized CarBookingService getInstance() {
		if(instance==null) instance=new CarBookingServiceImpl();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarBookingService#saveOrUpdate(com.jinjiang.hotel.domain.CarBooking)
	 */
	@Override
	public Integer saveOrUpdate(CarBooking booking) {
		Integer id=booking.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
			if (id!=null) {
				if (log.isDebugEnabled()) {
					log.debug("Updating {} : {}",booking.getClass().getSimpleName(), id);
				}
				mapper.update(booking);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("Inserting {}.",booking.getClass().getSimpleName());
				}
				id=mapper.save(booking);
				if (log.isDebugEnabled()) {
					log.debug("Booking saved with id: {}", id);
				}
			}
			session.commit();
		}finally {
			session.close();
		}
		if (log.isDebugEnabled()) {
			log.debug("Booking id to return: {}", id);
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarBookingService#delete(com.jinjiang.hotel.domain.CarBooking)
	 */
	@Override
	public void delete(CarBooking booking) {
		if(booking==null||booking.getGuest()==null||booking.getCarRental()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
			mapper.delete(booking);
			session.commit();
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarBookingService#getBooking(java.lang.Integer)
	 */
	@Override
	public CarBooking getBooking(Integer id) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
			return mapper.getById(id);
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarBookingService#getBookings(com.jinjiang.hotel.domain.Guest)
	 */
	@Override
	public List<CarBooking> getBookings(Guest guest) {
		if(guest==null||guest.getId()==null) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
			return mapper.getByGuest(guest);
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarBookingService#getBookings()
	 */
	@Override
	public List<CarBooking> getBookings() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
			return mapper.getAll();
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteByGuest(Guest guest) {
		if(guest==null||guest.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
			mapper.deleteByGuest(guest);
			session.commit();
		}finally {
			session.close();
		}
	}

}
