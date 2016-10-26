package it.redhat.osd;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import it.redhat.osd.model.Offer;

@Path("/")
public class Soap2RestEndpoint {
	   
		private static Logger log = Logger.getLogger(Soap2RestEndpoint.class.getName());
	
	  
	   
		@GET
	    @Path("/shipping/{country}")
	    @Produces(MediaType.APPLICATION_JSON)
		  public String shipping(@PathParam("country") String country) {
	    	 log.info("Getting shipping info for country "+country);   		
	         
	    	 
	    	 return null;
	    }
	    
	   
}
