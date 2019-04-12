package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;
import com.zoeeasy.cloud.pms.service.PacketRuleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.PacketRuleUpdateRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketRuleUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.PacketVehicleCrudService;
import com.zoeeasy.cloud.pms.service.ParkingPacketRuleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Component
public class PacketRuleUpdateRequestDtoValidatorImpl extends ValidatorHandler<PacketRuleUpdateRequestDto> implements PacketRuleUpdateRequestDtoValidator {

    @Autowired
    private ParkingPacketRuleCrudService parkingPacketRuleCrudService;

    @Autowired
    private PacketVehicleCrudService packetVehicleCrudService;

    @Autowired
    private PacketRuleCrudService packetRuleCrudService;

    @Override
    public boolean validate(ValidatorContext context, PacketRuleUpdateRequestDto requestDto) {
        PacketRuleEntity byPacketName = packetRuleCrudService.findByPacketName(requestDto.getPacketName());
        ParkingPacketRuleEntity packetRuleEntity = parkingPacketRuleCrudService.selectById(requestDto.getId());
        if (byPacketName != null && !byPacketName.getId().equals(packetRuleEntity.getRuleId())) {
            throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_EXIST);
        }
        ParkingPacketRuleEntity parkingPacketRuleEntity = parkingPacketRuleCrudService.selectById(requestDto.getId());
        if (parkingPacketRuleEntity != null) {
            Integer packetVehicleEntities = packetVehicleCrudService.findByRuleId(parkingPacketRuleEntity.getRuleId());
            if (packetVehicleEntities > 0) {
                throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_NOT_TO_EDIT);
            }
        }
        if (parkingPacketRuleEntity == null) {
            throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_NOT_EXIST);
        }
        return true;

    }
}
