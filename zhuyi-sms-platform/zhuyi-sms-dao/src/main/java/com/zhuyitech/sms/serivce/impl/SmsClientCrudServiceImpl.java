package com.zhuyitech.sms.serivce.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.sms.domain.SmsClient;
import com.zhuyitech.sms.mapper.SmsClientMapper;
import com.zhuyitech.sms.serivce.SmsClientCrudService;
import org.springframework.stereotype.Service;


@Service("smsClientCrudService")
public class SmsClientCrudServiceImpl extends CrudServiceImpl<SmsClientMapper, SmsClient> implements SmsClientCrudService {

    @Override
    public SmsClient getByClientId(String clientId) {
        EntityWrapper<SmsClient> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("clientId", clientId);
        entityWrapper.eq("status", 1);
        return selectOne(entityWrapper);
    }

}