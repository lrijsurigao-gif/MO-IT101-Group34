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
 * Irish Chene Sia
 * Jan Braunel Angeles
 */
public class MOIT101Group34 {

    public static void main(String[] args) {
        // declare both CSV files
        String employeeCSV = "C:/Users/ioshs/Documents/NetBeansProjects/MO-IT101-Group34/src/main/java/com/mycompany/mo/it101/group34/MotorPH_Employee Data - Employee Details.csv";
        String attendanceCSV = "C:/Users/ioshs/Documents/NetBeansProjects/MO-IT101-Group34/src/main/java/com/mycompany/mo/it101/group34/MotorPH_Employee Data - Attendance Record.csv";
        
        // declaration of variables
        String employeeNum = "";
        String l_name = "";
        String f_name = "";
        String birthday = "";
        String inputEmpNum = "";
        double hourlyRate = 0.00;
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
            inputEmpNum = scanner.nextLine();

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
                        hourlyRate = Double.parseDouble(data[19]); // Column 19: Hourly Rate
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
        
        // output outside before the month loop
            System.out.println("===================================");
            System.out.println("Employee # : " + employeeNum);
            System.out.println("Employee Name : " + f_name + " " + l_name);
            System.out.println("Birthday : " + birthday);
            System.out.printf("Hourly Rate : %.2f%n",  hourlyRate);
            System.out.println("===================================");
        
        
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");

        for (int month_num = 6; month_num <= 12; month_num++) {
            double first = 0;
            double second = 0;
            int days = YearMonth.of(2024, month_num).lengthOfMonth();
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(attendanceCSV))) {

            // reads header row
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(inputEmpNum)) // data[0] firstColumn EmployeeNum 
                {
                
                // PARSING THE DATE
                String[] dateParts = data[3].split("/");
                int month = Integer.parseInt(dateParts[0]);
                int day = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                    if (year != 2024 || month != month_num)
                        continue;
                
                LocalTime timeIn = LocalTime.parse(data[4].trim(), timeFormat);
                LocalTime timeOut = LocalTime.parse(data[5].trim(), timeFormat);

                double hours = computeHours(timeIn, timeOut);

                if (day <= 15) {
                    first += hours;
                } else {
                    second += hours;
                }
            }
        }

        } catch (Exception e) {
            System.out.println("Error reading attendance file for month " + month_num);
            e.printStackTrace();
            continue;
        }
        
        String monthName = switch (month_num) {
                case 6 -> "June";
                case 7 -> "July";
                case 8 -> "August";
                case 9 -> "September";
                case 10 -> "October";
                case 11 -> "November";
                case 12 -> "December";
                default -> "Month " + month_num;
            };
        
            double grossFirst = computeGrossSalary(first, hourlyRate);
            double grossSecond = computeGrossSalary(second, hourlyRate);
        
            System.out.println("\nCutoff Date: " + monthName + " 1 to 15");
            System.out.println("Total Hours Worked : " + first);
            System.out.printf("Gross Salary: %.2f%n", grossFirst);
            System.out.println("Net Salary: ");

            System.out.println("\nCutoff Date: " + monthName + " 16 to " + days);
            System.out.println("Total Hours Worked : " + second);
            System.out.printf("Gross Salary: %.2f%n", grossSecond);
            System.out.println("Deductions: ");
            System.out.println("    SSS: ");
            System.out.println("    PhilHealth: ");
            System.out.println("    Pag-IBIG: ");
            System.out.println("    Tax: ");
            System.out.println("Net Salary: ");
        }
    }


static double computeGrossSalary(double hoursWorked, double hourlyRate) {
    return hoursWorked * hourlyRate;
}

static double computeHours(LocalTime timeIn, LocalTime timeOut) {

        LocalTime graceTime = LocalTime.of(8, 10);
        LocalTime cutoffTime = LocalTime.of(17, 0);

        if (timeOut.isAfter(cutoffTime)) {
            timeOut = cutoffTime;
        }

        long minutesWorked = Duration.between(timeIn, timeOut).toMinutes();

        // Deduct lunch (if total worked is more than 1 hour)
        if (minutesWorked > 60) {
            minutesWorked -= 60;
        } else {
            minutesWorked = 0;
        }

        double hours = minutesWorked / 60.0;

        // Grace period rule
        if (!timeIn.isAfter(graceTime)) {
            return 8.0;
        }

        // Return hours worked, capped at 8
        return Math.min(hours, 8.0);
    }




}