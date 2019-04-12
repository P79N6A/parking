package com.zoeeasy.cloud.message.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.message.domain.MessageEntity;
import com.zoeeasy.cloud.message.mapper.MessageMapper;
import com.zoeeasy.cloud.message.service.MessageCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @since 2017-12-11
 */
@Service("messageCrudService")
public class MessageCrudServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements MessageCrudService {

    /**
     * 保存消息
     *
     * @param messageEntity
     */
    @Override
    public void saveMessage(MessageEntity messageEntity) {
        baseMapper.saveMessage(messageEntity);
    }
}

