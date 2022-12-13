package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
