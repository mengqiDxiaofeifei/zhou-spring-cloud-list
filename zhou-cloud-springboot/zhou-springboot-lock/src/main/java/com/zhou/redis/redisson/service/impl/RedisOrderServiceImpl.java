package com.zhou.redis.redisson.service.impl;

import com.zhou.component.OrderNumGenerator;
import com.zhou.constant.RedisConstant;
import com.zhou.dao.OrderDao;
import com.zhou.entity.Order;
import com.zhou.redis.redisson.service.RedisOrderService;
import com.zhou.utils.RedissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisOrderServiceImpl implements RedisOrderService {

    private static OrderNumGenerator codeGenerator = new OrderNumGenerator();

    @Autowired
    private OrderDao orderDao;

    @Override
    public String createOrder() {
        try {
            //最多等待3秒，20秒后自动解锁
            RedissonUtil.tryLock(RedisConstant.OrderLockConstant.ORDER_LOCK_KEY
                    , TimeUnit.SECONDS, 3, 20);

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
            RedissonUtil.unlock(RedisConstant.OrderLockConstant.ORDER_LOCK_KEY);
        }
        return "ok";
    }
}
