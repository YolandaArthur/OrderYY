package repository;

import exception.OrderAlreadyExistsException;
import exception.OrderNotFoundException;
import model.Sandwich;

import java.io.IOException;
import java.util.List;

public interface SandwichRepository {

    List<Sandwich> getAllSandwiches();

}
