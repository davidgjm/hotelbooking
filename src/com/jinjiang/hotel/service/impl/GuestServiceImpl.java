/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.mappers.GuestMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.GuestService;

/**
 * @author gaojianm
 *
 */
public class GuestServiceImpl implements GuestService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private static GuestService instance;
	/**
	 * 
	 */
	private GuestServiceImpl() {
	}
	
	public static synchronized GuestService getInstance() {
		if (instance==null) {
			instance=new GuestServiceImpl();
		}
		return instance;
	}


	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.GuestService#getById(java.lang.Integer)
	 */
	@Override
	public Guest getById(Integer id) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestMapper gm=session.getMapper(GuestMapper.class);
			return gm.getById(id);
		}finally {
			session.close();
		}
	}


	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.GuestService#saveOrUpdateGuest(com.jinjiang.hotel.domain.Guest)
	 */
	@Override
	public Integer saveOrUpdateGuest(Guest guest) {
		if(guest==null||guest.getName()==null) {
			if (log.isWarnEnabled()) {
				log.warn("Guest is invalid. [{}]", guest);
			}
			return null;
		}
		Integer id=guest.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestMapper gm=session.getMapper(GuestMapper.class);
			if (id!=null) {
				gm.update(guest);
			} else {
				//The count value is greater than 0 if the guest already exists.
				Guest persisted=gm.findGuest(guest);
				if (persisted ==null) {
					id=gm.save(guest);					
				}else {
					log.error("Guest already exists. {}", persisted);
				}
			}
			session.commit();
		}finally {
			session.close();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.GuestService#getGuests(com.jinjiang.hotel.domain.Expo)
	 */
	@Override
	public List<Guest> getGuests(Expo expo) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			GuestMapper gm=session.getMapper(GuestMapper.class);
			return gm.getGuestsByExpo(expo);
		}finally {
			session.close();
		}
	}
}
