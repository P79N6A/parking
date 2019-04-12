package com.zhuyitech.parking.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.pms.domain.VehicleRecord;

/**
 * 平台车辆的CrudService
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
public interface VehicleRecordCrudService extends CrudService<VehicleRecord> {

    /**
     * 根据车牌号获取车辆
     *
     * @param plateNumber plateNumber
     * @return VehicleRecord
     */
    VehicleRecord findByPlateNumber(String plateNumber);

}
