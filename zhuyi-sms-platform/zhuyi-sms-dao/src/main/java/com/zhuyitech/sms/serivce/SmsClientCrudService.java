package com.zhuyitech.sms.serivce;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.sms.domain.SmsClient;

public interface SmsClientCrudService extends CrudService<SmsClient> {

    /**
     * 根据ID查询客户端
     *
     * @param clientId
     * @return
     */
    SmsClient getByClientId(String clientId);
}