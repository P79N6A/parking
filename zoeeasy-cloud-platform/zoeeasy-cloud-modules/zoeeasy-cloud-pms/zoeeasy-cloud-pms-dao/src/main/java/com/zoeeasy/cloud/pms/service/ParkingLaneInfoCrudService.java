package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingLaneInfoEntity;

/**
 * Created by song on 2018/9/14.
 */
public interface ParkingLaneInfoCrudService extends CrudService<ParkingLaneInfoEntity> {

    ParkingLaneInfoEntity findByName(String name);

    ParkingLaneInfoEntity findByCode(String code);

    /**
     * 无租户，根据云平台code查询
     *
     * @param code
     * @return
     */
    ParkingLaneInfoEntity selectByCode(String code);

    /**
     * 无租户，根据客户端code查询
     *
     * @param localCode
     * @return
     */
    ParkingLaneInfoEntity selectByLocalCode(String localCode);

    Integer findCountByGarageId(Long garageId);

    Integer findByGateId(Long gateId);

    ParkingLaneInfoEntity findByParkingIdAndCode(Long parkingId, String code);

    /**
     * 无租户，根据停车场Id和车道code查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    ParkingLaneInfoEntity selectByParkingIdAndCode(Long parkingId, String code);

    ParkingLaneInfoEntity findByParkingIdAndName(Long parkingId, String name);

    /**
     * 无租户，根据停车场Id，车道名称查询
     *
     * @param parkingId
     * @param name
     * @return
     */
    ParkingLaneInfoEntity selectByParkingIdAndName(Long parkingId, String name);

    ParkingLaneInfoEntity findByGarageIdAndName(Long garageId, String name);

    /**
     * 无租户，根据车库Id和车道名称查询
     *
     * @param garageId
     * @param name
     * @return
     */
    ParkingLaneInfoEntity selectByGarageIdAndName(Long garageId, String name);

    ParkingLaneInfoEntity findByGateIdAndName(Long gateId, String name);

    /**
     * 无租户，根据出入口Id和车道名称查询
     *
     * @param gateId
     * @param name
     * @return
     */
    ParkingLaneInfoEntity selectGateIdAndName(Long gateId, String name);

    ParkingLaneInfoEntity findByNameAndGateId(String name, Long id);

    ParkingLaneInfoEntity findByNameAndParkingId(String name, Long id);

    /**
     * 删除ParkingLaneInfo（无租户）
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingLaneInfoNonTenant(Long parkingId);

    /**
     * 修改车道（无租户）
     * @param parkingLaneInfoEntity
     * @return
     */
    boolean updateParkingLaneNonTenant(ParkingLaneInfoEntity parkingLaneInfoEntity);

}
