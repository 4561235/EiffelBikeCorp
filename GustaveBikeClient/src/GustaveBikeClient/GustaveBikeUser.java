package GustaveBikeClient;

import java.util.Objects;

public class GustaveBikeUser {
	
	private final String surname;
	private final String name;
	
	public GustaveBikeUser(String surname, String name) {
		Objects.requireNonNull(surname);
		Objects.requireNonNull(name);
		this.surname = surname;
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}
	
	public int getId() {
		return Math.abs(Objects.hash(surname, name));
	}
	
	
}
