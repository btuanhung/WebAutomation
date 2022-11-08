package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	private static String configPath = "./configurations/configs.properties";

	public static String getProperty(String key)throws IOException {
		Properties prop = new Properties();
		String value = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(configPath);
			prop.load(fis);
			value = prop.getProperty(key);
			return value;
		}catch(Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị của "+key);
			ex.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void setProperty(String key, String value)throws IOException {
		Properties prop = new Properties();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(configPath);
			prop.setProperty(key, value);
			prop.store(fos, value);
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
