package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingAreaEntity;

/**
 * Created by song on 2018/9/21.
 */
public interface ParkingAreaCrudService extends CrudService<ParkingAreaEntity> {

    ParkingAreaEntity findByName(String name);

    ParkingAreaEntity findByCode(String code);

    Integer findLotTotalByParkingId(Long parkingId);

    Integer findLotFixedTotalByParkingId(Long parkingId);

    Integer findCountByGarageId(Long garageId);

    ParkingAreaEntity findByNameAndGarageId(String name, Long garageId);

    ParkingAreaEntity findByNameAndParkingId(String name, Long parkingId);

    ParkingAreaEntity findByCodeAndParkingId(String code, Long parkingId);

    ParkingAreaEntity getParkingAreaInfo(Long id);

    /**
     * 删除泊车区域(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingAreaNonTenant(Long parkingId);

    ParkingAreaEntity selectParkingAreaNonTenant(String code);

    ParkingAreaEntity selectByNameAndGarageId(String name, Long garageId);

    ParkingAreaEntity selectByNameAndParkingId(String name, Long parkingId);

    ParkingAreaEntity selectByCodeAndParkingId(String code, Long parkingId);

    ParkingAreaEntity selectByCode(String code);

    Integer findLotTotalByParkingIdNonTenant(Long parkingId);

    Integer findLotFixedTotalByParkingIdNonTenant(Long parkingId);

    boolean updateParkingAreaNonTenant(ParkingAreaEntity parkingAreaEntity);

}
