/**
 * 
 */
package com.jinjiang.hotel.notification;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.config.AppConfig;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.service.GuestService;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.RoomBookingService;
import com.jinjiang.hotel.service.impl.GuestServiceImpl;
import com.jinjiang.hotel.service.impl.HotelServiceImpl;
import com.jinjiang.hotel.service.impl.RoomBookingServiceImpl;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author gaojianm
 *
 */
public class CommonsMailTest {
	private static AppConfig config=AppConfig.getInstance();
	private HtmlEmail mail;
	private GuestService gs;
	private HotelService hs;
	private RoomBookingService rbs;
	@Before
	public void setup() throws EmailException {
		gs=GuestServiceImpl.getInstance();
		hs=HotelServiceImpl.getInstance();
		rbs=RoomBookingServiceImpl.getInstance();
		mail=new HtmlEmail();
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
	}
	
	@Test
	public void sendMail() throws EmailException {
		mail.setSubject("Test mail subject");
		mail.setMsg("hello this is a test message");
		mail.addBcc(config.getString("mail.users.admin"));
		mail.addTo("jian-min.gao@hp.com");
		mail.send();
	}
	
	@Test
	public void testSendNewOrderNotification() throws IOException, TemplateException, EmailException {
		Configuration configuration = new Configuration(); 
		 configuration.setDirectoryForTemplateLoading(new File("D:\\eclipse\\workspace\\hotelbooking\\WebContent\\WEB-INF\\templates"));
		 configuration.setObjectWrapper(new DefaultObjectWrapper()); 
		 configuration.setDefaultEncoding("UTF-8");
		 Template template = configuration.getTemplate("NewBooking.html");
		 
		 StringWriter sw=new StringWriter();
		 template.process(getParametersMap(), sw);
		 sw.toString();
		 
			mail.setSubject("预订成功");
			mail.setHtmlMsg(sw.toString());
			mail.addBcc(config.getString("mail.users.admin"));
			mail.addTo("jian-min.gao@hp.com");
			mail.send();
	}
	
	private Map<String, Object> getParametersMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		Guest g=gs.getById(16);
		paramMap.put("guest", g);
		paramMap.put("hotel", hs.getById(1));
		paramMap.put("roomBookings", rbs.getBookings(g));
		return paramMap;
	}
}
