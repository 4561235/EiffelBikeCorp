package EiffelBikeCorpService;

import common.BikeInterface;
import common.EiffelBikeCorpAccessInterface;
import common.EiffelBikeCorpInterface;
import common.EiffelUserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

public class BikeStorage extends UnicastRemoteObject implements EiffelBikeCorpInterface, EiffelBikeCorpAccessInterface {

    private final ConcurrentHashMap<Integer, BikeInterface> bikeStorage = new ConcurrentHashMap<>();
    private final LinkedBlockingDeque<BorrowRequest> usersQueue = new LinkedBlockingDeque<>();

    protected BikeStorage() throws RemoteException {
    }

    @Override
    public void rentBike(EiffelUserInterface user, int bikeId) throws RemoteException {
        if(this.bikeStorage.containsKey(bikeId)){
            if(!user.hasABike()){
                BikeInterface bikeToBorrow = bikeStorage.remove(bikeId);
                user.borrowBike(bikeToBorrow);
                System.out.println("Ok borrowed");
            }
            else {
                this.usersQueue.add(new BorrowRequest(user, bikeId));
                System.out.println("Added to queue");
            }
        }
    }

    private void tryBorrowBikeToUserInQueue() throws RemoteException {

        this.usersQueue.forEach((BorrowRequest borrowRequest) -> {
            try {
                EiffelUserInterface user = borrowRequest.getUser();
                int bikeId = borrowRequest.getBikeId();
                if(this.bikeStorage.containsKey(bikeId) && !user.hasABike()){
                    user.borrowBike(this.bikeStorage.remove(bikeId));
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void returnBike(EiffelUserInterface user) throws RemoteException {
        Objects.requireNonNull(user);
        if(user.hasABike()){
            BikeInterface bikeToReturn = user.giveBikeBack();
            this.bikeStorage.put(bikeToReturn.getId(), bikeToReturn);
            tryBorrowBikeToUserInQueue();
            System.out.println("Bike returned");
        }
    }

    @Override
    public List<String> bikesToBorrow() throws RemoteException {
        return this.bikeStorage.entrySet().stream().map((entry) -> {
            try {
                return "BikeID: " +entry.getKey() +" " +entry.getValue().getNotes();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public void addBike(BikeInterface bike) throws RemoteException {
        Objects.requireNonNull(bike);
        this.bikeStorage.put(bike.getId(), bike);
        this.tryBorrowBikeToUserInQueue();
    }


    @Override
    public BikeInterface removeBike(int bikeID) {
        return this.bikeStorage.remove(bikeID);
    }
}