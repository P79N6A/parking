package com.zoeeasy.cloud.pms.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingGarageInfoEntity;

public interface ParkingGarageInfoCrudService extends CrudService<ParkingGarageInfoEntity> {

    /**
     * 按照name查找
     *
     * @param name
     * @return
     */
    ParkingGarageInfoEntity findByName(String name);

    /**
     * 按照客户端code查找
     *
     * @param localCode
     * @return
     */
    ParkingGarageInfoEntity findByCode(String localCode);

    /**
     * 无租户根据云平台code查询
     *
     * @param code
     * @return
     */
    ParkingGarageInfoEntity selectByCode(String code);

    /**
     * 查询停车场下所有车库车位总数
     *
     * @param parkingId
     * @return
     */
    Integer findGarageLotTotalByParkingId(Long parkingId);

    /**
     * 无租户查询停车场下所有车库车位总数
     *
     * @param parkingId
     * @return
     */
    Integer selectGarageLotTotalByParkingId(Long parkingId);

    /**
     * 查询停车场下所有车库固定车位总数
     *
     * @param parkingId
     * @return
     */
    Integer findGarageLotFixedTotalByParkingId(Long parkingId);

    /**
     * 无租户查询停车场下所有车库固定车位总数
     *
     * @param parkingId
     * @return
     */
    Integer selectGarageLotFixedTotalByParkingId(Long parkingId);

    /**
     * 根据车库名称和停车场id查询
     *
     * @param name
     * @param parkingId
     * @return
     */
    ParkingGarageInfoEntity findGarageByNameAndParkingId(String name, Long parkingId);

    /**
     * 无租户根据车库名称和停车场id查询
     *
     * @param name
     * @param parkingId
     * @return
     */
    ParkingGarageInfoEntity selectGarageByNameAndParkingId(String name, Long parkingId);

    /**
     * 无车库查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    ParkingGarageInfoEntity parkingGarageByParkingIdAndCode(Long parkingId, String code);

    /**
     * 无租户，车库查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    ParkingGarageInfoEntity findGarageByParkingIdAndCode(Long parkingId, String code);

    /**
     * 有车库查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    ParkingGarageInfoEntity parkingGarageByParkingIdAndCodeAndGateId(Long parkingId, String code);
    /**
     * 删除车库(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteGarageByParkingIdNonTenant(Long parkingId);

    /**
     * 修改车库(无租户)
     * @param parkingGarageInfoEntity
     * @return
     */
    boolean updateGarageNonTenant(ParkingGarageInfoEntity parkingGarageInfoEntity);

}
