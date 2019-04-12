package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.area.AreaService;
import com.zoeeasy.cloud.pms.area.dto.request.DetailAddressRequestDto;
import com.zoeeasy.cloud.pms.area.dto.result.DetailAddressResultDto;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.*;
import com.zoeeasy.cloud.pms.park.ManagementParkingInfoService;
import com.zoeeasy.cloud.pms.park.cst.ColumnConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.ManagementParkingPagedResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ManagementParkingResultDto;
import com.zoeeasy.cloud.pms.park.validator.ManagementParkingDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.ParkingPutAwayRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.ThirdPartyParkingAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.ThirdPartyParkingUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.tool.enums.RegionLevelEnum;
import com.zoeeasy.cloud.tool.region.RegionService;
import com.zoeeasy.cloud.tool.region.dto.RegionCodeResultDto;
import com.zoeeasy.cloud.tool.region.dto.RegionRequestDto;
import com.zoeeasy.cloud.tool.region.dto.RegionResultDto;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantGetRequestDto;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantListRequestDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantListResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端停车场服务
 */
@Service("managementParkingInfoService")
@Slf4j
public class ManagementParkingInfoServiceImpl implements ManagementParkingInfoService {

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
    private ParkingLotStatusCrudService parkingLotStatusCrudService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 添加第三方停车场(无租户)
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto addThirdPartyParking(@FluentValid({ThirdPartyParkingAddRequestDtoValidator.class}) ThirdPartyParkingAddRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            if (StringUtils.isEmpty(requestDto.getLogo())) {
                parkingInfoEntity.setLogo(ParkingConstant.PARKING_LOGO);
            }
            //默认支付方式 现金支付
            if (StringUtils.isEmpty(requestDto.getPayType())) {
                parkingInfoEntity.setPayType(String.valueOf(PayTypeEnum.CASH_PAYMENT.getValue()));
            }
            parkingInfoEntity.setAuditStatus(AuditStatusEnum.PASS.getValue());
            parkingInfoEntity.setRunStatus(RunStatusEnum.NOT_UP.getValue());
            parkingInfoEntity.setPlatformSupport(Boolean.FALSE);
            //默认固定车位数
            if (requestDto.getLotFixed() == null) {
                parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
            }
            parkingInfoCrudService.insert(parkingInfoEntity);
            ParkingDetailInfoEntity parkingDetailInfoEntity = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
            parkingDetailInfoEntity.setParkingId(parkingInfoEntity.getId());
            //根据regionCode获取省市区编码
            if (!StringUtils.isEmpty(requestDto.getRegionCode())) {
                RegionRequestDto regionRequestDto = new RegionRequestDto();
                regionRequestDto.setCode(requestDto.getRegionCode());
                ObjectResultDto<RegionCodeResultDto> regionCode = regionService.getRegionCode(regionRequestDto);
                if (regionCode.getData() != null) {
                    parkingDetailInfoEntity.setProvinceCode(regionCode.getData().getProvinceCode());
                    parkingDetailInfoEntity.setCityCode(regionCode.getData().getCityCode());
                    parkingDetailInfoEntity.setCountyCode(regionCode.getData().getCountyCode());
                }
            }
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
            log.error("添加第三方停车场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改第三方停车场(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateThirdPartyParking(@FluentValid({ThirdPartyParkingUpdateRequestDtoValidator.class}) ThirdPartyParkingUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            EntityWrapper<ParkingInfoEntity> parkingInfoWrapper = new EntityWrapper<>();
            parkingInfoWrapper.eq(ColumnConstant.ID, requestDto.getId());
            parkingInfoCrudService.update(parkingInfoEntity, parkingInfoWrapper);

            ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingDetailInfoEntity != null) {
                ParkingDetailInfoEntity parkingDetailInfoEntityUpdate = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
                EntityWrapper<ParkingDetailInfoEntity> parkingDetailWrapper = new EntityWrapper<>();
                parkingDetailWrapper.eq(ColumnConstant.ID, parkingDetailInfoEntity.getId());
                //根据regionCode获取省市区编码
                if (!StringUtils.isEmpty(requestDto.getRegionCode())) {
                    RegionRequestDto regionRequestDto = new RegionRequestDto();
                    regionRequestDto.setCode(requestDto.getRegionCode());
                    ObjectResultDto<RegionCodeResultDto> regionCode = regionService.getRegionCode(regionRequestDto);
                    if (regionCode.getData() != null) {
                        parkingDetailInfoEntityUpdate.setProvinceCode(regionCode.getData().getProvinceCode());
                        parkingDetailInfoEntityUpdate.setCityCode(regionCode.getData().getCityCode());
                        parkingDetailInfoEntityUpdate.setCountyCode(regionCode.getData().getCountyCode());
                    }
                }
                //默认固定车位数
                if (requestDto.getLotFixed() == null) {
                    parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
                }
                parkingDetailInfoCrudService.update(parkingDetailInfoEntityUpdate, parkingDetailWrapper);
            }

            //判断收费信息是否更改，若没有更改则不做操作；若修改，则将之前的收费信息设为失效，再新增一条有效的收费信息
            ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingChargeInfoEntity != null && !compareChargeInfo(parkingChargeInfoEntity, requestDto)) {
                parkingChargeInfoEntity.setActive(Boolean.FALSE);
                EntityWrapper<ParkingChargeInfoEntity> parkingChargeWrapper = new EntityWrapper<>();
                parkingChargeWrapper.eq(ColumnConstant.ID, parkingChargeInfoEntity.getId());
                parkingChargeInfoCrudService.update(parkingChargeInfoEntity, parkingChargeWrapper);
                ParkingChargeInfoEntity parkingChargeInfoNew = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
                parkingChargeInfoNew.setActive(Boolean.TRUE);
                parkingChargeInfoNew.setParkingId(requestDto.getId());
                parkingChargeInfoCrudService.insert(parkingChargeInfoNew);
            }

