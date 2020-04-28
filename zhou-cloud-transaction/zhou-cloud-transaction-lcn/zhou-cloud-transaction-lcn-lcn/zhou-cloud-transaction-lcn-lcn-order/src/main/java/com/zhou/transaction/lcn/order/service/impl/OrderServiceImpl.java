package com.zhou.transaction.lcn.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.zhou.transacation.base.dao.enums.StatusEnum;
import com.zhou.transacation.base.dao.model.FwTradeLog;
import com.zhou.transacation.base.dao.service.FwTradeLogService;
import com.zhou.transaction.lcn.order.feign.RemoteSendServiceFeign;
import com.zhou.transaction.lcn.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description 订单记录表-业务实现
 * @author zhouhoujun
 * @date '2020-03-22'
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FwTradeLogService fwTradeLogService;

    @Autowired
    private RemoteSendServiceFeign remoteSendServiceFeign;

    @LcnTransaction
    @Override
    public void saveAndPayOrder(String productName) {

        FwTradeLog fwTradeLog =new FwTradeLog(StatusEnum.TWO);
        fwTradeLog.setProductId(System.currentTimeMillis());
        fwTradeLog.setProductName(productName);
        fwTradeLogService.save(fwTradeLog);
        log.info("[订单状态{}]=>{},当前商品id=>{},商品名称=>{}",fwTradeLog.getOrderId(), StatusEnum.TWO.getDesc(),fwTradeLog.getProductId(),fwTradeLog.getProductName());

        remoteSendServiceFeign.sendOrder(fwTradeLog);

        int i=1/0;
    }

}