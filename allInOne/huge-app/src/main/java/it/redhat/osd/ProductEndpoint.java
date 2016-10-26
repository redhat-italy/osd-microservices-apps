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

import it.redhat.osd.model.Product;


@Path("/")
public class ProductEndpoint {
	   
		private static Logger log = Logger.getLogger(ProductEndpoint.class.getName());
	
	  
	    @Context 
		HttpServletRequest request;
	    
	    private static final String REPO_SESSION_KEY = "productRepo";
	    
	    @GET
	    @Path("/products")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Product> list() {
	    	 log.info(request.getSession(true).getId()+" Listing all products.... ");   		
	         return getProductService().list();
	    }
	    
	    @GET
	    @Path("/products/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Product retrieveById(@PathParam("id") String id) {
	    	    		
	         return getProductService().retrieveById(id);
	    }
	    
	    @POST
	    @Path("/products")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Product> save(Product toSave) throws Exception {
	    	getProductService().save(toSave);
	    	return getProductService().list();
	      
	    }
	    
	    @POST
	    @Path("/products/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Product> save(@PathParam("id") String id,Product toSave) throws Exception {
	    	getProductService().save(id,toSave);
	    	return getProductService().list();
	      
	    }
	    @DELETE
	    @Path("/products")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Product>  deleteById(Product toSave) {
	    	 
	    	getProductService().deleteById(toSave.getId());
	         return getProductService().list();
	    }
	    
	    @DELETE
	    @Path("/products/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<Product>  deleteById(@PathParam("id") String id) {
	    	 
	    	getProductService().deleteById(id);
	         return getProductService().list();
	    }
	    
	    private ProductService getProductService() {
			if(request.getSession(true).getAttribute(REPO_SESSION_KEY) == null)
			{
				request.getSession(true).setAttribute(REPO_SESSION_KEY, new ProductService() );
			}
			return (ProductService)request.getSession(true).getAttribute(REPO_SESSION_KEY);
		}
}
