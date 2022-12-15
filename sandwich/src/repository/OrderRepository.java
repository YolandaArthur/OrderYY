package repository;

import exception.TooManyOrdersException;
import exception.OrderNotFoundException;
import model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderRepository {

    List<Order> getAllOrders();

    Order findOrder(String email, String password) throws OrderNotFoundException;
    void addOrder(Order o) throws IOException, TooManyOrdersException;

}
