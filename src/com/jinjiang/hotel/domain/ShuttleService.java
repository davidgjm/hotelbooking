package com.jinjiang.hotel.domain;

import java.io.Serializable;
import java.util.Date;

public class ShuttleService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5783621530438492049L;

	private Integer id;
	private String type;
	private String flightNumber;
	private Date flightDate;
	private Integer guests;
	
	public ShuttleService() {}

}
