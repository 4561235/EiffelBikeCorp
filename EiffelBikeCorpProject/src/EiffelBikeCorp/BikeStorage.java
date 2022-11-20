package EiffelBikeCorp;

import common.BikeInterface;
import common.EiffelBikeStorageInterface;
import common.EiffelUserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

public class BikeStorage extends UnicastRemoteObject implements EiffelBikeStorageInterface {

    private final LinkedBlockingDeque<BikeInterface> bikeStorage = new LinkedBlockingDeque<>();
    private final LinkedBlockingDeque<EiffelUserInterface> usersQueue = new LinkedBlockingDeque<>();

    protected BikeStorage() throws RemoteException {
    }

    @Override
    public void rentBike(EiffelUserInterface user) throws RemoteException {
        BikeInterface bikeToBorrow = bikeStorage.pollFirst();
        if (bikeToBorrow != null){
            if(!user.hasABike()) user.borrowBike(bikeToBorrow);
        }
        else {
            this.usersQueue.add(user);
        }
    }

    private void tryBorrowBikeToUserInQueue() throws RemoteException {
        if(!bikeStorage.isEmpty() && !usersQueue.isEmpty()){

            EiffelUserInterface user = usersQueue.pollFirst();
            BikeInterface bikeToBorrow = bikeStorage.pollFirst();

            if(!user.hasABike()) user.borrowBike(bikeToBorrow);
        }
    }

    @Override
    public void returnBike(BikeInterface bike) throws RemoteException {
        Objects.requireNonNull(bike);
        this.bikeStorage.add(bike);
    }

    public void addBike(Bike bike){
        Objects.requireNonNull(bike);
        this.bikeStorage.add(bike);
    }




}
