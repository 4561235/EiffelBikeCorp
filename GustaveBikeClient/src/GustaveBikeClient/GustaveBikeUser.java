package GustaveBikeClient;

import java.util.Objects;

public class GustaveBikeUser {
	
	private final String surname;
	private final String name;
	private int money;
	
	public GustaveBikeUser(String surname, String name, int money) {
		Objects.requireNonNull(surname);
		Objects.requireNonNull(name);
		if(money < 0) throw new IllegalArgumentException("Money can't be < 0");
		this.surname = surname;
		this.name = name;
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}
	
	
}
