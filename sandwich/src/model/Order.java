package model;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Order {
    private Sandwich sandwich;
    private LocalDate date;
    private String personName;
    private String course;
    private String typeBread;
    private Boolean withRawVegetables;
    private String veganOptions;
    private String comment;

    public boolean validate(){
        return true;
    }

}
