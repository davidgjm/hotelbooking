package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.GuestService;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.impl.GuestServiceImpl;
import com.jinjiang.hotel.service.impl.HotelServiceImpl;

public class PaymentModeMapperTest {
	private SqlSession session;
	private PaymentModeMapper mapper;
	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(PaymentModeMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		PaymentMode pm=new PaymentMode("前台现付");
		mapper.save(pm);
		session.commit();
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

}
