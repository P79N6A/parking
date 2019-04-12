package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingLotStatusEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by song on 2018/9/14.
 */
public interface ParkingLotStatusCrudService extends CrudService<ParkingLotStatusEntity> {

    /**
     * 根据停车场id和泊位id查询
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    ParkingLotStatusEntity findByParkingIdAndParkingLotId(Long parkingId, Long parkingLotId, Long tenantId);

    /**
     * 根据泊位ID查询
     *
     * @param parkingLotId
     * @return
     */
    ParkingLotStatusEntity findByParkingLotId(Long parkingLotId);

    /**
     * 修改泊位信息
     *
     * @param id
     * @param status
     * @return
     */
    Integer updateParkingLotStatus(Long id, Integer status, Date occupyTime);

    /**
     * 根据停车场ID查找
     *
     * @param parkingId
     * @return
     */
    List<ParkingLotStatusEntity> findByParkingId(Long parkingId);

    /**
     * 根据停车场id查找被占用泊位条数
     *
     * @param parkingId
     * @return
     */
    Integer findCountByParkingId(Long parkingId);

    /**
     * 根据停车场id查找被占用泊位条数(无租户)
     *
     * @param parkingId
     * @return
     */
    Integer findCountByParkingIdNonTenant(Long parkingId);

    /**
     * 删除ParkingLotStatus（无租户）
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingLotStatusNonTenant(Long parkingId);
}
