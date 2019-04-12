package com.zoeeasy.cloud.notify.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.notify.domain.UserPushTag;
import com.zoeeasy.cloud.notify.mapper.UserPushTagMapper;
import com.zoeeasy.cloud.notify.service.UserPushTagCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/12 0012
 */
@Service("userPushTagCrudService")
public class UserPushTagCrudServiceImpl extends CrudServiceImpl<UserPushTagMapper, UserPushTag> implements UserPushTagCrudService {
    /**
     * 根据userId 获取
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserPushTag> findByUserId(Long userId) {
        EntityWrapper<UserPushTag> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 删除userPushTag
     *
     * @param userId
     * @param deleteTagIds
     */
    @Override
    public void deleteTagIds(Long userId, List<Long> deleteTagIds) {
        EntityWrapper<UserPushTag> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        entityWrapper.in("tagId", deleteTagIds);
        baseMapper.delete(entityWrapper);
    }
}
