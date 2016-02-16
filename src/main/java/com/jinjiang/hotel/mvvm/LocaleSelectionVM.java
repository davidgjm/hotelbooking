/**
 * 
 */
package com.jinjiang.hotel.mvvm;

import java.util.Locale;

import org.zkoss.bind.annotation.Command;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;

/**
 * @author gaojianm
 *
 */
public class LocaleSelectionVM {

	@Command
	public void changeToEnglish() {
		changeLocale(Locale.ENGLISH);
	}
	
	@Command
	public void changeToDefault() {
		changeLocale(Locale.SIMPLIFIED_CHINESE);
	}
	
	
	private void changeLocale(Locale locale) {
		Sessions.getCurrent().setAttribute(Attributes.PREFERRED_LOCALE, locale);
		Executions.sendRedirect(null); //reload the same page
	}
}
