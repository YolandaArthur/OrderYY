package gui;

import controller.AppController;
import model.Order;
import model.Sandwich;
//import sun.jvm.hotspot.debugger.Address;
import repository.FileOrderRepository;
import repository.FileSandwichRepository;
import repository.SandwichRepository;
import utils.DateUtils;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderForm extends JFrame {
    private AppController controller;
    private JPanel orderPanel;
    private JLabel sandwichLabel;
    private JLabel breadTypeLabel;
    private JLabel personLabel;
    private JLabel commentLabel;
    private JTextField personNameTextField;
    private JTextField commentTextField;
    private JCheckBox rawVegetablesCheckBox;
    private JComboBox sandwichComboBox;
    private JButton addButton;
    private JButton sendButton;
    private JComboBox veganComboBox;
    private JLabel categoryLabel;
    private JComboBox categoryComboBox;
    private JComboBox breadTypeComboBox;
    private JLabel courseLabel;
    private JTextField courseTextField;
    private JLabel errorLabel;
    private JLabel sandwichDesc;

    private List<Sandwich> sandwichList1 = null;

    private void initialize() {
        this.setSize(600, 300);
        this.setContentPane(orderPanel);
        this.setTitle("Sandwich order");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void initializeComboBox() {

        Set<String> uniqueCategoryList = new HashSet<String>();
        for(Sandwich i : this.sandwichList1){
            //sandwichComboBox.addItem(i.getSandwichName());
            uniqueCategoryList.add(i.getCategory());
        }


        for (String i : uniqueCategoryList) {
            categoryComboBox.addItem(i);
        }

       String currentCategory;

       currentCategory = categoryComboBox.getSelectedItem().toString();


       for (Sandwich j : sandwichList1){
                if (j.getCategory().equals(currentCategory)){
                    sandwichComboBox.addItem(j);
                    sandwichDesc.setForeground(Color.BLUE);
                    sandwichDesc.setText(j.getDescription());
                }
        }

        sandwichDesc.setText(((Sandwich) sandwichComboBox.getSelectedItem()).getDescription());


        breadTypeComboBox.addItem("Gris");
        breadTypeComboBox.addItem("Blanc");

        if (currentCategory.equals("Végan")){
            rawVegetablesCheckBox.setEnabled(false);
            rawVegetablesCheckBox.setSelected(false);
            veganComboBox.addItem("Crudités");
            veganComboBox.addItem("légumes grillés");
            veganComboBox.addItem("Sans Légumes");
            veganComboBox.setEnabled(true);
        } else if (currentCategory.equals("Spécialités")) {
            rawVegetablesCheckBox.setEnabled(false);
            rawVegetablesCheckBox.setSelected(false);

            veganComboBox.setEnabled(false);
        } else {
            rawVegetablesCheckBox.setEnabled(true);
            veganComboBox.removeAllItems();
            veganComboBox.setEnabled(false);
        }





    }


    private void onAdd() {

        boolean allOk = true;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
        LocalDateTime now = LocalDateTime.now();
        int hrS = Integer.parseInt(dtf.format(now));
        if (hrS > 9) {
            errorLabel.setForeground(Color.RED);
            errorLabel.setText("it's too late. Please come back tomorrow");
            allOk = false;
        } else {
            String sandwichName = sandwichComboBox.getSelectedItem().toString();
            String breadType = breadTypeComboBox.getSelectedItem().toString();
            String personName = personNameTextField.getText();
            String commentString = commentTextField.getText();
            String courseName = courseTextField.getText();
            boolean withRawVegetables = Boolean.parseBoolean(rawVegetablesCheckBox.getText());
            String veganOptions;
            if (veganComboBox.getSelectedItem()==null) {
                veganOptions = "null";
            }
            else {
                veganOptions = veganComboBox.getSelectedItem().toString();
            }

            if (personName.isEmpty()) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Name is required.");
                allOk = false;
            } else if (courseName.isEmpty()) {
                errorLabel.setForeground(Color.RED);
                errorLabel.setText("Course name is required.");
                allOk = false;
            } else {
                errorLabel.setText("");
                allOk = true;
            }

            if (allOk) {
                    Sandwich s = new Sandwich();
                    s.setSandwichName(sandwichName);
                    LocalDate ld = DateUtils.parse("13/12/2022");
                    Order o = new Order(s, ld, personName, courseName, breadType, withRawVegetables, veganOptions, commentString);
                    controller.AddToOrderAction(OrderForm.this, o);
                }
        }
    }



    public OrderForm() throws HeadlessException {
        super();

        /*FileSandwichRepository sr = null;
        try {
            sr = FileSandwichRepository.getInstance();
            sr.readFileSandwich();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            sandwichList1 = FileSandwichRepository.getInstance().getAllSandwiches();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/


        try {
            controller= AppController.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sandwichList1 = controller.GetSandwichCatalog(this);
        initialize();
        initializeComboBox();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("going to add item to order list");
                onAdd();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        categoryComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //System.out.println("got to change sandwich combobox");

                sandwichComboBox.removeAllItems();
                String currentCategory;
                currentCategory = categoryComboBox.getSelectedItem().toString();

                for (Sandwich j : sandwichList1){
                    if (j.getCategory().equals(currentCategory)){
                        sandwichComboBox.addItem(j);
                    }
                }
                sandwichDesc.setForeground(Color.BLUE);
                sandwichDesc.setText(((Sandwich) sandwichComboBox.getSelectedItem()).getDescription());
                if (currentCategory.equals("Végan")){
                    rawVegetablesCheckBox.setEnabled(false);
                    rawVegetablesCheckBox.setSelected(false);
                    veganComboBox.addItem("Crudités");
                    veganComboBox.addItem("légumes grillés");
                    veganComboBox.addItem("Sans Légumes");
                    veganComboBox.setEnabled(true);
                } else if (currentCategory.equals("Spécialités")) {
                    rawVegetablesCheckBox.setEnabled(false);
                    rawVegetablesCheckBox.setSelected(false);

                    veganComboBox.setEnabled(false);
                } else {
                    rawVegetablesCheckBox.setEnabled(true);
                    veganComboBox.removeAllItems();
                    veganComboBox.setEnabled(false);
                }
            }
        });
        sandwichComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            if (sandwichComboBox.getSelectedItem()==null) {
            } else {
                sandwichDesc.setForeground(Color.BLUE);
                sandwichDesc.setText(((Sandwich) sandwichComboBox.getSelectedItem()).getDescription());
            }

            }
        });
    }

    public void fillErrorLabel(String message) {
        errorLabel.setText(message);
    }



}
