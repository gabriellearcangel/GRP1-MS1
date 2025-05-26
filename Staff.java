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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MotorPHStaffTeam {
    private JFrame frame;
    private JTable staffTable;
    private DefaultTableModel staffModel;

    public MotorPHStaffTeam() {
        frame = new JFrame("MotorPH Staff Team");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table columns
        String[] columns = {"Employee ID", "Name", "Department", "Position"};
        staffModel = new DefaultTableModel(columns, 0);
        staffTable = new JTable(staffModel);
        JScrollPane scrollPane = new JScrollPane(staffTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add Staff button
        JButton addButton = new JButton("Add Staff");
        frame.add(addButton, BorderLayout.SOUTH);

        addButton.addActionListener(e -> showAddStaffDialog());

        frame.setVisible(true);
    }

    private void showAddStaffDialog() {
        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField deptField = new JTextField(10);
        JTextField positionField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Employee ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Department:"));
        panel.add(deptField);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Add New Staff", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String dept = deptField.getText().trim();
            String pos = positionField.getText().trim();

            if (!id.isEmpty() && !name.isEmpty() && !dept.isEmpty() && !pos.isEmpty()) {
                staffModel.addRow(new Object[]{id, name, dept, pos});
            } else {
                JOptionPane.showMessageDialog(frame,
                        "All fields are required.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MotorPHStaffTeam::new);
    }
}




