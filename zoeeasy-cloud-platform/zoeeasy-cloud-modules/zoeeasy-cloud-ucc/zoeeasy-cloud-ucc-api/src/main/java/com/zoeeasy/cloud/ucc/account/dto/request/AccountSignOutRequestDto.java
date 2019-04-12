package com.zoeeasy.cloud.ucc.account.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账号登录请求参数
 *
 * @author AkeemSuper
 */
@ApiModel(value = "AccountSignOutRequestDto", description = "账号登出请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountSignOutRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
