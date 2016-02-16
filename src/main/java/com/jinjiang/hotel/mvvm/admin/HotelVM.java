/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.ZKWebUtil;
import com.jinjiang.hotel.config.AppConfig;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.Room;
import com.jinjiang.hotel.service.HotelService;
import com.jinjiang.hotel.service.impl.HotelServiceImpl;

/**
 * @author gaojianm
 *
 */
public class HotelVM {
	private Logger log=LoggerFactory.getLogger(getClass());
	private AppConfig config=AppConfig.getInstance();
	private ListModelList<Hotel> hotels;
	private Hotel hotel;
	private Room selectedRoom;
	
	/**
	 * @return the hotel
	 * @throws IOException 
	 */
	public Hotel getHotel() throws IOException {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	

	public HotelService getService() {
		return HotelServiceImpl.getInstance();
	}
	
	
	public List<Hotel> getHotels(){
		if (hotels==null) {
			hotels=new ListModelList<Hotel>(getService().getAll());
		}
		return hotels;
	}

	/**
	 * @param hotels the hotels to set
	 */
	public void setHotels(ListModelList<Hotel> hotels) {
		this.hotels = hotels;
	}
	
	@NotifyChange("hotels")
	@Command
	public void createHotel() {
		Hotel h=new Hotel();
		getHotels().add(h);
		hotel=h;
	}
	
	@NotifyChange({"hotel"})
	@Command
	public void saveHotel() {
		getService().saveOrUpdate(hotel);
	}
	
	@NotifyChange({"hotel"})
	@Command
	public void createRoom() {
		Room r=new Room();
		r.setHotel(hotel);
		hotel.getRooms().add(r);
	}
	
	@NotifyChange({"selectedRoom","hotel"})
	@Command
	public void saveRoom() {
		getService().saveOrUpdateRoom(selectedRoom);
	}
	
	@Command
	public void updateRoom(@BindingParam("room") Room room) {
		getService().saveOrUpdateRoom(room);
	}
	
	@NotifyChange({"hotel"})
	@Command
	public void deleteRoom(@BindingParam("room") Room room) {
		hotel.getRooms().remove(room);
		getService().deleteRoom(room);
	}
	
	@NotifyChange({"hotel","hotels"})
	@Command
	public void deleteHotel() {
		if (hotel.getId()!=null) {
			getService().deleteHotel(hotel);
		}
		hotels.remove(hotel);
		this.hotel=null;
	}
	
	@NotifyChange({"selectedRoom","hotel"})
	@Command
	public void handleOnSelectHotel() {
		if (hotel.getRooms()==null || hotel.getRooms().isEmpty()) {
			selectedRoom=null;
		}
	}
	/**
	 * @return the selectedRoom
	 */
	public Room getSelectedRoom() {
		return selectedRoom;
	}

	/**
	 * @param selectedRoom the selectedRoom to set
	 */
	public void setSelectedRoom(Room selectedRoom) {
		this.selectedRoom = selectedRoom;
	}

	@NotifyChange({"hotel"})
	@Command
	public void upload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		UploadEvent event = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent!=null&& (objUploadEvent instanceof UploadEvent)) {
			event=(UploadEvent) objUploadEvent;
		}
		
		if (event!=null) {
			Media media=event.getMedia();
			if (media instanceof Image) {
				if (log.isDebugEnabled()) {
					log.debug("Uploading image {}, format: {}", media.getName(), media.getFormat());
				}
				String imagePath=ZKWebUtil.uploadImage(media);
				hotel.setImagePath(imagePath);
			}else if (media != null)
				Messagebox.show("Not an image: "+media, "Error",
						Messagebox.OK, Messagebox.ERROR);
			
		}
	}
}
