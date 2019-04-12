package com.zhuyitech.sms.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 修改短信客户端请求
 *
 * @author walkman
 */
@ApiModel("修改短信客户端请求")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    @ApiModelProperty("客户端ID")
    @NotNull(message = "客户端ID不能为空")
    private String clientId;

    /**
     * 客户端密钥
     */
    @ApiModelProperty("客户端密钥")
    @NotNull(message = "客户端密钥不能为空")
    private String clientSecrete;

    /**
     * 客户端名称
     */
    @ApiModelProperty("客户端名称")
    @NotNull(message = "客户端名称不能为空")
    private String clientName;

    /**
     * 短信通道
     */
    @ApiModelProperty("短信通道")
    @NotNull(message = "短信通道不能为空")
    private String channelProvider;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

}
