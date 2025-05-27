package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;

public class AddPay extends JFrame{
    JLabel payTitle, empNo, name, hrsWorked, pagIbig, sss, philHealth;
    JTextField txtEmpNo, txtName, txtHrsWorked, txtPagibig, txtSSS, txtPhilhealth;
    JPanel payForm;
    JButton paySubmit;
    
    AddPay() {
        // Title Pane
        payTitle = new JLabel();
        payTitle.setText("New Payroll");
        payTitle.setForeground(new Color(0x202A3A));
        payTitle.setFont(new Font("DIN Alternate", Font.BOLD, 23));
        payTitle.setVerticalAlignment(JLabel.TOP);
        payTitle.setBounds(170, 20, 170, 30);
        
        // Frame
        this.setSize(500,500);
        this.setTitle("New Payroll");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(0xFFCED2));
        this.setLayout(null);
        
        // Panel
        payForm = new JPanel();
        payForm.setPreferredSize(new Dimension(300, 300));
        payForm.setBackground(null);
        payForm.setLayout(new GridLayout(6, 2));
        payForm.setBounds(45, 50, 400, 350);
        
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
        name.setBounds(5, 60, 10, 15);
        
        txtName = new JTextField(20);
        
        // Hours Worked
        hrsWorked = new JLabel();
            hrsWorked.setText("HOURS");
        hrsWorked.setForeground(new Color(0x202A3A));
        hrsWorked.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        hrsWorked.setBounds(5, 70, 20, 15);
        
        txtHrsWorked = new JTextField(15);
        
        // Pagibig
        pagIbig = new JLabel();
        pagIbig.setText("PAGIBIG");
        pagIbig.setForeground(new Color(0x202A3A));
        pagIbig.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        pagIbig.setBounds(5, 80, 25, 15);
        
        txtPagibig = new JTextField(6);
        
        // SSS
        sss = new JLabel();
        sss.setText("SSS");
        sss.setForeground(new Color(0x202A3A));
        sss.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        sss.setBounds(5, 90, 25, 15);
        
        txtSSS = new JTextField(6);
        
        // Philhealth
        philHealth = new JLabel();
        philHealth.setText("PHILHEALTH");
        philHealth.setForeground(new Color(0x202A3A));
        philHealth.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        philHealth.setBounds(5, 100, 25, 15);
        
        txtPhilhealth = new JTextField(6);
        
        // Add text fields to panel
        payForm.add(empNo);
        payForm.add(txtEmpNo);
        payForm.add(name);
        payForm.add(txtName);
        payForm.add(hrsWorked);
        payForm.add(txtHrsWorked);
        payForm.add(pagIbig);
        payForm.add(txtPagibig);
        payForm.add(sss);
        payForm.add(txtSSS);
        payForm.add(philHealth);
        payForm.add(txtPhilhealth);
       
        // Submit Button
        paySubmit = new JButton();
        paySubmit.setText("SUBMIT");
        paySubmit.setBounds(190, 425, 117, 20);
        //paySubmit.addActionListener(this);
        paySubmit.setFocusable(false);
        paySubmit.setFont(new Font("DIN Alternate", Font.PLAIN, 18));
        paySubmit.setHorizontalTextPosition(JButton.CENTER);
        paySubmit.setVerticalTextPosition(JButton.CENTER);
        paySubmit.setForeground(Color.WHITE);
        paySubmit.setBackground(new Color(0x202A3A));
        paySubmit.setOpaque(true);
        paySubmit.setBorderPainted(false);
        
        this.add(payTitle);
        this.add(payForm);
        this.add(paySubmit);
        
        this.setVisible(true);
    }
}
