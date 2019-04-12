package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingLotStatusEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by song on 2018/9/14.
 */
@Repository("parkingLotStatusMapper")
public interface ParkingLotStatusMapper extends BaseMapper<ParkingLotStatusEntity> {

    /**
     * 根据停车场id和泊位id获取泊位状态
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    @SqlParser(filter = true)
    ParkingLotStatusEntity findByParkingIdAndParkingLotId(@Param("parkingId") Long parkingId, @Param("parkingLotId") Long parkingLotId, @Param("tenantId") Long tenantId);

    /**
     * 根据id更新车位状态
     *
     * @param parkingLotStatusEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer updateStatusById(ParkingLotStatusEntity parkingLotStatusEntity);

    /**
     * 根据泊位Id查询状态
     *
     * @return
     */
    @SqlParser(filter = true)
    ParkingLotStatusEntity findByParkingLotId(@Param("parkingLotId") Long parkingLotId);

    @SqlParser(filter = true)
    Integer findCountByParkingIdNonTenant(@Param("ew") Wrapper<ParkingLotStatusEntity> wrapper);

    /**
     * 删除ParkingLotStatus（无租户）
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingLotStatusNonTenant(@Param("parkingId") Long parkingId);
}
