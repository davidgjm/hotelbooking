/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.jinjiang.hotel.config.HashUtil;
import com.jinjiang.hotel.domain.User;
import com.jinjiang.hotel.mappers.UserMapper;
import com.jinjiang.hotel.orm.MyBatisUtil;
import com.jinjiang.hotel.service.UserService;

/**
 * @author gaojianm
 *
 */
public class UserServiceImpl implements UserService {
	private static UserService instance;
	/**
	 * 
	 */
	private UserServiceImpl() {
	}

	public static synchronized UserService getInstance() {
		if(instance==null) instance=new UserServiceImpl();
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.UserService#save(com.jinjiang.hotel.domain.User)
	 */
	@Override
	public Integer save(User user) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			UserMapper mapper=session.getMapper(UserMapper.class);
			Integer id= mapper.save(user);
			session.commit();
			return id;
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.UserService#update(com.jinjiang.hotel.domain.User)
	 */
	@Override
	public void update(User user) {
		SqlSession session=MyBatisUtil.getSession();
		try {
			UserMapper mapper=session.getMapper(UserMapper.class);
			mapper.update(user);
			session.commit();
		} finally {
		  session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.UserService#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean changePassword(String userName, String oldPassword,
			String newPassword) {
		if(oldPassword.equals(newPassword)) return false;
		String hashedOld=HashUtil.encrypt(oldPassword);
		SqlSession session=MyBatisUtil.getSession();
		try {
			UserMapper mapper=session.getMapper(UserMapper.class);
			User dbUser=mapper.getByUserName(userName);
			if(dbUser==null) return false;
			if(!dbUser.getPassword().equals(hashedOld)) return false;
			dbUser.setPassword(HashUtil.encrypt(newPassword));
			mapper.changePassword(dbUser);
			session.commit();
		} finally {
		  session.close();
		}
		
		return true;
	}

	@Override
	public User findUser(String userName) {
		if(userName==null||userName.trim().isEmpty()) return null;
		SqlSession session=MyBatisUtil.getSession();
		try {
			UserMapper mapper=session.getMapper(UserMapper.class);
			return mapper.getByUserName(userName);
		} finally {
		  session.close();
		}
	}

}
