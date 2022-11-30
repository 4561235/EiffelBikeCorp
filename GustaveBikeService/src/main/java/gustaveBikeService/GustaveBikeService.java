package gustaveBikeService;

import java.net.MalformedURLException;


import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

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
	
	public GustaveBikeService() throws MalformedURLException, RemoteException, NotBoundException, ServiceException {
		this.eiffelBikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");
		this.bikeStorageAccess = (EiffelBikeCorpAccessInterface) Naming.lookup("EiffelBikeCorpService");
		this.bankService = new BankServiceServiceLocator().getBankService();
		((BankServiceSoapBindingStub) bankService).setMaintainSession(true);
	}
	
	public String[] getBikesToBuy() throws RemoteException {
		List<String> bikesList = this.eiffelBikeStorage.bikesToBorrow();
		String[] tab = new String[bikesList.size()];
		for (int i = 0; i < bikesList.size(); i++) {
			tab[i] = bikesList.get(i);
		}
		return tab;
    }
	
	public String sayHello() {
		return "Hello this is GustaveBikeService";
	}
	
	
	public GustaveBike buyBike(int bikeID, int userID) throws RemoteException {
        BikeInterface bike = bikeStorageAccess.removeBike(bikeID);
        if(this.bankService.removeFounds(userID, bike.getPrice())) {
        	return new GustaveBike(bike.getName(), bike.getNotes(), bike.getPrice());
        }
        else {
        	return null;
        }
    }
	
	public void addFounds(int userID, long founds) throws RemoteException {
		this.bankService.addFounds(userID, founds);
	}
	
	public long getUsersFounds(int userID) throws RemoteException {
		return this.bankService.getUsersFounds(userID);
	}
	
	
}
