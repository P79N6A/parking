package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取附近可预约停车场请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointmentParkingRequestDto", description = "分页获取可预约停车场请求参数")
public class AppointmentAroundParkingGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = IntegrationConstant.LONGITUDE_NOT_EMPTY)
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = IntegrationConstant.LATITUDE_ID_NOT_EMPTY)
    private Double latitude;

}