package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingRecordHistoryEntity;
import org.springframework.stereotype.Repository;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Repository("parkingRecordHistroyMapper")
public interface ParkingRecordHistoryMapper extends BaseMapper<ParkingRecordHistoryEntity> {

    /**
     * 保存停车历史记录
     *
     * @param parkingRecordHistoryEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean save(ParkingRecordHistoryEntity parkingRecordHistoryEntity);
}
