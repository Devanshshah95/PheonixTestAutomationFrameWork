package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager_OLDNOTINUSE {

	private static Properties prop = new Properties();// create new object of properties.

	static {
		// We are creating static block so the following code of loading of the file
		// does not happen more than once.
		// When the class is loaded, it will read the file once and it can be used for
		// all the tests. We will not create
		// objects of file class and file reader class again and agian.
		File fileconfig = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + "config.properties");
		//The above path is very long and non readable. Check configManager2 class for more optimized way to do it.
		try {
			FileReader filereader = new FileReader(fileconfig);
			prop.load(filereader);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
