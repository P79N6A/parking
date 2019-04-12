package com.zhuyitech.parking.pms.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.pms.domain.VehicleRecord;
import com.zhuyitech.parking.pms.mapper.VehicleRecordMapper;
import com.zhuyitech.parking.pms.service.VehicleRecordCrudService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 平台车辆的CrudServiceImpl
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
@Service("vehicleRecordCrudService")
public class VehicleRecordCrudServiceImpl extends CrudServiceImpl<VehicleRecordMapper, VehicleRecord> implements VehicleRecordCrudService {

    /**
     * 根据车牌号获取车辆
     */
    @Override
    public VehicleRecord findByPlateNumber(String plateNumber) {
        EntityWrapper<VehicleRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("plateNumber", plateNumber);
        List<VehicleRecord> userCarInfoList = baseMapper.selectList(entityWrapper);
        if (userCarInfoList != null && !userCarInfoList.isEmpty()) {
            return userCarInfoList.get(0);
        }
        return null;
    }
}
