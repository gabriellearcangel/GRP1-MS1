package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Staff extends JFrame implements ActionListener{ 
    
    JButton addEmp;
    
    Staff() {
        MotorFrame staffFrame = new MotorFrame();
        
        // Add Employee Button
        addEmp = new JButton();
        addEmp.setText("+ STAFF");
        addEmp.setBounds(650, 60, 117, 30);
        addEmp.addActionListener(this);
        addEmp.setFocusable(false);
        addEmp.setFont(new Font("DIN Alternate", Font.PLAIN, 20));
        addEmp.setHorizontalTextPosition(JButton.CENTER);
        addEmp.setVerticalTextPosition(JButton.CENTER);
        addEmp.setForeground(Color.WHITE);
        addEmp.setBackground(new Color(0x202A3A));
        addEmp.setOpaque(true);
        addEmp.setBorderPainted(false);
        
        staffFrame.add(addEmp);
        
       
        staffFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addEmp) {
            AddEmp empWin = new AddEmp();
        }
    }
    
}
