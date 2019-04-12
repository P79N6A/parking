package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserDriverLicense;

/**
 * 用户驾驶证
 * @Date: 2018/1/15
 * @author: yuzhicheng
 */
public interface UserDriverLicenseCrudService extends CrudService<UserDriverLicense> {

    /**
     * 通过用户ID查找
     */
    UserDriverLicense findByUserId(Long userId);

    Integer deleteByUserId(Long userId);


}
