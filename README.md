The MotorPH Payroll System reads employee information and attendance records from CSV files and calculates the total hours worked per payroll cutoff (1–15 and 16–end of month). It then computes gross salary, deductions (SSS, PhilHealth, Pag-IBIG, Withholding Tax), and net salary for each employee. The deductions are applied only on the second cutoff, while the first cutoff shows the gross pay.

**How the System Works**

Input – The program prompts the user to enter an employee number.

Read Employee Data – It reads the employee CSV file using OpenCSV, retrieving information such as name, birthday, and hourly rate. The hourly rate is extracted from the last column to handle cases where addresses contain commas.
Read Attendance Data – The program reads the attendance CSV file, parsing each date and time entry to calculate hours worked.
Compute Hours – computeHours accounts for grace period, lunch break deduction, and caps work hours at 8 per day.
Calculate Payroll


**Deductions**
– Calculated on monthly gross (second cutoff only) using dedicated functions:
SSS contribution (SSS)
PhilHealth contribution (PhilHealth)
Pag-IBIG contribution (PagIBIG)
Withholding tax (WithholdingTax)

**Gross Salary**
– Computed per cutoff based on hours worked and hourly rate.

**Net Salary**
– computeNetSalary subtracts deductions from the second cutoff’s gross salary.

**Output** 
– Displays a summary per cutoff, including total hours worked, gross salary, deductions, and net salary.

**Notes:**
* CSV files must exist at the specified paths in the project.
* The system currently supports only the year 2024.
* Hourly rates are taken from the last column of the employee CSV to account for commas in address fields.
* First cutoff shows gross pay only; deductions are applied to the second cutoff.
