package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager2 {

	private static Properties prop = new Properties();// create new object of properties.
	private static String path;
	private static String env;

	static {
		// We are creating static block so the following code of loading of the file
		// does not happen more than once.
		// When the class is loaded, it will read the file once and it can be used for
		// all the tests. We will not create
		// objects of file class and file reader class again and agian.

		env = System.getProperty("env", "qa");// This is an overloaded method, we have used this one where if user
												// enters any env name, it will have that value, if not, it will
												// automatically take qa as value.
		env.toLowerCase().trim();// We are just using these methods to make sure the env entered on terminal in
									// lower case and we trim spaces.

		switch (env) {
		case "dev" -> path = "config/config.dev.properties";

		case "QA" -> path = "config/config.QA.properties";

		case "UAT" -> path = "config/config.UAT.properties";

		default -> path = "config/config.QA.properties";
		}
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("File Not found, Please check path." + path);
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
