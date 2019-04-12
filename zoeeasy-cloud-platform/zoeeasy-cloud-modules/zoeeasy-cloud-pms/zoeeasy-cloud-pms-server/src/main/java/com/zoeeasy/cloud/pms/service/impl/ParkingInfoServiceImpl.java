package com.zoeeasy.cloud.pms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import com.scapegoat.infrastructure.core.session.SessionIdentity;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleDeleteRequestDto;
import com.zoeeasy.cloud.charge.park.ParkChargeRuleService;
import com.zoeeasy.cloud.charge.park.dto.request.ParkingChargeRuleDeleteRequestDto;
import com.zoeeasy.cloud.core.utils.QrCodeCreateUtil;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.*;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.config.ParkingConfig;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.*;
import com.zoeeasy.cloud.pms.park.validator.*;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingImageGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingImageViewResultDto;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.pms.tool.WeChatUtil;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import com.zoeeasy.cloud.tool.oss.OssService;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;
import com.zoeeasy.cloud.tool.region.RegionService;
import com.zoeeasy.cloud.tool.region.dto.RegionCodeResultDto;
import com.zoeeasy.cloud.tool.region.dto.RegionRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by song on 2018/9/18.
 */
@Service("parkingInfoService")
@Slf4j
public class ParkingInfoServiceImpl implements ParkingInfoService {

    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?" + "grant_type=client_credential&appid=APPID&secret=APPSECRET";

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

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
    private AreaCrudService areaCrudService;

    @Autowired
    private ParkingGarageInfoCrudService parkingGarageInfoCrudService;

    @Autowired
    private ParkingAreaCrudService parkingAreaCrudService;

    @Autowired
    private ParkingLotInfoCrudService parkingLotInfoCrudService;

    @Autowired
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private ParkingLotConfigCrudService parkingLotConfigCrudService;

    @Autowired
    private ParkingGateInfoCrudService parkingGateInfoCrudService;

    @Autowired
    private ParkingLaneInfoCrudService parkingLaneInfoCrudService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Autowired
    private ParkChargeRuleService parkChargeRuleService;

    @Autowired
    private ParkingAppointRuleService parkingAppointRuleService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OssService ossService;

    @Autowired
    private ParkingQrcodeCrudService parkingQrcodeCrudService;

    @Autowired
    private ParkingConfig parkingConfig;

    @Autowired
    private ParkingFloorCrudService parkingFloorCrudService;

