package it.redhat.osd.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public class Echo {
    @Inject
    EchoService echoService;

    @GET
    @Produces({ "application/json" })
    public Response getUUIDJSON() {
        return Response.status(Response.Status.OK).entity(echoService.createUUIDMessage()).build();
    }
    
    @GET
    @Path("/{payload}")
    @Produces({ "application/json" })
    public Response getEchoJSON(@PathParam("payload") String payload) {
        return Response.status(Response.Status.OK).entity(echoService.createEchoMessage(payload)).build();
    }

}
