package com.creditkarma.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class DataCreation {

	OutputStream output = null;
	/**
	 * This generic method is used to create environment.properties file in allure-results 
	 * directory. Data stored in environment.properties file will be used to populate allure
	 * report
	 * 
	 * @param fileName	:	Name of the file to be created.
	 * @param path	:	Relative path where to create file 
	 * @param data	:	Data to be stored in newly created file
	 * @throws IOException 
	 */
	public void createPropertyFile(String fileName, String path, Map<String,String> data) throws IOException {
		System.out.println("Inside DataCreation::createPropertyFile method.");
		try {
			//Get current directory path and specify additional path where to create
			//environment.properties file.
			String finalPath = System.getProperty("user.dir") + path + fileName;
			output = new FileOutputStream(finalPath);
			Properties prop = new Properties();

			for (Map.Entry<String,String> entry : data.entrySet()) {
				prop.setProperty((String)entry.getKey(), (String)entry.getValue());
			}
			prop.store(output, null);
		}catch(IOException io) {
			io.printStackTrace();
		}
		finally {
			if(output != null) {
				output.close();
			}
		}
	}

	/**
	 * This method generates a random number between min and max limit. 
	 * min and max are inclusive which means that min or max value can also be returned as
	 * a random number at runtime.
	 *  
	 * @param min	:	min number 
	 * @param max	:	max number
	 * @return		: random number between and inclusive of main and max
	 */
	public int generateRandomNumber(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return (r.nextInt((max - min) + 1) + min);
	}
}