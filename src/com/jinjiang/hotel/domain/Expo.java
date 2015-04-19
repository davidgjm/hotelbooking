/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaojianm
 *
 */
public class Expo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4430063630074695087L;

	private Integer id;
	private String name;
	private String venue;
	private String introduction;
	private Date beginDate;
	private Date endDate;
	private String imagePath;
	private String memo;
	private Boolean isEnglish=false;
	/**
	 * @param name
	 * @param introduction
	 */
	public Expo(String name, String introduction) {
		this.name = name;
		this.introduction = introduction;
	}

	/**
	 * @param name
	 */
	public Expo(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public Expo() {}

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
	 * @return the venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * @param venue the venue to set
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Boolean getIsEnglish() {
		return isEnglish;
	}

	public void setIsEnglish(Boolean isEnglish) {
		this.isEnglish = isEnglish;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		if(!(obj instanceof Expo)) return false;
		Expo e=(Expo)obj;
		if(this.id==null || e.getId()==null) return false;
		return this.id.equals(e.getId())
				&& this.name.equals(e.getName());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expo [");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (name != null)
			builder.append("name=").append(name).append(", ");
		if (venue != null)
			builder.append("venue=").append(venue).append(", ");
		if (introduction != null)
			builder.append("introduction=").append(introduction).append(", ");
		if (beginDate != null)
			builder.append("beginDate=").append(beginDate).append(", ");
		if (endDate != null)
			builder.append("endDate=").append(endDate).append(", ");
		if (imagePath != null)
			builder.append("imagePath=").append(imagePath).append(", ");
		if (memo != null)
			builder.append("memo=").append(memo).append(", ");
		if (isEnglish != null)
			builder.append("isEnglish=").append(isEnglish);
		builder.append("]");
		return builder.toString();
	}

	
}
