/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.domain.TourGuidingBooking;
import com.jinjiang.hotel.mappers.GuestBookingMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.BookingService;
import com.jinjiang.hotel.service.CarBookingService;
import com.jinjiang.hotel.service.CarRentalService;
import com.jinjiang.hotel.service.ExpoService;
import com.jinjiang.hotel.service.GuestService;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.RoomBookingService;
import com.jinjiang.hotel.service.TourGuidingBookingService;

/**
 * @author gaojianm
 *
 */
public class BookingServiceImpl implements BookingService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private static BookingService instance;
	private ExpoService es;
	private RoomBookingService rbs;
	private HotelService hs;
	private GuestService gs;
	private CarBookingService cbs;
	private CarRentalService rentalService;
	private TourGuidingBookingService tgbs;
	/**
	 * 
	 */
	public BookingServiceImpl() {
		es=ExpoServiceImpl.getInstance();
		rbs=RoomBookingServiceImpl.getInstance();
		cbs=CarBookingServiceImpl.getInstance();
		hs=HotelServiceImpl.getInstance();
		gs=GuestServiceImpl.getInstance();
		rentalService=CarRentalServiceImpl.getInstance();
		tgbs=TourGuidingBookingServiceImpl.getInstance();
	}
	
	/**
	 * @return
	 */
	public static synchronized BookingService getInstance() {
		if(instance==null) instance=new BookingServiceImpl();
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#saveOrUpdateRoomBooking(com.jinjiang.hotel.domain.RoomBooking)
	 */
	@Override
	public Integer saveOrUpdate(RoomBooking booking) {
		return rbs.saveOrUpdate(booking);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#delete(com.jinjiang.hotel.domain.RoomBooking)
	 */
	@Override
	public void delete(RoomBooking booking) {
		rbs.delete(booking);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getRoomBooking(com.jinjiang.hotel.domain.Hotel)
	 */
	@Override
	public List<RoomBooking> getRoomBooking(Hotel hotel) {
		return rbs.getBookings(hotel);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getRoomBooking(com.jinjiang.hotel.domain.Expo)
	 */
	@Override
	public List<RoomBooking> getRoomBooking(Expo expo) {
		return rbs.getBookings(expo);
	}

	@Override
	public List<PaymentMode> getAllPaymentModes() {
		return hs.getAllPaymentModes();
	}

	@Override
	public Integer saveOrUpdate(CarBooking booking) {
		return cbs.saveOrUpdate(booking);
	}

	@Override
	public void delete(CarBooking booking) {
		cbs.delete(booking);
	}

	@Override
	public List<CarBooking> getCarBookings(Guest guest) {
		return cbs.getBookings(guest);
	}

	@Override
	public List<CarBooking> getCarBookings() {
		return cbs.getBookings();
	}

	@Override
	public Map<String, List<CarRental>> getCarRentalMatrixByUsage() {
		return rentalService.getCarRentalMatrixByUsage();
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getCarUsages()
	 */
	@Override
	public List<String> getCarUsages() {
		return rentalService.getCarUsages();
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getCars()
	 */
	@Override
	public List<String> getCars() {
		return rentalService.getCars();
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getRentalsByUsage(java.lang.String)
	 */
	@Override
	public List<CarRental> getRentalsByUsage(String usage) {
		return rentalService.getCarRentalsByUsage(usage);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#saveOrUpdate(com.jinjiang.hotel.domain.TourGuidingBooking)
	 */
	@Override
	public Integer saveOrUpdate(TourGuidingBooking booking) {
		return tgbs.saveOrUpdate(booking);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#delete(com.jinjiang.hotel.domain.TourGuidingBooking)
	 */
	@Override
	public void delete(TourGuidingBooking booking) {
		tgbs.delete(booking);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getTourGuidingBookings(com.jinjiang.hotel.domain.Guest)
	 */
	@Override
	public List<TourGuidingBooking> getTourGuidingBookings(Guest guest) {
		return tgbs.getBookings(guest);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getTourGuidingBookings()
	 */
	@Override
	public List<TourGuidingBooking> getTourGuidingBookings() {
		return tgbs.getBookings();
	}

	@Override
	public List<TourGuiding> getTourGuidings() {
		return tgbs.getTourGuidings();
	}

	@Override
	public List<String> getTourRoutes() {
		return tgbs.getAllRoutes();
	}

	@Override
	public List<TourGuiding> getTourGuidings(String route) {
		return tgbs.getTourGuidings(route);
	}

	@Override
	public GuestBooking getBooking(Guest guest) {
		if(guest==null) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestBookingMapper mapper=session.getMapper(GuestBookingMapper.class);
			return mapper.getByGuest(guest);
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#getBookings(com.jinjiang.hotel.domain.Expo)
	 */
	@Override
	public List<GuestBooking> getBookings(Expo expo) {
		List<Guest> guests=gs.getGuests(expo);
		List<GuestBooking> bookings=new ArrayList<GuestBooking>();
		for (Guest guest : guests) {
			if (log.isDebugEnabled()) {
				log.debug("Retrieving booking information for [{}]", guest.getName());
			}
			GuestBooking gb=getBooking(guest);
			if(gb!=null) bookings.add(gb);
		}
		return bookings;
	}

	@Override
	public Integer save(GuestBooking booking) {
		if(booking==null||booking.getGuest()==null||booking.getGuest().getEmail()==null) {
			if (log.isWarnEnabled()) {
				log.warn("Guest booking is invalid. Cannot be saved. [{}]", booking);
			}
			return null;
		}
		SqlSession session=MyBatisUtil.getSession();
		try {
			Integer id=null;
			GuestBookingMapper mapper=session.getMapper(GuestBookingMapper.class);
			
			GuestBooking persisted=mapper.findBooking(booking);
			if (persisted !=null) {
				log.error("Booking already exists: {}", persisted);
				return null;
			}
			
			id=mapper.save(booking);
			session.commit();				
			
			for (RoomBooking rb:booking.getRoomBookings()) {
				if (rb.getRoomCount()>0) {
					rb.setGuest(booking.getGuest());
					saveOrUpdate(rb);
				}
			}
			
			for(CarBooking cb: booking.getCarBookings()) {
				if (cb.getBookCount()>0) {
					cb.setGuest(booking.getGuest());
					saveOrUpdate(cb);
				}
			}
			
			for(TourGuidingBooking tgb: booking.getTourBookings()) {
				if (tgb.getBookCount()>0) {
					tgb.setGuest(booking.getGuest());
					saveOrUpdate(tgb);
				}
			}
			return id;
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.BookingService#update(com.jinjiang.hotel.domain.GuestBooking)
	 */
	@Override
	public void update(GuestBooking booking, boolean cascade) {
		if(booking==null||booking.getId()==null
				||booking.getGuest()==null
				||booking.getGuest().getEmail()==null
				) {
			if (log.isWarnEnabled()) {
				log.warn("Guest booking is invalid. Cannot be saved. [{}]", booking);
			}
			return;
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Attempting to update booking:{}",booking);
		}
		
		/*
		 * update guest booking
		 */
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestBookingMapper mapper=session.getMapper(GuestBookingMapper.class);
			mapper.update(booking);
			session.commit();
		}finally {
			session.close();
		}
		
		if (cascade) {
			/*
			 * update room bookings
			 */
			List<RoomBooking> roomBookings=booking.getRoomBookings();
			for (RoomBooking roomBooking : roomBookings) {
				rbs.saveOrUpdate(roomBooking);
			}
			
			List<CarBooking> carBookings=booking.getCarBookings();
			for (CarBooking carBooking : carBookings) {
				cbs.saveOrUpdate(carBooking);
			}
			
			List<TourGuidingBooking> tourBookings=booking.getTourBookings();
			for (TourGuidingBooking tourGuidingBooking : tourBookings) {
				tgbs.saveOrUpdate(tourGuidingBooking);
			}
			
		}
	}

	@Override
	public void delete(GuestBooking booking) {
		if(booking==null||booking.getId()==null||booking.getGuest()==null) {
			log.warn("Guest booking is invalid. No deletion is to be performed. {}",booking);
			return;
		}
		deleteBooking(booking.getGuest());
	}

	@Override
	public void deleteBooking(Guest guest) {
		if(guest==null||guest.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestBookingMapper mapper=session.getMapper(GuestBookingMapper.class);
			mapper.deleteByGuest(guest);
			session.commit();
		}finally {
			session.close();
		}
		rbs.deleteBookings(guest);
		cbs.deleteByGuest(guest);
		tgbs.delete(guest);
	}

	@Override
	public List<GuestBooking> getBookingsUnconfirmed(Expo expo) {
		List<Guest> guests=gs.getGuests(expo);
		List<GuestBooking> bookings=new ArrayList<GuestBooking>();
		for (Guest guest : guests) {
			if (log.isDebugEnabled()) {
				log.debug("Retrieving booking information for [{}]", guest.getName());
			}
			GuestBooking gb=getBooking(guest);
			if(gb!=null && !gb.getConfirmStatus()) bookings.add(gb);
		}
		return bookings;
	}

	@Override
	public List<Expo> getCurrentExpos() {
		return es.getCurrentExpos(false);
	}

	@Override
	public GuestBooking getBooking(Integer id) {
		if(id==null||id<1) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestBookingMapper mapper=session.getMapper(GuestBookingMapper.class);
			return mapper.getById(id);
		}finally {
			session.close();
		}
	}

	@Override
	public Integer saveOrUpdate(Guest guest) {
		return gs.saveOrUpdateGuest(guest);
	}

}
