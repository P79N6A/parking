package com.zhuyitech.parking.data.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.data.service.PassingVehicleRecordCrudService;
import com.zhuyitech.parking.data.domain.PassingVehicleRecord;
import com.zhuyitech.parking.data.mapper.PassingVehicleRecordMapper;
import org.springframework.stereotype.Service;

/**
 * @Description 过车记录表的service
 * @Date: 2018/1/11 0011
 * @author: AkeemSuper
 */
@Service("passingVehicleRecordCrudService")
public class PassingVehicleRecordCrudServiceImpl extends CrudServiceImpl<PassingVehicleRecordMapper, PassingVehicleRecord> implements PassingVehicleRecordCrudService {
}
