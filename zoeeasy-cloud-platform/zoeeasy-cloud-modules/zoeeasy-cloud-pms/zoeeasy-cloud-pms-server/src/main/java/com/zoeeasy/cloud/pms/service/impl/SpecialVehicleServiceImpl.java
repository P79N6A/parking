package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.domain.PacketVehicleEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.SpecialVehicleEntity;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.enums.SpecialTypeEnum;
import com.zoeeasy.cloud.pms.service.PacketVehicleCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.SpecialVehicleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.SpecialVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.SpecialTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.SpecialVehicleInfoResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.SpecialVehicleTypeResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/10/26 0026
 */
@Service("specialVehicleService")
@Slf4j
public class SpecialVehicleServiceImpl implements SpecialVehicleService {

    @Autowired
    private SpecialVehicleCrudService specialVehicleCrudService;

    @Autowired
    private PacketVehicleCrudService packetVehicleCrudService;

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    /**
     * 通过车牌号获取特殊车辆类型(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<SpecialVehicleTypeResultDto> getSpecialTypeByPlateNo(SpecialTypeGetRequestDto requestDto) {
        ObjectResultDto<SpecialVehicleTypeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkInfoById = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
            if (parkInfoById == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }

            if (PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber())) {
                SpecialVehicleTypeResultDto specialVehicleTypeResultDto = new SpecialVehicleTypeResultDto();
                SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.findByPlateNumber(requestDto.getPlateNumber(), parkInfoById.getId());
                if (specialVehicleEntity != null) {
                    specialVehicleTypeResultDto.setSpecialType(specialVehicleEntity.getSpecialType());
                } else {
                    PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.findByPlateNoAndParkingId(requestDto.getPlateNumber(), parkInfoById.getId());
                    if (packetVehicleEntity != null) {
                        specialVehicleTypeResultDto.setSpecialType(SpecialTypeEnum.PACKET_CAR.getValue());
                    }
                    specialVehicleTypeResultDto.setSpecialType(SpecialTypeEnum.TEMPORTARY_CAR.getValue());
                }
                objectResultDto.setData(specialVehicleTypeResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过车牌号获取特殊车辆类型失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 带租户获取特殊车辆类型
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<SpecialVehicleTypeResultDto> findSpecialTypeByPlateNo(SpecialTypeGetRequestDto requestDto) {
        ObjectResultDto<SpecialVehicleTypeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkInfoById = parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (parkInfoById == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            if (PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber())) {
                SpecialVehicleTypeResultDto specialVehicleTypeResultDto = new SpecialVehicleTypeResultDto();
                Wrapper<SpecialVehicleEntity> specialVehicleEntityWrapper = new EntityWrapper<>();
                specialVehicleEntityWrapper.eq("plateNumber", requestDto.getPlateNumber());
                specialVehicleEntityWrapper.eq("parkingId", parkInfoById.getId());
                SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.selectOne(specialVehicleEntityWrapper);
                if (specialVehicleEntity != null) {
                    specialVehicleTypeResultDto.setSpecialType(specialVehicleEntity.getSpecialType());
                } else {
                    Wrapper<PacketVehicleEntity> packetVehicleEntityWrapper = new EntityWrapper<>();
                    packetVehicleEntityWrapper.eq("plateNumber", requestDto.getPlateNumber());
                    packetVehicleEntityWrapper.eq("parkingId", parkInfoById.getId());
                    PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.selectOne(packetVehicleEntityWrapper);
                    if (packetVehicleEntity != null) {
                        specialVehicleTypeResultDto.setSpecialType(SpecialTypeEnum.PACKET_CAR.getValue());
                    }
                    specialVehicleTypeResultDto.setSpecialType(SpecialTypeEnum.TEMPORTARY_CAR.getValue());
                }
                objectResultDto.setData(specialVehicleTypeResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过车牌号获取特殊车辆类型失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 无租户获取特殊车辆类型和有效时间
     *
     * @param requestDto
     */
    @Override
    public ObjectResultDto<SpecialVehicleInfoResultDto> selectSpecialTypeByPlateNo(SpecialTypeGetRequestDto requestDto) {
        ObjectResultDto<SpecialVehicleInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkInfoById = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
            if (parkInfoById == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            if (PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber())) {
                SpecialVehicleInfoResultDto specialVehicleTypeResultDto = new SpecialVehicleInfoResultDto();
                SpecialVehicleEntity specialVehicleEntity = specialVehicleCrudService.findByPlateNumberAndParkingId(requestDto.getPlateNumber(),
                        requestDto.getParkingId());
                if (specialVehicleEntity != null) {
                    specialVehicleTypeResultDto.setSpecialType(specialVehicleEntity.getSpecialType());
                    specialVehicleTypeResultDto.setValidStartTime(specialVehicleEntity.getBeginTime());
                    specialVehicleTypeResultDto.setValidEndTime(specialVehicleEntity.getEndTime());
                } else {
                    PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.findByPlateNoAndParkingId(requestDto.getPlateNumber(), requestDto.getParkingId());
                    if (packetVehicleEntity != null) {
                        specialVehicleTypeResultDto.setSpecialType(SpecialTypeEnum.PACKET_CAR.getValue());
                        specialVehicleTypeResultDto.setValidStartTime(packetVehicleEntity.getBeginDate());
                        specialVehicleTypeResultDto.setValidEndTime(packetVehicleEntity.getEndDate());
                    }
                    specialVehicleTypeResultDto.setSpecialType(SpecialTypeEnum.TEMPORTARY_CAR.getValue());
                }
                objectResultDto.setData(specialVehicleTypeResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过车牌号获取特殊车辆类型失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
