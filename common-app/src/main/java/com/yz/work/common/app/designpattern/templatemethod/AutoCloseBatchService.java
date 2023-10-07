package com.yz.work.common.app.designpattern.templatemethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-06-01 19:39
 */
@Service
public class AutoCloseBatchService {
  private final List<IAutoCloseBatchSendMessageProcessor> autoCloseBatchProcessorList =
      new CopyOnWriteArrayList();

  @Autowired private AutoCloseBatchRetryProcessor autoCloseBatchRetryProcessor;

  @Autowired
  public AutoCloseBatchService(Map<String, IAutoCloseBatchSendMessageProcessor> map) {
    for (Map.Entry<String, IAutoCloseBatchSendMessageProcessor> entry : map.entrySet()) {
      if (entry.getValue().getClass().getSimpleName().equals("AutoCloseFGBatchSendMessageProcessor")
      || entry.getValue().getClass().getSimpleName().equals("AutoCloseBatchRetryProcessor")) {
        continue;
      }
      autoCloseBatchProcessorList.add(entry.getValue());
    }
  }

  public void autoCloseBatchPPExecute() {
    for (IAutoCloseBatchSendMessageProcessor processor : autoCloseBatchProcessorList) {
      processor.autoCloseAndSendBills();
    }
  }


  public void autoCloseBatchRetryExecute() {
    autoCloseBatchRetryProcessor.autoCloseAndSendBills();
  }
}
