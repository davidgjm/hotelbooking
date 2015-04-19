package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.orm.MyBatisUtil;

public class TourGuidingMapperTest {
	private SqlSession session;
	private TourGuidingMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(TourGuidingMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		TourGuiding tg=new TourGuiding("英文导游","浦东机场接/送机","200元/次");
		mapper.save(tg);
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

	@Test
	public void testGetByLanguage() {
		String language="英文导游";
		List<TourGuiding> tgs=mapper.getByLanguage(language);
		assertTrue(tgs!=null && !tgs.isEmpty());
	}

	@Test
	public void testGetByRoute() {
		fail("Not yet implemented"); // TODO
	}

}
