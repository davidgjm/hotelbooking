/**
 * 
 */
package com.jinjiang.hotel.mappers;

import com.jinjiang.hotel.GenericTest;
import com.jinjiang.hotel.domain.Guest;

/**
 * @author gaojianm
 *
 */
public class GenericBookingTest extends GenericTest {
	protected GuestMapper guestMapper=session.getMapper(GuestMapper.class);
	
	/**
	 * @param id
	 * @return
	 */
	public Guest getGuest(Integer id) {
		return guestMapper.getById(id);
	}
}
