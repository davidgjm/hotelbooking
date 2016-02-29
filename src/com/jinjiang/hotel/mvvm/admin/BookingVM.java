/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.service.BookingService;
import com.jinjiang.hotel.service.MailService;
import com.jinjiang.hotel.service.MailService.NotificationType;
import com.jinjiang.hotel.service.impl.BookingServiceImpl;
import com.jinjiang.hotel.service.impl.MailServiceImpl;

import freemarker.template.TemplateException;

/**
 * @author gaojianm
 *
 */
public class BookingVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private ListModelList<GuestBooking> bookings;
	private GuestBooking booking;
	private ListModelList<Expo> expos;
	private Expo expo;
	private MailService mailer=new MailServiceImpl();
	private boolean loadUnConfirmedOnly;
	
	public BookingService getService() {
		return BookingServiceImpl.getInstance();
	}
	
	
	/**
	 * @return the expos
	 */
	public ListModelList<Expo> getExpos() {
		if(expos==null) expos=new ListModelList<>(getService().getCurrentExpos());
		return expos;
	}



	/**
	 * @param expos the expos to set
	 */
	public void setExpos(ListModelList<Expo> expos) {
		this.expos = expos;
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
	 * @return the bookings
	 */
	public ListModelList<GuestBooking> getBookings() {
		if(bookings==null) bookings=new ListModelList<GuestBooking>();
		return bookings;
	}
	/**
	 * @param bookings the bookings to set
	 */
	public void setBookings(ListModelList<GuestBooking> bookings) {
		this.bookings = bookings;
	}
	/**
	 * @return the booking
	 */
	public GuestBooking getBooking() {
		return booking;
	}
	/**
	 * @param booking the booking to set
	 */
	public void setBooking(GuestBooking booking) {
		this.booking = booking;
	}
	
	/**
	 * @return the loadUnConfirmedOnly
	 */
	public boolean isLoadUnConfirmedOnly() {
		return loadUnConfirmedOnly;
	}


	/**
	 * @param loadUnConfirmedOnly the loadUnConfirmedOnly to set
	 */
	public void setLoadUnConfirmedOnly(boolean loadUnConfirmedOnly) {
		this.loadUnConfirmedOnly = loadUnConfirmedOnly;
	}
	
	@Command
	public void openBooking(@BindingParam("bid") Integer bid) {
		GuestBooking selectedBooking=getService().getBooking(bid);
		Sessions.getCurrent().setAttribute(ZKConstants.ATTRS_ADMIN_BOOKING, selectedBooking);
		Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_BOOKING_DETAILS);
	}
	@Command
	public void openBookingPrintPreview(@BindingParam("bid") Integer bid) {
		GuestBooking selectedBooking=getService().getBooking(bid);
		Sessions.getCurrent().setAttribute(ZKConstants.ATTRS_ADMIN_BOOKING, selectedBooking);
		Executions.getCurrent().sendRedirect(ZKConstants.REDIRECTS_ADMIN_BOOKING_PRINT_PREVIEW, "_blank");
	}

	@NotifyChange("bookings")
	@Command
	public void doLoadBookings() {
		if (log.isDebugEnabled()) {
			log.debug("Loading all bookings for expo [{}]",expo);
			log.debug("Is to load unconfirmed bookins only? [{}]",loadUnConfirmedOnly);
		}
		
		if (loadUnConfirmedOnly) {
			bookings=new ListModelList<GuestBooking>(getService().getBookingsUnconfirmed(expo));
		} else {
			bookings=new ListModelList<GuestBooking>(getService().getBookings(expo));
		}
	}
	
	@NotifyChange("bookings")
	@Command
	public void doConfirmBookings() {
		Messagebox.show("确定要确认这些客人的预订信息吗？按确定继续。", 
	    	    "确认预订", Messagebox.OK | Messagebox.CANCEL,
	    	    Messagebox.QUESTION,new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
    	                	if (log.isDebugEnabled()) {
								log.debug("Preparing to confirm guest bookings!");
							}
    	                	
						}
					}
				});
	}
	
	@NotifyChange("booking")
	@Command
	public void doConfirmBooking() {
		booking.setConfirmStatus(true);
		getService().update(booking,false);
		sendConfirmationEmail();
	}
	
	@NotifyChange("bookings")
	@Command
	public void doSubmitChanges() {
		for (GuestBooking gb:bookings) {
			getService().update(gb,true);
		}
	}
	
	@NotifyChange("bookings")
	@Command
	public void doDeleteBookings() {
		Messagebox.show("确定要确认这些客人的预订信息吗？按确定继续。", "确认预订",
	    	      Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
	    	      new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
							if (log.isInfoEnabled()) {
								log.info("Deleting guest bookings..." );
							}
	   	                	Set<GuestBooking> selectedBookings=bookings.getSelection();
    	                	for (GuestBooking gb : selectedBookings) {
    							if (log.isInfoEnabled()) {
    								log.info("Deleting guest bookings..." );
    							}
    	                		getService().delete(gb);
    	                	}
						}
					}
				}
	    	    );
	}
	
	public void sendConfirmationEmail() {
		if(booking.getConfirmStatus()) return;
		try {
			mailer.sendMailNotification(NotificationType.BookingConfirmation, booking);
		} catch (IOException | TemplateException | EmailException e) {
			log.error("Error occurred when sending mail notification", e);
			e.printStackTrace();
		}
	}
}
