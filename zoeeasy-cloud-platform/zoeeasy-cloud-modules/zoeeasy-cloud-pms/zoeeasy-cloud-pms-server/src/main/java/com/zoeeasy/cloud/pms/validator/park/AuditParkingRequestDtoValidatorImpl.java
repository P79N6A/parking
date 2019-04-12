package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingApplyInfoEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AppointmentEnum;
import com.zoeeasy.cloud.pms.enums.AuditOpinionEnum;
import com.zoeeasy.cloud.pms.enums.AuditStatusEnum;
import com.zoeeasy.cloud.pms.enums.ChargeFeeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.AuditParkingRequestDto;
import com.zoeeasy.cloud.pms.park.validator.AuditParkingRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingApplyInfoCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditParkingRequestDtoValidatorImpl extends ValidatorHandler<AuditParkingRequestDto> implements AuditParkingRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private ParkingApplyInfoCrudService parkingApplyInfoCrudService;

    @Override
    public boolean accept(ValidatorContext context, AuditParkingRequestDto requestDto) {
        //是否收费若选择是，收费模式、免费时长、收费规则详情非空判断
        if (requestDto.getChargeFee().equals(ChargeFeeEnum.YES.getValue())){
            if (requestDto.getChargeMode() == null){
                throw new ValidationException(ParkingConstant.CHARGE_MODE_NOT_NULL);
            }
            if (requestDto.getFreeTime() == null){
                throw new ValidationException(ParkingConstant.FREE_TIME_NOT_NULL);
            }
            if (com.scapegoat.infrastructure.common.utils.StringUtils.isEmpty(requestDto.getChargeDescription())){
                throw new ValidationException(ParkingConstant.CHARGE_DESCRIPTION_NOT_NULL);
            }
        }

        //审核通过或驳回输入限制
        if (requestDto.getAuditOpinion().equals(AuditOpinionEnum.PASS.getValue())){
            if (requestDto.getAuditNotPassReason() != null){
                throw new ValidationException(ParkingConstant.AUDIT_NOT_PASS_REASON_CANNOT);
            }
            if (!StringUtils.isEmpty(requestDto.getAuditRemark())){
                throw new ValidationException(ParkingConstant.AUDIT_REMARK_CANNOT);
            }
        } else {
            if (requestDto.getPutAwayStatus() != null){
                throw new ValidationException(ParkingConstant.PUT_AWAY_STATUS_CANNOT);
            }
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.getParkInfoById(requestDto.getParkingId());
        if (parkingInfoEntity == null){
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingApplyInfoEntity parkingApplyInfoEntity = parkingApplyInfoCrudService.selectParkingApplyByParkingId(requestDto.getParkingId());
        if (parkingApplyInfoEntity == null){
            throw new ValidationException(ParkingConstant.PARKING_APPLY_NOT_NULL);
        }
        if (parkingApplyInfoEntity.getAuditStatus().equals(AuditStatusEnum.PASS.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_APPLY_PASS);
        }
        if (parkingApplyInfoEntity.getAuditStatus().equals(AuditStatusEnum.NOT_PASS.getValue())){
            throw new ValidationException(ParkingConstant.PARKING_APPLY_NOT_PASS);
        }
        //海康 支付宝id校验
        if (!StringUtils.isEmpty(requestDto.getAliParkId())){
            EntityWrapper<ParkingInfoEntity> aliWrapper = new EntityWrapper<>();
            aliWrapper.eq("aliParkId", requestDto.getAliParkId());
            ParkingInfoEntity aliParking = parkingInfoCrudService.selectPlatformParkingInfo(aliWrapper);
            if (aliParking != null && !aliParking.getId().equals(parkingInfoEntity.getId())) {
                throw new ValidationException(ParkingConstant.ALI_PARKID_EXIST);
            }
        }
        if (!StringUtils.isEmpty(requestDto.getHikParkId())){
            EntityWrapper<ParkingInfoEntity> hikWrapper = new EntityWrapper<>();
            hikWrapper.eq("hikParkId", requestDto.getHikParkId());
            ParkingInfoEntity hikParking = parkingInfoCrudService.selectPlatformParkingInfo(hikWrapper);
            if (hikParking != null && !hikParking.getId().equals(parkingInfoEntity.getId())) {
                throw new ValidationException(ParkingConstant.HIK_PARKID_EXIST);
            }
        }

        if (requestDto.getLotAvailable() != null && requestDto.getLotAvailable() > requestDto.getLotTotal()) {
            throw new ValidationException(ParkingConstant.PARKING_LOTAVAILABLE_NOT_GT_LOTTOTAL);
        }
        if (requestDto.getLotFixed() != null && requestDto.getLotFixed() > requestDto.getLotTotal()) {
            throw new ValidationException(ParkingConstant.PARKING_LOTFIXED_NOT_GT_LOTTOTAL);
        }
        if (requestDto.getLotAppointmentTotal() != null) {
            if (requestDto.getSupportAppointment().equals(AppointmentEnum.NO.getValue())) {
                throw new ValidationException(ParkingConstant.PARKING_NOT_SUPPORT_APPOINT);
            }
            if (requestDto.getLotAppointmentTotal() > requestDto.getLotTotal()) {
                throw new ValidationException(ParkingConstant.PARKING_LOTAPPOINTMENTTOTAL_NOT_GT_LOTTOTAL);
            }
        }

        if (requestDto.getLotAppointmentAvailable() != null) {
            if (requestDto.getSupportAppointment().equals(AppointmentEnum.NO.getValue())) {
                throw new ValidationException(ParkingConstant.PARKING_NOT_SUPPORT_APPOINT);
            }
            if (requestDto.getLotAppointmentAvailable() > requestDto.getLotAppointmentTotal()) {
                throw new ValidationException(ParkingConstant.PARKING_LOTAPPOINTMENTAVAILABLE_NOT_GT_LOTAPPOINTMENTTOTAL);
            }
        }
        return true;
    }
}
