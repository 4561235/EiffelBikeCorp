package EiffelBikeCorpClient;

import common.BikeInterface;
import common.EiffelBikeCorpInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class EiffelUsersClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        EiffelBikeCorpInterface bikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");

//        System.out.println(bikeStorage.bikesToBorrow());
//
//        EiffelUser harry = new EiffelUser("Harry", "Potter");
//        EiffelUser ron = new EiffelUser("Ron", "Weasley");
//        EiffelUser hermione = new EiffelUser("Hermione", "Granger");
//
//        bikeStorage.rentBike(harry, 3076);
//        BikeInterface harryBike = harry.getBike().get();
//
//        bikeStorage.rentBike(ron, 3207);
//        BikeInterface ronBike = ron.getBike().get();
//
//        bikeStorage.rentBike(hermione, 3076);
//
//        System.out.println(bikeStorage.bikesToBorrow());
//
//        if(harry.hasABike()) bikeStorage.returnBike(harry, "Harry: This is my bike");
//        if(ron.hasABike()) bikeStorage.returnBike(ron, "Ron: Nice bike");
//
//        System.out.println(bikeStorage.bikesToBorrow());
//
//        if(hermione.hasABike()) bikeStorage.returnBike(hermione, "Hermione: I love my bike");
//
//        System.out.println(bikeStorage.bikesToBorrow());


        Scanner scanner = new Scanner(System.in);
        System.out.println("Bonjour, quelle est votre nom ?");
        //nom
        String name = scanner.nextLine();
        System.out.println(name);
        //surnom
        System.out.println("Bonjour, quelle est votre prenom ?");
        String surname = scanner.nextLine();
        EiffelUser user = new EiffelUser(surname, name);

        System.out.println("Voici les actions possibles : ");
        System.out.println("DisplayBike (-b), Quit (-q), RentABike (-r), LeaveNote (-n)");
        try {
            while(scanner.hasNext()){
                var scan = scanner.nextLine();
                switch (scan) {
                    case "-b" :
                        System.out.println("Voici la liste des vélos disponibles : ");
                        System.out.println(bikeStorage.bikesToBorrow());
                        break;
                    case "-q" :
                        System.out.println("En revoir " + surname + " !");
                        if(user.hasABike()){
                            System.out.println("Vous aviez un vélo louer nous le reprenons");
                            System.out.println("Voulez vous laissez une note ? oui/non");
                            scan = scanner.nextLine();
                            var note = "";
                            if(scan.equals("oui")){
                                //leave a note
                                note = scanner.nextLine();
                            }
                            else {
                                System.out.println("Vous laissez une note vide");
                            }
                            //rendre le velo
                            System.out.println("Velo ID : " + user.getBike().get().getId() + " rendu !");
                            bikeStorage.returnBike(user, note);
                        }
                        scanner.close();
                        break;
                    case "-r" :
                        if(!user.hasABike()) {
                            if(!bikeStorage.bikesToBorrow().isEmpty()) {
                                //Choisie un velo
                                System.out.print("ID du vélo souhaité :");
                                var BikeID = scanner.nextInt();
                                // BikeID est dans la liste des choix ?
                                int i = 0;
                                while(i < bikeStorage.bikesToBorrow().size()){
                                    String velo = bikeStorage.bikesToBorrow().get(i);
                                    System.out.println(velo);
                                    if(velo.contains("BikeID: "+BikeID + " ")){
                                        System.out.println("Tu a louer le velo id " + BikeID);
                                        bikeStorage.rentBike(user, BikeID);
                                        user.getBike().get();
                                        break;
                                    }
                                    i++;
                                }
                                if(!user.hasABike()){
                                    System.out.println("Ton ID n'est pas correct");
                                }
                            }
                            else {
                                System.out.println("Il n'y a plus de vélo disponibles");
                            }
                        }
                        else {
                            System.out.println("Tu as déjà un vélo tu ne peux pas en louer un autre pour le moment");

                        }
                        break;
                    case "-n" :
                        if(!user.hasABike()) {
                            System.out.println("Vous ne pouvez pas laisser de note vous n'avez pas de vélo en cour de location");
                        }
                        else {
                            System.out.println("Vous rendez votre vélo laisser une note : ");
                            var note = scanner.nextLine();
                            bikeStorage.returnBike(user, note);
                        }
                        break;
                    default:
                        System.out.println("DisplayBike (-b), Quit (-q), RentABike (-r), LeaveNote (-n)");
                        break;
                }

            }
        }catch (IllegalStateException e) {
            System.out.println(e);
        }
        System.out.println("FIN PROGRAMME !");

    }

}