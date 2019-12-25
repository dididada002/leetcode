package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/23 17:41
 */
public class TaxCalaculatorMain {
    public static void main(String[] args) {
        /*TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d){
            @Override
            public double calcTax(){
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = taxCalaculator.calcTax();
        System.out.println(tax);*/

        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d, 2000d);
        CalaculatorStrategy simpleCalaculatorStrategy = new SimpleCalaculatorStrategy();
        taxCalaculator.setCalaculatorStrategy(simpleCalaculatorStrategy);
        double tax = taxCalaculator.calcTax();
        System.out.println(tax);
    }
}
