package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Repository("specialVehicleMapper")
public interface SpecialVehicleMapper extends BaseMapper<SpecialVehicleEntity> {

    @SqlParser(filter = true)
    SpecialVehicleEntity findByPlateNumber(@Param("plateNumber") String plateNumber, @Param("parkingId") Long parkingId);
}
