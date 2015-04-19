package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.orm.MyBatisUtil;

public class CarRentalMapperTest {
	private SqlSession session; 
	private CarRentalMapper crm;

	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		crm=session.getMapper(CarRentalMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		CarRental cr=new CarRental("别克君越\r\n4座", "虹桥机场单接/送","300元/次");
		Integer id=crm.save(cr);
		session.commit();
		assertTrue(id!=null);
		System.out.println(cr);
	}

	@Test
	public void testUpdate() {
		CarRental cr=crm.getById(1);
		cr.setNote("test note update");
		crm.update(cr);
		session.commit();
	}

	private void create(CarRental cr) {
		crm.save(cr);
		session.commit();
	}
	
	@Test
	public void testDelete() {
		CarRental cr=crm.getById(2);
		crm.delete(cr);
		session.commit();
	}


	@Test
	public void testGetAll() {
		List<CarRental> cars=crm.getAll();
		assertTrue(cars!=null&&!cars.isEmpty());
		for (CarRental carRental : cars) {
			System.out.println(carRental);
		}
	}

}
