package com.mycompany.motorph;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Attendance {
    Attendance() {
        MotorFrame attFrame = new MotorFrame();
        attFrame.setTitle("Attendance Log");
        
        // Main panel with increased height
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(20, 60, 746, 500); // Increased height
        
        // Title
        JLabel titleLabel = new JLabel("Attendance Log", SwingConstants.CENTER);
        titleLabel.setFont(new Font("DIN Alternate", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x202A3A));
        titleLabel.setBounds(0, 10, 746, 30);
        mainPanel.add(titleLabel);
        
        // Form fields with adjusted vertical spacing (reduced from 50 to 40 pixels)
        int yPosition = 60;
        addLabelAndField(mainPanel, "Employee ID:", yPosition);
        yPosition += 40;
        addLabelAndField(mainPanel, "Employee Name:", yPosition);
        yPosition += 40;
        
        // Date field
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(0x202A3A));
        dateLabel.setBounds(200, yPosition, 150, 25);
        mainPanel.add(dateLabel);
        
        JTextField dateField = new JTextField();
        dateField.setBounds(350, yPosition, 200, 25);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateField.setText(LocalDate.now().format(dtf));
        mainPanel.add(dateField);
        yPosition += 40;
        
        // Time In field
        JPanel timeInPanel = createTimeInputPanel("Time In:", yPosition);
        mainPanel.add(timeInPanel);
        yPosition += 40;
        
        // Time Out field
        JPanel timeOutPanel = createTimeInputPanel("Time Out:", yPosition);
        mainPanel.add(timeOutPanel);
        yPosition += 40;
        
        // Status dropdown
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(0x202A3A));
        statusLabel.setBounds(200, yPosition, 150, 25);
        mainPanel.add(statusLabel);
        
        JComboBox<String> statusCombo = new JComboBox<>();
        statusCombo.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        statusCombo.setBounds(350, yPosition, 220, 25);
        statusCombo.addItem("Present");
        statusCombo.addItem("Absent");
        statusCombo.addItem("Late");
        statusCombo.addItem("On Leave");
        mainPanel.add(statusCombo);
        yPosition += 50;
        
        // Save button centered at bottom
        JButton saveButton = createButton("Save Attendance");
        saveButton.setBounds(300, yPosition, 200, 30);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(attFrame, "Attendance record saved!");
        });
        mainPanel.add(saveButton);
        
        attFrame.add(mainPanel);
        attFrame.setVisible(true);
    }
    
    private JPanel createTimeInputPanel(String labelText, int yPos) {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(150, yPos, 450, 30);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        label.setForeground(new Color(0x202A3A));
        label.setBounds(50, 0, 100, 25);
        panel.add(label);
        
        JTextField timeField = new JTextField();
        timeField.setBounds(150, 0, 150, 25);
        panel.add(timeField);
        
        JButton nowButton = createSmallButton("Now");
        nowButton.setBounds(310, 0, 60, 25);
        nowButton.addActionListener(e -> {
            timeField.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
        panel.add(nowButton);
        
        return panel;
    }
    
    private void addLabelAndField(JPanel panel, String labelText, int yPos) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("DIN Alternate", Font.PLAIN, 12));
        label.setForeground(new Color(0x202A3A));
        label.setBounds(200, yPos, 150, 25);
        panel.add(label);
        
        JTextField field = new JTextField();
        field.setBounds(350, yPos, 220, 25); // Slightly wider fields
        panel.add(field);
    }
    
    private JButton createSmallButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("DIN Alternate", Font.PLAIN, 10));
        button.setForeground(new Color(0x202A3A));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0x202A3A)));
        button.setMargin(new Insets(0, 2, 0, 2)); // Tighter padding
        return button;
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
