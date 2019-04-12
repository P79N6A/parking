package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.AreaEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AppointmentEnum;
import com.zoeeasy.cloud.pms.enums.ChargeFeeEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingAddRequestDto;
import com.zoeeasy.cloud.pms.park.validator.ParkingAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.AreaCrudService;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 新增停车场参数校验
 * Created by song on 2018/9/11.
 */
@Component
public class ParkingAddRequestDtoValidatorImpl extends ValidatorHandler<ParkingAddRequestDto> implements ParkingAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private AreaCrudService areaCrudService;

    @Override
    public boolean validate(ValidatorContext context, ParkingAddRequestDto requestDto) {
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

        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.findByCode(requestDto.getCode());
        if (parkingInfoEntity != null) {
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_CODE_USED);
        }
        if (!StringUtils.isEmpty(requestDto.getAreaCode())) {
            AreaEntity areaEntity = areaCrudService.findByCode(requestDto.getAreaCode());
            if (areaEntity == null) {
                throw new ValidationException(ParkingConstant.AREA_NOT_FOUND);
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
            if (!(requestDto.getSupportAppointment() != null && requestDto.getSupportAppointment().equals(AppointmentEnum.YES.getValue()))) {
                throw new ValidationException(ParkingConstant.PARKING_NOT_SUPPORT_APPOINT);
            }
            if (requestDto.getLotAppointmentTotal() == null){
                throw new ValidationException(ParkingConstant.APPOINTMENT_TOTAL_NULL);
            }
            if (requestDto.getLotAppointmentAvailable() > requestDto.getLotAppointmentTotal()) {
                throw new ValidationException(ParkingConstant.PARKING_LOTAPPOINTMENTAVAILABLE_NOT_GT_LOTAPPOINTMENTTOTAL);
            }
        }
        return true;
    }
}
