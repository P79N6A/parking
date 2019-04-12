package com.zoeeasy.cloud.pms.parkingarea.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
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
 * @Date: 2018/12/1
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UpdateParkingAreaRequestDto", description = "修改停车区域请求参数")
public class UpdateParkingAreaRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     *租户Id
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
     * 云平台编号
     */
    @ApiModelProperty("platAreaCode")
    @NotBlank(message = GarageConstant.PARKING_GARAGE_GARAGE_CODE_NOT_NULL)
    @Length(min = GarageConstant.PARKING_GARAGE_GARAGE_CODE_MIN_LENGTH, max = GarageConstant.PARKING_GARAGE_GARAGE_CODE_MAX_LENGTH,
            message = GarageConstant.PARKING_GARAGE_GARAGE_CODE_LENGTH_RANGE)
    @Pattern(regexp = GarageConstant.PARKING_GARAGE_CODE_PATTERN, message = GarageConstant.PARKING_GARAGE_CODE_ILLEGAL)
    private String platAreaCode;

    /**
     * 区域名称
     */
    @ApiModelProperty("parkingAreaName")
    @Length(min = ParkingAreaConstant.PARKING_AREA_NAME_MIN_LENGTH, max = ParkingAreaConstant.PARKING_AREA_NAME_MAX_LENGTH,
            message = ParkingAreaConstant.PARKING_AREA_NAME_LENGTH_RANGE)
    @NotBlank(message = ParkingAreaConstant.PARKING_AREA_NAME_NOT_NULL)
    private String parkingAreaName;

    /**
     * 车位总数
     */
    @ApiModelProperty("lotTotal")
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_LOTTOTAL_NOT_NULL)
    private Integer lotTotal;

    /**
     * 固定车位数
     */
    @ApiModelProperty("lotFixed")
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_LOTFIXED_NOT_NULL)
    private Integer lotFixed;

    /**
     * 可用车位数
     */
    @ApiModelProperty("lotAvailable")
    @NotNull(message = ParkingAreaConstant.PARKING_AREA_LOTAVAILABLE_NOT_NULL)
    private Integer lotAvailable;

    /**
     * 备注
     */
    @ApiModelProperty("remark")
    @Length(max = ParkingAreaConstant.PARKING_AREA_REMARK_MAX_LENGTH, message = ParkingAreaConstant.PARKING_AREA_REMARK_LENGTH_RANGE)
    private String remark;
}

