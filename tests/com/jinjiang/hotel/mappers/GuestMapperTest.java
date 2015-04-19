package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;

public class GuestMapperTest {
	private SqlSession session; 
	private GuestMapper mapper;
	private ExpoMapper em;
	private Expo expo;
	@Before
	public void setUp() throws Exception {
		session= com.jinjiang.hotel.orm.MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(GuestMapper.class);
		em=session.getMapper(ExpoMapper.class);
		expo=em.getById(1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		Guest g=new Guest("david-types","1234566777","abs@abc.com","098372291-23");
		g.setExpo(expo);
		mapper.save(g);
		session.commit();
	}

	@Test
	public void testDelete() {
		Guest g=mapper.getById(1);
		mapper.delete(g);
		session.commit();
		
		Guest deleted=mapper.getById(1);
		assertTrue(deleted==null);
	}

	@Test
	public void testUpdate() {
		Guest g=mapper.getById(2);
		g.setName1("Obama");
		mapper.update(g);
		session.commit();
	}

	@Test
	public void testGetById() {
		Guest g=mapper.getById(1);
		assertTrue(g!=null);
		System.out.println(g);
	}

	@Test
	public void testGetAll() {
		List<Guest> guests=mapper.getAll();
		assertTrue(guests!=null&&!guests.isEmpty());
	}

	@Test
	public void testGetGuestsByExpo() {
		List<Guest> guests=mapper.getGuestsByExpo(expo);
		assertTrue(guests!=null&&!guests.isEmpty());
	}

}
