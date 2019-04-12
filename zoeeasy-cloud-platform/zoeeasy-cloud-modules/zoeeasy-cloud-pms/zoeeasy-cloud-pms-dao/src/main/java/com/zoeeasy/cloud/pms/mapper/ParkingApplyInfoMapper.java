package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("parkingApplyInfoMapper")
public interface ParkingApplyInfoMapper extends BaseMapper<ParkingApplyInfoEntity> {

    /**
     * 根据停车场id查询审核记录
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    ParkingApplyInfoEntity selectParkingApplyByParkingId(@Param("parkingId") Long parkingId);

    /**
     * 修改ParkingApplyInfoEntity
     * @param parkingApplyInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingApply(ParkingApplyInfoEntity parkingApplyInfoEntity);

}
