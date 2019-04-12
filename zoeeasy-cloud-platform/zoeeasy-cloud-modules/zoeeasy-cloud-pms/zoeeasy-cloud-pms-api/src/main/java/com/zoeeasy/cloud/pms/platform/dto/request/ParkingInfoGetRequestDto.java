package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/9/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingInfoGetRequestDto", description = "通过停车场id获取停车场信息")
public class ParkingInfoGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 停车场上线状态
     */
    @ApiModelProperty(value = "停车场上线状态")
    private Integer status;
}
