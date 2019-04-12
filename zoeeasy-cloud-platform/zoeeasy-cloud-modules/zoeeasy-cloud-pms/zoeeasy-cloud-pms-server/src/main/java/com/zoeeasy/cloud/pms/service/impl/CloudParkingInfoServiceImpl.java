package com.zoeeasy.cloud.pms.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.pms.domain.*;
import com.zoeeasy.cloud.pms.enums.OperationStateEnum;
import com.zoeeasy.cloud.pms.enums.PayTypeEnum;
import com.zoeeasy.cloud.pms.enums.RunStatusEnum;
import com.zoeeasy.cloud.pms.park.CloudParkingInfoService;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AddParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.UpdateParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.validator.AddParkingInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.park.validator.UpdateParkingInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.*;
import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 云平台提供给管理系统调用停车场接口
 */
@Service("cloudParkingInfoService")
@Slf4j
public class CloudParkingInfoServiceImpl implements CloudParkingInfoService {

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
    private DockInfoCrudService dockInfoCrudService;

    @Autowired
    private IdService idService;

    @Autowired
    private ParkingInfoServiceImpl parkingInfoService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 停车场管理系统添加停车场
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddParkingInfoResultDto addParkingInfo(@FluentValid({AddParkingInfoRequestDtoValidator.class}) AddParkingInfoRequestDto requestDto) {
        AddParkingInfoResultDto resultDto = new AddParkingInfoResultDto();
        try {
            String cloudCode = String.valueOf(idService.genId());
            ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.findByCodeAndTenantId(cloudCode, requestDto.getTenantId());
            if (parkingInfoExist == null) {
                ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
                parkingInfoEntity.setCode(cloudCode);
                parkingInfoEntity.setLocalCode(requestDto.getParkingCode());
                parkingInfoEntity.setFullName(requestDto.getParkingName());
                if (StringUtils.isEmpty(requestDto.getLogo())) {
                    parkingInfoEntity.setLogo(ParkingConstant.PARKING_LOGO);
                }
                //默认支付方式 现金支付
                if (StringUtils.isEmpty(requestDto.getPayType())) {
                    parkingInfoEntity.setPayType(String.valueOf(PayTypeEnum.CASH_PAYMENT.getValue()));
                }
                parkingInfoEntity.setRunStatus(RunStatusEnum.NOT_UP.getValue());
                parkingInfoEntity.setPlatformSupport(Boolean.FALSE);
                //根据客户端系统编号查找注册信息;
                DockInfoEntity dockInfoEntity = dockInfoCrudService.findByCloudCode(requestDto.getCloudCode(),
                        requestDto.getTenantId());
                if (dockInfoEntity != null) {
                    parkingInfoEntity.setDockId(dockInfoEntity.getId());
                } else {
                    log.warn("未先注册停车场" + requestDto.toString());
                    resultDto.failed();
                }
                //默认车位总数
                if (requestDto.getLotTotal() == null) {
                    parkingInfoEntity.setLotTotal(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
                }
                if (requestDto.getLotFixed() == null) {
                    parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
                }
                parkingInfoCrudService.insert(parkingInfoEntity);

                ParkingCurrentInfoEntity parkingCurrentInfoEntity = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
                parkingCurrentInfoEntity.setParkingId(parkingInfoEntity.getId());
                parkingCurrentInfoEntity.setOperationState(OperationStateEnum.AVAILABLE.getValue());
                parkingCurrentInfoCrudService.insert(parkingCurrentInfoEntity);

                ParkingDetailInfoEntity parkingDetailInfoEntity = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
                parkingDetailInfoEntity.setParkingId(parkingInfoEntity.getId());
                parkingDetailInfoCrudService.insert(parkingDetailInfoEntity);

                ParkingChargeInfoEntity parkingChargeInfoEntity = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
                parkingChargeInfoEntity.setChargeRule(null);
                parkingChargeInfoEntity.setChargeDescription(requestDto.getChargeRule());
                parkingChargeInfoEntity.setParkingId(parkingInfoEntity.getId());
                parkingChargeInfoEntity.setActive(Boolean.TRUE);
                parkingChargeInfoCrudService.insert(parkingChargeInfoEntity);

                ParkingAppointInfoEntity parkingAppointInfoEntity = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
                parkingAppointInfoEntity.setParkingId(parkingInfoEntity.getId());
                parkingAppointInfoEntity.setActive(Boolean.TRUE);
                parkingAppointInfoCrudService.insert(parkingAppointInfoEntity);

                if (CollectionUtils.isNotEmpty(requestDto.getImageUrls())) {
                    List<ParkingPictureEntity> pictures = new ArrayList<>();
                    for (String url : requestDto.getImageUrls()) {
                        ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                        parkingPictureEntity.setParkingId(parkingInfoEntity.getId());
                        parkingPictureEntity.setUrl(url);
                        parkingPictureEntity.setTenantId(requestDto.getTenantId());
                        pictures.add(parkingPictureEntity);
                    }
                    parkingPictureCrudService.insertBatch(pictures);
                }
                resultDto.setCloudCode(cloudCode);
                resultDto.success();
            } else {
                resultDto.failed();
                return resultDto;
            }
        } catch (Exception e) {
            log.error("停车场管理系统添加停车场失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 停车场管理系统修改停车场
     *
     * @param requestDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UpdateParkingInfoResultDto updateParkingInfo(@FluentValid({UpdateParkingInfoRequestDtoValidator.class}) UpdateParkingInfoRequestDto requestDto) {
        UpdateParkingInfoResultDto resultDto = new UpdateParkingInfoResultDto();
        try {
            ParkingInfoEntity parkingInfoExist = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
            ParkingInfoEntity parkingInfoEntity = modelMapper.map(requestDto, ParkingInfoEntity.class);
            parkingInfoEntity.setId(parkingInfoExist.getId());
            parkingInfoEntity.setFullName(requestDto.getParkingName());
            //默认车位总数
            if (requestDto.getLotTotal() == null) {
                parkingInfoEntity.setLotTotal(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
            }
            if (requestDto.getLotFixed() == null) {
                parkingInfoEntity.setLotFixed(ParkingConstant.DEFAULT_LOT_TOTAL_AND_LOT_FIXED);
            }
            parkingInfoEntity.setLastModificationTime(new Date());
            parkingInfoCrudService.updateParkInfoById(parkingInfoEntity);

            ParkingDetailInfoEntity parkingDetailInfoEntity = parkingDetailInfoCrudService.selectParkingDetailNonTenant(parkingInfoExist.getId(), requestDto.getTenantId());
            ParkingDetailInfoEntity parkingDetailInfoEntityUpdate = modelMapper.map(requestDto, ParkingDetailInfoEntity.class);
            parkingDetailInfoEntityUpdate.setId(parkingDetailInfoEntity.getId());
            parkingDetailInfoCrudService.updateParkingDetailInfoTenantless(parkingDetailInfoEntityUpdate);

            //判断收费信息是否更改，若没有更改则不做操作；若修改，则将之前的收费信息设为失效，再新增一条有效的收费信息
            ParkingChargeInfoEntity parkingChargeInfoEntity = parkingChargeInfoCrudService.findByParkingId(parkingInfoExist.getId());
            if (parkingChargeInfoEntity != null && !compareChargeInfo(parkingChargeInfoEntity, requestDto)) {
                parkingChargeInfoEntity.setActive(Boolean.FALSE);
                parkingChargeInfoCrudService.updateParkingChargeInfoNonTenant(parkingChargeInfoEntity);
                ParkingChargeInfoEntity parkingChargeInfoNew = modelMapper.map(requestDto, ParkingChargeInfoEntity.class);
                parkingChargeInfoNew.setChargeRule(null);
                parkingChargeInfoNew.setChargeDescription(requestDto.getChargeRule());
                parkingChargeInfoNew.setActive(Boolean.TRUE);
                parkingChargeInfoNew.setParkingId(parkingInfoExist.getId());
                parkingChargeInfoCrudService.insert(parkingChargeInfoNew);
            }

            //判断预约信息是否更改，若没有更改则不做操作；若修改，则将之前的预约信息设为失效，再新增一条有效的预约信息
            ParkingAppointInfoEntity parkingAppointInfoEntity = parkingAppointInfoCrudService.selectByParkingId(parkingInfoExist.getId());
            if (parkingAppointInfoEntity != null && !compareAppointInfo(parkingAppointInfoEntity, requestDto)) {
                parkingAppointInfoEntity.setActive(Boolean.FALSE);
                parkingAppointInfoCrudService.updateParkingAppointInfoNonTenant(parkingAppointInfoEntity);
                ParkingAppointInfoEntity parkingAppointInfoNew = modelMapper.map(requestDto, ParkingAppointInfoEntity.class);
                parkingAppointInfoNew.setActive(Boolean.TRUE);
                parkingAppointInfoNew.setParkingId(parkingInfoExist.getId());
                parkingAppointInfoCrudService.insert(parkingAppointInfoNew);
            }

            ParkingCurrentInfoEntity parkingCurrentInfoEntity = parkingCurrentInfoCrudService.selectByParkingId(parkingInfoExist.getId());
            ParkingCurrentInfoEntity parkingCurrentInfoUpdate = modelMapper.map(requestDto, ParkingCurrentInfoEntity.class);
            parkingCurrentInfoUpdate.setId(parkingCurrentInfoEntity.getId());
            parkingCurrentInfoCrudService.updateCurrentInfoById(parkingCurrentInfoUpdate);

            parkingPictureCrudService.deleteParkingPictureNonTenant(parkingInfoExist.getId());
            if (CollectionUtils.isNotEmpty(requestDto.getImageUrls())) {
                List<ParkingPictureEntity> pictures = new ArrayList<>();
                for (String url : requestDto.getImageUrls()) {
                    ParkingPictureEntity parkingPictureEntity = new ParkingPictureEntity();
                    parkingPictureEntity.setParkingId(parkingInfoExist.getId());
                    parkingPictureEntity.setUrl(url);
                    parkingPictureEntity.setTenantId(requestDto.getTenantId());
                    pictures.add(parkingPictureEntity);
                }
                parkingPictureCrudService.insertBatch(pictures);
            }
            resultDto.setCloudCode(requestDto.getCloudParkingCode());
            resultDto.success();
        } catch (Exception e) {
            log.error("停车场管理系统修改停车场失败" + e.getMessage());
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
    private boolean compareChargeInfo(ParkingChargeInfoEntity oldChargeInfo, UpdateParkingInfoRequestDto requestDto) {
        if (oldChargeInfo.getChargeDescription() == null && requestDto.getChargeRule() != null) {
            return false;
        }
        if (oldChargeInfo.getChargeDescription() != null && requestDto.getChargeRule() == null) {
            return false;
        }
        if (oldChargeInfo.getChargeDescription() != null && requestDto.getChargeRule() != null) {
            if (!oldChargeInfo.getChargeDescription().equals(requestDto.getChargeRule())) {
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
    private boolean compareAppointInfo(ParkingAppointInfoEntity oldAppointInfo, UpdateParkingInfoRequestDto requestDto) {
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
