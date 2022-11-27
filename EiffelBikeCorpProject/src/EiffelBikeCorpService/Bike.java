package EiffelBikeCorpService;

import common.BikeInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Bike extends UnicastRemoteObject implements BikeInterface {

    private final String bikeName;
    private final ArrayList<String> notes = new ArrayList<>();
    private final int price;

    private boolean isRented = false;

    public Bike(String bikeName, int price) throws RemoteException {
        super();
        Objects.requireNonNull(bikeName);
        if (price < 0) throw new IllegalArgumentException("Price can't be < 0");
        this.bikeName = bikeName;
        this.price = price;
    }
    @Override
    public void addNote(String note){
        Objects.requireNonNull(note);
        this.notes.add(note);
    }

    @Override
    public String getNotes() throws RemoteException {
        return this.toString();
    }

    @Override
    public int getId() throws RemoteException {
        return Math.abs(Objects.hash(this.bikeName, this.price));
    }

    @Override
    public boolean isRented() throws RemoteException{
        return isRented;
    }

    @Override
    public void isRented(boolean rented) throws RemoteException{
        isRented = rented;
    }

    @Override
    public String toString() {
        return "Bike: " +this.bikeName +" Price: " +this.price +", notes: " +notes;
    }
}
