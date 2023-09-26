package com.yz.work.common.app.designpattern.creational.singleton;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 16:25
 */
public class Client {
    public static void main(String[] args) {
        SingletonVolatile singleton = SingletonVolatile.getInstance();
    }
}
