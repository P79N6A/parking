package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.MessageLog;
import com.zhuyitech.parking.tool.mapper.MessageLogMapper;
import com.zhuyitech.parking.tool.service.MessageLogCrudService;

import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
@Service("messageLogCrudService")
public class MessageLogCrudServiceImpl extends ServiceImpl<MessageLogMapper, MessageLog> implements MessageLogCrudService {
}
