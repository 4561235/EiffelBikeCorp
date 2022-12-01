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
		
		System.out.println("Bikes in store:");
		for (String bikeStr : gustaveBikeService.getBikesToBuy()) {
			System.out.println(bikeStr);
		}
		
		System.out.println("Mon panier");
		for (String cardItem : gustaveBikeService.getCard(user.getId())) {
			System.out.println(cardItem);
		}
		
		gustaveBikeService.addFounds(user.getId(), 500);
		
		gustaveBikeService.addToCard(user.getId(), 3076);
		gustaveBikeService.addToCard(user.getId(), 3207);
		
		System.out.println("Mon panier");
		for (String cardItem : gustaveBikeService.getCard(user.getId())) {
			System.out.println(cardItem);
		}
		
		System.out.println("J'enleve du panier");
		gustaveBikeService.removeFromCard(user.getId(), 3076);
		
		System.out.println("Mon panier");
		for (String cardItem : gustaveBikeService.getCard(user.getId())) {
			System.out.println(cardItem);
		}
		
		GustaveBike[] bikeArr = gustaveBikeService.payBikesInCard(user.getId(), "FRA");
		for (GustaveBike gustaveBike : bikeArr) {
			System.out.println("J'ai mon velo acheté grace au panier!!!!");
			System.out.println(gustaveBike.getNotes());
		}
		
		System.out.println(gustaveBikeService.listCurrencies());
		
		
		
		GustaveBike gustaveBike = gustaveBikeService.buyBike(3076, user.getId(), "FRA");
		
		
		if(gustaveBike != null) {
			System.out.println("J'ai mon velo acheter normalement!!!!");
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
