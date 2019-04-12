package com.zoeeasy.cloud.pms.garage.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.MaxNumberValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 添加车库请求参数
 * Created by song on 2018/9/20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "GarageAddRequestDto", description = "添加车库请求参数")
public class GarageAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    @NotNull(message = GarageConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车库编号
     */
    @ApiModelProperty("code")
    @NotBlank(message = GarageConstant.PARKING_GARAGE_GARAGE_CODE_NOT_NULL)
    @Length(min = GarageConstant.PARKING_GARAGE_GARAGE_CODE_MIN_LENGTH, max = GarageConstant.PARKING_GARAGE_GARAGE_CODE_MAX_LENGTH,
            message = GarageConstant.PARKING_GARAGE_GARAGE_CODE_LENGTH_RANGE)
    @Pattern(regexp = GarageConstant.PARKING_GARAGE_CODE_PATTERN, message = GarageConstant.PARKING_GARAGE_CODE_ILLEGAL)
    private String code;

    /**
     * 车库名称
     */
    @ApiModelProperty("name")
    @Length(min = GarageConstant.PARKING_GARAGE_GARAGE_NAME_MIN_LENGTH, max = GarageConstant.PARKING_GARAGE_GARAGE_NAME_MAX_LENGTH,
            message = GarageConstant.PARKING_GARAGE_GARAGE_NAME_LENGTH_RANGE)
    @NotBlank(message = GarageConstant.PARKING_GARAGE_GARAGE_NAME_NOT_NULL)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = GarageConstant.PARKING_GARAGE_GARAGE_NAME_LENGTH_RANGE)
    private String name;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_NULL)
    @FluentValidate({MaxNumberValidator.class})
    private Integer lotCount;

    /**
     * 固定车位数
     */
    @ApiModelProperty("固定车位数")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_NULL)
    @FluentValidate({MaxNumberValidator.class})
    private Integer lotFixed;

    /**
     * 剩余车位数
     */
    @ApiModelProperty("剩余车位数")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_NULL)
    @FluentValidate({MaxNumberValidator.class})
    private Integer lotAvailable;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(max = GarageConstant.PARKING_GARAGE_GARAGE_REMARK_MAX_LENGTH, message = GarageConstant.PARKING_GARAGE_GARAGE_REMARK_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = GarageConstant.PARKING_GARAGE_GARAGE_REMARK_LENGTH_RANGE)
    private String remark;

    /**
     * 蓝牙id
     */
    @ApiModelProperty("蓝牙id")
    private String bleUuid;

}
