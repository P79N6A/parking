package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.charge.domain.ParkingAppointmentRuleHistoryEntity;
import com.zoeeasy.cloud.charge.mapper.ParkingAppointmentRuleHistoryMapper;
import com.zoeeasy.cloud.charge.service.ParkingAppointmentRuleHistoryCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 */
@Service("ParkingAppointmentRuleHistoryCrudService")
public class ParkingAppointmentRuleHistoryCrudServiceImpl extends ServiceImpl<ParkingAppointmentRuleHistoryMapper, ParkingAppointmentRuleHistoryEntity> implements ParkingAppointmentRuleHistoryCrudService {

}