package com.zoeeasy.cloud.pms.validator.dock;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.dock.cts.DockConstant;
import com.zoeeasy.cloud.pms.dock.dto.request.RegisterParkingRequestDto;
import com.zoeeasy.cloud.pms.dock.validator.RegisterParkingRequestDtoValidator;
import com.zoeeasy.cloud.pms.enums.NetZoneTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/12/4 0004
 */
@Component
public class RegisterParkingRequestDtoValidatorImpl extends ValidatorHandler<RegisterParkingRequestDto> implements RegisterParkingRequestDtoValidator {

    @Override
    public boolean accept(ValidatorContext context, RegisterParkingRequestDto requestDto) {
        if (requestDto.getTenantId() == null) {
            throw new ValidationException(DockConstant.TENANT_ID_EMPTY);
        }
        if (null != requestDto.getNetZoneType()) {
            NetZoneTypeEnum netZoneTypeEnum = NetZoneTypeEnum.parse(requestDto.getNetZoneType());
            if (netZoneTypeEnum == null) {
                throw new ValidationException(DockConstant.NET_ZONE_TYPE_NOT_STANDARD);
            }
        }
        return true;
    }
}
