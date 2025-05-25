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

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Staff Team</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f9f9f9;
      padding: 20px;
    }
    h2 {
      text-align: center;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      background: #fff;
      margin-bottom: 20px;
    }
    th, td {
      padding: 12px;
      border: 1px solid #ccc;
      text-align: left;
    }
    button {
      padding: 10px 15px;
      border: none;
      background: #007BFF;
      color: white;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background: #0056b3;
    }
    .modal {
      display: none;
      position: fixed;
      z-index: 10;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0,0,0,0.5);
    }
    .modal-content {
      background-color: #fff;
      margin: 10% auto;
      padding: 20px;
      width: 320px;
      border-radius: 8px;
    }
    .modal-content input {
      width: 100%;
      padding: 8px;
      margin-top: 10px;
      margin-bottom: 20px;
    }
    .close {
      float: right;
      font-size: 20px;
      cursor: pointer;
    }
  </style>
</head>
<body>

  <h2>Staff Team</h2>

  <button id="addStaffBtn">+ Add Staff</button>

  <table id="staffTable">
    <thead>
      <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Position</th>
      </tr>
    </thead>
    <tbody>
      <!-- Staff members will be added here -->
    </tbody>
  </table>

  <!-- Modal -->
  <div id="staffModal" class="modal">
    <div class="modal-content">
      <span class="close" id="closeModal">&times;</span>
      <h3>Add Staff Member</h3>
      <input type="text" id="name" placeholder="Full Name" required>
      <input type="email" id="email" placeholder="Email" required>
      <input type="text" id="position" placeholder="Position" required>
      <button onclick="addStaff()">Add</button>
    </div>
  </div>

  <script>
    const addStaffBtn = document.getElementById("addStaffBtn");
    const modal = document.getElementById("staffModal");
    const closeModal = document.getElementById("closeModal");

    addStaffBtn.onclick = () => modal.style.display = "block";
    closeModal.onclick = () => modal.style.display = "none";
    window.onclick = (e) => { if (e.target == modal) modal.style.display = "none"; }

    function addStaff() {
      const name = document.getElementById("name").value.trim();
      const email = document.getElementById("email").value.trim();
      const position = document.getElementById("position").value.trim();

      if (!name || !email || !position) {
        alert("Please fill in all fields.");
        return;
      }

      const table = document.getElementById("staffTable").getElementsByTagName("tbody")[0];
      const newRow = table.insertRow();

      newRow.insertCell(0).textContent = name;
      newRow.insertCell(1).textContent = email;
      newRow.insertCell(2).textContent = position;

      // Clear form
      document.getElementById("name").value = "";
      document.getElementById("email").value = "";
      document.getElementById("position").value = "";
      modal.style.display = "none";
    }
  </script>

</body>
</html>

