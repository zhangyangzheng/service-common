package com.yz.work.common.app.designpattern.creational.factory.method;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 15:27
 */
public class FactoryA extends Factory {
    @Override
    public Product factoryMethod() {
        return new ProductA();
    }
}
