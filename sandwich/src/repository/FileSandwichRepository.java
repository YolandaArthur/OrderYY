package repository;

import exception.OrderAlreadyExistsException;
import exception.OrderNotFoundException;
import model.Order;
import model.Sandwich;
import utils.DateUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSandwichRepository implements SandwichRepository{

    private static FileSandwichRepository instance;
    private List<Sandwich> allSandwiches = new ArrayList<Sandwich>();
    private static final String FILELOCATIONSAND = "/temp/javacourses/Sandwiches.csv";

    public static FileSandwichRepository getInstance() throws IOException {
        if (instance == null) instance = new FileSandwichRepository();
        return instance;
    }




    public void readFileSandwich() throws IOException {

        if (allSandwiches.size() != 0) allSandwiches.clear();
        List<String> lines = Files.readAllLines(Paths.get(FILELOCATIONSAND));
        for (String line : lines) {
            String[] vals = line.split(";");
            if (!vals[0].equals("")) {

                Sandwich s = new Sandwich();
                String sandName =  vals[0];
                System.out.println(sandName);
                if(! sandName.equals(null) ) {
                    s.setSandwichName(sandName );
                    s.setDescription(vals[1]);
                    s.setCategory(vals[2]);
                }
                allSandwiches.add(s);
            }
        }
    }


    @Override
    public List<Sandwich> getAllSandwiches() {
        return allSandwiches;
    }

}
