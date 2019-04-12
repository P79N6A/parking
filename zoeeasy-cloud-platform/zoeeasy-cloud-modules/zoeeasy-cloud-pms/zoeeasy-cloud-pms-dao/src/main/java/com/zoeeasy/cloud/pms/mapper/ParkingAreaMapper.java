package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2018/9/21.
 */
@Repository("parkingAreaMapper")
public interface ParkingAreaMapper extends BaseMapper<ParkingAreaEntity> {

    Integer selectLotTotalByParkingId(@Param("parkingId") Long parkingId);

    Integer selectLotFixedTotalByParkingId(@Param("parkingId") Long parkingId);

    /**
     * 删除泊车区域(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingAreaNonTenant(@Param("parkingId") Long parkingId);

    @SqlParser(filter = true)
    ParkingAreaEntity selectParkingAreaNonTenant(@Param("ew") Wrapper<ParkingAreaEntity> ew);

    @SqlParser(filter = true)
    Integer selectLotTotalByParkingIdNonTenant(@Param("parkingId") Long parkingId);

    @SqlParser(filter = true)
    Integer selectLotFixedTotalByParkingIdNonTenant(@Param("parkingId") Long parkingId);

    @SqlParser(filter = true)
    boolean updateParkingAreaNonTenant(ParkingAreaEntity parkingAreaEntity);


    @SqlParser(filter = true)
    ParkingAreaEntity getParkingAreaInfo(@Param("id") Long id);

}
