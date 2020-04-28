package com.zhou.transacation.base.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.transacation.base.dao.mapper.FwTransactionLogMapper;
import com.zhou.transacation.base.dao.model.FwTransactionLog;
import com.zhou.transacation.base.dao.service.FwTransactionLogService;
import org.springframework.stereotype.Service;

/**
 * @author zhouhoujun
 * @description 事务日志表-业务实现
 * @date '2020-03-26'
 */
@Service
public class FwTransactionLogServiceImpl extends ServiceImpl<FwTransactionLogMapper, FwTransactionLog> implements FwTransactionLogService {


}
