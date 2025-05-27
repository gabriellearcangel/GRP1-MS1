package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Attendance extends JFrame implements ActionListener{
    
    JButton addAtt;
    
    Attendance () {
        MotorFrame attFrame = new MotorFrame();
        
        // Add button
        addAtt = new JButton();
        addAtt.setText("+ ATTENDANCE");
        addAtt.setBounds(550, 60, 200, 30);
        addAtt.addActionListener(this);
        addAtt.setFocusable(false);
        addAtt.setFont(new Font("DIN Alternate", Font.PLAIN, 20));
        addAtt.setHorizontalTextPosition(JButton.CENTER);
        addAtt.setVerticalTextPosition(JButton.CENTER);
        addAtt.setForeground(Color.WHITE);
        addAtt.setBackground(new Color(0x202A3A));
        addAtt.setOpaque(true);
        addAtt.setBorderPainted(false);
        
        attFrame.add(addAtt);
        
        attFrame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addAtt) {
            AddAtt attWin = new AddAtt();
        }
    }
}
