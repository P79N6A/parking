package com.zoeeasy.cloud.notify.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.notify.domain.Notification;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 */
@Repository
public interface NotificationMapper extends BaseMapper<Notification> {

    /**
     * 根据用户Id获取消息条数
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    Integer selectNotificationByUserIdList(@Param("ew") Wrapper<Notification> wrapper);

    /**
     * 批量删除(有租户)
     *
     * @param ids
     * @param userId
     * @return
     */
    boolean deleteNotification(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
