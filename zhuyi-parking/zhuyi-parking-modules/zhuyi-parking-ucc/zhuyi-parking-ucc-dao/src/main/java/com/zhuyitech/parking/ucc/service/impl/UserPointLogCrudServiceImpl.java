package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserPointLog;
import com.zhuyitech.parking.ucc.mapper.UserPointLogMapper;
import com.zhuyitech.parking.ucc.service.UserPointLogCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2017-11-21
 */
@Service("userPointLogCrudService")
public class UserPointLogCrudServiceImpl extends CrudServiceImpl<UserPointLogMapper, UserPointLog> implements UserPointLogCrudService {

    /**
     * @param transactionNo
     * @return
     */
    @Override
    public UserPointLog findByTransactionNo(String transactionNo) {
        UserPointLog userPointLog = new UserPointLog();
        userPointLog.setTransactionNo(transactionNo);
        return baseMapper.selectOne(userPointLog);
    }
}
