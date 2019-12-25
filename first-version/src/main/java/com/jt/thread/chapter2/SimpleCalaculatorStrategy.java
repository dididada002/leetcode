package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/25 16:17
 */
public class SimpleCalaculatorStrategy implements CalaculatorStrategy {
    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
