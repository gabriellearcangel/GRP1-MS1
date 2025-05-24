package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;

public class Staff {
    Staff() {
        MotorFrame staffFrame = new MotorFrame();
        
        // Your code here
        JLabel label = new JLabel("Employee stuff here");
        label.setBounds(20, 80, 200, 50);
        staffFrame.add(label);
        staffFrame.setVisible(true);
    }
}
