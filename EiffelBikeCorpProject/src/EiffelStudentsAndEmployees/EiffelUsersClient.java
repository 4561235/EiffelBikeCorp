package EiffelStudentsAndEmployees;

import common.EiffelBikeStorageInterface;
import common.EiffelUserInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EiffelUsersClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        EiffelBikeStorageInterface bikeStorage = (EiffelBikeStorageInterface) Naming.lookup("EiffelBikeCorpService");
        System.out.println(bikeStorage);
    }
}
