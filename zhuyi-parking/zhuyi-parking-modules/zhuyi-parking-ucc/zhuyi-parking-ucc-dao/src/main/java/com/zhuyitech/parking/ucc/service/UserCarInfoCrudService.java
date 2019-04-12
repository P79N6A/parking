package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserCarInfo;

import java.util.List;

/**
 * 用户车辆
 *
 * @Date: 2018/1/4 0004
 * @author: AkeemSuper
 */
public interface UserCarInfoCrudService extends CrudService<UserCarInfo> {

    /**
     * 根据用户获取车辆认证信息
     *
     * @param userId
     * @return
     */
    List<UserCarInfo> findByUserId(Long userId);

    /**
     * 根据车牌号获取车辆
     *
     * @param plateNumber
     * @return
     */
    List<UserCarInfo> findByPlateNumber(String plateNumber);

    /**
     * 根据车牌号和用户ID获取车辆
     *
     * @param plateNumber
     * @param userId
     * @return
     */
    UserCarInfo findByPlateNumberAndUserId(String plateNumber, Long userId);

    /**
     * 通过用户ID和状态查找数量
     *
     * @param userId
     * @param status
     * @return
     */
    Integer selectCountByUserIdAndStatus(Long userId, Integer status);

    /**
     * 根据id和userid获取车辆
     * @param userId
     * @param id
     * @return
     */
    UserCarInfo findUserCarById(Long userId, Long id);
}
