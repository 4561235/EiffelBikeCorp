package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BikeInterface extends Remote {

    public void addNote(String note) throws RemoteException;
    public String getNotes() throws RemoteException;

    public int getId() throws  RemoteException;
}
