package com.zoeeasy.cloud.notify.push.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 设备设为无效请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InvalidPushDeviceRequestDto", description = "设备设为无效请求参数")
public class InvalidPushDeviceRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 极光推送的设备唯一标识
     */
    @ApiModelProperty(value = "极光推送的设备唯一标识", required = true)
    @NotBlank(message = "极光推送的设备唯一标识不能为空")
    private String registrationId;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @NotNull(message = "客户端类型不能为空")
    @ApiModelProperty(value = "客户端类型(1:ANDROID 2:APPLE 3:H5 4:WEB)")
    private Integer terminateType;

}
