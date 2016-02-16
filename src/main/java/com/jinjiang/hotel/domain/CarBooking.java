/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.util.Date;

/**
 * @author gaojianm
 *
 */
public class CarBooking extends Booking {
	private Integer bookCount;
	private CarRental carRental;
	private Date whenToUse;
	
	/**
	 * @param guest
	 * @param bookCount
	 * @param carRental
	 */
	public CarBooking(Guest guest, Integer bookCount, CarRental carRental) {
		super(guest);
		this.bookCount = bookCount;
		this.carRental = carRental;
		this.whenToUse=new Date();
	}
	/**
	 * 
	 */
	public CarBooking() {
	}
	/**
	 * @return the count
	 */
	public Integer getBookCount() {
		return bookCount;
	}
	/**
	 * @param count the count to set
	 */
	public void setBookCount(Integer count) {
		this.bookCount = count;
	}
	/**
	 * @return the carRental
	 */
	public CarRental getCarRental() {
		return carRental;
	}
	/**
	 * @param carRental the carRental to set
	 */
	public void setCarRental(CarRental carRental) {
		this.carRental = carRental;
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
	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.domain.Booking#equals(java.lang.Object)
	 */
/*	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof CarBooking)) return false;
		CarBooking cb=(CarBooking) obj;
		
		return isSameField(this.id, cb.id) && isSameField(this.guest, cb.guest)
				&& isSameField(this.bookDate, cb.bookDate)
				&& isSameField(this.note, cb.note)
				&& isSameField(this.bookCount, cb.bookCount)
				&& isSameField(this.carRental, cb.carRental)
				&& isSameField(this.whenToUse, cb.whenToUse)
				;
	}*/
	
	private boolean isSameField(Object f1, Object f2) {
		boolean isEqual=false;
		if (f1==null && f2==null) {
			isEqual=true;
		} else if(f1!=null && f2!=null) {
			isEqual=f1.equals(f2);
		}
		return isEqual;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarBooking [");
		if (bookCount != null) {
			builder.append("bookCount=");
			builder.append(bookCount);
			builder.append(", ");
		}
		if (carRental != null) {
			builder.append("carRental=");
			builder.append(carRental);
			builder.append(", ");
		}
		if (whenToUse != null) {
			builder.append("whenToUse=");
			builder.append(whenToUse);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (guest != null) {
			builder.append("guest=");
			builder.append(guest.getName());
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
