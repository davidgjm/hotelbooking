/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.ZKWebUtil;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.mappers.HotelMapper;
import com.jinjiang.hotel.mappers.PaymentModeMapper;
import com.jinjiang.hotel.mappers.RoomMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.HotelService;

/**
 * @author gaojianm
 *
 */
public class HotelServiceImpl implements HotelService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private static HotelService instance;
	/**
	 * 
	 */
	private HotelServiceImpl() {
	}
	
	public static synchronized HotelService getInstance() {
		if (instance==null) {
			instance=new HotelServiceImpl();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#saveOrUpdateHotel(com.jinjiang.hotel.domain.Hotel)
	 */
	@Override
	public Integer saveOrUpdate(Hotel hotel) {
		SqlSession session=MyBatisUtil.getSession();
		Integer id=null;
		try {
			HotelMapper mapper=session.getMapper(HotelMapper.class);
			RoomMapper rm=session.getMapper(RoomMapper.class);
			id=hotel.getId();
			if (id!=null) {
				mapper.update(hotel);
				List<Room> rooms=hotel.getRooms();
				if (rooms!=null) {
					for (Room room : rooms) {
						rm.update(room);
					}
				}
			} else {
				id=mapper.save(hotel);
				List<Room> rooms=hotel.getRooms();
				if (rooms!=null) {
					for (Room room : rooms) {
						rm.save(room);
					}
				}
			}
			session.commit();
		}finally {
			session.close();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#deleteHotel(com.jinjiang.hotel.domain.Hotel)
	 */
	@Override
	public void deleteHotel(Hotel hotel) {
		if(hotel==null||hotel.getId()==null) {
			if (log.isErrorEnabled()) {
				log.error("Provided hotel is invalid. Stopped deleting. {}", hotel);
			}
			return;
		}
		
		if (hotel.getImagePath()!=null) {
			ZKWebUtil.deleteImage(hotel.getImagePath());
		}
		SqlSession session=MyBatisUtil.getSession();
		try {
			HotelMapper hm=session.getMapper(HotelMapper.class);
			hm.delete(hotel);
			session.commit();
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getById(java.lang.Integer)
	 */
	@Override
	public Hotel getById(Integer id) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			HotelMapper hm=session.getMapper(HotelMapper.class);
			return hm.getById(id);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getAll()
	 */
	@Override
	public List<Hotel> getAll() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			HotelMapper hm=session.getMapper(HotelMapper.class);
			return hm.getAll();
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getExpos(com.jinjiang.hotel.domain.Hotel)
	 */
	@Override
	public List<Expo> getExpos(Hotel hotel) {
		if(hotel==null||hotel.getId()==null) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			HotelMapper hm=session.getMapper(HotelMapper.class);
			return hm.getExposByHotel(hotel);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#saveOrUpdateRoom(com.jinjiang.hotel.domain.Room)
	 */
	@Override
	public Integer saveOrUpdateRoom(Room room) {
		Integer id=room.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomMapper rm=session.getMapper(RoomMapper.class);
			if (id!=null) {
				rm.update(room);
			} else {
				id=rm.save(room);
			}
			session.commit();
		} finally {
		  session.close();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#deleteRoom(com.jinjiang.hotel.domain.Room)
	 */
	@Override
	public void deleteRoom(Room room) {
		if(room==null||room.getId()==null) return;
		if (room.getId()!=null && room.getHotel()!=null) {
			SqlSession session=MyBatisUtil.getSession();
			try {
				RoomMapper rm=session.getMapper(RoomMapper.class);
				rm.delete(room);
				session.commit();
			} finally {
			  session.close();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getHotelRooms(com.jinjiang.hotel.domain.Hotel)
	 */
	@Override
	public List<Room> getHotelRooms(Hotel hotel) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomMapper rm=session.getMapper(RoomMapper.class);
			return rm.getByHotel(hotel);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getRoomById(java.lang.Integer)
	 */
	@Override
	public Room getRoomById(Integer id) {
		if(id==null||id<1) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			RoomMapper rm=session.getMapper(RoomMapper.class);
			return rm.getById(id);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#saveOrUpdate(com.jinjiang.hotel.domain.PaymentMode)
	 */
	@Override
	public Integer saveOrUpdate(PaymentMode mode) {
		if(mode==null) return null;
		Integer id=mode.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			PaymentModeMapper pmm=session.getMapper(PaymentModeMapper.class);
			if (id!=null) {
				pmm.update(mode);
			} else {
				id=pmm.save(mode);
			}
			session.commit();
		} finally {
		  session.close();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#delete(com.jinjiang.hotel.domain.PaymentMode)
	 */
	@Override
	public void delete(PaymentMode mode) {
		if(mode==null||mode.getId()==null) return;
		SqlSession session=MyBatisUtil.getSession();
		try {
			PaymentModeMapper pmm=session.getMapper(PaymentModeMapper.class);
			pmm.delete(mode);
			session.commit();
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getPaymentModeById(java.lang.Integer)
	 */
	@Override
	public PaymentMode getPaymentModeById(Integer id) {
		if(id==null||id<1) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			PaymentModeMapper pmm=session.getMapper(PaymentModeMapper.class);
			return pmm.getById(id);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.HotelService#getAllPaymentModes()
	 */
	@Override
	public List<PaymentMode> getAllPaymentModes() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			PaymentModeMapper pmm=session.getMapper(PaymentModeMapper.class);
			return pmm.getAll();
		} finally {
		  session.close();
		}
	}

	@Override
	public List<Hotel> getHotels(Expo expo) {
		if(expo==null||expo.getId()==null) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			HotelMapper hm=session.getMapper(HotelMapper.class);
			return hm.getHotelsByExpo(expo);
		} finally {
		  session.close();
		}
	}


}
