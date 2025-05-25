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

public class MotorPH extends JFrame {
    public MotorPH() {
        setTitle("MotorPH Staff Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JLabel header = new JLabel("MotorPH Staff Management", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        mainPanel.add(header, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Attendance Button (neutral style)
        JButton attendanceButton = createStyledButton("Log Attendance");
        attendanceButton.addActionListener(e -> new AttendanceFrame(this).setVisible(true));

        // Payroll Button (neutral style)
        JButton payrollButton = createStyledButton("Generate Payroll");
        payrollButton.addActionListener(e -> new PayrollFrame(this).setVisible(true));

        // Add buttons to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(attendanceButton, gbc);
        
        gbc.gridy = 1;
        buttonPanel.add(payrollButton, gbc);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    // Neutral button style
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        // Removed background color - uses default system style
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            }
            
            new MotorPH().setVisible(true);
        });
    }
}

