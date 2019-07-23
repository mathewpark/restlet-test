package com.example.server;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class ServerResourceImpl extends ServerResource {
	@Get
	public Representation webGet() {
		JSONObject root = new JSONObject();
		
		root.put("msg", "test source.");
		
		return new JsonRepresentation(root);
	}
}