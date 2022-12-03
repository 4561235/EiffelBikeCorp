package EiffelBikeCorpService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class EiffelBikeCorpService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        BikeStorage bikeStorage = new BikeStorage();
        bikeStorage.addBike("A", 100);
        bikeStorage.addBike("B", 200);
        Naming.rebind("EiffelBikeCorpService", bikeStorage);
        System.out.println("EiffelBikeCorpService a été lancé");
    }
}
