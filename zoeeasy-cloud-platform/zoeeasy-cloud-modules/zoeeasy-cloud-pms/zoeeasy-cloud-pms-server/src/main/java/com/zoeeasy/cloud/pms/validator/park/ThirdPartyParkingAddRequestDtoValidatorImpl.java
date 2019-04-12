package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AppointmentEnum;
import com.zoeeasy.cloud.pms.enums.ChargeFeeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ThirdPartyParkingAddRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ThirdPartyParkingAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyParkingAddRequestDtoValidatorImpl extends ValidatorHandler<ThirdPartyParkingAddRequestDto> implements ThirdPartyParkingAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private AreaCrudService areaCrudService;

    @Override
    public boolean validate(ValidatorContext context, ThirdPartyParkingAddRequestDto requestDto) {
        //是否收费若选择是，收费模式、免费时长、收费规则详情非空判断
        if (requestDto.getChargeFee().equals(ChargeFeeEnum.YES.getValue())){
            if (requestDto.getChargeMode() == null){
                throw new ValidationException(ParkingConstant.CHARGE_MODE_NOT_NULL);
            }
            if (requestDto.getFreeTime() == null){
                throw new ValidationException(ParkingConstant.FREE_TIME_NOT_NULL);
            }
            if (StringUtils.isEmpty(requestDto.getChargeDescription())){
                throw new ValidationException(ParkingConstant.CHARGE_DESCRIPTION_NOT_NULL);
            }
        }

        EntityWrapper<ParkingInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("code", requestDto.getCode());
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectPlatformParkingInfo(entityWrapper);
        if (parkingInfoEntity != null) {
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_CODE_USED);
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
