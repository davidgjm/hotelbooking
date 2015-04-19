/**
 * 
 */
package com.jinjiang.hotel.config;

/**
 * @author gaojianm
 *
 */
public interface ZKConstants {

	static final String ATTRS_FRONT_CAR_RENTALS="rentalMatrix";
	static final String ATTRS_FRONT_EXPO="expo";
	static final String ATTRS_FRONT_HOTEL="hotel";
	static final String ATTRS_ADMIN_BOOKING="adminBooking";
	static final String ATTRS_ADMIN_EXPO="adminExpo";
	static final String ATTRS_ADMIN_USER="adminUser";
	static final String ATTRS_REQUEST_URI="requestURI";
	
	static final String PAGE_REGISTER="register.zul";
	static final String PAGE_LOGIN="login.zul";
	static final String PAGE_ADMIN_EXPO="index.zul";
	static final String PAGE_ADMIN_EXPO_DETAILS="expoDetails.zul";
	static final String PAGE_ADMIN_BOOKING_DETAILS="bookingDetails.zul";
	static final String PAGE_ADMIN_BOOKINGS="bookings.zul";
	static final String PAGE_FRONT_EXPO="expo.zul";
	
	static final String REDIRECTS_HOME="/";
	static final String REDIRECTS_ADMIN_HOME="/ht/";
	static final String REDIRECTS_FRONT_EXPO=REDIRECTS_HOME+PAGE_FRONT_EXPO;
	
	static final String REDIRECTS_LOGIN=REDIRECTS_ADMIN_HOME+PAGE_LOGIN;
	static final String REDIRECTS_ADMIN_BOOKING_DETAILS=PAGE_ADMIN_BOOKING_DETAILS;
	static final String REDIRECTS_ADMIN_BOOKING_PRINT_PREVIEW="bookingPrintPreview.zul";
	static final String REDIRECTS_ADMIN_BOOKINGS=PAGE_ADMIN_BOOKINGS;
	
	static final String PARAM_BOOKING_ID="bid";
}
