package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoAroundEntity;

import java.util.List;

/**
 * Created by song on 2018/9/18.
 */
public interface ParkingInfoCrudService extends CrudService<ParkingInfoEntity> {

    /**
     * ***********************
     * * 租户特定操作
     *****************************/

    /**
     * 根据code查找
     *
     * @param code
     * @return
     */
    ParkingInfoEntity findByCode(String code);

    /**
     * 根据fullName查找
     *
     * @param fullName
     * @return
     */
    ParkingInfoEntity findByFullName(String fullName);

    /**
     * 查找多个停车场
     */
    List<ParkingInfoEntity> selectAllParkingInfoList();

    /**
     * 根据停车场id获取停车场信息
     *
     * @param parkingId
     * @return
     */
    ParkingInfoEntity getParkInfoById(Long parkingId);

    /**
     ************************
     * * 租户无关操作
     *****************************/

    /**
     * 通过海康平台ID查找
     *
     * @param hikParkId 海康平台ID
     * @return
     */
    ParkingInfoEntity findByHikParkId(String hikParkId);

    /**
     * 无租户根据code查找
     *
     * @param code
     * @return
     */
    ParkingInfoEntity selectByCode(String code);

    /**
     * 通过场库系统编号查询
     *
     * @param localCode
     * @return
     */
    ParkingInfoEntity selectByLocalCode(String localCode);

    /**
     * 停车场管理系统查询
     *
     * @param code
     * @param tenantId
     * @return
     */
    ParkingInfoEntity findByCodeAndTenantId(String code, Long tenantId);

    /**
     * 查询ParkingInfo
     *
     * @param wrapper
     * @return
     */
    ParkingInfoEntity selectPlatformParkingInfo(EntityWrapper<ParkingInfoEntity> wrapper);

    /**
     * 查询审核停车场列表
     *
     * @param page
     * @param ew
     * @return
     */
    List<ParkingInfoEntity> selectPlatformParkingInfoPageList(Pagination page, Wrapper<ParkingInfoEntity> ew);

    /**
     * 条件查询平台停车场列表
     *
     * @param ew
     * @return
     */
    List<ParkingInfoEntity> selectPlatformParkingInfoList(EntityWrapper<ParkingInfoEntity> ew);

    /**
     * 通过位置查询
     *
     * @param wrapper
     * @param minLongitude
     * @param maxLongitude
     * @param minLatitude
     * @param maxLatitude
     * @return
     */
    List<ParkingInfoEntity> selectListByPosition(Wrapper<ParkingInfoEntity> wrapper, Double minLongitude, Double maxLongitude, Double minLatitude, Double maxLatitude);

    /**
     * @param wrapper
     * @param minLongitude
     * @param maxLongitude
     * @param minLatitude
     * @param maxLatitude
     * @return
     */
    List<ParkingInfoAroundEntity> selectAroundListByPosition(Wrapper<ParkingInfoEntity> wrapper, Double minLongitude, Double maxLongitude, Double minLatitude, Double maxLatitude);

    /**
     * 修改ParkingInfo
     *
     * @param parkingInfoEntity
     * @return
     */
    boolean updateParkInfoById(ParkingInfoEntity parkingInfoEntity);

    /**
     * 修改停车场
     *
     * @param parkingInfoEntity parkingInfoEntity
     * @param ew                ew
     * @return
     */
    boolean updateParkingInfo(ParkingInfoEntity parkingInfoEntity, EntityWrapper<ParkingInfoEntity> ew);

    /**
     * 更改停车场审核状态
     *
     * @param parkingId
     * @return
     */
    boolean updateParkingAuditStatus(Long parkingId);

}
