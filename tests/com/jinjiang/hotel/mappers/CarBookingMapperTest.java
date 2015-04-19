package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.GenericTest;
import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.CarRental;

public class CarBookingMapperTest extends GenericBookingTest {
	private CarBookingMapper mapper=session.getMapper(CarBookingMapper.class);
	private CarRentalMapper carMapper=session.getMapper(CarRentalMapper.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		CarRental carRental=carMapper.getById(6);
		CarBooking cb=new CarBooking(getGuest(15), 2, carRental);
		mapper.save(cb);
		session.commit();
	}

	@Test
	public void testUpdate() {
		String note="test update ->"+System.currentTimeMillis();
		CarBooking cb=mapper.getById(mapper.getLastId());
		cb.setNote(note);
		mapper.update(cb);
		session.commit();
		
		CarBooking updated=mapper.getById(cb.getId());
		assertTrue(updated.getNote().equals(note));
	}

	@Test
	public void testDelete() {
		CarBooking cb=mapper.getById(mapper.getLastId());
		System.out.println("deleting "+cb);
		mapper.delete(cb);
		session.commit();
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetBookingsByGuest() {
		List<CarBooking> bookings=mapper.getByGuest(getGuest(15));
		assertTrue(bookings!=null &&!bookings.isEmpty());
		for (CarBooking booking : bookings) {
			System.out.println(booking);
		}
	}
	@Test
	public void testGetLastId() {
		Integer lastId=mapper.getLastId();
		assertNotNull(lastId);
		System.out.println(lastId);
	}

}
