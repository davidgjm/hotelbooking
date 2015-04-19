/**
 * 
 */
package com.jinjiang.hotel.mappers;

import java.util.List;

import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.Room;

/**
 * @author gaojianm
 *
 */
public interface RoomMapper {

	Integer save(Room room);
	void delete(Room room);
	void update(Room room);
	Room getById(Integer id);
	List<Room> getAll();
	List<Room> getByHotel(Hotel hotel);
	Integer getLast();
}
