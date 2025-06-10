package com.mycompany.motorph;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Attendance extends JFrame implements ActionListener {

    JButton addAtt,computeHours;
    JTable attendanceTable;
    DefaultTableModel tableModel;
    String[] columnNames = {"Employee Number", "Last Name", "First Name", "Date", "Log In", "Log Out"};
    ArrayList<String[]> employees; // To store employee data

    Attendance() {
        MotorFrame attFrame = new MotorFrame();

        // Initialize table model and JTable
        tableModel = new DefaultTableModel(columnNames, 0);
        attendanceTable = new JTable(tableModel);
        attendanceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 100, 700, 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x202A3A)));

        // Load attendance data from CSV
        loadAttendanceData();

        // Load employee data from Payroll
        loadEmployeeData();

        // Button to add new attendance
        addAtt = new JButton("+ ATTENDANCE");
        addAtt.setBounds(550, 60, 200, 30);
        addAtt.addActionListener(this);
        addAtt.setFocusable(false);
        addAtt.setFont(new Font("DIN Alternate", Font.PLAIN, 20));
        addAtt.setHorizontalTextPosition(JButton.CENTER);
        addAtt.setVerticalTextPosition(JButton.CENTER);
        addAtt.setForeground(Color.WHITE);
        addAtt.setBackground(new Color(0x202A3A));
        addAtt.setOpaque(true);
        addAtt.setBorderPainted(false);

        // Button to compute hours
        computeHours = new JButton("Compute Hours");
        computeHours.setBounds(50, 420, 150, 35);
        computeHours.setFont(new Font("DIN Alternate", Font.BOLD, 16));
        computeHours.setForeground(new Color(0x202A3A));
        computeHours.setBackground(Color.WHITE);
        computeHours.setBorder(BorderFactory.createLineBorder(new Color(0x202A3A)));
        computeHours.setFocusable(false);
        computeHours.addActionListener(this);

        // Add components to frame
        attFrame.add(scrollPane);
        attFrame.add(addAtt);
        attFrame.add(computeHours);

        attFrame.setVisible(true);
    }

    private void loadAttendanceData() {
        // Clear existing rows
        tableModel.setRowCount(0);
        try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == columnNames.length) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading attendance data: " + e.getMessage());
        }
    }

    private void loadEmployeeData() {
        employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) { // Ensure there are enough fields
                    employees.add(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading employee data: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addAtt) {
            new AddAtt(this, employees);
        }

            String[] employeeNames = new String[employees.size()];
            for (int i = 0; i < employees.size(); i++) {
                String[] emp = employees.get(i);
                employeeNames[i] = emp[1] + ", " + emp[2]; // LastName, FirstName
            }

        if (e.getSource() == computeHours) {
            new ComputeHoursFrame(employees);
        }
    }

    // Frame to display employee details and enter attendance
    class EmployeeDetailsFrame extends JFrame implements ActionListener {
        private final JTextField dateField, logInField, logOutField;
        private final JButton submitBtn, cancelBtn;
        private final String[] employeeData;

        EmployeeDetailsFrame(String[] employeeData) {
            this.employeeData = employeeData;

            setTitle("Attendance for " + employeeData[1] + " " + employeeData[2]);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            getContentPane().setBackground(Color.WHITE);

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(6, 2, 12, 12));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
            formPanel.setBackground(Color.WHITE);

            Font labelFont = new Font("DIN Alternate", Font.BOLD, 14);
            Font fieldFont = new Font("DIN Alternate", Font.PLAIN, 14);

            JLabel lblEmpNum = new JLabel("Employee Number:");
            lblEmpNum.setFont(labelFont);
            JTextField empNumField = new JTextField(employeeData[0]);
            empNumField.setFont(fieldFont);
            empNumField.setEditable(false);

            JLabel lblLastName = new JLabel("Last Name:");
            lblLastName.setFont(labelFont);
            JTextField lastNameField = new JTextField(employeeData[1]);
            lastNameField.setFont(fieldFont);
            lastNameField.setEditable(false);

            JLabel lblFirstName = new JLabel("First Name:");
            lblFirstName.setFont(labelFont);
            JTextField firstNameField = new JTextField(employeeData[2]);
            firstNameField.setFont(fieldFont);
            firstNameField.setEditable(false);

            JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
            lblDate.setFont(labelFont);
            dateField = new JTextField();
            dateField.setFont(fieldFont);

            JLabel lblLogIn = new JLabel("Log In (HH:mm):");
            lblLogIn.setFont(labelFont);
            logInField = new JTextField();
            logInField.setFont(fieldFont);

            JLabel lblLogOut = new JLabel("Log Out (HH:mm):");
            lblLogOut.setFont(labelFont);
            logOutField = new JTextField();
            logOutField.setFont(fieldFont);

            formPanel.add(lblEmpNum);
            formPanel.add(empNumField);
            formPanel.add(lblLastName);
            formPanel.add(lastNameField);
            formPanel.add(lblFirstName);
            formPanel.add(firstNameField);
            formPanel.add(lblDate);
            formPanel.add(dateField);
            formPanel.add(lblLogIn);
            formPanel.add(logInField);
            formPanel.add(lblLogOut);
            formPanel.add(logOutField);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
            buttonPanel.setBackground(Color.WHITE);

            submitBtn = new JButton("Submit");
            submitBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
            submitBtn.setForeground(Color.WHITE);
            submitBtn.setBackground(new Color(0x202A3A));
            submitBtn.setBorderPainted(false);
            submitBtn.setOpaque(true);
            submitBtn.setFocusable(false);
            submitBtn.addActionListener(this);

            cancelBtn = new JButton("Cancel");
            cancelBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
            cancelBtn.setForeground(new Color(0x202A3A));
            cancelBtn.setBackground(Color.WHITE);
            cancelBtn.setBorder(null);
            cancelBtn.setFocusable(false);
            cancelBtn.addActionListener(this);

            buttonPanel.add(submitBtn);
            buttonPanel.add(cancelBtn);

            add(formPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitBtn) {
                // Validate inputs
                if (dateField.getText().trim().isEmpty() ||
                        logInField.getText().trim().isEmpty() ||
                        logOutField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }

                // Append to CSV
                String[] newAttendance = {
                        employeeData[0], // Employee Number
                        employeeData[1], // Last Name
                        employeeData[2], // First Name
                        dateField.getText().trim(),
                        logInField.getText().trim(),
                        logOutField.getText().trim()
                };

                try (FileWriter fw = new FileWriter("attendance.csv", true)) {
                    fw.write(String.join(",", newAttendance) + System.lineSeparator());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(this, "Failed to save attendance data: " + ioException.getMessage());
                    return;
                }

                // Refresh table in parent Attendance
                loadAttendanceData();

                JOptionPane.showMessageDialog(this, "Attendance added successfully.");
                dispose();

            } else if (e.getSource() == cancelBtn) {
                dispose();
            }
        }
    }

    class AddAtt extends JFrame implements ActionListener {
        private final JComboBox<String> employeeDropdown;
        private final JTextField dateField, logInField, logOutField;
        private final JButton submitBtn, cancelBtn;
        private final Attendance parent;
        private final ArrayList<String[]> employeesList;

        AddAtt(Attendance parent, ArrayList<String[]> employees) {
            this.parent = parent;
            this.employeesList = employees;

            setTitle("New Attendance Entry");
            setSize(400, 320);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setLayout(new BorderLayout());
            getContentPane().setBackground(Color.WHITE);

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(6, 2, 12, 12));
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
            formPanel.setBackground(Color.WHITE);

            Font labelFont = new Font("DIN Alternate", Font.BOLD, 14);
            Font fieldFont = new Font("DIN Alternate", Font.PLAIN, 14);

            JLabel lblEmployee = new JLabel("Select Employee:");
            lblEmployee.setFont(labelFont);

            String[] employeeNames = new String[employees.size()];
            for (int i = 0; i < employees.size(); i++) {
                String[] emp = employees.get(i);
                employeeNames[i] = emp[1] + ", " + emp[2] + " (" + emp[0] + ")";
            }

            employeeDropdown = new JComboBox<>(employeeNames);
            employeeDropdown.setFont(fieldFont);

            JLabel lblDate = new JLabel("Date (YYYY-MM-DD):");
            lblDate.setFont(labelFont);
            dateField = new JTextField();
            dateField.setFont(fieldFont);

            JLabel lblLogIn = new JLabel("Log In (HH:mm):");
            lblLogIn.setFont(labelFont);
            logInField = new JTextField();
            logInField.setFont(fieldFont);

            JLabel lblLogOut = new JLabel("Log Out (HH:mm):");
            lblLogOut.setFont(labelFont);
            logOutField = new JTextField();
            logOutField.setFont(fieldFont);

            formPanel.add(lblEmployee);
            formPanel.add(employeeDropdown);
            formPanel.add(lblDate);
            formPanel.add(dateField);
            formPanel.add(lblLogIn);
            formPanel.add(logInField);
            formPanel.add(lblLogOut);
            formPanel.add(logOutField);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
            buttonPanel.setBackground(Color.WHITE);

            submitBtn = new JButton("Submit");
            submitBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
            submitBtn.setForeground(Color.WHITE);
            submitBtn.setBackground(new Color(0x202A3A));
            submitBtn.setBorderPainted(false);
            submitBtn.setOpaque(true);
            submitBtn.setFocusable(false);
            submitBtn.addActionListener(this);

            cancelBtn = new JButton("Cancel");
            cancelBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
            cancelBtn.setForeground(new Color(0x202A3A));
            cancelBtn.setBackground(Color.WHITE);
            cancelBtn.setBorder(null);
            cancelBtn.setFocusable(false);
            cancelBtn.addActionListener(this);

            buttonPanel.add(submitBtn);
            buttonPanel.add(cancelBtn);

            add(formPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitBtn) {
                // Validate inputs
                if (dateField.getText().trim().isEmpty() ||
                        logInField.getText().trim().isEmpty() ||
                        logOutField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }

                int selectedIndex = employeeDropdown.getSelectedIndex();
                if (selectedIndex < 0 || selectedIndex >= employeesList.size()) {
                    JOptionPane.showMessageDialog(this, "Invalid employee selected.");
                    return;
                }

                String[] selectedEmployee = employeesList.get(selectedIndex);

                // Prepare new attendance record
                String[] newAttendance = {
                        selectedEmployee[0], // Employee Number
                        selectedEmployee[1], // Last Name
                        selectedEmployee[2], // First Name
                        dateField.getText().trim(),
                        logInField.getText().trim(),
                        logOutField.getText().trim()
                };

                try (FileWriter fw = new FileWriter("attendance.csv", true)) {
                    fw.write(String.join(",", newAttendance) + System.lineSeparator());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(this, "Failed to save attendance data: " + ioException.getMessage());
                    return;
                }

                // Refresh attendance table
                parent.loadAttendanceData();

                JOptionPane.showMessageDialog(this, "Attendance added successfully.");
                dispose();

            } else if (e.getSource() == cancelBtn) {
                dispose();
            }
        }
    }

    // Frame to compute worked hours
    class ComputeHoursFrame extends JFrame implements ActionListener {
        private final JComboBox<String> employeeSelector;
        private final JComboBox<String> monthSelector;
        private final JButton computeButton;
        private final JLabel resultLabel;

        final String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        ComputeHoursFrame(ArrayList<String[]> employees) {
            setTitle("Compute Worked Hours");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            getContentPane().setBackground(Color.WHITE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.setBackground(Color.WHITE);

            ArrayList<String> employeeNamesList = new ArrayList<>();
            for (String[] emp : employees) {
                employeeNamesList.add(emp[1] + ", " + emp[2] + " (" + emp[0] + ")");
            }
            employeeSelector = new JComboBox<>(employeeNamesList.toArray(new String[0]));
            employeeSelector.setFont(new Font("DIN Alternate", Font.PLAIN, 14));
            panel.add(new JLabel("Select Employee:"));
            panel.add(employeeSelector);

            monthSelector = new JComboBox<>(months);
            monthSelector.setFont(new Font("DIN Alternate", Font.PLAIN, 14));
            panel.add(new JLabel("Select Month:"));
            panel.add(monthSelector);

            computeButton = new JButton("Compute");
            computeButton.setFont(new Font("DIN Alternate", Font.BOLD, 14));
            computeButton.setForeground(Color.WHITE);
            computeButton.setBackground(new Color(0x202A3A));
            computeButton.setBorderPainted(false);
            computeButton.setOpaque(true);
            computeButton.addActionListener(this);
            panel.add(computeButton);

            resultLabel = new JLabel(" ");
            resultLabel.setFont(new Font("DIN Alternate", Font.PLAIN, 14));
            resultLabel.setForeground(new Color(0x202A3A));
            panel.add(resultLabel);

            add(panel, BorderLayout.CENTER);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedEmpText = (String) employeeSelector.getSelectedItem();
            String selectedMonth = (String) monthSelector.getSelectedItem();

            if (selectedEmpText == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee.");
                return;
            }

            // Extract employee number from string
            String employeeNumber = selectedEmpText.substring(selectedEmpText.lastIndexOf('(') + 1, selectedEmpText.length() - 1);

            // Retrieve employee details
            String lastName = "", firstName = "";
            for (String[] emp : employees) {
                if (emp[0].equals(employeeNumber)) {
                    lastName = emp[1];
                    firstName = emp[2];
                    break;
                }
            }

            double totalHours = 0.0;
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length < 6) continue;
                    if (data[0].equals(employeeNumber)) {
                        String dateStr = data[3];
                        LocalDate date = LocalDate.parse(dateStr, formatter);
                        int recordMonth = date.getMonthValue();
                        int selectedMonthNumber = monthNametoNumber(selectedMonth);
                        if (recordMonth == selectedMonthNumber) {
                            String logIn = data[4];
                            String logOut = data[5];
                            
                            String[] logInParts = logIn.split(":");
                            String[] logOutParts = logOut.split(":");
                            
                            int logInHour = Integer.parseInt(logInParts[0]);
                            int logInMinute = Integer.parseInt(logInParts[1]);
                            int logOutHour = Integer.parseInt(logOutParts[0]);
                            int logOutMinute = Integer.parseInt(logOutParts[1]);
                            
                            int totalMinutesWorked = (logOutHour * 60 + logOutMinute) - (logInHour * 60 + logInMinute);
                            
                            // Grace Period
                            if (logInHour == 8 && logInMinute > 10) {
                                totalMinutesWorked -= (logInMinute - 10);
                            }
                            
                            if (totalMinutesWorked < 0) totalMinutesWorked = 0;
                            
                            totalHours += totalMinutesWorked / 60.0;
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error calculating worked hours: " + ex.getMessage());
            }
            
            resultLabel.setText(String.format("<html><b>Employee:</b> %s %s<br><b>Worked Hours for %s: </b> %.2f</html>", firstName, lastName, selectedMonth, totalHours));
        }
        
            private int monthNametoNumber(String monthName) {
                switch (monthName) {
                    case "January": return 1;
                    case "February": return 2;
                    case "March": return 3;
                    case "April": return 4;
                    case "May": return 5;
                    case "June": return 6;
                    case "July": return 7;
                    case "August": return 8;
                    case "September": return 9;
                    case "October": return 10;
                    case "November": return 11;
                    case "December": return 12;
                    default: return 0;
                }
            }    
    }
}
