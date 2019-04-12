package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.PacketRuleDeleteRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketRuleDeleteRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.PacketVehicleCrudService;
import com.zoeeasy.cloud.pms.service.ParkingPacketRuleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @date: 2018/10/23.
 * @author：zm
 */
@Component
public class PacketRuleDeleteRequestDtoValidatorImpl extends ValidatorHandler<PacketRuleDeleteRequestDto> implements PacketRuleDeleteRequestDtoValidator {

    @Autowired
    private ParkingPacketRuleCrudService parkingPacketRuleCrudService;

    @Autowired
    private PacketVehicleCrudService packetVehicleCrudService;

    @Override
    public boolean validate(ValidatorContext context, PacketRuleDeleteRequestDto requestDto) {
        ParkingPacketRuleEntity parkingPacketRuleEntity = parkingPacketRuleCrudService.selectById(requestDto.getId());
        if (parkingPacketRuleEntity != null) {
            Integer packetVehicleEntities = packetVehicleCrudService.findByParkingIdAndRuleId(parkingPacketRuleEntity.getParkingId(), parkingPacketRuleEntity.getRuleId());
            if (packetVehicleEntities > 0) {
                throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_NOT_TO_DELETE);
            }
        } else {
            throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_NOT_EXIST);
        }
        return true;
    }
}
