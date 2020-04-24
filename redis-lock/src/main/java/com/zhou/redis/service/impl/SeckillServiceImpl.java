package com.zhou.redis.service.impl;

import com.zhou.redis.constant.RedisConstant;
import com.zhou.redis.dao.SeckillProductDao;
import com.zhou.redis.entity.SecKillMessage;
import com.zhou.redis.entity.SecKillProduct;
import com.zhou.redis.result.ApiResult;
import com.zhou.redis.result.ErrorResult;
import com.zhou.redis.service.SeckillService;
import com.zhou.redis.utils.ListUtils;
import com.zhou.redis.utils.RedisUtil;
import com.zhou.redis.utils.RedissonUtil;
import org.redisson.api.RMapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class SeckillServiceImpl implements SeckillService {


    @Autowired
    private SeckillProductDao seckillProductDao;

    @Autowired
    private  RedisUtil redisUtil;

    private RMapCache<Long, Boolean> localCachedMap = RedissonUtil.getRMapCache("sparksys-kill_count");


//    @Autowired
//    public SeckillServiceImpl(SeckillProductDao seckillProductDao,
//                              RedisUtil redisUtil
//    ) {
//        this.seckillProductDao = seckillProductDao;
//        this.redisUtil = redisUtil;
//    }

    @Override
    public ApiResult startSecKillOperation(String userId, Long secKillId) {
        boolean result = false;
        final long killId = secKillId;
        boolean over = localCachedMap.get(secKillId);
        if (over) {
            return ApiResult.failed(ErrorResult.END_KILL);
        }
        try {
            /**
             *  尝试获取锁，最多等待3秒，
             * 上锁以后20秒自动解锁（实际项目中推荐这;/ 种，以防出现死锁）、
             * 这里根据预估秒杀人数，设定自动释放锁时间.
             */
            result = RedissonUtil.tryLock(RedisConstant.SecKillConstant.SEC_KILL_LOCK_KEY + killId
                    , TimeUnit.SECONDS, 3, 20);
            if (result) {
                //预减库存
                long stockCount = redisUtil.decrement(RedisConstant.SecKillConstant.STOCK_COUNT_KEY + +killId
                        , 1);
                if (stockCount >= 0) {
                    SecKillMessage secKillMessage = new SecKillMessage();
                    secKillMessage.setUserId(userId);
                    secKillMessage.setSeckillId(killId);
                    //发消息给rabbitmq 异步生成订单
                    // topicSender.sendMessage(secKillMessage);
                } else {
                    localCachedMap.put(secKillId, true);
                    redisUtil.set(RedisConstant.SecKillConstant.STOCK_COUNT_KEY + killId, 0);
                    return ApiResult.failed(ErrorResult.END_KILL);
                }
            } else {
                return ApiResult.failed(ErrorResult.MUCH_KILL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.success(ErrorResult.FAILED);
        } finally {
            if (result) {
                RedissonUtil.unlock(RedisConstant.SecKillConstant.SEC_KILL_LOCK_KEY + killId);
            }
        }
        return ApiResult.success(ErrorResult.SUCCESS_KILL);
    }


    @Override
    public void listInitSecKillProduct() {
        List<SecKillProduct> killProductList = seckillProductDao.selectAll();
        if (ListUtils.isEmpty(killProductList)) {
            return;
        }
        for (SecKillProduct secKillProduct : killProductList) {
            redisUtil.set(RedisConstant.SecKillConstant.STOCK_COUNT_KEY + secKillProduct.getSecKillId()
                    , secKillProduct.getStockCount());
            localCachedMap.put(secKillProduct.getSecKillId(), false);
        }
    }

}
