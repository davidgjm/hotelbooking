/**
 * 
 */
package com.jinjiang.hotel.orm;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * @author gaojianm
 *
 */
public class BoneCPDataSourceFactory extends UnpooledDataSourceFactory {
	/**
	 * 
	 */
	public BoneCPDataSourceFactory() {
		this.dataSource=new BoneCPDataSource();
	}

}
