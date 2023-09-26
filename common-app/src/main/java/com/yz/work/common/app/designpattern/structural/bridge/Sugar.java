package com.yz.work.common.app.designpattern.structural.bridge;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-08 15:38
 */
public class Sugar implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加糖");
    }
}
