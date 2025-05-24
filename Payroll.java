package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;

public class Payroll extends MotorFrame{
    Payroll() {
        MotorFrame payFrame = new MotorFrame();
        // Your code here
        JLabel label = new JLabel("Payroll stuff here");
        label.setBounds(20, 80, 150, 50);
        payFrame.add(label);
        payFrame.setVisible(true);
    }
}
