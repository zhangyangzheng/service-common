package com.yz.work.common.app.designpattern.chainofresponsibility;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-14 15:48
 */
public class HandleResult<R> {
    private final R data;

    public HandleResult(R data) {
        this.data = data;
    }

    public R getData() {
        return data;
    }
}
