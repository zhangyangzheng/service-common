package com.yz.work.common.app.designpattern.creational.builder.lombok;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-08 0:07
 */
public class Client {
    public static void main(String[] args) {
        // 非 Builder 模式
//        Computer computer = new Computer(“cpu”, “screen”, “memory”, “mainboard”);
        // Builder 模式
        NewComputer newComputer = new NewComputer.Builder()
                .cpu("cpu")
                .screen("screen")
                .memory("memory")
                .mainboard("mainboard")
                .build();
    }
}
