package com.zoeeasy.cloud.pms.parkingarea.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.validator.MaxNumberValidator;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 添加泊位区域请求参数
 * Created by song on 2018/9/21.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAreaAddRequestDto", description = "添加泊位区域请求参数")
public class ParkingAreaAddRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("parkingId")
    @NotNull(message = GarageConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     * 泊车区域编号
     */
    @ApiModelProperty("泊车区域编号")
    @NotBlank(message = ParkingAreaConstant.PARKING_AREA_CODE_NOT_NULL)
    @Length(min = ParkingAreaConstant.PARKING_AREA_CODE_MIN_LENGTH, max = ParkingAreaConstant.PARKING_AREA_CODE_MAX_LENGTH,
            message = ParkingAreaConstant.PARKING_AREA_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingAreaConstant.PARKING_AREA_CODE_PATTERN, message = ParkingAreaConstant.PARKING_AREA_CODE_ILLEGAL)
    private String code;

    /**
     * 泊位区域名称
     */
    @ApiModelProperty("泊位区域名称")
    @Length(min = ParkingAreaConstant.PARKING_AREA_NAME_MIN_LENGTH, max = ParkingAreaConstant.PARKING_AREA_NAME_MAX_LENGTH,
            message = ParkingAreaConstant.PARKING_AREA_NAME_LENGTH_RANGE)
    @NotBlank(message = ParkingAreaConstant.PARKING_AREA_NAME_NOT_NULL)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = ParkingAreaConstant.PARKING_AREA_NAME_LENGTH_RANGE)
    private String name;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_LOTTOTAL_NOT_NULL)
    @FluentValidate({MaxNumberValidator.class})
    private Integer lotTotal;

    /**
     * 固定车位总数
     */
    @ApiModelProperty("固定车位总数")
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_LOTFIXED_NOT_NULL)
    @FluentValidate({MaxNumberValidator.class})
    private Integer lotFixed;

    /**
     * 剩余车位数
     */
    @ApiModelProperty("剩余车位数")
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_LOTAVAILABLE_NOT_NULL)
    @FluentValidate({MaxNumberValidator.class})
    private Integer lotAvailable;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @Length(max = ParkingAreaConstant.PARKING_AREA_REMARK_MAX_LENGTH, message = ParkingAreaConstant.PARKING_AREA_REMARK_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = ParkingAreaConstant.PARKING_AREA_REMARK_LENGTH_RANGE)
    private String remark;

    /**
     * 楼层id
     */
    @ApiModelProperty("楼层id")
    private Long floorId;

}
