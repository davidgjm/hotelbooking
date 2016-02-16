/**
 * 
 */
package com.jinjiang.hotel.domain;

/**
 * @author gaojianm
 *
 */
public class PaymentMode {
	private Integer id;
	private String name;
	
	/**
	 * @param name
	 */
	public PaymentMode(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public PaymentMode() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentMode [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
		}
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if (!(obj instanceof PaymentMode)) return false;
		PaymentMode pm=(PaymentMode) obj;
		if(this.id==null||pm.id==null) return false;
		if(this.name==null||pm.name==null) return false;
		return this.id.equals(pm.getId()) && this.name.equals(pm.name);
	}

}
