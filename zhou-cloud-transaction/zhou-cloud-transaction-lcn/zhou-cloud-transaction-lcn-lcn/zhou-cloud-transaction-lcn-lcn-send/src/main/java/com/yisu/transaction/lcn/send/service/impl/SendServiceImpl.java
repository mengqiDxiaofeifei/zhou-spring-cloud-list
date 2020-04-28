package com.yisu.transaction.lcn.send.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.yisu.transaction.lcn.send.service.SendService;
import com.zhou.transacation.base.dao.enums.StatusEnum;
import com.zhou.transacation.base.dao.model.FwTradeLog;
import com.zhou.transacation.base.dao.service.FwTradeLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouhoujun
 * @description 发货-业务实现
 * @date '2020-03-25'
 */
@Service
@Slf4j
public class SendServiceImpl implements SendService {

    @Autowired
    private FwTradeLogService fwTradeLogService;

    @LcnTransaction
    @Override
    public void sendOrder(FwTradeLog fwTradeLog) {
        fwTradeLog.setStatus(StatusEnum.THREE.getValue());
        fwTradeLog.setStatusDsc(StatusEnum.THREE.getDesc());
        fwTradeLogService.save(fwTradeLog);
        log.info("[订单状态{}]=>{},当前商品id=>{},商品名称=>{}", fwTradeLog.getOrderId(), StatusEnum.THREE.getDesc(), fwTradeLog.getProductId(), fwTradeLog.getProductName());
    }
}