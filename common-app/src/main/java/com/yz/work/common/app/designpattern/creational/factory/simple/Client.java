package com.yz.work.common.app.designpattern.creational.factory.simple;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 15:18
 */
public class Client {
    public static void main(String[] args) {
        Product a = Factory.createProduct("A");
        a.use();
    }
}
