package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.validator.LotTypeEnumValidator;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 包期规则列表请求参数
 *
 * @date: 2018/10/15.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketRuleListGetRequestDto", description = "包期规则列表请求参数")
public class PacketRuleListGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 包期类型
     */
    @ApiModelProperty(value = "包期类型")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PACKET_TYPE_NOT_NULL)
    private Integer packetType;

    /**
     * 停车场类型
     */
    @ApiModelProperty("lotType")
    @FluentValidate({LotTypeEnumValidator.class})
    private String lotType;

    /**
     * 区域code
     */
    @ApiModelProperty("areaCode")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_AREA_CODE_NOT_NULL)
    private String areaCode;
}
