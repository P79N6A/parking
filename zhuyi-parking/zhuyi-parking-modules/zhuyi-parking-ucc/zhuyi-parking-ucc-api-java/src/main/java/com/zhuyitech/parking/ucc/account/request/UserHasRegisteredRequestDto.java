package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户账号是否注册请求参数
 *
 * @author shenliang
 */
@ApiModel(description = "用户账号是否注册请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasRegisteredRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty(value = "手机或微信账号或支付宝账号", required = true)
    @NotNull(message = "账号不能为空")
    private String account;

    @NotNull(message = "账号类型不能为空")
    @ApiModelProperty(value = "账号类型(1:手机 2：微信 3：支付宝)", required = true, allowableValues = "1,2,3")
    private Integer accountType;

}
