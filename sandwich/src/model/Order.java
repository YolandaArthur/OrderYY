package model;

import java.time.LocalDate;
public class Order {

    private Sandwich sandwich;
    private LocalDate date;
    private String personName;
    private String course;
    private String typeBread;
    private Boolean withRawVegetables;
    private String comment;

    public boolean validate(){
        return true;
    }

}
