/**
 * 
 */
package com.jinjiang.hotel.mvvm;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

import com.jinjiang.hotel.HotelComparator;
import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.service.ExpoService;
import com.jinjiang.hotel.service.impl.ExpoServiceImpl;

/**
 * @author gaojianm
 *
 */
public class FrontExpoVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private ListModelList<Hotel> hotels;
	private Expo expo;
	@Init
	public void loadFrontExpo() {
		expo=(Expo) Executions.getCurrent().getSession().getAttribute(ZKConstants.ATTRS_FRONT_EXPO);
	}
	public ExpoService getService() {
		return ExpoServiceImpl.getInstance();
	}

	/**
	 * @return the hotels
	 */
	public ListModelList<Hotel> getHotels() {
		if (hotels==null && expo!=null) {
			hotels=new ListModelList<Hotel>(getService().getHotels(expo));
			Collections.sort(hotels, Collections.reverseOrder(new HotelComparator()));
		}
		return hotels;
	}

	/**
	 * @param hotels the hotels to set
	 */
	public void setHotels(ListModelList<Hotel> hotels) {
		this.hotels = hotels;
	}

	/**
	 * @return the expo
	 */
	public Expo getExpo() {
		if (expo==null) {
			expo=(Expo) Executions.getCurrent().getSession().getAttribute(ZKConstants.ATTRS_FRONT_EXPO);
		}
		return expo;
	}

	/**
	 * @param expo the expo to set
	 */
	public void setExpo(Expo expo) {
		this.expo = expo;
	}

	@Command
	public void doHotelBooking(@BindingParam("hotel") Hotel hotel) {
		if (log.isDebugEnabled()) {
			log.debug("Book hotel {}", hotel.getName());
		}
		Executions.getCurrent().setAttribute("_hotel", hotel);
		Executions.getCurrent().getSession().setAttribute(ZKConstants.ATTRS_FRONT_HOTEL, hotel);
		
		Executions.sendRedirect(ZKConstants.PAGE_REGISTER);
	}
}
