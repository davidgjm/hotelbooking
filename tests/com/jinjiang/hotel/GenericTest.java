/**
 * 
 */
package com.jinjiang.hotel;

import org.apache.ibatis.session.SqlSession;

import com.jinjiang.hotel.orm.MyBatisUtil;

/**
 * @author gaojianm
 *
 */
public class GenericTest {
	protected SqlSession session=MyBatisUtil.getSessionFactory().openSession(); 

}
