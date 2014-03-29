import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPMessage;

import org.apache.http.HttpResponse;


import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;


public class SimpleHandler extends GenericHandler {

	HandlerInfo hi;
	 
	  public void init(HandlerInfo info) {
	    hi = info;
	  }

	  public QName[] getHeaders() {
	    return hi.getHeaders();
	  }

	  public boolean handleResponse(MessageContext context) {
	    try {
	     
	     //Iterate over all properties - did not find the cookie there :(
	     Iterator properties = context.getPropertyNames();
	        while(properties.hasNext()){
	         Object property = properties.next();
	         System.out.println(property.toString());
	        }
	        
	      //examine the response header - did not find the cookie there either :( 
	      if(context.containsProperty("response")){
	       Object response = context.getProperty("response");
	       HttpResponse httpResponse = (HttpResponse)response;
	       
	       /*
	       Header[] headers = httpResponse.getAllHeaders();
	       for(Header header:headers){
	        System.out.println(header.toString());
	       }
	       */
	      }
	     
	     //here is how to get the SOAP headers - they do not serve - we need pure HTTP response
	      // get the soap header
	      SOAPMessageContext smc = (SOAPMessageContext) context;
	      SOAPMessage message = smc.getMessage();
	      
	    } catch (Exception e) {
	      throw new JAXRPCException(e);
	    }
	    return true;
	  }
	  public boolean handleRequest(MessageContext context) { 
	    return true;
	  }

}
