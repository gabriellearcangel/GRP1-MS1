// src/com/mycompany/payroll/AttendancePanel.java
package com.mycompany.payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttendancePanel extends JPanel {
    private JTable attendanceTable;

    public AttendancePanel() {
        setLayout(new BorderLayout());
        
        // Title
        JLabel title = new JLabel("ATTENDANCE LOG", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Table with mock data
        String[] columns = {"Employee ID", "Name", "Date", "Time In", "Time Out"};
        Object[][] data = {
            {"10001", "Manuel Garcia", "2024-06-17", "08:58", "16:23"},
            {"10002", "Antonio Lim", "2024-06-17", "10:36", "20:55"}
        };
        attendanceTable = new JTable(data, columns);
        add(new JScrollPane(attendanceTable), BorderLayout.CENTER);

        // Button to open Mark's pop-up
        JButton btnLog = new JButton("Log Attendance");
        btnLog.addActionListener((ActionEvent e) -> {
            new MotorFrame("ATTENDANCE").setVisible(true); // Use Mark's MotorFrame
        });
        add(btnLog, BorderLayout.SOUTH);
    }
}
