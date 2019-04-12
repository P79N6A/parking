package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingFloorEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2018/9/14.
 */
@Repository("parkingFloorMapper")
public interface ParkingFloorMapper extends BaseMapper<ParkingFloorEntity> {

    Integer findLotCountByParkingId(@Param("parkingId") Long parkingId);

    Integer findLotFixedTotalByParkingId(@Param("parkingId") Long parkingId);

    @SqlParser(filter = true)
    ParkingFloorEntity getparkingFloor(@Param("id") Long id);

}
