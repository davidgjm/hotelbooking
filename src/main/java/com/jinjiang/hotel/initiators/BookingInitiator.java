/**
 * 
 */
package com.jinjiang.hotel.initiators;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericInitiator;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.GuestBooking;

/**
 * @author gaojianm
 *
 */
public class BookingInitiator extends GenericInitiator {
	protected Logger log=LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.GenericInitiator#doInit(org.zkoss.zk.ui.Page, java.util.Map)
	 */
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		GuestBooking booking=(GuestBooking) Sessions.getCurrent().getAttribute(ZKConstants.ATTRS_ADMIN_BOOKING);
		if(booking==null) {
			if (log.isWarnEnabled()) {
				log.warn("The select booking is not found in session. Redirecting to bookings page.");
			}
			Execution exec = Executions.getCurrent();
			HttpServletResponse response = (HttpServletResponse)exec.getNativeResponse();
			response.sendRedirect(response.encodeRedirectURL(ZKConstants.REDIRECTS_ADMIN_BOOKINGS));
			exec.setVoided(true);
		}
	}

}
