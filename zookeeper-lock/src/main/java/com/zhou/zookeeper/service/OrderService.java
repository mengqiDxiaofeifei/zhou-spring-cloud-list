package com.zhou.zookeeper.service;

import com.zhou.zookeeper.component.OrderNumGenerator;
import com.zhou.zookeeper.dao.OrderDao;
import com.zhou.zookeeper.entity.Order;
import com.zhou.zookeeper.service.impl.ZookeeperDistrbuteLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;


@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderNumGenerator orderNumGenerator;
    @Resource
    private ZookeeperDistrbuteLock lock;


    public void createOrder() {
        lock.getLock();
        Order order = new Order();
        order.setId(orderNumGenerator.getOrderNumber());
        order.setPrice(new BigDecimal(19.1));
        order.setCreateTime(null);
        orderDao.insert(order);
        lock.unLock();
    }
}