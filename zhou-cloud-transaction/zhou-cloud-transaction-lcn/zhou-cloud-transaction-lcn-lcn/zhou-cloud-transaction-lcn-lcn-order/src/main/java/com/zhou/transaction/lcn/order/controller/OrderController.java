package com.zhou.transaction.lcn.order.controller;

import com.zhou.common.result.ZhResult;
import com.zhou.transaction.lcn.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhoujun
 * @description 订单接口
 * @date '2020-03-22'
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     *
     * @return
     */
    @GetMapping("saveOrder")
    public ZhResult saveOrder(String productName) {
        orderService.saveAndPayOrder(productName);
        return ZhResult.ok();
    }


}
