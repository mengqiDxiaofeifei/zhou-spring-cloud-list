package com.zhou.zookeeper.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zhou.component.OrderNumGenerator;
import com.zhou.dao.OrderDao;
import com.zhou.entity.Order;
import com.zhou.zookeeper.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class CuratorDisLockOrderServiceImpl implements OrderService {


    private static OrderNumGenerator codeGenerator = new OrderNumGenerator();

    private static String LOCK_PATH = "/distribute-lock";


    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private OrderDao orderDao;

    @Override
    public String createOrder() {
        String orderCode = "";
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, LOCK_PATH);
        try {
            lock.acquire();
            //生成订单编号
            orderCode = codeGenerator.getOrderNumber();
            log.info(Thread.currentThread().getName() + "-->获取锁成功-->生成订单编号：{}", orderCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                lock.release();
                log.info(Thread.currentThread().getName() + "-->释放锁成功。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 最好异步去生成订单详情 mq发消息  什么的，
        Order order = new Order();
        order.setId(orderCode);
        order.setPrice(new BigDecimal(19));
        order.setCreateTime(new Date());
        orderDao.insert(order);
        return orderCode;
    }
}
