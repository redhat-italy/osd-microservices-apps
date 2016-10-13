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

@Path("/api")
public class OffersEndpoint {
	   
		private static Logger log = Logger.getLogger(OffersEndpoint.class.getName());
	
	  
	    @Context 
		HttpServletRequest request;
	    
	    private static final String REPO_SESSION_KEY = "repo";
	    
	    @GET
	    @Path("/offers")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> list() {
	    	 log.info(request.getSession(true).getId()+" Listing all offers.... ");   		
	         return getOffersService().list();
	    }
	    
	    @GET
	    @Path("/offers/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Offer retrieveById(@PathParam("id") String id) {
	    	    		
	         return getOffersService().retrieveById(id);
	    }
	    
	    @POST
	    @Path("/offers")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> save(Offer toSave) throws Exception {
	    	getOffersService().save(toSave);
	    	return getOffersService().list();
	      
	    }
	    
	    @POST
	    @Path("/offers/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> save(@PathParam("id") String id,Offer toSave) throws Exception {
	    	getOffersService().save(id,toSave);
	    	return getOffersService().list();
	      
	    }
	    @DELETE
	    @Path("/offers")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer>  deleteById(Offer toSave) {
	    	 
	    	getOffersService().deleteById(toSave.getId());
	         return getOffersService().list();
	    }
	    
	    @DELETE
	    @Path("/offers/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer>  deleteById(@PathParam("id") String id) {
	    	 
	    	getOffersService().deleteById(id);
	         return getOffersService().list();
	    }
	    
	    private OffersService getOffersService() {
			if(request.getSession(true).getAttribute(REPO_SESSION_KEY) == null)
			{
				request.getSession(true).setAttribute(REPO_SESSION_KEY, new OffersService() );
			}
			return (OffersService)request.getSession(true).getAttribute(REPO_SESSION_KEY);
		}
}
