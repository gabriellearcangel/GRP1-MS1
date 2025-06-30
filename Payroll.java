package com.mycompany.motorph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.event.ListSelectionEvent;

public class Payroll extends MotorFrame implements ActionListener {

    JButton newEmployeeBtn, viewEmployeeBtn, updateEmployeeBtn, deleteEmployeeBtn;
    JTable employeeTable;
    DefaultTableModel tableModel;
    String[] columnNames = {"Employee #", "Last Name", "First Name", "SSS #", "PhilHealth #", "TIN", "Pag-IBIG #"};
    JTextField empNumField, lastNameField, firstNameField, sssField, philHealthField, tinField, pagIbigField;
    JPasswordField passwordField;
    JPanel detailsPanel;
    
    Payroll() {
        MotorFrame payFrame = new MotorFrame();
        
        empNumField = new JTextField();
        lastNameField = new JTextField();
        firstNameField = new JTextField();
        sssField = new JTextField();
        philHealthField = new JTextField();
        tinField = new JTextField();
        pagIbigField = new JTextField();
        passwordField = new JPasswordField();
        
        detailsPanel = new JPanel(new GridLayout(8, 2));
        detailsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        detailsPanel.add(new JLabel("Employee #:"));
        detailsPanel.add(empNumField);
        detailsPanel.add(new JLabel("Last Name:"));
        detailsPanel.add(lastNameField);
        detailsPanel.add(new JLabel("First Name:"));
        detailsPanel.add(firstNameField);
        detailsPanel.add(new JLabel("SSS #:"));
        detailsPanel.add(sssField);
        detailsPanel.add(new JLabel("Philhealth #:"));
        detailsPanel.add(philHealthField);
        detailsPanel.add(new JLabel("TIN:"));
        detailsPanel.add(tinField);
        detailsPanel.add(new JLabel("Pag-Ibig #:"));
        detailsPanel.add(pagIbigField);
        detailsPanel.add(new JLabel("Password:"));
        detailsPanel.add(passwordField);
        detailsPanel.setBounds(50, 450, 700, 220);
        detailsPanel.setVisible(false);

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        employeeTable = new JTable(tableModel);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(50, 75, 700, 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x202A3A)));
        
        loadEmployeeData();
        
        employeeTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = employeeTable.getSelectedRow();
                updateEmployeeBtn.setEnabled(selectedRow != -1);
                if (selectedRow != -1) {
                    empNumField.setText((String) tableModel.getValueAt(selectedRow, 0));
                    lastNameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                    firstNameField.setText((String) tableModel.getValueAt(selectedRow, 2));
                    sssField.setText((String) tableModel.getValueAt(selectedRow, 3));
                    philHealthField.setText((String) tableModel.getValueAt(selectedRow, 4));
                    tinField.setText((String) tableModel.getValueAt(selectedRow, 5));
                    pagIbigField.setText((String) tableModel.getValueAt(selectedRow, 6));
                    passwordField.setText("");
                    
                    detailsPanel.setVisible(true);
                } else {
                    empNumField.setText("");
                    lastNameField.setText("");
                    firstNameField.setText("");
                    sssField.setText("");
                    philHealthField.setText("");
                    tinField.setText("");
                    pagIbigField.setText("");
                    passwordField.setText("");
                    
                    detailsPanel.setVisible(false);
                }
            }
        });

        loadEmployeeData();

        viewEmployeeBtn = new JButton("View Employee");
        viewEmployeeBtn.setBounds(50, 400, 150, 35);
        viewEmployeeBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
        viewEmployeeBtn.setForeground(new Color(0x202A3A));
        viewEmployeeBtn.setBackground(Color.WHITE);
        viewEmployeeBtn.setBorder(BorderFactory.createLineBorder(new Color(0x202A3A)));
        viewEmployeeBtn.setFocusable(false);
        viewEmployeeBtn.addActionListener(this);

        newEmployeeBtn = new JButton("New Employee");
        newEmployeeBtn.setBounds(600, 400, 150, 35);
        newEmployeeBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
        newEmployeeBtn.setForeground(Color.WHITE);
        newEmployeeBtn.setBackground(new Color(0x202A3A));
        newEmployeeBtn.setBorderPainted(false);
        newEmployeeBtn.setOpaque(true);
        newEmployeeBtn.setFocusable(false);
        newEmployeeBtn.addActionListener(this);
        
        updateEmployeeBtn = new JButton("Update");
        updateEmployeeBtn.setBounds(250, 400, 150, 35);
        updateEmployeeBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
        updateEmployeeBtn.setForeground(Color.WHITE);
        updateEmployeeBtn.setBackground(new Color(0x202A3A));
        updateEmployeeBtn.setBorderPainted(false);
        updateEmployeeBtn.setOpaque(true);
        updateEmployeeBtn.setFocusable(false);
        updateEmployeeBtn.addActionListener(this);
        
        deleteEmployeeBtn = new JButton("Delete");
        deleteEmployeeBtn.setBounds(425, 400, 150, 35);
        deleteEmployeeBtn.setFont(new Font("DIN Alternate", Font.BOLD, 16));
        deleteEmployeeBtn.setForeground(Color.WHITE);
        deleteEmployeeBtn.setBackground(new Color(0x202A3A));
        deleteEmployeeBtn.setBorderPainted(false);
        deleteEmployeeBtn.setOpaque(true);
        deleteEmployeeBtn.setFocusable(false);
        deleteEmployeeBtn.addActionListener(this);

        payFrame.add(scrollPane);
        payFrame.add(viewEmployeeBtn);
        payFrame.add(newEmployeeBtn);
        payFrame.add(updateEmployeeBtn);
        payFrame.add(deleteEmployeeBtn);
        payFrame.add(detailsPanel);

        payFrame.setVisible(true);
    }

    private void loadEmployeeData() {
        tableModel.setRowCount(0);
        
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if(data.length >= 7) {
                    String[] tableData = new String[7];
                    System.arraycopy(data, 0, tableData, 0, 7);
                    tableModel.addRow(tableData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableModel.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newEmployeeBtn) {
            new AddNewEmployeeFrame(this);
        }

        if (e.getSource() == viewEmployeeBtn) {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
                String lastName = (String) tableModel.getValueAt(selectedRow, 1);
                String firstName = (String) tableModel.getValueAt(selectedRow, 2);
                String sssNumber = (String) tableModel.getValueAt(selectedRow, 3);
                String philHealthNumber = (String) tableModel.getValueAt(selectedRow, 4);
                String tin = (String) tableModel.getValueAt(selectedRow, 5);
                String pagIbigNumber = (String) tableModel.getValueAt(selectedRow, 6);
                
                new EmployeeDetailsFrame(employeeNumber, lastName, firstName, sssNumber, philHealthNumber, tin, pagIbigNumber);         
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee.");
            }
        }
        
        if (e.getSource() == updateEmployeeBtn) {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                String originalEmployeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
                String[] updatedData = {
                    empNumField.getText().trim(),
                    lastNameField.getText().trim(),
                    firstNameField.getText().trim(),
                    sssField.getText().trim(),
                    philHealthField.getText().trim(),
                    tinField.getText().trim(),
                    pagIbigField.getText().trim()
                };
                
                for (int i = 0; i < updatedData.length; i++) {
                    tableModel.setValueAt(updatedData[i], selectedRow, i);
                }
                
                updateEmployeeInCSV(updatedData, originalEmployeeNumber);
                JOptionPane.showMessageDialog(null, "Employee updated successfully.");
                
                loadEmployeeData();
                empNumField.setText("");
                lastNameField.setText("");
                firstNameField.setText("");
                sssField.setText("");
                philHealthField.setText("");
                tinField.setText("");
                pagIbigField.setText("");
                passwordField.setText("");
                
                detailsPanel.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee to update.");
            }
        }
    
        if (e.getSource() == deleteEmployeeBtn) {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                String employeeNumber = (String) tableModel.getValueAt(selectedRow, 0);
                deleteEmployeeFromCSV(employeeNumber);
                loadEmployeeData();
                JOptionPane.showMessageDialog(null, "Employee deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee to delete.");
            }
        }
    }
    
    private void updateEmployeeInCSV(String[] updatedData, String originalEmployeeNumber) {
        try {
            File inputFile = new File("employees.csv");
            File tempFile = new File("temp_employees.csv");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    writer.write(line);
                    writer.newLine();
                    isFirstLine = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(originalEmployeeNumber)) {
                    String newLine = String.join(",", updatedData);
                    if (data.length >= 8 && new String(passwordField.getPassword()).trim().isEmpty()) {
                        newLine += "," + data[7];
                    } else {
                        newLine += "," + new String(passwordField.getPassword()).trim();
                    }
                    
                    writer.write(newLine);
                } else {
                    writer.write(line);
                }
                
                writer.newLine();
    }
            
            writer.close();
            reader.close();
            
            if (!inputFile.delete()) {
                throw new IOException("Could not delete original file");
            }
            
            if (!tempFile.renameTo(inputFile)) {
                throw new IOException("Could not rename temp file");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating employee data: " + e.getMessage());
        }
    }
    
    private void deleteEmployeeFromCSV(String employeeNumber) {
        try {
            File inputFile = new File("employees.csv");
            File tempFile = new File("temp_employees.csv");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String line;
            while((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (!data[0].equals(employeeNumber)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            writer.close();
            reader.close();
            
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error deleting employee data: " + e.getMessage());
        }
    }

    // Frame to display employee details and compute salary with month selection
    class EmployeeDetailsFrame extends JFrame implements ActionListener {
        JComboBox<String> monthSelector;
        JTextField hourlyRateField;
        JButton computeButton;
        JLabel salaryInfoLabel;

        String employeeNumber, lastName, firstName, sssNumber, philHealthNumber, tin, pagIbigNumber;

        final String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        EmployeeDetailsFrame(String employeeNumber, String lastName, String firstName, String sssNumber,
                             String philHealthNumber, String tin, String pagIbigNumber) {
            this.employeeNumber = employeeNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.sssNumber = sssNumber;
            this.philHealthNumber = philHealthNumber;
            this.tin = tin;
            this.pagIbigNumber = pagIbigNumber;

            setTitle("Employee Details - " + firstName + " " + lastName);
            setSize(420, 475);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            setResizable(true);
            getContentPane().setBackground(Color.WHITE);

            JPanel detailsPanel = new JPanel();
            detailsPanel.setBackground(Color.WHITE);
            detailsPanel.setLayout(new GridLayout(7, 2, 10, 10));
            detailsPanel.setBorder(new EmptyBorder(20, 20, 15, 20));

            detailsPanel.add(new JLabel("Employee #:"));
            detailsPanel.add(new JLabel(employeeNumber));
            detailsPanel.add(new JLabel("Last Name:"));
            detailsPanel.add(new JLabel(lastName));
            detailsPanel.add(new JLabel("First Name:"));
            detailsPanel.add(new JLabel(firstName));
            detailsPanel.add(new JLabel("SSS #:"));
            detailsPanel.add(new JLabel(sssNumber));
            detailsPanel.add(new JLabel("PhilHealth #:"));
            detailsPanel.add(new JLabel(philHealthNumber));
            detailsPanel.add(new JLabel("TIN:"));
            detailsPanel.add(new JLabel(tin));
            detailsPanel.add(new JLabel("Pag-IBIG #:"));
            detailsPanel.add(new JLabel(pagIbigNumber));

            JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            monthPanel.setBackground(Color.WHITE);
            monthPanel.setBorder(new EmptyBorder(0, 20, 10, 20));

            JLabel monthLabel = new JLabel("Select Month:");
            monthLabel.setFont(new Font("DIN Alternate", Font.BOLD, 14));
            monthSelector = new JComboBox<>(months);
            monthSelector.setFont(new Font("DIN Alternate", Font.PLAIN, 14));
            monthSelector.setPreferredSize(new Dimension(150, 28));
            monthPanel.add(monthLabel);
            monthPanel.add(monthSelector);

            JPanel computePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
            computePanel.setBackground(Color.WHITE);
            computePanel.setBorder(new EmptyBorder(0, 20, 20, 20));

            computePanel.add(new JLabel("Hourly Rate:"));
            hourlyRateField = new JTextField(10);
            hourlyRateField.setFont(new Font("DIN Alternate", Font.PLAIN, 14));
            computePanel.add(hourlyRateField);

            computeButton = new JButton("Compute");
            computeButton.setFont(new Font("DIN Alternate", Font.BOLD, 14));
            computeButton.setForeground(Color.WHITE);
            computeButton.setBackground(new Color(0x202A3A));
            computeButton.setBorderPainted(false);
            computeButton.setOpaque(true);
            computeButton.addActionListener(this);
            computePanel.add(computeButton);

            salaryInfoLabel = new JLabel(" ");
            salaryInfoLabel.setFont(new Font("DIN Alternate", Font.PLAIN, 14));
            salaryInfoLabel.setForeground(new Color(0x202A3A));
            salaryInfoLabel.setBorder(new EmptyBorder(0, 20, 10, 20));

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBackground(Color.WHITE);
            bottomPanel.add(salaryInfoLabel, BorderLayout.CENTER);

            // Compose main panel with vertical stack
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
            mainPanel.add(detailsPanel);
            mainPanel.add(monthPanel);
            mainPanel.add(computePanel);
            mainPanel.add(bottomPanel);

            add(mainPanel, BorderLayout.CENTER);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == computeButton) {
                try {
                    double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                    if (hourlyRate < 0) {
                        JOptionPane.showMessageDialog(this, "Hourly rate must be positive.");
                        return;
                    }

                    String selectedMonth = (String) monthSelector.getSelectedItem();

                    double workHours = computeWorkedHours(employeeNumber, selectedMonth);

                    double grossPay = hourlyRate * workHours;

                    double sssDeduction = grossPay * 0.11;
                    double philHealthDeduction = grossPay * 0.03;
                    double pagIbigDeduction = grossPay * 0.02;

                    double netPay = grossPay - (sssDeduction + philHealthDeduction + pagIbigDeduction);

                    String resultHtml = "<html>" +
                            "<b>Salary Computation for " + selectedMonth + "</b><br>" +
                            String.format("Gross Pay: ₱ %.2f<br>", grossPay) +
                            String.format("SSS Deduction (11%%): ₱ %.2f<br>", sssDeduction) +
                            String.format("PhilHealth Deduction (3%%): ₱ %.2f<br>", philHealthDeduction) +
                            String.format("Pag-IBIG Deduction (2%%): ₱ %.2f<br>", pagIbigDeduction) +
                            String.format("<b>Net Pay: ₱ %.2f</b>", netPay) +
                            "</html>";

                    salaryInfoLabel.setText(resultHtml);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Enter a valid number for hourly rate.");
                }
            }
        }
        
    private double computeWorkedHours(String empNumber, String monthName) {
        double totalHours = 0.0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int monthNum = monthNametoNumber(monthName);
        if (monthNum == 0) return 0.0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("attendance.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
               String[] data = line.split(",");
               if (data.length < 6) continue;
               if (data[0].equals(empNumber)) {
                   String dateStr = data[3];
                   LocalDate date = LocalDate.parse(dateStr, formatter);
                   if (date.getMonthValue() == monthNum) {
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
        
        return totalHours;
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

    class AddNewEmployeeFrame extends JFrame implements ActionListener {
        private final JTextField empNumField, lastNameField, firstNameField, sssField, philHealthField, tinField, pagIbigField;
        private final JButton submitBtn, cancelBtn;
        private final Payroll parent;

        AddNewEmployeeFrame(Payroll parent) {
            this.parent = parent;
            setTitle("New Employee");
            setSize(400, 480);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);
            setLayout(new BorderLayout());
            getContentPane().setBackground(Color.WHITE);

            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(8, 2, 12, 12));
            formPanel.setBorder(new EmptyBorder(20, 30, 10, 30));
            formPanel.setBackground(Color.WHITE);

            Font labelFont = new Font("DIN Alternate", Font.BOLD, 14);
            Font fieldFont = new Font("DIN Alternate", Font.PLAIN, 14);

            JLabel lblEmpNum = new JLabel("Employee Number:");
            lblEmpNum.setFont(labelFont);
            empNumField = new JTextField();
            empNumField.setFont(fieldFont);

            JLabel lblLastName = new JLabel("Last Name:");
            lblLastName.setFont(labelFont);
            lastNameField = new JTextField();
            lastNameField.setFont(fieldFont);

            JLabel lblFirstName = new JLabel("First Name:");
            lblFirstName.setFont(labelFont);
            firstNameField = new JTextField();
            firstNameField.setFont(fieldFont);

            JLabel lblSSS = new JLabel("SSS Number:");
            lblSSS.setFont(labelFont);
            sssField = new JTextField();
            sssField.setFont(fieldFont);

            JLabel lblPhilHealth = new JLabel("PhilHealth Number:");
            lblPhilHealth.setFont(labelFont);
            philHealthField = new JTextField();
            philHealthField.setFont(fieldFont);

            JLabel lblTIN = new JLabel("TIN:");
            lblTIN.setFont(labelFont);
            tinField = new JTextField();
            tinField.setFont(fieldFont);

            JLabel lblPagIbig = new JLabel("Pag-IBIG Number:");
            lblPagIbig.setFont(labelFont);
            pagIbigField = new JTextField();
            pagIbigField.setFont(fieldFont);
            
            JLabel lblPassword = new JLabel("Password:");
            lblPassword.setFont(labelFont);
            passwordField = new JPasswordField();
            passwordField.setFont(fieldFont);

            formPanel.add(lblEmpNum);
            formPanel.add(empNumField);
            formPanel.add(lblLastName);
            formPanel.add(lastNameField);
            formPanel.add(lblFirstName);
            formPanel.add(firstNameField);
            formPanel.add(lblSSS);
            formPanel.add(sssField);
            formPanel.add(lblPhilHealth);
            formPanel.add(philHealthField);
            formPanel.add(lblTIN);
            formPanel.add(tinField);
            formPanel.add(lblPagIbig);
            formPanel.add(pagIbigField);
            formPanel.add(lblPassword);
            formPanel.add(passwordField);

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
                if (empNumField.getText().trim().isEmpty() ||
                        lastNameField.getText().trim().isEmpty() ||
                        firstNameField.getText().trim().isEmpty() ||
                        sssField.getText().trim().isEmpty() ||
                        philHealthField.getText().trim().isEmpty() ||
                        tinField.getText().trim().isEmpty() ||
                        pagIbigField.getText().trim().isEmpty() ||
                        passwordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }
                
                String [] employeeTableData = {
                    empNumField.getText().trim(),
                    lastNameField.getText().trim(),
                    firstNameField.getText().trim(),
                    sssField.getText().trim(),
                    philHealthField.getText().trim(),
                    tinField.getText().trim(),
                    pagIbigField.getText().trim()
                };
                
                String csvLine = String.join(",", employeeTableData) + "," + new String(passwordField.getPassword()).trim();
                
                try {
                    File file = new File("employees.csv");
                    boolean fileExists = file.exists() && file.length() > 0;
                    
                    FileWriter fw = new FileWriter(file, true);
                    if(!fileExists) {
                        fw.write("Employee #,Last Name,First Name,SSS #,Philhealth #,TIN,Pag-IBIG,Password\n");
                    }
                    fw.write(csvLine + System.lineSeparator());
                    fw.close();
                    
                    parent.tableModel.addRow(employeeTableData);
                    
                    JOptionPane.showMessageDialog(this, "Employee added successfully.");
                    dispose();
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(this, "Failed to save employee data: " + ioException.getMessage());
                }
            } else if (e.getSource() == cancelBtn) {
                dispose();
            }
        }
    }
}
        
    
