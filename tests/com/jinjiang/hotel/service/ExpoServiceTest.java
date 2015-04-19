package com.jinjiang.hotel.service;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.mappers.HotelMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.impl.ExpoServiceImpl;

public class ExpoServiceTest {

	private static ExpoService service=ExpoServiceImpl.getInstance();
	private SqlSession session=MyBatisUtil.getSessionFactory().openSession();
	private HotelMapper hm;
	
	@Before
	public void setUp() throws Exception {
		hm=session.getMapper(HotelMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveOrUpdateExpo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteExpo() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetById() {
		Expo expo=service.getExpoById(1);
		assertTrue(expo!=null);
	}

	@Test
	public void testGetExpoHotels() {
		Expo expo=service.getExpoById(1);
		List<Hotel> hotels=service.getHotels(expo);
		assertTrue(!hotels.isEmpty());
		for (Hotel hotel : hotels) {
			System.out.println(hotel);
		}
	}

	@Test
	public void testAddHotel() {
		Expo expo=service.getExpoById(2);
		Hotel hotel=hm.getById(2);
		service.addHotel(expo, hotel,"this hotel is not great!");
	}
}
