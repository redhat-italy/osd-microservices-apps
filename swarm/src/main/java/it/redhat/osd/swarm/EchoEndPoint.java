package com.example.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

@Path("/echo")
public class EchoEndPoint {

	@GET
	@Produces("text/plain")
	public Response doGet() {
		return Response.ok("Ready to go for OSD?").build();
	}
}
