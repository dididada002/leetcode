package com.jt.pattern;

/**
 * @author: jingteng
 * @date: 2019/12/17 22:00
 */
public class TestControllerPattern {
    public static void main(String[] args) {
        //工厂方法模式
        Application application = new ConcreateProductB();
        Product product = application.getObject();
        product.method();


        //简单工厂模式
//        Application application = new Application();
//        Product productA = application.getObject("1");
//        productA.method();
    }
}

interface Product{
    public void method();
}

class ProductA implements Product{
    public void method(){
        System.out.println("ProductA.method run");
    }
}

class ProductB implements Product{
    public void method(){
        System.out.println("ProductB.method run");
    }
}

//工厂方法模式
abstract class Application{
    abstract Product createProduct();
    Product getObject(){
        Product product = createProduct();
        return product;
    }
}

class ConcreateProductA extends Application{

    @Override
    Product createProduct() {
        return new ProductA();
    }
}

class ConcreateProductB extends Application{

    @Override
    Product createProduct() {
        return new ProductB();
    }
}



/*简单工厂模式

class Application{
    private Product createProduct(String type){
        return SimpleFactory.createProduct(type);
    }
    Product getObject(String type){
        Product product = createProduct(type);
        return product;
    }
}
*/

//简单工厂
class SimpleFactory{
    public static Product createProduct(String type){
        if (type.equals("0")){
            return new ProductA();
        }else if (type.equals("1")){
            return new ProductB();
        }else {
            return null;
        }
    }
}
