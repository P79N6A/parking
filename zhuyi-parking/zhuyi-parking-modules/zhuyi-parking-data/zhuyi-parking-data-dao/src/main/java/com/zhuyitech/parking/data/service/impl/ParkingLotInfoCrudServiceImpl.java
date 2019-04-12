package com.zhuyitech.parking.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.data.domain.ParkingLotInfo;
import com.zhuyitech.parking.data.mapper.ParkingLotInfoMapper;
import com.zhuyitech.parking.data.service.ParkingLotInfoCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2017-12-11
 */
@Service("parkingLotInfoCrudService")
public class ParkingLotInfoCrudServiceImpl extends ServiceImpl<ParkingLotInfoMapper, ParkingLotInfo> implements ParkingLotInfoCrudService {

}
