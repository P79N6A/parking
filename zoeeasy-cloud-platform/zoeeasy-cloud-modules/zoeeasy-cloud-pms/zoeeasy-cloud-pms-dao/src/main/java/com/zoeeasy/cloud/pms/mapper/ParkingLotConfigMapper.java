package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingLotConfigEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2018/9/14.
 */
@Repository("parkingLotConfigMapper")
public interface ParkingLotConfigMapper extends BaseMapper<ParkingLotConfigEntity> {

    /**
     * 删除ParkingLotConfig（无租户）
     *
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingLotConfigNonTenant(@Param("parkingId") Long parkingId);

}
