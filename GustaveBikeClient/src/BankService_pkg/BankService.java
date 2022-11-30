/**
 * BankService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package BankService_pkg;

public interface BankService extends java.rmi.Remote {
    public boolean removeFounds(int userID, long founds) throws java.rmi.RemoteException;
    public void addFounds(int userID, long founds) throws java.rmi.RemoteException;
    public long getUsersFounds(int userID) throws java.rmi.RemoteException;
}
