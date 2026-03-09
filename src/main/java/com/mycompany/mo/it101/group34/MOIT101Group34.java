/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mo.it101.group34;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.Duration;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * 
 Repository Name: MO-IT101-Group34 
 Sir AJ Added: 02/27/2026
 
 Group Members:
 * Ioshua Jericho Surigao
 * Jan Braunel Angeles
 */
public class MOIT101Group34 {

    public static void main(String[] args) {
        // declare both CSV files
        String employeeCSV = "C:/Users/Kat/Documents/NetBeansProjects/MO-IT101-Group34/MotorPH_Employee Data - Employee Details.csv";
        String attendanceCSV = "C:/Users/Kat/Documents/NetBeansProjects/MO - IT101 - Group34/MotorPH_Employee Data - Attendance Record.csv";
        
        // declaration of variables
        String employeeNum = "";
        String l_name = "";
        String f_name = "";
        String birthday = "";
        boolean match = false;
        
        // Welcome Output
        System.out.println("===================================");
        System.out.println("Welcome to MotorPH Payroll System");
        System.out.println("===================================\n");
        
        while(true){
            match = false;
            // Ask for user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Employee No.: ");
            String inputEmpNum = scanner.nextLine();

            // used TRY CATCH EXCEPTION because of errors without one
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(employeeCSV));

                // reads header row
                bReader.readLine();
                String line;

                while((line=bReader.readLine()) != null){
                    String[] data = line.split(","); // split the rows into columns
                    if (data[0].equals(inputEmpNum)) // data[0] firstColumn EmployeeNum 
                    {
                        employeeNum = data[0]; // Column 1: EmployeeNum
                        l_name = data[1]; // Column 2: LastName
                        f_name = data[2]; // Column 3: FirstName
                        birthday = data[3]; // Column 4: Birthday
                        match = true; // Change match to true if data[0] have match
                    }
                }
            }

            catch (IOException e){ // print error message
                System.out.println("Error Reading File: " + e.getMessage());
            }
            if(match){
                break;
            }
            else{
                System.out.println("Employee not found. Please try again.");
            }
        }
            // output outside of while
            System.out.println("===================================");
            System.out.println("Employee # : " + employeeNum);
            System.out.println("Employee Name : " + f_name + " " + l_name);
            System.out.println("Birthday : " + birthday);
            System.out.println("===================================");
    }
}