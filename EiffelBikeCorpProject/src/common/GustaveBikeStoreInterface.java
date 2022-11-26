package common;

import java.util.HashMap;

public interface GustaveBikeStoreInterface {

    public BikeInterface buyBike(int id);
    public HashMap<Integer,String> getBikes();
}
