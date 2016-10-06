package it.redhat.osd;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet(value="/echo-ha", name="echoha-servlet")
public class HAServlet extends GenericServlet {

	private static final String COUNT_VALUE = "countValue";

	    // We now want to use a session value to see if they persist across HA redirects
	    public void service(ServletRequest req, ServletResponse res)
	            throws IOException, ServletException
	    {
	        String message = System.getenv("HOSTNAME"); // This will be the pod name
	        if (req instanceof HttpServletRequest)
	        {
	            HttpServletRequest httpReq = (HttpServletRequest) req;
	            HttpSession session = httpReq.getSession();
	            Integer count = incrementCount( session );
	            message = "From session " + session.getId() + ", for the " + count + " time on pod " + message;
	        }
	        res.getWriter().println(message);
	    }

	    private Integer incrementCount(HttpSession session)
	    {
	        Integer count = (Integer)session.getAttribute(COUNT_VALUE);
	        if (count == null)
	            count = 1;
	        else
	            count = count + 1;
	        session.setAttribute(COUNT_VALUE,count);
	        return count;
	    }

}
