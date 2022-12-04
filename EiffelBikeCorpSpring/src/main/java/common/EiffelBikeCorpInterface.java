package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EiffelBikeCorpInterface extends Remote {
    public boolean rentBike(EiffelUserInterface user, int bikeId) throws RemoteException;
    public void returnBike(EiffelUserInterface user, String note) throws RemoteException;
    public List<String> bikesToBorrow() throws RemoteException;
    public List<String> bikesToBuy() throws RemoteException;
    public void addBike(String name, int price) throws RemoteException;
}
