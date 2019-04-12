package com.zhuyitech.parking.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.data.domain.ParkingRecord;
import com.zhuyitech.parking.data.mapper.ParkingRecordMapper;
import com.zhuyitech.parking.data.service.ParkingRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @since 2017-11-27
 */
@Service("parkingRecordCrudService")
public class ParkingRecordCrudServiceImpl extends ServiceImpl<ParkingRecordMapper, ParkingRecord> implements ParkingRecordCrudService {
}
