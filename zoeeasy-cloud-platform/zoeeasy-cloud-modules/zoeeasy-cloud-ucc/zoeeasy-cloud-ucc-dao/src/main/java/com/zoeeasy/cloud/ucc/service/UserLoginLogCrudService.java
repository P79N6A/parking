package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.UserLoginLogEntity;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
public interface UserLoginLogCrudService extends CrudService<UserLoginLogEntity> {

    /**
     * 保存登录日志
     *
     * @param userLoginLogEntity
     */
    void save(UserLoginLogEntity userLoginLogEntity);
}
