package EiffelStudentsAndEmployees;

import common.Bike;
import common.EiffelUserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.Optional;

public class EiffelUser extends UnicastRemoteObject implements EiffelUserInterface {

    private final String surname;
    private final String name;

    private Bike bike = null;


    public EiffelUser(String surname, String name) throws RemoteException {
        super();
        Objects.requireNonNull(surname);
        Objects.requireNonNull(name);
        this.surname = surname;
        this.name = name;
    }


    @Override
    public void borrowBike(Bike bike) throws RemoteException {
        Objects.requireNonNull(bike);
        if (this.hasABike()) throw new IllegalStateException("User: " +this.surname +" " +this.name +" already has a bike");
        this.bike = bike;
    }

    @Override
    public boolean hasABike() throws RemoteException {
        return this.bike != null;
    }

    public Optional<Bike> getBike(){
        return Optional.of(this.bike);
    }
}
