package com.zhou.zookeeper.controller;



import com.zhou.zookeeper.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/create")
    public String createOrder() {
        orderService.createOrder();
        return "ok";
    }
}
