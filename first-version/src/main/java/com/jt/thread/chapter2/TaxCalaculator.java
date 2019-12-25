package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/23 17:39
 */
public class TaxCalaculator {

    private final double salary;

    private final double bonus;

    private CalaculatorStrategy calaculatorStrategy;

    public void setCalaculatorStrategy(CalaculatorStrategy calaculatorStrategy) {
        this.calaculatorStrategy = calaculatorStrategy;
    }

    public TaxCalaculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calcTax(){
        return calaculatorStrategy.calculate(salary,bonus);
    }

    protected double calculate(){
        return calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
