package com.yz.work.common.app.designpattern.templatemethod;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-05-23 17:23
 */
public abstract class AbstractAutoCloseBatchSendMessageProcessor implements IAutoCloseBatchSendMessageProcessor {

    private static final String CONFIG_GROUP_NAME = "AutoCloseAndSendBillsParam";
    private static final String CONFIG_NAME = "AutoCloseAndSendBills";
    private static final String CONFIG_NAME_PP606 = "AutoCloseAndSendBills-606";
    private final String JOB_RUN_STATUS_S = "S";// 0
    private final String JOB_RUN_STATUS_W = "W";// -3,-1,1
    private final String JOB_RUN_STATUS_F = "F";// -2
    private final String JOB_RUNABLE = "T";

    private final Integer DETAIL_DEAL_STATUS = 0;


    protected abstract SettlementPeriodModel initCSPAutoCloseBatchSendMessageJob();

    protected abstract Date beginTime();

    protected abstract boolean assertRun();

    protected abstract void closeAndSend(SettlementPeriodModel settlementPeriod) throws Exception;

    @Override
    public final Boolean autoCloseAndSendBills() {
        if (!assertRun()) {
            return true;
        }
        SettlementPeriodModel settlementPeriod = initCSPAutoCloseBatchSendMessageJob();
        if (Objects.isNull(settlementPeriod)) {
            return false;
        }
        try {
            closeAndSend(settlementPeriod);
        } catch (Exception e) {
            System.out.println("error info");
        }
        return true;
    }

    // 生成账期处理过程明细，执行了哪几步，处理结果等等
    protected SettlementPeriodDealDetailModel creatDetail(int batchId, int templateId, long jobId) {
        return new SettlementPeriodDealDetailModel();
    }


    // 给账单系统发消息，通知账单系统去生成账单并发送给酒店商家
    public StatementOfAccountModel creatCTISenderJob(Integer statementBatchId, Integer templateId) {
        return new StatementOfAccountModel();
    }

    public StatementOfAccountModel creatCTISenderJobForNS(Integer statementBatchId) {
        return new StatementOfAccountModel();
    }

    protected int getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    protected void alarm(Supplier<Integer> supplier) {
        if (supplier.get() > 0) {
            // 发邮件
        }
        if (supplier.get() == -1) {
            // 发邮件
        }
    }
}
