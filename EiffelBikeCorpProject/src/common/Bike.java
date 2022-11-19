package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Bike implements Serializable {

    private final ArrayList<String> notes = new ArrayList<>();

    public void addNote(String note){
        Objects.requireNonNull(note);
        this.notes.add(note);
    }

    @Override
    public String toString() {
        return "Bike, notes: " +notes;
    }
}
