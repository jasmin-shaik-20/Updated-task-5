package com.emp;

public class PartTimeEmployee extends Employee
{
	public int hoursWorked;
    public PartTimeEmployee(String name, int age, int salary, int hoursWorked)
    {
        super(name, age, salary);
        this.hoursWorked = hoursWorked;
    }
    public int getHoursWorked()
    {
        return hoursWorked;
    }
}