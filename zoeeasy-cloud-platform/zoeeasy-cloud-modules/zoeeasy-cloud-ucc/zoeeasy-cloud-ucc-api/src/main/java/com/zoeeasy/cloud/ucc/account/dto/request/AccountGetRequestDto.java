package com.zoeeasy.cloud.ucc.account.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 获取用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AccountGetRequestDto", description = "获取用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * account
     */
    @ApiModelProperty(value = "account")
    @NotEmpty(message = UccConstant.ACCOUNT_CANNOT_EMPTY)
    private String account;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址")
    private String emailAddress;

}
