package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.PacketVehicleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Repository("PacketVehicleMapper")
public interface PacketVehicleMapper extends BaseMapper<PacketVehicleEntity> {

    @SqlParser(filter = true)
    PacketVehicleEntity findByPlateNoAndParkingId(@Param("plateNumber") String plateNumber,
                                                  @Param("parkingId") Long parkingId);
}
