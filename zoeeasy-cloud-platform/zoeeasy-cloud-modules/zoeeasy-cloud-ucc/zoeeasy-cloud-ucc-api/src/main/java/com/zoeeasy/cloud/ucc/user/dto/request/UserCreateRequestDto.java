package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 创建用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserCreateRequestDto", description = "创建用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCreateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(required = true, value = "用户名")
    @NotBlank(message = UccConstant.ACCOUNT_NAME_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_NAME_MINI_LENGTH,
            max = UccConstant.ACCOUNT_NAME_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_NAME_PATTERN, message = UccConstant.ACCOUNT_NAME_ILLEGAL)
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码")
    @NotBlank(message = UccConstant.ACCOUNT_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(required = true, value = "确认密码")
    @NotBlank(message = UccConstant.ACCOUNT_CONFIRM_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String confirmPassword;

    /**
     * 用户姓名
     */
    private String realName;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String emailAddress;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private Long organizationId;

    /**
     *
     */
    @ApiModelProperty("角色ID列表")
    private List<Long> roleIds;

}
