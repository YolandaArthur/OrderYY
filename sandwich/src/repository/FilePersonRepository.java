package repository;

import exception.PersonNotFoundException;
import model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilePersonRepository {


    private static FilePersonRepository instance;
    private List<Person> allPersons = new ArrayList<Person>();


    public static FilePersonRepository getInstance()  {
        if (instance == null) instance = new FilePersonRepository();
        return instance;
    }
    public void readFile(){

        Person p = new Person("Yolanda","Secret");
        allPersons.add(p);
         p = new Person("Yves","007");
        allPersons.add(p);

    }

    public Person findPerson(String name, String password) throws  PersonNotFoundException {

        return allPersons.stream()
                .filter(p -> p.getName().equals(name) && p.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException("Login not correct. Try again."));

    }
    private FilePersonRepository() {
        this.readFile();
    }
}
