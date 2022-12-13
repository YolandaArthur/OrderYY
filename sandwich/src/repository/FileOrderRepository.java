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

public class FileOrderRepository implements OrderRepository{

    private static FileOrderRepository instance;

    private List<Order> allOrders = new ArrayList<Order>();

    private static final String FILELOCATION = "/temp/javacourses/Orders.csv";

    public static FileOrderRepository getInstance() throws IOException {
        if (instance == null) instance = new FileOrderRepository();
        return instance;
    }


    public void readFile() throws IOException {

        if (allOrders.size() != 0) allOrders.clear();
        List<String> lines = Files.readAllLines(Paths.get(FILELOCATION));
        for (String line : lines) {
            String[] vals = line.split(";");
            if (!vals[0].equals("")) {

                Order o = null;
                Sandwich s =  null;
                String sandName =  vals[0];
                if(! sandName.equals(null) ) {
                    s.setSandwichName(sandName );
                    s.setDescription(vals[1]);
                    s.setCategory(vals[2]);
                }
                if (s != null) o.setSandwich(s);
                o.setDate(!vals[3].equals("null") ? DateUtils.parse(vals[3]) : null);
                o.setPersonName(!vals[4].equals("null") ? vals[4] : null);
                o.setCourse(!vals[5].equals("null") ? vals[5] : null);
                o.setTypeBread(!vals[6].equals("null") ? vals[6] : null);
                o.setWithRawVegetables(vals[7].equals("null"));
                o.setCourse(!vals[8].equals("null") ? vals[8] : null);
                o.setComment(!vals[9].equals("null") ? vals[9] : null);

                allOrders.add(o);
            }
        }
    }

    private String convertOrderToString(Order o) {
        Sandwich s = o.getSandwich();
        StringBuilder sb = new StringBuilder();
        sb.append(s.getSandwichName()).append(";")
                .append(s.getCategory()).append(";")
                .append(s.getDescription()).append(";")
                .append(DateUtils.format(o.getDate())).append(";")
                .append(o.getPersonName()).append(";")
                .append(o.getCourse()).append(";")
                .append(o.getTypeBread()).append(";")
                .append(o.getWithRawVegetables()).append(";")
                .append(o.getVeganOptions()).append(";")
                .append(o.getComment())
;
        return sb.toString();
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public Order findOrder(String email, String password) throws OrderNotFoundException {
        return null;
    }

    @Override
    public void addOrder(Order o) throws OrderAlreadyExistsException, IOException {

        if (allOrders.contains(o)) {
            String message =  " was already registered.";
            throw new OrderAlreadyExistsException(message);
        }

        PrintWriter pw = new PrintWriter(new FileWriter(FILELOCATION, true));
        String s = this.convertOrderToString (o);
        pw.append("\n" + s);
        pw.close();
        allOrders.add(o);
    }
}
