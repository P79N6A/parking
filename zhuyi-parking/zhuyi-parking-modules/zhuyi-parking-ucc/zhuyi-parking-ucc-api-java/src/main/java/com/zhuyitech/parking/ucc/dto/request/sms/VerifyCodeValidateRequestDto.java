package com.zhuyitech.parking.ucc.dto.request.sms;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 验证码验证请求参数
 *
 * @author walkman
 */
@ApiModel(value = "VerifyCodeValidateRequestDto", description = "验证码发送请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyCodeValidateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "phoneNumber", required = true, notes = "单个手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Mobile
    private String phoneNumber;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true, notes = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 短信回执ID
     */
    private String smsRequestId;

}