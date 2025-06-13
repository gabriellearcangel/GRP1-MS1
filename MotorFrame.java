package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorFrame extends JFrame implements ActionListener{
    
    JButton payroll, attendance;
    JLabel motorPH;
    JPanel menuPane, logoPane, separator;
    
    MotorFrame() {
        ImageIcon prelogo = new ImageIcon("MotorPH Logo.png");
        Image logo = prelogo.getImage();
        Image motorLogo = logo.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        prelogo = new ImageIcon(motorLogo);
        
        logoPane = new JPanel();
        logoPane.setPreferredSize(new Dimension(145, 35));
        logoPane.setBackground(null);
        logoPane.setBounds(5, 5, 150, 35);
        
        motorPH = new JLabel();
        motorPH.setText("MotorPH");
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
        
        menuPane = new JPanel();
        menuPane.setPreferredSize(new Dimension(210,25));
        menuPane.setBackground(null);
        menuPane.setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 10));
        menuPane.setBounds(485, 13, 300, 25);
        
        attendance = new JButton();
        attendance.setBounds(625, 13, 75, 15);
        attendance.addActionListener(this);
        attendance.setText("ATTENDANCE");
        attendance.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        attendance.setForeground(new Color(0x202A3A));
        attendance.setBackground(Color.WHITE);
        attendance.setBorder(null);
        
        payroll = new JButton();
        payroll.setBounds(725, 13, 55, 15);
        payroll.addActionListener(this);
        payroll.setText("PAYROLL");
        payroll.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        payroll.setForeground(new Color(0x202A3A));
        payroll.setBackground(Color.WHITE);
        payroll.setBorder(null);
        
        // Add buttons
        menuPane.add(attendance);
        menuPane.add(payroll);
        
        // Separator
        separator = new JPanel();
        separator.setBackground(new Color(0x202A3A));
        separator.setBounds(20, 50, 746, 1);
        
        this.add(logoPane);
        this.add(menuPane);
        this.add(separator);
        
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==payroll) {
           this.dispose();
           Payroll payWin = new Payroll();
       } 
       
       if (e.getSource()==attendance) {
           this.dispose();
           Attendance attWin = new Attendance();
       }
    }
}
