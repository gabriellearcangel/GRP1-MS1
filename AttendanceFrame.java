/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.payroll;

/**
 *
 * @author Mark Goyon
 */
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class AttendanceFrame extends JDialog {
    public AttendanceFrame(JFrame parent) {
        super(parent, "Log Attendance", true);
        setSize(350, 300); // Smaller frame size
        setLocationRelativeTo(parent);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form Fields
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Employee ID:"), gbc);
        
        gbc.gridx = 1;
        JTextField empIdField = new JTextField(15);
        panel.add(empIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Date:"), gbc);
        
        gbc.gridx = 1;
        JTextField dateField = new JTextField(LocalDate.now().toString(), 15);
        panel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Time In:"), gbc);
        
        gbc.gridx = 1;
        JTextField timeInField = new JTextField(LocalTime.now().toString().substring(0, 5), 15);
        panel.add(timeInField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        JComboBox<String> statusCombo = new JComboBox<>(
            new String[]{"Present", "Late", "Absent", "On Leave"});
        statusCombo.setPreferredSize(new Dimension(150, 25));
        panel.add(statusCombo, gbc);

        // Buttons (smaller size)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 25)); // Smaller button
        submitButton.addActionListener(e -> {
            // Add validation logic here
            JOptionPane.showMessageDialog(this, "Attendance recorded!");
            dispose();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 25)); // Smaller button
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(submitButton);
        panel.add(buttonPanel, gbc);

        add(panel);
    }
}
    

    

