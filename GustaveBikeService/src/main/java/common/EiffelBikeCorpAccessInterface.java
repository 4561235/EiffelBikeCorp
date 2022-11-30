package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EiffelBikeCorpAccessInterface extends Remote {
	
    public BikeInterface removeBike(int bikeID) throws RemoteException;
    public BikeInterface getBike(int bikeID) throws RemoteException;
}
