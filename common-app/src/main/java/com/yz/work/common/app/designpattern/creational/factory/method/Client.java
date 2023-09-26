package com.yz.work.common.app.designpattern.creational.factory.method;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 15:42
 */
public class Client {
    public static void main(String[] args) {
        FactoryA factoryA = new FactoryA();
        Product productA = factoryA.factoryMethod();
        productA.use();

        // another
        Product product_A = getProduct("A");
        product_A.use();
    }

    public static Product getProduct(String type) {
        if (type.equals("A")) {
            return new FactoryA().factoryMethod();
        } else if (type.equals("B")) {
            return new FactoryB().factoryMethod();
        }
        return null;
    }

}
