package controller;

import exception.OrderAlreadyExistsException;
import gui.OrderForm;
import model.Order;
import repository.FileOrderRepository;

import java.io.IOException;

public class AppController {

    private static AppController instance;

    private static FileOrderRepository orderRepo;

    private AppController() throws IOException {
        orderRepo = FileOrderRepository.getInstance();
    }

    public static AppController getInstance() throws IOException {
        if (instance == null) instance = new AppController();
        return instance;
    }

    public void AddToOrderAction(Order o) {
        try {
            orderRepo.addOrder(o);
        } catch (IOException | OrderAlreadyExistsException e) {
            System.out.println(e.getMessage());
            //OrderForm.fillErrorLabel(e.getMessage());


            //exceptionLogger.error(e.getMessage());
        }
    }
}

