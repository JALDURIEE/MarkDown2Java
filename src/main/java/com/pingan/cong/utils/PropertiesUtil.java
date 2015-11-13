package com.pingan.cong.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	
	private  static Properties properties;
	

	public static String getProperty(String key) throws IOException{
		
		if(properties==null){
			synchronized (PropertiesUtil.class) {
				if(properties==null){
					properties = new Properties();
					InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
					properties.load(is);
				}
			}
		}
		return properties.getProperty(key);
		
	}
	
	
	public static void main(String[] args){
	try {
		System.out.println(	getProperty("scan.package"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
