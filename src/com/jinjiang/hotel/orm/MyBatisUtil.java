package com.jinjiang.hotel.orm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisUtil {

	public static String config_file="mybatis-config.xml";
	private static SqlSessionFactory factory;
	private static Logger log=LoggerFactory.getLogger(MyBatisUtil.class);
	
	static {
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(config_file);
			factory =new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static final SqlSessionFactory getSessionFactory() {
		if (factory==null) {
			rebuildSqlSessionFactory();
		}
		
		return factory;
	}
	
	public static SqlSession getSession(){
		if(factory == null){
			rebuildSqlSessionFactory();
		}
		SqlSession session = factory.openSession();
		return session;
	}
	private static void rebuildSqlSessionFactory() {
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(config_file);
			factory= new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
