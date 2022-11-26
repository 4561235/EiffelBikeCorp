package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EiffelBikeCorpInterface extends Remote {
    public void rentBike(EiffelUserInterface user, int bikeId) throws RemoteException;
    public void returnBike(EiffelUserInterface user) throws RemoteException;
    public List<String> bikesToBorrow() throws RemoteException;
}
