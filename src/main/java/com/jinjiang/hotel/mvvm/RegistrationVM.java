/**
 * 
 */
package com.jinjiang.hotel.mvvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModelSet;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.CarBooking;
import com.jinjiang.hotel.domain.CarRental;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Guest;
import com.jinjiang.hotel.domain.GuestBooking;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.PaymentMode;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.domain.RoomBooking;
import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.domain.TourGuidingBooking;
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
public class RegistrationVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private MailService mailer=new MailServiceImpl();
	private Validator emailValidator=new EmailValidator();
	private String message;
	private Guest guest=new Guest();
	private GuestBooking booking;
	private Hotel hotel;
	private ListModelList<Room> rooms;
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
	

	@Init
	public void initialize() {
		hotel=(Hotel) Executions.getCurrent().getSession().getAttribute(ZKConstants.ATTRS_FRONT_HOTEL);
		Expo expo=(Expo) Executions.getCurrent().getSession().getAttribute(ZKConstants.ATTRS_FRONT_EXPO);
		if (hotel==null||expo==null) {
			if (log.isInfoEnabled()) {
				log.info("The session may have been distroyed. Redirecting to homepage");
			}
			Executions.getCurrent().sendRedirect(ZKConstants.REDIRECTS_HOME);
			return;
		}
		guest.setExpo(expo);
	}
	
	/**
	 * @return the emailValidator
	 */
	public Validator getEmailValidator() {
		return emailValidator;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public BookingService getService() {
		return BookingServiceImpl.getInstance();
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
	 * @return the guest
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * @param guest the guest to set
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	/**
	 * @return the booking
	 */
	public GuestBooking getBooking() {
		if(booking==null) booking=new GuestBooking();
		return booking;
	}

	/**
	 * @param booking the booking to set
	 */
	public void setBooking(GuestBooking booking) {
		this.booking = booking;
	}

	/**
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * @return the rooms
	 */
	public ListModelList<Room> getRooms() {
		if (rooms==null) {
			rooms=new ListModelList<>();
			if (hotel!=null) {
				for (Room room : hotel.getRooms()) {
					if (!room.getIsFull()) {
						rooms.add(room);
					}
				}
			}
		}
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(ListModelList<Room> rooms) {
		this.rooms = rooms;
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
		if(modes==null)
			modes=new ListModelList<PaymentMode>(getService().getAllPaymentModes());
		return modes;
	}

	/**
	 * @param modes the modes to set
	 */
	public void setModes(ListModelList<PaymentMode> modes) {
		this.modes = modes;
	}

	/**
	 * @return the booking
	 */
	public RoomBooking getRoomBooking() {
		return roomBooking;
	}

	/**
	 * @param booking the booking to set
	 */
	public void setRoomBooking(RoomBooking booking) {
		this.roomBooking = booking;
	}
	
	@NotifyChange("guest")
	@Command
	public Integer doSaveGuest() {
		if (log.isDebugEnabled()) {
			log.debug("Trying to save guest {}. Email: {}", guest.getName(), guest.getEmail());
		}
		Integer id=getService().saveOrUpdate(guest);
		if (log.isDebugEnabled()) {
			log.debug("The guest is saved with id: {}",id);
		}
		return id;
	}
	
	private void sendMail() {
		/*
		 * send email notification
		 */
		try {
			mailer.sendMailNotification(NotificationType.BookingCreation, booking);
		} catch (TemplateException | IOException | EmailException e) {
			e.printStackTrace();
		}
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
		booking.setRoomCount(roomBooking.getRoomCount());
		booking.setRoom(roomBooking.getRoom());
		booking.setCheckinDate(roomBooking.getCheckinDate());
		booking.setCheckoutDate(roomBooking.getCheckoutDate());
		booking.setMode(roomBooking.getMode());
		this.booking.getRoomBookings().add(booking);
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
		cb.setBookCount(carBooking.getBookCount());
		cb.setWhenToUse(carBooking.getWhenToUse());
		cb.setCarRental(carBooking.getCarRental());
		this.booking.getCarBookings().add(cb);
	}
	
	@NotifyChange("booking")
	@Command
	public void doRemoveRoomBooking(@BindingParam("booking") RoomBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Removing room booking {}", booking);
		}
		this.booking.getRoomBookings().remove(booking);
	}
	@NotifyChange("booking")
	@Command
	public void doRemoveCarBooking(@BindingParam("booking") CarBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Removing car booking {}", booking);
		}
		this.booking.getCarBookings().remove(booking);
	}
	
	@NotifyChange("booking")
	@Command
	public void doAddTourBooking() {
		if(tourBooking.getGuiding()==null||tourBooking.getBookCount()==null||tourBooking.getBookCount()<1) return;
		TourGuidingBooking booking=new TourGuidingBooking();
		booking.setBookCount(tourBooking.getBookCount());
		booking.setGuiding(tourBooking.getGuiding());
		booking.setWhenToUse(tourBooking.getWhenToUse());
		if (log.isDebugEnabled()) {
			log.debug("Tour Guiding booking to add: {}",booking);
		}
//		if(!booking.contains(booking))booking.add(booking);
		if(!this.booking.getTourBookings().contains(booking))this.booking.getTourBookings().add(booking);
	}
	@NotifyChange("booking")
	@Command
	public void doRemoveTourBooking(@BindingParam("booking") TourGuidingBooking booking) {
		if (log.isDebugEnabled()) {
			log.debug("Removing tour booking {}", booking);
		}
		this.booking.getTourBookings().remove(booking);
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
		if(tourRoutes==null) tourRoutes=new ListModelList<String>(getService().getTourRoutes());
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

	@NotifyChange({"guest","booking"})
	@Command
	public void doSubmit() {
		if(guest.getIsBuyer()==null&&guest.getIsExhibitor()==null && guest.getIsVIP()==null) {
			Messagebox.show("请设定您的参展身份","错误", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if(booking.getRoomBookings()==null||booking.getRoomBookings().isEmpty()) {
			Messagebox.show("请至少预订一个酒店房间！","错误", Messagebox.OK, Messagebox.ERROR);
			return;
		}
	    Messagebox.show("确定要提交预订信息吗？按确定继续。", 
	    	    "提交", Messagebox.OK | Messagebox.CANCEL,
	    	    Messagebox.QUESTION,
	    	        new org.zkoss.zk.ui.event.EventListener<Event>(){
	    	            public void onEvent(Event e){
	    	            	if (log.isDebugEnabled()) {
								log.debug("Messagebox event type: {}",e.getClass().getName());
							}
	    	            	if(e.getName().equals("onCancel")) return;
	    	            	
	    	            	
	    	                if("onOK".equals(e.getName())){
	    	                	if (log.isDebugEnabled()) {
									log.debug("Preparing to submit user booking!");
								}
	    	                	
	    	                	/*
	    	                	 * saving guest
	    	                	 */
	    	                	doSaveGuest();
	    	                	
	    	                	booking.setBookedOn(new Date());
	    	                	booking.setGuest(guest);
	    	                	booking.setConfirmStatus(false);
	    	                	
	    	            		Integer id=getService().save(booking);
	    	            		if (log.isDebugEnabled()) {
	    	            			log.debug("Guest booking saved with id: {}", id);
	    	            		}
	    	            		if(id!=null&&id>0) booking.setId(id);
	    	            		
	    	            		sendMail();
	    	                	Executions.sendRedirect(ZKConstants.REDIRECTS_FRONT_EXPO);
	    	                }
	    	            }
	    	        }
	    	    );
	}
}
