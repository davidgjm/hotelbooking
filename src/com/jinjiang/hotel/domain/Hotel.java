/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaojianm
 *
 */
public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4571659421573499873L;

	private Integer id;
	private String name;
	private String rating;
	private String address;
	private String description;
	private String imagePath;
	private String priceInfo;
	private String note;
	private List<Room> rooms=new ArrayList<Room>();
	
	/**
	 * @param name
	 * @param address
	 * @param description
	 */
	public Hotel(String name, String address, String description) {
		this.name = name;
		this.address = address;
		this.description = description;
	}

	/**
	 * 
	 */
	public Hotel() {}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

	/**
	 * @return the priceInfo
	 */
	public String getPriceInfo() {
		return priceInfo;
	}

	/**
	 * @param priceInfo the priceInfo to set
	 */
	public void setPriceInfo(String priceInfo) {
		this.priceInfo = priceInfo;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	 * @return the rooms
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hotel [");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (rating != null)
			builder.append("rating=").append(rating).append(", ");
		if (address != null)
			builder.append("address=").append(address).append(", ");
		if (description != null)
			builder.append("description=").append(description).append(", ");
		if (imagePath != null)
			builder.append("imagePath=").append(imagePath).append(", ");
		if (priceInfo != null)
			builder.append("priceInfo=").append(priceInfo).append(", ");
		if (note != null)
			builder.append("note=").append(note);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof Hotel)) return false;
		Hotel h=(Hotel) obj;
		if(this.id==null||h.id==null) return false;
		if(this.name==null||h.name==null) return false;
		return this.id.equals(h.getId()) && this.name.equals(h.getName());
	}

	

}
