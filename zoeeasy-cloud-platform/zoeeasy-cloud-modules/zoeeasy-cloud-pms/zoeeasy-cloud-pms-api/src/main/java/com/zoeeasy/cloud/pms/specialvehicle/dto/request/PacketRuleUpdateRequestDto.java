package com.zoeeasy.cloud.pms.specialvehicle.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.scapegoat.infrastructure.jackson.convert.deserializer.ToIntegerFenDeserializer;
import com.zoeeasy.cloud.pms.specialvehicle.cst.SpecialVehicleConstant;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PacketTypeEnumValidator;
import com.zoeeasy.cloud.pms.specialvehicle.validator.PlateColorEnumValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 修改包期规则请求参数
 *
 * @date: 2018/10/13.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketRuleUpdateRequestDto", description = "修改包期规则请求参数")
public class PacketRuleUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 包期规则名称
     */
    @ApiModelProperty(value = "包期规则名称")
    @NotNull(message = SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_NOT_NULL)
    @Length(min = SpecialVehicleConstant.PACKET_NAME_MIN_LENGTH, max = SpecialVehicleConstant.PACKET_NAME_MAX_LENGTH,
            message = SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_ILLEGAL)
    @Pattern(regexp = SpecialVehicleConstant.PACKET_VEHICLE_SPECIAL_SYMBOLS, message = SpecialVehicleConstant.PACKET_RULE_PACKET_NAME_ILLEGAL)
    private String packetName;

    /**
     * 包期类型，1：包月，2：包年
     */
    @ApiModelProperty(value = "包期类型")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PACKET_TYPE_NOT_NULL)
    @FluentValidate(PacketTypeEnumValidator.class)
    private Integer packetType;

    /**
     * 包期金额
     */
    @ApiModelProperty(value = "包期金额")
    @NotNull(message = SpecialVehicleConstant.PACKET_RULE_PRICE_NOT_NULL)
    @JsonDeserialize(using = ToIntegerFenDeserializer.class)
    @Range(max = SpecialVehicleConstant.INT_MAX, message = SpecialVehicleConstant.PACKET_RULE_PRICE_ERROR)
    private Integer price;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    @NotNull(message = SpecialVehicleConstant.PACKET_VEHICLE_PLATE_COLOR_NOT_NULL)
    @FluentValidate(PlateColorEnumValidator.class)
    private Integer plateColor;
}
