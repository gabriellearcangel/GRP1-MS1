package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MotorFrame extends JFrame implements ActionListener{
    
    JButton payroll, attendance, staff, home;
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
        
        this.setSize(800, 500);
        this.setTitle("MotorPH Payroll");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        
        menuPane = new JPanel();
        menuPane.setPreferredSize(new Dimension(210,25));
        menuPane.setBackground(null);
        menuPane.setLayout(new FlowLayout(FlowLayout.TRAILING, 20, 10));
        menuPane.setBounds(485, 13, 300, 25);
        
        staff = new JButton();
        staff.setBounds(530, 13, 40, 15);
        staff.addActionListener(this);
        staff.setText("STAFF");
        staff.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        staff.setForeground(new Color(0x202A3A));
        staff.setBackground(Color.WHITE);
        staff.setBorder(null);
        
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
        
        home = new JButton();
        home.setBounds(430, 13, 40, 15);
        home.addActionListener(this);
        home.setText("HOME");
        home.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        home.setForeground(new Color(0x202A3A));
        home.setBackground(Color.WHITE);
        home.setBorder(null);
        
        // Add buttons
        menuPane.add(home);
        menuPane.add(staff);
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
       
       if (e.getSource()==staff) {
           this.dispose();
           Staff staffWin = new Staff();
       }
       
       if (e.getSource()==home) {
           this.dispose();
           Home homeWin = new Home();
       }
    }
}
