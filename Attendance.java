package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;

public class Attendance {
    Attendance () {
        MotorFrame attFrame = new MotorFrame();
        
        // Your code here
        JLabel label = new JLabel("Attendance stuff here");
        label.setBounds(20, 80, 200, 50);
        attFrame.add(label);
        attFrame.setVisible(true);
    }
}
