package com.zhou.transaction.lcn.order.service;

/**
 * @description 订单记录表-业务接口
 * @author zhouhoujun
 * @date '2020-03-22'
 */
public interface OrderService{

    /**
     * 下单
     */
    void saveAndPayOrder(String productName);

}