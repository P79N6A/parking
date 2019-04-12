package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.NotificationTemplate;

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
}