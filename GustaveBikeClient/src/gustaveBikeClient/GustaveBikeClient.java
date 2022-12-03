package gustaveBikeClient;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Scanner;

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
		
//		GustaveBikeUser user = new GustaveBikeUser("Drago", "Malfoy");
//		
//		System.out.println(gustaveBikeService.sayHello());
//		
//		System.out.println("Bikes in store:");
//		for (String bikeStr : gustaveBikeService.getBikesToBuy()) {
//			System.out.println(bikeStr);
//		}
//		
//		gustaveBikeService.addFounds(user.getId(), 500);
//		
//		gustaveBikeService.addToCard(user.getId(), 3076);
//		gustaveBikeService.addToCard(user.getId(), 3207);
//		
//		System.out.println("Mon panier");
//		for (String cardItem : gustaveBikeService.getCard(user.getId())) {
//			System.out.println(cardItem);
//		}
//		
//		System.out.println("J'enleve du panier");
//		gustaveBikeService.removeFromCard(user.getId(), 3076);
//		
//		System.out.println("Mon panier");
//		for (String cardItem : gustaveBikeService.getCard(user.getId())) {
//			System.out.println(cardItem);
//		}
//		
//		GustaveBike[] bikeArr = gustaveBikeService.payBikesInCard(user.getId(), "FRA");
//		for (GustaveBike gustaveBike : bikeArr) {
//			System.out.println("J'ai mon velo acheté grace au panier!!!!");
//			System.out.println(gustaveBike.getNotes());
//		}
//		
//		System.out.println(gustaveBikeService.listCurrencies());
//		
//		
//		
//		GustaveBike gustaveBike = gustaveBikeService.buyBike(3076, user.getId(), "FRA");
//		
//		
//		if(gustaveBike != null) {
//			System.out.println("J'ai mon velo acheter normalement!!!!");
//			System.out.println(gustaveBike.getBikeName());
//			System.out.println(gustaveBike.getNotes());
//			System.out.println(gustaveBike.getPrice());
//			
//			System.out.println(gustaveBikeService.getUsersFounds(user.getId()));
//		}
//		else {
//			System.out.println("Je n'ai pas eu mon velo :(");
//		}
		//Initialisation
		Scanner scanner = new Scanner(System.in);
        //System.out.println("Bonjour, voici les services disponibles !");
        System.out.println(gustaveBikeService.sayHello());
        System.out.println("Bonjour, quelle est votre nom ?");
        //nom
        String name = scanner.nextLine();
        System.out.println(name);
        //surnom
        System.out.println("Bonjour, quelle est votre prenom ?");
        String surname = scanner.nextLine();
        GustaveBikeUser user = new GustaveBikeUser(surname, name);
        
        
        System.out.println("Liste des choix : \n"
        		+ "1) Liste des vélos à acheter disponible (-listBikes) \n"
        		+ "2) Acheter un vélo dans les moyens du client (-buy) \n"
        		+ "3) Crediter un compte utilisateur(-credit) et le crée si il n'existe pas\n"
        		+ "4) Consulter les fonds disponibles des different utilisateur (-compte)\n"
        		+ "5) Ajouter un vélo sur la carte d'un user (-add)\n"
        		+ "6) Liste de toutes les monnaies disponibles (-currencies)\n"
        		+ "7) Acheter tout les vélos avec la carte et renvois la liste des vélo achetés (-buyAll)\n"
        		+ "8) Retire un vélo d'une carte (-remove) \n"
        		+ "9) Récuperer la carte d'un user (-getCard) \n"
        		);
        
        
        // Action sur terminale 
        try {
            while(scanner.hasNext()){
                String scan = scanner.nextLine();
                // 1)
                if(scan.equals("-listBikes")) {
                	System.out.println("Bikes in store:");
                	String[] list = gustaveBikeService.getBikesToBuy();
                	if(list==null) {
                		System.out.println("Désolé mais il n'y a aucun vélos disponibles");
                	}
                	else {
                		for (String bikeStr : list) {
	            			System.out.println(bikeStr);
	            		}
                	}
	           
                }
                //2)
                else if(scan.equals("-buy")) {
                	System.out.println("Bikes in store:");
                	String[] list = gustaveBikeService.getBikesToBuy();
                	if(list==null) {
                		System.out.println("Désolé mais il n'y a pas de vélo disponible");
                	}
                	else {
                		for (String bikeStr : list) {
	            			System.out.println(bikeStr);
	            		}
                		System.out.print("ID de vélo choisie ? : ");
                		int id = scanner.nextInt();
//                		System.out.print("\nID de l'user ? : ");
//                		int user = scanner.nextInt();
                		System.out.println("\n"+gustaveBikeService.listCurrencies());
                		System.out.print("\nMonnaie utiliser  ? : ");
                		String currency = scanner.nextLine();
                		
                		if(gustaveBikeService.buyBike(id, user.getId(), currency)!=null) {
                			System.out.println("\nMerci pour votre achat ! id-velo : " + id + " id-user : " + user.getId() + " currency = " + currency );
                		}
                		else {
                			System.out.println("Désoler mais vous n'avez pas assez de fonds sur votre carte");
                			
                		}
                		
                	}
                }
                // 3) 
                else if(scan.equals("-credit")) {
//                	System.out.print("ID de l'user ? : ");
//            		int user = scanner.nextInt();
            		System.out.print("Combien voulez vous ajouter ? : ");
            		long founds = scanner.nextLong();
            		//si le user n'est pas existant la banque le crée
            		gustaveBikeService.addFounds(user.getId(), founds);
                }
                //4)
                else if(scan.equals("-compte")) {
//                	System.out.print("ID de l'user ? : ");
//            		int user = scanner.nextInt();
                	System.out.println("\n"+gustaveBikeService.getUsersFounds(user.getId()));
                }
                //5)
                else if(scan.equals("-add")) {
//                	System.out.print("ID de l'user ? : ");
//            		int user = scanner.nextInt();
            		System.out.print("ID de la bike ? : ");
            		int idbike = scanner.nextInt();
            		
            		if(gustaveBikeService.addToCard(user.getId(), idbike)) System.out.println("\nAjout à été un succès sur la carte du client >" + user.getId());
            		else System.out.println("\nEchec de l'ajout du vélo id > " + idbike);
            			
                }
                //6)
                else if(scan.equals("-currencies")) System.out.println(gustaveBikeService.listCurrencies());
                
                //7)
                else if(scan.equals("-buyAll")) {
//                	System.out.print("ID de l'user ? : ");
//            		int user = scanner.nextInt();
            		System.out.println("\n"+gustaveBikeService.listCurrencies());
            		System.out.print("\nMonnaie utiliser  ? : ");
            		String currency = scanner.nextLine();
            		GustaveBike[] list = gustaveBikeService.payBikesInCard(user.getId(), currency);
            		if(gustaveBikeService.payBikesInCard(user.getId(), currency)!=null) {
            			System.out.print("Voici les vélos que vous avez acheter : " );
            			for (GustaveBike bike : list) {
	            			System.out.println(bike.getBikeName());
	            		}
            			
            		}
            		else {
            			System.out.println("Aucun vélo n'a été acheter, ils sont toujours dans votre cadie");
            		}
                }
                //8)
                else if(scan.equals("-remove")) {
//                	System.out.print("ID de l'user ? : ");
//            		int user = scanner.nextInt();
            		System.out.print("ID de la bike ? : ");
            		int idbike = scanner.nextInt();
            		
            		boolean removed = gustaveBikeService.removeFromCard(user.getId(), idbike);
            		if(removed) {
            			System.out.println("id User > "+ user +", vous avez retirer de votre panier le vélo id > " + idbike);
            		}
            		else {
            			System.out.println("Le vélo n'a pas été trouver dans votre panier ou vous n'avez pas de carte ici");
            		}
                }
                //9)
                else if(scan.equals("-getCard")) {
//                	System.out.print("ID de l'user ? : ");
//            		int user = scanner.nextInt();
            		String[] card = gustaveBikeService.getCard(user.getId());
            		if(card == null) {
            			System.out.println("Il n'y a pas de vélo dans votre panier");
            		}
            		else {
            			for (String bikeStr : card) {
	            			System.out.println(bikeStr);
	            		}
            		}
                }
                
                
            }
        }catch (IllegalStateException e) {
            System.out.println(e);
        }
        System.out.println("FIN PROGRAMME !");
		
	}
}
