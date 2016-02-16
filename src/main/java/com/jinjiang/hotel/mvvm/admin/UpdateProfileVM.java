/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.config.AppConfig;
import com.jinjiang.hotel.config.HashUtil;
import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.User;
import com.jinjiang.hotel.service.AuthenticationService;
import com.jinjiang.hotel.service.impl.AuthenticationServiceImpl;

/**
 * @author gaojianm
 *
 */
public class UpdateProfileVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private AuthenticationService as;
	private AppConfig config;
	private User user;
	private String password;
	private String newPassword;
	private String retypePassword;
	private String message;
	/**
	 * 
	 */
	public UpdateProfileVM() {
		as=new AuthenticationServiceImpl();
		config=AppConfig.getInstance();
	}
	
	@Init
	public void doInit() {
		user=(User) Sessions.getCurrent().getAttribute(ZKConstants.ATTRS_ADMIN_USER);
		
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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

	private boolean isPasswordFormValid() {
		if( password==null||newPassword==null || retypePassword==null) return false;
		if(password!=null && newPassword!=null && retypePassword!=null) return true;
		return true;
	}
	
	@NotifyChange({"user","message"})
	@Command
	public void doUpdateProfile() {
		
		/*
		 * Updating user information
		 */
		as.update(user);
		Sessions.getCurrent().setAttribute(ZKConstants.ATTRS_ADMIN_USER, user);
		Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_HOME);
	}
	@NotifyChange({"user","message"})
	@Command
	public void doChangePassword() {
		boolean isValid=isPasswordFormValid();
		if(!isValid) return;
		if (log.isDebugEnabled()) {
			log.debug("Is password form valid? {}",isValid);
		}
		
		if(!newPassword.equals(retypePassword)) {
			showError("两次输入密码不一致！");
			return;
		}
		User authUser=as.getAuthenticationUser();
		String encrypted=HashUtil.encrypt(password);
		if (log.isDebugEnabled()) {
			log.debug("Old Hashed: [{}], new hashed: [{}]",user.getPassword(), encrypted);
		}
		if (!authUser.getPassword().equals(encrypted)) {
			showError("原始密码错误！");
			return;
		}
		String hashedNew=HashUtil.encrypt(newPassword);
		if(hashedNew.equals(user.getPassword())) {
			showError("新密码和原始密码不能重复");
			return;
		}
		
		if (newPassword.length()< config.getInt("validation.passwordMinLength")) {
			showError("新密码最小长度为 6。请设定长度不小于 6 的密码。");
			return;
		}
		
		boolean isOK=as.changePassword(user, password, newPassword);
		if(!isOK) {
			showError("密码修改失败！");
			return;
		}else {
			Messagebox.show("密码修改成功", "成功", Messagebox.OK, Messagebox.INFORMATION);
		}
		Sessions.getCurrent().setAttribute(ZKConstants.ATTRS_ADMIN_USER, user);
		Executions.sendRedirect("");
	}
	
	private void showError(String msg) {
		Messagebox.show(msg, "错误",
	    	      Messagebox.OK, Messagebox.ERROR);
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

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the retypePassword
	 */
	public String getRetypePassword() {
		return retypePassword;
	}

	/**
	 * @param retypePassword the retypePassword to set
	 */
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}


	public Validator getNameValidator() {
		return new AbstractValidator() {
			
			@Override
			public void validate(ValidationContext ctx) {
				String _value=(String) ctx.getProperty().getValue();
				if (log.isDebugEnabled()) {
					log.debug("User input: {}",_value);
				}
				if (_value==null||_value.trim().isEmpty()||_value.length()<4) {
					addInvalidMessage(ctx, "用户名为空或过短！");
				}
			}
		};
		
	}
	public Validator getPasswordValidator() {
		return new AbstractValidator() {
			
			@Override
			public void validate(ValidationContext ctx) {
				String _value=(String) ctx.getProperty().getValue();
				if (_value==null||_value.trim().isEmpty()) {
					addInvalidMessage(ctx, "密码不能为空。");
				}
			}
		};
		
	}
	public Validator getChangePasswordValidator() {
		return new AbstractValidator() {
			
			@Override
			public void validate(ValidationContext ctx) {
				String _value=(String) ctx.getProperty().getValue();
				if (_value==null||_value.trim().isEmpty()) {
					addInvalidMessage(ctx, "密码不能为空。");
				}
			}
		};
		
	}
}
