/**
 * 
 */
package com.jinjiang.hotel.service;

import com.jinjiang.hotel.domain.User;

/**
 * @author gaojianm
 *
 */
public interface AuthenticationService {
	User getAuthenticationUser();
	boolean login(String userName, String password);
	void logout();
	boolean changePassword(User user, String oldPasswd, String newPass);
	void update(User user);
	
}
