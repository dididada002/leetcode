package com.jt.thread.chapter2;

/**
 * @author: jingteng
 * @date: 2019/12/25 16:14
 * 备注：模拟策略模式在Runnable中的案例：CalaculatorStrategy   SimpleCalaculatorStrategy   TaxCalaculator  TaxCalaculatorMain
 */
@FunctionalInterface
public interface CalaculatorStrategy {
    double calculate(double salary, double bonus);
}
