package com.example.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerApplication;
import org.restlet.routing.Router;

public class SwaggerApplicationImpl extends SwaggerApplication {
//public class SwaggerApplicationImpl extends Application {
    
	@Override
	public Restlet createInboundRoot() {
		// Create a router Restlet that defines routes.
		Router router = new Router(getContext());
		
		router.attach("ping", ServerResourceImpl.class);

		attachSwaggerSpecificationRestlet(router, "api-docs");

//	    // Configuring Swagger 2 support
//	    Swagger2SpecificationRestlet swagger2SpecificationRestlet = new Swagger2SpecificationRestlet(this);
//	    swagger2SpecificationRestlet.setBasePath("http://localhost:8889/api-docs");
//	    swagger2SpecificationRestlet.attach(router);

	    return router;
	}
}