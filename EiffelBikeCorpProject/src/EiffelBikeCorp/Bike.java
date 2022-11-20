package EiffelBikeCorp;

import common.BikeInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Bike extends UnicastRemoteObject implements BikeInterface {

    private final String bikeName;
    private final ArrayList<String> notes = new ArrayList<>();

    public Bike(String bikeName) throws RemoteException {
        super();
        Objects.requireNonNull(bikeName);
        this.bikeName = bikeName;
    }
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
        return Math.abs(Objects.hash(this.bikeName));
    }

    @Override
    public String toString() {
        return "Bike " +this.bikeName +", notes: " +notes;
    }
}
