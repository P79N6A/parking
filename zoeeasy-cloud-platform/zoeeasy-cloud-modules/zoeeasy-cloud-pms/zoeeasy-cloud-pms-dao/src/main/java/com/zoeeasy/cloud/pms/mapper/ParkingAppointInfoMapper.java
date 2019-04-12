package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingAppointInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:停车场预约信息表Mapper
 * @Autor: Kane
 * @Date: 2018/9/17
 */
@Repository("parkingAppointInfoMapper")
public interface ParkingAppointInfoMapper extends BaseMapper<ParkingAppointInfoEntity> {

    /**
     * 根据parkingid查找(无租户)
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    ParkingAppointInfoEntity selectByParkingId(@Param("ew") Wrapper<ParkingAppointInfoEntity> ew);

    /**
     * 添加ParkingAppointInfoEntity(无租户)
     *
     * @param parkingAppointInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean insertParkingAppointInfoNonTenant(ParkingAppointInfoEntity parkingAppointInfoEntity);

    /**
     * 修改ParkingAppointInfoEntity(无租户)
     *
     * @param parkingAppointInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingAppointInfoNonTenant(ParkingAppointInfoEntity parkingAppointInfoEntity);

    /**
     * 删除ParkingAppointInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingAppointInfoNonTenant(Long parkingId);

    /**
     * 根据ID查找(无租户)
     *
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    ParkingAppointInfoEntity findById(@Param("id") Long id);
}
