package common;

import EiffelBikeCorp.Bike;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EiffelBikeStorageInterface extends Remote {
    public void rentBike(EiffelUserInterface user, int bikeId) throws RemoteException;
    public void returnBike(BikeInterface bike) throws RemoteException;
    public List<String> bikesToBorrow() throws RemoteException;
}
