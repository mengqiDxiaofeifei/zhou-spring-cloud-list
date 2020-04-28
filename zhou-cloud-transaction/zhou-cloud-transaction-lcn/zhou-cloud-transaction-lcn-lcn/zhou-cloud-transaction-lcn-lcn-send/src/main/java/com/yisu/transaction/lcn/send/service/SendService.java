package com.yisu.transaction.lcn.send.service;

import com.zhou.transacation.base.dao.model.FwTradeLog;

/**
 * @author zhouhoujun
 * @description 发货-业务接口
 * @date '2020-03-25'
 */
public interface SendService {

    /**
     * 订单发货
     */
    void sendOrder(FwTradeLog fwTradeLog);

}