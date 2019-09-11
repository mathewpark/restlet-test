package com.example.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.swagger.Swagger2SpecificationRestlet;
import org.restlet.ext.swagger.SwaggerApplication;
import org.restlet.routing.Router;

public class SwaggerApplicationImpl extends SwaggerApplication {
	/*
     * Define role names
     */
    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_OWNER = "owner";

    public static final String ROLE_USER = "user";

    /*
     * Define route constants
     */
    public static final String ROUTE_COMPANIES = "/companies";

    public static final String ROUTE_CONTACTS = "/contacts";

    /*
     * Define SQL State code constants
     */
    public static final String SQL_STATE_23000_DUPLICATE = "23000";
    
	@Override
	public Restlet createInboundRoot() {
		// Create a router Restlet that defines routes.
		Router router = new Router(getContext());
		
		router.attach("ping", ServerResourceImpl.class);

//		attachSwaggerSpecificationRestlet(router, "api-docs");

	    // Configuring Swagger 2 support
	    Swagger2SpecificationRestlet swagger2SpecificationRestlet = new Swagger2SpecificationRestlet(this);
	    swagger2SpecificationRestlet.setBasePath("http://45.115.155.125:8889/");
	    swagger2SpecificationRestlet.attach(router, "/api-docs");

	    return router;
	}
}
