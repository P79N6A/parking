package com.zhuyitech.parking.tool.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.PushDevice;

import java.util.List;


/**
 * @author walkman
 */
public interface PushDeviceCrudService extends CrudService<PushDevice> {

    /**
     * 根据设备号查询
     *
     * @param registrationId registrationId
     * @param status         status
     * @return
     */
    PushDevice getPushDeviceByRegistrationId(String registrationId, Integer status);

    /**
     * 根据用户的Id和状态查询设备号
     *
     * @param userId userId
     * @param status status
     * @return
     */
    List<PushDevice> queryListByUserIdAndStatus(Long userId, Integer status);

    /**
     * 根据类型和状态查询推送设备
     *
     * @param type
     * @param status
     * @return
     */
    List<PushDevice> queryListByTypeAndStatus(String type, Integer status);

    /**
     * @param userId
     * @description 根据用户的Id删除设备号
     */
    void deleteByUserId(Long userId);

    /**
     * 切换数据状态
     *
     * @param oldStatus
     * @param newStatus
     * @param registrationId
     * @return
     */
    int toggleValid(Integer oldStatus, Integer newStatus, Long userId, String registrationId);

    /**
     * 该设备未关联用户记录
     * @param registrationId
     * @return
     */
    PushDevice selectByRegistrationId(String registrationId);
}
