package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.PacketVehicleEntity;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
public interface PacketVehicleCrudService extends CrudService<PacketVehicleEntity> {

    Integer findByParkingIdAndRuleId(Long parkingId, Long ruleId);

    Integer findByRuleId(Long ruleId);

    PacketVehicleEntity findByPlateNumberAndParkingIdAndRuleId(String plateNumber, Long parkingId, Long ruleId);

    PacketVehicleEntity findByPlateNoAndParkingId(String plateNumber, Long id);
}
