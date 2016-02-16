/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.service.CarRentalService;
import com.jinjiang.hotel.service.impl.CarRentalServiceImpl;

/**
 * @author gaojianm
 *
 */
public class CarRentalVM {
	private ListModelList<CarRental> rentals;
	private CarRental selected;
	private Logger log=LoggerFactory.getLogger(getClass());
	
	public CarRentalService getService() {
		return CarRentalServiceImpl.getInstance();
	}
	
	/**
	 * @return the rentals
	 */
	public ListModelList<CarRental> getRentals() {
		if (rentals==null) {
			rentals=new ListModelList<CarRental>(getService().getAll());
		}
		return rentals;
	}

	/**
	 * @param rentals the rentals to set
	 */
	public void setRentals(ListModelList<CarRental> rentals) {
		this.rentals = rentals;
	}

	/**
	 * @return the selected
	 */
	public CarRental getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(CarRental selected) {
		this.selected = selected;
	}
	
	@NotifyChange({"selected","rentals"})
	@Command
	public void doCreate() {
		CarRental cr=new CarRental();
		getRentals().add(cr);
		selected=cr;
	}
	
	@NotifyChange({"selected","rentals"})
	@Command
	public void doSave() {
		getService().saveOrUpdateCarRental(selected);
	}
	
	@NotifyChange({"selected","rentals"})
	@Command
	public void doDelete() {
		Messagebox.show("确定要删除此记录吗？", "删除", Messagebox.OK | Messagebox.CANCEL,
	    	    Messagebox.QUESTION,new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
    	                	if (log.isInfoEnabled()) {
								log.info("Deleting selected car rental: [{}]",selected);
							}
    	            		getService().delete(selected);
    	            		rentals.remove(selected);
						}
					}
				});
	}
	
}
