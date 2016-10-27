package it.redhat.osd.endpoints;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.cdi.ContextName;

import it.redhat.osd.camel.CamelDelegate;


@Path("/")
public class Soap2RestEndpoint {
	   
		private static Logger log = Logger.getLogger(Soap2RestEndpoint.class.getName());
	
		@Inject
	    @ContextName("Soap2RestRoute")
	    private CamelContext context;
	  
		private CamelDelegate camelDelegate;
	   
		
		 @PostConstruct
		    void initDelegate() throws Exception {
		        camelDelegate = new ProxyBuilder(context).endpoint("direct:input").build(CamelDelegate.class);
		    }
		 
		
		@GET
	    @Path("/shipping/{country}")
	    @Produces(MediaType.APPLICATION_JSON)
		  public String shipping(@PathParam("country") String country) {
	    	 log.info("Getting shipping info for country "+country);   		
	         return camelDelegate.executeRoute(country);
	    }
}
