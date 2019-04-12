package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.VehicleRecordEntity;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
public interface VehicleRecordCrudService extends CrudService<VehicleRecordEntity> {

    VehicleRecordEntity findByPlateNumber(String plateNumber);

}
