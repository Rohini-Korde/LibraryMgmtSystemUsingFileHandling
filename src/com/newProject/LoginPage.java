package com.newProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoginPage {

	private final String file_name = "login.txt";

    public boolean login(String username, String password) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new FileReader(file_name));
        String line = br.readLine();
        br.close();

        String[] data = line.split("\\|");
        String storedUser = data[0];
        String storedPass = data[1];

        if (username.equals(storedUser) && password.equals(storedPass)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }


    public void changePassword(String username, String oldPassword,String newPassword, String confirmPassword) throws IOException 
    {
    	BufferedReader br = new BufferedReader(new FileReader(file_name));
        String line = br.readLine();
        br.close();

        String[] data = line.split("\\|");

        if (!username.equals(data[0]) || !oldPassword.equals(data[1])) {
        	System.out.println("Authentication failed. Cannot change password.");
            return;
        }
        
        if (newPassword.length() < 6) {
            System.out.println("Password must be at least 6 characters long.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New password and confirmation do not match.");
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file_name));
        bw.write(username + "|" + newPassword);
        bw.close();

        System.out.println("Password changed permanently!");
    }
  }

