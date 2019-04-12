package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingChargeInfoEntity;

public interface ParkingChargeInfoCrudService extends CrudService<ParkingChargeInfoEntity> {

    /**
     * 根据停车场id获取停车场收费信息
     *
     * @param parkingId 停车场id
     * @return ParkingChargeInfoEntity
     */
    ParkingChargeInfoEntity findByParkingId(Long parkingId);


    /**
     * 根据id获取停车场收费信息
     *
     * @param id ID
     * @return 停车场收费信息
     */
    ParkingChargeInfoEntity findById(Long id);

    /**
     * 添加ParkingChargeInfoEntity(无租户)
     *
     * @param parkingChargeInfoEntity
     * @return
     */
    boolean insertParkingChargeInfoNonTenant(ParkingChargeInfoEntity parkingChargeInfoEntity);

    /**
     * 修改ParkingChargeInfoEntity(无租户)
     *
     * @param parkingChargeInfoEntity
     * @return
     */
    boolean updateParkingChargeInfoNonTenant(ParkingChargeInfoEntity parkingChargeInfoEntity);

    /**
     * 删除ParkingChargeInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingChargeInfoNonTenant(Long parkingId);

}
