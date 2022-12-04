package EiffelBikeCorpClient.form;

import javax.validation.constraints.NotNull;
import java.util.List;

public class FormRent {

    private List<Integer> bikesIDs;

    @NotNull
    private int bikeID;

    public List<Integer> getBikesIDs() {
        return bikesIDs;
    }

    public void setBikesIDs(List<Integer> bikesIDs) {
        this.bikesIDs = bikesIDs;
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }
}
