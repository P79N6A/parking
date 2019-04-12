package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.ChargeRuleTotalRequestDto;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleTotalResultDto;
import com.zoeeasy.cloud.pms.area.AreaService;
import com.zoeeasy.cloud.pms.area.dto.request.DetailAddressRequestDto;
import com.zoeeasy.cloud.pms.area.dto.result.DetailAddressResultDto;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.AuditStatusEnum;
import com.zoeeasy.cloud.pms.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.pms.enums.PictureTypeEnum;
import com.zoeeasy.cloud.pms.enums.PutAwayStatusEnum;
import com.zoeeasy.cloud.pms.park.ParkingApplyInfoService;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ApplyParkingPagedRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.AuditParkingRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ApplyParkingPagedResultDto;
import com.zoeeasy.cloud.pms.park.validator.AuditParkingRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.tool.region.RegionService;
import com.zoeeasy.cloud.tool.region.dto.RegionCodeResultDto;
import com.zoeeasy.cloud.tool.region.dto.RegionRequestDto;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantGetRequestDto;
import com.zoeeasy.cloud.ucc.tenant.dto.request.TenantListRequestDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantListResultDto;
import com.zoeeasy.cloud.ucc.tenant.dto.result.TenantResultDto;
import com.zoeeasy.cloud.ucc.user.UserService;
import com.zoeeasy.cloud.ucc.user.dto.request.UserGetRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.result.UserDetailResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 停车场审核服务
 */
@Service("parkingApplyInfoService")
@Slf4j
public class ParkingApplyInfoServiceImpl implements ParkingApplyInfoService {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChargeRuleService chargeRuleService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private ParkingDetailInfoCrudService parkingDetailInfoCrudService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ModelMapper modelMapper;

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

