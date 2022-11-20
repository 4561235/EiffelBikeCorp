package EiffelStudentsAndEmployees;

import EiffelBikeCorp.Bike;
import common.BikeInterface;
import common.EiffelUserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Optional;

public class EiffelUser extends UnicastRemoteObject implements EiffelUserInterface {

    private final String surname;
    private final String name;

    private BikeInterface bike = null;


    public EiffelUser(String surname, String name) throws RemoteException {
        super();
        Objects.requireNonNull(surname);
        Objects.requireNonNull(name);
        this.surname = surname;
        this.name = name;
    }


    @Override
    public void borrowBike(BikeInterface bike) throws RemoteException {
        Objects.requireNonNull(bike);
        if (this.hasABike()) throw new IllegalStateException("User: " +this.surname +" " +this.name +" already has a bike");
        this.bike = bike;
    }

    @Override
    public boolean hasABike() throws RemoteException {
        return this.bike != null;
    }

    public Optional<BikeInterface> getBike(){
        return Optional.ofNullable(this.bike);
    }

    public Optional<BikeInterface> giveBikeBack(){
        Optional<BikeInterface> opt = Optional.ofNullable(this.bike);
        this.bike = null;
        return opt;
    }

}
