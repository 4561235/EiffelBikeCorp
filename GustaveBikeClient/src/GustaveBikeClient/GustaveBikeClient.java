package GustaveBikeClient;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import GustaveBikeService_pkg.GustaveBikeService;
import GustaveBikeService_pkg.GustaveBikeServiceServiceLocator;

public class GustaveBikeClient {
	
	public static void main(String[] args) throws ServiceException, RemoteException {
		GustaveBikeService service = new GustaveBikeServiceServiceLocator().getGustaveBikeService();
		System.out.println(service.sayHello());
	}
}
