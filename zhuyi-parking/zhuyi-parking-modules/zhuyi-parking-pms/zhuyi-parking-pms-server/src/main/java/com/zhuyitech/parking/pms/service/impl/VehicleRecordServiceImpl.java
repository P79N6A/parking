package com.zhuyitech.parking.pms.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zhuyitech.parking.pms.domain.VehicleRecord;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordAddRequestDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordGetRequestDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordUpdateRequestDto;
import com.zhuyitech.parking.pms.dto.result.VehicleRecordResultDto;
import com.zhuyitech.parking.pms.enums.PmsResultEnum;
import com.zhuyitech.parking.pms.service.VehicleRecordCrudService;
import com.zhuyitech.parking.pms.service.api.VehicleRecordService;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 平台车辆服务
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
@Service("vehicleRecordService")
@Slf4j
public class VehicleRecordServiceImpl implements VehicleRecordService {

    @Autowired
    private VehicleRecordCrudService vehicleRecordCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加平台车辆
     *
     * @param requestDto VehicleRecordAddRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto addVehicleRecord(VehicleRecordAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        if (null == requestDto) {
            return resultDto.makeResult(PmsResultEnum.VEHICLE_RECORD_REQUEST_EMPTY.getValue(), PmsResultEnum.VEHICLE_RECORD_REQUEST_EMPTY.getComment());
        }
        try {
            VehicleRecord vehicleRecord = modelMapper.map(requestDto, VehicleRecord.class);
            vehicleRecordCrudService.insert(vehicleRecord);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加平台车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改平台车辆记录
     *
     * @param requestDto VehicleRecordAddRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto updateVehicleRecord(VehicleRecordUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        if (StringUtils.isBlank(requestDto.getPlateNumber())) {
            return resultDto.makeResult(PmsResultEnum.VEHICLE_RECORD_PLATE_NUMBER_EMPTY.getValue(), PmsResultEnum.VEHICLE_RECORD_PLATE_NUMBER_EMPTY.getComment());
        }
        try {
            VehicleRecord vehicleRecord = vehicleRecordCrudService.findByPlateNumber(requestDto.getPlateNumber());
            modelMapper.map(requestDto, vehicleRecord);
            vehicleRecordCrudService.updateById(vehicleRecord);
            resultDto.success();
        } catch (Exception e) {
            log.error("添加平台车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取平台车辆
     *
     * @param requestDto VehicleRecordGetRequestDto
     * @return VehicleRecordResultDto
     */
    @Override
    public ObjectResultDto<VehicleRecordResultDto> getVehicleRecord(VehicleRecordGetRequestDto requestDto) {
        ObjectResultDto<VehicleRecordResultDto> resultDto = new ObjectResultDto<>();
        if (StringUtils.isEmpty(requestDto.getPlateNumber())) {
            return resultDto.makeResult(PmsResultEnum.VEHICLE_RECORD_PLATE_NUMBER_EMPTY.getValue(), PmsResultEnum.VEHICLE_RECORD_PLATE_NUMBER_EMPTY.getComment());
        }
        try {
            EntityWrapper<VehicleRecord> wrapper = new EntityWrapper<>();
            if (null != requestDto.getProofStatus()) {
                wrapper.eq("proofStatus", requestDto.getProofStatus());
            }
            if (StringUtils.isNotBlank(requestDto.getCarBrand())) {
                wrapper.eq("carBrand", requestDto.getCarBrand());
            }
            if (requestDto.getCarLevel() != null) {
                wrapper.eq("carLevel", requestDto.getCarLevel());
            }
            if (StringUtils.isNotBlank(requestDto.getCarStyle())) {
                wrapper.eq("carStyle", requestDto.getCarStyle());
            }
            if (StringUtils.isNotBlank(requestDto.getCarType())) {
                wrapper.eq("carType", requestDto.getCarType());
            }
            if (StringUtils.isNotBlank(requestDto.getEngineNumber())) {
                wrapper.eq("engineNumber", requestDto.getEngineNumber());
            }
            if (requestDto.getPlateColor() != null) {
                wrapper.eq("plateColor", requestDto.getPlateColor());
            }
            if (StringUtils.isNotBlank(requestDto.getPlateType())) {
                wrapper.eq("plateType", requestDto.getPlateType());
            }
            if (StringUtils.isNotBlank(requestDto.getVehicleNumber())) {
                wrapper.eq("vehicleNumber", requestDto.getVehicleNumber());
            }
            wrapper.eq("plateNumber", requestDto.getPlateNumber());
            VehicleRecord vehicleRecord = vehicleRecordCrudService.selectOne(wrapper);
            if (null != vehicleRecord) {
                VehicleRecordResultDto vehicleRecordResultDto = modelMapper.map(vehicleRecord, VehicleRecordResultDto.class);
                resultDto.setData(vehicleRecordResultDto);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取平台车辆失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
