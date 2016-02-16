/**
 * 
 */
package com.jinjiang.hotel.service;

import java.io.IOException;

import org.apache.commons.mail.EmailException;

import com.jinjiang.hotel.domain.GuestBooking;

import freemarker.template.TemplateException;

/**
 * @author gaojianm
 *
 */
public interface MailService {
	enum NotificationType {
		BookingCreation,
		BookingModification,
		BookingConfirmation
	}
	
	void sendMailNotification(NotificationType type, GuestBooking booking)throws TemplateException, IOException, EmailException;
}
