package com.zhou.redis.controller;



import com.zhou.redis.result.ApiResult;
import com.zhou.redis.service.SeckillService;
import com.zhou.redis.service.impl.SeckillServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 中文类名：秒杀 前端控制器
 * 中文描述：秒杀 前端控制器
 *
 * @author zhouxinlei
 * @date 2019-12-13 10:12:02
 */
@RestController
@RequestMapping("/secKill")
public class SecKillController {


    @Resource
    private SeckillServiceImpl seckillService;



//    @Override
//    public void afterPropertiesSet() throws Exception {
//            seckillService.listInitSecKillProduct();
//    }

    /**
     * 开始秒杀订单（redis分布式锁）
     *
     * @param userId
     * @param secKillId
     * @return boolean
     * @throw
     * @date 2019-12-13 10:50:35
     */
    @GetMapping("/secKillOrder")
    public ApiResult createSecKillOrder(String userId, Long secKillId) {
        return seckillService.startSecKillOperation(userId, secKillId);
    }


}
