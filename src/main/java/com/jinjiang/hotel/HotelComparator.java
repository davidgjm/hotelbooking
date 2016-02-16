/**
 * 
 */
package com.jinjiang.hotel;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.Room;

/**
 * @author gaojianm
 *
 */
public class HotelComparator implements Comparator<Hotel> {
	private Logger logger=LoggerFactory.getLogger(getClass());
	/**
	 * 
	 */
	public HotelComparator() {
	}

	@Override
	public int compare(Hotel o1, Hotel o2) {
		if(Objects.deepEquals(o1, o2)) return 0;
		List<Room> rooms=o1.getRooms();
		List<Room> otherRooms=o2.getRooms();
		if(rooms.isEmpty() && otherRooms.size()>0) return -1;
		if(rooms.size()>0 && otherRooms.isEmpty()) return 1;
		if(rooms.equals(otherRooms)) return o1.getId() - o2.getId();
		
		//because rooms are retrieved in DESC order, the first room will have the highest price
		String o1Highest=rooms.get(0).getPrice();
		
		String o2Highest=otherRooms.get(0).getPrice();
		return o1Highest.compareToIgnoreCase(o2Highest);
	}

}
