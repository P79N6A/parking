package com.zoeeasy.cloud.pms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.LocationUtil;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.*;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.*;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingAppointInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.*;
import com.zoeeasy.cloud.pms.platform.validator.*;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.tool.region.RegionService;
import com.zoeeasy.cloud.tool.region.dto.RegionAddressGetRequestDto;
import com.zoeeasy.cloud.tool.region.dto.RegionAddressResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 平台停车场服务
 *
 * @author walkman
 */
@Service("platformParkingInfoService")
@Slf4j
public class PlatformParkingServiceImpl implements PlatformParkingInfoService {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingDetailInfoCrudService parkingDetailInfoCrudService;

    @Autowired
    private ParkingChargeInfoCrudService parkingChargeInfoCrudService;

    @Autowired
    private ParkingAppointInfoCrudService parkingAppointInfoCrudService;

    @Autowired
    private ParkingPictureCrudService parkingPictureCrudService;

    @Autowired
    private ParkingCurrentInfoCrudService parkingCurrentInfoCrudService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ParkingVehicleRecordCrudService parkingVehicleRecordCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 通过海康停车场Id获取停车场信息
     *
     * @param requestDto ParkingInfoGetByHikParkIdRequestDto
     * @return ObjectResultDto<ParkingInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingInfoResultDto> getParkInfoByHikParkId(ParkingInfoGetByHikParkIdRequestDto requestDto) {
        ObjectResultDto<ParkingInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByHikParkId(requestDto.getHikParkId());
            if (null != parkingInfoEntity) {
                ParkingInfoResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingInfoResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过海康停车场Id获取停车场信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 通过客户端编号获取停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingInfoResultDto> getParkInfoByCode(ParkingInfoGetByCodeRequestDto requestDto) {
        ObjectResultDto<ParkingInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectByCode(requestDto.getCode());
            if (null != parkingInfoEntity) {
                ParkingInfoResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingInfoResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过客户端编号获取停车场信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 处理过车数据----通过id获取停车场
     *
     * @param requestDto ParkingInfoGetRequestDto
     * @return ObjectResultDto<ParkingInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingInfoResultDto> getParkInfoById(ParkingInfoGetRequestDto requestDto) {
        ObjectResultDto<ParkingInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getParkingId());
            if (null != requestDto.getStatus()) {
                entity.eq("status", requestDto.getStatus());
            }
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (null != parkingInfoEntity) {
                ParkingInfoResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingInfoResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过id获取停车场信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 通过客户端编号获取停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingInfoResultDto> getParkInfoByLocalCode(ParkingInfoGetByLocalCodeRequestDto requestDto) {
        ObjectResultDto<ParkingInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectByLocalCode(requestDto.getLocalCode());
            if (null != parkingInfoEntity) {
                ParkingInfoResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingInfoResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过客户端编号获取停车场信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 可预约停车场数量
     */
    @Override
    public ObjectResultDto<AppointmentParkingCountResultDto> getAppointmentParkingCount(AppointmentParkingCountRequestDto requestDto) {
        ObjectResultDto<AppointmentParkingCountResultDto> objectResultDto = new ObjectResultDto<>();
        AppointmentParkingCountResultDto appointmentParkingCountResultDto = new AppointmentParkingCountResultDto();
        try {
            EntityWrapper<ParkingCurrentInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.gt("lotAppointmentAvailable", 0);
            List<ParkingCurrentInfoEntity> appointParkList = parkingCurrentInfoCrudService.selectAppointParkList(entityWrapper);
            List<ParkingInfoEntity> parkingInfoList = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(appointParkList)) {
                for (ParkingCurrentInfoEntity obj : appointParkList) {
                    EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
                    entity.eq("id", obj.getParkingId());
                    entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
                    ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
                    if (null == parkingInfoEntity) {
                        continue;
                    }
                    if (ParkingStatusEnum.ON_LINE.getValue().equals(parkingInfoEntity.getStatus())) {
                        parkingInfoList.add(parkingInfoEntity);
                    }
                }
            }
            appointmentParkingCountResultDto.setCount(parkingInfoList.size());
            objectResultDto.setData(appointmentParkingCountResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取可预约停车场数量失败" + e.getMessage());
            objectResultDto.makeResult(PmsResultEnum.GET_APPOINTPARKING_COUNT.getValue(), PmsResultEnum.GET_APPOINTPARKING_COUNT.getComment());
        }
        return objectResultDto;
    }

    /**
     * 获取停车场列表
     *
     * @param requestDto AppointmentParkingListGetRequestDto
     * @return ListResultDto<AppointParkingInfoResultDto>
     */
    @Override
    public ListResultDto<AppointParkingInfoResultDto> selectAppointmentParkingList(AppointmentParkingListGetRequestDto requestDto) {
        ListResultDto<AppointParkingInfoResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("supportAppointment", Boolean.TRUE);
            entityWrapper.eq("status", ParkingStatusEnum.ON_LINE.getValue());
            List<ParkingInfoEntity> parkingInfoList = parkingInfoCrudService.selectListByPosition(entityWrapper, requestDto.getMinLongitude(),
                    requestDto.getMaxLongitude(),
                    requestDto.getMinLatitude(),
                    requestDto.getMaxLatitude());
            if (null != parkingInfoList && !parkingInfoList.isEmpty()) {
                List<AppointParkingInfoResultDto> list = modelMapper.map(parkingInfoList, new TypeToken<List<AppointParkingInfoResultDto>>() {
                }.getType());
                listResultDto.setItems(list);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车场地址
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<String> getParkingAddressByParkId(ParkingGetRequestDto requestDto) {
        ObjectResultDto<String> objectResultDto = new ObjectResultDto<>();
        try {
            //停车场地址
            String address = parkingDetailInfoCrudService.selectParkingAddress(requestDto.getId());
            objectResultDto.setData(address);
        } catch (Exception e) {
            log.error("获取停车场地址失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取停车场
     *
     * @param requestDto ParkingGetRequestDto
     * @return ObjectResultDto<ParkingResultDto>
     */
    @Override
    public ObjectResultDto<ParkingResultDto> getParkingApp(ParkingGetRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getId());
            entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (parkingInfoEntity != null) {
                ParkingResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingResultDto.class);
                //停车场详细信息
                ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingIdTenantless(requestDto.getId());
                parkingResultDto.setAddress(parkingDetailInfoEntity.getAddress());
                parkingResultDto.setProvinceCode(parkingDetailInfoEntity.getProvinceCode());
                parkingResultDto.setCityCode(parkingDetailInfoEntity.getCityCode());
                parkingResultDto.setCountyCode(parkingDetailInfoEntity.getCountyCode());
                //停车场收费信息
                ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(requestDto.getId());
                parkingResultDto.setChargeDescription(parkingChargeInfoEntity.getChargeDescription());
                parkingResultDto.setChargeDescription(parkingChargeInfoEntity.getChargeDescription());
                //停车场预约信息
                ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.selectByParkingId(requestDto.getId());
                parkingResultDto.setLotAppointmentTotal(parkingAppointInfoEntity.getLotAppointmentTotal());
                parkingResultDto.setAppointmentRule(parkingAppointInfoEntity.getAppointmentRule());
                //停车场当前状态
                ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getId());
                parkingResultDto.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable());
                parkingResultDto.setLotAppointmentAvailable(parkingCurrentInfoEntity.getLotAppointmentAvailable());
                //停车场图片
                List<ParkingPictureEntity> pictures = parkingPictureCrudService.findParkingPictureList(requestDto.getId());
                if (pictures != null && !pictures.isEmpty()) {
                    List<String> imageUrls = new ArrayList<>();
                    for (ParkingPictureEntity parkingPictureEntity : pictures) {
                        imageUrls.add(parkingPictureEntity.getUrl());
                    }
                    parkingResultDto.setImages(imageUrls);
                }
                objectResultDto.setData(parkingResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取停车场失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取停车场图像
     *
     * @param requestDto ParkingImageGetRequestDto
     * @return ListResultDto<ParkingImageViewResultDto>
     */
    @Override
    public ListResultDto<ParkingImageViewResultDto> getParkingImageList(ParkingImageGetRequestDto requestDto) {
        ListResultDto<ParkingImageViewResultDto> resultDto = new ListResultDto<>();
        try {
            //获取停车场图像
            List<ParkingPictureEntity> pictures = parkingPictureCrudService.findParkingPictureList(requestDto.getParkingId());

            if (pictures != null && !pictures.isEmpty()) {

                resultDto.setItems(pictures.stream()
                        .filter(item ->
                                StringUtils.isNotEmpty(item.getUrl())
                        ).map(
                                item ->
                                {
                                    ParkingImageViewResultDto parkingRecordImageResultDto = new ParkingImageViewResultDto();
                                    parkingRecordImageResultDto.setUrl(item.getUrl());
                                    return parkingRecordImageResultDto;
                                }
                        ).collect(Collectors.toList()));
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取停车场图片失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取停车场地址
     */
    @Override
    public ObjectResultDto<ParkingLocationResultDto> getParkingAddress(ParkingLocationGetRequestDto requestDto) {
        ObjectResultDto<ParkingLocationResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
            parkingGetRequestDto.setId(requestDto.getParkingId());
            ObjectResultDto<ParkingResultDto> parkingObject = this.getParkingApp(parkingGetRequestDto);
            if (parkingObject.isFailed() || null == parkingObject.getData()) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }

            ParkingResultDto parkingInfo = parkingObject.getData();
            ParkingLocationResultDto resultDto = new ParkingLocationResultDto();
            resultDto.setAddress(parkingInfo.getAddress());
            resultDto.setLatitude(Double.valueOf(parkingInfo.getLatitude()));
            resultDto.setLongitude(Double.valueOf(parkingInfo.getLongitude()));
            objectResultDto.setData(resultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场地址失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据坐标及半径获取停车场
     *
     * @param requestDto ParkingInfoAroundGetRequestDto
     * @return ListResultDto<ParkingInfoAroundResultDto>
     */
    @Override
    public ListResultDto<ParkingInfoAroundResultDto> getAroundParkingInfoList(ParkingInfoAroundGetRequestDto requestDto) {

        //附近停车场列表
        ListResultDto<ParkingInfoAroundResultDto> closestParkingInfoList = new ListResultDto<>();
        if (requestDto.getLongitude() == null || requestDto.getLatitude() == null || requestDto.getMaxDistance() == null) {
            return closestParkingInfoList.success();
        }
        //获取距离范围
        Double[] aroundPosition = LocationUtil.getAroundPosition(requestDto.getLongitude(), requestDto.getLatitude(),
                requestDto.getMaxDistance());
        if (aroundPosition != null) {

            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            List<ParkingInfoAroundEntity> parkingInfoList = parkingInfoCrudService.selectAroundListByPosition(entityWrapper,
                    aroundPosition[0],
                    aroundPosition[1],
                    aroundPosition[2],
                    aroundPosition[3]);

            if (CollectionUtils.isNotEmpty(parkingInfoList)) {
                //循环取附近的停车场
                List<ParkingInfoAroundResultDto> parkingInfoAroundResultDtos = new ArrayList<>();
                for (ParkingInfoAroundEntity parkingInfo : parkingInfoList) {
                    if (parkingInfo.getLatitude() == null || parkingInfo.getLongitude() == null) {
                        //坐标为空
                        continue;
                    }
                    //距离
                    Double distance = LocationUtil.getDistanceFromTwoPoints(requestDto.getLatitude(), requestDto.getLongitude(),
                            parkingInfo.getLatitude(), parkingInfo.getLongitude());

                    //距离小于最大距离
                    if (distance != null && distance.compareTo(requestDto.getMaxDistance()) <= 0) {
                        ParkingInfoAroundResultDto resultDto = createParkingInfoAroundResultDto(parkingInfo);
                        resultDto.setDistance(distance);
                        parkingInfoAroundResultDtos.add(resultDto);
                    }
                }
                closestParkingInfoList.setItems(parkingInfoAroundResultDtos);
            }
        }
        return closestParkingInfoList.success();
    }

    /**
     * 通过停车场id获取停车场收费信息
     *
     * @param requestDto ParkingChargeInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingChargeInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingChargeInfoResultDto> getParkChargeInfoByParkingId(@FluentValid(ParkingChargeInfoGetByParkingIdRequestDtoValidator.class) ParkingChargeInfoGetByParkingIdRequestDto requestDto) {
        ObjectResultDto<ParkingChargeInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(requestDto.getParkingId());
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

    /**
     * 通过id获取停车场收费信息
     *
     * @param requestDto ParkingChargeInfoGetByIdRequestDto
     * @return ObjectResultDto<ParkingChargeInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingChargeInfoResultDto> getParkChargeInfoById(ParkingChargeInfoGetByIdRequestDto requestDto) {
        ObjectResultDto<ParkingChargeInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findById(requestDto.getId());
            if (null != parkingChargeInfoEntity) {
                ParkingChargeInfoResultDto chargeInfoResultDto = modelMapper.map(parkingChargeInfoEntity, ParkingChargeInfoResultDto.class);
                objectResultDto.setData(chargeInfoResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("通过停车场收费信息id获取停车场收费信息失败", e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据parkingId获取停车场预约信息
     *
     * @param requestDto ParkingAppointInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingAppointInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingAppointInfoResultDto> getParkingAppointmentInfoByParkingId(ParkingAppointInfoGetByParkingIdRequestDto requestDto) {
        ObjectResultDto<ParkingAppointInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.findByParkingId(requestDto.getParkingId());
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
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.selectByParkingId(requestDto.getParkingId());
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

    /**
     * 根据parkingId获取停车场当前信息
     *
     * @param requestDto ParkingCurrentInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingCurrentInfoGetByParkingIdResultDto>
     */
    @Override
    public ObjectResultDto<ParkingCurrentInfoGetByParkingIdResultDto> getParkingCurrentInfoByParkingId(@FluentValid(ParkingCurrentInfoGetByParkingIdRequestDtoValidator.class)
                                                                                                               ParkingCurrentInfoGetByParkingIdRequestDto requestDto) {
        ObjectResultDto<ParkingCurrentInfoGetByParkingIdResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getParkingId());
            if (parkingCurrentInfoEntity == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            ParkingCurrentInfoGetByParkingIdResultDto parkingCurrentInfoGetByParkingIdResultDto = modelMapper.map(parkingCurrentInfoEntity, ParkingCurrentInfoGetByParkingIdResultDto.class);
            objectResultDto.setData(parkingCurrentInfoGetByParkingIdResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场当前信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据泊位编号获取泊位信息
     *
     * @param requestDto ParkingLotInfoGetByCodeRequestDto
     * @return ObjectResultDto<ParkingLotResultDto>
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLotByCode(ParkingLotInfoGetByCodeRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingLotInfoEntity parkingInfoEntity = parkingLotInfoCrudService.findByCode(requestDto.getParkingLotCode());
            if (parkingInfoEntity != null) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(parkingInfoEntity, ParkingLotResultDto.class);
                objectResultDto.setData(parkingLotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("根据泊位编号获取泊位信息失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 处理过车记录时获车位信息
     *
     * @param requestDto ParkingLotInfoGetForPassVehicleRequestDto
     * @return ObjectResultDto<ParkingLotResultDto>
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLotForPassVehicle(ParkingLotInfoGetForPassVehicleRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("tenantId", requestDto.getTenantId());
            if (org.apache.commons.lang.StringUtils.isNotEmpty(requestDto.getHikParkLotId())) {
                entityWrapper.eq("hikParkingLotId", requestDto.getHikParkLotId());
            }
            if (null != requestDto.getParkingId() && org.apache.commons.lang.StringUtils.isNotEmpty(requestDto.getBerthNumber())) {
                entityWrapper.eq("parkingId", requestDto.getParkingId());
                entityWrapper.eq("hikBerthNumber", requestDto.getBerthNumber());
            }
            ParkingLotInfoEntity exitParkingLotInfo = parkingLotInfoCrudService.findForPassVehicle(entityWrapper);
            if (null != exitParkingLotInfo) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(exitParkingLotInfo, ParkingLotResultDto.class);
                objectResultDto.setData(parkingLotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("获车位信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 客户端获取泊位信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLotByLocal(ParkingLotInfoGetByLocalRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            Wrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("tenantId", requestDto.getTenantId());
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            if (StringUtils.isNotEmpty(requestDto.getLocalCode())) {
                entityWrapper.eq("localCode", requestDto.getLocalCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getNumber())) {
                entityWrapper.eq("number", requestDto.getNumber());
            }
            ParkingLotInfoEntity exitParkingLotInfo = parkingLotInfoCrudService.findForPassVehicle(entityWrapper);
            if (null != exitParkingLotInfo) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(exitParkingLotInfo, ParkingLotResultDto.class);
                objectResultDto.setData(parkingLotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("获车位信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 处理过车记录----根据停车场id和泊位id获取泊位信息
     *
     * @param requestDto ParkingLotInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingLotResultDto>
     */
    @Override
    public ObjectResultDto<ParkingLotResultDto> getParkingLotByParkingId(@FluentValid(ParkingLotInfoGetByParkingIdRequestDtoValidator.class) ParkingLotInfoGetByParkingIdRequestDto requestDto) {
        ObjectResultDto<ParkingLotResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingLotInfoEntity exitParkingLotInfo = parkingLotInfoCrudService.findByParkingId(requestDto.getParkingId(), requestDto.getParkingLotId(), requestDto.getTenantId());
            if (null != exitParkingLotInfo) {
                ParkingLotResultDto parkingLotResultDto = modelMapper.map(exitParkingLotInfo, ParkingLotResultDto.class);
                objectResultDto.setData(parkingLotResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("获车位信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 通过停车场id和泊位Id获取泊位状态
     *
     * @param requestDto ParkingLotStatusGetRequestDto
     * @return ObjectResultDto<ParkingLotStatusResultDto>
     */
    @Override
    public ObjectResultDto<ParkingLotStatusResultDto> findByParkingIdAndParkingLotId(@FluentValid(ParkingLotStatusGetRequestDtoValidator.class) ParkingLotStatusGetRequestDto requestDto) {
        ObjectResultDto<ParkingLotStatusResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingLotStatusEntity lotStatusEntity = parkingLotStatusCrudService.findByParkingIdAndParkingLotId(requestDto.getParkingId(), requestDto.getParkingLotId(), requestDto.getTenantId());
            if (null == lotStatusEntity) {
                return resultDto.failed();
            }
            ParkingLotStatusResultDto parkingLotStatusResultDto = modelMapper.map(lotStatusEntity, ParkingLotStatusResultDto.class);
            resultDto.setData(parkingLotStatusResultDto);
            resultDto.success();
        } catch (Exception e) {
            resultDto.failed();
            log.error("通过停车场id和泊位Id获取泊位状态失败" + e.getMessage());
        }
        return resultDto;
    }

    private ParkingInfoAroundResultDto createParkingInfoAroundResultDto(ParkingInfoAroundEntity parkingInfoAroundEntity) {
        return modelMapper.map(parkingInfoAroundEntity, ParkingInfoAroundResultDto.class);
    }

    /**
     * 变更停车场可用车位数及变更车位状态空闲
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto increaseParkingLotAvailable(ParkingLotIncreaseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getParkingId());
            entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (parkingInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getParkingId());
            if (parkingCurrentInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            //递增可用车位数
            if (parkingCurrentInfoEntity.getLotAvailable() < parkingInfoEntity.getLotTotal()) {
                ParkingCurrentInfoEntity parkingCurrentInfoUpdate = new ParkingCurrentInfoEntity();
                parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
                parkingCurrentInfoUpdate.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable() + 1);
                parkingCurrentInfoUpdate.setLastModificationTime(DateUtils.now());
                parkingCurrentInfoCrudService.updateCurrentInfoById(parkingCurrentInfoUpdate);
            }
            //如果泊位不空，更新泊位为空闲状态
            if (requestDto.getParkingLotId() != null) {
                ParkingLotStatusEntity lotStatusEntity = parkingLotStatusCrudService.findByParkingIdAndParkingLotId(requestDto.getParkingId(),
                        requestDto.getParkingLotId(), requestDto.getTenantId());
                if (lotStatusEntity == null) {
                    return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                            PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment()
                    );
                }
                parkingLotStatusCrudService.updateParkingLotStatus(lotStatusEntity.getId(), ParkingLotStatusEnum.FREE.getValue()
                        , requestDto.getFreeTime());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("变更停车场可用车位数及变更车位状态空闲失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 变更停车场可用车位数及变更车位状态占用
     *
     * @param requestDto requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto decreaseParkingLotAvailable(@FluentValid(ParkingLotDecreaseValidator.class) ParkingLotDecreaseRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getParkingId());
            entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (parkingInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getParkingId());
            if (parkingCurrentInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            //递减可用车位数
            if (parkingCurrentInfoEntity.getLotAvailable() >= 1) {
                ParkingCurrentInfoEntity parkingCurrentInfoUpdate = new ParkingCurrentInfoEntity();
                parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
                parkingCurrentInfoUpdate.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable() - 1);
                parkingCurrentInfoUpdate.setLastModificationTime(DateUtils.now());
                parkingCurrentInfoCrudService.updateCurrentInfoById(parkingCurrentInfoUpdate);
            }
            //如果泊位不空，更新泊位为空闲状态
            if (requestDto.getParkingLotId() != null) {
                ParkingLotStatusEntity lotStatusEntity = parkingLotStatusCrudService.findByParkingIdAndParkingLotId(requestDto.getParkingId(),
                        requestDto.getParkingLotId(), requestDto.getTenantId());
                if (lotStatusEntity == null) {
                    return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                            PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment()
                    );
                }
                parkingLotStatusCrudService.updateParkingLotStatus(lotStatusEntity.getId(),
                        ParkingLotStatusEnum.USED.getValue(), requestDto.getOccupyTime());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("变更停车场可用车位数及变更车位状态占用失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据是否预约更新可预约车位
     *
     * @param requestDto
     */
    @Override
    public ResultDto updateLotAppointAvailable(ParkingLotAppointUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getParkingId());
            entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (parkingInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getParkingId());
            if (parkingCurrentInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment()
                );
            }
            if (requestDto.getIncrease()) {
                //递增可预约车位
                if (parkingCurrentInfoEntity.getLotAvailable() < parkingInfoEntity.getLotTotal() && parkingCurrentInfoEntity.getLotAppointmentAvailable() <= parkingCurrentInfoEntity.getLotAvailable()) {
                    ParkingCurrentInfoEntity parkingCurrentInfoUpdate = new ParkingCurrentInfoEntity();
                    parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
                    parkingCurrentInfoUpdate.setLotAppointmentAvailable(parkingCurrentInfoEntity.getLotAppointmentAvailable() + 1);
                    parkingCurrentInfoUpdate.setLastModificationTime(DateUtils.now());
                    parkingCurrentInfoCrudService.updateCurrentInfoById(parkingCurrentInfoUpdate);
                }
            } else {
                //递减可预约车位
                if (parkingCurrentInfoEntity.getLotAppointmentAvailable() >= 1) {
                    ParkingCurrentInfoEntity parkingCurrentInfoUpdate = new ParkingCurrentInfoEntity();
                    parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
                    parkingCurrentInfoUpdate.setLotAppointmentAvailable(parkingCurrentInfoEntity.getLotAppointmentAvailable() - 1);
                    parkingCurrentInfoUpdate.setLastModificationTime(DateUtils.now());
                    parkingCurrentInfoCrudService.updateCurrentInfoById(parkingCurrentInfoUpdate);
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("根据是否预约更新可预约车位失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页查询根据泊位编号查询停车信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingInfoByParkingLotCodeQueryPageResultDto> getParkingInfoByParkingLotCode(ParkingInfoByParkingLotCodeQueryPageRequestDto requestDto) {
        PagedResultDto<ParkingInfoByParkingLotCodeQueryPageResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (currentParking == null) {
                return pagedResultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", currentParking.getParkingId());
            if (StringUtils.isNotEmpty(requestDto.getNumber())) {
                entityWrapper.eq("number", requestDto.getNumber());
            }
            Page<ParkingLotInfoEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingLotInfoEntity> parkingLotInfoEntityPage = parkingLotInfoCrudService.selectPage(page, entityWrapper);
            List<ParkingInfoByParkingLotCodeQueryPageResultDto> parkingInfoByParkingLotCodeQueryPageResultDtos = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(parkingLotInfoEntityPage.getRecords())) {
                for (ParkingLotInfoEntity parkingLotInfoEntity : parkingLotInfoEntityPage.getRecords()) {
                    ParkingInfoByParkingLotCodeQueryPageResultDto parkingInfoByParkingLotCodeQueryPageResultDto = new ParkingInfoByParkingLotCodeQueryPageResultDto();
                    EntityWrapper<ParkingLotStatusEntity> statusEntityEntityWrapper = new EntityWrapper<>();
                    statusEntityEntityWrapper.eq("parkingLotId", parkingLotInfoEntity.getId());
                    //查询在停车辆信息
                    EntityWrapper<ParkingVehicleRecordEntity> parkingVehicleWrapper = new EntityWrapper<>();
                    parkingVehicleWrapper.eq("parkingId", currentParking.getParkingId());
                    parkingVehicleWrapper.eq("parkingLotId", parkingLotInfoEntity.getId());
                    List<ParkingVehicleRecordEntity> byParkingIdAndParkingLotId = parkingVehicleRecordCrudService.selectList(parkingVehicleWrapper);
                    if (CollectionUtils.isNotEmpty(byParkingIdAndParkingLotId)) {
                        for (ParkingVehicleRecordEntity parkingVehicleRecordEntity : byParkingIdAndParkingLotId) {
                            ParkingInfoByParkingLotCodeQueryPageResultDto parkingLotCodeQueryPageResultDto = new ParkingInfoByParkingLotCodeQueryPageResultDto();
                            parkingLotCodeQueryPageResultDto.setPlateNumber(parkingVehicleRecordEntity.getPlateNumber());
                            //根据车牌号判断车位占用状态
                            parkingLotCodeQueryPageResultDto.setStatus(ParkingConstant.PARKING_IN_PARK);
                            //在停车辆Id
                            parkingLotCodeQueryPageResultDto.setId(parkingVehicleRecordEntity.getId());
                            parkingLotCodeQueryPageResultDto.setNumber(parkingLotInfoEntity.getNumber());
                            parkingLotCodeQueryPageResultDto.setCode(parkingLotInfoEntity.getCode());
                            //入场时间
                            parkingLotCodeQueryPageResultDto.setStartTime(parkingVehicleRecordEntity.getStartTime());
                            //停车时长
                            Date date = new Date();
                            parkingLotCodeQueryPageResultDto.setParkPeriodTime((DateTimeUtils.getSecondDiff(parkingVehicleRecordEntity.getStartTime(), date)) / 60);
                            parkingLotCodeQueryPageResultDto.setCarType(parkingVehicleRecordEntity.getCarType());
                            parkingLotCodeQueryPageResultDto.setIntoRecordNo(parkingVehicleRecordEntity.getIntoRecordNo());
                            parkingLotCodeQueryPageResultDto.setPlateColor(parkingVehicleRecordEntity.getPlateColor());
                            parkingLotCodeQueryPageResultDto.setParkingId(parkingVehicleRecordEntity.getParkingId());
                            parkingLotCodeQueryPageResultDto.setId(parkingVehicleRecordEntity.getId());
                            parkingLotCodeQueryPageResultDto.setParkingLotId(parkingVehicleRecordEntity.getParkingLotId());
                            parkingInfoByParkingLotCodeQueryPageResultDtos.add(parkingLotCodeQueryPageResultDto);
                        }
                    } else {
                        parkingInfoByParkingLotCodeQueryPageResultDto.setCode(parkingLotInfoEntity.getCode());
                        parkingInfoByParkingLotCodeQueryPageResultDto.setNumber(parkingLotInfoEntity.getNumber());
                        parkingInfoByParkingLotCodeQueryPageResultDto.setParkingLotId(parkingLotInfoEntity.getId());
                        parkingInfoByParkingLotCodeQueryPageResultDto.setParkingId(parkingLotInfoEntity.getParkingId());
                        parkingInfoByParkingLotCodeQueryPageResultDto.setStatus(ParkingConstant.PARKING_LEISURE);
                        parkingInfoByParkingLotCodeQueryPageResultDtos.add(parkingInfoByParkingLotCodeQueryPageResultDto);
                    }
                }
                pagedResultDto.setPageNo(parkingLotInfoEntityPage.getCurrent());
                pagedResultDto.setPageSize(parkingLotInfoEntityPage.getSize());
                pagedResultDto.setTotalCount(parkingLotInfoEntityPage.getTotal());
                pagedResultDto.setItems(parkingInfoByParkingLotCodeQueryPageResultDtos);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询根据泊位编号查询停车信息失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 根据停车场Id获取泊位列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingLotByParkingIdResultDto> getParkingLotByByParkingIdList(ParkingLotByParkingIdListGetRequestDto requestDto) {
        ListResultDto<ParkingLotByParkingIdResultDto> listResultDto = new ListResultDto<>();
        try {
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (currentParking == null) {
                return listResultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            EntityWrapper<ParkingLotInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", currentParking.getParkingId());
            List<ParkingLotInfoEntity> codeByParkingId = parkingLotInfoCrudService.selectList(entityWrapper);
            if (CollectionUtils.isNotEmpty(codeByParkingId)) {
                List<ParkingLotByParkingIdResultDto> parkingLotByParkingIdResultDtos = new ArrayList<>();
                for (ParkingLotInfoEntity parkingLotInfoEntity : codeByParkingId) {
                    ParkingLotByParkingIdResultDto parkingLotByParkingIdResultDto = new ParkingLotByParkingIdResultDto();
                    parkingLotByParkingIdResultDto.setNumber(parkingLotInfoEntity.getNumber());
                    parkingLotByParkingIdResultDtos.add(parkingLotByParkingIdResultDto);
                }
                listResultDto.setItems(parkingLotByParkingIdResultDtos);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("根据停车场Id获取泊位列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车场（无租户）
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingResultDto> getParkingInfoById(ParkingGetRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("id", requestDto.getId());
//            entity.eq("status", ParkingStatusEnum.ON_LINE.getValue());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (parkingInfoEntity != null) {
                ParkingResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ObjectResultDto<ParkingResultDto> getParkingInfoByCode(ParkingGetByCodeRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
            entity.eq("code", requestDto.getCode());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entity);
            if (parkingInfoEntity != null) {
                ParkingResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取停车场可用车位数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingResultDto> getParkingLotAvailable(ParkingByParkingIdGetRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            EntityWrapper<ParkingCurrentInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            ParkingCurrentInfoEntity currentInfoEntity = parkingCurrentInfoCrudService.selectOne(entityWrapper);
            if (currentInfoEntity != null) {
                ParkingResultDto parkingResultDto = modelMapper.map(currentInfoEntity, ParkingResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场可用车位数" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 修改可用车位数
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateParkingLotAvailable(ParkingGetLotAvailableRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {

            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (currentParking == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            ParkingInfoEntity parkInfoById = parkingInfoCrudService.selectById(currentParking.getParkingId());
            if (null == parkInfoById) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            EntityWrapper<ParkingCurrentInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("parkingId", currentParking.getParkingId());
            ParkingCurrentInfoEntity currentInfoEntity = parkingCurrentInfoCrudService.selectOne(entityWrapper);
            if (currentInfoEntity == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(), PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = new ParkingCurrentInfoEntity();
            //查询车位总数
            if (null != requestDto.getLotAvailable() && requestDto.getLotAvailable() > parkInfoById.getLotTotal()) {
                return resultDto.makeResult(PmsResultEnum.PARKING_LOT_AVAILABLE_MORE_THAN_LOT_TOTAL.getValue(),
                        PmsResultEnum.PARKING_LOT_AVAILABLE_MORE_THAN_LOT_TOTAL.getComment());
            }
            parkingCurrentInfoEntity.setLotAvailable(requestDto.getLotAvailable());
            if (null == requestDto.getLotAvailable() || requestDto.getLotAvailable() < 0) {
                parkingCurrentInfoEntity.setLotAvailable(0);
            }
            parkingCurrentInfoCrudService.update(parkingCurrentInfoEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改可用车位数失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    public String getParkingLotStatus(ParkingLotStatusEntity parkingLotStatusEntity) {
        String comment = null;
        ParkingLotStatusEnum parkingLotStatusEnum = ParkingLotStatusEnum.parse(parkingLotStatusEntity.getStatus());
        if (parkingLotStatusEnum != null) {
            comment = parkingLotStatusEnum.getComment();
        }
        return comment;
    }

    /**
     * 根据Id获取停车场预约信息
     *
     * @param requestDto ParkingAppointInfoGetByIdRequestDto
     * @return ObjectResultDto<ParkingAppointInfoResultDto>
     */
    @Override
    public ObjectResultDto<ParkingAppointInfoResultDto> getAppointmentInfoById(ParkingAppointInfoGetByIdRequestDto requestDto) {
        ObjectResultDto<ParkingAppointInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.findById(requestDto.getId());
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

    /**
     * 查询停车场当前状态
     *
     * @param resultDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingCurrentInfoResultDto> selectCurrentInfoByParkingId(ParkingCurrentGetByIdRequestDto resultDto) {
        ObjectResultDto<ParkingCurrentInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(resultDto.getParkingId());
            if (null != parkingCurrentInfoEntity) {
                ParkingCurrentInfoResultDto currentGetByIdResultDto = modelMapper.map(parkingCurrentInfoEntity, ParkingCurrentInfoResultDto.class);
                objectResultDto.setData(currentGetByIdResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("查询车场当前状态失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取停车场地址
     */
    @Override
    public ObjectResultDto<ParkingLocationResultDto> getParkingAddressForCloud(ParkingLocationGetRequestDto requestDto) {
        ObjectResultDto<ParkingLocationResultDto> objectResultDto = new ObjectResultDto<>();
        try {

            ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingId(requestDto.getParkingId());
            if (null == parkingDetailInfoEntity) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(), PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }

            RegionAddressGetRequestDto regionAddressGetRequestDto = new RegionAddressGetRequestDto();
            regionAddressGetRequestDto.setProvinceCode(parkingDetailInfoEntity.getProvinceCode());
            regionAddressGetRequestDto.setCityCode(parkingDetailInfoEntity.getCityCode());
            regionAddressGetRequestDto.setCountyCode(parkingDetailInfoEntity.getCountyCode());

            ObjectResultDto<RegionAddressResultDto> regionAddressObject = regionService.getRegionAddressApp(regionAddressGetRequestDto);
            if (regionAddressObject.isFailed() || regionAddressObject.getData() == null) {
                objectResultDto.makeResult(PmsResultEnum.PARKING_ADDRESS_GET_ERROR.getValue(), PmsResultEnum.PARKING_ADDRESS_GET_ERROR.getComment());
            } else {
                ParkingLocationResultDto resultDto = new ParkingLocationResultDto();
                resultDto.setAddress(regionAddressObject.getData().getAddress() + parkingDetailInfoEntity.getAddress());
                objectResultDto.setData(resultDto);
                objectResultDto.success();
            }
        } catch (Exception e) {
            log.error("获取停车场地址失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    @Override
    public ResultDto parkingInfoExist(ParkingInfoExistDto request) {
        ResultDto resultDto = new ResultDto();

        EntityWrapper<ParkingInfoEntity> entity = new EntityWrapper<>();
        entity.eq("longitude", request.getLongitude());
        entity.eq("latitude", request.getLatitude());
        entity.eq("name", request.getName());
        List<ParkingInfoEntity> parkingInfoList = parkingInfoCrudService.selectPlatformParkingInfoPageList(new Page<>(), entity);
        if (parkingInfoList != null && !parkingInfoList.isEmpty()) {
            return resultDto.success();
        }
        return resultDto.failed();
    }

    /**
     * 分页获取停车场详情信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingDetailInfoResultDto> getParkingDetailInfoList(ParkingDetailInfoPageRequestDto requestDto) {
        PagedResultDto<ParkingDetailInfoResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            Page<ParkingDetailInfoEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            EntityWrapper<ParkingDetailInfoEntity> entityWrapper = new EntityWrapper<>();
            Page<ParkingDetailInfoEntity> selectListPage = parkingDetailInfoCrudService.selectListPage(page, entityWrapper);
            if (CollectionUtil.isNotEmpty(selectListPage.getRecords())) {
                List<ParkingDetailInfoResultDto> list = modelMapper.map(selectListPage.getRecords(), new TypeToken<List<ParkingDetailInfoResultDto>>() {
                }.getType());
                pagedResultDto.setPageNo(page.getCurrent());
                pagedResultDto.setPageSize(page.getSize());
                pagedResultDto.setTotalCount(selectListPage.getTotal());
                pagedResultDto.setItems(list);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取停车场详情信息失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 更新停车场基本信息
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateParkingInfo(ParkingInfoUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            if (parkingInfoEntity != null) {
                EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", requestDto.getId());
                parkingInfoCrudService.updateParkingInfo(parkingInfoEntity, entityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
