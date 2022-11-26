package GustaveBikeService;

import common.BikeInterface;
import common.BikeStoreInterface;
import common.EiffelBikeCorpAccessInterface;
import common.EiffelBikeCorpInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Objects;

public class BikeStore extends UnicastRemoteObject implements BikeStoreInterface {

    private final EiffelBikeCorpInterface eiffelBikeStorage;
    private final EiffelBikeCorpAccessInterface eiffelBikeStorageAccess;
    protected BikeStore(EiffelBikeCorpInterface eiffelBikeStorage, EiffelBikeCorpAccessInterface eiffelBikeStorageAccess) throws RemoteException {
        super();
        Objects.requireNonNull(eiffelBikeStorage);
        Objects.requireNonNull(eiffelBikeStorageAccess);
        this.eiffelBikeStorage = eiffelBikeStorage;
        this.eiffelBikeStorageAccess = eiffelBikeStorageAccess;
    }


    @Override
    public BikeInterface buyBike(int id) {
        // TODO: 26/11/2022  
        return null;
    }

    @Override
    public HashMap<Integer, String> getBikes() {
        // TODO: 26/11/2022  
        return null;
    }
}
