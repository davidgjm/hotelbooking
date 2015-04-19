/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.User;

/**
 * @author gaojianm
 *
 */
public interface UserMapper {
	Integer save(User user);
	void delete(User user);
	void update(User user);
	void changePassword(User user);
	User getById(Integer id);
	User getByUserName(String userName);
	List<User> getAll();
}
