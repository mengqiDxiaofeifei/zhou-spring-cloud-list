package com.zhou.redis.service;

import com.zhou.redis.result.ApiResult;

public interface SeckillService {

    /**
     * 开始秒杀商品
     *
     * @param userId
     * @param secKillId
     * @return ApiResult
     * @throws
     * @author zhouxinlei
     * @date 2019-12-13 10:50:09
     */
    ApiResult startSecKillOperation(String userId, Long secKillId);




    /**
     * 查询秒杀商品
     *
     * @return List<SecKillProduct>
     * @throws
     * @author zhouxinlei
     * @date 2019-12-13 10:50:09
     */
    void listInitSecKillProduct();
}
