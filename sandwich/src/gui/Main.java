package gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                OrderForm of = new OrderForm();
                of.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                of.setVisible(true);
            }
        });
    }
}
