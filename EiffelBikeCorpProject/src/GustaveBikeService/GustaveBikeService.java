package GustaveBikeService;

import common.EiffelBikeCorpAccessInterface;
import common.EiffelBikeCorpInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class GustaveBikeService {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        EiffelBikeCorpInterface bikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");
        EiffelBikeCorpAccessInterface bikeStorageAccess = (EiffelBikeCorpAccessInterface) Naming.lookup("EiffelBikeCorpService");

        BikeStore bikeStore = new BikeStore(bikeStorage, bikeStorageAccess);

        LocateRegistry.createRegistry(1099);
        Naming.rebind("GustaveBikeService", bikeStore);
    }
}
