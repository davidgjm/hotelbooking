/**
 * 
 */
package com.jinjiang.hotel.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gaojianm
 *
 */
public class Guest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4634087839519776372L;
	private Integer id;
	private String name;
	private String name1;
	private String name2;
	private String name3;
	private String name4;
	private String address;
	private String postcode;
	private String company;
	private String phone;
	private String cellPhone;
	private String email;
	private String fax;
	private Date registerDate;
	private Boolean isConfirmed;
	private Boolean isBuyer;
	private Boolean isExhibitor;;
	private Boolean isVIP;
	private Expo expo;
	private String note;
	/**
	 * @param visitorType
	 * @param name
	 * @param phone
	 * @param email
	 * @param fax
	 */
	public Guest(String name, String phone,
			String email, String fax) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.fax = fax;
	}

	/**
	 * 
	 */
	public Guest() {
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

	/**
	 * @return the name1
	 */
	public String getName1() {
		return name1;
	}

	/**
	 * @param name1 the name1 to set
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * @return the name2
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * @param name2 the name2 to set
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * @return the name3
	 */
	public String getName3() {
		return name3;
	}

	/**
	 * @param name3 the name3 to set
	 */
	public void setName3(String name3) {
		this.name3 = name3;
	}

	/**
	 * @return the name4
	 */
	public String getName4() {
		return name4;
	}

	/**
	 * @param name4 the name4 to set
	 */
	public void setName4(String name4) {
		this.name4 = name4;
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
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the companyName
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the companyName to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the cellPhone
	 */
	public String getCellPhone() {
		return cellPhone;
	}

	/**
	 * @param cellPhone the cellPhone to set
	 */
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	

	/**
	 * @return the isConfirmed
	 */
	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	/**
	 * @param isConfirmed the isConfirmed to set
	 */
	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
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
		if (!(obj instanceof Guest)) return false;
		Guest gt=(Guest) obj;
		if(this.id==null||gt.id==null||this.name==null||gt.name==null) return false;
		return this.id.equals(gt.getId()) && this.name.equals(gt.getName());
	}

	/**
	 * @return the isBuyer
	 */
	public Boolean getIsBuyer() {
		return isBuyer;
	}

	/**
	 * @param isBuyer the isBuyer to set
	 */
	public void setIsBuyer(Boolean isBuyer) {
		this.isBuyer = isBuyer;
	}

	/**
	 * @return the isExhibitor
	 */
	public Boolean getIsExhibitor() {
		return isExhibitor;
	}

	/**
	 * @param isExhibitor the isExhibitor to set
	 */
	public void setIsExhibitor(Boolean isExhibitor) {
		this.isExhibitor = isExhibitor;
	}

	/**
	 * @return the isVIP
	 */
	public Boolean getIsVIP() {
		return isVIP;
	}

	/**
	 * @param isVIP the isVIP to set
	 */
	public void setIsVIP(Boolean isVIP) {
		this.isVIP = isVIP;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Guest [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (name1 != null) {
			builder.append("name1=");
			builder.append(name1);
			builder.append(", ");
		}
		if (name2 != null) {
			builder.append("name2=");
			builder.append(name2);
			builder.append(", ");
		}
		if (name3 != null) {
			builder.append("name3=");
			builder.append(name3);
			builder.append(", ");
		}
		if (name4 != null) {
			builder.append("name4=");
			builder.append(name4);
			builder.append(", ");
		}
		if (address != null) {
			builder.append("address=");
			builder.append(address);
			builder.append(", ");
		}
		if (postcode != null) {
			builder.append("postcode=");
			builder.append(postcode);
			builder.append(", ");
		}
		if (company != null) {
			builder.append("company=");
			builder.append(company);
			builder.append(", ");
		}
		if (phone != null) {
			builder.append("phone=");
			builder.append(phone);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		if (fax != null) {
			builder.append("fax=");
			builder.append(fax);
			builder.append(", ");
		}
		if (registerDate != null) {
			builder.append("registerDate=");
			builder.append(registerDate);
			builder.append(", ");
		}
		if (isConfirmed != null) {
			builder.append("isConfirmed=");
			builder.append(isConfirmed);
			builder.append(", ");
		}
		if (isBuyer != null) {
			builder.append("isBuyer=");
			builder.append(isBuyer);
			builder.append(", ");
		}
		if (isExhibitor != null) {
			builder.append("isExhibitor=");
			builder.append(isExhibitor);
			builder.append(", ");
		}
		if (isVIP != null) {
			builder.append("isVIP=");
			builder.append(isVIP);
			builder.append(", ");
		}
		if (expo != null) {
			builder.append("expo=");
			builder.append(expo.getName());
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
