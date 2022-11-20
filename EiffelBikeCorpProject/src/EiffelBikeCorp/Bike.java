package EiffelBikeCorp;

import common.BikeInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Bike extends UnicastRemoteObject implements BikeInterface {

    private final ArrayList<String> notes = new ArrayList<>();

    protected Bike() throws RemoteException {
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
    public String toString() {
        return "Bike, notes: " +notes;
    }
}
