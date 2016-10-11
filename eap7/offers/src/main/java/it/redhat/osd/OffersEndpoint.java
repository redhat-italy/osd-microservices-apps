package it.redhat.osd;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.redhat.osd.model.Offer;

@Path("/api")
public class OffersEndpoint {
	   
	    @Inject
	    OffersService offersService;

	
	    @GET
	    @Path("/offers")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> list() {
	    	    		
	         return offersService.list();
	    }
	    
	    @GET
	    @Path("/offers/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Offer retrieveById(@PathParam("id") String id) {
	    	    		
	         return offersService.retrieveById(id);
	    }
	    
	    @POST
	    @Path("/offers")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> save(Offer toSave) throws Exception {
	    	offersService.save(toSave);
	    	return offersService.list();
	      
	    }
	    
	    @POST
	    @Path("/offers/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer> save(@PathParam("id") String id,Offer toSave) throws Exception {
	    	offersService.save(id,toSave);
	    	return offersService.list();
	      
	    }
	    @DELETE
	    @Path("/offers")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer>  deleteById(Offer toSave) {
	    	 
	    	 offersService.deleteById(toSave.getId());
	         return offersService.list();
	    }
	    
	    @DELETE
	    @Path("/offers/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Offer>  deleteById(@PathParam("id") String id) {
	    	 
	    	 offersService.deleteById(id);
	         return offersService.list();
	    }
}
