package com.zhuyitech.sms.dto.request;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MessageSendRequestDto", description = "短信发送请求参数")
public class MessageSendRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "clientId", required = true, notes = "客户端ID")
    @NotEmpty(message = "客户端ID不能为空")
    private String clientId;

    /**
     * 短信模板ID
     */
    @ApiModelProperty(value = "templateId", required = true, notes = "短信模板ID")
    @NotEmpty(message = "短信模板ID不能为空")
    private String templateId;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "phoneNumber", required = true, notes = "单个手机号码")
    @NotEmpty(message = "手机号码不能为空")
    @Mobile
    private String phoneNumber;

    /**
     * 验证码类型(1, 注册 2, 登录 3, 忘记密码)
     */
    @ApiModelProperty(value = "verifyType", notes = "验证码类型(1, 注册 2, 登录 3, 忘记密码),验证码类短信必须")
    private Integer verifyType;

    /**
     * 短信参数
     *
     * @return
     */
    @ApiModelProperty(value = "parameters", notes = "短信参数")
    private Map<String, String> parameters;

}
