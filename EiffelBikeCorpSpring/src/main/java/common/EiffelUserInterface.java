package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EiffelUserInterface extends Remote {
    public void borrowBike(BikeInterface bike) throws RemoteException;
    public boolean hasABike() throws RemoteException;

    public String getSurname() throws RemoteException;
    public String getName() throws RemoteException;

    public BikeInterface giveBikeBack() throws RemoteException;
}
