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

import it.redhat.osd.model.User;

@Path("/")
public class UsersEndpoint {
	   
		private static Logger log = Logger.getLogger(UsersEndpoint.class.getName());
	
	  
	    @Context 
		HttpServletRequest request;
	    
	    private static final String REPO_SESSION_KEY = "userRepo";
	    
	    @GET
	    @Path("/users")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<User> list() {
	    	 log.info(request.getSession(true).getId()+" Listing all Users.... ");   		
	         return getUserService().list();
	    }
	    
	    @GET
	    @Path("/users/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public User retrieveById(@PathParam("id") String id) {
	    	    		
	         return getUserService().retrieveById(id);
	    }
	    
	    @POST
	    @Path("/users")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<User> save(User toSave) throws Exception {
	    	getUserService().save(toSave);
	    	return getUserService().list();
	      
	    }
	    
	    @POST
	    @Path("/users/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<User> save(@PathParam("id") String id,User toSave) throws Exception {
	    	getUserService().save(id,toSave);
	    	return getUserService().list();
	      
	    }
	    @DELETE
	    @Path("/users")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<User>  deleteById(User toSave) {
	    	 
	    	 getUserService().deleteById(toSave.getId());
	         return getUserService().list();
	    }
	    
	    @DELETE
	    @Path("/users/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<User>  deleteById(@PathParam("id") String id) {
	    	 
	    	 getUserService().deleteById(id);
	         return getUserService().list();
	    }
	    
	    private UsersService getUserService() {
			if(request.getSession(true).getAttribute(REPO_SESSION_KEY) == null)
			{
				request.getSession(true).setAttribute(REPO_SESSION_KEY, new UsersService() );
			}
			return (UsersService)request.getSession(true).getAttribute(REPO_SESSION_KEY);
		}
}
