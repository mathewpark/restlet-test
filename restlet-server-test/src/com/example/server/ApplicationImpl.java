package com.example.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ApplicationImpl extends Application {

	@Override
	public Restlet createInboundRoot() {
		// Create a router Restlet that defines routes.
		Router router = new Router(getContext());
		
		router.attach("ping", ServerResourceImpl.class);

		return router;
	}
}