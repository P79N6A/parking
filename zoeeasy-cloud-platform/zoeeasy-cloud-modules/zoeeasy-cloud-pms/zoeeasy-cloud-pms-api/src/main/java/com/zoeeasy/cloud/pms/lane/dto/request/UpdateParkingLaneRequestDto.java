package com.zoeeasy.cloud.pms.lane.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.gate.cst.ParkingGateConstant;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneTypeValidator;
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
 * 修改车道请求参数
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UpdateParkingLaneRequestDto", description = "修改车道请求参数")
public class UpdateParkingLaneRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Long tenantId;

    /**
     * 云平台停车场Code
     */
    @ApiModelProperty("云平台停车场Code")
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
    @Length(min = ParkingGateConstant.PARKING_GATE_CODE_MIN_LENGTH,
            max = ParkingGateConstant.PARKING_GATE_CODE_MAX_LENGTH,
            message = ParkingGateConstant.PARKING_GATE_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingGateConstant.PARKING_GATE_CODE_PARTTERN,
            message = ParkingGateConstant.PARKING_GATE_CODE_ILLEGAL)
    private String platGateCode;

    /**
     * 云平台车道编号
     */
    @ApiModelProperty("platLaneCode")
    @NotBlank(message = ParkingLaneConstant.PARKING_LANE_CODE_NOT_NULL)
    @Length(min = ParkingLaneConstant.PARKING_LANE_CODE_MIN_LENGTH,
            max = ParkingLaneConstant.PARKING_LANE_CODE_MAX_LENGTH,
            message = ParkingLaneConstant.PARKING_LANE_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingLaneConstant.PARKING_LANE_CODE_PARTTERN,
            message = ParkingLaneConstant.PARKING_LANE_CODE_ILLEGAL)
    private String platLaneCode;

    /**
     * 车道名称
     */
    @ApiModelProperty("laneName")
    @NotBlank(message = ParkingLaneConstant.PARKING_LANE_NAME_NOT_NULL)
    @Length(min = ParkingLaneConstant.PARKING_LANE_NAME_MIN_LENGTH,
            max = ParkingLaneConstant.PARKING_LANE_NAME_MAX_LENGTH,
            message = ParkingLaneConstant.PARKING_LANE_NAME_LENGTH_RANGE)
    @Pattern(regexp = ParkingLaneConstant.PARKING_LANE_NAME_PARTTERN,
            message = ParkingLaneConstant.PARKING_LANE_NAME_ILLEGAL)
    private String laneName;

    /**
     * 出入口方向：，1-入车道，2-出车道 3 出入车道
     */
    @ApiModelProperty("direction")
    @NotNull(message = ParkingLaneConstant.PARKING_LANE_DIRECTION_NOT_NULL)
    @FluentValidate({ParkingLaneTypeValidator.class})
    private Integer direction;

    /**
     * 备注
     */
    @ApiModelProperty("remark")
    @Length(max = ParkingLaneConstant.PARKING_LANE_REMARKS_MAX_LENGTH,
            message = ParkingLaneConstant.PARKING_LANE_REMARKS_LENGTH_RANGE)
    private String remark;
}
