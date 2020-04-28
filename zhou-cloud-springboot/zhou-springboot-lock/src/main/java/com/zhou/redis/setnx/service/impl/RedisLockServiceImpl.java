package com.zhou.redis.setnx.service.impl;


import com.zhou.component.OrderNumGenerator;
import com.zhou.dao.OrderDao;
import com.zhou.entity.Order;
import com.zhou.redis.redisson.service.RedisOrderService;
import com.zhou.redis.test.LockCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class RedisLockServiceImpl implements RedisOrderService {


    private static OrderNumGenerator codeGenerator = new OrderNumGenerator();
    private String lockKey = "lockKey";

    private LockCase lock;

//    @Autowired
//    @Qualifier("jedisPool")
//    private JedisPool jedis;


//    public RedisLockServiceImpl() {
//        this.lock = new LockCase(jedis.getResource(), lockKey);
//    }

    @Autowired
    private OrderDao orderDao;


    @Override
    public String createOrder() {
        try {
            //加锁
            lock.lock();
            //获取锁成功
            log.info("start lock lockNxExJob success");
            String orderNumber = codeGenerator.getOrderNumber();
            // 最好异步去生成订单详情 mq发消息  什么的，
            Order order = new Order();
            order.setId(orderNumber);
            order.setPrice(new BigDecimal(19));
            order.setCreateTime(new Date());
            orderDao.insert(order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "ok";
    }
}
