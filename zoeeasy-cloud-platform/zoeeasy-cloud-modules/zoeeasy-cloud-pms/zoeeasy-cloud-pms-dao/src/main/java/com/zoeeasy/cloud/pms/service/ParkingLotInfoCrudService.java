package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingLotInfoEntity;

import java.util.List;

/**
 * 车位
 *
 * @author walkman
 */
public interface ParkingLotInfoCrudService extends CrudService<ParkingLotInfoEntity> {

    /**
     * 更新车位状态
     *
     * @param parkingLotId parkingLotId
     * @param status       status
     * @return
     */
    Integer updateParkingLotStatus(Long parkingLotId, Integer status);

    /**
     * 通过停车场ID，编号查询
     *
     * @param parkingId
     * @param code
     * @return
     */
    ParkingLotInfoEntity findByParkingIdAndCode(Long parkingId, String code);

    /**
     * 通过停车场id ,编号,id 查询
     *
     * @param id
     * @param parkingId
     * @param code
     * @return
     */
    ParkingLotInfoEntity findByIdAndCode(Long id, Long parkingId, String code);

    /**
     * 通过海康平台ID查询
     *
     * @param hikParkingLotId hikParkingLotId
     */
    ParkingLotInfoEntity findByHikParkingLotId(String hikParkingLotId);

    /**
     * 通过平台停车场ID和海康平台车位编号查询
     *
     * @param parkingId      parkingId
     * @param hikBerthNumber hikBerthNumber
     * @return ParkingLotInfo
     */
    ParkingLotInfoEntity findByParkingIdAndHikBerthNumber(Long parkingId, String hikBerthNumber);

    /**
     * 通过支付宝平台查询
     *
     * @param aliParkingLotId aliParkingLotId
     */
    ParkingLotInfoEntity findByAliParkingLotId(String aliParkingLotId);

    /**
     * 根据停车场id和泊位id获取泊位
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    ParkingLotInfoEntity findByParkingId(Long parkingId, Long parkingLotId, Long tenantId);

    /**
     * 根据泊位编号查找
     *
     * @param code
     * @return
     */
    ParkingLotInfoEntity findByNumber(String code);

    /**
     * 过车记录获取泊位信息
     *
     * @param wrapper
     * @return
     */
    ParkingLotInfoEntity findForPassVehicle(Wrapper<ParkingLotInfoEntity> wrapper);

    /**
     * 根据code查找
     *
     * @param code
     * @return
     */
    ParkingLotInfoEntity findByCode(String code);

    /**
     * 根据车库ID获取
     *
     * @param garageId
     * @return
     */
    List<ParkingLotInfoEntity> findByGarageId(Long garageId);

    /**
     * 根据parkingAreaId获取
     *
     * @param parkingAreaId
     * @return
     */
    List<ParkingLotInfoEntity> findByParkingAreaId(Long parkingAreaId);

    Integer findCountByGarageId(Long garageId);

    Integer findCountByParkingAreaId(Long parkingAreaId);

    ParkingLotInfoEntity findByNumberAndParkingAreaId(String number, Long parkingAreaId);

    ParkingLotInfoEntity findByNumberAndGarageId(String number, Long garageId);

    ParkingLotInfoEntity findByNumberAndParkingId(String number, Long parkingId);

    /**
     * 根据停车场Id和泊位编号分页查询
     *
     * @param parkingId
     * @param number
     * @return
     */
    List<ParkingLotInfoEntity> findByParkingIdAndLotNumber(Pagination page, Long parkingId, String number);

    /**
     * 根据停车场Id获取泊位code
     *
     * @param parkingId
     * @return
     */
    List<ParkingLotInfoEntity> findCodeByParkingId(Long parkingId);

    /**
     * 删除ParkingLotInfo(无租户)
     *
     * @param parkingId
     * @return
     */
    boolean deleteParkingLotInfoNonTenant(Long parkingId);

    ParkingLotInfoEntity findByLocalCode(String localCode);

    /**
     * 修改ParkingLotInfo(无租户)
     * @param parkingLotInfoEntity
     * @return
     */
    boolean updateParkingLotInfoNonTenant(ParkingLotInfoEntity parkingLotInfoEntity);
}
