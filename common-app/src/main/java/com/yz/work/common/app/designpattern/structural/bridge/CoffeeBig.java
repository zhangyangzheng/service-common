package com.yz.work.common.app.designpattern.structural.bridge;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-08 15:43
 */
public class CoffeeBig extends Coffee {
    public CoffeeBig(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee(int count) {

    }
}
