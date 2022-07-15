package com.yz.work.common.app.designpattern.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-07-14 15:33
 */
public class BaseHandlerChain<T extends IHandler<P, R>, P, R> {

  private final List<T> handlerChains;

  public BaseHandlerChain(List<T> handlerChains) {
    this.handlerChains = handlerChains;
  }

  public List<R> chainDoResult(P p) throws Exception {
    List<R> results = new ArrayList<>();
    for (T handler : handlerChains) {
      results.add(handler.handle(p));
    }
    return results;
  }

  public void chainProcess(P p) throws Exception {
    for (T handler : handlerChains) {
      handler.handle(p);
    }
  }
}
