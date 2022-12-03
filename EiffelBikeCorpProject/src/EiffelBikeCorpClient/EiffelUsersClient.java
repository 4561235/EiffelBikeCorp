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
        System.out.println("Consulter les vélos disponibles (-b), Rendre le vélo (-ret), Louer un vélo (-rent), Consulter les notes du vélo (-notes)");
        try {
            while(scanner.hasNext()){
                var scan = scanner.nextLine();
                switch (scan) {
                    case "-b" :
                        System.out.println("Voici la liste des vélos disponibles : ");
                        bikeStorage.bikesToBorrow().forEach((String b) -> System.out.println(b));
                        break;
                    case "-ret" :
                        if(user.hasABike()){
                            System.out.println("Vous avez un vélo louer, nous allons le reprendre");
                            System.out.println("Ecrivez une note qui va etre attaché au velo:");

                            var note = scanner.nextLine();

                            //rendre le velo
                            System.out.println("Velo ID : " + user.getBike().get().getId() + " rendu !");
                            bikeStorage.returnBike(user, note);

                            System.out.println("Merci, le vélo a été rendu");
                        }
                        else{
                            System.out.println("Vous n'avez pas de vélo a rendre");
                        }
                        break;
                    case "-rent" :
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
                                        System.out.println("Vous avez louer le velo id " + BikeID);
                                        bikeStorage.rentBike(user, BikeID);
                                        user.getBike().get();
                                        break;
                                    }
                                    i++;
                                }
                                if(!user.hasABike()){
                                    System.out.println("L'ID du velo n'est pas correct");
                                }
                            }
                            else {
                                System.out.println("Il n'y a plus de vélo disponibles");
                            }
                        }
                        else {
                            System.out.println("Vous avez déjà un vélo vous ne pouvez pas en louer un autre pour le moment");

                        }
                        break;
                    case "-notes" :
                        if(user.hasABike()){
                            System.out.println("Voici les notes de votre vélo:");
                            System.out.println(user.getBike().get().getNotes());
                        }
                        else{
                            System.out.println("Vous n'avez pas de vélo");
                        }
                        break;
                    default:
                        System.out.println("Consulter les vélos disponibles (-b), Rendre le vélo (-ret), Louer un vélo (-rent), Consulter les notes du vélo (-notes)");
                        break;
                }

            }
        }catch (IllegalStateException e) {
            System.out.println(e);
        }
        scanner.close();
        System.out.println("FIN PROGRAMME !");

    }

}