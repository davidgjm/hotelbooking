package com.jinjiang.hotel.config;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class AppConfig {

	private static Configuration config;
	private static String fileName="config.xml";
	private static AppConfig instance;
	public AppConfig() {
		try {
			config=new XMLConfiguration(fileName);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static synchronized AppConfig getInstance() {
		if (instance==null) {
			instance=new AppConfig();
		}
		return instance;
	}
	
	public String getString(String key) {
		return config.getString(key);
	}
	
	public boolean getBoolean(String key) {
		return config.getBoolean(key);
	}
	
	public int getInt(String key) {
		return config.getInt(key);
	}
}
