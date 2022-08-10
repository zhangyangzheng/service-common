package com.yz.work.common.app.ttl;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-08-10 10:31
 */
public class ClientApp {

    public void setTTL() {
        try {
            ThreadTTL.setTransmittableThreadLocal(1);
            // do something...
        } finally{
            ThreadTTL.getParameterContext().remove();
        }
    }

    public void  useTTL() {

        // somewhere use it
        Integer integer = ThreadTTL.getParameterContext().get();
    }

}
