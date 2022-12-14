package gui;

import javax.swing.*;

public class MainLogin {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Login lf = new Login();
                lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                lf.setVisible(true);
            }
        });
    }
}

