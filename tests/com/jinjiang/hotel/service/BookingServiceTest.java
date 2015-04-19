package com.jinjiang.hotel.service;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.impl.BookingServiceImpl;
import com.jinjiang.hotel.service.impl.ExpoServiceImpl;

public class BookingServiceTest {
	private SqlSession session=MyBatisUtil.getSessionFactory().openSession();
	private BookingService bs=BookingServiceImpl.getInstance();
	private ExpoService es=ExpoServiceImpl.getInstance();
	private Expo expo;
	@Before
	public void setUp() throws Exception {
		expo=es.getExpoById(3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveOrUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRoomBookingHotel() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetRoomBookingExpo() {
		List<RoomBooking> bookings=bs.getRoomBooking(expo);
		assertTrue(bookings!=null &&!bookings.isEmpty());
		for (RoomBooking booking : bookings) {
			System.out.println(booking);
		}
	}

}
