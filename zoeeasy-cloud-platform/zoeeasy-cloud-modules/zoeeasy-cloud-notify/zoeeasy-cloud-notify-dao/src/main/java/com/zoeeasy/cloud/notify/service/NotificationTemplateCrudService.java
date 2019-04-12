package com.zoeeasy.cloud.notify.service;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.notify.domain.NotificationTemplate;

/**
 *
 */
public interface NotificationTemplateCrudService extends CrudService<NotificationTemplate> {

    /**
     * 根据ID查询模板
     *
     * @param templateId
     * @return
     */
    NotificationTemplate getByTemplateId(String templateId);

    /**
     * 获取模板
     *
     * @param entityWrapper
     * @return
     */
    NotificationTemplate findTemplateId(EntityWrapper<NotificationTemplate> entityWrapper);
}