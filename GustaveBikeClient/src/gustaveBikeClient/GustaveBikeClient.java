package gustaveBikeClient;

import java.rmi.RemoteException;




import javax.xml.rpc.ServiceException;

//import bankService.BankService;
//import bankService.BankServiceServiceLocator;
//import bankService.BankServiceSoapBindingStub;
import gustaveBikeService.GustaveBike;
import gustaveBikeService.GustaveBikeService;
import gustaveBikeService.GustaveBikeServiceServiceLocator;
import gustaveBikeService.GustaveBikeServiceSoapBindingStub;



public class GustaveBikeClient {
	
	public static void main(String[] args) throws ServiceException, RemoteException {
		GustaveBikeService gustaveBikeService = new GustaveBikeServiceServiceLocator().getGustaveBikeService();

		((GustaveBikeServiceSoapBindingStub) gustaveBikeService).setMaintainSession(true);
		
		GustaveBikeUser user = new GustaveBikeUser("Drago", "Malfoy");
		
		System.out.println(gustaveBikeService.sayHello());
		
		for (String bikeStr : gustaveBikeService.getBikesToBuy()) {
			System.out.println(bikeStr);
		}
		
		gustaveBikeService.addFounds(user.getId(), 200);
		
		GustaveBike gustaveBike = gustaveBikeService.buyBike(3076, user.getId(), "GBR");
		
		if(gustaveBike != null) {
			System.out.println("J'ai mon velo!!!!");
			System.out.println(gustaveBike.getBikeName());
			System.out.println(gustaveBike.getNotes());
			System.out.println(gustaveBike.getPrice());
			
			System.out.println(gustaveBikeService.getUsersFounds(user.getId()));
		}
		else {
			System.out.println("Je n'ai pas eu mon velo :(");
		}
		
		
		
	}
}
