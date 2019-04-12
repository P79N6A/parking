package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/12/7 0007
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLotAppointUpdateRequestDto", description = "根据预约状态更新可预约车位")
public class ParkingLotAppointUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = "停车场ID不能为空")
    private Long parkingId;

    /**
     * 是否递增
     */
    private Boolean increase;
}
