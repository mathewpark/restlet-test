package com.example.server;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.common.base.Throwables;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

//@Api
public class ServerResourceImpl extends ServerResource {
	Logger log4j = Logger.getLogger(ServerResourceImpl.class);
	
	@Get
	public Representation webGet() {
		log4j.debug("@GET Method called.");
		
		JSONObject root = new JSONObject();
		
		root.put("msg", "@GET Method called.");
		
		return new JsonRepresentation(root);
	}
	
	@Post("json")
//	public Representation webPost(JsonRepresentation entity) {
	public Representation webPost(String json) {
		Representation rep = getRequestEntity();
		
		JSONObject data = null;
		
//		try {
//			if (rep.isAvailable()) {
//				Form form = new Form(rep);
//				
//				data = new JSONObject(java.net.URLDecoder.decode(form.getQueryString(), "UTF-8"));
//			} else {
//				data = new JSONObject(json);
//			}
//		} catch (Exception e) { log4j.error(Throwables.getStackTraceAsString(e)); }
		
		data = new JSONObject(json);
		
		log4j.debug("@POST Method called.");
		log4j.debug(String.format("body data=%s", data == null ? "null" : data.toString()));
		
		JSONObject root = new JSONObject();
		
		root.put("msg", "@POST Method called.");
		root.put("body", data == null ? "null" : data.toString());
		
		return new JsonRepresentation(root);
	}
}