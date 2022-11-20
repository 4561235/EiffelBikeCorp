package common;

import java.util.HashMap;
import java.util.List;

public interface BikeStoreInterface {

    public BikeInterface buyBike(int id);
    public HashMap<Integer,String> getBikes();
}
