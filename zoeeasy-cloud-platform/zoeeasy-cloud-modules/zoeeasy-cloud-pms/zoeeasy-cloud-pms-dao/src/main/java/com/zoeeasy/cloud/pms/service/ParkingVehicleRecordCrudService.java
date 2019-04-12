package com.zoeeasy.cloud.pms.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pms.domain.ParkingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.park.dto.request.MyPlateNumberParkingGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingGuidanceParamDto;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
public interface ParkingVehicleRecordCrudService extends CrudService<ParkingVehicleRecordEntity> {

    /**
     * 找到车辆的最后一次在场记录
     *
     * @param parkingId   parkingId
     * @param plateNumber plateNumber
     * @param plateColor  plateColor
     * @param carType     carType
     * @param endTime     endTime
     */
    ParkingVehicleRecordEntity findLastParkingRecord(Long parkingId, Long parkingLotId, String plateNumber, Integer plateColor, Integer carType, Date endTime, Long tenantId);

    /**
     * 保存在场车辆记录
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    boolean save(ParkingVehicleRecordEntity parkingVehicleRecordEntity);

    /**
     * 根据入车记录号查找在停车辆
     *
     * @param intoRecordNo
     * @return
     */
    ParkingVehicleRecordEntity findByIntoRecordNo(String intoRecordNo);

    /**
     * 根据入车记录编号更新
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    Boolean updateParkVehicle(ParkingVehicleRecordEntity parkingVehicleRecordEntity);

    /**
     * 根据停车场Id和泊位Id查询
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    List<ParkingVehicleRecordEntity> findByParkingIdAndParkingLotId(Long parkingId, Long parkingLotId);

    /**
     * 根据泊位删除在停车辆
     *
     * @param entityWrapper
     */
    void deleteByLotId(Wrapper<ParkingVehicleRecordEntity> entityWrapper);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    boolean deletedById(Long id, Long parkingId);


    /**
     * 根据车牌号查询车牌和车场Id列表
     *
     * @param parkingGuidanceParamDto
     * @return
     */
    List<ParkingVehicleRecordEntity>  queryPlateNumberOrParkingLotCode(ParkingGuidanceParamDto parkingGuidanceParamDto);

    /**
     * 根据车牌号查询车牌和车场Id列表
     *
     * @param parkingGuidanceParamDto
     * @return
     */
    ParkingVehicleRecordEntity getPlateNumber(MyPlateNumberParkingGetRequestDto parkingGuidanceParamDto);


    /**
     * 根据车牌号查询车牌和车场Id列表
     *
     * @param plateNumber
     * @return
     */
    List<ParkingVehicleRecordEntity> queryPlateNumber(String plateNumber);

    /**
     * 更新在停车辆泊位
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    boolean updateParkVehicleRecord(ParkingVehicleRecordEntity parkingVehicleRecordEntity);
}
