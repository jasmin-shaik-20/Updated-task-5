package com.emp;

public class PermanentEmployee extends Employee
{
	public int bonus;
    public PermanentEmployee(String name, int age, int salary, int bonus)
    {
        super(name, age, salary);
        this.bonus = bonus;
    }
    public int getBonus()
    {
        return bonus;
    }



}
