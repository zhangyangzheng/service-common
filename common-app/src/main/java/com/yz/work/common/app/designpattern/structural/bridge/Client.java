package com.yz.work.common.app.designpattern.structural.bridge;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-08 15:45
 */
public class Client {
    public static void main(String[] args) {
        CoffeeBig bigMile = new CoffeeBig(new Milk());
        bigMile.orderCoffee(2);
    }
}
