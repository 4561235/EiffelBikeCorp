package EiffelBikeCorpService;

import common.BikeInterface;
import common.EiffelBikeCorpAccessInterface;
import common.EiffelBikeCorpInterface;
import common.EiffelUserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public boolean rentBike(EiffelUserInterface user, int bikeId) throws RemoteException {

        if(this.bikeStorage.containsKey(bikeId)){
            BikeInterface bikeToBorrow = bikeStorage.get(bikeId);

            if(!user.hasABike() && !bikeToBorrow.isRented()){
                bikeToBorrow.isRented(true);
                user.borrowBike(bikeToBorrow);
                System.out.println("Ok borrowed");
                return false;
            }
            else if(!user.hasABike()){
                this.usersQueue.add(new BorrowRequest(user, bikeId));
                System.out.println("Added to queue");
                return true;
            }
        }

        return false;

    }

    private void tryBorrowBikeToUserInQueue() throws RemoteException {
        ArrayList<BorrowRequest> requestsToRemove = new ArrayList<>();

        this.usersQueue.forEach((BorrowRequest borrowRequest) -> {
            try {
                EiffelUserInterface user = borrowRequest.getUser();
                int bikeId = borrowRequest.getBikeId();
                if(this.bikeStorage.containsKey(bikeId) && !user.hasABike()){
                    BikeInterface bikeToBorrow = this.bikeStorage.get(bikeId);
                    bikeToBorrow.isRented(true);
                    user.borrowBike(bikeToBorrow);
                    requestsToRemove.add(borrowRequest);
                    System.out.println("Borrowed to someone in queue");
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

        requestsToRemove.forEach((BorrowRequest borrowRequest) -> this.usersQueue.remove(borrowRequest));
    }

    @Override
    public void returnBike(EiffelUserInterface user, String note) throws RemoteException {
        Objects.requireNonNull(user);
        if(user.hasABike()){
            BikeInterface bikeToReturn = user.giveBikeBack();
            bikeToReturn.addNote(user.getSurname() +" " +user.getName() +": " +note);
            bikeToReturn.isRented(false);
//            this.bikeStorage.put(bikeToReturn.getId(), bikeToReturn);
            System.out.println("Bike returned");
            tryBorrowBikeToUserInQueue();
        }
        else {
            System.out.println("User wants to return but no bike is present");
        }
    }

    @Override
    public List<String> bikesToBorrow() throws RemoteException {
//        return this.bikeStorage.entrySet().stream().map((entry) -> {
//            try {
//                return "BikeID: " +entry.getKey() +" " +entry.getValue().getNotes();
//            } catch (RemoteException e) {
//                throw new RuntimeException(e);
//            }
//        }).collect(Collectors.toList());
        return this.bikeStorage.entrySet().stream().map(Map.Entry::getValue).filter((BikeInterface bike) -> {
            try {
                return !bike.isRented();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).map((BikeInterface bike) -> {
            try {
                return "BikeID: " +bike.getId() +" " +bike.getNotes();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> bikesToBuy() throws RemoteException {
        return this.bikeStorage.entrySet().stream().map(Map.Entry::getValue).filter((BikeInterface bike) -> {
            try {
                return bike.wasRentedOnce() && !bike.isRented();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).map((BikeInterface bike) -> {
            try {
                return "BikeID: " +bike.getId() +" " +bike.getNotes();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void addBike(String name, int price) throws RemoteException {
        Objects.requireNonNull(name);
        if(price < 0) throw new IllegalArgumentException("price can't be < 0");
        Bike bike = new Bike(name, price);
        this.bikeStorage.put(bike.getId(), bike);
        this.tryBorrowBikeToUserInQueue();
    }


    @Override
    public BikeInterface removeBike(int bikeID) {
        return this.bikeStorage.remove(bikeID);
    }

    @Override
    public BikeInterface getBike(int bikeID) throws RemoteException {
        return this.bikeStorage.get(bikeID);
    }
}
