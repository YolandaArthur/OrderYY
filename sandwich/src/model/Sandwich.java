package model;
import lombok.*;


@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Sandwich {


    private String sandwichName;
    private String description;
    private String Category;

    @Override
    public String toString() {
        return sandwichName;
    }
}

