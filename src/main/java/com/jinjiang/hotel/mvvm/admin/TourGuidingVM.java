/**
 * 
 */
package com.jinjiang.hotel.mvvm.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import com.jinjiang.hotel.domain.TourGuiding;
import com.jinjiang.hotel.service.TourGuidingService;
import com.jinjiang.hotel.service.impl.TourGuidingServiceImpl;

/**
 * @author gaojianm
 *
 */
public class TourGuidingVM{
	private Logger log=LoggerFactory.getLogger(getClass());
	private ListModelList<TourGuiding> guides;
	private TourGuiding selected;
	public TourGuidingService getService() {
		return TourGuidingServiceImpl.getInstance();
	}
	
	/**
	 * @return the guides
	 */
	public ListModelList<TourGuiding> getGuides() {
		if (guides==null) {
			guides=new ListModelList<TourGuiding>(getService().getAll());
		}
		return guides;
	}

	/**
	 * @param guides the guides to set
	 */
	public void setGuides(ListModelList<TourGuiding> guides) {
		this.guides = guides;
	}

	/**
	 * @return the selected
	 */
	public TourGuiding getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(TourGuiding selected) {
		this.selected = selected;
	}
	
	@NotifyChange({"selected","guides"})
	@Command
	public void doCreate() {
		TourGuiding cr=new TourGuiding();
		getGuides().add(cr);
		selected=cr;
	}
	
	@NotifyChange({"selected","guides"})
	@Command
	public void doSave() {
		getService().saveOrUpdate(selected);
	}
	
	@NotifyChange({"selected","guides"})
	@Command
	public void doDelete() {
		Messagebox.show("确定要删除此记录吗？", "删除", Messagebox.OK | Messagebox.CANCEL,
	    	    Messagebox.QUESTION,new EventListener<Event>() {
					@Override
					public void onEvent(Event e) throws Exception {
						if(e.getName().equals("onCancel")) return;
						if("onOK".equals(e.getName())){
    	                	if (log.isInfoEnabled()) {
								log.info("Deleting selected tour guide.");
							}
    	                	guides.remove(selected);
    	                	getService().delete(selected);
						}
					}
				});
	}
	
}
