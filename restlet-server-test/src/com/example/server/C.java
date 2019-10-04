package com.example.server;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;

/**
 * Constants and Configurations,
 * 
 * @author 
 * 
 */
public class C {
	private static final Logger log4j = Logger.getLogger(C.class);
	
    public static final String BASE_PATH = "/root/workspace/restlet-server-test/";
    public static final String PROPERTY_PATH = String.format("%sconf/server.properties", BASE_PATH);
    public static final String MDC_LOG_LEVEL = "dl";
    
    public static final int SERVER_PORT;
    
    static {
    	StrictProperties p = null;
        try {
        	log4j.info("load property from " + new File(PROPERTY_PATH).getAbsolutePath());
            p = new StrictProperties();
            p.load(new FileInputStream(PROPERTY_PATH));
        } catch (Exception e) {
        	log4j.fatal(e);
            System.exit(1);
        }

        SERVER_PORT = p.getIntegerProperty("SERVER_PORT");
    }
}
