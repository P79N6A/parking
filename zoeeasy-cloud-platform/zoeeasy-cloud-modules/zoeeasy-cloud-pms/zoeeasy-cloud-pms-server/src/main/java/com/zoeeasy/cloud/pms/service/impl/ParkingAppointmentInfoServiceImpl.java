package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.appoint.ParkingAppointmentInfoService;
import com.zoeeasy.cloud.pms.domain.ParkingAppointInfoEntity;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingAppointInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingAppointInfoResultDto;
import com.zoeeasy.cloud.pms.service.ParkingAppointInfoCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/11/22 0022
 */
@Service("parkingAppointmentInfoService")
@Slf4j
public class ParkingAppointmentInfoServiceImpl implements ParkingAppointmentInfoService {

    @Autowired
    private ParkingAppointInfoCrudService parkingAppointInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 根据parkingId获取停车场预约信息
     *
     * @param requestDto ParkingAppointInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingAppointInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingAppointInfoResultDto> getAppointmentInfoByParkingId(ParkingAppointInfoGetByParkingIdRequestDto requestDto) {
        ObjectResultDto<ParkingAppointInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingAppointInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.selectOne(entityWrapper);
            if (parkingAppointInfoEntity == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_APPOINT_INFO_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_APPOINT_INFO_NOT_FOUND.getComment());
            }
            ParkingAppointInfoResultDto parkingAppointInfoResultDto = modelMapper.map(parkingAppointInfoEntity, ParkingAppointInfoResultDto.class);
            objectResultDto.setData(parkingAppointInfoResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场预约信息失败" + e.getMessage());
            return objectResultDto.makeResult(PmsResultEnum.GET_PARKING_APPOINT_INFO_ERR.getValue(), PmsResultEnum.GET_PARKING_APPOINT_INFO_ERR.getComment());
        }
        return objectResultDto;
    }
}
