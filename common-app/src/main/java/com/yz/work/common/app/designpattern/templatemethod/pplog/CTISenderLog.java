package com.yz.work.common.app.designpattern.templatemethod.pplog;

import com.yz.work.common.app.designpattern.chainofresponsibility.IHandler;
import com.yz.work.common.app.designpattern.templatemethod.ProcessLog;
import com.yz.work.common.app.designpattern.templatemethod.SettlementPeriodDealDetailModel;
import org.springframework.stereotype.Component;

/**
 * @author yangzhengzhang
 * @description
 * @date 2022-08-05 11:32
 */
@Component
public class CTISenderLog implements IHandler<SettlementPeriodDealDetailModel, ProcessLog> {
    @Override
    public ProcessLog handle(SettlementPeriodDealDetailModel detail) throws Exception {
        return null;
    }
}
