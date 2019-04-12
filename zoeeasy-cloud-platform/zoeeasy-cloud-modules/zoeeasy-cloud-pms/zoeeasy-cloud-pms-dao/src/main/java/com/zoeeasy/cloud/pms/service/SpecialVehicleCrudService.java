package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
public interface SpecialVehicleCrudService extends CrudService<SpecialVehicleEntity> {

    SpecialVehicleEntity findByPlateNumberAndParkingId(String plateNumber, Long parkingId);

    /**
     * @param plateNumber
     * @param id
     */
    SpecialVehicleEntity findByPlateNumber(String plateNumber, Long id);

    SpecialVehicleEntity findByIdAndSpecialType(Long id, Integer specialType);

    SpecialVehicleEntity findByParkingLotId(Long parkingLotId, String parkingLotNumber);
}
