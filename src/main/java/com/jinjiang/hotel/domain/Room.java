/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.io.Serializable;

/**
 * @author gaojianm
 *
 */
public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8297811685444727890L;

	private Integer id;
	private String type;
	private Hotel hotel;
	private Boolean isFull=false;
	private String price;
	private String note;
	

	/**
	 * @param type
	 * @param hotel
	 * @param price
	 */
	public Room(String type, Hotel hotel, String price) {
		this.type = type;
		this.hotel = hotel;
		this.price = price;
	}

	/**
	 * 
	 */
	public Room() {
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
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isFull
	 */
	public Boolean getIsFull() {
		return isFull;
	}

	/**
	 * @param isFull the isFull to set
	 */
	public void setIsFull(Boolean isFull) {
		this.isFull = isFull;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room [id=").append(id).append(", type=").append(type)
				.append(", hotel=").append(hotel.getName()).append(", isFull=")
				.append(isFull).append(", price=").append(price)
				.append(", note=").append(note).append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof Room)) return false;
		Room room=(Room) obj;
		if(this.id==null||room.id==null||this.isFull==null||room.isFull==null) return false;
		if(room.hotel==null||room.type==null||room.type.trim().isEmpty()
				||room.price==null||room.price.trim().isEmpty()
				) return false;
		return this.id.equals(room.getId()) && this.hotel.equals(room.hotel) 
				&& this.type.equals(room.type)
				&& this.isFull.equals(room.isFull)
				&& this.price.trim().equals(room.getPrice().trim());
	}

	
}
