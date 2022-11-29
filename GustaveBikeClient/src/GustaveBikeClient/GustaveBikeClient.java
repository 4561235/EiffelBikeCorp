package GustaveBikeClient;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import GustaveBikeService_pkg.GustaveBikeService;
import GustaveBikeService_pkg.GustaveBikeServiceServiceLocator;
import GustaveBikeService_pkg.GustaveBikeServiceSoapBindingStub;

public class GustaveBikeClient {
	
	public static void main(String[] args) throws ServiceException, RemoteException {
		GustaveBikeService service = new GustaveBikeServiceServiceLocator().getGustaveBikeService();
		((GustaveBikeServiceSoapBindingStub) service).setMaintainSession(true);
		System.out.println(service.sayHello());
		System.out.println(service.helloWorld());
		
		for (String bikeStr : service.getBikesToBuy()) {
			System.out.println(bikeStr);
		}
		
	}
}
