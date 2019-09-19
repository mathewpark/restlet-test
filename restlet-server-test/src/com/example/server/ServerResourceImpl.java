package com.example.server;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

public class ServerResourceImpl extends ServerResource {
	Logger log4j = Logger.getLogger(ServerResourceImpl.class);
	
	@Get
	public Representation webGet() {
		log4j.debug("called.");
		
		JSONObject root = new JSONObject();
		
		root.put("msg", "test source.");
		
		return new JsonRepresentation(root);
	}
}