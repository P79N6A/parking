package com.zoeeasy.cloud.integration.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.notify.cts.NotifyConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取可用车位和消息条请求参数
 *
 * @Date: 2018/11/20
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAndNotifyRequestDto", description = "获取可用车位和消息条请求参数")
public class ParkingAndNotifyRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "停车场id")
    @NotNull(message = NotifyConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;
}