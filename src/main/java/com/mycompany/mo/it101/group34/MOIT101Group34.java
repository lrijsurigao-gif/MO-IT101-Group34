/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mo.it101.group34;

import com.opencsv.CSVReader;
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
 */
public class MOIT101Group34 {

// FUNCTIONS FOR DEDUCTIONS    
static double SSS (double monthlyGross){
    if (monthlyGross < 3250)
        return 135.00;
    else if (monthlyGross <= 3750)
        return 157.50;
    else if (monthlyGross <= 4250)
        return 180.00;
    else if (monthlyGross <= 4750)
        return 202.50;
    else if (monthlyGross <= 5250)
        return 225.00;
    else if (monthlyGross <= 5750)
        return 247.50;
    else if (monthlyGross <= 6250)
        return 270.00;
    else if (monthlyGross <= 6750)
        return 292.50;
    else if (monthlyGross <= 7250)
        return 315.00;
    else if (monthlyGross <= 7750)
        return 337.50;
    else if (monthlyGross <= 8250)
        return 360.00;
    else if (monthlyGross <= 8750)
        return 382.50;
    else if (monthlyGross <= 9250)
        return 405.00;
    else if (monthlyGross <= 9750)
        return 427.50;
    else if (monthlyGross <= 10250)
        return 450.00;
    else if (monthlyGross <= 10750)
        return 472.50;
    else if (monthlyGross <= 11250)
        return 495.00;
    else if (monthlyGross <= 11750)
        return 517.50;
    else if (monthlyGross <= 12250)
        return 540.00;
    else if (monthlyGross <= 12750)
        return 562.50;
    else if (monthlyGross <= 13250)
        return 585.00;
    else if (monthlyGross <= 13750)
        return 607.50;
    else if (monthlyGross <= 14250)
        return 630.00;
    else if (monthlyGross <= 14750)
        return 652.50;
    else if (monthlyGross <= 15250)
        return 675.00;
    else if (monthlyGross <= 15750)
        return 697.50;
    else if (monthlyGross <= 16250)
        return 720.00;
    else if (monthlyGross <= 16750)
        return 742.50;
    else if (monthlyGross <= 17250)
        return 765.00;
    else if (monthlyGross <= 17750)
        return 787.50;
    else if (monthlyGross <= 18250)
        return 810.00;
    else if (monthlyGross <= 18750)
        return 832.50;
    else if (monthlyGross <= 19250)
        return 855.00;
    else if (monthlyGross <= 19750)
        return 877.50;
    else if (monthlyGross <= 20250)
        return 900.00;
    else if (monthlyGross <= 20750)
        return 922.50;
    else if (monthlyGross <= 21250)
        return 945.00;
    else if (monthlyGross <= 21750)
        return 967.50;
    else if (monthlyGross <= 22250)
        return 990.00;
    else if (monthlyGross <= 22750)
        return 1012.50;
    else if (monthlyGross <= 23250)
        return 1035.00;
    else if (monthlyGross <= 23750)
        return 1057.50;
    else if (monthlyGross <= 24250)
        return 1080.00;
    else if (monthlyGross <= 24750)
        return 1102.50;
    else
        return 1125.00;
}

static double PhilHealth(double monthlyGross){
    // 3% premium rate
    double premium = monthlyGross * 0.03;

    // Cap the premium between 300 and 1800
    if (premium < 300) {
        premium = 300;
    }
    if (premium > 1800) {
        premium = 1800;
    }

    // Employee share is 50% only
    return premium / 2;
}

static double PagIBIG (double monthlyGross){
    double pi = monthlyGross * 0.02;
    return Math.min(pi, 100);
}

static double WithholdingTax (double monthlyGross){
    double sss = SSS(monthlyGross);
    double ph = PhilHealth(monthlyGross);
    double pi = PagIBIG(monthlyGross);

    double taxable = monthlyGross - (sss + ph + pi);

    if (taxable <= 20832)
        return 0;
    else if (taxable <= 33332)
        return (taxable - 20833) * 0.20;
    else if (taxable <= 66666)
        return 2500 + (taxable - 33332) * 0.25;
    else if (taxable <= 166666)
        return 10833 + (taxable - 66666) * 0.30;
    else if (taxable <= 666666)
        return 40833.33 + (taxable - 166667) * 0.32;
    else
        return 200833.33 + (taxable - 666667) * 0.35;
}


// FUNCTION FOR GROSS SALARY
static double computeGrossSalary(double hoursWorked, double hourlyRate) {
    return hoursWorked * hourlyRate;
}

// FUNCTION FOR NET SALARY
static double computeNetSalary(double secondGross, double monthlyGross) {
    double sss = SSS(monthlyGross);
    double ph = PhilHealth(monthlyGross);
    double pi = PagIBIG(monthlyGross);
    double tax = WithholdingTax(monthlyGross);

    return secondGross - (sss + ph + pi + tax);
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

    public static void main(String[] args) {
        // declare both CSV files
        String employeeCSV = "C:/Users/ioshs/Documents/NetBeansProjects/MO-IT101-Group34/src/main/java/com/mycompany/mo/it101/group34/MotorPH_Employee Data - Employee Details.csv";
        String attendanceCSV = "C:/Users/ioshs/Documents/NetBeansProjects/MO-IT101-Group34/src/main/java/com/mycompany/mo/it101/group34/MotorPH_Employee Data - Attendance Record.csv";

//        Ioshua Filepath
//        String employeeCSV = "C:/Users/ioshs/Documents/NetBeansProjects/MO-IT101-Group34/src/main/java/com/mycompany/mo/it101/group34/MotorPH_Employee Data - Employee Details.csv";
//        String attendanceCSV = "C:/Users/ioshs/Documents/NetBeansProjects/MO-IT101-Group34/src/main/java/com/mycompany/mo/it101/group34/MotorPH_Employee Data - Attendance Record.csv";
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

        while (true) {
            // Ask for user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Employee No.: ");
            inputEmpNum = scanner.nextLine();

            // used TRY CATCH EXCEPTION because of errors without one
            // used CSVReader to read CSV better
            try (CSVReader reader = new CSVReader(new FileReader(employeeCSV))) {
                reader.readNext(); // skip header
                String[] data;

                while ((data = reader.readNext()) != null) {
                    if (data[0].equals(inputEmpNum)) {
                        employeeNum = data[0];
                        l_name = data[1];
                        f_name = data[2];
                        birthday = data[3];
                        // Always get the last column for hourly rate
                        hourlyRate = Double.parseDouble(data[data.length - 1]);
                        match = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (match) {
                break;
            } else {
                System.out.println("Employee not found. Please try again.");
            }
        }

        // output outside before the while loop
        System.out.println("===================================");
        System.out.println("Employee # : " + employeeNum);
        System.out.println("Employee Name : " + f_name + " " + l_name);
        System.out.println("Birthday : " + birthday);
//        System.out.printf("Hourly Rate : %.2f%n", hourlyRate); // tests if hourly rate is properly caught
        System.out.println("===================================");

        // proper format time
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

                        if (year != 2024 || month != month_num) {
                            continue;
                        }

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
                case 6 ->
                    "June";
                case 7 ->
                    "July";
                case 8 ->
                    "August";
                case 9 ->
                    "September";
                case 10 ->
                    "October";
                case 11 ->
                    "November";
                case 12 ->
                    "December";
                default ->
                    "Month " + month_num;
            };

            double grossFirst = computeGrossSalary(first, hourlyRate);
            double grossSecond = computeGrossSalary(second, hourlyRate);
            double monthlyGross = grossFirst + grossSecond;

            double netFirst = grossFirst;
            double netSecond = computeNetSalary(grossSecond, monthlyGross);

            System.out.println("\nCutoff Date: " + monthName + " 1-15 Cutoff");
            System.out.println("Total Hours Worked : " + first);
            System.out.printf("Gross Salary: %.2f%n", grossFirst);
            System.out.printf("Net Salary: %.2f%n", netFirst);

            System.out.println("\nCutoff Date: " + monthName + " 16-" + days + " Cutoff");
            System.out.println("Total Hours Worked : " + second);
            System.out.printf("Gross Salary: %.2f%n", grossSecond);
            System.out.println("=====Deductions===== ");
            System.out.printf("    SSS: %.2f%n", SSS(monthlyGross));
            System.out.printf("    PhilHealth: %.2f%n", PhilHealth(monthlyGross));
            System.out.printf("    Pag-IBIG: %.2f%n", PagIBIG(monthlyGross));
            System.out.printf("    Tax: %.2f%n", WithholdingTax(monthlyGross));
            System.out.printf("Net Salary: %.2f%n", netSecond);
        }
    }
}