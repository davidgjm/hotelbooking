/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.util.Date;

/**
 * @author gaojianm
 *
 */
public abstract class Booking {
	protected Integer id;
	protected Guest guest;
	protected Date bookDate=new Date();
	protected String note;
	
	
	/**
	 * @param guest
	 */
	protected Booking(Guest guest) {
		super();
		this.guest = guest;
	}
	/**
	 * 
	 */
	protected Booking() {
	}
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
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the bookDate
	 */
	public Date getBookDate() {
		return bookDate;
	}
	/**
	 * @param bookDate the bookDate to set
	 */
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
//	@Override
//	public abstract boolean equals(Object obj);
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();

	
}
