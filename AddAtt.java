package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
        
public class AddAtt extends JFrame{
    
    JLabel attTitle, empNo, name, timeIn, timeOut;
    JTextField txtEmpNo, txtName, txtTimeIn, txtTimeOut;
    JPanel attForm;
    JButton attSubmit;
    
    AddAtt() {
        // Title Pane
        attTitle = new JLabel();
        attTitle.setText("Log Attendance");
        attTitle.setForeground(new Color(0x202A3A));
        attTitle.setFont(new Font("DIN Alternate", Font.BOLD, 23));
        attTitle.setVerticalAlignment(JLabel.TOP);
        attTitle.setBounds(170, 20, 170, 30);
        
        // Frame
        this.setSize(500,500);
        this.setTitle("New Attendance");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0xFFCED2));
        this.setLayout(null);
        
        // Panel
        attForm = new JPanel();
        attForm.setPreferredSize(new Dimension(300, 300));
        attForm.setBackground(null);
        attForm.setLayout(new GridLayout(8, 1));
        attForm.setBounds(45, 50, 400, 350);
        
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
        timeIn = new JLabel();
        timeIn.setText("TIME-IN");
        timeIn.setForeground(new Color(0x202A3A));
        timeIn.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        timeIn.setBounds(5, 150, 20, 15);
        
        txtTimeIn = new JTextField(15);
        
        // hourly Rate
        timeOut = new JLabel();
        timeOut.setText("TIME-OUT");
        timeOut.setForeground(new Color(0x202A3A));
        timeOut.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        timeOut.setBounds(5, 200, 25, 15);
        
        txtTimeOut = new JTextField(6);
        // Add text fields to panel
        attForm.add(empNo);
        attForm.add(txtEmpNo);
        attForm.add(name);
        attForm.add(txtName);
        attForm.add(timeIn);
        attForm.add(txtTimeIn);
        attForm.add(timeOut);
        attForm.add(txtTimeOut);
        
        // Submit Button
        attSubmit = new JButton();
        attSubmit.setText("SUBMIT");
        attSubmit.setBounds(190, 425, 117, 20);
        //attSubmit.addActionListener(this);
        attSubmit.setFocusable(false);
        attSubmit.setFont(new Font("DIN Alternate", Font.PLAIN, 18));
        attSubmit.setHorizontalTextPosition(JButton.CENTER);
        attSubmit.setVerticalTextPosition(JButton.CENTER);
        attSubmit.setForeground(Color.WHITE);
        attSubmit.setBackground(new Color(0x202A3A));
        attSubmit.setOpaque(true);
        attSubmit.setBorderPainted(false);
        
        this.add(attTitle);
        this.add(attForm);
        this.add(attSubmit);
        
        this.setVisible(true);
    }
}
