package com.emp;

public class ContractEmployee extends Employee
{
	
    public int contractPeriod;
    public ContractEmployee(String name, int age, int salary, int contractPeriod)
    {
        super( name, age, salary);
        this.contractPeriod = contractPeriod;
    }
    public int getContractPeriod()
    {
        return contractPeriod;
    }


}