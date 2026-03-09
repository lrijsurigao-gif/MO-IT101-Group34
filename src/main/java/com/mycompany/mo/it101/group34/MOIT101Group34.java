/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mo.it101.group34;

import java.io.BufferedReader;
import java.io.FileReader;
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
        // read both CSV files
        String employeeCSV = "MO - IT101 - Group34/MotorPH_Employee Data - Employee Details.csv";
        String attendanceCSV = "MO - IT101 - Group34/MotorPH_Employee Data - Attendance Record.csv";
        
        // declaration of variables
        String employeeNum = "";
        String f_name = "";
        String l_name = "";
        String birthday = "";
        boolean match = false;
        
        // output
        System.out.println("\n===================================");
        System.out.println("Employee # : " + employeeNum);
        System.out.println("Employee Name : " + f_name + " " + l_name);
        System.out.println("Birthday : " + birthday);
        System.out.println("===================================");
        
    }
}
