/**
 * 
 */
package com.jinjiang.hotel.mvvm;

import com.jinjiang.hotel.domain.Guest;
/**
 * @author gaojianm
 *
 */
public class GuestForm {
	private Guest guest=new Guest();

	/**
	 * @return the guest
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * @param guest the guest to set
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
}
