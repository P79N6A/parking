package com.zoeeasy.cloud.pms.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.pms.domain.ParkingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.park.dto.request.MyPlateNumberParkingGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingGuidanceParamDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Repository("parkingVehicleRecordMapper")
public interface ParkingVehicleRecordMapper extends BaseMapper<ParkingVehicleRecordEntity> {

    /**
     * 找到车辆的最后一次在场记录
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingVehicleRecordEntity> findLastParkingRecord(@Param("ew") EntityWrapper<ParkingVehicleRecordEntity> entityWrapper);

    /**
     * 保存在场车辆记录
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean save(ParkingVehicleRecordEntity parkingVehicleRecordEntity);

    /**
     * 根据入车记录号查找在停车辆
     *
     * @param intoRecordNo
     * @return
     */
    @SqlParser(filter = true)
    ParkingVehicleRecordEntity findByIntoRecordNo(@Param("intoRecordNo") String intoRecordNo);

    /**
     * 根据入车记录编号更新
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    Boolean updateParkVehicle(ParkingVehicleRecordEntity parkingVehicleRecordEntity);

    /**
     * 根据停车场Id和泊位Id查询
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingVehicleRecordEntity> findByParkingIdAndParkingLotId(@Param("parkingId") Long parkingId, @Param("parkingLotId") Long parkingLotId);

    /**
     * 根据泊位删除在停车辆
     *
     * @param entityWrapper
     */
    @SqlParser(filter = true)
    void deleteByLotId(@Param("ew") Wrapper<ParkingVehicleRecordEntity> entityWrapper);

    /**
     * 根据id删除在场车辆
     *
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    boolean deletedById(@Param("id") Long id, @Param("parkingId") Long parkingId);

    /**
     * 根据车牌号查询车牌和车场Id列表
     *
     * @param plateNumber
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingVehicleRecordEntity> queryPlateNumber(@Param("plateNumber") String plateNumber);

     /**
     * 根据车牌号查询车牌和车场Id列表
     *
     * @param parkingGuidanceParamDto
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingVehicleRecordEntity> queryPlateNumberOrParkingLotCode(ParkingGuidanceParamDto parkingGuidanceParamDto);

    /**
     * 更新在停车辆泊位
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateParkVehicleRecord(ParkingVehicleRecordEntity parkingVehicleRecordEntity);

    /**
     * 根据车牌号查
     *
     * @param parkingGuidanceParamDto
     * @return
     */
    @SqlParser(filter = true)
    ParkingVehicleRecordEntity getPlateNumber(MyPlateNumberParkingGetRequestDto parkingGuidanceParamDto);
}
