package GustaveBikeService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import common.EiffelBikeCorpInterface;

public class GustaveBikeService {
	
	private final EiffelBikeCorpInterface eiffelBikeStorage;
	
	public GustaveBikeService() throws MalformedURLException, RemoteException, NotBoundException {
		this.eiffelBikeStorage = (EiffelBikeCorpInterface) Naming.lookup("EiffelBikeCorpService");
//		System.out.println(this.getBikesToBuy());
		System.out.println("WOW ca marche");
	}
	
	public String sayHello() {
		return "Hello m8";
	}
	
	public String[] getBikesToBuy() throws RemoteException {
        return this.eiffelBikeStorage.bikesToBorrow().toArray(String[]::new);
    }
	
	public String helloWorld() {
		return "Hello world";
	}
}
