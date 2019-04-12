package com.zoeeasy.cloud.pms.park.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.parkingarea.cst.ParkingAreaConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AddParkingLotRequestDto", description = "停车场管理系统添加泊位请求参数")
public class AddParkingLotRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Long tenantId;

    /**
     * 云平台停车场Code
     */
    @ApiModelProperty(value = "code")
    @NotBlank(message = ParkingConstant.PARKING_INFO_PARKING_CODE_NOT_NULL)
    @Length(min = ParkingConstant.PARKING_INFO_PARKING_CODE_MIN_LENGTH, max = ParkingConstant.PARKING_INFO_PARKING_CODE_MAX_LENGTH, message = ParkingConstant.PARKING_INFO_PARKING_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.PARKING_CODE_PATTERN, message = ParkingConstant.PARKING_CODE_ILLEGAL)
    private String cloudParkingCode;

    /**
     * 云平台区域Code
     */
    @ApiModelProperty("云平台区域Code")
    @Length(max = ParkingAreaConstant.PARKING_AREA_CODE_MAX_LENGTH,
            message = ParkingAreaConstant.PARKING_AREA_CODE_LENGTH_RANGE_MAX)
    @Pattern(regexp = ParkingAreaConstant.PARKING_AREA_CODE_PATTERN_CLOUD, message = ParkingAreaConstant.PARKING_AREA_CODE_ILLEGAL)
    private String platAreaCode;

    /**
     * 管理系统泊位编号
     */
    @ApiModelProperty("管理系统泊位编号")
    @NotBlank(message = ParkingLotConstant.PARKING_LOT_LOCAL_CODE_NOT_NULL)
    @Length(min = ParkingLotConstant.PARKING_LOT_LOCAL_CODE_MIN_LENGTH, max = ParkingLotConstant.PARKING_LOT_LOCAL_CODE_MAX_LENGTH,
            message = ParkingLotConstant.PARKING_LOT_LOCAL_CODE_LENGTH_RANGE)
    @Pattern(regexp = ParkingLotConstant.PARKING_LOT_LOCAL_CODE_PATTERN, message = ParkingLotConstant.PARKING_LOT_LOCAL_CODE_ILLEGAL)
    private String parkingLotCode;

    /**
     * 泊位名称
     */
    @Length(max = ParkingLotConstant.PARKING_LOT_NAME_MAX_LENGTH, message = ParkingLotConstant.PARKING_LOT_NAME_LENGTH_RANGE)
    private String parkingLotName;

    /**
     * 备注
     */
    @Length(max = ParkingLotConstant.PARKING_LOT_DESCRIPTION_MAX_LENGTH, message = ParkingLotConstant.PARKING_LOT_DESCRIPTION_LENGTH_RANGE)
    private String remark;

}
