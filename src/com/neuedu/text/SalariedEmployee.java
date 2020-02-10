package com.neuedu.text;

public class SalariedEmployee extends ColaEmployee{
    public static int salar;
    public SalariedEmployee(int salar){
        this.salar=salar;
    }
    @Override
    public double getSalary(int month) {
        return month*salar;
    }
}
