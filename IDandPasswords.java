package com.mycompany.motorph;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class IDandPasswords {
    HashMap<String, String> logininfo = new HashMap<String, String>();
    
    IDandPasswords() {
      
        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] employeeData = line.split(",");
                if (employeeData.length >= 8) { 
                    logininfo.put(employeeData[0].trim(), employeeData[7].trim());
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading employee credentials: " + e.getMessage());

            logininfo = new HashMap<String, String>();
        }
    }
    
    protected HashMap<String, String> getLoginInfo() {
        return logininfo;
    }
}
