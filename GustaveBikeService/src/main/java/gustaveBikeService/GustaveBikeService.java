package gustaveBikeService;

import java.net.MalformedURLException;



import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;
import FxtopAPI.FxtopServicesLocator;
import FxtopAPI.FxtopServicesPortType;
import bankService.BankService;
import bankService.BankServiceServiceLocator;
import bankService.BankServiceSoapBindingStub;
import common.BikeInterface;
import common.EiffelBikeCorpAccessInterface;
import common.EiffelBikeCorpInterface;


public class GustaveBikeService {
	
	private final EiffelBikeCorpInterface eiffelBikeStorage;
	private final EiffelBikeCorpAccessInterface bikeStorageAccess;
	private final BankService bankService;
	private final FxtopServicesPortType fxtopServices;
	
	private final HashMap<Integer, List<BikeInterface>> card;
	
	public GustaveBikeService() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		this.eiffelBikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");
		this.bikeStorageAccess = (EiffelBikeCorpAccessInterface) Naming.lookup("EiffelBikeCorpService");
		this.fxtopServices = new FxtopServicesLocator().getFxtopServicesPort();
		this.bankService = new BankServiceServiceLocator().getBankService();
		((BankServiceSoapBindingStub) bankService).setMaintainSession(true);
		
		this.card = new HashMap<>();
	}
	
	public String[] getBikesToBuy() throws RemoteException {
		List<String> bikesList = this.eiffelBikeStorage.bikesToBuy();
		String[] tab = new String[bikesList.size()];
		for (int i = 0; i < bikesList.size(); i++) {
			tab[i] = bikesList.get(i);
		}
		return tab;
    }
	
	public String sayHello() {
		return "Hello this is GustaveBikeService";
	}
	
	
	public GustaveBike buyBike(int bikeID, int userID, String currencyType) throws RemoteException {
        BikeInterface bike = bikeStorageAccess.removeBike(bikeID);
        
        String priceBike = fxtopServices.convert(String.valueOf(bike.getPrice()), "FRA", currencyType, null, null, null).getResultAmount();
        
        if(this.bankService.removeFounds(userID, Long.valueOf(priceBike))) {
        	return new GustaveBike(bike.getName(), bike.getNotes(), bike.getPrice());
        }
        else {
        	return null;
        }
    }
	
	public String listCurrencies() throws RemoteException {
		return this.fxtopServices.listCurrencies(null, null);
	}
	
	public void addFounds(int userID, long founds) throws RemoteException {
		this.bankService.addFounds(userID, founds);
	}
	
	public long getUsersFounds(int userID) throws RemoteException {
		return this.bankService.getUsersFounds(userID);
	}
	
	public boolean addToCard(int userID, int bikeID) throws RemoteException {
		if(this.card.containsKey(userID)) {
			BikeInterface bike = this.bikeStorageAccess.getBike(bikeID);
			if(bike != null) {
				List<BikeInterface> list = this.card.get(userID);
				if(!list.contains(bike)) {
					list.add(bike);
				}
				return true;
			}
			else {
				return false;
			}
			
		}else {
			List<BikeInterface> list = new ArrayList<BikeInterface>();
			this.card.put(userID, list);
			
			BikeInterface bike = this.bikeStorageAccess.getBike(bikeID);
			if(bike != null) {
				list.add(bike);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public GustaveBike[] payBikesInCard(int userID, String currencyType) throws RemoteException {
		ArrayList<GustaveBike> bikesBought = new ArrayList<GustaveBike>();
		
		if(this.card.containsKey(userID)) {
			List<BikeInterface> listToBuy = this.card.get(userID);
			for (int i = 0; i < listToBuy.size(); i++) {
				GustaveBike bikeBought = this.buyBike(listToBuy.get(i).getId(), userID, currencyType);
				bikesBought.add(bikeBought);
			}
		}
		
		GustaveBike[] arr = new GustaveBike[bikesBought.size()];
		for (int i = 0; i < bikesBought.size(); i++) {
			arr[i] = bikesBought.get(i);
		}
		
		return arr;
	}
	
	public boolean removeFromCard(int userID, int bikeID) {
		return false;
	}
	
	
}
