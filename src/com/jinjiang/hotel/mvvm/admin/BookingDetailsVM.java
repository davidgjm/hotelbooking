/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModelSet;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.domain.TourGuidingBooking;
import com.jinjiang.hotel.mvvm.RegistrationVM;
import com.jinjiang.hotel.service.BookingService;
import com.jinjiang.hotel.service.MailService;
import com.jinjiang.hotel.service.MailService.NotificationType;
import com.jinjiang.hotel.service.impl.BookingServiceImpl;
import com.jinjiang.hotel.service.impl.MailServiceImpl;
import com.jinjiang.hotel.validators.EmailValidator;

import freemarker.template.TemplateException;

/**
 * @author gaojianm
 *
 */
public class BookingDetailsVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private MailService mailer=new MailServiceImpl();
	private Validator emailValidator=new EmailValidator();
	private String message;
	private GuestBooking booking;
	private Hotel hotel;
	private Room room;
	private RoomBooking roomBooking=new RoomBooking();
	private ListModelList<PaymentMode> modes;
	
	private ListModelList<String> carUsages;
	private CarBooking carBooking=new CarBooking();
	private String carUsage;
	private ListModelList<CarRental> carRentals;

	private TourGuidingBooking tourBooking=new TourGuidingBooking();
	private ListModelList<TourGuiding> guidings;
	private ListModelList<String> tourRoutes;
	private String tourRoute;
	
	private List<RoomBooking> removedRoomBookings=new ArrayList<>();
	private List<CarBooking> removedCarBookings=new ArrayList<>();
	private List<TourGuidingBooking> removedTourBookings=new ArrayList<>();
	
	@Init
	public void doInit() {
		booking=(GuestBooking) Sessions.getCurrent().getAttribute(ZKConstants.ATTRS_ADMIN_BOOKING);
		if(booking==null) {
			if (log.isWarnEnabled()) {
				log.warn("The select booking is not found in session. Redirecting to bookings page.");
			}
			Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_BOOKINGS);
			return;
		}
	}
	
	public BookingService getService() {
		return BookingServiceImpl.getInstance();
	}


	/**
	 * @return the emailValidator
	 */
	public Validator getEmailValidator() {
		return emailValidator;
	}

	/**
	 * @param emailValidator the emailValidator to set
	 */
	public void setEmailValidator(Validator emailValidator) {
		this.emailValidator = emailValidator;
	}
	


	/**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		if(hotel==null && booking.getRoomBookings()!=null && !booking.getRoomBookings().isEmpty()) {
			hotel=booking.getRoomBookings().get(0).getRoom().getHotel();
		}
		
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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
	 * @return the roomBooking
	 */
	public RoomBooking getRoomBooking() {
		return roomBooking;
	}

	/**
	 * @param roomBooking the roomBooking to set
	 */
	public void setRoomBooking(RoomBooking roomBooking) {
		this.roomBooking = roomBooking;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the modes
	 */
	public ListModelList<PaymentMode> getModes() {
		if(modes==null) modes=new ListModelList<PaymentMode>(getService().getAllPaymentModes());
		return modes;
	}

	/**
	 * @param modes the modes to set
	 */
	public void setModes(ListModelList<PaymentMode> modes) {
		this.modes = modes;
	}

	/**
	 * @return the carUsages
	 */
	public ListModelList<String> getCarUsages() {
		if(carUsages==null) carUsages=new ListModelList<String>(getService().getCarUsages());
		return carUsages;
	}

	/**
	 * @param carUsages the carUsages to set
	 */
	public void setCarUsages(ListModelList<String> carUsages) {
		this.carUsages = carUsages;
	}

	/**
	 * @return the carBooking
	 */
	public CarBooking getCarBooking() {
		return carBooking;
	}

	/**
	 * @param carBooking the carBooking to set
	 */
	public void setCarBooking(CarBooking carBooking) {
		this.carBooking = carBooking;
	}

	/**
	 * @return the carUsage
	 */
	public String getCarUsage() {
		return carUsage;
	}

	/**
	 * @param carUsage the carUsage to set
	 */
	public void setCarUsage(String carUsage) {
		this.carUsage = carUsage;
	}

	/**
	 * @return the carRentals
	 */
	public ListModelList<CarRental> getCarRentals() {
		return carRentals;
	}

	/**
	 * @param carRentals the carRentals to set
	 */
	public void setCarRentals(ListModelList<CarRental> carRentals) {
		this.carRentals = carRentals;
	}

	/**
	 * @return the tourBooking
	 */
	public TourGuidingBooking getTourBooking() {
		return tourBooking;
	}

	/**
	 * @param tourBooking the tourBooking to set
	 */
	public void setTourBooking(TourGuidingBooking tourBooking) {
		this.tourBooking = tourBooking;
	}

	/**
	 * @return the guidings
	 */
	public ListModelList<TourGuiding> getGuidings() {
		return guidings;
	}

	/**
	 * @param guidings the guidings to set
	 */
	public void setGuidings(ListModelList<TourGuiding> guidings) {
		this.guidings = guidings;
	}

	/**
	 * @return the tourRoutes
	 */
	public ListModelList<String> getTourRoutes() {
		if(tourRoutes==null) tourRoutes=new ListModelList<>(getService().getTourRoutes());
		return tourRoutes;
	}

	/**
	 * @param tourRoutes the tourRoutes to set
	 */
	public void setTourRoutes(ListModelList<String> tourRoutes) {
		this.tourRoutes = tourRoutes;
	}

	/**
	 * @return the tourRoute
	 */
	public String getTourRoute() {
		return tourRoute;
	}

	/**
	 * @param tourRoute the tourRoute to set
	 */
	public void setTourRoute(String tourRoute) {
		this.tourRoute = tourRoute;
	}
	
	
	@NotifyChange("carRentals")
	@Command
	public void loadCarRentls() {
		if (log.isDebugEnabled()) {
			log.debug("Loading car rentals for {}", carUsage);
		}
		
		carRentals=new ListModelList<CarRental>(getService().getRentalsByUsage(carUsage));
	}
	
	@NotifyChange("guidings")
	@Command
	public void loadTourGuidings() {
		if (log.isDebugEnabled()) {
			log.debug("Loading tour guidings for [{}]", tourRoute);
		}
		guidings=new ListModelList<TourGuiding>(getService().getTourGuidings(tourRoute));
	}
	
	@NotifyChange("booking")
	@Command
	public void doAddRoomBooking() {
		if (roomBooking.getRoom()==null||roomBooking.getRoomCount()==null||roomBooking.getRoomCount()<1) {
			if (log.isInfoEnabled()) {
				log.info("Room booking is invalid. return now.");
			}
			return;
		}
		RoomBooking booking=new RoomBooking();
		booking.setGuest(this.booking.getGuest());
		booking.setRoomCount(roomBooking.getRoomCount());
		booking.setRoom(roomBooking.getRoom());
		booking.setCheckinDate(roomBooking.getCheckinDate());
		booking.setCheckoutDate(roomBooking.getCheckoutDate());
		booking.setMode(roomBooking.getMode());
		this.booking.getRoomBookings().add(booking);
		getService().saveOrUpdate(booking);
	}
	
	@NotifyChange({"booking"})
	@Command
	public void doAddCarBooking() {
		if (log.isDebugEnabled()) {
			log.debug("Preparing to add the selected car rental");
		}
		if (carBooking.getCarRental()==null||carBooking.getBookCount()==null||carBooking.getBookCount()<1) {
			if (log.isInfoEnabled()) {
				log.info("carRental is invalid. return now.");
			}
			return;
		}
		CarBooking cb=new CarBooking();
		cb.setGuest(this.booking.getGuest());
		cb.setBookCount(carBooking.getBookCount());
		cb.setWhenToUse(carBooking.getWhenToUse());
		cb.setCarRental(carBooking.getCarRental());
		this.booking.getCarBookings().add(cb);
		getService().saveOrUpdate(cb);
	}
	
	@NotifyChange("booking")
	@Command
	public void doAddTourBooking() {
		if(tourBooking.getGuiding()==null||tourBooking.getBookCount()==null||tourBooking.getBookCount()<1) return;
		TourGuidingBooking booking=new TourGuidingBooking();
		booking.setGuest(this.booking.getGuest());
		booking.setBookCount(tourBooking.getBookCount());
		booking.setGuiding(tourBooking.getGuiding());
		booking.setWhenToUse(tourBooking.getWhenToUse());
		if (log.isDebugEnabled()) {
			log.debug("Tour Guiding booking to add: {}",booking);
		}
		if(!this.booking.getTourBookings().contains(booking)) {
			this.booking.getTourBookings().add(booking);
			getService().saveOrUpdate(booking);
		}
	}
	
	@NotifyChange("booking")
	@Command
	public void doUpdateRoomBooking(@BindingParam("booking") RoomBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Updating room booking {}", booking);
		}
		getService().saveOrUpdate(booking);
	}
	
	@NotifyChange("booking")
	@Command
	public void doUpdateCarBooking(@BindingParam("booking") CarBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Updating car booking {}", booking);
		}
		getService().saveOrUpdate(booking);
	}
	
	@NotifyChange("booking")
	@Command
	public void doUpdateTourBooking(@BindingParam("booking") TourGuidingBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Updating tour booking {}", booking);
		}
		getService().saveOrUpdate(booking);
	}	
	
	@NotifyChange("booking")
	@Command
	public void doRemoveRoomBooking(@BindingParam("booking") RoomBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Removing room booking {}", booking);
		}
		this.booking.getRoomBookings().remove(booking);
