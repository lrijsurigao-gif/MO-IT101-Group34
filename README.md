Team Details

Ioshua Jericho Surigao – Responsible for the system design, coding, and overall development of the program.

Irish Chene Sia – Responsible for assisting in system testing, debugging, and operating the system.

Program Details

The system is designed to perform its intended functions by processing user input, executing programmed operations, and generating the appropriate output. It follows a structured workflow where users interact with the interface, the system processes the data, and results are displayed or stored accordingly.


The MotorPH Payroll System reads employee information and attendance records from CSV files and calculates the total hours worked per payroll cutoff (1–15 and 16–end of month). It then computes gross salary, deductions (SSS, PhilHealth, Pag-IBIG, Withholding Tax), and net salary for each employee. The deductions are applied only on the second cutoff, while the first cutoff shows the gross pay.

**How the System Works**

Input – The program prompts the user to enter an employee number.If the employee number is not found in the employee CSV file, the system will ask the user to enter the number again until a valid employee is found.

Read Employee Data – It reads the employee CSV file using OpenCSV, retrieving information such as name, birthday, and hourly rate. The hourly rate is extracted from the last column to handle cases where addresses contain commas.
Read Attendance Data – The program reads the attendance CSV file,processes the records that match the entered employee number, and parsing each date and time entry to calculate hours worked.
Compute Hours – computeHours accounts for grace period, lunch break deduction, and caps work hours at 8 per day.
Calculate Payroll


**Deductions**
Government deductions are calculated using separate functions based on the employee’s monthly gross salary. These include:
SSS contribution (SSS)
PhilHealth contribution (PhilHealth)
Pag-IBIG contribution (PagIBIG)
Withholding tax (WithholdingTax)
These deductions are applied only during the second payroll cutoff.

**Gross Salary**
– Computed per cutoff based on hours worked multiplied by the employee’s hourly rate..

**Net Salary**
– computeNetSalary subtracts deductions (SSS, PhilHealth, Pag-IBIG, and withholding tax) from the second cutoff’s gross salary.

**Output** 
– Displays a payroll summary for each cutoff period, including total hours worked, gross salary, deductions, and net salary for the employee.

**Notes:**
* CSV files must exist at the specified file paths in the project directory. paths in the project.
* The system currently processes attendance records for the year 2024 only.
* Hourly rates are taken from the last column of the employee CSV file to avoid issues caused by commas in address fields.
*The first cutoff (1–15) displays gross pay only, while deductions are applied to the second cutoff (16–end of month).
*Payroll summaries are generated for each month from June to December 2024.

Project Plank Link
https://docs.google.com/spreadsheets/d/191wCYeDszavsIyeajUZtuKeLhfIYSlSsX1f0L-Dl3Tk/edit?usp=sharing
