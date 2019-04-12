package com.zoeeasy.cloud.notify.service;


import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.notify.domain.Notification;

import java.util.List;

/**
 * @author walkman
 */
public interface NotificationCrudService extends CrudService<Notification> {

    /**
     * 通过用户ID和读状态查找
     *
     * @param userId
     * @param readStatus
     * @return
     */
    List<Notification> findListByUserIdAndReadStatus(Long userId, Boolean readStatus);

    /**
     * @param userId
     * @param notifyType
     * @param readStatus
     * @return
     */
    Integer selectCountByUserIdAndNotifyType(Long userId, Integer notifyType, Boolean readStatus);

    /**
     * 通过用户ID查找最近两条
     *
     * @param userId
     * @return
     */
    List<Notification> findLatestTwoListByUserId(Long userId);

    /**
     * 通过userId和id查找
     *
     * @param userId
     * @param id
     * @return
     */
    Notification findByUserIdAndId(Long userId, Long id);

    /**
     * 批量删除消息
     *
     * @param ids
     * @return
     */
    boolean deleteNotification(List<Long> ids, Long userId);

}
