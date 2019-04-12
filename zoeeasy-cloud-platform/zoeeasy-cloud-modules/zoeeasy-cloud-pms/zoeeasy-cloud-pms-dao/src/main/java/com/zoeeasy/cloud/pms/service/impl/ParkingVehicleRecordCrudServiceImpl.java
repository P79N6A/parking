package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.pms.domain.ParkingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.mapper.ParkingVehicleRecordMapper;
import com.zoeeasy.cloud.pms.park.dto.request.MyPlateNumberParkingGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingGuidanceParamDto;
import com.zoeeasy.cloud.pms.service.ParkingVehicleRecordCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@Service("parkingVehicleRecordCrudService")
public class ParkingVehicleRecordCrudServiceImpl extends CrudServiceImpl<ParkingVehicleRecordMapper, ParkingVehicleRecordEntity> implements ParkingVehicleRecordCrudService {

    /**
     * 找到车辆的最后一次在场记录
     *
     * @param parkingId   parkingId
     * @param plateNumber plateNumber
     * @param plateColor  plateColor
     * @param carType     carType
     */
    @Override
    public ParkingVehicleRecordEntity findLastParkingRecord(Long parkingId, Long parkingLotId, String plateNumber, Integer plateColor, Integer carType, Date endTime, Long tenantId) {
        EntityWrapper<ParkingVehicleRecordEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        if (StringUtils.isNotEmpty(plateNumber)) {
            entityWrapper.eq("plateNumber", plateNumber);
        }
        if (plateColor != null) {
            entityWrapper.eq("plateColor", plateColor);
        }
        if (carType != null) {
            entityWrapper.eq("carType", carType);
        }
        if (null != parkingLotId) {
            entityWrapper.eq("parkingLotId", parkingLotId);
        }
        entityWrapper.eq("tenantId", tenantId);
        entityWrapper.lt("startTime", endTime);
        entityWrapper.orderBy("startTime", false);
        entityWrapper.last("LIMIT 1");
        List<ParkingVehicleRecordEntity> parkingVehicleRecordList = baseMapper.findLastParkingRecord(entityWrapper);
        if (CollectionUtils.isNotEmpty(parkingVehicleRecordList)) {
            return parkingVehicleRecordList.get(0);
        }
        return null;
    }

    /**
     * 保存在场车辆记录
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    @Override
    public boolean save(ParkingVehicleRecordEntity parkingVehicleRecordEntity) {
        return baseMapper.save(parkingVehicleRecordEntity);
    }

    /**
     * 根据入车记录号查找在停车辆
     *
     * @param intoRecordNo
     * @return
     */
    @Override
    public ParkingVehicleRecordEntity findByIntoRecordNo(String intoRecordNo) {
        return baseMapper.findByIntoRecordNo(intoRecordNo);
    }

    /**
     * 感觉入车记录编号更新
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    @Override
    public Boolean updateParkVehicle(ParkingVehicleRecordEntity parkingVehicleRecordEntity) {
        return baseMapper.updateParkVehicle(parkingVehicleRecordEntity);
    }

    /**
     * 根据停车场Id和泊位Id查询
     *
     * @param parkingId
     * @param parkingLotId
     * @return
     */
    @Override
    public List<ParkingVehicleRecordEntity> findByParkingIdAndParkingLotId(Long parkingId, Long parkingLotId) {
        return baseMapper.findByParkingIdAndParkingLotId(parkingId, parkingLotId);
    }

    /**
     * 根据泊位删除在停车辆
     *
     * @param entityWrapper
     */
    @Override
    public void deleteByLotId(Wrapper<ParkingVehicleRecordEntity> entityWrapper) {
        baseMapper.deleteByLotId(entityWrapper);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deletedById(Long id, Long parkingId) {
        return baseMapper.deletedById(id, parkingId);
    }

    @Override
    public List<ParkingVehicleRecordEntity> queryPlateNumberOrParkingLotCode(ParkingGuidanceParamDto parkingGuidanceParamDto) {
        return baseMapper.queryPlateNumberOrParkingLotCode(parkingGuidanceParamDto);
    }

    @Override
    public ParkingVehicleRecordEntity getPlateNumber(MyPlateNumberParkingGetRequestDto parkingGuidanceParamDto) {
        return baseMapper.getPlateNumber(parkingGuidanceParamDto);
    }

    /**
     * 根据车牌号查询车牌和车场Id列表
     *
     * @param plateNumber
     * @return
     */
    @Override
    public List<ParkingVehicleRecordEntity> queryPlateNumber(String plateNumber) {
        return baseMapper.queryPlateNumber(plateNumber);
    }

    /**
     * 更新在停车辆泊位
     *
     * @param parkingVehicleRecordEntity
     * @return
     */
    @Override
    public boolean updateParkVehicleRecord(ParkingVehicleRecordEntity parkingVehicleRecordEntity) {
        return baseMapper.updateParkVehicleRecord(parkingVehicleRecordEntity);
    }
}
