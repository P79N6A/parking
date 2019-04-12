package com.zoeeasy.cloud.pms.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingRecordHistoryEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingRecordHistoryMapper;
import com.zoeeasy.cloud.pms.service.ParkingRecordHistoryCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Service("parkingRecordHistoryCrudService")
public class ParkingRecordHistoryCrudServiceImpl extends CrudServiceImpl<ParkingRecordHistoryMapper, ParkingRecordHistoryEntity> implements ParkingRecordHistoryCrudService {

    /**
     * 保存停车历史记录
     *
     * @param parkingRecordHistoryEntity
     * @return
     */
    @Override
    public boolean save(ParkingRecordHistoryEntity parkingRecordHistoryEntity) {
        return baseMapper.save(parkingRecordHistoryEntity);
    }
}
