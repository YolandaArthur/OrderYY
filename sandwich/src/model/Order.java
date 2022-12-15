package model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Order {
    private Sandwich sandwich;
    private LocalDate date;
    @EqualsAndHashCode.Include
    private String personName;
    @EqualsAndHashCode.Include
    private String course;
    private String typeBread;
    private Boolean withRawVegetables;
    private String veganOptions;
    private String comment;

    public boolean validate(){
        return true;
    }

    @Override
    public String toString(){
        String or ;
        or =  this.personName + " " +  this.getSandwich().getCategory() + " ";
        or = or + " " + this.getSandwich().getDescription();
        return or;
    }

}
