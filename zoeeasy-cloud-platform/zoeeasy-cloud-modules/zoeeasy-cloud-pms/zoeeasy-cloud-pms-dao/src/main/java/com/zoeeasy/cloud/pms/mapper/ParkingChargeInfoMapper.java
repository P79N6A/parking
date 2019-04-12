package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingChargeInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:停车场收费信息表Mapper
 * @Autor: Kane
 * @Date: 2018/9/17
 */
@Repository("parkingChargeInfoMapper")
public interface ParkingChargeInfoMapper extends BaseMapper<ParkingChargeInfoEntity> {

    /**
     * 查找规则信息
     *
     * @param entityEntityWrapper Wrapper<ParkingChargeInfoEntity>
     * @return ParkingChargeInfoEntity
     */
    @SqlParser(filter = true)
    ParkingChargeInfoEntity findByParkingId(@Param("ew") Wrapper<ParkingChargeInfoEntity> entityEntityWrapper);

    /**
     * 查找规则信息
     *
     * @param id ID
     * @return 规则信息
     */
    @SqlParser(filter = true)
    ParkingChargeInfoEntity findById(@Param("id") Long id);

    /**
     * 添加ParkingChargeInfoEntity(无租户)
     *
     * @param parkingChargeInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean insertParkingChargeInfoNonTenant(ParkingChargeInfoEntity parkingChargeInfoEntity);

    /**
     * 修改ParkingChargeInfoEntity(无租户)
     *
     * @param parkingChargeInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingChargeInfoNonTenant(ParkingChargeInfoEntity parkingChargeInfoEntity);

    /**
     * 删除ParkingChargeInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingChargeInfoNonTenant(@Param("parkingId") Long parkingId);

}
