package com.zoeeasy.cloud.ucc.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/11/6 0006
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserGetByUserIdRequestDto", description = "根据userid获取用户信息")
public class UserGetByUserIdRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}
