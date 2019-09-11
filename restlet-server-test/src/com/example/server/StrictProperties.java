package com.example.server;

import java.util.Properties;

import org.apache.log4j.Logger;

public class StrictProperties extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7911552146813372641L;
	private static final Logger log4j = Logger
			.getLogger(StrictProperties.class);

	@Override
	public String getProperty(String key) {
		return getPropertyWithEmptyCheck(key);
	}

	public int getIntegerProperty(String key) {
		int ret = 0;
		try {
			ret = Integer.parseInt(getPropertyWithEmptyCheck(key));
		} catch (NumberFormatException e) {
			log4j.fatal(String
					.format("the value of the key(%s) is not number. It should be number.",
							key));
			System.exit(1);
		}

		return ret;
	}
	
	public Long getLongProperty(String key) {
        Long ret = 0l;
        try {
            ret = Long.parseLong(getPropertyWithEmptyCheck(key));
        } catch (NumberFormatException e) {
            log4j.fatal(String
                    .format("the value of the key(%s) is not number. It should be number.",
                            key));
            System.exit(1);
        }

        return ret;
	}
	
	public Double getDoubleProperty(String key) {
		Double ret = 0d;
		try {
			ret = Double.parseDouble(getPropertyWithEmptyCheck(key));
		} catch (NumberFormatException e) {
			log4j.fatal(String
					.format("the value of the key(%s) is not number. It should be number.",
							key));
			System.exit(1);
		}

		return ret;
	}

	public boolean getBooleanProperty(String key) {
		boolean ret = false;

		ret = Boolean.valueOf(getPropertyWithEmptyCheck(key));

		return ret;
	}
	
	public String getStringAsNull(String key) {
		if (key == null || key.isEmpty()) {
			log4j.fatal("key value for the properties is empty. It should not be empty.");
			System.exit(1);
		}

		String value = super.getProperty(key);
		if (value == null || value.isEmpty()) {
			value = null;
		}

		return value;
	}

	private String getPropertyWithEmptyCheck(String key) {
		if (key == null || key.isEmpty()) {
			log4j.fatal("key value for the properties is empty. It should not be empty.");
			System.exit(1);
		}

		String value = super.getProperty(key);
		if (value == null || value.isEmpty()) {
			log4j.fatal(String
					.format("the value of the key(%s) is empty. It should not be empty.",
							key));
			System.exit(1);
		}

		return value;
	}
}