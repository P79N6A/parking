package com.zoeeasy.cloud.notify.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.notify.domain.NotificationTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 */
@Repository
public interface NotificationTemplateMapper extends BaseMapper<NotificationTemplate> {

    @SqlParser(filter = true)
    NotificationTemplate selectByTemplateId(@Param("ew") EntityWrapper<NotificationTemplate> entityWrapper);

    @SqlParser(filter = true)
    NotificationTemplate findTemplateId(@Param("ew") EntityWrapper<NotificationTemplate> entityWrapper);
}
