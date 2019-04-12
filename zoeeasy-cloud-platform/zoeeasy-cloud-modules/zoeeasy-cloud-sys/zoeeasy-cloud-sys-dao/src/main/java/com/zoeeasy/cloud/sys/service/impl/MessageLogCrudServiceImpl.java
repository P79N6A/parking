package com.zoeeasy.cloud.sys.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.MessageLogEntity;
import com.zoeeasy.cloud.sys.mapper.MessageLogMapper;
import com.zoeeasy.cloud.sys.service.MessageLogCrudService;
import org.springframework.stereotype.Service;

/**
 * 第三方接口消息请求表(MessageLog)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Service("messageLogCrudService")
public class MessageLogCrudServiceImpl extends CrudServiceImpl<MessageLogMapper, MessageLogEntity> implements MessageLogCrudService {
}