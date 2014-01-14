package com.sav.ra.framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	
	private static Properties props = null;

		private PropertyManager(){  //to make this class a singleton class
			
		}
	public static String getProperty(String key) {
		if (props == null) {
			props = new Properties();
			try {
				//FileInputStream fis = new FileInputStream(
				//		"C:\\sqa_class\\RARcProject\\src\\rideauction.properties");
				InputStream fis=ClassLoader.getSystemClassLoader().getResourceAsStream("rideauction.properties");
				
				props.load(fis);
			} catch (FileNotFoundException fne) {
				fne.printStackTrace();

			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}

		return props.getProperty(key);

	}

}