            //判断预约信息是否更改，若没有更改则不做操作；若修改，则将之前的预约信息设为失效，再新增一条有效的预约信息
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.findByParkingId(requestDto.getId());
            if (parkingAppointInfoEntity != null && !compareAppointInfo(parkingAppointInfoEntity, requestDto)) {
                parkingAppointInfoEntity.setActive(Boolean.FALSE);
                EntityWrapper<ParkingAppointInfoEntity> parkingAppointWrapper = new EntityWrapper<>();
                parkingAppointWrapper.eq(ColumnConstant.ID, parkingAppointInfoEntity.getId());
                parkingAppointInfoCrudService.update(parkingAppointInfoEntity, parkingAppointWrapper);
                ParkingAppointInfoEntity parkingAppointInfoNew = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
                parkingAppointInfoNew.setActive(Boolean.TRUE);
                parkingAppointInfoNew.setParkingId(requestDto.getId());
                parkingAppointInfoCrudService.insert(parkingAppointInfoNew);
            }

            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.findByParkingId(requestDto.getId());
            ParkingCurrentInfoEntity parkingCurrentInfoUpdate = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
            EntityWrapper<ParkingCurrentInfoEntity> parkingCurrentWrapper = new EntityWrapper<>();
            parkingCurrentWrapper.eq(ColumnConstant.ID, parkingCurrentInfoEntity.getId());
            parkingCurrentInfoCrudService.update(parkingCurrentInfoUpdate, parkingCurrentWrapper);

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
            log.error("修改第三方停车场失败" + e.getMessage());
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
    private boolean compareChargeInfo(ParkingChargeInfoEntity oldChargeInfo, ThirdPartyParkingUpdateRequestDto requestDto) {
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
    private boolean compareAppointInfo(ParkingAppointInfoEntity oldAppointInfo, ThirdPartyParkingUpdateRequestDto requestDto) {
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
     * 获取停车场详情(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ManagementParkingResultDto> getManagementParking(ParkingGetRequestDto requestDto) {
        ObjectResultDto<ManagementParkingResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getId());
            if (parkingInfoEntity != null) {
                ManagementParkingResultDto parkingResultDto = modelMapper.map(parkingInfoEntity, ManagementParkingResultDto.class);
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
                //停车场详细信息
                ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingIdTenantless(requestDto.getId());
                if (parkingDetailInfoEntity != null) {
                    parkingResultDto.setAddress(parkingDetailInfoEntity.getAddress());
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
                    parkingResultDto.setRegionCountyCode(parkingDetailInfoEntity.getCountyCode());
                    parkingResultDto.setRegionCityCode(parkingDetailInfoEntity.getCityCode());
                    parkingResultDto.setRegionProvinceCode(parkingDetailInfoEntity.getProvinceCode());
                    DetailAddressRequestDto detailAddressRequestDto = new DetailAddressRequestDto();
                    detailAddressRequestDto.setProvinceCode(parkingDetailInfoEntity.getProvinceCode());
                    detailAddressRequestDto.setCityCode(parkingDetailInfoEntity.getCityCode());
                    detailAddressRequestDto.setCountyCode(parkingDetailInfoEntity.getCountyCode());
                    detailAddressRequestDto.setTenantId(parkingDetailInfoEntity.getTenantId());
                    detailAddressRequestDto.setAddress(parkingDetailInfoEntity.getAddress());
                    ObjectResultDto<DetailAddressResultDto> detailAddressResultDto = areaService.getDetailAddress(detailAddressRequestDto);
                    if (detailAddressResultDto.getData() != null) {
                        parkingResultDto.setDetailAddress(detailAddressResultDto.getData().getDetailAddress());
                    }
                }
                //获取商户区域code
                AreaEntity areaEntity = areaCrudService.findAreaByIdNonTenant(parkingInfoEntity.getAreaId());
                if (areaEntity != null) {
                    Map<String, String> map = getDetailAddressNonTenant(areaEntity);
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
                ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.selectByParkingId(requestDto.getId());
                if (parkingAppointInfoEntity != null) {
                    parkingResultDto.setLotAppointmentTotal(parkingAppointInfoEntity.getLotAppointmentTotal());
                    parkingResultDto.setAppointmentRule(parkingAppointInfoEntity.getAppointmentRule());
                }
                //停车场当前状态
                ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getId());
                if (parkingCurrentInfoEntity != null) {
                    parkingResultDto.setOperationState(parkingCurrentInfoEntity.getOperationState());
                    parkingResultDto.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable());
                    parkingResultDto.setLotAppointmentAvailable(parkingCurrentInfoEntity.getLotAppointmentAvailable());
                }
                //停车场图片
                List<ParkingPictureEntity> pictures = parkingPictureCrudService.findParkingPictureList(requestDto.getId());
                if (CollectionUtils.isNotEmpty(pictures)) {
                    List<String> imageUrls = new ArrayList<>();
                    for (ParkingPictureEntity parkingPictureEntity : pictures) {
                        imageUrls.add(parkingPictureEntity.getUrl());
                    }
                    parkingResultDto.setImages(imageUrls);
                }
                ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getId());
                if (parkingApplyInfoExist != null) {
                    parkingResultDto.setApplyType(parkingApplyInfoExist.getApplyType());
                    parkingResultDto.setApplyRemark(parkingApplyInfoExist.getApplyRemark());
                    parkingResultDto.setAuditStatus(parkingApplyInfoExist.getAuditStatus());
                }
                objectResultDto.setData(parkingResultDto);
                objectResultDto.success();
            } else {
                return objectResultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
        } catch (Exception e) {
            log.error("获取停车场详情失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 获取省市区
     *
     * @return
     */
    private Map<String, String> getDetailAddressNonTenant(AreaEntity areaEntity) {
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
            areaEntity = areaCrudService.findAreaByIdNonTenant(areaEntity.getParentId());
        }
        return map;
    }

    /**
     * 删除停车场(无租户)
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteManagementParking(@FluentValid({ManagementParkingDeleteRequestDtoValidator.class}) ParkingDeleteRequestDto requestDto) {
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
            resultDto.success();
        } catch (Exception e) {
            log.error("删除停车场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页查询已审核通过停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ManagementParkingPagedResultDto> getManagementParkingPagedList(ManagementParkingPagedRequestDto requestDto) {
        PagedResultDto<ManagementParkingPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            List<Long> parkingIds;
            if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
                parkingIds = getParkingIdsByArea(requestDto.getAreaCode());
                if (CollectionUtils.isEmpty(parkingIds)) {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                } else {
                    entityWrapper.in("id", parkingIds);
                }
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
            if (requestDto.getRunStatus() != null) {
                entityWrapper.eq("runStatus", requestDto.getRunStatus());
            }
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                entityWrapper.like("fullName", requestDto.getParkingName());
            }
            if (!org.apache.commons.lang.StringUtils.isEmpty(requestDto.getTenantName())) {
                List<Long> tenantIds = new ArrayList<>();
                TenantListRequestDto tenantListRequestDto = new TenantListRequestDto();
                tenantListRequestDto.setName(requestDto.getTenantName());
                ListResultDto<TenantListResultDto> tenantList = tenantService.getTenantListByName(tenantListRequestDto);
                for (TenantListResultDto tenantListResultDto : tenantList.getItems()) {
                    tenantIds.add(tenantListResultDto.getId());
                }
                if (CollectionUtils.isEmpty(tenantIds)) {
                    pagedResultDto = new PagedResultDto(requestDto.getPageNo(), requestDto.getPageSize(), new ArrayList<>(), 0L);
                    pagedResultDto.success();
                    return pagedResultDto;
                } else {
                    entityWrapper.in("tenantId", tenantIds);
                }
            }
            entityWrapper.eq("auditStatus", AuditStatusEnum.PASS.getValue());
            Page<ParkingInfoEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingInfoEntity> parkingPage = page.setRecords(parkingInfoCrudService.selectPlatformParkingInfoPageList(page, entityWrapper));
            List<ManagementParkingPagedResultDto> list = new ArrayList<>();
            Map<Long, TenantResultDto> map = new HashMap<>();
            for (ParkingInfoEntity parkingInfoEntity : parkingPage.getRecords()) {
                ManagementParkingPagedResultDto managementParkingPagedResultDto = new ManagementParkingPagedResultDto();
                managementParkingPagedResultDto.setId(parkingInfoEntity.getId());
                managementParkingPagedResultDto.setCode(parkingInfoEntity.getCode());
                managementParkingPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                managementParkingPagedResultDto.setLotTotal(parkingInfoEntity.getLotTotal());
                //剩余车位数 实时计算:1.原始状态 总车位-固定车位  2.有车进入 可用车位-实时进入车辆数量(入车出车数量已做改变)
                Integer count = parkingLotStatusCrudService.findCountByParkingIdNonTenant(parkingInfoEntity.getId());
                if (count > 0) {
                    ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(parkingInfoEntity.getId());
                    if (parkingCurrentInfoEntity != null) {
                        managementParkingPagedResultDto.setLotAvailable(parkingCurrentInfoEntity.getLotAvailable());
                    }
                } else {
                    managementParkingPagedResultDto.setLotAvailable(parkingInfoEntity.getLotTotal() - parkingInfoEntity.getLotFixed());
                }
                managementParkingPagedResultDto.setLatitude(String.valueOf(parkingInfoEntity.getLatitude()));
                managementParkingPagedResultDto.setLongitude(String.valueOf(parkingInfoEntity.getLongitude()));
                if (map.containsKey(parkingInfoEntity.getTenantId())) {
                    managementParkingPagedResultDto.setTenantName(map.get(parkingInfoEntity.getTenantId()).getName());
                } else {
                    TenantGetRequestDto tenantGetRequestDto = new TenantGetRequestDto();
                    tenantGetRequestDto.setId(parkingInfoEntity.getTenantId());
                    ObjectResultDto<TenantResultDto> tenantResultDto = tenantService.getTenant(tenantGetRequestDto);
                    if (tenantResultDto.getData() != null) {
                        managementParkingPagedResultDto.setTenantName(tenantResultDto.getData().getName());
                        map.put(tenantResultDto.getData().getId(), tenantResultDto.getData());
                    }
                }
                managementParkingPagedResultDto.setCreationTime(parkingInfoEntity.getCreationTime());
                managementParkingPagedResultDto.setRunStatus(parkingInfoEntity.getRunStatus());
                if (parkingInfoEntity.getTenantId() == null) {
                    managementParkingPagedResultDto.setEdit(Boolean.TRUE);
                } else {
                    managementParkingPagedResultDto.setEdit(Boolean.FALSE);
                }
                list.add(managementParkingPagedResultDto);
            }
            pagedResultDto.setPageNo(parkingPage.getCurrent());
            pagedResultDto.setPageSize(parkingPage.getSize());
            pagedResultDto.setTotalCount(parkingPage.getTotal());
            pagedResultDto.setItems(list);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页查询已审核通过停车场" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 根据省市区code获取停车场ids
     *
     * @return
     */
    private List<Long> getParkingIdsByArea(String areaCode) {
        RegionRequestDto requestDto = new RegionRequestDto();
        requestDto.setCode(areaCode);
        ObjectResultDto<RegionResultDto> region = regionService.getRegionByCode(requestDto);
        List<Long> ids = new ArrayList<>();
        if (region.getData() != null) {
            EntityWrapper<ParkingDetailInfoEntity> entityWrapper = new EntityWrapper<>();
            if (region.getData().getLevel().equals(RegionLevelEnum.PROVINCE.getValue())) {
                entityWrapper.eq("provinceCode", region.getData().getCode());
            }
            if (region.getData().getLevel().equals(RegionLevelEnum.CITY.getValue())) {
                entityWrapper.eq("cityCode", region.getData().getCode());
            }
            if (region.getData().getLevel().equals(RegionLevelEnum.COUNTY.getValue())) {
                entityWrapper.eq("countyCode", region.getData().getCode());
            }
            List<ParkingDetailInfoEntity> list = parkingDetailInfoCrudService.findParkingDetailNonTenant(entityWrapper);
            for (ParkingDetailInfoEntity parkingDetailInfoEntity : list) {
                ids.add(parkingDetailInfoEntity.getParkingId());
            }
        }
        return ids;
    }

    /**
     * 停车场上下架
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto parkingPutAway(@FluentValid({ParkingPutAwayRequestDtoValidator.class}) ParkingPutAwayRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoEntity parkingInfoEntity = new ParkingInfoEntity();
            ParkingApplyInfoEntity parkingApplyInfoUpdate = new ParkingApplyInfoEntity();
            if (requestDto.getStatus().equals(ParkingStatusEnum.ON_LINE.getValue())) {
                parkingInfoEntity.setStatus(ParkingStatusEnum.ON_LINE.getValue());
                parkingApplyInfoUpdate.setRunStatus(RunStatusEnum.ALREADY_UP.getValue());
                parkingInfoEntity.setRunStatus(RunStatusEnum.ALREADY_UP.getValue());
            } else {
                parkingInfoEntity.setStatus(ParkingStatusEnum.NOT_ON_LINE.getValue());
                parkingApplyInfoUpdate.setRunStatus(RunStatusEnum.ALREADY_DOWN.getValue());
                parkingInfoEntity.setRunStatus(RunStatusEnum.ALREADY_DOWN.getValue());
            }
            parkingInfoEntity.setId(requestDto.getParkingId());
            parkingInfoCrudService.updateParkInfoById(parkingInfoEntity);
            Long runUserId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            parkingApplyInfoUpdate.setRunUserId(runUserId);
            parkingApplyInfoUpdate.setRunOperateTime(DateUtils.now());
            ParkingApplyInfoEntity parkingApplyInfoEntity = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getParkingId());
            if (parkingApplyInfoEntity != null) {
                parkingApplyInfoUpdate.setId(parkingApplyInfoEntity.getId());
                parkingApplyInfoCrudService.updateParkingApply(parkingApplyInfoUpdate);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场上下架失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
