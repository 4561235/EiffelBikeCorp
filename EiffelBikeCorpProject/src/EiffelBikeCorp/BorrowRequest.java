package EiffelBikeCorp;

import common.EiffelUserInterface;

import java.util.Objects;

public class BorrowRequest {
    private final EiffelUserInterface user;
    private final int bikeId;

    public BorrowRequest(EiffelUserInterface user, int bikeId){
        Objects.requireNonNull(user);
        this.user = user;
        this.bikeId = bikeId;
    }

    public EiffelUserInterface getUser() {
        return user;
    }

    public int getBikeId() {
        return bikeId;
    }
}
