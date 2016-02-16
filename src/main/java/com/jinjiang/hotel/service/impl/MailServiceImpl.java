/**
 * 
 */
package com.jinjiang.hotel.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Sessions;

import com.jinjiang.hotel.config.AppConfig;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.service.MailService;
import com.jinjiang.hotel.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author gaojianm
 *
 */
public class MailServiceImpl implements MailService {
	private static AppConfig config=AppConfig.getInstance();
	private Logger log=LoggerFactory.getLogger(getClass());
	private Configuration ftlConfig; 
	private UserService us;
	/**
	 * 
	 */
	public MailServiceImpl() {
		us=UserServiceImpl.getInstance();
		/*
		 * setting up Freemarker
		 */
		ftlConfig = new Configuration();
		ftlConfig.setDefaultEncoding("UTF-8");
		HttpSession sess=(HttpSession) Sessions.getCurrent().getNativeSession();
		ftlConfig.setServletContextForTemplateLoading(sess.getServletContext(), 
				config.getString("ftl.config.templatedir"));
	}

	public HtmlEmail getMailer() throws EmailException {
		HtmlEmail mail=new HtmlEmail();
		mail.setHostName(config.getString("mail.smtp.host"));
		mail.setSmtpPort(config.getInt("mail.smtp.port"));
		mail.setSSLOnConnect(config.getBoolean("mail.smtp.ssl"));
		mail.setCharset(config.getString("mail.config.charset"));
		boolean needsAuth=config.getBoolean("mail.smtp.authenticate");
		if (needsAuth) {
			mail.setAuthentication(config.getString("mail.smtp.user"), config.getString("mail.smtp.password"));
		}
		mail.setFrom(config.getString("mail.config.from.email"),
				config.getString("mail.config.from.name"),
				config.getString("mail.config.charset"));
		mail.setDebug(config.getBoolean("mail.config.debug"));
		if (config.getBoolean("mail.config.bccToAdmin")) {
			String adminEmail=us.findUser("admin").getEmail();
			if (log.isDebugEnabled()) {
				log.debug("The email address for [admin] is: {}", adminEmail);
			}
			mail.addBcc(adminEmail);
			mail.addCc(adminEmail);
		}
		return mail;
	}
	
	private String processTemplate(Template template, Map<String, Object> paraMap) throws TemplateException, IOException {
		 StringWriter sw=new StringWriter();
		 template.process(paraMap, sw);
		 return sw.toString();
	}
	
	private Map<String, Object> getParametersMap(GuestBooking booking) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		Guest guest=booking.getGuest();
		Hotel hotel=null;
		if(booking.getRoomBookings()!=null && !booking.getRoomBookings().isEmpty()) 
			hotel=booking.getRoomBookings().get(0).getRoom().getHotel();
		paramMap.put("booking", booking);
		paramMap.put("guest", guest);
		paramMap.put("hotel", hotel);
		paramMap.put("roomBookings", booking.getRoomBookings());
		paramMap.put("carBookings", booking.getCarBookings());
		paramMap.put("tourBookings", booking.getTourBookings());
		return paramMap;
	}
	
	

	/* (non-Javadoc)
	 * @see com.jinjiang.hotel.service.MailService#sendMailNotification(com.jinjiang.hotel.service.MailService.NotificationType, com.jinjiang.hotel.domain.GuestBooking)
	 */
	@Override
	public void sendMailNotification(NotificationType type, GuestBooking booking)
			throws TemplateException, IOException, EmailException {
		if(type==null||booking==null||booking.getGuest()==null||booking.getGuest().getEmail()==null) {
			log.error("Notification Type is invalid or Guest Booking is invalid.");
			return;
		}

		String subject=null;
		String templateName=null;
		HtmlEmail mail=getMailer();
		if (type.equals(NotificationType.BookingCreation)) {//new booking
			subject=config.getString("ftl.templates.newBooking.subject");
			templateName=config.getString("ftl.templates.newBooking.file");
			if (!config.getBoolean("mail.config.newBookingToAdminOnly")) {
				mail.addTo(booking.getGuest().getEmail());
			}
		}else if (type.equals(NotificationType.BookingModification)) {//updating
			subject=config.getString("ftl.templates.editBooking.subject");
			templateName=config.getString("ftl.templates.editBooking.file");
			mail.addTo(booking.getGuest().getEmail());
		}else if (type.equals(NotificationType.BookingConfirmation)) {//confirmation
			subject=config.getString("ftl.templates.confirmedBooking.subject");
			templateName=config.getString("ftl.templates.confirmedBooking.file");
			mail.addTo(booking.getGuest().getEmail());
		}else {
			log.error("Failed to send mail notification because the subject and template name were not initialized due to errors.");
			return;
		}
		
		
		if(subject!=null&&templateName!=null) {
			sendMail(mail,subject, templateName, booking);
		}
	}

	private void sendMail(HtmlEmail mail,String subject, String templateName, GuestBooking booking) throws TemplateException, IOException, EmailException {
		Map<String, Object> paraMap=getParametersMap(booking);
		Template template=ftlConfig.getTemplate(templateName);
		String content=processTemplate(template, paraMap);
		mail.setSubject(subject);
		mail.setHtmlMsg(content);
		mail.send();

	}
}
