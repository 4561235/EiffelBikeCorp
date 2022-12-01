/**
 * GustaveBikeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gustaveBikeService;

public interface GustaveBikeService extends java.rmi.Remote {
    public java.lang.String[] getBikesToBuy() throws java.rmi.RemoteException;
    public java.lang.String sayHello() throws java.rmi.RemoteException;
    public gustaveBikeService.GustaveBike buyBike(int bikeID, int userID, java.lang.String currencyType) throws java.rmi.RemoteException;
    public java.lang.String listCurrencies() throws java.rmi.RemoteException;
    public void addFounds(int userID, long founds) throws java.rmi.RemoteException;
    public long getUsersFounds(int userID) throws java.rmi.RemoteException;
    public boolean addToCard(int userID, int bikeID) throws java.rmi.RemoteException;
    public gustaveBikeService.GustaveBike[] payBikesInCard(int userID, java.lang.String currencyType) throws java.rmi.RemoteException;
    public boolean removeFromCard(int userID, int bikeID) throws java.rmi.RemoteException;
    public java.lang.String[] getCard(int userID) throws java.rmi.RemoteException;
}
