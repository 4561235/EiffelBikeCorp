package GustaveBikeService;

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
	
	public String sayHello() {
		return "Hello this is GustaveBikeService";
	}
	
	public String[] getBikesToBuy() throws RemoteException {
        return this.eiffelBikeStorage.bikesToBorrow().toArray(String[]::new);
    }
	
	
//	public BikeInterface buyBike(int id) throws RemoteException {
//        // TODO: 26/11/2022  
//        return bikeStorageAccess.removeBike(id);
//    }
}
