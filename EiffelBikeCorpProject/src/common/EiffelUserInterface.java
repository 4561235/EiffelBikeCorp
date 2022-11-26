package common;

import EiffelBikeCorp.Bike;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EiffelUserInterface extends Remote {
    public void borrowBike(BikeInterface bike) throws RemoteException;
    public boolean hasABike() throws RemoteException;

    public BikeInterface giveBikeBack() throws RemoteException;
}
