package com.zoeeasy.cloud.pms.gate.dto.request;


import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateTypeValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 更新停车场出入口请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateUpdateRequestDto", description = "更新停车场出入口请求参数")
public class ParkingGateUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ParkingGateConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;
    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;
    /**
     * 出入口编号
     */
    @ApiModelProperty("出入口编号")
    @NotBlank(message = ParkingGateConstant.PARKING_GATE_CODE_NOT_NULL)
    @Length(min = ParkingGateConstant.PARKING_GATE_CODE_MIN_LENGTH,
            max = ParkingGateConstant.PARKING_GATE_CODE_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingGateConstant.PARKING_GATE_CODE_PARTTERN,
            message = ParkingGateConstant.PARKING_GATE_CODE_ILLEGAL)
    private String code;
    /**
     * 出入口名称
     */
    @ApiModelProperty("出入口名称")
    @NotBlank(message = ParkingGateConstant.PARKING_GATE_NAME_NOT_NULL)
    @Length(max = ParkingGateConstant.PARKING_GATE_NAME_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_NAME_LENGTH_RANGE)
    @Pattern(regexp = ParkingGateConstant.PARKING_GATE_NAME_PARTTERN,
            message = ParkingGateConstant.PARKING_GATE_NAME_ILLEGAL)
    private String name;
    /**
     * 出入口方向:
     * 1.入口
     * 2.出口
     * 3.出入口
     */
    @ApiModelProperty("出入口类型")
    @NotNull(message = ParkingGateConstant.PARKING_GATE_DIRECTION_NOT_NULL)
    @FluentValidate({ParkingGateTypeValidator.class})
    private Integer direction;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(max = ParkingGateConstant.PARKING_GATE_REMARKS_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_REMARKS_LENGTH_RANGE)
    private String remarks;
}
