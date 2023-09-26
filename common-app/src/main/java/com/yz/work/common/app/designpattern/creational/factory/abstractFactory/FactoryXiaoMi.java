package com.yz.work.common.app.designpattern.creational.factory.abstractFactory;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 16:07
 */
public class FactoryXiaoMi implements Factory {
    @Override
    public ProductPhone createProductPhone() {
        return new ProductPhoneXiaoMi();
    }

    @Override
    public ProductMac createProductMac() {
        return new ProductMacXiaoMi();
    }
}
