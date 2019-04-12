package com.zoeeasy.cloud.pms.validator.park;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AppointmentEnum;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.validator.UpdateParkingInfoRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import com.zoeeasy.cloud.ucc.tenant.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 更新停车场参数校验
 * Created by song on 2018/9/11.
 */
@Component
public class UpdateParkingInfoRequestDtoValidatorImpl extends ValidatorHandler<UpdateParkingInfoRequestDto> implements UpdateParkingInfoRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private TenantService tenantService;

    @Override
    public boolean validate(ValidatorContext context, UpdateParkingInfoRequestDto requestDto) {
        //租户
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(GarageConstant.TENANTE_ID_IS_NULL);
        }
        ParkingInfoEntity parkingInfoExistByCode = parkingInfoCrudService.findByCodeAndTenantId(requestDto.getCloudParkingCode(), requestDto.getTenantId());
        if (parkingInfoExistByCode == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
        }
        ParkingInfoEntity parkingInfoExistByLocalCode = parkingInfoCrudService.selectByLocalCode(requestDto.getParkingCode());
        if (parkingInfoExistByLocalCode == null) {
            throw new ValidationException(ParkingConstant.PARKING_NOT_FOUND);
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
