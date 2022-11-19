package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EiffelBikeStorageInterface extends Remote {
    public void rentBike(EiffelUserInterface user) throws RemoteException;
    public void returnBike(Bike bike) throws RemoteException;
}
