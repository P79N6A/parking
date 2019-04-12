package com.zoeeasy.cloud.ucc.account.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 账号登录请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AccountSignInRequestDto", description = "账号登录请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountSignInRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 是否记住我
     */
    private Boolean rememberMe;


}
