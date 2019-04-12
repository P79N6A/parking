package com.zoeeasy.cloud.pms.gate.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.gate.validator.ParkingGateTypeValidator;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 修改停车场出入口请求参数
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UpdateGateInfoRequestDto", description = "修改停车场出入口请求参数")
public class UpdateGateInfoRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Long tenantId;

    /**
     * 云平台停车场Code
     */
    @ApiModelProperty("cloudParkingCode")
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_INFO_PARKING_CODE_MIN_LENGTH, max = ParkingConstant.PARKING_INFO_PARKING_CODE_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.PARKING_CODE_PATTERN, message = ParkingConstant.PARKING_CODE_ILLEGAL)
    private String cloudParkingCode;

    /**
     * 云平台车库编号
     */
    @ApiModelProperty("cloudGarageCode")
    @Length(min = GarageConstant.PARKING_GARAGE_GARAGE_CODE_MIN_LENGTH, max = GarageConstant.PARKING_GARAGE_GARAGE_CODE_MAX_LENGTH,
            message = GarageConstant.PARKING_GARAGE_GARAGE_CODE_LENGTH_RANGE)
    @Pattern(regexp = GarageConstant.PARKING_GARAGE_CODE_PATTERN, message = GarageConstant.PARKING_GARAGE_CODE_ILLEGAL)
    private String cloudGarageCode;

    /**
     * 云平台出入口编号
     */
    @ApiModelProperty("platGateCode")
    @NotBlank(message = ParkingGateConstant.PARKING_GATE_CODE_NOT_NULL)
    @Length(min = ParkingGateConstant.PARKING_GATE_CODE_MIN_LENGTH,
            max = ParkingGateConstant.PARKING_GATE_CODE_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingGateConstant.PARKING_GATE_CODE_PARTTERN,
            message = ParkingGateConstant.PARKING_GATE_CODE_ILLEGAL)
    private String platGateCode;

    /**
     * 出入口名称
     */
    @ApiModelProperty("gateName")
    @NotBlank(message = ParkingGateConstant.PARKING_GATE_NAME_NOT_NULL)
    @Length(max = ParkingGateConstant.PARKING_GATE_NAME_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_NAME_LENGTH_RANGE)
    @Pattern(regexp = ParkingGateConstant.PARKING_GATE_NAME_PARTTERN,
            message = ParkingGateConstant.PARKING_GATE_NAME_ILLEGAL)
    private String gateName;

    /**
     * 出入口方向:
     * 1.入口
     * 2.出口
     * 3.出入口
     */
    @ApiModelProperty("direction")
    @NotNull(message = ParkingGateConstant.PARKING_GATE_DIRECTION_NOT_NULL)
    @FluentValidate({ParkingGateTypeValidator.class})
    private Integer direction;

    /**
     * 备注
     */
    @ApiModelProperty("remark")
    @Length(max = ParkingGateConstant.PARKING_GATE_REMARKS_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_REMARKS_LENGTH_RANGE)
    private String remark;
}
