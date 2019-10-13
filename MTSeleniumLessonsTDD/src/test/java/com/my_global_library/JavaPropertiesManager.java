package com.my_global_library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.testng.log4testng.Logger;

public class JavaPropertiesManager {

	final static Logger logger = Logger.getLogger(JavaPropertiesManager.class);
	private String propertiesFile;
	private Properties prop;
	private OutputStream output;
	private InputStream input;

	public JavaPropertiesManager(String propertiesFilePath) {

		propertiesFile = propertiesFilePath;
		prop = new Properties();
	}

	public String readProperty(String key) {
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					logger.error("Error: ", e);
				}

			}
		}
		return value;
	}

	public void setProperty(String key, String value) {

		try {

			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
		} catch (Exception e) {
			logger.error("Error: ", e);

		}

	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * JavaPropertiesManager propManager = new
	 * JavaPropertiesManager("src/test/resources/config.properties");
	 * propManager.setProperty("browserType", "Chrome");
	 * propManager.setProperty("isDemo", "false");
	 * propManager.setProperty("isRemoteTest", "false");
	 * 
	 * String propertyValue = propManager.readProperty("browserType"); String demo =
	 * propManager.readProperty("isDemo"); logger.info("Value is: " +
	 * propertyValue); logger.info("Value is: " + demo); }
	 */
}
