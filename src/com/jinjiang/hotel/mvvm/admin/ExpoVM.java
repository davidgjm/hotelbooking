/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import java.io.IOException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.ZKWebUtil;
import com.jinjiang.hotel.config.ZKConstants;
import com.jinjiang.hotel.domain.Expo;
import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.service.ExpoService;
import com.jinjiang.hotel.service.impl.ExpoServiceImpl;

/**
 * @author gaojianm
 *
 */
public class ExpoVM {
	protected Logger log=LoggerFactory.getLogger(getClass());
	protected Expo selected;
	protected ListModelList<Expo> expos;
	
	public ExpoService getService() {
		return ExpoServiceImpl.getInstance();
	}
	
	/**
	 * @return the selected
	 */
	public Expo getSelected() {
		return selected;
	}
	
	/**
	 * @param selected the selected to set
	 */
	public void setSelected(Expo selected) {
		this.selected = selected;
	}
	/**
	 * @return the expos
	 */
	public List<Expo> getExpos() {
		if (expos==null) {
			expos=new ListModelList<Expo>(getService().getAll());
		}
		return expos;
	}
	/**
	 * @param expos the expos to set
	 */
	public void setExpos(ListModelList<Expo> expos) {
		this.expos = expos;
	}
	
	
	@Command
	public void showExpoDetails() {
		Executions.getCurrent().getSession().setAttribute(ZKConstants.ATTRS_ADMIN_EXPO, selected);
		Executions.getCurrent().sendRedirect(ZKConstants.PAGE_ADMIN_EXPO_DETAILS);
	}
	
	@NotifyChange("selected")
	@Command
	public void createExpo() {
		Expo expo=new Expo();
		getExpos().add(expo);
		selected=expo;
	}
	
	@NotifyChange({"selected","expos"})
	@Command
	public void saveExpo() {
		getService().saveOrUpdateExpo(selected);
	}
	
	@NotifyChange({"selected","expos"})
	@Command
	public void deleteExpo() {
		Messagebox.show("确定要删除此记录吗？", "删除", Messagebox.OK | Messagebox.CANCEL,
	    	    Messagebox.QUESTION,new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
    	                	if (log.isInfoEnabled()) {
								log.info("Deleting selected car rental: [{}]",selected);
							}
    	            		getService().deleteExpo(selected);
    	            		expos.remove(selected);
						}
					}
				});
	}
	@NotifyChange({"selected"})
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
				selected.setImagePath(imagePath);
				getService().saveOrUpdateExpo(selected);
			}else if (media != null)
				Messagebox.show("Not an image: "+media, "Error",
						Messagebox.OK, Messagebox.ERROR);
			
		}
	}
	
	@NotifyChange({"selected","hotels"})
	@Command
	public void addHotel() {
		
	}
	
	@NotifyChange({"selected","hotels"})
	@Command
	public void removeHotel() {
		
	}
}
