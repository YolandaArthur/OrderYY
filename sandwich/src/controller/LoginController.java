package controller;

import exception.OrderAlreadyExistsException;
import exception.PersonNotFoundException;
import gui.Login;
import model.Order;
import model.Person;
import repository.FileOrderRepository;
import repository.FilePersonRepository;

import java.io.IOException;

public class LoginController {

    private static LoginController instance;

    private static FilePersonRepository pr;

    private LoginController()  {
        pr = FilePersonRepository.getInstance();
    }

    public static LoginController getInstance() {
        if (instance == null) instance = new LoginController();
        return instance;
    }

    public void loginAction(Login login, String name, String password) {
        System.out.println("name="+name+", password="+password);
        Person p = null;
        try {
            p = pr.findPerson(name,password);
            login.fillErrorLabel("successfull login "+name);
        } catch (PersonNotFoundException e) {
            login.fillErrorLabel(e.getMessage());

        }

    }
}

