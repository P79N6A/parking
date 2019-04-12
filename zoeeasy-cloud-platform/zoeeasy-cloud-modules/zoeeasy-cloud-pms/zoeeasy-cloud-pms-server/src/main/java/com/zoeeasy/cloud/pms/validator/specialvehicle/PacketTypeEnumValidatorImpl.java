package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.enums.PacketTypeEnum;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketTypeEnumValidator;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class PacketTypeEnumValidatorImpl extends ValidatorHandler<Integer> implements PacketTypeEnumValidator {

    @Override
    public boolean accept(ValidatorContext context, Integer target) {
        if (target != null) {
            PacketTypeEnum packetTypeEnum = PacketTypeEnum.parse(target);
            if (packetTypeEnum == null) {
                throw new ValidationException(SpecialVehicleConstant.PACKET_VEHICLE_PACKET_TYPE_ERROR);
            }
        }
        return true;
    }
}