    /**
     * 添加停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addParking(@FluentValid({ParkingAddRequestDtoValidator.class}) ParkingAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                AreaEntity areaEntity = areaCrudService.findAreaByAreaCode(requestDto.getAreaCode());
                parkingInfoEntity.setAreaId(areaEntity.getId());
                parkingInfoEntity.setPathCode(areaEntity.getPathCode());
            }
            //默认LOGO
            if (StringUtils.isEmpty(requestDto.getLogo())) {
                parkingInfoEntity.setLogo(ParkingConstant.PARKING_LOGO);
            }
            //默认支付方式 现金支付
            if (StringUtils.isEmpty(requestDto.getPayType())) {
                parkingInfoEntity.setPayType(String.valueOf(PayTypeEnum.CASH_PAYMENT.getValue()));
            }
            parkingInfoEntity.setRunStatus(RunStatusEnum.NOT_UP.getValue());
            parkingInfoEntity.setPlatformSupport(Boolean.FALSE);
            //默认固定车位数
            if (requestDto.getLotFixed() == null) {
                parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
            }
            //根据regionCode获取省市区编码
            if (!StringUtils.isEmpty(requestDto.getRegionCode())) {
                RegionRequestDto regionRequestDto = new RegionRequestDto();
                regionRequestDto.setCode(requestDto.getRegionCode());
                ObjectResultDto<RegionCodeResultDto> regionCode = regionService.getRegionCode(regionRequestDto);
                if (regionCode.getData() != null) {
                    parkingInfoEntity.setProvinceCode(regionCode.getData().getProvinceCode());
                    parkingInfoEntity.setCityCode(regionCode.getData().getCityCode());
                    parkingInfoEntity.setCountyCode(regionCode.getData().getCountyCode());
                }
            }
            parkingInfoCrudService.insert(parkingInfoEntity);

            ParkingDetailInfoEntity parkingDetailInfoEntity = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
            parkingDetailInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingDetailInfoCrudService.insert(parkingDetailInfoEntity);
            ParkingChargeInfoEntity parkingChargeInfoEntity = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
            parkingChargeInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingChargeInfoEntity.setActive(Boolean.TRUE);
            parkingChargeInfoCrudService.insert(parkingChargeInfoEntity);
            ParkingAppointInfoEntity parkingAppointInfoEntity = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
            parkingAppointInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingAppointInfoEntity.setActive(Boolean.TRUE);
            parkingAppointInfoCrudService.insert(parkingAppointInfoEntity);
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
            parkingCurrentInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingCurrentInfoEntity.setOperationState(OperationStateEnum.AVAILABLE.getValue());
            parkingCurrentInfoCrudService.insert(parkingCurrentInfoEntity);

            if (CollectionUtils.isNotEmpty(requestDto.getImageUrls())) {
                List<ParkingPictureEntity> pictures = new ArrayList<>();
                for (String url : requestDto.getImageUrls()) {
                    ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                    parkingPictureEntity.setParkingId(parkingInfoEntity.getId());
                    parkingPictureEntity.setUrl(url);
                    pictures.add(parkingPictureEntity);
                }
                parkingPictureCrudService.insertBatch(pictures);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场添加失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * @param requestDto NonTenantParkingAddRequestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addNonTenantParking(NonTenantParkingAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        SessionIdentity oldSessionIdentity = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity();
        SessionIdentity sessionIdentity = new SessionIdentity();
        sessionIdentity.setTenantId(-1L);
        sessionIdentity.setMultiTenancySide(TenancyHostSide.Tenant);
        FundamentalRequestContext.getFundamentalRequestContext().setSessionIdentity(sessionIdentity);
        try {
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            parkingInfoEntity.setName(parkingInfoEntity.getFullName());
            parkingInfoCrudService.insert(parkingInfoEntity);
            ParkingDetailInfoEntity parkingDetailInfoEntity = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
            parkingDetailInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingDetailInfoCrudService.insert(parkingDetailInfoEntity);
            ParkingChargeInfoEntity parkingChargeInfoEntity = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
            parkingChargeInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingChargeInfoEntity.setActive(Boolean.TRUE);
            parkingChargeInfoCrudService.insert(parkingChargeInfoEntity);
            ParkingAppointInfoEntity parkingAppointInfoEntity = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
            parkingAppointInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingAppointInfoEntity.setActive(Boolean.TRUE);
            parkingAppointInfoCrudService.insert(parkingAppointInfoEntity);
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
            parkingCurrentInfoEntity.setParkingId(parkingInfoEntity.getId());
            parkingCurrentInfoCrudService.insert(parkingCurrentInfoEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场添加失败" + e.getMessage());
            resultDto.failed();
        }
        FundamentalRequestContext.getFundamentalRequestContext().setSessionIdentity(oldSessionIdentity);
        return resultDto;
    }

    /**
     * 获取省市区
     *
     * @return
     */
    private Map<String, String> getDetailAddress(AreaEntity areaEntity) {
        Map<String, String> map = new HashMap<>();
        while (areaEntity != null && !areaEntity.getLevel().equals(RegionLevelEnum.COUNTRY.getValue())) {
            switch (areaEntity.getLevel()) {
                case ParkingConstant.PROVINCE_CODE:
                    map.put(ParkingConstant.PROVINCE, areaEntity.getCode());
                    break;
                case ParkingConstant.CITY_CODE:
                    map.put(ParkingConstant.CITY, areaEntity.getCode());
                    break;
                case ParkingConstant.COUNTY_CODE:
                    map.put(ParkingConstant.COUNTY, areaEntity.getCode());
                    break;
                default:
                    break;
            }
            areaEntity = areaCrudService.selectById(areaEntity.getParentId());
        }
        return map;
    }

