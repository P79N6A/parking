package com.zhuyitech.sms.serivce;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.sms.domain.SmsTemplate;

/**
 *
 */
public interface SmsTemplateCrudService extends CrudService<SmsTemplate> {

    /**
     * 根据ID查询模板
     *
     * @param clientId
     * @param templateId
     * @return
     */
    SmsTemplate getByTemplateId(String clientId, String templateId);
}