package com.yz.work.common.app.designpattern.creational.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 17:58
 */
public class DecorationPackageMenu implements IMenu {

    private List<Matter> list = new ArrayList<>(); //装修清单
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal area; //面积
    private String grade; // 装修等级

    public DecorationPackageMenu(Double area, String grade) {
        this.area = new BigDecimal(area);
        this.grade = grade;
    }

    @Override
    public IMenu appendCoat(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendFloor(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendTile(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }
}
