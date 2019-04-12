package com.zoeeasy.cloud.pms.validator.specialvehicle;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.domain.PacketRuleEntity;
import com.zoeeasy.cloud.pms.domain.ParkingInfoEntity;
import com.zoeeasy.cloud.pms.enums.AllParkingEnum;
import com.zoeeasy.cloud.pms.service.PacketRuleCrudService;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.PacketRuleAddRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketRuleAddRequestDtoValidator;
import com.zoeeasy.cloud.pms.service.ParkingInfoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 新增包期规则效验
 *
 * @date: 2018/10/13.
 * @author：zm
 */
@Component
public class PacketRuleAddRequestDtoValidatorImpl extends ValidatorHandler<PacketRuleAddRequestDto> implements PacketRuleAddRequestDtoValidator {

    @Autowired
    private ParkingInfoCrudService parkingInfoCrudService;

    @Autowired
    private PacketRuleCrudService packetRuleCrudService;

    @Override
    public boolean validate(ValidatorContext context, PacketRuleAddRequestDto requestDto) {
        PacketRuleEntity byPacketName = packetRuleCrudService.findByPacketName(requestDto.getPacketName());
        if (byPacketName != null) {
            throw new ValidationException(SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_EXIST);
        }
        if (AllParkingEnum.NO.getValue().equals(requestDto.getAllParking())) {
            if (CollectionUtils.isNotEmpty(requestDto.getParkingIds())) {
                for (Long parkingId : requestDto.getParkingIds()) {
                    ParkingInfoEntity parkingInfoEntity = parkingInfoCrudService.selectById(parkingId);
                    if (parkingInfoEntity == null) {
                        throw new ValidationException(SpecialVehicleConstant.PARKING_ID_NOT_EXIST);
                    }
                }
            } else {
                throw new ValidationException(SpecialVehicleConstant.SPECIAL_VEHICLE_PARKING_ID_NOT_NULL);
            }
        }
        return true;
    }
}
