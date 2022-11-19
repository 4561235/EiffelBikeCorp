package EiffelBikeCorp;

import common.Bike;
import common.EiffelBikeStorageInterface;
import common.EiffelUserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

public class BikeStorage extends UnicastRemoteObject implements EiffelBikeStorageInterface {

    private final LinkedBlockingDeque<Bike> bikeStorage = new LinkedBlockingDeque<>();

    protected BikeStorage() throws RemoteException {
    }

    @Override
    public void rentBike(EiffelUserInterface user) throws RemoteException {
        // TODO: 19/11/2022
        if(!user.hasABike()) user.borrowBike(bikeStorage.getFirst());
    }

    @Override
    public void returnBike(Bike bike) throws RemoteException {
        Objects.requireNonNull(bike);
        this.bikeStorage.add(bike);
    }

    public void addBike(Bike bike){
        Objects.requireNonNull(bike);
        this.bikeStorage.add(bike);
    }
}
