package com.zhuyitech.sms.dto.request;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 验证码校验参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("验证码校验结果")
public class VerifyCodeCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 验证码是否有效
     */
    private Boolean valid;

}
