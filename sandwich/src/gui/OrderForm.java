package gui;

import controller.AppController;
import model.Order;
import model.Sandwich;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

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

    private void initialize() {
        this.setSize(600, 300);
        this.setContentPane(orderPanel);
        this.setTitle("Sandwich order");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void initializeComboBox() {
       String[] allCat = { "Viande", "Poulet", "Poisson","Végétarien","Végan","Spécialités" };
        for (String i : allCat) {
            categoryComboBox.addItem(i);
        }
        String[] viandeSandW = {"Filet américain","Boulette","Pastrami"};
        String[] pouletSandw = {"Blance de Poulet","Poulet au curry piquant"};
        String[] poissonSandw = {"Salade de thon","Salade de thon piquant"};
        String[] vegetSandw = {"Fromage","Tomate mozarella pesto"};
        String[] veganSandw = {"Spread de carotte + sésam + cressonnette","Houmous"};
        for(String i : viandeSandW){
            sandwichComboBox.addItem(i);
        }
        breadTypeComboBox.addItem("Gris");
        breadTypeComboBox.addItem("Blanc");
        veganComboBox.addItem("Crudités");
        veganComboBox.addItem("légumes grillés");
        veganComboBox.addItem("Sans Légumes");

    }


    private void onAdd() {

        boolean allOk=true;
        String sandwichName = sandwichComboBox.getSelectedItem().toString();
        String breadType = breadTypeComboBox.getSelectedItem().toString();
        String personName = personNameTextField.getText();
        String commentString = commentTextField.getText();
        String courseName = courseTextField.getText();
        boolean withRawVegetables = Boolean.parseBoolean(rawVegetablesCheckBox.getText());
        String veganOptions = veganComboBox.getSelectedItem().toString();

        if (personName.isEmpty()){
            errorLabel.setText("Name is required.");
            allOk=false;
        } else if (courseName.isEmpty()){
            errorLabel.setText("Course name is required.");
            allOk=false;
        } else {
            errorLabel.setText("");
            allOk=true;
        }

        if (allOk){
            Sandwich s = new Sandwich();
            s.setSandwichName(sandwichName);
            LocalDate ld = DateUtils.parse("13/12/2022");
            Order o = new Order(s,ld,personName,courseName,breadType,withRawVegetables,veganOptions,commentString);
            controller.AddToOrderAction(o);
        }

    }




    public OrderForm() throws HeadlessException {
        super();
        initialize();
        initializeComboBox();
        try {
            controller= AppController.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("adding item to order list");
                onAdd();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("sending order");

            }
        });



    }

    public void fillErrorLabel(String message) {
        errorLabel.setText(message);
    }
}
