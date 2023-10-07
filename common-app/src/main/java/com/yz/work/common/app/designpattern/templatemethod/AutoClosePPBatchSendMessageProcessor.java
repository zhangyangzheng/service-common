package com.yz.work.common.app.designpattern.templatemethod;

import com.yz.work.common.app.designpattern.chainofresponsibility.BaseHandlerChain;
import com.yz.work.common.app.designpattern.chainofresponsibility.IHandler;
import com.yz.work.common.app.sampling.JobCoreProcess;
import com.yz.work.common.utils.PageUtil;
import com.yz.work.common.utils.RetryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-06-06 11:21
 */
@Component
public class AutoClosePPBatchSendMessageProcessor
    extends BaseHandlerChain<
            IHandler<SettlementPeriodDealDetailModel, ProcessLog>,
            SettlementPeriodDealDetailModel,
            ProcessLog> {

  @Autowired
  public AutoClosePPBatchSendMessageProcessor(
      List<IHandler<SettlementPeriodDealDetailModel, ProcessLog>> handlerChains) {
    super(handlerChains);
  }

  @Component("autoClosePPBProcessor")
  public class AutoClosePPBProcessor extends AbstractAutoCloseBatchSendMessageProcessor
      implements JobCoreProcess<SettlementPeriodDealDetailModel, SettlementPeriodModel> {

    @Override
    protected boolean assertRun() {
      if (getHour() < Integer.parseInt("2")) {
        return false;
      }
      return true;
    }

    // DCL的方式解决并发生成问题：账期初始化生成
    @Override
    protected SettlementPeriodModel initCSPAutoCloseBatchSendMessageJob() {
      try {
        SettlementPeriodModel period = null;//repository.querySettlementPeriod();
        if (Objects.nonNull(period)) {
          return period;
        }
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }

      // 多分片并发问题
//      DLock dLock = serviceDistributedLock.getLock("initCSPAutoCloseBatchSendMessageJob_PPB_lock");
      boolean locked = false;
      try {
//        locked = dLock.tryLock(60, TimeUnit.SECONDS);
        if (locked) {
          SettlementPeriodModel period = null;//repository.querySettlementPeriod();
          if (Objects.nonNull(period)) {
            return period;
          }

          SettlementPeriodModel currentPeriod = new SettlementPeriodModel();
          // currentPeriod.set...

          try {
            // repository.save(currentPeriod);
          } catch (Exception e) {
            System.out.println(e);
            return null;
          }
          return currentPeriod;
        }
      } catch (Exception e) {
        System.out.println(e);
      } finally {
        if (locked) {
//          dLock.unlock();
        }
      }
      return null;
    }

    @Override
    protected Date beginTime() {
      // PP_B : T+1 --> begin =< time < end
      return new Date();
    }

    @Override
    protected void closeAndSend(SettlementPeriodModel period)
        throws Exception {

      PageUtil.pageHandle(
              period::getPeriodId,
          Integer.parseInt("200"),
          (limitStart, pageSize) -> {
                // 查询 账期，遍历处理
            List<Object> batchs = new ArrayList<>();//cspStatementBatchRepository.pageAutoCloseBatch(limitStart, pageSize);
            Integer pageStart = 0;
            if (CollectionUtils.isEmpty(batchs)) {
              return null;
            } else {
//              pageStart = batchs.get(batchs.size() - 1).getStatementBatchId();
            }

            // 分片逻辑
//            Parameter parameter = PPCloseBatchThreadTTL.getParameterContext().get();
//            int index = Integer.parseInt(parameter.getString("qschedule_subtask_index"));
//            Integer shards = Integer.parseInt(parameter.getString("qschedule_subtask_shards"));
//            if (shards > 1) {
//              batchs =
//                  batchs.stream()
//                      .filter(e -> e.getStatementBatchId() % shards == index)
//                      .collect(Collectors.toList());
//            }
            // 分片逻辑

            // 灰度逻辑
//            int mod =
//                Integer.parseInt("100");
//            int remainder =
//                Integer.parseInt("100");
//
//            if (cspAutoCloseBatchSendMessageJob.getSettlementItemID()
//                    == EnumSettlementItem.PP_D.getSettlementItemId()
//                && cspAutoCloseBatchSendMessageJob
//                    .getCommissionDeduction()
//                    .equals(EnumSettlementItem.PP_D.getCommissionDeduction())) {
//              mod =
//                  Integer.parseInt("100");
//              remainder =
//                  Integer.parseInt("100");
//            }
//            if (cspAutoCloseBatchSendMessageJob.getSettlementItemID()
//                == EnumSettlementItem.PP_606.getSettlementItemId()) {
//              mod =
//                  Integer.parseInt("100");
//              remainder =
//                  Integer.parseInt("100");
//            }
//            if (cspAutoCloseBatchSendMessageJob.getSettlementItemID()
//                == EnumSettlementItem.NS.getSettlementItemId()) {
//              mod =
//                  Integer.parseInt("100");
//              remainder =
//                  Integer.parseInt("100");
//            }
//
//            int finalMod = mod;
//            int finalRemainder = remainder;
//            if (finalRemainder < finalMod) {
//              batchs =
//                  batchs.stream()
//                      .filter(
//                          e ->
//                              ParameterData.sample(
//                                  e.getStatementBatchId().longValue(), finalMod, finalRemainder))
//                      .collect(Collectors.toList());
//            }
            // 灰度逻辑


            // 数据处理...
            List<SettlementPeriodDealDetailModel> details = new ArrayList<>();
//            for (;;) {
//              SettlementPeriodDealDetailModel processDetail = process(null);
//              details.add(processDetail);
//            }
//
//            List<ProcessLog> batchLogs = new ArrayList<>();
//            for (SettlementPeriodDealDetailModel detail : details) {
//              List<ProcessLog> logs = chainDoResult(detail);
//              batchLogs.addAll(logs);
//            }
            RetryUtil.executeWithRetry(
                () -> {
                  try {
//                    cspAutoCloseAndSendDetailRepository.batchUpdate(details);
//                    cspStatementBatchRepository.saveLog(batchLogs);
                    return true;
                  } catch (Exception ex) {
                    System.out.println(ex);
                    return false;
                  }
                },
                true,
                10);

            return pageStart;
          });

      // 当日未处理
      alarm(
          () -> {
            try {
              return 0; // cspStatementBatchRepository.countAutoCloseBatch();
            } catch (Exception e) {
              System.out.println(e);
            }
            return -1;
          });
    }

    @Override
    public SettlementPeriodDealDetailModel process(SettlementPeriodModel cspStatementBatch)
        throws Exception {
      // 账期处理流程
      // 1、合计 商家--订单类型--订单金额--各种债务
      // 2、给商家发账单
      return new SettlementPeriodDealDetailModel();
    }
  }

  @Component("autoClosePPDProcessor")
  public class AutoClosePPDProcessor extends AutoClosePPBProcessor {

    @Override
    protected boolean assertRun() {
      if (getHour() < Integer.parseInt("2")) {
        return false;
      }
      return true;
    }

    @Override
    protected SettlementPeriodModel initCSPAutoCloseBatchSendMessageJob() {
      try {
        SettlementPeriodModel period = null;//repository.querySettlementPeriod();
        if (Objects.nonNull(period)) {
          return period;
        }
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }

      // 多分片并发问题
//      DLock dLock = serviceDistributedLock.getLock("initCSPAutoCloseBatchSendMessageJob_PPD_lock");
      boolean locked = false;
      try {
//        locked = dLock.tryLock(60, TimeUnit.SECONDS);
        if (locked) {
          SettlementPeriodModel period = null;//repository.querySettlementPeriod();
          if (Objects.nonNull(period)) {
            return period;
          }

          SettlementPeriodModel currentPeriod = new SettlementPeriodModel();
          // currentPeriod.set...

          try {
            // repository.save(currentPeriod);
          } catch (Exception e) {
            System.out.println(e);
            return null;
          }
          return currentPeriod;
        }
      } catch (Exception e) {
        System.out.println(e);
      } finally {
        if (locked) {
//          dLock.unlock();
        }
      }
      return null;
    }

    @Override
    protected Date beginTime() {
      // PP_D : T+3 --> begin =< time < end
      return new Date();
    }
  }

  @Component("autoClosePP606Processor")
  public class AutoClosePP606Processor extends AutoClosePPBProcessor {

    @Override
    protected boolean assertRun() {
      if (getHour() < Integer.parseInt("14")) {
        return false;
      }
      return true;
    }

    @Override
    protected SettlementPeriodModel initCSPAutoCloseBatchSendMessageJob() {
      try {
        SettlementPeriodModel period = null;//repository.querySettlementPeriod();
        if (Objects.nonNull(period)) {
          return period;
        }
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }

      // 多分片并发问题
//      DLock dLock = serviceDistributedLock.getLock("initCSPAutoCloseBatchSendMessageJob_PP606_lock");
      boolean locked = false;
      try {
//        locked = dLock.tryLock(60, TimeUnit.SECONDS);
        if (locked) {
          SettlementPeriodModel period = null;//repository.querySettlementPeriod();
          if (Objects.nonNull(period)) {
            return period;
          }

          SettlementPeriodModel currentPeriod = new SettlementPeriodModel();
          // currentPeriod.set...

          try {
            // repository.save(currentPeriod);
          } catch (Exception e) {
            System.out.println(e);
            return null;
          }
          return currentPeriod;
        }
      } catch (Exception e) {
        System.out.println(e);
      } finally {
        if (locked) {
//          dLock.unlock();
        }
      }
      return null;
    }

    @Override
    protected Date beginTime() {
      // PP_606 : T+1 --> begin =< time < end
      return new Date();
    }
  }

  @Component("autoCloseNSProcessor")
  public class AutoCloseNSProcessor extends AutoClosePPBProcessor {

    @Override
    protected boolean assertRun() {
      if (getHour() < Integer.parseInt("21")) {
        return false;
      }
      return true;
    }

    @Override
    protected SettlementPeriodModel initCSPAutoCloseBatchSendMessageJob() {
      try {
        SettlementPeriodModel period = null;//repository.querySettlementPeriod();
        if (Objects.nonNull(period)) {
          return period;
        }
      } catch (Exception e) {
        System.out.println(e);
        return null;
      }

      // 多分片并发问题
//      DLock dLock = serviceDistributedLock.getLock("initCSPAutoCloseBatchSendMessageJob_NS_lock");
      boolean locked = false;
      try {
//        locked = dLock.tryLock(60, TimeUnit.SECONDS);
        if (locked) {
          SettlementPeriodModel period = null;//repository.querySettlementPeriod();
          if (Objects.nonNull(period)) {
            return period;
          }

          SettlementPeriodModel currentPeriod = new SettlementPeriodModel();
          // currentPeriod.set...

          try {
            // repository.save(currentPeriod);
          } catch (Exception e) {
            System.out.println(e);
            return null;
          }
          return currentPeriod;
        }
      } catch (Exception e) {
        System.out.println(e);
      } finally {
        if (locked) {
//          dLock.unlock();
        }
      }
      return null;
    }

    @Override
    protected Date beginTime() {
      // NS : T+2 --> begin =< time < end
      return new Date();
    }
  }
}
