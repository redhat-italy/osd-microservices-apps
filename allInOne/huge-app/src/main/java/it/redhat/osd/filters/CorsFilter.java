package it.redhat.osd.filters;


	import java.io.IOException;
	import java.util.logging.Logger;

	import javax.ws.rs.container.ContainerRequestContext;
	import javax.ws.rs.container.ContainerResponseContext;
	import javax.ws.rs.container.ContainerResponseFilter;
	import javax.ws.rs.ext.Provider;

	@Provider 
	public class CorsFilter implements ContainerResponseFilter {
		private static Logger log = Logger.getLogger(CorsFilter.class.getName());
		
		
	    public CorsFilter() {
	    	log.fine("CorsResponseFilter init");
	    }

	    @Override
	    public void filter(ContainerRequestContext req,
	            ContainerResponseContext resp) throws IOException {
	        resp.getHeaders().add("Access-Control-Allow-Origin", "*");
	        resp.getHeaders().add("Access-Control-Allow-Credentials", "true");
	        resp.getHeaders().add("Access-Control-Allow-Methods",
	                "GET, POST, DELETE, PUT");
	        resp.getHeaders().add("Access-Control-Allow-Headers",
	                "Content-Type, Accept");
	    }

}