    /**
     * 删除停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteParking(@FluentValid({ParkingDeleteRequestDtoValidator.class}) ParkingDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //删除停车场的同时，需要删除停车场下的所有资源
            parkingInfoCrudService.deleteById(requestDto.getId());
            //删除 ParkingDetailInfoEntity
            EntityWrapper<ParkingDetailInfoEntity> parkingDetailWrapper = new EntityWrapper<>();
            parkingDetailWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingDetailInfoCrudService.delete(parkingDetailWrapper);
            //删除 ParkingCurrentInfoEntity
            EntityWrapper<ParkingCurrentInfoEntity> parkingCurrentWrapper = new EntityWrapper<>();
            parkingCurrentWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingCurrentInfoCrudService.delete(parkingCurrentWrapper);
            //删除 ParkingChargeInfoEntity
            EntityWrapper<ParkingChargeInfoEntity> parkingChargeWrapper = new EntityWrapper<>();
            parkingChargeWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingChargeInfoCrudService.delete(parkingChargeWrapper);
            //删除 ParkingAppointInfoEntity
            EntityWrapper<ParkingAppointInfoEntity> parkingAppointWrapper = new EntityWrapper<>();
            parkingAppointWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingAppointInfoCrudService.delete(parkingAppointWrapper);
            //删除 ParkingPictureEntity
            EntityWrapper<ParkingPictureEntity> parkingPictureWrapper = new EntityWrapper<>();
            parkingPictureWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingPictureWrapper.eq("pictureType", PictureTypeEnum.DEFAULT.getValue());
            parkingPictureCrudService.delete(parkingPictureWrapper);
            //删除 ParkingGarageInfoEntity
            EntityWrapper<ParkingGarageInfoEntity> parkingGarageWrapper = new EntityWrapper<>();
            parkingGarageWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingGarageInfoCrudService.delete(parkingGarageWrapper);
            //删除 ParkingAreaEntity
            EntityWrapper<ParkingAreaEntity> parkingAreaWrapper = new EntityWrapper<>();
            parkingAreaWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingAreaCrudService.delete(parkingAreaWrapper);
            //删除 ParkingGateInfoEntity
            EntityWrapper<ParkingGateInfoEntity> parkingGateWrapper = new EntityWrapper<>();
            parkingGateWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingGateInfoCrudService.delete(parkingGateWrapper);
            //删除 ParkingLaneInfoEntity
            EntityWrapper<ParkingLaneInfoEntity> parkingLaneWrapper = new EntityWrapper<>();
            parkingLaneWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingLaneInfoCrudService.delete(parkingLaneWrapper);
            //删除 ParkingLotInfoEntity
            EntityWrapper<ParkingLotInfoEntity> parkingLotWrapper = new EntityWrapper<>();
            parkingLotWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingLotInfoCrudService.delete(parkingLotWrapper);
            //删除 ParkingLotStatusEntity
            EntityWrapper<ParkingLotStatusEntity> parkingLotStatusWrapper = new EntityWrapper<>();
            parkingLotStatusWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingLotStatusCrudService.delete(parkingLotStatusWrapper);
            //删除 ParkingLotConfigEntity
            EntityWrapper<ParkingLotConfigEntity> parkingLotConfigWrapper = new EntityWrapper<>();
            parkingLotConfigWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingLotConfigCrudService.delete(parkingLotConfigWrapper);
            //删除停车场关联的收费规则
            ParkingChargeRuleDeleteRequestDto parkingChargeRuleDeleteRequestDto = new ParkingChargeRuleDeleteRequestDto();
            parkingChargeRuleDeleteRequestDto.setId(requestDto.getId());
            parkChargeRuleService.deleteParkingChargeRuleByParkingId(parkingChargeRuleDeleteRequestDto);
            //删除停车场关联的预约规则
            ParkingAppointRuleDeleteRequestDto parkingAppointRuleDeleteRequestDto = new ParkingAppointRuleDeleteRequestDto();
            parkingAppointRuleDeleteRequestDto.setId(requestDto.getId());
            parkingAppointRuleService.deleteParkingAppointRuleByParkingId(parkingAppointRuleDeleteRequestDto);
            //删除楼层
            EntityWrapper<ParkingFloorEntity> floorEntityWrapper = new EntityWrapper<>();
            floorEntityWrapper.eq(ColumnConstant.PARKING_ID, requestDto.getId());
            parkingFloorCrudService.delete(floorEntityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("删除停车场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateParking(@FluentValid({ParkingUpdateRequestDtoValidator.class}) ParkingUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                AreaEntity areaEntity = areaCrudService.findAreaByAreaCode(requestDto.getAreaCode());
                parkingInfoEntity.setPathCode(areaEntity.getPathCode());
                parkingInfoEntity.setAreaId(areaEntity.getId());
            }
            EntityWrapper<ParkingInfoEntity> parkingInfoWrapper = new EntityWrapper<>();
            parkingInfoWrapper.eq(ColumnConstant.ID, requestDto.getId());
            //默认固定车位数
            if (requestDto.getLotFixed() == null) {
                parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
            }
            //根据regionCode获取省市区编码
            if (!StringUtils.isEmpty(requestDto.getRegionCode())) {
                RegionRequestDto regionRequestDto = new RegionRequestDto();
                regionRequestDto.setCode(requestDto.getRegionCode());
                ObjectResultDto<RegionCodeResultDto> regionCode = regionService.getRegionCode(regionRequestDto);
                if (regionCode.getData() != null) {
                    parkingInfoEntity.setProvinceCode(regionCode.getData().getProvinceCode());
                    parkingInfoEntity.setCityCode(regionCode.getData().getCityCode());
                    parkingInfoEntity.setCountyCode(regionCode.getData().getCountyCode());
                }
            }
            parkingInfoCrudService.update(parkingInfoEntity, parkingInfoWrapper);

            ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingDetailInfoEntity != null) {
                ParkingDetailInfoEntity parkingDetailInfoEntityUpdate = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
                EntityWrapper<ParkingDetailInfoEntity> parkingDetailInfoWrapper = new EntityWrapper<>();
                parkingDetailInfoWrapper.eq(ColumnConstant.ID, parkingDetailInfoEntity.getId());
                parkingDetailInfoCrudService.update(parkingDetailInfoEntityUpdate, parkingDetailInfoWrapper);
            }
            //判断收费信息是否更改，若没有更改则不做操作；若修改，则将之前的收费信息设为失效，再新增一条有效的收费信息
            ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingChargeInfoEntity != null && !compareChargeInfo(parkingChargeInfoEntity, requestDto)) {
                parkingChargeInfoEntity.setActive(Boolean.FALSE);
                EntityWrapper<ParkingChargeInfoEntity> parkingChargeInfoWrapper = new EntityWrapper<>();
                parkingChargeInfoWrapper.eq(ColumnConstant.ID, parkingChargeInfoEntity.getId());
                parkingChargeInfoCrudService.update(parkingChargeInfoEntity, parkingChargeInfoWrapper);
                ParkingChargeInfoEntity parkingChargeInfoNew = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
                parkingChargeInfoNew.setActive(Boolean.TRUE);
                parkingChargeInfoNew.setParkingId(requestDto.getId());
                parkingChargeInfoCrudService.insert(parkingChargeInfoNew);
            }

            //判断预约信息是否更改，若没有更改则不做操作；若修改，则将之前的预约信息设为失效，再新增一条有效的预约信息
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingAppointInfoEntity != null && !compareAppointInfo(parkingAppointInfoEntity, requestDto)) {
                parkingAppointInfoEntity.setActive(Boolean.FALSE);
                EntityWrapper<ParkingAppointInfoEntity> parkingAppointInfoWrapper = new EntityWrapper<>();
                parkingAppointInfoWrapper.eq(ColumnConstant.ID, parkingAppointInfoEntity.getId());
                parkingAppointInfoCrudService.update(parkingAppointInfoEntity, parkingAppointInfoWrapper);
                ParkingAppointInfoEntity parkingAppointInfoNew = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
                parkingAppointInfoNew.setActive(Boolean.TRUE);
                parkingAppointInfoNew.setParkingId(requestDto.getId());
                parkingAppointInfoCrudService.insert(parkingAppointInfoNew);
            }

            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingCurrentInfoEntity != null) {
                ParkingCurrentInfoEntity parkingCurrentInfoUpdate = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
                EntityWrapper<ParkingCurrentInfoEntity> parkingCurrentInfoWrapper = new EntityWrapper<>();
                parkingCurrentInfoWrapper.eq(ColumnConstant.ID, parkingCurrentInfoEntity.getId());
                parkingCurrentInfoCrudService.update(parkingCurrentInfoUpdate, parkingCurrentInfoWrapper);
            }

            parkingPictureCrudService.deleteParkingPicture(requestDto.getId());
            if (CollectionUtils.isNotEmpty(requestDto.getImageUrls())) {
                List<ParkingPictureEntity> pictures = new ArrayList<>();
                for (String url : requestDto.getImageUrls()) {
                    ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                    parkingPictureEntity.setParkingId(parkingInfoEntity.getId());
                    parkingPictureEntity.setUrl(url);
                    pictures.add(parkingPictureEntity);
                }
                parkingPictureCrudService.insertBatch(pictures);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("修改停车场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 判断收费信息是否改变
     *
     * @param oldChargeInfo
     * @param requestDto
     * @return
     */
    private boolean compareChargeInfo(ParkingChargeInfoEntity oldChargeInfo, ParkingUpdateRequestDto requestDto) {
        if (oldChargeInfo.getChargeDescription() == null && requestDto.getChargeDescription() != null) {
            return false;
        }
        if (oldChargeInfo.getChargeDescription() != null && requestDto.getChargeDescription() == null) {
            return false;
        }
        if (oldChargeInfo.getChargeDescription() != null && requestDto.getChargeDescription() != null) {
            if (!oldChargeInfo.getChargeDescription().equals(requestDto.getChargeDescription())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断预约信息是否改变
     *
     * @param oldAppointInfo
     * @param requestDto
     * @return
     */
    private boolean compareAppointInfo(ParkingAppointInfoEntity oldAppointInfo, ParkingUpdateRequestDto requestDto) {
        if (requestDto.getLotAppointmentTotal() == null && oldAppointInfo.getLotAppointmentTotal() != null) {
            return false;
        }
        if (requestDto.getLotAppointmentTotal() != null && oldAppointInfo.getLotAppointmentTotal() == null) {
            return false;
        }
        if (requestDto.getLotAppointmentTotal() != null && oldAppointInfo.getLotAppointmentTotal() != null) {
            if (!requestDto.getLotAppointmentTotal().equals(oldAppointInfo.getLotAppointmentTotal())) {
                return false;
            }
        }
        if (requestDto.getAppointmentRule() == null && oldAppointInfo.getAppointmentRule() != null) {
            return false;
        }
        if (requestDto.getAppointmentRule() != null && oldAppointInfo.getAppointmentRule() == null) {
            return false;
        }
        if (requestDto.getAppointmentRule() != null && oldAppointInfo.getAppointmentRule() != null) {
            if (!requestDto.getAppointmentRule().equals(oldAppointInfo.getAppointmentRule())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingResultDto> getParking(ParkingGetRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getId());
            if (parkingInfoEntity != null) {
                ParkingResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingResultDto.class);
                parkingResultDto.setLatitude(String.valueOf(parkingInfoEntity.getLatitude()));
                parkingResultDto.setLongitude(String.valueOf(parkingInfoEntity.getLongitude()));
                parkingResultDto.setChargeFee(parkingInfoEntity.getChargeFee());
                parkingResultDto.setFreeTime(parkingInfoEntity.getFreeTime());
                parkingResultDto.setSupportAppointment(parkingInfoEntity.getSupportAppointment());
                if (parkingInfoEntity.getChargeMode() != null && parkingInfoEntity.getChargeMode().equals(ParkingConstant.SELECTED)) {
                    parkingResultDto.setChargeMode(null);
                }
                //默认支付方式现金支付不展示
                if (!StringUtils.isEmpty(parkingInfoEntity.getPayType()) && parkingInfoEntity.getPayType().equals(String.valueOf(PayTypeEnum.CASH_PAYMENT.getValue()))) {
                    parkingResultDto.setPayType(null);
                }
                parkingResultDto.setRegionCountyCode(parkingResultDto.getCountyCode());
                parkingResultDto.setRegionCityCode(parkingResultDto.getCityCode());
                parkingResultDto.setRegionProvinceCode(parkingResultDto.getProvinceCode());
                parkingResultDto.setAddress(parkingResultDto.getAddress());
                //停车场详细信息
                ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingId(requestDto.getId());
                if (parkingDetailInfoEntity != null) {
                    parkingResultDto.setZipCode(parkingDetailInfoEntity.getZipCode());
                    parkingResultDto.setManagerUnit(parkingDetailInfoEntity.getManagerUnit());
                    parkingResultDto.setOwnerName(parkingDetailInfoEntity.getOwnerName());
                    parkingResultDto.setOperatorUnit(parkingDetailInfoEntity.getOperatorUnit());
                    parkingResultDto.setChargerUnit(parkingDetailInfoEntity.getChargerUnit());
                    parkingResultDto.setContactName(parkingDetailInfoEntity.getContactName());
                    parkingResultDto.setContactTel(parkingDetailInfoEntity.getContactTel());
                    parkingResultDto.setContactPhone(parkingDetailInfoEntity.getContactPhone());
                    parkingResultDto.setContactEmail(parkingDetailInfoEntity.getContactEmail());
                    parkingResultDto.setContactQQ(parkingDetailInfoEntity.getContactQQ());
                    parkingResultDto.setContactWeixin(parkingDetailInfoEntity.getContactWeixin());
                    parkingResultDto.setContactAlipay(parkingDetailInfoEntity.getContactAlipay());
                }
                //获取商户区域code
                AreaEntity areaEntity = areaCrudService.selectById(parkingInfoEntity.getAreaId());
                if (areaEntity != null) {
                    Map<String, String> map = getDetailAddress(areaEntity);
                    parkingResultDto.setCountyCode(map.get(ParkingConstant.COUNTY));
                    parkingResultDto.setCityCode(map.get(ParkingConstant.CITY));
                    parkingResultDto.setProvinceCode(map.get(ParkingConstant.PROVINCE));
                }
                //停车场收费信息
                ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(requestDto.getId());
                if (parkingChargeInfoEntity != null) {
                    parkingResultDto.setChargeDescription(parkingChargeInfoEntity.getChargeDescription());
                    parkingResultDto.setChargeDescription(parkingChargeInfoEntity.getChargeDescription());
                }
                //停车场预约信息
                ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.findByParkingId(requestDto.getId());
                if (parkingAppointInfoEntity != null) {
                    parkingResultDto.setLotAppointmentTotal(parkingAppointInfoEntity.getLotAppointmentTotal());
                    parkingResultDto.setAppointmentRule(parkingAppointInfoEntity.getAppointmentRule());
                }
                //停车场当前状态
                ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.findByParkingId(requestDto.getId());
                if (parkingCurrentInfoEntity != null) {
                    parkingResultDto.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable());
                    parkingResultDto.setLotAppointmentAvailable(parkingCurrentInfoEntity.getLotAppointmentAvailable());
                }
                //停车场图片
                List<ParkingPictureEntity> pictures = parkingPictureCrudService.findParkingPicture(requestDto.getId());
                if (CollectionUtils.isNotEmpty(pictures)) {
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
     * 获取停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingResultDto> getParkingInfo(ParkingGetRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getId());
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
     * 根据code获取停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingResultDto> getParkingInfoByCode(ParkingByCodeGetRequestDto requestDto) {
        ObjectResultDto<ParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectByCode(requestDto.getCode());
            if (parkingInfoEntity != null) {
                ParkingResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ParkingResultDto.class);
                objectResultDto.setData(parkingResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场失败" + e.getMessage(),e);
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 分页获取停车场列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ParkingListResultDto> getParkingPagedList(ParkingQueryPagedRequestDto requestDto) {
        PagedResultDto<ParkingListResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            //如果根据区域code未查到区域，则停车场列表为空
            if (StringUtils.isNotEmpty(requestDto.getAreaCode())) {
                AreaEntity areaEntity = areaCrudService.findAreaByAreaCode(requestDto.getAreaCode());
                if (areaEntity != null) {
                    entityWrapper.like("pathCode", areaEntity.getPathCode(), SqlLike.RIGHT);
                } else {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                }
            }
            if (!StringUtils.isEmpty(requestDto.getFullName())) {
                entityWrapper.like("fullName", requestDto.getFullName());
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like("code", requestDto.getCode());
            }
            if (requestDto.getRunStatus() != null) {
                entityWrapper.eq("runStatus", requestDto.getRunStatus());
            }
            if (!StringUtils.isEmpty(requestDto.getLotType())) {
                String[] strs = requestDto.getLotType().split(",");
                for (int i = 0; i < strs.length; i++) {
                    if (i == 0) {
                        entityWrapper.andNew(ColumnConstant.LOTTYPE, strs[i]);
                    } else {
                        entityWrapper.orNew(ColumnConstant.LOTTYPE, strs[i]);
                    }
                }
            }
            entityWrapper.orderBy("creationTime", false);
            Page<ParkingInfoEntity> parkingPage = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingInfoEntity> parkingPageList = parkingInfoCrudService.selectPage(parkingPage, entityWrapper);

            List<ParkingListResultDto> parkingDtoList = new ArrayList<>();
            for (ParkingInfoEntity parkingInfoEntity : parkingPageList.getRecords()) {
                ParkingListResultDto parkingListResultDto = new ParkingListResultDto();
                parkingListResultDto.setId(parkingInfoEntity.getId());
                parkingListResultDto.setCode(parkingInfoEntity.getCode());
                parkingListResultDto.setName(parkingInfoEntity.getName());
                parkingListResultDto.setFullName(parkingInfoEntity.getFullName());
                parkingListResultDto.setLotType(parkingInfoEntity.getLotType());
                parkingListResultDto.setOpenStartTime(parkingInfoEntity.getOpenStartTime());
                parkingListResultDto.setOpenEndTime(parkingInfoEntity.getOpenEndTime());
                parkingListResultDto.setLatitude(String.valueOf(parkingInfoEntity.getLatitude()));
                parkingListResultDto.setLongitude(String.valueOf(parkingInfoEntity.getLongitude()));
                parkingListResultDto.setLotTotal(parkingInfoEntity.getLotTotal());
                parkingListResultDto.setLotFixed(parkingInfoEntity.getLotFixed());
                parkingListResultDto.setPayMode(parkingInfoEntity.getPayMode());
                parkingListResultDto.setRunStatus(parkingInfoEntity.getRunStatus());
                if (parkingInfoEntity.getChargeMode() != null && !parkingInfoEntity.getChargeMode().equals(ParkingConstant.SELECTED)) {
                    parkingListResultDto.setChargeMode(parkingInfoEntity.getChargeMode());
                }
                parkingListResultDto.setChargeFee(parkingInfoEntity.getChargeFee());
                parkingListResultDto.setSupportAppointment(parkingInfoEntity.getSupportAppointment());
                ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingId(parkingInfoEntity.getId());
                if (parkingDetailInfoEntity != null) {
                    parkingListResultDto.setAddress(parkingDetailInfoEntity.getAddress());
                }
                ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.findByParkingId(parkingInfoEntity.getId());
                if (parkingCurrentInfoEntity != null) {
                    parkingListResultDto.setOperationState(parkingCurrentInfoEntity.getOperationState());
                    parkingListResultDto.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable());
                    parkingListResultDto.setLotAppointmentAvailable(parkingCurrentInfoEntity.getLotAppointmentAvailable());
                }
                ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.findByParkingId(parkingInfoEntity.getId());
                if (parkingAppointInfoEntity != null) {
                    parkingListResultDto.setLotAppointmentTotal(parkingAppointInfoEntity.getLotAppointmentTotal());
                }

                //处理上下架撤销
                ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(parkingInfoEntity.getId());
                if (parkingApplyInfoExist != null) {
                    parkingListResultDto.setOperateStatus(getOperateStatus(parkingApplyInfoExist));
                    if (parkingInfoEntity.getRunStatus().equals(RunStatusEnum.ALREADY_UP.getValue())) {
                        parkingListResultDto.setRunOperateTime(parkingApplyInfoExist.getRunOperateTime());
                    }
                } else {
                    parkingListResultDto.setOperateStatus(OperateStatusEnum.APPLY_ON.getValue());
                }
                parkingDtoList.add(parkingListResultDto);
            }
            pagedResultDto.setPageNo(parkingPageList.getCurrent());
            pagedResultDto.setPageSize(parkingPageList.getSize());
            pagedResultDto.setTotalCount(parkingPageList.getTotal());
            pagedResultDto.setItems(parkingDtoList);

            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取停车场列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 获取处理上下架撤销状态
     *
     * @param parkingApplyInfoExist
     * @return
     */
    private Integer getOperateStatus(ParkingApplyInfoEntity parkingApplyInfoExist) {
        Integer operateStatus = null;
        if (parkingApplyInfoExist.getAuditStatus().equals(AuditStatusEnum.AWAIT.getValue())) {
            operateStatus = OperateStatusEnum.CAN_REPEAL.getValue();
        } else if (parkingApplyInfoExist.getAuditStatus().equals(AuditStatusEnum.PASS.getValue())) {
            if (parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.NOT_UP.getValue())) {
                operateStatus = OperateStatusEnum.CANNOT_REPEAL.getValue();
            }
            if (parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.ALREADY_UP.getValue())) {
                operateStatus = OperateStatusEnum.APPLY_OUT.getValue();
            }
            if (parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.ALREADY_DOWN.getValue())) {
                operateStatus = OperateStatusEnum.APPLY_ON.getValue();
            }
        } else if (parkingApplyInfoExist.getAuditStatus().equals(AuditStatusEnum.NOT_PASS.getValue())) {
            if (parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.NOT_UP.getValue())) {
                operateStatus = OperateStatusEnum.APPLY_ON.getValue();
            }
            if (parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.ALREADY_UP.getValue())) {
                operateStatus = OperateStatusEnum.APPLY_OUT.getValue();
            }
            if (parkingApplyInfoExist.getRunStatus().equals(RunStatusEnum.ALREADY_DOWN.getValue())) {
                operateStatus = OperateStatusEnum.APPLY_ON.getValue();
            }
        }
        return operateStatus;
    }

    /**
     * 获取停车场列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingListGetResultDto> getParkingList(ParkingListGetRequestDto requestDto) {
        ListResultDto<ParkingListGetResultDto> listResultDto = new ListResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            boolean area = true;
            if (requestDto.getAreaCode() != null) {
                AreaEntity areaEntity = areaCrudService.findAreaByAreaCode(requestDto.getAreaCode());
                if (areaEntity != null) {
                    entityWrapper.like("pathCode", areaEntity.getPathCode(), SqlLike.RIGHT);
                } else {
                    area = false;
                }
            }
            if (!StringUtils.isEmpty(requestDto.getName())) {
                entityWrapper.like("fullName", requestDto.getName());
            }
            if (!StringUtils.isEmpty(requestDto.getLotType())) {
                String[] strs = requestDto.getLotType().split(",");
                for (int i = 0; i < strs.length; i++) {
                    if (i == 0) {
                        entityWrapper.andNew(ColumnConstant.LOTTYPE, strs[i]);
                    } else {
                        entityWrapper.orNew(ColumnConstant.LOTTYPE, strs[i]);
                    }
                }
            }
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.eq("code", requestDto.getCode());
            }
            if (null != requestDto.getId()) {
                entityWrapper.eq("id", requestDto.getId());
            }
            List<ParkingInfoEntity> parkingInfoEntities = null;
            if (area) {
                parkingInfoEntities = parkingInfoCrudService.selectList(entityWrapper);
            }
            if (parkingInfoEntities != null) {
                List<ParkingListGetResultDto> parkingListGetResultDtoList = modelMapper.map(parkingInfoEntities, new TypeToken<List<ParkingListGetResultDto>>() {
                }.getType());
                listResultDto.setItems(parkingListGetResultDtoList);
            }
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取停车场列表失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 获取停车场图像
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ParkingImageViewResultDto> getParkingImageList(ParkingImageGetRequestDto requestDto) {
        ListResultDto<ParkingImageViewResultDto> resultDto = new ListResultDto<>();
        try {
            //获取停车场图像
            List<ParkingPictureEntity> pictures = parkingPictureCrudService.findParkingPicture(requestDto.getParkingId());

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
     * 根据ID获取停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingByIdResultDto> getParkingById(ParkingInfoGetByIdRequestDto requestDto) {
        ObjectResultDto<ParkingByIdResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            if (null != requestDto.getId()) {
                ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getId());
                if (null != parkingInfoEntity) {
                    ParkingByIdResultDto parkingResultDto = new ParkingByIdResultDto();
                    StringBuilder path = new StringBuilder();
                    AreaEntity areaEntity = areaCrudService.selectById(parkingInfoEntity.getAreaId());
                    while (areaEntity != null && !areaEntity.getLevel().equals(RegionLevelEnum.COUNTRY.getValue())) {
                        path.insert(0, areaEntity.getName());
                        areaEntity = areaCrudService.selectById(areaEntity.getParentId());
                    }
                    parkingResultDto.setAreaPath(path.toString());
                    objectResultDto.setData(parkingResultDto);
                }
            }
            objectResultDto.success();
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("通过id获取停车场信息失败" + e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 申请停车场上下线
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto applyRunStatus(@FluentValid({ApplyRunStatusRequestDtoValidator.class}) ApplyRunStatusRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long applyUserId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            ParkingApplyInfoEntity parkingApplyInfoEntity = new ParkingApplyInfoEntity();
            ParkingInfoEntity parkingInfoEntity = new ParkingInfoEntity();
            parkingApplyInfoEntity.setParkingId(requestDto.getParkingId());
            parkingApplyInfoEntity.setApplyUserId(applyUserId);
            parkingApplyInfoEntity.setApplyTime(DateUtils.now());
            parkingApplyInfoEntity.setAuditStatus(AuditStatusEnum.AWAIT.getValue());
            parkingApplyInfoEntity.setApplyType(requestDto.getApplyType());
            parkingApplyInfoEntity.setApplyRemark(requestDto.getApplyRemark());
            if (requestDto.getApplyType().equals(ApplyTypeEnum.UP.getValue())) {
                parkingApplyInfoEntity.setRunStatus(RunStatusEnum.NOT_UP.getValue());
                parkingInfoEntity.setRunStatus(RunStatusEnum.NOT_UP.getValue());
            } else {
                parkingApplyInfoEntity.setRunStatus(RunStatusEnum.ALREADY_UP.getValue());
                parkingInfoEntity.setRunStatus(RunStatusEnum.ALREADY_UP.getValue());
            }
            parkingApplyInfoCrudService.insert(parkingApplyInfoEntity);
            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("id", requestDto.getParkingId());
            parkingInfoEntity.setAuditStatus(AuditStatusEnum.AWAIT.getValue());
            parkingInfoCrudService.update(parkingInfoEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("申请停车场上下线失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 撤销上下架申请
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto repealApply(@FluentValid({RepealApplyRequestDtoValidator.class}) RepealApplyRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getParkingId());
            parkingApplyInfoCrudService.deleteById(parkingApplyInfoExist.getId());
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (parkingInfoEntity.getRunStatus().equals(RunStatusEnum.ALREADY_UP.getValue())) {
                EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("id", requestDto.getParkingId());
                ParkingInfoEntity parkingInfoUpdate = new ParkingInfoEntity();
                parkingInfoUpdate.setAuditStatus(AuditStatusEnum.PASS.getValue());
                parkingInfoCrudService.update(parkingInfoUpdate, entityWrapper);
            } else {
                parkingInfoCrudService.updateParkingAuditStatus(requestDto.getParkingId());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("撤销上下架申请失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改可用车位
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateLotAvailable(@FluentValid({LotAvailableUpdateRequestDtoValidator.class}) LotAvailableUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingCurrentInfoEntity parkingCurrentInfoEntity = new ParkingCurrentInfoEntity();
            EntityWrapper<ParkingCurrentInfoEntity> entityWrapper = new EntityWrapper<>();
            parkingCurrentInfoEntity.setLotAvailable(requestDto.getLotAvailable());
            entityWrapper.eq("parkingId", requestDto.getParkingId());
            parkingCurrentInfoCrudService.update(parkingCurrentInfoEntity, entityWrapper);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改可用车位数失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ObjectResultDto<ParkQRCodeResultDto> getQRCodeUrl(ParkQRCodeRequestDto requestDto) {
        ObjectResultDto<ParkQRCodeResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(requestDto.getParkingId());
            if (parkingInfoEntity == null) {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            EntityWrapper<ParkingQrcodeEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("parkingId", requestDto.getParkingId());
            final ParkingQrcodeEntity parkingQrcodeEntity = parkingQrcodeCrudService.selectOne(entityEntityWrapper);
            if (parkingQrcodeEntity != null) {
                final ParkQRCodeResultDto parkQRCodeResultDto = new ParkQRCodeResultDto();
                parkQRCodeResultDto.setCodeUrl(parkingQrcodeEntity.getCodeUrl());
                objectResultDto.setData(parkQRCodeResultDto);
                objectResultDto.success();
            } else {
                // 生成二维码
                final String nonceStr = StringUtils.getUUID();
                final String fileName = StringUtils.getUUID() + ".png";
                String content = parkingConfig.getTemplet().concat(nonceStr);
                final ByteArrayOutputStream outputStream =
                        QrCodeCreateUtil.createQrCode(content, ParkingConstant.QRCODE_IMAGE_SIZE, "png");
                //上传二维码
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
                ObjectResultDto<OssFileUploadResultDto> fileUploadResult =
                        ossService.aliyunOssFileUpload(fileName, byteArrayInputStream);
                if (fileUploadResult.isSuccess() && fileUploadResult.getData() != null) {
                    String codeUrl = fileUploadResult.getData().getFileUrl();
                    //保存信息
                    final ParkingQrcodeEntity saveEntity = new ParkingQrcodeEntity();
                    saveEntity.setCodeUrl(codeUrl);
                    saveEntity.setParkingId(requestDto.getParkingId());
                    saveEntity.setNoncestr(nonceStr);
                    parkingQrcodeCrudService.insert(saveEntity);
                    final ParkQRCodeResultDto parkQRCodeResultDto = new ParkQRCodeResultDto();
                    parkQRCodeResultDto.setCodeUrl(codeUrl);
                    objectResultDto.setData(parkQRCodeResultDto);
                    objectResultDto.success();
                } else {
                    objectResultDto.makeResult(PmsResultEnum.PARKING_QRCODE_CREATE_FAILED.getValue(),
                            PmsResultEnum.PARKING_QRCODE_CREATE_FAILED.getComment());
                }
            }
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("生成二维码失败:{}", e.getMessage());
        }
        return objectResultDto;
    }

    /**
     * 获取token
     *
     * @return
     */
    public ObjectResultDto<WeChatSmallAppQrcodeResultDto> getToken() {
        ObjectResultDto<WeChatSmallAppQrcodeResultDto> objectResultDto = new ObjectResultDto<>();
        WeChatSmallAppQrcodeResultDto parkQRCodeResultDto = new WeChatSmallAppQrcodeResultDto();
        try {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("grant_type", "client_credential");
            map.put("appid", "wxeec1af7e2251f967");
            map.put("secret", "07d0173a1c9c65ed12f09162fbfe52af");
            String weChat = WeChatUtil.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);
            JSONObject json = JSONObject.parseObject(weChat);
            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                parkQRCodeResultDto.setToken(json.getString("access_token"));
                objectResultDto.setData(parkQRCodeResultDto);
            } else {
                return null;
            }
        } catch (Exception e) {
            objectResultDto.failed();
            log.error("获取token失败:{}", e.getMessage(), e);
        }
        return objectResultDto;
    }
}