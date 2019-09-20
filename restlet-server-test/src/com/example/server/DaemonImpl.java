package com.example.server;

import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Handler;
import java.util.logging.LogManager;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.data.Parameter;
import org.restlet.data.Protocol;
import org.restlet.service.CorsService;
import org.restlet.util.Series;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class DaemonImpl implements Daemon {
	private Logger log4j = Logger.getLogger(this.getClass());

	private Component restletComponent;
	
	public void init(DaemonContext context) throws DaemonInitException, Exception {
		DOMConfigurator.configureAndWatch(String.format("%s/conf/log4j.xml", C.BASE_PATH));

		log4j.info("Initialize daemon.");

		// remove the default java logger
		java.util.logging.Logger rootLogger = LogManager.getLogManager().getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		rootLogger.removeHandler(handlers[0]);

		// bridging a default log to the log4j by the slf4j
		SLF4JBridgeHandler.install();

		// Create a new Component.
		restletComponent = new ComponentImpl();

		// Add a new HTTP server listening on port#.
		restletComponent.getServers().add(Protocol.HTTP, C.SERVER_PORT);
		
		Series<Parameter> parameters = restletComponent.getServers().getContext().getParameters();
		parameters.add("maxThreads", "1024");
		parameters.add("tracing", "true");

		// clientKeepAlive, keepAlive

		CorsService corsService = new CorsService();         
		corsService.setAllowedOrigins(new HashSet(Arrays.asList("*")));
		corsService.setAllowedCredentials(true);
		
		Application application = new Swagger2ApplicationImpl();
		application.getServices().add(corsService);
		
		restletComponent.getDefaultHost().attach("/", application);

		log4j.info("Daemon initialized.");
	}

	public void start() throws Exception {
		log4j.info("Start daemon.");
		
		restletComponent.start();

		log4j.info("daemon started.");
	}

	public void stop() throws Exception {
		log4j.info("Stop daemon.");

		restletComponent.stop();
		
		log4j.info("Daemon stopped.");
	}

	public void destroy() {
		log4j.info("Destory daemon.");
		System.exit(0);
	}

}
