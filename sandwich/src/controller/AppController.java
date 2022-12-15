package controller;

import exception.TooManyOrdersException;
import gui.OrderForm;
import model.Order;
import repository.FileOrderRepository;
import repository.FileSandwichRepository;

import java.io.IOException;

public class AppController {

    private static AppController instance;

    private static FileOrderRepository orderRepo;
    private static FileSandwichRepository sandwichRepo;

    private AppController() throws IOException {
        orderRepo = FileOrderRepository.getInstance();
        sandwichRepo = FileSandwichRepository.getInstance();
    }

    public static AppController getInstance() throws IOException {
        if (instance == null) instance = new AppController();
        return instance;
    }

    public void AddToOrderAction(OrderForm of, Order o) {
        try {
            orderRepo.addOrder(o);
        } catch (IOException | TooManyOrdersException e) {
            System.out.println(e.getMessage());
            of.fillErrorLabel(e.getMessage());


            //exceptionLogger.error(e.getMessage());
        }
    }
}