package EiffelBikeCorp;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class EiffelBikeCorpService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        BikeStorage bikeStorage = new BikeStorage();
        bikeStorage.addBike(new Bike("A"));
        bikeStorage.addBike(new Bike("B"));
        Naming.rebind("EiffelBikeCorpService", bikeStorage);
    }
}
