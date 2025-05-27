package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payroll extends MotorFrame implements ActionListener{
    
    JButton addPay;
    
    Payroll() {
        MotorFrame payFrame = new MotorFrame();
        
        // Add button
        addPay = new JButton();
        addPay.setText("+ PAYROLL");
        addPay.setBounds(550, 60, 200, 30);
        addPay.addActionListener(this);
        addPay.setFocusable(false);
        addPay.setFont(new Font("DIN Alternate", Font.PLAIN, 20));
        addPay.setHorizontalTextPosition(JButton.CENTER);
        addPay.setVerticalTextPosition(JButton.CENTER);
        addPay.setForeground(Color.WHITE);
        addPay.setBackground(new Color(0x202A3A));
        addPay.setOpaque(true);
        addPay.setBorderPainted(false);
        
        payFrame.add(addPay);
        
        payFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addPay) {
            AddPay payWin = new AddPay();
        }
    }
}
