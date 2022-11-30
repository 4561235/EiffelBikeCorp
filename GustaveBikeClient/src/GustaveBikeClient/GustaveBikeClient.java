package GustaveBikeClient;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import BankService_pkg.BankService;
import BankService_pkg.BankServiceServiceLocator;
import BankService_pkg.BankServiceSoapBindingStub;
import GustaveBikeService_pkg.GustaveBikeService;
import GustaveBikeService_pkg.GustaveBikeServiceServiceLocator;
import GustaveBikeService_pkg.GustaveBikeServiceSoapBindingStub;

public class GustaveBikeClient {
	
	public static void main(String[] args) throws ServiceException, RemoteException {
		GustaveBikeService gustaveBikeService = new GustaveBikeServiceServiceLocator().getGustaveBikeService();
		BankService bankService = new BankServiceServiceLocator().getBankService();
		
		((BankServiceSoapBindingStub) bankService).setMaintainSession(true);
		
		
		((GustaveBikeServiceSoapBindingStub) gustaveBikeService).setMaintainSession(true);
		
		
		
		GustaveBikeUser user = new GustaveBikeUser("Drago", "Malfoy");
		
//		System.out.println(gustaveBikeService.sayHello());
//		
//		for (String bikeStr : gustaveBikeService.getBikesToBuy()) {
//			System.out.println(bikeStr);
//		}
		
		bankService.addFounds(user.getId(), 200);
		System.out.println(bankService.getUsersFounds(user.getId()));
		bankService.addFounds(user.getId(), 200);
		System.out.println(bankService.getUsersFounds(user.getId()));
		bankService.removeFounds(user.getId(), 69);
		System.out.println(bankService.getUsersFounds(user.getId()));
		
	}
}
