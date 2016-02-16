/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.util.Date;

/**
 * @author gaojianm
 *
 */
public class RoomBooking extends Booking {
	private Room room;
	private Integer roomCount;
	private PaymentMode mode;
	private Date checkinDate;
	private Date checkoutDate;
	/**
	 * @param guest
	 * @param room
	 * @param roomCount
	 */
	public RoomBooking(Guest guest, Room room, Integer roomCount) {
		this.guest = guest;
		this.room = room;
		this.roomCount = roomCount;
	}
	/**
	 * 
	 */
	public RoomBooking() {
	}
	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
	/**
	 * @return the roomCount
	 */
	public Integer getRoomCount() {
		return roomCount;
	}
	/**
	 * @param roomCount the roomCount to set
	 */
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}
	
	
	/**
	 * @return the mode
	 */
	public PaymentMode getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	/**
	 * @return the checkinDate
	 */
	public Date getCheckinDate() {
		return checkinDate;
	}
	/**
	 * @param checkinDate the checkinDate to set
	 */
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	/**
	 * @return the checkoutDate
	 */
	public Date getCheckoutDate() {
		return checkoutDate;
	}
	/**
	 * @param checkoutDate the checkoutDate to set
	 */
	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
/*	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof RoomBooking)) return false;
		RoomBooking rb=(RoomBooking) obj;
		if(this.id==null||rb.id==null) return false;
		if(rb.room==null||rb.guest==null) return false;
		return this.id.equals(rb.getId()) && this.guest.equals(rb.guest)
				&& this.mode.equals(rb.mode)
				&& this.room.equals(rb.room);
	}*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomBooking [");
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
		if (room != null) {
			builder.append("room=");
			builder.append(room);
			builder.append(", ");
		}
		if (roomCount != null) {
			builder.append("roomCount=");
			builder.append(roomCount);
			builder.append(", ");
		}
		if (mode != null) {
			builder.append("mode=");
			builder.append(mode);
			builder.append(", ");
		}
		if (checkinDate != null) {
			builder.append("checkinDate=");
			builder.append(checkinDate);
			builder.append(", ");
		}
		if (checkoutDate != null) {
			builder.append("checkoutDate=");
			builder.append(checkoutDate);
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
