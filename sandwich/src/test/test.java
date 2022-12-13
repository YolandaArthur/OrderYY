package test;

import exception.OrderAlreadyExistsException;
import model.Order;
import model.Sandwich;
import repository.FileOrderRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class test {
    public static void main(String[] args) throws OrderAlreadyExistsException, IOException {
        Sandwich s = new Sandwich("viande","americain","autre");
        Order o = new Order(s, LocalDate.of(2022, 12, 15),"Yoyo","java","blanc",true,"no","comment");
        FileOrderRepository or = FileOrderRepository.getInstance();

        List<Order> orders  = null ;
        or.addOrder(o);


    }
}
