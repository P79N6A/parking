package com.zoeeasy.cloud.gather.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.gather.domain.HikvisionVehicleRecordEntity;

import java.util.Date;

/**
 * @author walkman
 */
public interface HikvisionVehicleRecordCrudService extends CrudService<HikvisionVehicleRecordEntity> {

    /**
     * 通过UUID查找
     *
     * @param passingUuid passingUuid
     * @return
     */
    HikvisionVehicleRecordEntity findByPassingUuid(String passingUuid);

    /**
     * 查找唯一的过车记录
     *
     * @param uuid uuid
     * @return 过车记录
     */
    HikvisionVehicleRecordEntity findOne(String uuid);

    /**
     * 查找唯一的过车记录
     *
     * @param uuid        uuid
     * @param parkCode    parkCode
     * @param plateNumber plateNumber
     * @param passDirect  passDirect
     * @param passTime    passTime
     * @return
     */
    HikvisionVehicleRecordEntity findOne(String uuid, String parkCode, String plateNumber, Integer passDirect, Date passTime);

    /**
     * 查找唯一的过车记录
     *
     * @param uuid        uuid
     * @param parkCode    parkCode
     * @param plateNumber plateNumber
     * @param passDirect  passDirect
     * @param passTime    passTime
     * @return
     */
    Integer findCount(String uuid, String parkCode, String plateNumber, Integer passDirect, Date passTime);

    /**
     * 更新处理状态
     *
     * @param passingUuid   passingUuid
     * @param processStatus processStatus
     * @param processRemark processRemark
     * @return
     */
    Integer updateProcessStatus(String passingUuid, Integer processStatus, String processRemark);

}
