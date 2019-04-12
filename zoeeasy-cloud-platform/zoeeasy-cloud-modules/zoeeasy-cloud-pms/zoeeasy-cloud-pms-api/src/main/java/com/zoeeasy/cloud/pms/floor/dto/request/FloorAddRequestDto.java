package com.zoeeasy.cloud.pms.floor.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.floor.cst.FloorConstant;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 楼层添加请求参数
 *
 * Created by song on 2019/3/23 10:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FloorAddRequestDto", description = "楼层添加请求参数")
public class FloorAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = PmsConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车库id
     */
    @ApiModelProperty(value = "车库ID")
    @NotNull(message = GarageConstant.PARKING_GARAGE_ID_NOT_NULL)
    private Long garageId;

    /**
     * 楼层编号
     */
    @ApiModelProperty(value = "楼层编号")
    @NotNull(message = GarageConstant.FLOOR_CODE_NOT_NULL)
    @Length(min = FloorConstant.FLOOR_CODE_MIN_LENGTH, max = FloorConstant.FLOOR_CODE_MAX_LENGTH, message = FloorConstant.FLOOR_CODE_LENGTH_RANGE)
    @Pattern(regexp = FloorConstant.FLOOR_CODE_PATTERN, message = FloorConstant.FLOOR_CODE_ILLEGAL)
    private String floorCode;

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    @NotNull(message = GarageConstant.FLOOR_NAME_NOT_NULL)
    @Length(min = FloorConstant.FLOOR_NAME_MIN_LENGTH, max = FloorConstant.FLOOR_NAME_MAX_LENGTH, message = FloorConstant.FLOOR_NAME_LENGTH_RANGE)
    @Pattern(regexp = FloorConstant.SPECIAL_CHARACTER_PATTERN, message = FloorConstant.FLOOR_NAME_LENGTH_RANGE)
    private String floorName;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_NULL)
    @Range(min = FloorConstant.FLOOR_NUMBER_MIN_SIZE, max = FloorConstant.FLOOR_NUMBER_MAX_SIZE, message = FloorConstant.LOT_COUNT_ERROR_MESSAGE)
    private Integer lotCount;

    /**
     * 固定车位数
     */
    @ApiModelProperty("固定车位数")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_NULL)
    @Range(min = FloorConstant.FLOOR_NUMBER_MIN_SIZE, max = FloorConstant.FLOOR_NUMBER_MAX_SIZE, message = FloorConstant.LOT_FIXED_ERROR_MESSAGE)
    private Integer lotFixed;

    /**
     * 剩余车位数
     */
    @ApiModelProperty("剩余车位数")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_NULL)
    @Range(min = FloorConstant.FLOOR_NUMBER_MIN_SIZE, max = FloorConstant.FLOOR_NUMBER_MAX_SIZE, message = FloorConstant.LOT_AVAILABLE_ERROR_MESSAGE)
    private Integer lotAvailable;

    /**
     * 蓝牙导航图
     */
    @ApiModelProperty("蓝牙导航图")
    private String bleNavigationPic;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
