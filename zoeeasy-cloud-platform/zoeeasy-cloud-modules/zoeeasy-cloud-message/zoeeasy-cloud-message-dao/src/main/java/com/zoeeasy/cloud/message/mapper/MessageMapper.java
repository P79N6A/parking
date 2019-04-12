package com.zoeeasy.cloud.message.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.message.domain.MessageEntity;

/**
 * @author walkman
 */
public interface MessageMapper extends BaseMapper<MessageEntity> {

    /**
     * 保存消息
     *
     * @param messageEntity
     */
    @SqlParser(filter = true)
    void saveMessage(MessageEntity messageEntity);
}
