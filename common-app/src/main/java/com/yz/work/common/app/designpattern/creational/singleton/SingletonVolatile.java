package com.yz.work.common.app.designpattern.creational.singleton;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 16:22
 */
public class SingletonVolatile {
    private volatile  static  SingletonVolatile instance;

    public static SingletonVolatile getInstance() {
        if (instance == null) {
            synchronized (SingletonVolatile.class) {
                if (instance == null) {
                    instance = new SingletonVolatile();
                }
            }
        }
        return instance;
    }

}
