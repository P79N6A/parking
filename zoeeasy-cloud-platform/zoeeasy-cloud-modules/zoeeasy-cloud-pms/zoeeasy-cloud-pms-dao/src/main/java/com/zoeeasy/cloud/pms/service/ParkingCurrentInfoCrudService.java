package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingCurrentInfoEntity;

import java.util.List;

public interface ParkingCurrentInfoCrudService extends CrudService<ParkingCurrentInfoEntity> {


    ParkingCurrentInfoEntity findByParkingId(Long parkingId);

    /**
     * 根据停车场id获取车位信息
     *
     * @param parkingId
     * @return
     */
    ParkingCurrentInfoEntity selectByParkingId(Long parkingId);

    /**
     * 更新可用车位数量
     *
     * @param id           id
     * @param lotAvailable lotAvailable
     * @return
     */
    Integer updateLotAvailable(Long id, Integer lotAvailable);

    /**
     * 更新车位信息
     *
     * @param parkingCurrentInfo
     */
    void updateCurrentInfoById(ParkingCurrentInfoEntity parkingCurrentInfo);

    /**
     * 根据id获取车位信息
     *
     * @param parkingCurrentInfoId
     * @return
     */
    ParkingCurrentInfoEntity findById(Long parkingCurrentInfoId);

    /**
     * 查询可预约停车场数量
     *
     * @param wrapper
     * @return
     */
    Integer selectAppointParkCount(Wrapper<ParkingCurrentInfoEntity> wrapper);

    /**
     * 修改可用车位数
     *
     * @param parkingCurrentInfoEntity
     * @return
     */
    boolean updateParkingLotAvailable(ParkingCurrentInfoEntity parkingCurrentInfoEntity);

    /**
     * 添加ParkingCurrentInfoEntity(无租户)
     *
     * @param parkingCurrentInfoEntity
     * @return
     */
    boolean insertParkingCurrentInfoNonTenant(ParkingCurrentInfoEntity parkingCurrentInfoEntity);

    /**
     * 删除ParkingCurrentInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingCurrentInfoNonTenant(Long parkingId);

    /**
     * 查询可预约停车场列表
     *
     * @param wrapper
     * @return
     */
    List<ParkingCurrentInfoEntity> selectAppointParkList(Wrapper<ParkingCurrentInfoEntity> wrapper);
}
