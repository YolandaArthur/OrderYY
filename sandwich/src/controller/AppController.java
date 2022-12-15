package controller;

import exception.OrderAlreadyExistsException;
import gui.OrderForm;
import model.Order;
import model.Sandwich;
import repository.FileOrderRepository;
import repository.FileSandwichRepository;

import java.io.IOException;
import java.util.List;

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
        } catch (IOException | OrderAlreadyExistsException e) {
            System.out.println(e.getMessage());
            of.fillErrorLabel(e.getMessage());


            //exceptionLogger.error(e.getMessage());
        }
    }

    public List<Sandwich> GetSandwichCatalog(OrderForm of){
        try {
            sandwichRepo.readFileSandwich();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            of.fillErrorLabel(e.getMessage());
        }

        return sandwichRepo.getAllSandwiches();

    }
}