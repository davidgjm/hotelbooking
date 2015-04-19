package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.User;
import com.jinjiang.hotel.orm.MyBatisUtil;

public class UserMapperTest {
	private SqlSession session;
	private UserMapper mapper;
	@Before
	public void setUp() throws Exception {
		session=MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(UserMapper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		String userName="david";
		String password="david";
		String email="gao.jianmin@qq.com";
		User user=new User(userName, password, email);
		Integer id=mapper.save(user);
		session.commit();
		assertNotNull(id);
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetById() {
		User user= mapper.getById(3);
		assertNotNull(user);
		System.out.println(user);
	}

	@Test
	public void testGetByUserName() {
		User user= mapper.getByUserName("admin");
		assertNotNull(user);
		System.out.println(user);
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

}
