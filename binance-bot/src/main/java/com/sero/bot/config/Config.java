package com.sero.bot.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
	
	private final static String PATH = "config.properties";
	public final static String KEY = getValue("KEY");
	public final static String SECRET = getValue("SECRET");
	
	private static String getValue(String property) {
		
		InputStream input = Config.class.getClassLoader().getResourceAsStream(Config.PATH);
		if(input==null)
	        System.out.println("Unable to find " + Config.PATH);
		
	    Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(property);
	}
	
}
