package com.zoeeasy.cloud.charge.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleHistoryEntity;
import com.zoeeasy.cloud.charge.mapper.ParkingChargeRuleHistoryMapper;
import com.zoeeasy.cloud.charge.service.ParkingChargeRuleHistoryCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@Service("parkingChargeRuleHistoryCrudService")
public class ParkingChargeHistoryCrudServiceImpl extends CrudServiceImpl<ParkingChargeRuleHistoryMapper,
        ParkingChargeRuleHistoryEntity> implements ParkingChargeRuleHistoryCrudService {
}
