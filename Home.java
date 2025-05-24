package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;

public class Home {
    JLabel homeJPG;
    
    
    Home() {
        MotorFrame homeFrame = new MotorFrame();
        
        ImageIcon homePic = new ImageIcon("home.jpg");
        Image home = homePic.getImage();
        Image homeBan = home.getScaledInstance(755, 475, java.awt.Image.SCALE_SMOOTH);
        homePic = new ImageIcon(homeBan);
        
        homeJPG = new JLabel();
        homeJPG.setIcon(homePic);
        homeJPG.setVerticalAlignment(JLabel.CENTER);
        homeJPG.setBounds(20, 38, 746, 500);
        
        homeFrame.add(homeJPG);
        homeFrame.setVisible(true);
    }
}
