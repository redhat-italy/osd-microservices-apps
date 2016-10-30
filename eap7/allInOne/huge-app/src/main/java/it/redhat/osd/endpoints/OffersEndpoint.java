package it.redhat.osd.endpoints;

import java.util.ArrayList;
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

import it.redhat.osd.model.Country;
import it.redhat.osd.model.Offer;
import it.redhat.osd.services.OffersService;

@Path("/")
public class OffersEndpoint {
	   
		private static Logger log = Logger.getLogger(OffersEndpoint.class.getName());
	
	  
	    @Context 
		HttpServletRequest request;
	    
	    private static final String REPO_SESSION_KEY = "offersRepo";
	    
	    @GET
	    @Path("/offers")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> list() {
	    	 log.info(request.getSession(true).getId()+" Listing all offers.... ");   		
	         return getOfferService().list();
	    }
	    
	    @GET
	    @Path("/offers/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Offer retrieveById(@PathParam("id") String id) {
	    	    		
	         return getOfferService().retrieveById(id);
	    }
	    
	    @POST
	    @Path("/offers")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> save(Offer toSave) throws Exception {
	    	getOfferService().save(toSave);
	    	return getOfferService().list();
	      
	    }
	    
	    @POST
	    @Path("/offers/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> save(@PathParam("id") String id,Offer toSave) throws Exception {
	    	getOfferService().save(id,toSave);
	    	return getOfferService().list();
	      
	    }
	    @DELETE
	    @Path("/offers")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer>  deleteById(Offer toSave) {
	    	 
	    	 getOfferService().deleteById(toSave.getId());
	         return getOfferService().list();
	    }
	    
	    @DELETE
	    @Path("/offers/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer>  deleteById(@PathParam("id") String id) {
	    	 
	    	 getOfferService().deleteById(id);
	         return getOfferService().list();
	    }
	    
	    private OffersService getOfferService() {
			if(request.getSession(true).getAttribute(REPO_SESSION_KEY) == null)
			{
				request.getSession(true).setAttribute(REPO_SESSION_KEY, new OffersService() );
			}
			return (OffersService)request.getSession(true).getAttribute(REPO_SESSION_KEY);
		}
	    
	    @GET
	    @Path("/shipping/countries")
	    @Produces(MediaType.APPLICATION_JSON)
		  public List<Country> countries() {
	    	 log.info("Getting countries list ");   
	    	 List<Country> toRet = new ArrayList<Country>(); 
	    	 toRet.add(new Country("Country", "IT"));
	    	 toRet.add(new Country("Country", "UK"));
	    	 toRet.add(new Country("Country", "DE"));
	    	 toRet.add(new Country("Country", "FR"));
	    	 toRet.add(new Country("Country", "ES"));
	    	 toRet.add(new Country("Country", "US"));
	    	 toRet.add(new Country("Country", "CH"));
	    	 return toRet;
	    }
}
