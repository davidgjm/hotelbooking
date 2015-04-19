package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.service.GuestService;
import com.jinjiang.hotel.service.impl.GuestServiceImpl;

public class GuestBookingMapperTest extends GenericBookingTest{
	private GuestBookingMapper mapper;
	private GuestService gs=GuestServiceImpl.getInstance();
	@Before
	public void setUp() throws Exception {
		mapper=session.getMapper(GuestBookingMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		fail("Not yet implemented"); // TODO
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
	public void testDeleteAll() {
		Guest guest=gs.getById(16);
		mapper.deleteAll(guest);
		session.commit();
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllBookings() {
		List<GuestBooking> bookings=mapper.getAllBookings();
		assertTrue(bookings!=null && !bookings.isEmpty());
		for (GuestBooking booking : bookings) {
			System.out.println(booking);
		}
	}

}
