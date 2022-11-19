package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EiffelUserInterface extends Remote {
    public void borrowBike(Bike bike) throws RemoteException;
    public boolean hasABike() throws  RemoteException;
}
