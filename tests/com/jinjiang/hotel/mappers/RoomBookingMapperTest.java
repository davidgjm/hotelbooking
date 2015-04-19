package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.GuestService;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.impl.GuestServiceImpl;
import com.jinjiang.hotel.service.impl.HotelServiceImpl;

public class RoomBookingMapperTest {
	private SqlSession session;
	private RoomBookingMapper mapper;
	private HotelService hs;
	private GuestService gs;
	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(RoomBookingMapper.class);
		gs=GuestServiceImpl.getInstance();
		hs=HotelServiceImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		Guest guest=gs.getById(2);
		Room room=hs.getRoomById(1);
		RoomBooking rb=new RoomBooking(guest,room,2);
		rb.setCheckinDate(new Date());
		rb.setCheckoutDate(new Date());
		Integer id=mapper.save(rb);
		session.commit();
		assertNotNull(id);
		System.out.println(id);
	}

	@Test
	public void testUpdate() {
		RoomBooking rb=mapper.getById(3);
		Room room=hs.getRoomById(2);
		rb.setRoom(room);
		mapper.update(rb);
		session.commit();
	}

	@Test
	public void testDelete() {
		RoomBooking rb=mapper.getById(3);
		mapper.delete(rb);
		session.commit();
	}

	@Test
	public void testGetById() {
		RoomBooking rb=mapper.getById(2);
		assertTrue(rb!=null);
		System.out.println(rb);
	}

	@Test
	public void testGetByGuest() {
		Guest guest=gs.getById(6);
		List<RoomBooking> rbs=mapper.getByGuest(guest);
		assertTrue(rbs!=null&&!rbs.isEmpty());
		for (RoomBooking rb : rbs) {
			System.out.println(rb);
		}
	}

	@Test
	public void testGetByRoom() {
		Room room=hs.getRoomById(3);
		List<RoomBooking> rbs=mapper.getByRoom(room);
		assertNotNull(rbs);
		for (RoomBooking rb : rbs) {
			System.out.println(rb);
		}
	}
}
