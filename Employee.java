package com.emp;


public class Employee
{

    public  String name;
    public int age;
    public int salary;

    public Employee(String name, int age, int salary) {

        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public String getName()
    {
        return name;
    }



    public int getAge()
    {
        return age;
    }



    public int getSalary()
    {
        return salary;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id=" + "id" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
