package gui;


import controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame {

    private LoginController controller;
    private JPanel LoginPanel;
    private JTextField txt_name;
    private JLabel lbl_name;
    private JButton loginButton;
    private JTextField txt_message;
    private JLabel lbl_password;
    private JPasswordField Txt_password;


    private void initialize() {
        this.setSize(600, 300);
        this.setContentPane(LoginPanel);
        this.setTitle("Login form");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.controller =  LoginController.getInstance();

    }

    public Login()  {
        super();
        initialize();
        loginButton.addActionListener(new LoginButtonListener());


    }
    public void fillErrorLabel(String message) {
        txt_message.setText(message);
    }
        private class LoginButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("doing things");
                String email = txt_name.getText();
                String password = Txt_password.getText();
                controller.loginAction(Login.this, email, password);
            }
        }

    }





