package com.zoeeasy.cloud.pms.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingRecordImageEntity;
import com.zoeeasy.cloud.pms.domain.PassingVehicleRecordEntity;
import com.zoeeasy.cloud.pms.enums.ImageTypeEnum;
import com.zoeeasy.cloud.pms.enums.ParkingImageTypeEnum;
import com.zoeeasy.cloud.pms.image.ParkingImageService;
import com.zoeeasy.cloud.pms.image.dto.ParkingImageItemDto;
import com.zoeeasy.cloud.pms.image.dto.ParkingImageSaveRequestDto;
import com.zoeeasy.cloud.pms.image.dto.ParkingRecordImageRequestDto;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingRecordImageCrudService;
import com.zoeeasy.cloud.pms.service.PassingVehicleRecordCrudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车过车图像处理服务
 *
 * @author walkman
 */
@Service("parkingRecordImageService")
@Slf4j
public class ParkingImageServiceImpl implements ParkingImageService {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingRecordImageCrudService parkingRecordImageCrudService;

    @Autowired
    private PassingVehicleRecordCrudService passingVehicleRecordCrudService;

    /**
     * 保存过车图像
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultDto savePassingImages(ParkingImageSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (CollectionUtils.isNotEmpty(requestDto.getImages())) {
                List<ParkingRecordImageEntity> parkingRecordImageEntityList = new ArrayList<>();
                for (ParkingImageItemDto item : requestDto.getImages()) {
                    ParkingRecordImageEntity parkingRecordImageEntity = new ParkingRecordImageEntity();
                    parkingRecordImageEntity.setTenantId(requestDto.getTenantId());
                    parkingRecordImageEntity.setParkingId(requestDto.getParkingId());
                    parkingRecordImageEntity.setBizId(requestDto.getPassingId());
                    parkingRecordImageEntity.setBizNo(requestDto.getPassingNo());
                    parkingRecordImageEntity.setBizType(ParkingImageTypeEnum.PASSING.getValue());
                    if (item.getType() == null) {
                        parkingRecordImageEntity.setType("");
                    } else {
                        parkingRecordImageEntity.setType(item.getType());
                    }
                    parkingRecordImageEntity.setUuid(item.getUuid());
                    parkingRecordImageEntity.setUrl(item.getUrl());
                    parkingRecordImageEntityList.add(parkingRecordImageEntity);
                }
                this.parkingRecordImageCrudService.insertBatch(parkingRecordImageEntityList);
                //更新过车图像
                PassingVehicleRecordEntity passingVehicleRecordEntity = new PassingVehicleRecordEntity();
                passingVehicleRecordEntity.setUploadedDate(DateUtils.now());
                passingVehicleRecordEntity.setPassingNo(requestDto.getPassingNo());
                passingVehicleRecordEntity.setPhotoCount(parkingRecordImageEntityList.size());
                passingVehicleRecordEntity.setPhotoUploaded(Boolean.TRUE);
                passingVehicleRecordCrudService.updateByPassNo(passingVehicleRecordEntity);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存过车图像数据失败:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 添加停车图片
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto addParkingRecordImage(ParkingRecordImageRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //根据BizNo查询是否存在
            ParkingRecordImageEntity parkingRecordImageEntity = parkingRecordImageCrudService.findParkingImageByBizNo(requestDto.getBizNo());
            if (null == parkingRecordImageEntity) {
                //添加图片
                ParkingInfoEntity parkingInfoCrudServiceByCode = parkingInfoCrudService.selectByCode(requestDto.getParkingCode());
                if (StringUtils.isNotEmpty(requestDto.getFullImage())) {
                    ParkingRecordImageEntity entity = new ParkingRecordImageEntity();
                    entity.setBizType(ParkingImageTypeEnum.PARKING.getValue());
                    //停车场
                    if (null != parkingInfoCrudServiceByCode) {
                        entity.setParkingId(parkingInfoCrudServiceByCode.getId());
                    }
                    entity.setTenantId(requestDto.getTenantId());
                    entity.setUrl(requestDto.getFullImage());
                    entity.setUuid(StringUtils.getUUID());
                    entity.setBizId(requestDto.getBizId());
                    entity.setBizNo(requestDto.getBizNo());
                    entity.setType(ImageTypeEnum.FULLIMAGE.getComment());
                    parkingRecordImageCrudService.addParkingRecordImage(entity);
                }
                if (StringUtils.isNotEmpty(requestDto.getPlateImage())) {
                    ParkingRecordImageEntity recordImageEntity = new ParkingRecordImageEntity();
                    recordImageEntity.setBizType(ParkingImageTypeEnum.PARKING.getValue());
                    if (null != parkingInfoCrudServiceByCode) {
                        recordImageEntity.setParkingId(parkingInfoCrudServiceByCode.getId());
                    }
                    recordImageEntity.setTenantId(requestDto.getTenantId());
                    recordImageEntity.setUrl(requestDto.getPlateImage());
                    recordImageEntity.setUuid(StringUtils.getUUID());
                    recordImageEntity.setBizId(requestDto.getBizId());
                    recordImageEntity.setBizNo(requestDto.getBizNo());
                    recordImageEntity.setType(ImageTypeEnum.PLATEIMAGE.getComment());
                    parkingRecordImageCrudService.addParkingRecordImage(recordImageEntity);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存过车图像数据失败:{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
