package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingGateInfoEntity;

public interface ParkingGateInfoCrudService extends CrudService<ParkingGateInfoEntity> {

    ParkingGateInfoEntity findByName(String name);

    ParkingGateInfoEntity findByCode(String code);

    /**
     * 无租户根据客户端编号查询
     *
     * @param localCode
     * @return
     */
    ParkingGateInfoEntity selectByCode(String localCode);

    /**
     * 无租户根据云平台code查询出入口
     *
     * @param code
     * @return
     */
    ParkingGateInfoEntity selectGateByCode(String code);

    ParkingGateInfoEntity findByParkingIdeAndCode(Long parkingId,String code);

    /**
     * 无租户，根据停车场Id和编号查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    ParkingGateInfoEntity selectByParkingIdeAndCode (Long parkingId,String code);

    /**
     * 无租户，未关联车库，出入口名称停车场内唯一
     *
     * @param parkingId
     * @param name
     * @return
     */
    ParkingGateInfoEntity findByParkingIdeAndName(Long parkingId, String name);

    /**
     * 无租户根据Id和名称查询车库内名称唯一
     *
     * @param garageId
     * @param name
     * @return
     */
    ParkingGateInfoEntity findByGarageIdeAndName(Long garageId, String name);

    /**
     * 无租户，根据车库Id和出入口编号查询
     *
     * @param garageId
     * @param code
     * @return
     */
    ParkingGateInfoEntity findByGarageIdAndCode(Long garageId, String code);

    Integer findCountByGarageId(Long garageId);

    ParkingGateInfoEntity findByNameAndGarageId(String name, Long id);

    ParkingGateInfoEntity findByNameAndParkingId(String name, Long id);

    /**
     * 删除ParkingGateInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingGateInfoNonTenant(Long parkingId);

    /**
     * 修改gate(无租户)
     * @param parkingGateInfoEntity
     * @return
     */
    boolean updateParkingGateNonTenant(ParkingGateInfoEntity parkingGateInfoEntity);
}
