package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payroll extends MotorFrame {
    Payroll() {
        MotorFrame payFrame = new MotorFrame();
        payFrame.setTitle("Generate Payroll");
        
        // Main panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(20, 60, 746, 400);
        
        // Title
        JLabel titleLabel = new JLabel("Generate Payroll", SwingConstants.CENTER);
        titleLabel.setFont(new Font("DIN Alternate", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x202A3A));
        titleLabel.setBounds(0, 20, 746, 30);
        mainPanel.add(titleLabel);
        
        // Form fields
        addLabelAndField(mainPanel, "Employee ID:", 100);
        addLabelAndField(mainPanel, "Employee Name:", 150);
        
        // Pay period dropdown
        JLabel periodLabel = new JLabel("Pay Period:");
        periodLabel.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        periodLabel.setForeground(new Color(0x202A3A));
        periodLabel.setBounds(200, 200, 150, 25);
        mainPanel.add(periodLabel);
        
        JComboBox<String> periodCombo = new JComboBox<>();
        periodCombo.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        periodCombo.setBounds(350, 200, 300, 25);
        
        LocalDate now = LocalDate.now();
        String firstHalf = now.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("MMM d")) + 
                          " - " + now.withDayOfMonth(15).format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        String secondHalf = now.withDayOfMonth(16).format(DateTimeFormatter.ofPattern("MMM d")) + 
                           " - " + now.withDayOfMonth(now.lengthOfMonth()).format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
        
        periodCombo.addItem(firstHalf);
        periodCombo.addItem(secondHalf);
        mainPanel.add(periodCombo);
        
        addLabelAndField(mainPanel, "Basic Salary:", 250);
        addLabelAndField(mainPanel, "Overtime Hours:", 300);
        
        // Generate button
        JButton generateButton = createButton("Generate Payroll");
        generateButton.setBounds(300, 350, 150, 30);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(payFrame, "Payroll generated successfully!");
            }
        });
        mainPanel.add(generateButton);
        
        payFrame.add(mainPanel);
        payFrame.setVisible(true);
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
    
