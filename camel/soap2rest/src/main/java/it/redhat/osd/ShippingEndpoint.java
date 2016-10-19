package it.redhat.osd;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/api")
public interface ShippingEndpoint {

	
	@GET
    @Path("/shipping/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String shipping(@PathParam("country") String country);
}
