package com.yz.work.common.app.designpattern.creational.factory.abstractFactory;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 16:12
 */
public class Client {
    public static void main(String[] args) {

        Factory huaWei = getFactory("HuaWei");
        if (huaWei != null) {
            ProductMac mac = huaWei.createProductMac();
            mac.use();
        }

    }

    public static Factory getFactory(String type){
        if(type.equalsIgnoreCase("HuaWei")){
            return new FactoryHuaWei();
        } else if(type.equalsIgnoreCase("XiaoMi")){
            return new FactoryXiaoMi();
        }
        return null;
    }

}
