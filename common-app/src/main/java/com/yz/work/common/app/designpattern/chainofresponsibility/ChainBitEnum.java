package com.yz.work.common.app.designpattern.chainofresponsibility;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-06 14:24
 */
public enum ChainBitEnum {
    RAPID_SETTLEMENT_FOLLOW(1),
    OLD_ONLINE_PAY_FOLLOW(2),
    NEW_ONLINE_PAY_FOLLOW(4),
    SEND_CONFIRM_BILL(8),
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
