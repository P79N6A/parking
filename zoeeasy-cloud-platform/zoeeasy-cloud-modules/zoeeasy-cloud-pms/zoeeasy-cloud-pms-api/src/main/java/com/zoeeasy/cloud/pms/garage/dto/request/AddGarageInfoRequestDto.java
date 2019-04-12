package com.zoeeasy.cloud.pms.garage.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.garage.cst.GarageConstant;
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
 * 添加车库信息请求参数
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddGarageInfoRequestDto", description = "添加车库信息请求参数")
public class AddGarageInfoRequestDto extends SignedSessionRequestDto {

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
     * 管理系统车库编号
     */
    @ApiModelProperty("garageCode")
    @NotBlank(message = GarageConstant.PARKING_GARAGE_GARAGE_CODE_NOT_NULL)
    @Length(min = GarageConstant.GARAGE_CODE_MIN_LENGTH, max = GarageConstant.GARAGE_CODE_MAX_LENGTH,
            message = GarageConstant.GARAGE_CODE_LENGTH_RANGE_LENGTH_RANGE)
    @Pattern(regexp = GarageConstant.GARAGE_CODE_PATTERN, message = GarageConstant.PARKING_GARAGE_CODE_ILLEGAL)
    private String garageCode;

    /**
     * 车库名称
     */
    @ApiModelProperty("garageName")
    @Length(min = GarageConstant.PARKING_GARAGE_GARAGE_NAME_MIN_LENGTH, max = GarageConstant.PARKING_GARAGE_GARAGE_NAME_MAX_LENGTH,
            message = GarageConstant.PARKING_GARAGE_GARAGE_NAME_LENGTH_RANGE)
    @NotBlank(message = GarageConstant.PARKING_GARAGE_GARAGE_NAME_NOT_NULL)
    private String garageName;

    /**
     * 车位总数
     */
    @ApiModelProperty("lotTotal")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTCOUNT_NOT_NULL)
    private Integer lotTotal;

    /**
     * 固定车位数
     */
    @ApiModelProperty("lotFixed")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTFIXED_NOT_NULL)
    private Integer lotFixed;

    /**
     * 出入口数量
     */
    @ApiModelProperty("portNumber")
    private Integer portNumber;

    /**
     * 可用车位数
     */
    @ApiModelProperty("lotAvailable")
    @NotNull(message = GarageConstant.PARKING_GARAGE_GARAGE_LOTAVAILABLE_NOT_NULL)
    private Integer lotAvailable;

    /**
     * 备注
     */
    @ApiModelProperty("remark")
    @Length(max = GarageConstant.PARKING_GARAGE_GARAGE_REMARK_MAX_LENGTH, message = GarageConstant.PARKING_GARAGE_GARAGE_REMARK_LENGTH_RANGE)
    private String remark;
}
