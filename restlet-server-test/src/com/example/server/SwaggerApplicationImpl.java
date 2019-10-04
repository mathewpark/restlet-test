package com.example.server;

import org.restlet.Restlet;
import org.restlet.ext.swagger.SwaggerApplication;
import org.restlet.routing.Router;

public class SwaggerApplicationImpl extends SwaggerApplication {
    @Override
	public Restlet createInboundRoot() {
		// Create a router Restlet that defines routes.
		Router router = new Router(getContext());
		
		router.attach("/admin/pings", ServerResourceImpl.class);

		attachSwaggerSpecificationRestlet(router, "/api-docs");

	    return router;
	}
}