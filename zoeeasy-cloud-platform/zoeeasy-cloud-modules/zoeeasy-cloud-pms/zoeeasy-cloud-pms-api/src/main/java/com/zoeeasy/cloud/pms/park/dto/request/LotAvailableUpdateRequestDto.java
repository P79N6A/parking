package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @date: 2018/12/1.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LotAvailableUpdateRequestDto", description = "修改可用车位请求参数")
public class LotAvailableUpdateRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 可用车位数
     */
    @ApiModelProperty("可用车位数")
    @Range(max = ParkingConstant.PARKING_LOTAVAILABLE_MAX, message = ParkingConstant.LOT_AVAILABLE_ERROR_MESSAGE)
    private Integer lotAvailable;
}