//		getService().delete(booking);
		removedRoomBookings.add(booking);//added for future delete
	}
	
	@NotifyChange("booking")
	@Command
	public void doRemoveCarBooking(@BindingParam("booking") CarBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Removing car booking {}", booking);
		}
		this.booking.getCarBookings().remove(booking);
//		getService().delete(booking);
		removedCarBookings.add(booking);
	}
	
	@NotifyChange("booking")
	@Command
	public void doRemoveTourBooking(@BindingParam("booking") TourGuidingBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Removing tour booking {}", booking);
		}
		this.booking.getTourBookings().remove(booking);
//		getService().delete(booking);
		removedTourBookings.add(booking);
	}	

	@NotifyChange("booking")
	@Command
	public void doConfirm() {
		Messagebox.show("确定要确认客人的预订信息吗？按确定继续。", 
	    	    "确认预订", Messagebox.OK | Messagebox.CANCEL,
	    	    Messagebox.QUESTION,new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
    	                	if (log.isDebugEnabled()) {
								log.debug("Preparing to confirm guest bookings!");
							}
    	                	
    	                	booking.setConfirmStatus(true);
    	                	getService().update(booking,false);
    	                	sendMail(NotificationType.BookingConfirmation);
    	                	
    	                	Sessions.getCurrent().removeAttribute(ZKConstants.ATTRS_ADMIN_BOOKING);
    	            		Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_BOOKINGS);
						}
					}
				});
	}
	
	@NotifyChange("booking")
	@Command
	public void doUpdate() {
		if (log.isInfoEnabled()) {
			log.info("{} - Saving/Updating guest booking...", Thread.currentThread().getStackTrace()[0].getMethodName());
		}
		getService().saveOrUpdate(booking.getGuest());
		getService().update(booking,false);
		deleteRemovedBookings();
		
		if (log.isInfoEnabled()) {
			log.info("Sending mail to admin/guest for editing");
		}
		sendMail(NotificationType.BookingModification);
    	Sessions.getCurrent().removeAttribute(ZKConstants.ATTRS_ADMIN_BOOKING);
		Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_BOOKINGS);
	}
	
	@NotifyChange("booking")
	@Command
	public void revertRemovedBookings() {
		if (log.isDebugEnabled()) {
			log.debug("Reverting removed bookings");
		}
		booking.getRoomBookings().addAll(removedRoomBookings);
		booking.getCarBookings().addAll(removedCarBookings);
		booking.getTourBookings().addAll(removedTourBookings);
		
		Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_BOOKINGS);
	}
	private void deleteRemovedBookings() {
		if (log.isDebugEnabled()) {
			log.debug("Deleting removed bookings");
		}
		/*
		 * delete previously removed bookings
		 */
		for (RoomBooking booking: removedRoomBookings) {
			getService().delete(booking);
		}
		for (CarBooking booking: removedCarBookings) {
			getService().delete(booking);
		}
		for (TourGuidingBooking booking: removedTourBookings) {
			getService().delete(booking);
		}
		
	}
	
	private void sendMail(NotificationType type) {
		try {
			mailer.sendMailNotification(type, booking);
		} catch (TemplateException | IOException | EmailException e) {
			e.printStackTrace();
			log.error("Exception when sending mail notification.",e);
		}
	}
	
	@NotifyChange("booking")
	@Command
	public void doDelete() {
		Messagebox.show("确定要确认这些客人的预订信息吗？按确定继续。", "确认预订",
	    	      Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
	    	      new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
							if (log.isInfoEnabled()) {
								log.info("Deleting guest booking..." );
							}
    	                	getService().delete(booking);
    	                	
    	                	if (log.isDebugEnabled()) {
								log.debug("Guest booking deleted. Redirecting to admin home.");
							}
    	                	//remove this booking since it's deleted.
    	                	Sessions.getCurrent().removeAttribute(ZKConstants.ATTRS_ADMIN_BOOKING);
    	                	Executions.sendRedirect(ZKConstants.REDIRECTS_ADMIN_HOME);
						}
					}
				}
	    	    );
	}
}
