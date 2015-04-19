/**
 * 
 */
package com.jinjiang.hotel.domain;

/**
 * @author gaojianm
 *
 */
public class TourGuiding {
	private Integer id;
	private String language;
	private String route;
	private String price;
	
	/**
	 * @param language
	 * @param route
	 * @param price
	 */
	public TourGuiding(String language, String route, String price) {
		this.language = language;
		this.route = route;
		this.price = price;
	}
	/**
	 * 
	 */
	public TourGuiding() {
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
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		this.route = route;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TourGuiding [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (language != null) {
			builder.append("language=");
			builder.append(language);
			builder.append(", ");
		}
		if (route != null) {
			builder.append("route=");
			builder.append(route);
			builder.append(", ");
		}
		if (price != null) {
			builder.append("price=");
			builder.append(price);
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
		if (!(obj instanceof TourGuiding)) return false;
		TourGuiding tg=(TourGuiding) obj;
		if(this.id==null||tg.id==null) return false;
		if(tg.language==null||tg.route==null||tg.price==null) return false;
		return this.id.equals(tg.getId()) && this.language.equals(tg.language) 
				&& this.price.trim().equals(tg.getPrice().trim());
	}

}
