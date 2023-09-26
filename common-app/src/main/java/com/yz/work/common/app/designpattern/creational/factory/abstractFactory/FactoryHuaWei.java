package com.yz.work.common.app.designpattern.creational.factory.abstractFactory;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 16:06
 */
public class FactoryHuaWei implements Factory {
    @Override
    public ProductPhone createProductPhone() {
        return new ProductPhoneHuaWei();
    }

    @Override
    public ProductMac createProductMac() {
        return new ProductMacHuaWei();
    }
}
