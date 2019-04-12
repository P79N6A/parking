package com.zoeeasy.cloud.inspect.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/11/7 0007
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkInspectorGetByParkingIdRequestDto", description = "通过停车场获取收费id请求参数")
public class ParkCollectorGetByParkingIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = InspectConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id", required = true)
    @NotNull(message = InspectConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;
}
