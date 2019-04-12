package com.zoeeasy.cloud.pms.lane.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pms.lane.cst.ParkingLaneConstant;
import com.zoeeasy.cloud.pms.lane.validator.ParkingLaneTypeValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 更新车道请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneUpdateRequest", description = "更新车道请求参数")
public class ParkingLaneUpdateRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ParkingLaneConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     * 出入口ID
     */
    @ApiModelProperty("出入口ID")
    private Long gateId;

    /**
     * 车道序号
     */
    @ApiModelProperty("车道序号")
    @NotBlank(message = ParkingLaneConstant.PARKING_LANE_CODE_NOT_NULL)
    @Length(min = ParkingLaneConstant.PARKING_LANE_CODE_MIN_LENGTH,
            max = ParkingLaneConstant.PARKING_LANE_CODE_MAX_LENGTH,
            message = ParkingLaneConstant.PARKING_LANE_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingLaneConstant.PARKING_LANE_CODE_PARTTERN,
            message = ParkingLaneConstant.PARKING_LANE_CODE_ILLEGAL)
    private String code;

    /**
     * 车道名称
     */
    @ApiModelProperty("车道名称")
    @NotBlank(message = ParkingLaneConstant.PARKING_LANE_NAME_NOT_NULL)
    @Length(min = ParkingLaneConstant.PARKING_LANE_NAME_MIN_LENGTH,
            max = ParkingLaneConstant.PARKING_LANE_NAME_MAX_LENGTH,
            message = ParkingLaneConstant.PARKING_LANE_NAME_LENGTH_RANGE)
    @Pattern(regexp = ParkingLaneConstant.PARKING_LANE_NAME_PARTTERN,
            message = ParkingLaneConstant.PARKING_LANE_NAME_ILLEGAL)
    private String name;

    /**
     * 出入口方向：，1-入车道，2-出车道 3 出入车道
     */
    @ApiModelProperty("车道类型")
    @NotNull(message = ParkingLaneConstant.PARKING_LANE_DIRECTION_NOT_NULL)
    @FluentValidate({ParkingLaneTypeValidator.class})
    private Integer direction;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(max = ParkingLaneConstant.PARKING_LANE_REMARKS_MAX_LENGTH,
            message = ParkingLaneConstant.PARKING_LANE_REMARKS_LENGTH_RANGE)
    private String remarks;
}
