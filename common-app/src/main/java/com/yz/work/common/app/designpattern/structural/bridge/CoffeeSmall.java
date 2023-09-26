package com.yz.work.common.app.designpattern.structural.bridge;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-08 15:44
 */
public class CoffeeSmall extends Coffee {
    public CoffeeSmall(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee(int count) {

    }
}
