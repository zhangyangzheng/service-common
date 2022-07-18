package com.yz.work.common.app.designpattern.chainofresponsibility;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-06 14:24
 */
public enum ChainBitEnum {
    THE_FIRST_STEP(1),
    THE_SECOND_STEP(2),
    THE_THIRD_STEP(4),
    THE_FOURTH_STEP(8),
            ;
    private int bit;

    ChainBitEnum(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }
}
