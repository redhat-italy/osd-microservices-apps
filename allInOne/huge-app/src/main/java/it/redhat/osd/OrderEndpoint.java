package it.redhat.osd;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
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

import it.redhat.osd.model.Order;


@Path("/")
public class OrderEndpoint {
	   
		private static Logger log = Logger.getLogger(OrderEndpoint.class.getName());
	
		@Inject
		OrderService orderService;
	  
	    @Context 
		HttpServletRequest request;
	    
	    
	    @POST
	    @Path("/brms/ds/order")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Order order(Order order) {
	    	 log.info("Getting offer for order:  "+order);   		
	         return orderService.getResult(order);
	    }
	   
	    
}
