package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BikeInterface extends Remote{

    public String getName() throws RemoteException;
    public int getPrice() throws RemoteException;
    public void addNote(String note) throws RemoteException;
    public String getNotes() throws RemoteException;
    public int getId() throws  RemoteException;
    public void isRented(boolean rented) throws RemoteException;
    public boolean isRented() throws RemoteException;
    public boolean wasRentedOnce() throws RemoteException;
}
