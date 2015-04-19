/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gaojianm
 *
 */
public class GuestBooking {
	private Integer id;
	private Guest guest;
	private Boolean confirmStatus;
	private String note;
	private Date bookedOn;
	private List<RoomBooking> roomBookings=new ArrayList<>();
	private List<CarBooking> carBookings=new ArrayList<>();
	private List<TourGuidingBooking> tourBookings=new ArrayList<>();
	
	/**
	 * @param guest
	 * @param roomBookings
	 */
	public GuestBooking(Guest guest, List<RoomBooking> roomBookings) {
		this.guest = guest;
		this.roomBookings = roomBookings;
	}

	/**
	 * @param guest
	 */
	public GuestBooking(Guest guest) {
		super();
		this.guest = guest;
	}

	/**
	 * 
	 */
	public GuestBooking() {
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
	 * @return the confirmStatus
	 */
	public Boolean getConfirmStatus() {
		return confirmStatus;
	}

	/**
	 * @param confirmStatus the confirmStatus to set
	 */
	public void setConfirmStatus(Boolean confirmStatus) {
		this.confirmStatus = confirmStatus;
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
	 * @return the bookedOn
	 */
	public Date getBookedOn() {
		return bookedOn;
	}

	/**
	 * @param bookedOn the bookedOn to set
	 */
	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}

	/**
	 * @return the roomBookings
	 */
	public List<RoomBooking> getRoomBookings() {
		return roomBookings;
	}

	/**
	 * @param roomBookings the roomBookings to set
	 */
	public void setRoomBookings(List<RoomBooking> roomBookings) {
		this.roomBookings = roomBookings;
	}

	/**
	 * @return the carBookings
	 */
	public List<CarBooking> getCarBookings() {
		return carBookings;
	}

	/**
	 * @param carBookings the carBookings to set
	 */
	public void setCarBookings(List<CarBooking> carBookings) {
		this.carBookings = carBookings;
	}

	/**
	 * @return the tourBookings
	 */
	public List<TourGuidingBooking> getTourBookings() {
		return tourBookings;
	}

	/**
	 * @param tourBookings the tourBookings to set
	 */
	public void setTourBookings(List<TourGuidingBooking> tourBookings) {
		this.tourBookings = tourBookings;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof GuestBooking)) return false;
		GuestBooking gb=(GuestBooking) obj;
		if(this.guest==null||gb.guest==null) return false;
		return this.guest.equals(gb.guest);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuestBooking [id=");
		builder.append(id);
		builder.append(", guest=");
		builder.append(guest);
		builder.append(", confirmStatus=");
		builder.append(confirmStatus);
		builder.append(", note=");
		builder.append(note);
		builder.append(", bookedOn=");
		builder.append(bookedOn);
		builder.append(", roomBookings=");
		builder.append(roomBookings);
		builder.append(", carBookings=");
		builder.append(carBookings);
		builder.append(", tourBookings=");
		builder.append(tourBookings);
		builder.append("]");
		return builder.toString();
	}

}
