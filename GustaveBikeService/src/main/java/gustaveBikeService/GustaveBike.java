package gustaveBikeService;


public class GustaveBike {
	

	private String bikeName;
    private String notes;
    private int price;
    
    public GustaveBike() {
    	this.bikeName = "Default bike";
    	this.notes = "";
    	this.price = 5;
    }
	
	public GustaveBike(String bikename, String notes, int price) {
//		Objects.requireNonNull(bikename);
//		Objects.requireNonNull(notes);
		this.bikeName = bikename;
		this.notes = notes;
		this.price = price;
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
