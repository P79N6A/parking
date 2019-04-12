package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserCarAuth;

/**
 * @author AkeemSuper
 */
public interface UserCarAuthCrudService extends CrudService<UserCarAuth> {

    /**
     * 根据车牌号获取车辆
     *
     * @param plateNumber plateNumber
     * @return UserCarAuth
     */
    UserCarAuth findByPlateNumber(String plateNumber);

    /**
     * 根据车牌号和用户Id获取车辆
     *
     * @param plateNumber plateNumber
     * @param userId      userId
     * @return UserCarAuth
     */
    UserCarAuth findByPlateNumberAndUserId(String plateNumber, Long userId);
}