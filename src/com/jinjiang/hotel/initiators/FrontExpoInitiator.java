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
import org.zkoss.zk.ui.util.GenericInitiator;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.Expo;

/**
 * @author gaojianm
 *
 */
public class FrontExpoInitiator extends GenericInitiator {
	protected Logger log=LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.GenericInitiator#doInit(org.zkoss.zk.ui.Page, java.util.Map)
	 */
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		Expo expo=(Expo) Executions.getCurrent().getSession().getAttribute(ZKConstants.ATTRS_FRONT_EXPO);

		if(expo==null) {
			if (log.isWarnEnabled()) {
				log.warn("The expo is not found in session. Redirecting to home page.");
			}
			Execution exec = Executions.getCurrent();
			HttpServletResponse response = (HttpServletResponse)exec.getNativeResponse();
			response.sendRedirect(response.encodeRedirectURL("./"));
			exec.setVoided(true);
		}
	}

}
