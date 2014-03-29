package Proximex.Jws.Resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Proximex.Jws.Common.*;

import Proximex.Jws.Common.*;

@Path("/Alert")
public class PxAlertResource {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_XML)	
	  public String GetAlert(@PathParam("id") int AlertId) {
		String result = "";
		PxWSClient WSClient = new PxWSClient();
		result = WSClient.CreateWSClient();
	    return "<?xml version=\"1.0\"?>" +  "<hello> Hello PxAlert: " + AlertId + " " + result + "</hello>";
	  }
	
	@GET
	@Path("{id}/{lutime}")
	@Produces(MediaType.TEXT_XML)	
	  public String GetDeltaAlert(@PathParam("id") int AlertId,@PathParam("lutime") String lutime) {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello PxAlert lutime: " + lutime +  "</hello>";
	  }
	
	@GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello> Hello PxAlert" + "</hello>";
	  }

	@GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello PxAlert";
	  }
	
	@GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "Hello PxAlert" + "</title>"
	        + "<body><h1>" + "Hello PxAlert" + "</body></h1>" + "</html> ";
	  }
}
