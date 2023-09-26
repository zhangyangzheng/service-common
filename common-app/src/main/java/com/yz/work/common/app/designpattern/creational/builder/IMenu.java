package com.yz.work.common.app.designpattern.creational.builder;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 17:52
 */
public interface IMenu {

    IMenu appendCoat(Matter matter); // 涂料
    IMenu appendFloor(Matter matter); // 地板
    IMenu appendTile(Matter matter); // 地砖

}
