package com.yz.work.common.app.designpattern.creational.singleton;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 16:29
 */
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton () {}

    public static Singleton getInstance() {
        return instance;
    }
}
