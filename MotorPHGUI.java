package com.mycompany.motorph;

import java.awt.*;
import javax.swing.*;

public class MotorPHGUI {

    public static void main(String[] args) {
       IDandPasswords idandPasswords = new IDandPasswords();
       
       LogIn loginPage = new LogIn(idandPasswords.getLoginInfo());
        
    }
}
