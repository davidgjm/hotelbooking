/**
 * 
 */
package com.jinjiang.hotel.domain;

/**
 * @author gaojianm
 *
 */
public class ExpoHotelGuest {

	private Integer id;
	private Expo expo;
	private Hotel hotel;
	private Guest guest;
	private String note;
	/**
	 * 
	 */
	public ExpoHotelGuest() {
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
	 * @return the expo
	 */
	public Expo getExpo() {
		return expo;
	}
	/**
	 * @param expo the expo to set
	 */
	public void setExpo(Expo expo) {
		this.expo = expo;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpoHotelGuest [id=").append(id).append(", expo=")
				.append(expo).append(", hotel=").append(hotel)
				.append(", guest=").append(guest).append(", note=")
				.append(note).append("]");
		return builder.toString();
	}

	
}
