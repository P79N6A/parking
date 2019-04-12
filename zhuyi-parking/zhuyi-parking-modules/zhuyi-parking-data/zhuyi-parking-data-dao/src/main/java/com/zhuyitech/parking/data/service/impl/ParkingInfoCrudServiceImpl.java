package com.zhuyitech.parking.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.data.domain.ParkingInfo;
import com.zhuyitech.parking.data.mapper.ParkingInfoMapper;
import com.zhuyitech.parking.data.service.ParkingInfoCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @date 2017-12-11
 */
@Service("parkingInfoCrudService")
public class ParkingInfoCrudServiceImpl extends ServiceImpl<ParkingInfoMapper, ParkingInfo> implements ParkingInfoCrudService {
}
