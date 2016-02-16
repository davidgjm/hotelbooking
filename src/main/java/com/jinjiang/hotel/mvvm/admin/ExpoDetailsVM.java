/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.service.ExpoService;
import com.jinjiang.hotel.service.impl.ExpoServiceImpl;

/**
 * @author gaojianm
 *
 */
public class ExpoDetailsVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private ListModelList<Hotel> expoHotels;
	private ListModelList<Hotel> hotels;
	private Expo expo;
	private Boolean isUpdated=false;
	
	@Init
	public void loadAdminExpo() {
		expo=(Expo) Executions.getCurrent().getSession().getAttribute(ZKConstants.ATTRS_ADMIN_EXPO);
	}
	
	public ExpoService getService() {
		return ExpoServiceImpl.getInstance();
	}
	
	
	
	/**
	 * @return the isUpdated
	 */
	public Boolean getIsUpdated() {
		return isUpdated;
	}

	/**
	 * @param isUpdated the isUpdated to set
	 */
	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

	/**
	 * @return the hotels
	 */
	public ListModelList<Hotel> getHotels() {
		if (hotels==null) {
			List<Hotel> all=getService().getAllHotels();
			List<Hotel> hotels2=getService().getHotels(expo);
			if(hotels2 !=null)
				all.removeAll(hotels2);
			hotels=new ListModelList<Hotel>(all);
		}
		this.hotels.setMultiple(true);
		return hotels;
	}

	/**
	 * @param hotels the hotels to set
	 */
	public void setHotels(ListModelList<Hotel> hotels) {
		this.hotels = hotels;
		this.hotels.setMultiple(true);
	}

	/**
	 * @return the hotels
	 */
	public ListModelList<Hotel> getExpoHotels() {
		if (expoHotels==null && expo!=null) {
			expoHotels=new ListModelList<Hotel>(getService().getHotels(expo));
			expoHotels.setMultiple(true);
		}
		return expoHotels;
	}
	/**
	 * @param hotels the hotels to set
	 */
	public void setExpoHotels(ListModelList<Hotel> hotels) {
		this.expoHotels = hotels;
	}
	

	@NotifyChange({"hotels","expoHotels"})
	@Command
	public void handleAddHotels() {
		Set<Hotel> selectedHotels= hotels.getSelection();
		if (selectedHotels==null || selectedHotels.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("No hotel selected");
			}
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug("{} hotels selected.",selectedHotels.size());
		}
		
		for (Hotel hotel : selectedHotels) {
			if(!expoHotels.contains(hotel)) expoHotels.add(hotel);
		}
		hotels.removeAll(selectedHotels);
		isUpdated=true;
		if (log.isDebugEnabled()) {
			log.debug("isNotUpdated: {}",isUpdated);
		}
	}
	
	@NotifyChange({"hotels","expoHotels"})
	@Command
	public void handleRemoveHotels() {
		if(expoHotels.isEmpty()) return;
		Set<Hotel> selectedHotels= expoHotels.getSelection();
		if (selectedHotels==null || selectedHotels.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("No hotel to remove");
			}
			return;
		}
		if (log.isDebugEnabled()) {
			log.debug("{} hotels selected.");
		}
		for (Hotel hotel : selectedHotels) {
			if(!hotels.contains(hotel)) hotels.add(hotel);
		}
		expoHotels.removeAll(selectedHotels);
		isUpdated=true;
	}
	
	@NotifyChange({"hotels","expoHotels"})
	@Command
	public void updateExpo() {
		if (isUpdated) {
			getService().updateExpoHotels(expo, expoHotels);
			Messagebox.show("酒店关联信息保存成功！");
		}
		
	}
	
	@NotifyChange({"hotels","expoHotels"})
	@Command
	public void revertChanges() {
		if(!isUpdated) return;
		expoHotels=new ListModelList<Hotel>(getService().getHotels(expo));
		expoHotels.setMultiple(true);
		List<Hotel> all=getService().getAllHotels();
		all.removeAll(getService().getHotels(expo));
		hotels=new ListModelList<Hotel>(all);
	}
	
	@Command
	public void close() {
		log.info("Going to page: {}",ZKConstants.PAGE_ADMIN_EXPO);
		Executions.sendRedirect(ZKConstants.PAGE_ADMIN_EXPO);
	}
}
