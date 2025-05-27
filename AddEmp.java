package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmp extends JFrame implements ActionListener{
    
    JLabel staffTitle, empNo, name, birthday, hourlyRate;
    JTextField txtEmpNo, txtName, txtBirthday, txtHourly;
    JPanel staffForm;
    JButton staffSubmit;
    
    
    AddEmp() {
        // Title Pane
        staffTitle = new JLabel();
        staffTitle.setText("New Staff");
        staffTitle.setForeground(new Color(0x202A3A));
        staffTitle.setFont(new Font("DIN Alternate", Font.BOLD, 23));
        staffTitle.setVerticalAlignment(JLabel.TOP);
        staffTitle.setBounds(200, 20, 150, 30);
        
        // Frame
        this.setSize(500,500);
        this.setTitle("New Staff");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0xFFCED2));
        this.setLayout(null);
        
        // Panel
        staffForm = new JPanel();
        staffForm.setPreferredSize(new Dimension(300, 300));
        staffForm.setBackground(null);
        staffForm.setLayout(new GridLayout(8, 1));
        staffForm.setBounds(45, 50, 400, 350);
        
        // Employee Number
        empNo = new JLabel();
        empNo.setText("STAFF #");
        empNo.setForeground(new Color(0x202A3A));
        empNo.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        empNo.setHorizontalAlignment(JLabel.LEFT);
        empNo.setBounds(5, 50, 20, 15);
        
        txtEmpNo = new JTextField(6);
        
        // Name
        name = new JLabel();
        name.setText("NAME");
        name.setForeground(new Color(0x202A3A));
        name.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        name.setBounds(5, 100, 10, 15);
        
        txtName = new JTextField(20);
        
        // Birthday
        birthday = new JLabel();
        birthday.setText("BIRTHDAY");
        birthday.setForeground(new Color(0x202A3A));
        birthday.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        birthday.setBounds(5, 150, 20, 15);
        
        txtBirthday = new JTextField(15);
        
        // hourly Rate
        hourlyRate = new JLabel();
        hourlyRate.setText("Hourly Rate");
        hourlyRate.setForeground(new Color(0x202A3A));
        hourlyRate.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        hourlyRate.setBounds(5, 200, 25, 15);
        
        txtHourly = new JTextField(6);
        // Add text fields to panel
        staffForm.add(empNo);
        staffForm.add(txtEmpNo);
        staffForm.add(name);
        staffForm.add(txtName);
        staffForm.add(birthday);
        staffForm.add(txtBirthday);
        staffForm.add(hourlyRate);
        staffForm.add(txtHourly);
        
        // Submit Button
        staffSubmit = new JButton();
        staffSubmit.setText("SUBMIT");
        staffSubmit.setBounds(190, 425, 117, 20);
        staffSubmit.addActionListener(this);
        staffSubmit.setFocusable(false);
        staffSubmit.setFont(new Font("DIN Alternate", Font.PLAIN, 18));
        staffSubmit.setHorizontalTextPosition(JButton.CENTER);
        staffSubmit.setVerticalTextPosition(JButton.CENTER);
        staffSubmit.setForeground(Color.WHITE);
        staffSubmit.setBackground(new Color(0x202A3A));
        staffSubmit.setOpaque(true);
        staffSubmit.setBorderPainted(false);
        
        this.add(staffTitle);
        this.add(staffForm);
        this.add(staffSubmit);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==staffSubmit) {
            String empNumStr = txtEmpNo.getText().trim();
            String name = txtName.getText().trim();
            String birthday = txtBirthday.getText().trim();
            String hourlyRateStr = txtHourly.getText().trim();
            
            if(empNumStr.isEmpty() || name.isEmpty() || birthday.isEmpty() || hourlyRateStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int empNumber;
            double hourlyRate;
            
            try {
                empNumber = Integer.parseInt(empNumStr);
                if(empNumber < 10001 || empNumber > 10034) {
                    JOptionPane.showMessageDialog(this, "Employee number must be within 10001 and 10034.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Employee number must be a valid integer.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                hourlyRate = Double.parseDouble(hourlyRateStr);
                if(hourlyRate < 133.93 || hourlyRate > 535.71) {
                    JOptionPane.showMessageDialog(this, "Hourly Rate must be between 133.93 and 535.71.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Hourly rate must be a valid number.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
            this.dispose();
        }
    }
    
}
