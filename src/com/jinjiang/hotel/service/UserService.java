/**
 * 
 */
package com.jinjiang.hotel.service;

import com.jinjiang.hotel.domain.User;

/**
 * @author gaojianm
 *
 */
public interface UserService {
	Integer save(User user);
	void update(User user);
	User findUser(String userName);
	boolean changePassword(String userName, String oldPassword, String newPassword);
}
