package test;

import exception.TooManyOrdersException;
import model.Order;
import model.Sandwich;
import repository.FileOrderRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class test1 {
    public static void main(String[] args) throws TooManyOrdersException, IOException {

        FileOrderRepository or = FileOrderRepository.getInstance();
        or.readFile();
        List<Order> test0 = or.getAllOrders();
        System.out.print(test0.size());
        or.formatGlobalOrder();




    }
}
