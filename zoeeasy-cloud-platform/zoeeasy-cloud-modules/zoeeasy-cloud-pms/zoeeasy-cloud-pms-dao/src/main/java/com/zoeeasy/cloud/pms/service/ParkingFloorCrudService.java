package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;

/**
 *
 */
public interface ParkingFloorCrudService extends CrudService<ParkingFloorEntity> {

    Integer findLotCountByParkingId(Long parkingId);

    Integer findLotFixedTotalByParkingId(Long parkingId);

    Integer findCountByGarageId(Long garageId);

    ParkingFloorEntity getparkingFloor(Long garageId);

}


