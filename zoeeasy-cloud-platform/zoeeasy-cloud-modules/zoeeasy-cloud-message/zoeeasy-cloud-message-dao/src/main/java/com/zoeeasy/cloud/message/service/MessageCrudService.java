package com.zoeeasy.cloud.message.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.message.domain.MessageEntity;

/**
 * @author walkman
 */
public interface MessageCrudService extends CrudService<MessageEntity> {

    /**
     * 保存消息
     *
     * @param messageEntity
     */
    void saveMessage(MessageEntity messageEntity);
}