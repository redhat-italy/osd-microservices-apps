package it.redhat.osd;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * A simple REST service which is able to echo using EchoService. 
 * Please take a look at the web.xml where JAX-RS is enabled
 *
 * @author gbonocor@redhat.com
 *
 */

@Path("/")
public class Echo {
    @Inject
    EchoService echoService;

    

    @GET
    @Path("/")
    @Produces({ "application/json" })
    public String getUUIDJSON() {
    	    		
         return "{\"result\":\"" + echoService.createUUIDMessage() + "\"}";
    }
    
    @GET
    @Path("/{payload}")
    @Produces({ "application/json" })
    public String getEchoJSON(@PathParam("payload") String payload) {
    	    		
         return "{\"result\":\"" + echoService.createEchoMessage(payload) + "\"}";
    }

}
