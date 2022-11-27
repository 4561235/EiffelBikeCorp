package EiffelBikeCorpClient;

import common.BikeInterface;
import common.EiffelBikeCorpInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EiffelUsersClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        EiffelBikeCorpInterface bikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");

        System.out.println(bikeStorage.bikesToBorrow());

        EiffelUser harry = new EiffelUser("Harry", "Potter");
        EiffelUser ron = new EiffelUser("Ron", "Weasley");
        EiffelUser hermione = new EiffelUser("Hermione", "Granger");

        bikeStorage.rentBike(harry, 3076);
        BikeInterface harryBike = harry.getBike().get();

        bikeStorage.rentBike(ron, 3207);
        BikeInterface ronBike = ron.getBike().get();

        bikeStorage.rentBike(hermione, 3076);

        System.out.println(bikeStorage.bikesToBorrow());

        if(harry.hasABike()) bikeStorage.returnBike(harry, "Harry: This is my bike");
        if(ron.hasABike()) bikeStorage.returnBike(ron, "Ron: Nice bike");

        System.out.println(bikeStorage.bikesToBorrow());

        if(hermione.hasABike()) bikeStorage.returnBike(hermione, "Hermione: I love my bike");

        System.out.println(bikeStorage.bikesToBorrow());
    }
}
