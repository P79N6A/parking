package com.zhuyitech.sms.serivce.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.sms.domain.SmsTemplate;
import com.zhuyitech.sms.mapper.SmsTemplateMapper;
import com.zhuyitech.sms.serivce.SmsTemplateCrudService;
import org.springframework.stereotype.Service;


@Service("smsTemplateCrudService")
public class SmsTemplateCrudServiceImpl extends CrudServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateCrudService {

    /**
     * 根据ID查询模板
     *
     * @param clientId
     * @param templateId
     * @return
     */
    @Override
    public SmsTemplate getByTemplateId(String clientId, String templateId) {
        EntityWrapper<SmsTemplate> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("clientId", clientId);
        entityWrapper.eq("templateId", templateId);
        entityWrapper.eq("status", 1);
        return selectOne(entityWrapper);
    }

}
