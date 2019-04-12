package com.zhuyitech.sms.dto.result;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 验证码
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "验证码")
public class VerifyCodeResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    private String clientId;

    /**
     * 模版编号
     */
    @ApiModelProperty("模版编号")
    private String templateId;

    /**
     * 验证码类型(1-注册，2-忘记密码，3-客户平台信息)
     */
    @ApiModelProperty("验证码类型")
    private Integer verifyType;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phoneNumber;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String verifyCode;

    /**
     *
     */
    @ApiModelProperty("expiredSeconds")
    private Integer expiredSeconds;

    /**
     * 验证码发送状态(1-发送成功，2-发送失败)
     */
    @ApiModelProperty("status")
    private Integer status;

    /**
     * 使用状态(0-未使用，1-已使用)
     */
    @ApiModelProperty("used")
    private Integer used;

    /**
     * 备注
     */
    @ApiModelProperty("description")
    private String description;

}
