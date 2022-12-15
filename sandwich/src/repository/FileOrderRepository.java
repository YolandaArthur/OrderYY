package repository;

import exception.OrderNotFoundException;
import exception.TooManyOrdersException;
import model.Order;
import model.Person;
import model.Sandwich;
import utils.DateUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileOrderRepository implements OrderRepository{

    private static FileOrderRepository instance;

    private static List<Order> allOrders = new ArrayList<Order>();

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

                Order o =new Order();
                Sandwich s = new Sandwich();
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
                o.setVeganOptions(!vals[8].equals("null") ? vals[8] : null);
                o.setComment(!vals[9].equals("null") ? vals[9] : null);

                allOrders.add(o);
            }
        }
    }
    private String convertOrderToString(Order o) {
        Sandwich s = o.getSandwich();
        StringBuilder sb = new StringBuilder();

        System.out.println("desc :" + s.getDescription());
        System.out.println("cat :" + s.getCategory());
        System.out.println("name :" + s.getSandwichName());


        sb.append(s.getSandwichName()).append(";")
                .append(s.getCategory()).append(";")
                .append(s.getDescription()).append(";")
                .append(DateUtils.format(o.getDate())).append(";")
                .append((o.getPersonName()!=null ?o.getPersonName():"null")).append(";")
                .append((o.getCourse()!=null ? o.getCourse():"null")).append(";")
                .append((o.getTypeBread()!=null ?o.getTypeBread():"null")).append(";")
                .append((o.getWithRawVegetables()?true:false)).append(";")
                .append((o.getVeganOptions()==null?"null":o.getVeganOptions())).append(";")
                .append((o.getComment()==null?"null":o.getComment())).append(";");

        return sb.toString();
    }

    @Override
    public List<Order> getAllOrders() {
        return this.allOrders;
    }

    @Override
    public Order findOrder(String email, String password) throws OrderNotFoundException {
        return null;
    }

    @Override
    public void addOrder(Order o) throws TooManyOrdersException, IOException {
        long i =  allOrders.stream().filter(a -> a.getPersonName().equals(o.getPersonName())  && a.getCourse().equals(o.getCourse()))
                .count();
        if (i>1) throw new TooManyOrdersException("only 2 orders allowed by person");
        PrintWriter pw = new PrintWriter(new FileWriter(FILELOCATION, true));
        String s = this.convertOrderToString (o);
        pw.append("\n" + s);
        pw.close();
        allOrders.add(o);
    }
    public String formatGlobalOrder(){
        Map<String, List<Order>> orderPerCourse = allOrders.stream().
                collect(Collectors.groupingBy(o -> o.getCourse()));

        System.out.println(orderPerCourse + "\n");
        for (String cName : orderPerCourse.keySet()) {
            System.out.println(cName + ": ");
            for (Order o : orderPerCourse.get(cName)) {
                System.out.println(o);
            }
            System.out.println("");
        }

        return null;
    }


}
