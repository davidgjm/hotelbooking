/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.util.Date;

/**
 * @author gaojianm
 *
 */
public class TourGuidingBooking extends Booking {
	private TourGuiding guiding;
	private Date whenToUse;
	private Integer bookCount;
	
	
	/**
	 * @param guest
	 * @param guiding
	 * @param whenToUse
	 * @param bookCount
	 */
	public TourGuidingBooking(Guest guest, TourGuiding guiding, Date whenToUse,
			Integer bookCount) {
		super(guest);
		this.guiding = guiding;
		this.whenToUse = whenToUse;
		this.bookCount = bookCount;
	}
	/**
	 * 
	 */
	public TourGuidingBooking() {
	}
	/**
	 * @return the guiding
	 */
	public TourGuiding getGuiding() {
		return guiding;
	}
	/**
	 * @param guiding the guiding to set
	 */
	public void setGuiding(TourGuiding guiding) {
		this.guiding = guiding;
	}
	/**
	 * @return the whenToUse
	 */
	public Date getWhenToUse() {
		return whenToUse;
	}
	/**
	 * @param whenToUse the whenToUse to set
	 */
	public void setWhenToUse(Date whenToUse) {
		this.whenToUse = whenToUse;
	}
	/**
	 * @return the bookCount
	 */
	public Integer getBookCount() {
		return bookCount;
	}
	/**
	 * @param bookCount the bookCount to set
	 */
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TourGuideBooking [");
		if (guiding != null) {
			builder.append("guiding=");
			builder.append(guiding);
			builder.append(", ");
		}
		if (whenToUse != null) {
			builder.append("whenToUse=");
			builder.append(whenToUse);
			builder.append(", ");
		}
		if (bookCount != null) {
			builder.append("bookCount=");
			builder.append(bookCount);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (guest != null) {
			builder.append("guest=");
			builder.append(guest);
			builder.append(", ");
		}
		if (bookDate != null) {
			builder.append("bookDate=");
			builder.append(bookDate);
			builder.append(", ");
		}
		if (note != null) {
			builder.append("note=");
			builder.append(note);
		}
		builder.append("]");
		return builder.toString();
	}

}