    /**
     * 审核停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto auditParking(@FluentValid({AuditParkingRequestDtoValidator.class}) AuditParkingRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Long auditUserId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
            Date nowTime = DateUtils.now();
            ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getParkingId());
            ParkingApplyInfoEntity parkingApplyInfoUpdate = new ParkingApplyInfoEntity();
            parkingApplyInfoUpdate.setAuditStatus(requestDto.getAuditOpinion());
            if (requestDto.getAuditOpinion().equals(AuditStatusEnum.PASS.getValue())) {
                parkingApplyInfoUpdate.setRunStatus(requestDto.getPutAwayStatus());
                parkingApplyInfoUpdate.setRunUserId(auditUserId);
                parkingApplyInfoUpdate.setRunOperateTime(nowTime);
            } else {
                parkingApplyInfoUpdate.setAuditNotPassReason(requestDto.getAuditNotPassReason());
                parkingApplyInfoUpdate.setAuditRemark(requestDto.getAuditRemark());
            }
            parkingApplyInfoUpdate.setAuditUserId(auditUserId);
            parkingApplyInfoUpdate.setAuditTime(nowTime);
            parkingApplyInfoUpdate.setId(parkingApplyInfoExist.getId());
            parkingApplyInfoCrudService.updateParkingApply(parkingApplyInfoUpdate);
            updatePark(requestDto, auditUserId);
            resultDto.success();
        } catch (Exception e) {
            log.error("审核停车场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 分页获取审核停车场列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public PagedResultDto<ApplyParkingPagedResultDto> getApplyParkingPagedList(ApplyParkingPagedRequestDto requestDto) {
        PagedResultDto<ApplyParkingPagedResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.orderBy("creationTime", false);
            if (!StringUtils.isEmpty(requestDto.getCode())) {
                entityWrapper.like("code", requestDto.getCode());
            }
            if (!StringUtils.isEmpty(requestDto.getParkingName())) {
                entityWrapper.like("fullName", requestDto.getParkingName());
            }
            if (requestDto.getRunStatus() != null) {
                entityWrapper.eq("runStatus", requestDto.getRunStatus());
            }
            if (requestDto.getAuditUserId() != null) {
                entityWrapper.eq("auditUserId", requestDto.getAuditUserId());
            }
            if (requestDto.getStartTime() != null) {
                entityWrapper.ge("auditTime", requestDto.getStartTime());
            }
            if (requestDto.getEndTime() != null) {
                entityWrapper.le("auditTime", requestDto.getEndTime());
            }
            if (!StringUtils.isEmpty(requestDto.getTenantName())) {
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
            entityWrapper.isNotNull("auditStatus");
            Page<ParkingInfoEntity> page = new Page<>(requestDto.getPageNo(), requestDto.getPageSize());
            Page<ParkingInfoEntity> parkingPage = page.setRecords(parkingInfoCrudService.selectPlatformParkingInfoPageList(page, entityWrapper));
            List<ApplyParkingPagedResultDto> list = new ArrayList<>();
            for (ParkingInfoEntity parkingInfoEntity : parkingPage.getRecords()) {
                ApplyParkingPagedResultDto applyParkingPagedResultDto = new ApplyParkingPagedResultDto();
                applyParkingPagedResultDto.setId(parkingInfoEntity.getId());
                applyParkingPagedResultDto.setParkingName(parkingInfoEntity.getFullName());
                applyParkingPagedResultDto.setCode(parkingInfoEntity.getCode());
                ParkingApplyInfoEntity parkingApplyInfoExist = parkingApplyInfoCrudService.selectParkingApplyByParkingId(parkingInfoEntity.getId());
                if (parkingApplyInfoExist != null) {
                    applyParkingPagedResultDto.setApplyTime(parkingApplyInfoExist.getApplyTime());
                    applyParkingPagedResultDto.setAuditNotPassReason(parkingApplyInfoExist.getAuditNotPassReason());
                }
                //获取收费规则条数
                ChargeRuleTotalRequestDto chargeRuleTotalRequestDto = new ChargeRuleTotalRequestDto();
                chargeRuleTotalRequestDto.setParkingId(parkingInfoEntity.getId());
                ObjectResultDto<ChargeRuleTotalResultDto> chargeRuleTotal = chargeRuleService.getChargeRuleTotal(chargeRuleTotalRequestDto);
                if (chargeRuleTotal.isSuccess() && chargeRuleTotal.getData() != null) {
                    applyParkingPagedResultDto.setChargeRuleTotal(chargeRuleTotal.getData().getTotal());
                }
                TenantGetRequestDto tenantGetRequestDto = new TenantGetRequestDto();
                tenantGetRequestDto.setId(parkingInfoEntity.getTenantId());
                ObjectResultDto<TenantResultDto> tenantResultDto = tenantService.getTenant(tenantGetRequestDto);
                if (tenantResultDto.isSuccess() && tenantResultDto.getData() != null) {
                    applyParkingPagedResultDto.setTenantName(tenantResultDto.getData().getName());
                }
                applyParkingPagedResultDto.setAuditTime(parkingInfoEntity.getAuditTime());
                applyParkingPagedResultDto.setAuditStatus(parkingInfoEntity.getAuditStatus());
                applyParkingPagedResultDto.setAuditUserName(parkingInfoEntity.getAuditUserName());
                applyParkingPagedResultDto.setRunStatus(parkingInfoEntity.getRunStatus());

                ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingIdTenantless(parkingInfoEntity.getId());
                if (parkingDetailInfoEntity != null) {
                    DetailAddressRequestDto detailAddressRequestDto = new DetailAddressRequestDto();
                    detailAddressRequestDto.setProvinceCode(parkingDetailInfoEntity.getProvinceCode());
                    detailAddressRequestDto.setCityCode(parkingDetailInfoEntity.getCityCode());
                    detailAddressRequestDto.setCountyCode(parkingDetailInfoEntity.getCountyCode());
                    detailAddressRequestDto.setTenantId(parkingDetailInfoEntity.getTenantId());
                    detailAddressRequestDto.setAddress(parkingDetailInfoEntity.getAddress());
                    ObjectResultDto<DetailAddressResultDto> detailAddressResultDto = areaService.getDetailAddress(detailAddressRequestDto);
                    if (detailAddressResultDto.isSuccess() && detailAddressResultDto.getData() != null) {
                        applyParkingPagedResultDto.setDetailAddress(detailAddressResultDto.getData().getDetailAddress());
                    }
                }
                list.add(applyParkingPagedResultDto);
            }
            pagedResultDto.setPageNo(parkingPage.getCurrent());
            pagedResultDto.setPageSize(parkingPage.getSize());
            pagedResultDto.setTotalCount(parkingPage.getTotal());
            pagedResultDto.setItems(list);
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("分页获取审核停车场列表失败" + e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 修改停车场
     */
    private void updatePark(AuditParkingRequestDto requestDto, Long auditUserId) {
        Date nowTime = DateUtils.now();
        ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
        parkingInfoEntity.setLastModificationTime(nowTime);
        if (requestDto.getAuditOpinion().equals(AuditStatusEnum.PASS.getValue())) {
            if (requestDto.getPutAwayStatus().equals(PutAwayStatusEnum.ALREADY_UP.getValue())) {
                parkingInfoEntity.setStatus(ParkingStatusEnum.ON_LINE.getValue());
            } else {
                parkingInfoEntity.setStatus(ParkingStatusEnum.NOT_ON_LINE.getValue());
            }
            parkingInfoEntity.setRunStatus(requestDto.getPutAwayStatus());
        }
        UserGetRequestDto userGetRequestDto = new UserGetRequestDto();
        userGetRequestDto.setId(auditUserId);
        ObjectResultDto<UserDetailResultDto> userDetailResultDto = userService.getUser(userGetRequestDto);
        if (userDetailResultDto.isSuccess() && userDetailResultDto.getData() != null) {
            parkingInfoEntity.setAuditUserName(userDetailResultDto.getData().getUserName());
        }
        parkingInfoEntity.setAuditStatus(requestDto.getAuditOpinion());
        parkingInfoEntity.setAuditUserId(auditUserId);
        parkingInfoEntity.setAuditTime(nowTime);
        parkingInfoEntity.setId(requestDto.getParkingId());
        //默认固定车位数
        if (requestDto.getLotFixed() == null) {
            parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
        }
        parkingInfoCrudService.updateParkInfoById(parkingInfoEntity);

        ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.findByParkingIdTenantless(requestDto.getParkingId());
        if (parkingDetailInfoEntity != null) {
            ParkingDetailInfoEntity parkingDetailInfoEntityUpdate = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
            parkingDetailInfoEntityUpdate.setId(parkingDetailInfoEntity.getId());
            parkingDetailInfoEntityUpdate.setLastModificationTime(nowTime);
            //根据regionCode获取省市区编码
            if (!com.scapegoat.infrastructure.common.utils.StringUtils.isEmpty(requestDto.getRegionCode())) {
                RegionRequestDto regionRequestDto = new RegionRequestDto();
                regionRequestDto.setCode(requestDto.getRegionCode());
                ObjectResultDto<RegionCodeResultDto> regionCode = regionService.getRegionCode(regionRequestDto);
                if (regionCode.getData() != null) {
                    parkingDetailInfoEntityUpdate.setProvinceCode(regionCode.getData().getProvinceCode());
                    parkingDetailInfoEntityUpdate.setCityCode(regionCode.getData().getCityCode());
                    parkingDetailInfoEntityUpdate.setCountyCode(regionCode.getData().getCountyCode());
                }
            }
            parkingDetailInfoCrudService.updateParkingDetailInfoTenantless(parkingDetailInfoEntityUpdate);
        }

        //判断收费信息是否更改，若没有更改则不做操作；若修改，则将之前的收费信息设为失效，再新增一条有效的收费信息
        ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(requestDto.getParkingId());
        if (parkingChargeInfoEntity != null) {
            if (!compareChargeInfo(parkingChargeInfoEntity, requestDto)) {
                parkingChargeInfoEntity.setActive(Boolean.FALSE);
                parkingChargeInfoEntity.setLastModificationTime(nowTime);
                parkingChargeInfoCrudService.updateParkingChargeInfoNonTenant(parkingChargeInfoEntity);
                ParkingChargeInfoEntity parkingChargeInfoNew = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
                parkingChargeInfoNew.setActive(Boolean.TRUE);
                parkingChargeInfoNew.setParkingId(requestDto.getParkingId());
                parkingChargeInfoNew.setCreationTime(nowTime);
                parkingChargeInfoNew.setTenantId(parkingChargeInfoEntity.getTenantId());
                parkingChargeInfoCrudService.insertParkingChargeInfoNonTenant(parkingChargeInfoNew);
            }
        }

        //判断预约信息是否更改，若没有更改则不做操作；若修改，则将之前的预约信息设为失效，再新增一条有效的预约信息
        ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.selectByParkingId(requestDto.getParkingId());
        if (parkingAppointInfoEntity != null) {
            if (!compareAppointInfo(parkingAppointInfoEntity, requestDto)) {
                parkingAppointInfoEntity.setActive(Boolean.FALSE);
                parkingAppointInfoEntity.setLastModificationTime(nowTime);
                parkingAppointInfoCrudService.updateParkingAppointInfoNonTenant(parkingAppointInfoEntity);
                ParkingAppointInfoEntity parkingAppointInfoNew = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
                parkingAppointInfoNew.setActive(Boolean.TRUE);
                parkingAppointInfoNew.setParkingId(requestDto.getParkingId());
                parkingAppointInfoNew.setCreationTime(nowTime);
                parkingAppointInfoNew.setTenantId(parkingAppointInfoEntity.getTenantId());
                parkingAppointInfoCrudService.insertParkingAppointInfoNonTenant(parkingAppointInfoNew);
            }
        }

        ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(requestDto.getParkingId());
        if (parkingCurrentInfoEntity != null) {
            ParkingCurrentInfoEntity parkingCurrentInfoUpdate = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
            parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
            parkingCurrentInfoUpdate.setLastModificationTime(nowTime);
            parkingCurrentInfoCrudService.updateCurrentInfoById(parkingCurrentInfoUpdate);
        }

        parkingPictureCrudService.deleteParkingPictureNonTenant(requestDto.getParkingId());
        if (CollectionUtils.isNotEmpty(requestDto.getImageUrls())) {
            for (String url : requestDto.getImageUrls()) {
                ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                parkingPictureEntity.setParkingId(parkingInfoEntity.getId());
                parkingPictureEntity.setUrl(url);
                parkingPictureEntity.setPictureType(PictureTypeEnum.DEFAULT.getValue());
                parkingPictureEntity.setCreationTime(nowTime);
                parkingPictureCrudService.insertParkingPictureNonTenant(parkingPictureEntity);
            }
        }
    }

    /**
     * 判断收费信息是否改变
     *
     * @param oldChargeInfo
     * @param requestDto
     * @return
     */
    private boolean compareChargeInfo(ParkingChargeInfoEntity oldChargeInfo, AuditParkingRequestDto requestDto) {
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
    private boolean compareAppointInfo(ParkingAppointInfoEntity oldAppointInfo, AuditParkingRequestDto requestDto) {
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

}
