package EiffelBikeCorpClient.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FormBike {
    @NotNull
    private String name;

    @NotNull
    @Min(1)
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
