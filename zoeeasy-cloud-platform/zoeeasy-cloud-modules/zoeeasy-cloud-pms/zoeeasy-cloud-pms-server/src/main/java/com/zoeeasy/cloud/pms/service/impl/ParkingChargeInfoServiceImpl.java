package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.charge.ParkingChargeInfoService;
import com.zoeeasy.cloud.pms.domain.ParkingChargeInfoEntity;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingChargeInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingChargeInfoResultDto;
import com.zoeeasy.cloud.pms.service.ParkingChargeInfoCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/11/22 0022
 */
@Service("parkingChargeInfoService")
@Slf4j
public class ParkingChargeInfoServiceImpl implements ParkingChargeInfoService {

    @Autowired
    private ParkingChargeInfoCrudService parkingChargeInfoCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过停车场id获取停车场收费信息
     *
     * @param requestDto ParkingChargeInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingChargeInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingChargeInfoResultDto> getParkChargeInfoByParkingId(ParkingChargeInfoGetByParkingIdRequestDto requestDto) {
        ObjectResultDto<ParkingChargeInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingChargeInfoEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("parkingId", requestDto.getParkingId());
            entityEntityWrapper.eq("active", 1);
            ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.selectOne(entityEntityWrapper);
            if (null != parkingChargeInfoEntity) {
                ParkingChargeInfoResultDto chargeInfoResultDto = modelMapper.map(parkingChargeInfoEntity, ParkingChargeInfoResultDto.class);
                objectResultDto.setData(chargeInfoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过停车场id获取停车场收费信息失败", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
