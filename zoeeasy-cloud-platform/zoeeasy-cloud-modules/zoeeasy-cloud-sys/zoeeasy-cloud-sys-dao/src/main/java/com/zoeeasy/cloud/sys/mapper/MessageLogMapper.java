package com.zoeeasy.cloud.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.sys.domain.MessageLogEntity;
import org.springframework.stereotype.Repository;

/**
 * 第三方接口消息请求表(MessageLog)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Repository("messageLogMapper")
public interface MessageLogMapper extends BaseMapper<MessageLogEntity> {
}