package com.zoeeasy.cloud.pms.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.VehicleRecordEntity;
import com.zoeeasy.cloud.pms.mapper.VehicleRecordMapper;
import com.zoeeasy.cloud.pms.service.VehicleRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Service("vehicleRecordCrudService")
public class VehicleRecordCrudServiceImpl extends CrudServiceImpl<VehicleRecordMapper, VehicleRecordEntity> implements VehicleRecordCrudService {

    @Override
    public VehicleRecordEntity findByPlateNumber(String plateNumber) {
        VehicleRecordEntity vehicleRecordEntity = new VehicleRecordEntity();
        vehicleRecordEntity.setPlateNumber(plateNumber);
        return baseMapper.selectOne(vehicleRecordEntity);
    }
}
