/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.mappers.RoomBookingMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.RoomBookingService;

/**
 * @author gaojianm
 *
 */
public class RoomBookingServiceImpl implements RoomBookingService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private HotelService hs;
	private static RoomBookingService instance;
	/**
	 * 
	 */
	public RoomBookingServiceImpl() {
		hs=HotelServiceImpl.getInstance();
	}
	
	public static synchronized RoomBookingService getInstance() {
		if(instance==null) instance=new RoomBookingServiceImpl();
		return instance;
	}

	@Override
	public Integer saveOrUpdate(RoomBooking booking) {
		Integer id=booking.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomBookingMapper rbm=session.getMapper(RoomBookingMapper.class);
			if (id!=null) {
				if (log.isDebugEnabled()) {
					log.debug("Updating {} : {}",booking.getClass().getSimpleName(), id);
				}
				rbm.update(booking);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("Inserting {}.",booking.getClass().getSimpleName());
				}
				id=rbm.save(booking);
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
	public void delete(RoomBooking booking) {
		if(booking==null||booking.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomBookingMapper rbm=session.getMapper(RoomBookingMapper.class);
			rbm.delete(booking);
			session.commit();
		} finally {
		  session.close();
		}
	}

	@Override
	public List<RoomBooking> getBookings(Hotel hotel) {
		if(hotel==null||hotel.getId()==null||hotel.getId()<1) return null;
		List<RoomBooking> bookings=new ArrayList<RoomBooking>();
		List<Room> rooms=hotel.getRooms();
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomBookingMapper rbm=session.getMapper(RoomBookingMapper.class);
			for (Room room : rooms) {
				List<RoomBooking> rbs=rbm.getByRoom(room);
				if(rbs!=null) bookings.addAll(rbs);
			}
		} finally {
		  session.close();
		}
		return bookings;
	}

	@Override
	public List<RoomBooking> getBookings(Expo expo) {
		if(expo==null||expo.getId()==null||expo.getId()<1) return null;
		List<Hotel> hotels=hs.getHotels(expo);
		List<RoomBooking> bookings=new ArrayList<RoomBooking>();
		for (Hotel hotel : hotels) {
			bookings.addAll(getBookings(hotel));
		}
		return bookings;
	}

	@Override
	public List<PaymentMode> getAllPaymentModes() {
		return hs.getAllPaymentModes();
	}

	@Override
	public List<RoomBooking> getBookings(Guest guest) {
		if(guest==null||guest.getId()==null) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomBookingMapper rbm=session.getMapper(RoomBookingMapper.class);
			return rbm.getByGuest(guest);
		} finally {
		  session.close();
		}
	}

	@Override
	public void deleteBookings(Guest guest) {
		if(guest==null||guest.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomBookingMapper rbm=session.getMapper(RoomBookingMapper.class);
			rbm.deleteByGuest(guest);
			session.commit();
		} finally {
		  session.close();
		}
	}



}
