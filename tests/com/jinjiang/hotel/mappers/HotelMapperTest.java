/**
 * 
 */
package com.jinjiang.hotel.mappers;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jinjiang.hotel.domain.Hotel;
import com.jinjiang.hotel.domain.Room;

/**
 * @author gaojianm
 *
 */
public class HotelMapperTest  {
	private SqlSession session; 
	private HotelMapper mapper;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		session= com.jinjiang.hotel.orm.MyBatisUtil.getSessionFactory().openSession();
		mapper=session.getMapper(HotelMapper.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.HotelMapper#save(com.jinjiang.hotel.domain.Hotel)}.
	 */
	@Test
	public void testSave() {
		String name="上海浦东星河湾酒店（5星）";
		String address="上海浦东新区锦绣路2588号( 浦东新国际博览中心 近前程路)";
		String description=
				"座落于上海浦东陆家嘴中心地带，咫尺上海浦东商业中心，短短8分钟的车程，即可轻松到达新国际博览中心。 \r\n" + 
				" 上海浦东星河湾酒店以地中海城堡为蓝本，弥漫着欧洲的宫廷气息。酒店共21层，设有3间特色餐厅；大堂吧、德国酒吧；国际会议中心及各类型的多功能会议室；康乐设施；水疗SPA等。此外，酒店还配备了高度完善的保安系统，停车场、商场、商务中心等配套设施一应俱全。";
		Hotel hotel=new Hotel(name, address, description);
		
		Integer id=mapper.save(hotel);
		session.commit();
		System.out.println(id);
		
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.HotelMapper#delete(com.jinjiang.hotel.domain.Hotel)}.
	 */
	@Test
	public void testDelete() {
		Hotel hotel=mapper.getById(3);
		mapper.delete(hotel);
		session.commit();
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.HotelMapper#update(com.jinjiang.hotel.domain.Hotel)}.
	 */
	@Test
	public void testUpdate() {
		Hotel hotel=mapper.getById(2);
		hotel.setAddress("testing update address");
		mapper.update(hotel);
		session.commit();
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.HotelMapper#getAll()}.
	 */
	@Test
	public void testGetAll() {
		List<Hotel> hotels=mapper.getAll();
		assertTrue(hotels!=null&&!hotels.isEmpty());
		for (Hotel hotel : hotels) {
			System.out.println(hotel);
		}
	}

	/**
	 * Test method for {@link com.jinjiang.hotel.mappers.HotelMapper#getById(java.lang.Integer)}.
	 */
	@Test
	public void testGetById() {
		Hotel h= mapper.getById(1);
		assertTrue(h!=null);
		System.out.println(h);
		List<Room> rooms=h.getRooms();
		if (rooms!=null) {
			for (Room room : rooms) {
				System.out.println(room);
			}
		}
	}

}
