package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.orm.MyBatisUtil;

public class ExpoMapperTest {
	private SqlSession session; 
	private ExpoMapper mapper;
	private SimpleDateFormat sdf;
	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(ExpoMapper.class);
		sdf=new SimpleDateFormat("yyyy-MM-dd");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() throws ParseException {
		Expo expo=new Expo("中国国际家具展览会");
		expo.setIntroduction("n/a");
		expo.setVenue("上海新国际博览中心");
		expo.setBeginDate(sdf.parse("2013-09-11"));
		expo.setEndDate(sdf.parse("2013-09-15"));
		
		mapper.save(expo);
		session.commit();
	}

	@Test
	public void testDelete() throws ParseException {
/*		Expo expo=new Expo("China Clean expo");
		expo.setIntroduction("n/a");
		expo.setVenue("上海新国际博览中心");
		expo.setBeginDate(sdf.parse("2013-09-11"));
		expo.setEndDate(sdf.parse("2013-09-15"));
		
		mapper.save(expo);
		session.commit();*/
		Expo expo=mapper.getById(mapper.getLast());
		mapper.delete(expo);
		session.commit();
	}

	@Test
	public void testUpdate() {
		Expo expo=mapper.getById(mapper.getLast());
		expo.setIntroduction("test updated text");
		mapper.update(expo);
		session.commit();
	}

	@Test
	public void testGetById() {
		Expo expo=mapper.getById(mapper.getLast());
		assertTrue(expo!=null);
		System.out.println(expo);
	}

	@Test
	public void testGetAll() {
		List<Expo> expos=mapper.getAll();
		assertTrue(expos!=null &&!expos.isEmpty());
	}
	@Test
	public void testGetCurrent() {
		List<Expo> expos=mapper.getCurrent();
		if (expos!=null &&!expos.isEmpty()) {
			System.out.println("current expos: "+expos.size());
			assertTrue(expos.size()!=mapper.getAll().size());
		}else
			fail("No current expos found");
	}
	@Test
	public void testGetCurrentEnglish() {
		List<Expo> expos=mapper.getCurrentEnglish();
		if (expos!=null &&!expos.isEmpty()) {
			System.out.println("current expos: "+expos.size());
			assertTrue(expos.size()!=mapper.getAll().size());
		}else
			fail("No current expos found");
	}

	@Test
	public void testGetByName() {
		String name="China Clean expo";
		List<Expo> expos=mapper.getByName(name);
		assertTrue(expos!=null &&!expos.isEmpty());
	}

}
