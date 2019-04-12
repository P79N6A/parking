package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 可预约停车场详情
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointmentParkingDetailRequestDto", description = "可预约停车场详情")
public class AppointmentParkingDetailRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场Id
     */
    @ApiModelProperty(value = "停车场Id", required = true)
    @NotNull(message = IntegrationConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

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