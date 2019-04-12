package com.zhuyitech.parking.tool.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.tool.domain.NotificationTemplate;
import com.zhuyitech.parking.tool.mapper.NotificationTemplateMapper;
import com.zhuyitech.parking.tool.service.NotificationTemplateCrudService;
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
        return selectOne(entityWrapper);
    }

}
