package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame implements ActionListener{
    
    JLabel motorPH, title, userLbl, passwordLbl, success;
    JTextField userTxt;
    JPasswordField pass;
    JPanel logoPane, separator, logInPanel;
    JButton logButton;
    
    LogIn() {
        ImageIcon prelogo = new ImageIcon("MotorPH Logo.png");
        Image logo = prelogo.getImage();
        Image motorLogo = logo.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        prelogo = new ImageIcon(motorLogo);
        
        logoPane = new JPanel();
        logoPane.setPreferredSize(new Dimension(145, 35));
        logoPane.setBackground(null);
        logoPane.setBounds(5, 5, 150, 35);
        
        motorPH = new JLabel("MotorPH");
        motorPH.setIcon(prelogo);
        motorPH.setForeground(new Color(0x202A3A));
        motorPH.setFont(new Font("DIN Alternate", Font.BOLD, 23));
        motorPH.setIconTextGap(10);
        motorPH.setVerticalAlignment(JLabel.TOP);
        motorPH.setBounds(5, 5, 150, 30);
        
        logoPane.add(motorPH);
        
        this.setSize(800, 700);
        this.setTitle("MotorPH Payroll");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        logInPanel = new JPanel();
        logInPanel.setLayout(null);
        logInPanel.setBounds(20, 75, 746, 250);
        
        title = new JLabel("Welcome");
        title.setForeground(new Color(0xF8B32A));
        title.setFont(new Font("DIN Alternate", Font.BOLD, 46));
        title.setBounds(290, 10, 205, 60);
        
        userLbl = new JLabel("Username");
        userLbl.setBounds(220, 100, 180, 25);
        userLbl.setForeground(new Color(0x202A3A));
        userLbl.setFont(new Font("DIN Alternate", Font.PLAIN, 15));
        
        userTxt = new JTextField(20);
        userTxt.setBounds(320, 100, 200, 25);
        
        passwordLbl = new JLabel("Password");
        passwordLbl.setBounds(220, 130, 180, 25);
        passwordLbl.setForeground(new Color(0x202A3A));
        passwordLbl.setFont(new Font("DIN Alternate", Font.PLAIN, 15));
        
        pass = new JPasswordField();
        pass.setBounds(320, 130, 200, 25);
        
        logButton = new JButton("Log In");
        logButton.setBounds(330, 180, 80, 25);
        logButton.addActionListener(this);
        logButton.setFont(new Font("DIN Alternate", Font.PLAIN, 15));
        logButton.setForeground(Color.WHITE);
        logButton.setBackground(new Color(0x202A3A));
        logButton.setBorder(null);
        logButton.setOpaque(true);
        
        success = new JLabel("Placeholder");
        success.setBounds(320, 210, 300, 25);
        success.setForeground(new Color(0xF90606));
        success.setFont(new Font("DIN Alternate", Font.ITALIC, 15));
        
        logInPanel.add(title);
        logInPanel.add(userLbl);
        logInPanel.add(passwordLbl);
        logInPanel.add(userTxt);
        logInPanel.add(pass);
        logInPanel.add(logButton);
        logInPanel.add(success);
        
        separator = new JPanel();
        separator.setBackground(new Color(0x202A3A));
        separator.setBounds(20, 50, 746, 1);
        
        this.add(logoPane);
        this.add(logInPanel);
        this.add(separator);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==logButton) {
           String username = userTxt.getText().trim();
           String password = new String(pass.getPassword());

           // Get the valid employee numbers from Payroll class
           String[] validEmployeeNumbers = Payroll.getEmployeeNumbers();

           boolean isValidEmployee = false;
           for (String empNum : validEmployeeNumbers) {
               if (empNum.equals(username)) {
                   isValidEmployee = true;
                   break;
               }
           }

           if (isValidEmployee) {
               // For now, we're just checking the username (employee number)
               // You might want to add password validation later
               this.dispose();
               new Payroll();
           } else {
               success.setText("Invalid employee number");
           }
       }
    }
}
