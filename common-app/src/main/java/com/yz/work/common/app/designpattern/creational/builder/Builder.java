package com.yz.work.common.app.designpattern.creational.builder;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 23:06
 */
public class Builder {

    public IMenu level1(Double area) {
        return new DecorationPackageMenu(area, "北欧风")
                .appendCoat(new MatterCostA())
                .appendFloor(new MatterFloorA());
    }

    public IMenu level2(Double area) {
        return new DecorationPackageMenu(area, "简约风")
                .appendCoat(new MatterCostB())
                .appendFloor(new MatterFloorB());
    }

}
