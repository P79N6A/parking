package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 根据parkingId获取停车场预约详情请求参数
 *
 * @author zwq
 * @date 2019-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointInfoGetByParkingIdRequestDto", description = "根据parkingId获取停车场预约详情请求参数")
public class ParkingAppointInfoGetByParkingIdRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * parkingId
     */
    @ApiModelProperty(value = "parkingId", required = true)
    @NotNull(message = "parkingId不能为空")
    private Long parkingId;
}
