package com.example.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.routing.Router;

public class Swagger2ApplicationImpl extends Application {
	@Override
	public Restlet createInboundRoot() {
		// Create a router Restlet that defines routes.
		Router router = new Router(getContext());
		
		router.attach("/admin/pings", ServerResourceImpl.class);

	    // Configuring Swagger 2 support
	    Swagger2SpecificationRestlet swagger2SpecificationRestlet = new Swagger2SpecificationRestlet(this);
	    swagger2SpecificationRestlet.setBasePath("http://myapp.com:8889/");
	    swagger2SpecificationRestlet.attach(router, "/docs");
	    
		return router;
	}
}