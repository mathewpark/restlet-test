package com.example.server;

import java.util.logging.Handler;
import java.util.logging.LogManager;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.restlet.Component;
import org.restlet.data.Parameter;
import org.restlet.data.Protocol;
import org.restlet.util.Series;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class DaemonImpl implements Daemon {
//	private Logger log4j = Logger.getLogger(this.getClass());

	private Component restletComponent;
	
	public void init(DaemonContext context) throws DaemonInitException, Exception {
//		DOMConfigurator.configureAndWatch("conf/log4j.xml");

		System.out.println("Initialize daemon.");

//		// remove the default java logger
//		java.util.logging.Logger rootLogger = LogManager.getLogManager().getLogger("");
//		Handler[] handlers = rootLogger.getHandlers();
//		rootLogger.removeHandler(handlers[0]);
//
//		// bridging a default log to the log4j by the slf4j
//		SLF4JBridgeHandler.install();

		// Create a new Component.
		restletComponent = new ComponentImpl();

		// Add a new HTTP server listening on port#.
		restletComponent.getServers().add(Protocol.HTTP, 8089);
		Series<Parameter> parameters = restletComponent.getServers().getContext().getParameters();
		parameters.add("maxThreads", "1024");

		// clientKeepAlive, keepAlive

		restletComponent.getDefaultHost().attach("/", new ApplicationImpl());

		System.out.println("Daemon initialized.");
	}

	public void start() throws Exception {
		System.out.println("Start daemon.");
		
		restletComponent.start();

		System.out.println("daemon started.");
	}

	public void stop() throws Exception {
		System.out.println("Stop daemon.");

		restletComponent.stop();
		
		System.out.println("Daemon stopped.");
	}

	public void destroy() {
		System.out.println("Destory daemon.");
		System.exit(0);
	}

}
