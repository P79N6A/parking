package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;

public interface ParkingApplyInfoCrudService extends CrudService<ParkingApplyInfoEntity> {

    ParkingApplyInfoEntity selectParkingApplyByParkingId(Long parkingId);

    boolean updateParkingApply(ParkingApplyInfoEntity parkingApplyInfoEntity);

}
