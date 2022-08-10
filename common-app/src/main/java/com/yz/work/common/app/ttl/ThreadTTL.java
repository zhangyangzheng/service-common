package com.yz.work.common.app.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-08-09 14:27
 */
public class ThreadTTL {
    public static final TransmittableThreadLocal<Integer> context = new TransmittableThreadLocal<>();

    public static TransmittableThreadLocal<Integer> getParameterContext() {
        return context;
    }

    public static void setTransmittableThreadLocal(Integer parameter) {
        context.set(parameter);
    }
}
