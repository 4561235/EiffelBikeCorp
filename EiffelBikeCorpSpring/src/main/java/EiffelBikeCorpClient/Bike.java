package EiffelBikeCorpClient;

import common.BikeInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;

public class Bike {

    private final int id;
    private final String bikeName;
    private final ArrayList<String> notes = new ArrayList<>();
    private final int price;


    public Bike(int id, String bikeName, int price) throws RemoteException {
        super();
        Objects.requireNonNull(bikeName);
        if (price < 0) throw new IllegalArgumentException("Price can't be < 0");
        this.id = id;
        this.bikeName = bikeName;
        this.price = price;
    }

    public String getName() throws RemoteException {
        return this.bikeName;
    }

    public int getPrice() throws RemoteException {
        return this.price;
    }

    public void addNote(String note){
        Objects.requireNonNull(note);
        this.notes.add(note);
    }

    public ArrayList<String> getNotes() throws RemoteException {
        return notes;
    }

    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String toString() {
        return "BikeId:" +id + " bikeName: " + bikeName + " Price:" + price + " Notes: " + notes;
    }
}
