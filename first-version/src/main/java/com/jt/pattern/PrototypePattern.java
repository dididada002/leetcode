package com.jt.pattern;

import lombok.Data;

/**
 * @author: jingteng
 * @date: 2020/1/6 21:19
 * 原型模式
 */
public class PrototypePattern {

    public static void main(String[] args) throws CloneNotSupportedException {
        PrototypeProduct prototypeProduct = new PrototypeProduct("part1","part2");
        PrototypeProduct clone = prototypeProduct.clone();
    }

}

@Data
class PrototypeProduct implements Cloneable {
    //Cloneable 是一个标记接口
    private String part1;
    private String part2;

    public PrototypeProduct(String part1, String part2) {
        this.part1 = part1;
        this.part2 = part2;
    }


    /**
     * 克隆的对象，如果有变量为对象，那么克隆的对象仍然会指向这个对象，
     * 这种深拷贝，必须将内层的对象都进行拷贝，直至没有可变对象，并逐次将外层的指向设置为新的拷贝对象
     *
     * */
    @Override
    protected PrototypeProduct clone() throws CloneNotSupportedException {
        return (PrototypeProduct) super.clone();
    }
}
