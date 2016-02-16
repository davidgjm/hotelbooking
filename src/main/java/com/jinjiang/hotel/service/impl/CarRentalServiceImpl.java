/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.mappers.CarRentalMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.CarRentalService;

/**
 * @author gaojianm
 *
 */
public class CarRentalServiceImpl implements CarRentalService {
	private Logger log=LoggerFactory.getLogger(getClass());
	private static CarRentalService instance;
	/**
	 * 
	 */
	private CarRentalServiceImpl() {
	}
	
	public static synchronized CarRentalService getInstance() {
		if(instance==null) instance=new CarRentalServiceImpl();
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#saveOrUpdateCarRental(com.jinjiang.hotel.domain.CarRental)
	 */
	@Override
	public Integer saveOrUpdateCarRental(CarRental rental) {
		if(rental==null) {
			if (log.isWarnEnabled()) {
				log.warn("CarRental is invalid. [{}]", rental);
			}
			return null;
		}
		Integer id=rental.getId();
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			if (id!=null) {
				crm.update(rental);
			} else {
				crm.save(rental);
			}
			session.commit();
		}finally {
			session.close();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#delete(com.jinjiang.hotel.domain.CarRental)
	 */
	@Override
	public void delete(CarRental rental) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			if(rental.getId()!=null)
				crm.delete(rental);
			session.commit();
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#getById(java.lang.Integer)
	 */
	@Override
	public CarRental getById(Integer id) {
		if(id==null||id<1) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			return crm.getById(id);
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#getAll()
	 */
	@Override
	public List<CarRental> getAll() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			return crm.getAll();
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#getCars()
	 */
	@Override
	public List<String> getCars() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			return crm.getCars();
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.CarRentalService#getCarUsages()
	 */
	@Override
	public List<String> getCarUsages() {
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			return crm.getUsages();
		}finally {
			session.close();
		}
	}

	@Override
	public Map<String, List<CarRental>> getCarRentalMatrixByUsage() {
		List<String> uses=getCarUsages();
		Map<String, List<CarRental>> matrix=new TreeMap<String, List<CarRental>>();
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			for (String use : uses) {
				List<CarRental> cars=crm.getByUsage(use);
				matrix.put(use, cars);
			}
		}finally {
			session.close();
		}
		return matrix;
	}

	@Override
	public List<CarRental> getCarRentalsByUsage(String usage) {
		if(usage==null||usage.trim().isEmpty()) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			return crm.getByUsage(usage);
		}finally {
			session.close();
		}
	}

	@Override
	public List<CarRental> getCarRentalsByCar(String car) {
		if(car==null||car.trim().isEmpty()) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			CarRentalMapper crm=session.getMapper(CarRentalMapper.class);
			return crm.getByCar(car);
		}finally {
			session.close();
		}
	}
	
	
}
