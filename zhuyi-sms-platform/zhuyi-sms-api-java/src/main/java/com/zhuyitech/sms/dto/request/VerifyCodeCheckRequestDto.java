package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 验证码校验参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("验证码校验参数")
public class VerifyCodeCheckRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "clientId", required = true, notes = "客户端ID")
    @NotEmpty(message = "客户端ID不能为空")
    private String clientId;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", required = true, notes = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    @Mobile(message = "无效的手机号码")
    private String phoneNumber;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true, notes = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 短信回执Id
     */
    @ApiModelProperty(value = "短信回执Id")
    private String smsRequestId;

}
