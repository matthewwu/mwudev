
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

public class PxWSClient {

	
	public void CreateWSClient()
	{
		try
		{
			QName serviceName = new QName("http://ws.proximex.com/");
			URL wsdlLocation = new URL("http://localhost/ProximexWS/ProximexWcfWS.svc/basic?wsdl");
			
			ServiceFactory factory = ServiceFactory.newInstance();
			Service service = factory.createService(wsdlLocation,serviceName);
			
			HandlerRegistry hr = service.getHandlerRegistry();
			HandlerInfo hi = new HandlerInfo();
			hi.setHandlerClass(SimpleHandler.class);
			
			QName  portName = new QName("http://localhost/ProximexWS/ProximexWcfWS.svc/basic?wsdl", "BasicHttpBinding_AuthService");
			List handlerChain = hr.getHandlerChain(portName);
			  
			handlerChain.add(hi);
			
			QName operationName = new QName("http://localhost/ProximexWS/ProximexWcfWS.svc/basic?wsdl", "Logon");
			  Call call = service.createCall(portName,operationName);
			  
			  //call the operation
			  Object resp = call.invoke(new java.lang.Object[] {"login","pass"});
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		
		
	}
}
