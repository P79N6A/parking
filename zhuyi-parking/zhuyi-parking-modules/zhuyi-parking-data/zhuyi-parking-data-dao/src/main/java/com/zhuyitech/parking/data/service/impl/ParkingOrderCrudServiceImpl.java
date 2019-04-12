package com.zhuyitech.parking.data.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.data.domain.ParkingOrder;
import com.zhuyitech.parking.data.mapper.ParkingOrderMapper;
import com.zhuyitech.parking.data.service.ParkingOrderCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @since 2018-06-01
 */
@Service("parkingOrderCrudService")
public class ParkingOrderCrudServiceImpl extends ServiceImpl<ParkingOrderMapper, ParkingOrder> implements ParkingOrderCrudService {

}
