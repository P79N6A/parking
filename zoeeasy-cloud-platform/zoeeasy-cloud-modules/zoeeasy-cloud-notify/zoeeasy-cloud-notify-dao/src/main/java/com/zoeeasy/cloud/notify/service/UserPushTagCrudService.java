package com.zoeeasy.cloud.notify.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.notify.domain.UserPushTag;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
public interface UserPushTagCrudService extends CrudService<UserPushTag> {

    /**
     * 根据userId 获取
     *
     * @param userId
     * @return
     */
    List<UserPushTag> findByUserId(Long userId);

    /**
     * 删除userPushTag
     *
     * @param userId
     * @param deleteTagIds
     */
    void deleteTagIds(Long userId, List<Long> deleteTagIds);
}
