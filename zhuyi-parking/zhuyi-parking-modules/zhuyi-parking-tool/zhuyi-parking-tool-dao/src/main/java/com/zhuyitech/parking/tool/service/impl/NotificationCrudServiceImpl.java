package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.tool.domain.Notification;
import com.zhuyitech.parking.tool.mapper.NotificationMapper;
import com.zhuyitech.parking.tool.service.NotificationCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 * @date 2018-05-16
 */
@Service("notificationCrudService")
public class NotificationCrudServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements NotificationCrudService {

    /**
     * 通过用户ID和读状态查找
     *
     * @param userId
     * @param readStatus
     * @return
     */
    @Override
    public List<Notification> findListByUserIdAndReadStatus(Long userId, Boolean readStatus) {
        EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        if (readStatus != null) {
            entityWrapper.eq("readStatus", readStatus);
        }
        entityWrapper.orderBy("sendTime", false);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * @param userId
     * @param notifyType
     * @param readStatus
     * @return
     */
    @Override
    public Integer selectCountByUserIdAndNotifyType(Long userId, Integer notifyType, Boolean readStatus) {
        EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        if (notifyType != null) {
            entityWrapper.eq("notifyType", notifyType);
        }
        if (readStatus != null) {
            entityWrapper.eq("readStatus", readStatus);
        }
        return baseMapper.selectCount(entityWrapper);
    }

    /**
     * 通过用户ID查找最近两条
     *
     * @param userId
     * @return
     */
    @Override
    public List<Notification> findLatestTwoListByUserId(Long userId) {
        EntityWrapper<Notification> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        entityWrapper.orderBy("sendTime", false);
        entityWrapper.last("LIMIT 2");
        return baseMapper.selectList(entityWrapper);
    }
}
