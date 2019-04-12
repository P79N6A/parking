package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AppointmentEnum;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.validator.AddParkingInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 停车场管理系统新增停车场参数校验
 * Created by song on 2018/9/11.
 */
@Component
public class AddParkingInfoRequestDtoValidatorImpl extends ValidatorHandler<AddParkingInfoRequestDto> implements AddParkingInfoRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Override
    public boolean validate(ValidatorContext context, AddParkingInfoRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectByLocalCode(requestDto.getParkingCode());
        if (parkingInfoEntity != null) {
            throw new ValidationException(ParkingConstant.PARKING_INFO_PARKING_LOCAL_CODE_USED);
        }
        if (StringUtils.isSpecialSymbols(requestDto.getParkingName())) {
            throw new ValidationException(ParkingConstant.PARKING_NAME_ILLEGAL);
        }
        if (requestDto.getLotTotal() != null) {
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
        } else {
            if (requestDto.getLotAvailable() != null){
                throw new ValidationException(ParkingConstant.LOT_TOTAL_IS_NULL);
            }
            if (requestDto.getLotFixed() != null){
                throw new ValidationException(ParkingConstant.LOT_TOTAL_IS_NULL);
            }
            if (requestDto.getLotAppointmentTotal() != null){
                throw new ValidationException(ParkingConstant.LOT_TOTAL_IS_NULL);
            }
        }
        return true;
    }
}
