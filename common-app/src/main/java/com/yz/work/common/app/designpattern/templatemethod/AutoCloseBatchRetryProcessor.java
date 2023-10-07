package com.yz.work.common.app.designpattern.templatemethod;

import org.springframework.stereotype.Component;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-06-21 15:38
 */
@Component
public class AutoCloseBatchRetryProcessor implements IAutoCloseBatchSendMessageProcessor {

  @Override
  public Boolean autoCloseAndSendBills() {

    return true;
  }

  private Boolean checkFail(Integer dealStatus, Integer dealBit) {
    return (dealStatus | dealBit) != dealStatus;
  }

  private Boolean checkSucess(Integer dealStatus, Integer dealBit) {
    return (dealStatus | dealBit) == dealStatus;
  }
}
