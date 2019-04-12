package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户退出登录请求参数
 *
 * @author AkeemSuper
 */
@ApiModel(value = "UserLogoutIntegrationRequestDto", description = "用户退出登录请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLogoutIntegrationRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 极光推送的设备唯一标识
     */
    @ApiModelProperty(value = "极光推送的设备唯一标识", required = true)
    @NotBlank(message = "极光推送的设备唯一标识不能为空")
    private String registrationId;

}
