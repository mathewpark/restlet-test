package com.example.server;

import org.restlet.Restlet;
import org.restlet.ext.swagger.SwaggerApplication;
import org.restlet.routing.Router;

public class ApplicationImpl extends SwaggerApplication {

	public Restlet createInbountRoot() {
		// Create a router Restlet that defines routes.
		Router router = new Router(getContext());
		
		router.attach("ping", ServerResourceImpl.class);

//		attachSwaggerSpecificationRestlet(router, "api-docs");

		return router;
	}
}
