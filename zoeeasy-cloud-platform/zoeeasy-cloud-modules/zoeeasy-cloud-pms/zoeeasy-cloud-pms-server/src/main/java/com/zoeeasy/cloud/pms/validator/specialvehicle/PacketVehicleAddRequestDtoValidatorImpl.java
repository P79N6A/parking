package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.common.utils.PlateNumberUtil;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;
import com.zoeeasy.cloud.pms.domain.PacketVehicleEntity;
import com.zoeeasy.cloud.pms.domain.ParkingPacketRuleEntity;
import com.zoeeasy.cloud.pms.enums.AllParkingEnum;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.PacketVehicleAddRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.ParkingPacketRuleDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketVehicleAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.PacketRuleCrudService;
import com.zoeeasy.cloud.pms.service.PacketVehicleCrudService;
import com.zoeeasy.cloud.pms.service.ParkingPacketRuleCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by song on 2018/10/19.
 */
@Component
public class PacketVehicleAddRequestDtoValidatorImpl extends ValidatorHandler<PacketVehicleAddRequestDto> implements PacketVehicleAddRequestDtoValidator {

    @Autowired
    private ParkingPacketRuleCrudService parkingPacketRuleCrudService;

    @Autowired
    private PacketRuleCrudService packetRuleCrudService;

    @Autowired
    private PacketVehicleCrudService packetVehicleCrudService;

    @Override
    public boolean validate(ValidatorContext context, PacketVehicleAddRequestDto requestDto) {
        boolean plateNumber = PlateNumberUtil.isPlateNumber(requestDto.getPlateNumber());
        if (!plateNumber){
            throw new ValidationException(ParkingConstant.PLATENUMBER_ILLEGAL);
        }
        if (requestDto.getAllParking().equals(AllParkingEnum.NO.getValue()) && CollectionUtils.isEmpty(requestDto.getRules())){
            throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_NOT_EMPTY);
        }
        if (CollectionUtils.isNotEmpty(requestDto.getRules())) {
            for (ParkingPacketRuleDto parkingPacketRuleDto : requestDto.getRules()) {
                ParkingPacketRuleEntity parkingPacketRuleEntity = parkingPacketRuleCrudService.findByParkingIdAndRuleId(parkingPacketRuleDto.getParkingId(),
                        parkingPacketRuleDto.getRuleId());
                if (parkingPacketRuleEntity == null) {
                    throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_NOT_EXIST);
                }
                PacketRuleEntity packetRuleEntity = packetRuleCrudService.selectById(parkingPacketRuleDto.getRuleId());
                if (packetRuleEntity != null && !packetRuleEntity.getPacketType().equals(requestDto.getPacketType())) {
                    throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_TYPE_NOT_MATE);
                }
                PacketVehicleEntity packetVehicleEntity = packetVehicleCrudService.findByPlateNumberAndParkingIdAndRuleId(requestDto.getPlateNumber(),
                        parkingPacketRuleDto.getParkingId(), parkingPacketRuleDto.getRuleId());
                if (packetVehicleEntity != null) {
                    throw new ValidationException(SpecialVehicleConstant.PACKET_VEHICLE_EXIST);
                }
            }
        }
        return true;
    }
}
