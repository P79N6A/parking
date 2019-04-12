package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zoeeasy.cloud.pms.domain.ParkingInfoAroundEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by song on 2018/9/18.
 */
@Repository("parkingInfoMapper")
public interface ParkingInfoMapper extends BaseMapper<ParkingInfoEntity> {

    /**
     * 通过海康停车场id获取停车场
     *
     * @param hikParkId
     * @return
     */
    @SqlParser(filter = true)
    ParkingInfoEntity findByHikParkId(@Param("hikParkId") String hikParkId);

    /**
     * 根据停车场id获取
     *
     * @param parkingId
     * @return
     */
    @SqlParser(filter = true)
    ParkingInfoEntity findByParkingId(@Param("parkingId") Long parkingId);

    /**
     * 根据客户端编号获取停车场信息
     *
     * @param localCode
     * @return
     */
    @SqlParser(filter = true)
    ParkingInfoEntity findByLocalCode(@Param("localCode") String localCode);

    /**
     * 根据停车场编号查询
     *
     * @param code
     * @return
     */
    @SqlParser(filter = true)
    ParkingInfoEntity findByCode(@Param("code") String code);

    /**
     * 无租户列表查询
     *
     * @param ew ew
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingInfoEntity> selectListTenantLess(@Param("ew") Wrapper<ParkingInfoEntity> ew);

    /**
     * 停车场附近功能查询
     *
     * @param ew ew
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingInfoAroundEntity> selectAroundListTenantLess(@Param("ew") Wrapper<ParkingInfoEntity> ew);

    /**
     * 无租户分页查询
     *
     * @param page
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingInfoEntity> selectPageListTenantLess(Pagination page, @Param("ew") Wrapper<ParkingInfoEntity> ew);

    /**
     * 修改parkingInfo(无租户)
     *
     * @param parkingInfoEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkInfoNonTenant(ParkingInfoEntity parkingInfoEntity);

    /**
     * 更新停车场
     *
     * @param parkingInfoEntity
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkingInfo(@Param("entity") ParkingInfoEntity parkingInfoEntity, @Param("ew") EntityWrapper<ParkingInfoEntity> ew);

    /**
     * 更改停车场审核状态
     *
     * @param parkingId
     * @return
     */
    boolean updateParkingAuditStatus(@Param("parkingId") Long parkingId);

}
