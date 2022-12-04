package EiffelBikeCorpClient;

import EiffelBikeCorpClient.form.FormBike;
import EiffelBikeCorpClient.form.FormGiveBack;
import EiffelBikeCorpClient.form.FormName;
import EiffelBikeCorpClient.form.FormRent;
import common.BikeInterface;
import common.EiffelBikeCorpInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class EiffelClientController {

    private final EiffelBikeCorpInterface bikeModel;
    private final Set<String> hasRented;
    private final Set<String> leased;
    private EiffelUser eiffelUser;

    public EiffelClientController() throws MalformedURLException, NotBoundException, RemoteException {
        bikeModel = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");
        leased = new HashSet<>();
        hasRented = new HashSet<>();
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/EiffelBikeCorp/login";
    }

    @GetMapping("/EiffelBikeCorp")
    public String main() {
        return "redirect:/EiffelBikeCorp/login";
    }

    @GetMapping("/EiffelBikeCorp/login")
    public String login(Model model) {
        model.addAttribute("formName",new FormName());
        return "login";
    }

    @PostMapping("/EiffelBikeCorp/login")
    public String processFormName(@Valid @ModelAttribute("formName") FormName param,
                                  BindingResult bindingResult,
                                  Model model) throws RemoteException {
        if(bindingResult.hasErrors()){
            return "login";
        }
        eiffelUser = new EiffelUser(param.getLastName(),param.getFirstName());
        return "redirect:/EiffelBikeCorp/hub";
    }

    @GetMapping("/EiffelBikeCorp/hub")
    public String hub(Model model) throws RemoteException {
        model.addAttribute("name",eiffelUser.getName());
        model.addAttribute("bikes",bikeModel.bikesToBorrow());
        return "site";
    }

    //LEASE PAGE

    @GetMapping("/EiffelBikeCorp/lease")
    public String lease(Model model){
        if(leased.contains(eiffelUser.getName()+eiffelUser.getSurname())){
            //ADD ERROR
            return "redirect:/EiffelBikeCorp/hub";
        }
        model.addAttribute("formBike",new FormBike());
        return "lease";
    }

    @PostMapping("/EiffelBikeCorp/lease")
    public String processFormLease(@Valid @ModelAttribute("formBike") FormBike param,
                                   BindingResult bindingResult,
                                   Model model) throws RemoteException {
        if(bindingResult.hasErrors()) {
            return "lease";
        }
        bikeModel.addBike(param.getName(),param.getPrice());
        leased.add(eiffelUser.getName()+eiffelUser.getSurname());
        return "redirect:/EiffelBikeCorp/hub";
    }

    //RENT PAGE

    @GetMapping("EiffelBikeCorp/rent")
    public String rent(Model model) throws RemoteException {
        if(hasRented.contains(eiffelUser.getName()+eiffelUser.getSurname())){
            //ADD ERROR
            return "redirect:/EiffelBikeCorp/hub";
        }
        var form = new FormRent();
        var bikes = updateBikeToBorrow();
        var bikeIds = new ArrayList<Integer>();
        for(var bike : bikes){
            bikeIds.add(bike.getId());
        }
        form.setBikesIDs(bikeIds);
        model.addAttribute("bikesToRent",bikes);
        model.addAttribute("formRent",form);
        return "rent";
    }

    @PostMapping("EiffelBikeCorp/rent")
    public String processFormRent(@Valid @ModelAttribute("formRent") FormRent param, BindingResult bindingResult, Model model) throws RemoteException {
        if(bindingResult.hasErrors()){
            return "rent";
        }
        bikeModel.rentBike(eiffelUser,param.getBikeID());
        hasRented.add(eiffelUser.getName()+eiffelUser.getSurname());
        return "redirect:/EiffelBikeCorp/hub";
    }

    //GIVEBACK PAGE

    @GetMapping("EiffelBikeCorp/giveback")
    public String giveback(Model model){
        if(!hasRented.contains(eiffelUser.getName()+eiffelUser.getSurname())){
            //ADD ERROR
            return "redirect:/EiffelBikeCorp/hub";
        }
        model.addAttribute("formGiveBack",new FormGiveBack());
        return "giveback";
    }

    @PostMapping("EiffelBikeCorp/giveback")
    public String processFormGiveBack(@Valid @ModelAttribute("formGiveBack") FormGiveBack param, BindingResult bindingResult, Model model) throws RemoteException {
        if(bindingResult.hasErrors()){
            return "giveback";
        }
        bikeModel.returnBike(eiffelUser, param.getNote());
        hasRented.remove(eiffelUser.getName()+eiffelUser.getSurname());
        return "redirect:/EiffelBikeCorp/hub";
    }

    //USAGE METHODS

    private Set<Bike> updateBikeToBorrow() throws RemoteException {
        Set<Bike> bikes = new HashSet<>();
        for(var bikeString : bikeModel.bikesToBorrow()){
            var splited = bikeString.split(":");
            var bike = new Bike(
                    Integer.parseInt(splited[1].split("Bike")[0].trim()),
                    splited[2].split("Price")[0].trim(),
                    Integer.parseInt(splited[3].split(",")[0].trim()));
            var notesString = splited[4].trim();
            if(notesString.length() > 2){
                var notesArray = notesString.substring(1, splited.length-1).split(",");
                for(var note : notesArray){
                    bike.addNote(note);
                }
            }
            bikes.add(bike);
        }
        return bikes;
    }

}
