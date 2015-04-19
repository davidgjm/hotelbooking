package com.jinjiang.hotel.service;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.mappers.ExpoMapper;
import com.jinjiang.hotel.mappers.GuestMapper;
import com.jinjiang.hotel.mappers.HotelMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.impl.ExpoServiceImpl;
import com.jinjiang.hotel.service.impl.GuestServiceImpl;

public class GuestServiceTest {
	private static GuestService service=GuestServiceImpl.getInstance();
	private SqlSession session=MyBatisUtil.getSessionFactory().openSession();
	private ExpoMapper em;
	
	@Before
	public void setUp() throws Exception {
		em=session.getMapper(ExpoMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveOrUpdateGuest() {
		Guest guest=new Guest("test1--23", "9038-13", "abs@foo.bar", "9038872");
		Expo expo=em.getById(1);
		guest.setExpo(expo);
		service.saveOrUpdateGuest(guest);
	}

	@Test
	public void testDuplicateSave() {
		
		Expo expo=em.getById(2);
		Guest guest=new Guest("欧影谊", "0756-8677666", "joannaau@hwtel.com", "0756-8677000");
		guest.setExpo(expo);
		service.saveOrUpdateGuest(guest);
	}

}
