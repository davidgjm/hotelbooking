/**
 * 
 */
package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.*;
import com.jinjiang.hotel.orm.MyBatisUtil;

/**
 * @author gaojianm
 *
 */
public class RoomMapperTest {

	private SqlSession session;
	private RoomMapper mapper;
	private HotelMapper hmapper;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(RoomMapper.class);
		hmapper=session.getMapper(HotelMapper.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.RoomMapper#save(com.jinjiang.hotel.domain.Room)}.
	 */
	@Test
	public void testSave() {
		Hotel h=hmapper.getById(1);
		Room r=new Room("高级大床房",h,"300元/晚、间");
		r.setIsFull(false);
		r.setNote("（含早餐和展览期间早晚班车）");
		mapper.save(r);
		session.commit();
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.RoomMapper#delete(com.jinjiang.hotel.domain.Room)}.
	 */
	@Test
	public void testDelete() {
		Integer lastId=mapper.getLast();
		System.out.println("last id: "+lastId);
		Room r=mapper.getById(lastId);
		mapper.delete(r);
		session.commit();
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.RoomMapper#update(com.jinjiang.hotel.domain.Room)}.
	 */
	@Test
	public void testUpdate() {
		Room r=mapper.getById(1);
		r.setIsFull(true);
		mapper.update(r);
		session.commit();
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.RoomMapper#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetById() {
		Room r=mapper.getById(1);
		assertTrue(r!=null);
		System.out.println(r);
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.RoomMapper#getAll()}.
	 */
	@Test
	public void testGetAll() {
		List<Room> rooms=mapper.getAll();
		assertTrue(rooms!=null&& !rooms.isEmpty());
		for (Room room : rooms) {
			System.out.println(room);
		}
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.RoomMapper#getByHotel(com.jinjiang.hotel.domain.Hotel)}.
	 */
	@Test
	public void testGetByHotel() {
		Hotel hotel=hmapper.getById(1);
		List<Room> rooms=mapper.getByHotel(hotel);
		assertTrue(rooms!=null&& !rooms.isEmpty());
		for (Room room : rooms) {
			System.out.println(room);
		}
	}

}
