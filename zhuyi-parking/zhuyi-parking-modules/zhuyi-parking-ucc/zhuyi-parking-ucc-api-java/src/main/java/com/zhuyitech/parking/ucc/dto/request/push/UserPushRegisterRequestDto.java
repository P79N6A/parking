package com.zhuyitech.parking.ucc.dto.request.push;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 推送设备注册
 */
@ApiModel(value = "UserPushRegisterRequestDto", description = "推送设备注册")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPushRegisterRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 设备类型
     */
    @ApiModelProperty(required = true, value = "设备类型 1: Android 2 iPhone 3 iPad", allowableValues = "1,2,3")
    @NotNull(message = "设备类型不能为空")
    private Integer terminateType;

    /**
     * 设备类型
     */
    @ApiModelProperty(required = true, value = "极光推送的设备唯一性标识")
    @NotEmpty(message = "设备唯一性标识不能为空")
    private String registrationID;

}
