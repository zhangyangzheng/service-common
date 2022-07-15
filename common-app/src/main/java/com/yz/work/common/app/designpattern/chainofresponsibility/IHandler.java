package com.yz.work.common.app.designpattern.chainofresponsibility;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-06 14:31
 */
public interface IHandler<P, R> {
    R handle(P t) throws Exception;

    default Boolean concreteHandler(Integer code, Integer state) {
        return (code & state) != state;
    }

    default Integer handled(Integer code, Integer state) {
        return code | state;
    }
}
