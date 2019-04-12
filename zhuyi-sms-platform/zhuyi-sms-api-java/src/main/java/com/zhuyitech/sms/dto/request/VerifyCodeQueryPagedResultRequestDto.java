package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zhuyitech.sms.enums.VerifyTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 验证码分页查询参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VerifyCodeQueryPagedResultRequestDto", description = "验证码分页查询参数")
public class VerifyCodeQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    /**
     * 模板ID
     */
    @ApiModelProperty(value = "模板ID")
    private String templateId;

    /**
     * 验证类型{@link VerifyTypeEnum}
     */
    @ApiModelProperty(value = "验证类型")
    private Integer verifyType;

}
