/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.mappers.TourGuidingMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.TourGuidingService;

/**
 * @author gaojianm
 *
 */
public class TourGuidingServiceImpl implements TourGuidingService {
	private static TourGuidingService instance;
	/**
	 * 
	 */
	private TourGuidingServiceImpl() {
	}
	
	public static synchronized TourGuidingService getInstance() {
		if(instance==null) instance=new TourGuidingServiceImpl();
		return instance;
	}


	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.TourGuidingService#saveOrUpdate(com.jinjiang.hotel.domain.TourGuiding)
	 */
	@Override
	public Integer saveOrUpdate(TourGuiding tg) {
		Integer id=tg.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			if (id!=null && id>0) {
				tgm.update(tg);
			} else {
				id=tgm.save(tg);
			}
			session.commit();
			return id;
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#delete(com.jinjiang.hotel.domain.CarRental)
	 */
	@Override
	public void delete(TourGuiding tg) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			if(tg.getId()!=null)
				tgm.delete(tg);
			session.commit();
		} finally {
		  session.close();
		}
	}

	@Override
	public TourGuiding getById(Integer id) {
		if(id==null||id<1) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			return tgm.getById(id);
		} finally {
		  session.close();
		}
	}

	@Override
	public List<TourGuiding> getAll() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			return tgm.getAll();
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.TourGuidingService#getByLanguage(java.lang.String)
	 */
	@Override
	public List<TourGuiding> getByLanguage(String language) {
		if(language==null||language.trim().isEmpty()) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			return tgm.getByLanguage(language);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.TourGuidingService#getByRoute(java.lang.String)
	 */
	@Override
	public List<TourGuiding> getByRoute(String route) {
		if(route==null||route.trim().isEmpty()) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			return tgm.getByRoute(route);
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.TourGuidingService#getRoutes()
	 */
	@Override
	public List<String> getRoutes() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			TourGuidingMapper tgm=session.getMapper(TourGuidingMapper.class);
			return tgm.getRoutes();
		} finally {
		  session.close();
		}
	}

	
	
}
