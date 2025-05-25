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
import java.time.YearMonth;

public class PayrollFrame extends JDialog {
    public PayrollFrame(JFrame parent) {
        super(parent, "Generate Payroll", true);
        setSize(400, 350); // Compact size
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
        panel.add(new JLabel("Pay Period:"), gbc);
        
        gbc.gridx = 1;
        JPanel periodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JComboBox<String> monthCombo = new JComboBox<>(
            new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        monthCombo.setPreferredSize(new Dimension(80, 25));
        JTextField yearField = new JTextField(String.valueOf(YearMonth.now().getYear()), 4);
        yearField.setPreferredSize(new Dimension(50, 25));
        periodPanel.add(monthCombo);
        periodPanel.add(new JLabel("/"));
        periodPanel.add(yearField);
        panel.add(periodPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Basic Salary:"), gbc);
        
        gbc.gridx = 1;
        JTextField salaryField = new JTextField(15);
        panel.add(salaryField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Overtime Hours:"), gbc);
        
        gbc.gridx = 1;
        JTextField overtimeField = new JTextField("0", 15);
        panel.add(overtimeField, gbc);

        // Buttons (compact size)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton generateButton = new JButton("Generate");
        generateButton.setPreferredSize(new Dimension(100, 25));
        generateButton.addActionListener(e -> {
            // Add payroll calculation logic
            JOptionPane.showMessageDialog(this, 
                "Payroll generated!\nNet Pay: ₱25,000.00", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 25));
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(generateButton);
        panel.add(buttonPanel, gbc);

        add(panel);
    }
}
    
    

