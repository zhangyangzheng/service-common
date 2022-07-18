package com.yz.work.common.app.designpattern.chainofresponsibility.client;

import com.yz.work.common.app.designpattern.chainofresponsibility.BaseHandlerChain;
import com.yz.work.common.app.designpattern.chainofresponsibility.IHandler;
import com.yz.work.common.app.designpattern.chainofresponsibility.handler.HandleParameter;
import com.yz.work.common.app.designpattern.chainofresponsibility.handler.HandleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-18 11:22
 */
@Component
public class ChainClient extends BaseHandlerChain<IHandler<HandleParameter, HandleResult<Object>>, HandleParameter, HandleResult<Object>> {

    @Autowired
    public ChainClient(List<IHandler<HandleParameter, HandleResult<Object>>> handlerChains) {
        super(handlerChains);
    }

    public void doWork() {
        try {
            chainProcess(new HandleParameter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
