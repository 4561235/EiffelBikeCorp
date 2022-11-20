package EiffelStudentsAndEmployees;

import EiffelBikeCorp.Bike;
import common.BikeInterface;
import common.EiffelBikeStorageInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class EiffelUsersClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        EiffelBikeStorageInterface bikeStorage = (EiffelBikeStorageInterface) Naming.lookup("EiffelBikeCorpService");
        System.out.println(bikeStorage);
        EiffelUser harry = new EiffelUser("Harry", "Potter");
        EiffelUser ron = new EiffelUser("Ron", "Weasley");
        EiffelUser hermione = new EiffelUser("Hermione", "Granger");

        bikeStorage.rentBike(harry);
        BikeInterface harryBike = harry.getBike().get();
        harryBike.addNote("Harry: This is my bike");
        System.out.println(harryBike.getNotes());

        bikeStorage.rentBike(ron);
        BikeInterface ronBike = ron.getBike().get();
        ronBike.addNote("Ron: I like my bike");
        System.out.println(ronBike.getNotes());

        if(harry.hasABike()){
            bikeStorage.returnBike(harry.giveBikeBack().get());
        }

        bikeStorage.rentBike(hermione);
        if(hermione.getBike().isPresent()){
            BikeInterface hermioneBike = hermione.getBike().get();
            hermioneBike.addNote("Hermione: This bike is very nice");
            System.out.println(hermioneBike.getNotes());
        }else {
            System.out.println("Hermione doesn't have a bike!");
        }

        if(harry.hasABike()) bikeStorage.returnBike(harry.giveBikeBack().get());
        if(ron.hasABike()) bikeStorage.returnBike(ron.giveBikeBack().get());
        if(hermione.hasABike()) bikeStorage.returnBike(hermione.giveBikeBack().get());
    }
}
