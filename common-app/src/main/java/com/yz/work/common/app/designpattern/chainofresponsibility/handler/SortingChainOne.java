package com.yz.work.common.app.designpattern.chainofresponsibility.handler;

import com.yz.work.common.app.designpattern.chainofresponsibility.ChainBitEnum;
import com.yz.work.common.app.designpattern.chainofresponsibility.IHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-18 11:07
 */
@Component
@Order(1)
public class SortingChainOne implements IHandler<HandleParameter, HandleResult<Object>> {
    @Override
    public HandleResult<Object> handle(HandleParameter parameter) throws Exception {
        if (concreteHandler(parameter.getState(), ChainBitEnum.THE_FIRST_STEP.getBit())) {
            // do something

            return new HandleResult<>(new Object());
        }
        return null;
    }
}
