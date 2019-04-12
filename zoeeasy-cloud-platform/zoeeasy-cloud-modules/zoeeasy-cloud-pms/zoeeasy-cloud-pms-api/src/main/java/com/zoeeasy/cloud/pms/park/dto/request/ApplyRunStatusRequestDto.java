package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 申请停车场请求参数
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ApplyRunStatusRequestDto", description = "申请停车场请求参数")
public class ApplyRunStatusRequestDto extends SignedSessionRequestDto {

    /**
     * 停车场id
     */
    @ApiModelProperty("parkingId")
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 申请类别 1上线申请2下线申请
     */
    @ApiModelProperty("申请类别 1上线申请2下线申请")
    @NotNull(message = ParkingConstant.APPLY_TYPE_NOT_NULL)
    private Integer applyType;

    /**
     * 申请说明
     */
    @ApiModelProperty("申请说明")
    @Length(max = ParkingConstant.APPLY_REMARK_MAX_LENGTH, message = ParkingConstant.APPLY_REMARK_LENGTH_RANGE)
    @Pattern(regexp = ParkingConstant.SPECIAL_CHARACTER_PATTERN, message = ParkingConstant.APPLY_REMARK_LENGTH_RANGE)
    private String applyRemark;

}
