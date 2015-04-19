/**
 * 
 */
package com.jinjiang.hotel.mvvm;

import java.util.List;
import java.util.Locale;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.mvvm.admin.ExpoVM;

/**
 * @author gaojianm
 *
 */
public class WelcomeVM extends ExpoVM {

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.mvvm.ExpoVM#getExpos()
	 */
	@Override
	public List<Expo> getExpos() {
		if (expos==null) {
			Locale locale=(Locale) Sessions.getCurrent().getAttribute(Attributes.PREFERRED_LOCALE);
			if (locale==null||locale.equals(Locale.SIMPLIFIED_CHINESE)) {
				expos=new ListModelList<Expo>(getService().getCurrentExpos(false));
			}else if (locale.equals(Locale.ENGLISH)) {
				expos=new ListModelList<Expo>(getService().getCurrentExpos(true));
			}
		}
		return expos;
	}


	@Command
	public void showDetails(@BindingParam("eid") Integer eid) {
		Expo expo=getService().getExpoById(eid);
		Executions.getCurrent().getSession().setAttribute(ZKConstants.ATTRS_FRONT_EXPO, expo);
		if (log.isDebugEnabled()) {
			log.debug("Showing expo details page for [{}]", expo.getName());
		}
		Executions.getCurrent().sendRedirect(ZKConstants.REDIRECTS_FRONT_EXPO);
	}
}
