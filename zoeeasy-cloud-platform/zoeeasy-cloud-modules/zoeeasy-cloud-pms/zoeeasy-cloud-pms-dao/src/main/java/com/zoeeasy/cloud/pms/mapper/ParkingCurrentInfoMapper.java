package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pms.domain.ParkingCurrentInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:停车场当前状态表Mapper
 * @Autor: Kane
 * @Date: 2018/9/17
 */
@Repository("parkingCurrentInfoMapper")
public interface ParkingCurrentInfoMapper extends BaseMapper<ParkingCurrentInfoEntity> {

    /**
     * 根据id获取
     *
     * @param parkingCurrentInfoId
     * @return
     */
    @SqlParser(filter = true)
    ParkingCurrentInfoEntity findById(@Param("id") Long parkingCurrentInfoId);

    /**
     * 根据停车场Id获取车位信息
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    ParkingCurrentInfoEntity selectByParkingId(@Param("parkingId") Long parkingId);

    /**
     * @param parkingCurrentInfo
     */
    @SqlParser(filter = true)
    void updateCurrentInfoById(ParkingCurrentInfoEntity parkingCurrentInfo);

    /**
     * 查询可预约停车场数量
     * @param wrapper
     */
    @SqlParser(filter = true)
    Integer selectParkCount(@Param("ew") Wrapper<ParkingCurrentInfoEntity> wrapper);

    /**
     * 修改可用车位数
     *
     * @param parkingCurrentInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingLotAvailable(ParkingCurrentInfoEntity parkingCurrentInfoEntity);

    /**
     * 添加ParkingCurrentInfoEntity(无租户)
     *
     * @param parkingCurrentInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean insertParkingCurrentInfoNonTenant(ParkingCurrentInfoEntity parkingCurrentInfoEntity);

    /**
     * 删除ParkingCurrentInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    boolean deleteParkingCurrentInfoNonTenant(@Param("parkingId") Long parkingId);

    /**
     * 查询可预约停车场列表
     * @param wrapper
     */
    @SqlParser(filter = true)
    List<ParkingCurrentInfoEntity> selectParkList(@Param("ew") Wrapper<ParkingCurrentInfoEntity> wrapper);
}
