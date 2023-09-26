package com.yz.work.common.app.designpattern.creational.builder;

import java.math.BigDecimal;

/**
 * @author yangzhengzhang
 * @description
 * @date 2023-09-07 17:54
 */
public interface Matter {
    String scene();
    String brand(); // 品牌
    String model(); // 型号
    BigDecimal price();
    String desc();
}
