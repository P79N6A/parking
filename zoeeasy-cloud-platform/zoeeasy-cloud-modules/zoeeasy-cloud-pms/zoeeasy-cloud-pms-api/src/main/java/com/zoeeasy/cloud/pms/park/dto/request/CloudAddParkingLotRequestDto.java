package com.zoeeasy.cloud.pms.park.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 添加泊位数据请求参数
 *
 * @Date: 2019/3/27
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CloudAddParkingLotRequestDto", description = "添加泊位数据请求参数")
public class CloudAddParkingLotRequestDto extends SignedSessionRequestDto {

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
    @ApiModelProperty("云平台车库编号")
    @Length(max = ParkingConstant.MAX, message = ParkingConstant.LENGTH_RANGE)
    private String cloudGarageCode;

    /**
     * 过车流水号 过车记录编号
     */
    @ApiModelProperty("过车流水号 过车记录编号")
    @NotBlank(message = ParkingConstant.PASSING_CODE_NOT_EMPTY)
    @Length(min = ParkingConstant.MIN, max = ParkingConstant.MAX, message = ParkingConstant.LENGTH_RANGE)
    private String passingCode;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    @NotBlank(message = ParkingConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;

    /**
     * 过车方向
     */
    @ApiModelProperty("过车方向 0未知 1入车 2出车")
    private Integer direction;

    /**
     * 全景图像
     */
    @ApiModelProperty("全景图像")
    private String fullImage;

    /**
     * 车牌图像
     */
    @ApiModelProperty("车牌图像")
    private String plateImage;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 泊位号
     */
    @ApiModelProperty("泊位号")
    @NotBlank(message = ParkingConstant.PARKING_LOT_NUMBER_NOT_NULL)
    @Length(max = ParkingConstant.MAX, message = ParkingConstant.LENGTH_RANGE)
    private String parkingLotNumber;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    @NotBlank(message = ParkingConstant.PARKING_LOT_CODE_NOT_NULL)
    @Length(max = ParkingConstant.MAX, message = ParkingConstant.LENGTH_RANGE)
    private String parkingLotCode;

}
