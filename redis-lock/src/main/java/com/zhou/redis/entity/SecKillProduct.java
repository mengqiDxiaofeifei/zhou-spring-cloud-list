package com.zhou.redis.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 中文类名: 秒杀库存表
 * 中文描述: 秒杀库存表
 *
 * @author zhouxinlei
 * @date 2019-12-13 09:56:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table(name = "sec_kill_product")
public class SecKillProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品库存id
     */
    private Long secKillId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开启时间
     */
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 版本号
     */
    private Integer version;


}
