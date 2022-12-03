package bankService;

import java.util.HashMap;

public class BankService {
	
	private final HashMap<Integer, Long> usersFounds;
	
	public BankService() {
		System.out.println("BankService constructor");
		this.usersFounds = new HashMap<Integer, Long>();
	}
	
	public long getUsersFounds(int userID) {
//		System.out.println("Getting user");
//		System.out.println(this.usersFounds);
		if(this.usersFounds.containsKey(userID)) {
			return this.usersFounds.get(userID);
		}else {
			return -1l;
		}
	}
	
	public void addFounds(int userID, long founds) {
		if(this.usersFounds.containsKey(userID)) {
			long usersFounds = this.usersFounds.get(userID);
			usersFounds = usersFounds + founds;
			this.usersFounds.replace(userID, usersFounds);
		}else {
//			System.out.println("User added");
			this.usersFounds.put(userID, founds);
//			System.out.println(this.usersFounds);
		}
	}
	
	public boolean removeFounds(int userID, long founds) {
		if(this.usersFounds.containsKey(userID)) {
			
			long usersFounds = this.usersFounds.get(userID);
			
			if(usersFounds - founds >= 0) {
				usersFounds = usersFounds - founds;
				this.usersFounds.replace(userID, usersFounds);
				return true;
			}
			else {
				return false;
			}
			
		}else {
			return false;
		}
	}
	
}
