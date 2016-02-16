/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.ZKWebUtil;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.mappers.ExpoMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.ExpoService;
import com.jinjiang.hotel.service.HotelService;

/**
 * @author gaojianm
 *
 */
public class ExpoServiceImpl implements ExpoService {

	private Logger log=LoggerFactory.getLogger(getClass());
	private static ExpoService instance;
	private HotelService hs;
	/**
	 * 
	 */
	private ExpoServiceImpl() {
		hs=HotelServiceImpl.getInstance();
	}
	
	public static synchronized ExpoService getInstance() {
		if (instance==null) {
			instance=new ExpoServiceImpl();
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#saveOrUpdateExpo(com.jinjiang.hotel.domain.Expo)
	 */
	@Override
	public Integer saveOrUpdateExpo(Expo expo) {
		Integer id=expo.getId();
		if(expo==null||expo.getName()==null||expo.getName().trim().isEmpty()) {
			if (log.isWarnEnabled()) {
				log.warn("expo is invalid. [{}]", expo);
			}
			return null;
		}
		SqlSession session=MyBatisUtil.getSession();
		try {
			ExpoMapper em=session.getMapper(ExpoMapper.class);
			if (id!=null) {
				if (log.isDebugEnabled()) {
					log.debug("Updating expo [{}]", expo.getName());
				}
				em.update(expo);
			} else {
				em.save(expo);
			}
			session.commit();
		}finally {
			session.close();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#deleteExpo(com.jinjiang.hotel.domain.Expo)
	 */
	@Override
	public void deleteExpo(Expo expo) {
		if (expo==null||expo.getId()==null) return;
		if (expo.getImagePath()!=null) {
			ZKWebUtil.deleteImage(expo.getImagePath());
		}
		SqlSession session=MyBatisUtil.getSession();
		try {
			ExpoMapper em=session.getMapper(ExpoMapper.class);
			em.delete(expo);
			session.commit();
		}finally {
			session.close();
		}
	}
	
	

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#getAll()
	 */
	@Override
	public List<Expo> getAll() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			ExpoMapper em=session.getMapper(ExpoMapper.class);
			return em.getAll();
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#getById(java.lang.Integer)
	 */
	@Override
	public Expo getExpoById(Integer id) {
		if(id==null) {
			if (log.isErrorEnabled()) {
				log.error("Expo ID is null!");
			}
			return null;
		}
		SqlSession session=MyBatisUtil.getSession();
		try {
			ExpoMapper em=session.getMapper(ExpoMapper.class);
			return em.getById(id);
		}finally {
			session.close();
		}
	}

	

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#getCurrentExpos()
	 */
	@Override
	public List<Expo> getCurrentExpos(boolean isGetEnglishExpos) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			ExpoMapper em=session.getMapper(ExpoMapper.class);
			if (isGetEnglishExpos) {
				return em.getCurrentEnglish();
			} else {
				return em.getCurrent();
			}
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#getAllHotels()
	 */
	@Override
	public List<Hotel> getAllHotels() {
		return hs.getAll();
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#getHotels(com.jinjiang.hotel.domain.Expo)
	 */
	@Override
	public List<Hotel> getHotels(Expo expo) {
		return hs.getHotels(expo);
	}

	@Override
	public void addHotel(Expo expo, Hotel hotel) {
		addHotel(expo, hotel, null);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#removeHotel(com.jinjiang.hotel.domain.Expo, com.jinjiang.hotel.domain.Hotel)
	 */
	@Override
	public void removeHotel(Expo expo, Hotel hotel) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("expo", expo);
		map.put("hotel", hotel);
		SqlSession session=MyBatisUtil.getSession();
		try {
			session.delete("com.jinjiang.hotel.mappers.ExpoMapper.removeHotel", map);
			session.commit();
		}finally {
			session.close();
		}		
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#addHotel(com.jinjiang.hotel.domain.Expo, com.jinjiang.hotel.domain.Hotel, java.lang.String)
	 */
	@Override
	public void addHotel(Expo expo, Hotel hotel, String note) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("expo", expo);
		map.put("hotel", hotel);
		map.put("note", note);
		SqlSession session=MyBatisUtil.getSession();
		try {
			session.insert("com.jinjiang.hotel.mappers.ExpoMapper.addHotel", map);
			session.commit();
		}finally {
			session.close();
		}		
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.ExpoService#updateExpoHotels(com.jinjiang.hotel.domain.Expo, java.util.List)
	 */
	@Override
	public void updateExpoHotels(Expo expo, List<Hotel> hotels) {
		if(expo==null||hotels==null||hotels.isEmpty()) return;
		
		SqlSession session=MyBatisUtil.getSession();
		try {
			ExpoMapper em=session.getMapper(ExpoMapper.class);
			/*
			 * first remove all current hotels
			 */
			em.removeAllHotels(expo);
			
			/*
			 * add current hotels
			 */
			for (Hotel hotel : hotels) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("expo", expo);
				map.put("hotel", hotel);
				session.insert("com.jinjiang.hotel.mappers.ExpoMapper.addHotel", map);
			}
			session.commit();
		}finally {
			session.close();
		}
	}

	
}
