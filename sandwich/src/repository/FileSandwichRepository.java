package repository;

import model.Order;
import model.Sandwich;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSandwichRepository {

     private static  FileSandwichRepository instance;

    private List<Order> allOrders = new ArrayList<Order>();

    private static final String FILELOCATION = "/temp/javacourses/Orders.csv";

    public static FileSandwichRepository getInstance() throws IOException {
        if (instance == null) instance = new FileSandwichRepository();
        return instance;
    }


    public void readFile() throws IOException {

        if (allOrders.size() != 0) allOrders.clear();
        List<String> lines = Files.readAllLines(Paths.get(FILELOCATION));
        for (String line : lines) {
            String[] vals = line.split(";");
            if (!vals[0].equals("")) {

                Order o = null;

                String sandName =  vals[0];
                if(! sandName.equals(null) ) {
                    Sandwich s =  null;
                    s.setSandwichName(sandName );
                }
            }
        }
    }


}
