/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.jinjiang.hotel.config.HashUtil;
import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.User;
import com.jinjiang.hotel.service.AuthenticationService;
import com.jinjiang.hotel.service.UserService;

/**
 * @author gaojianm
 *
 */
public class AuthenticationServiceImpl implements AuthenticationService {
	private UserService us;
	private Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	public AuthenticationServiceImpl() {
		us=UserServiceImpl.getInstance();
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.AuthenticationService#getAuthenticationUser()
	 */
	@Override
	public User getAuthenticationUser() {
		return (User) Sessions.getCurrent().getAttribute(ZKConstants.ATTRS_ADMIN_USER);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.AuthenticationService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean login(String userName, String password) {
		User user=us.findUser(userName);
		if(user==null) {
			if (log.isWarnEnabled()) {
				log.warn("User not found for user name: [{}]",userName);
			}
			return false;
		}
		String passwordHashed=HashUtil.encrypt(password);
		if(!passwordHashed.equals(user.getPassword())) return false;
		
		Sessions.getCurrent().setAttribute(ZKConstants.ATTRS_ADMIN_USER, user);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.AuthenticationService#logout()
	 */
	@Override
	public void logout() {
		Session sess=Sessions.getCurrent();
		sess.removeAttribute(ZKConstants.ATTRS_ADMIN_USER);
		sess.invalidate();
	}

	@Override
	public boolean changePassword(User user, String oldPasswd, String newPass) {
		return us.changePassword(user.getUserName(), oldPasswd, newPass);
	}

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.AuthenticationService#update(com.jinjiang.hotel.domain.User)
	 */
	@Override
	public void update(User user) {
		if(user==null||user.getId()==null) return;
		us.update(user);
	}

}
