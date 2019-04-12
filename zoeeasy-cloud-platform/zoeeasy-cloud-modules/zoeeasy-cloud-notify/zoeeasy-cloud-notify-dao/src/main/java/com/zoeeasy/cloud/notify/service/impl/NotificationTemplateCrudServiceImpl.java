package com.zoeeasy.cloud.notify.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.notify.domain.NotificationTemplate;
import com.zoeeasy.cloud.notify.mapper.NotificationTemplateMapper;
import com.zoeeasy.cloud.notify.service.NotificationTemplateCrudService;
import org.springframework.stereotype.Service;

/**
 * 消息通知模板服务
 *
 * @author walkamn
 */
@Service("notificationTemplateCrudService")
public class NotificationTemplateCrudServiceImpl extends CrudServiceImpl<NotificationTemplateMapper, NotificationTemplate> implements NotificationTemplateCrudService {

    /**
     * 根据ID查询模板
     *
     * @param templateId
     * @return
     */
    @Override
    public NotificationTemplate getByTemplateId(String templateId) {
        EntityWrapper<NotificationTemplate> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("templateId", templateId);
        entityWrapper.eq("status", 1);
        return baseMapper.selectByTemplateId(entityWrapper);
    }

    /**
     * 获取模板
     *
     * @param entityWrapper
     * @return
     */
    @Override
    public NotificationTemplate findTemplateId(EntityWrapper<NotificationTemplate> entityWrapper) {
        return baseMapper.findTemplateId(entityWrapper);
    }

}
