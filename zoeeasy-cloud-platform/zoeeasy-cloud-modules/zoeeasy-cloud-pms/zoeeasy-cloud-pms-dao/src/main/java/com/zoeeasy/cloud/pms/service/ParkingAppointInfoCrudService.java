package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingAppointInfoEntity;

public interface ParkingAppointInfoCrudService extends CrudService<ParkingAppointInfoEntity> {

    ParkingAppointInfoEntity findByParkingId(Long parkingId);

    ParkingAppointInfoEntity selectByParkingId(Long parkingId);

    /**
     * 添加ParkingAppointInfoEntity(无租户)
     *
     * @param parkingAppointInfoEntity
     * @return
     */
    boolean insertParkingAppointInfoNonTenant(ParkingAppointInfoEntity parkingAppointInfoEntity);

    /**
     * 修改ParkingAppointInfoEntity(无租户)
     *
     * @param parkingAppointInfoEntity
     * @return
     */
    boolean updateParkingAppointInfoNonTenant(ParkingAppointInfoEntity parkingAppointInfoEntity);

    /**
     * 删除ParkingAppointInfoEntity(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingAppointInfoNonTenant(Long parkingId);

    /**
     * 根据ID查找(无租户)
     *
     * @param id
     * @return
     */
    ParkingAppointInfoEntity findById(Long id);
}
