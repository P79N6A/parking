package com.zhuyitech.parking.ucc.dto.request.sms;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 验证码发送请求参数
 *
 * @author walkman
 */
@ApiModel(value = "VerifyCodeSendRequestDto", description = "验证码发送请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyCodeSendRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "phoneNumber", required = true, notes = "单个手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Mobile
    private String phoneNumber;

    /**
     * 验证码类型
     */
    @ApiModelProperty(value = "验证码类型(1, 注册 2, 登录 3, 忘记密码 4.密码设置 5.支付密码),验证码类短信必须", required = true)
    @NotNull(message = "验证码类型不能为空")
    private Integer verifyType;

}
