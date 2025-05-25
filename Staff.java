package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Staff {
    Staff() {
        MotorFrame staffFrame = new MotorFrame();
        staffFrame.setTitle("Staff Management");
        
        // Main panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(20, 60, 746, 400);
        
        // Title
        JLabel titleLabel = new JLabel("Add New Employee", SwingConstants.CENTER);
        titleLabel.setFont(new Font("DIN Alternate", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x202A3A));
        titleLabel.setBounds(0, 20, 746, 30);
        mainPanel.add(titleLabel);
        
        // Form fields
        addLabelAndField(mainPanel, "Employee Number:", 100);
        addLabelAndField(mainPanel, "Employee Name:", 150);
        addLabelAndField(mainPanel, "Birthday:", 200);
        addLabelAndField(mainPanel, "Hourly Rate:", 250);
        
        // Add button
        JButton addButton = createButton("Add");
        addButton.setBounds(300, 320, 150, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(staffFrame, "Employee added successfully!");
            }
        });
        mainPanel.add(addButton);
        
        staffFrame.add(mainPanel);
        staffFrame.setVisible(true);
    }
    
    private void addLabelAndField(JPanel panel, String labelText, int yPos) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        label.setForeground(new Color(0x202A3A));
        label.setBounds(200, yPos, 150, 25);
        panel.add(label);
        
        JTextField field = new JTextField();
        field.setBounds(350, yPos, 200, 25);
        panel.add(field);
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        button.setForeground(new Color(0x202A3A));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0x202A3A)));
        return button;
    }
}
