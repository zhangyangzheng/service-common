package com.yz.work.common.app.designpattern.creational.factory.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 15:17
 */
public class Factory {
    private static Map<String, Product> factoryMap = new HashMap<>();

    static {
        factoryMap.put("A", new ProductA());
        factoryMap.put("B", new ProductB());
    }

    public static Product createProduct(String type) {
        return factoryMap.get(type.toUpperCase());
    }
}
