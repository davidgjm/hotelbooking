/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.service.AuthenticationService;
import com.jinjiang.hotel.service.impl.AuthenticationServiceImpl;
import com.jinjiang.hotel.validators.GenericStringValidator;

/**
 * @author gaojianm
 *
 */
public class AuthenticationVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private AuthenticationService as;
	private String userName;
	private String password;
	private String message;
	/**
	 * 
	 */
	public AuthenticationVM() {
		as=new AuthenticationServiceImpl();
	}
	
	@NotifyChange("message")
	@Command
	public void doLogin() {
		boolean isOK=as.login(userName, password);
		if (log.isInfoEnabled()) {
			log.info("Is user login successful? {}", isOK);
		}
		if (!isOK) {
			message="登录失败。用户名或密码不正确！";
			return;
		} 

		Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_HOME);
	}
	
	@Command
	public void doLogout() {
		if (log.isInfoEnabled()) {
			log.info("Logging out user: [{}]", as.getAuthenticationUser().getUserName());
		}
		as.logout();
		if (log.isDebugEnabled()) {
			log.debug("Redirecting user to home page....");
		}
		Executions.sendRedirect(ZKConstants.REDIRECTS_HOME);
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
