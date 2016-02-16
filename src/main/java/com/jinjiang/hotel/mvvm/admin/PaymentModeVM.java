/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.impl.HotelServiceImpl;

/**
 * @author gaojianm
 *
 */
public class PaymentModeVM{
	private ListModelList<PaymentMode> modes;
	private PaymentMode selected;
	
	public HotelService getService() {
		return HotelServiceImpl.getInstance();
	}

	/**
	 * @return the modes
	 */
	public ListModelList<PaymentMode> getModes() {
		if (modes==null) {
			modes=new ListModelList<PaymentMode>(getService().getAllPaymentModes());
		}
		return modes;
	}

	/**
	 * @param modes the modes to set
	 */
	public void setModes(ListModelList<PaymentMode> modes) {
		this.modes = modes;
	}


	/**
	 * @return the selected
	 */
	public PaymentMode getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(PaymentMode selected) {
		this.selected = selected;
	}
	
	@NotifyChange({"selected","modes"})
	@Command
	public void doCreate() {
		PaymentMode cr=new PaymentMode();
		getModes().add(cr);
		selected=cr;
	}
	
	@NotifyChange({"selected","modes"})
	@Command
	public void doSave() {
		getService().saveOrUpdate(selected);
	}
	
	@NotifyChange({"selected","modes"})
	@Command
	public void doDelete() {
		getService().delete(selected);
	}
	
}
