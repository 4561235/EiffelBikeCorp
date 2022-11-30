package gustaveBikeService;

import java.net.MalformedURLException;


import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import common.BikeInterface;
import common.EiffelBikeCorpAccessInterface;
import common.EiffelBikeCorpInterface;


public class GustaveBikeService {
	
	private final EiffelBikeCorpInterface eiffelBikeStorage;
	private final EiffelBikeCorpAccessInterface bikeStorageAccess;
	
	public GustaveBikeService() throws MalformedURLException, RemoteException, NotBoundException {
		this.eiffelBikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");
		this.bikeStorageAccess = (EiffelBikeCorpAccessInterface) Naming.lookup("EiffelBikeCorpService");
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
        return new GustaveBike(bike.getName(), bike.getNotes(), bike.getPrice());
    }
}
