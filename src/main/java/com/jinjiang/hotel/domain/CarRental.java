/**
 * 
 */
package com.jinjiang.hotel.domain;

/**
 * @author gaojianm
 *
 */
public class CarRental {

	private Integer id;
	private String car;
	private String usage;
	private String price;
	private String note;
	
	/**
	 * @param car
	 * @param usage
	 * @param price
	 */
	public CarRental(String car, String usage, String price) {
		this.car = car;
		this.usage = usage;
		this.price = price;
	}

	/**
	 * 
	 */
	public CarRental() {
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
	 * @return the car
	 */
	public String getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(String car) {
		this.car = car;
	}

	/**
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * @param usage the usage to set
	 */
	public void setUsage(String usage) {
		this.usage = usage;
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof CarRental)) return false;
		CarRental cr=(CarRental) obj;
		if(this.id==null||cr.id==null) return false;
		if(cr.car==null||cr.usage==null||cr.price==null) return false;
		return this.id.equals(cr.getId()) && this.car.trim().equals(cr.getCar().trim()) 
				&& this.usage.trim().equals(cr.getUsage().trim()) && this.price.trim().equals(cr.getPrice().trim());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarRental [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (car != null) {
			builder.append("car=");
			builder.append(car);
			builder.append(", ");
		}
		if (usage != null) {
			builder.append("usage=");
			builder.append(usage);
			builder.append(", ");
		}
		if (price != null) {
			builder.append("price=");
			builder.append(price);
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
