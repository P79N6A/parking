package com.zoeeasy.cloud.pms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingQrcodeEntity;
import com.zoeeasy.cloud.pms.park.ParkingQrcodeService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingQrcodeGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingQrcodeResultDto;
import com.zoeeasy.cloud.pms.service.ParkingQrcodeCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @date 2018/12/21 0022
 */
@Service("parkingQrcodeService")
@Slf4j
public class ParkingQrcodeServiceImpl implements ParkingQrcodeService {

    @Autowired
    private ParkingQrcodeCrudService parkingQrcodeCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过noncestr获取parkingId
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<ParkingQrcodeResultDto>
     */
    @Override
    public ObjectResultDto<ParkingQrcodeResultDto> getParkingQrcode(ParkingQrcodeGetRequestDto requestDto) {
        ObjectResultDto<ParkingQrcodeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingQrcodeEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("noncestr", requestDto.getNoncestr());
            ParkingQrcodeEntity parkingQrcodeEntity = parkingQrcodeCrudService.findParkingQrcode(entityEntityWrapper);
            if (null != parkingQrcodeEntity) {
                ParkingQrcodeResultDto parkingQrcodeResultDto = modelMapper.map(parkingQrcodeEntity, ParkingQrcodeResultDto.class);
                objectResultDto.setData(parkingQrcodeResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场二维码数据失败", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }
}
