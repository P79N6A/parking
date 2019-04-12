package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.UserLoginLogEntity;
import com.zoeeasy.cloud.ucc.mapper.UserLoginLogMapper;
import com.zoeeasy.cloud.ucc.service.UserLoginLogCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@Service("userLoginLogCrudService")
public class UserLoginLogCrudServiceImpl extends CrudServiceImpl<UserLoginLogMapper, UserLoginLogEntity> implements UserLoginLogCrudService {
    /**
     * 保存登录日志
     *
     * @param userLoginLogEntity
     */
    @Override
    public void save(UserLoginLogEntity userLoginLogEntity) {
        userLoginLogEntity.setCreationTime(DateUtils.now());
        this.baseMapper.save(userLoginLogEntity);
    }
}
