package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingPacketRuleDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long ruleId;

    private Long parkingId;

}
